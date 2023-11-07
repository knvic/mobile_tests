package com.demoqa.web;


import com.demoqa.models.LoginBodyModel;
import com.demoqa.web.pages.LoginPage;
import com.demoqa.web.pages.ProfilePage;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static com.demoqa.tests.TestData.login;

import static com.demoqa.tests.TestData.password;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginTests extends BaseWebTest {
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage =new ProfilePage();

    @Test
    void successfulLoginWithUiTest() {

        step("Открытие сайта", () -> {
            open("/login");
        });

        step("Ввод логина и пароля", () -> {
            loginPage
                    .setUserName(login)
                    .setPassword(password)
                    .pressEnterKey();
        });

        step("Проверка авторизации. В поле User Name логин, введенный в процессе авторизации.", () -> {
            profilePage
                    .searchValidLogin(login);
        });


        }

    @Test
    void successfulLoginWithApiTest() {

        LoginBodyModel authData=new LoginBodyModel("knvik021","*Test021knvik");
        Response authResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open("/profile");
        $("#userName-value").shouldHave(text(login));
    }
}
