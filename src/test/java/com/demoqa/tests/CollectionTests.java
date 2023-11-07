package com.demoqa.tests;

import com.demoqa.api.authorization.models.AuthorizationResponseModel;
import com.demoqa.collections.CollectionsApi;
import com.demoqa.collections.models.BookModel;
import com.demoqa.helpers.WithLogin;
import com.demoqa.web.BaseWebTest;
import com.demoqa.web.pages.ProfilePage;
import io.qameta.allure.*;

import io.qameta.allure.internal.shadowed.jackson.databind.cfg.ContextAttributes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;


import static com.demoqa.api.authorization.AuthorizationApi.getAuthResponse;
import static com.demoqa.collections.CollectionsApi.*;
import static com.demoqa.tests.TestData.*;

import static com.demoqa.web.pages.ProfilePage.checkBookIsPresent;
import static io.qameta.allure.Allure.step;

@Epic("Book Store Application Tests")
@Feature("Base API Tools")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://demoqa.com/profile")
@Severity(SeverityLevel.BLOCKER)
public class CollectionTests extends BaseWebTest {
    protected CollectionsApi api = new CollectionsApi();
    protected ProfilePage web = new ProfilePage();

    @WithLogin
    @Story(value = "Добавление книг")
    @DisplayName("Тест добавления книги в коллекцию")
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


}
