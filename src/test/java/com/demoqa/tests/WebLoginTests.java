package com.demoqa.tests;


import com.demoqa.pages.LoginPage;
import com.demoqa.pages.ProfilePage;
import io.qameta.allure.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import static com.demoqa.helpers.TestData.login;

import static com.demoqa.helpers.TestData.password;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Epic("Book Store Application Tests")
@Feature("WEB Tests")
@Owner("krivorotovnv")
@Link(value = "Testing", url = "https://demoqa.com/profile")
@Severity(SeverityLevel.BLOCKER)
@Tags({
        @Tag("web"),
        @Tag("all")
})
public class WebLoginTests extends BaseTest {
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
