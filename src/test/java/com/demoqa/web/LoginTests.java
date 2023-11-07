package com.demoqa.web;


import com.demoqa.models.LoginBodyModel;
import com.demoqa.tests.BaseTest;
import com.demoqa.web.pages.LoginPage;
import com.demoqa.web.pages.ProfilePage;
import io.qameta.allure.*;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
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
@Epic("Book Store Application Tests")
@Feature("WEB Tests")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://demoqa.com/profile")
@Severity(SeverityLevel.BLOCKER)
@Tags({
        @Tag("web"),
        @Tag("all")
})
public class LoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage =new ProfilePage();
    @Story(value = "Авторизация с Web ")
    @DisplayName("Проверка ввода учетных данных")
    @Description("Открываем страницу авторизации, вводим учетные данные. Проверяем что мы вошли под нужной учеткой")
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

   }
