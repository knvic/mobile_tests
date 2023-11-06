package com.demoqa.tests;

import com.demoqa.api.authorization.models.AuthorizationResponseModel;
import com.demoqa.collections.models.BookModel;
import com.demoqa.helpers.WithLogin;
import com.demoqa.web.BaseWebTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.api.authorization.AuthorizationApi.getAuthResponse;
import static com.demoqa.collections.CollectionsApi.*;
import static com.demoqa.tests.TestData.*;
import static com.demoqa.web.pages.ProfilePage.checkBookIsPresent;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
@Epic("Book Store Application Tests")
@Feature("Base API Tools")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://demoqa.com/profile")
@Severity(SeverityLevel.BLOCKER)
public class CollectionTests extends BaseWebTest {


    @WithLogin
    @Story(value = "Добавление книг")
    @DisplayName("Тест добавления книги в коллекцию")
    @Description("Авторизованы. Удаляем книги из профайла. Добавляем новую. Проверяем ее видимость в профайле.")
    @Test
    void addBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        checkBookIsPresent(book);
    }


}
