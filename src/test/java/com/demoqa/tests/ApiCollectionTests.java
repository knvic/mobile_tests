package com.demoqa.tests;

import com.demoqa.api.authorization.AuthorizationResponseModel;
import com.demoqa.api.CollectionsApi;
import com.demoqa.api.models.BookModel;
import com.demoqa.helpers.WithLogin;
import com.demoqa.pages.ProfilePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import static com.demoqa.api.models.AuthorizationApi.getAuthResponse;
import static com.demoqa.helpers.TestData.*;
import static io.qameta.allure.Allure.step;

@Epic("Book Store Application Tests")
@Feature("Base API Tools")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://demoqa.com/profile")
@Severity(SeverityLevel.BLOCKER)

@Tags({
        @Tag("api"),
        @Tag("all")
})
public class ApiCollectionTests extends BaseTest {
    protected CollectionsApi api = new CollectionsApi();
    protected ProfilePage web = new ProfilePage();

    @WithLogin
    @Story(value = "Добавление книги")
    @DisplayName("Тест добавления книги в коллекцию профайла")
    @Description("Авторизованы. Удаляем книги из профайла. Добавляем новую. Проверяем ее видимость в профайле.")
    @Test
    void addBookToTheCollectionTest() {
        AuthorizationResponseModel authResponse =
                step("Получаем учетные данные провиля для дальнейшей работы", () ->
                        getAuthResponse(getCredentials())
                );
        BookModel book =
                step("Выбоор случайной книги из списка используя комбинацию классов ThreadLocal и Random для достижения " +
                        "лучшей производительности в многопоточной среде.", () ->
                        api
                                .getRandomBook()
                );
        step("Удаляем все книги из профайла", () ->
                api
                        .deleteAllBooks(authResponse)
        );
        step("Добавляем книгу в профайл", () ->
                api
                        .addBookToTheCollection(authResponse, book)
        );
        step("Проверяем присутствие книги в профайле", () -> {
                web
                    .checkBookIsPresent(book);
        });
    }


    @Test
    @WithLogin
    @Story(value = "Удаление книги")
    @DisplayName("Удаление одной книги из коллекции профайла")
    @Description("Авторизованы, Добавляем и далее удаляем книгу из коллекции профайла. Проверяем отсутствие после удаления")
    void deleteSingleBookFromTheCollectionTest() {

        AuthorizationResponseModel authResponse =
                step("Получаем учетные данные провиля для дальнейшей работы", () ->
                        getAuthResponse(getCredentials())
                );
        BookModel book =
                step("Выбоор случайной книги из списка используя комбинацию классов ThreadLocal и Random для достижения " +
                        "лучшей производительности в многопоточной среде.", () ->
                        api
                                .getRandomBook()
                );
        step("Удаляем все книги из профайла", () ->
                api
                        .deleteAllBooks(authResponse)
        );
        step("Добавляем книгу в профайл", () ->
                api
                        .addBookToTheCollection(authResponse, book)
        );

        step("Удаление книги из профайла", () ->
        api
                .deleteBookFromTheCollection(authResponse, book)
        );
        step("Проверяем, что удаленной книги нет в коллекции профайла.", () -> {
        web.checkBookIsNotPresent(book);
        });
    }

}
