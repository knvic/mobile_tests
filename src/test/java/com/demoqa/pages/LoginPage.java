package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.api.authorization.AuthorizationResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginPage {
    SelenideElement
    userName=$("#userName"),
    password= $("#password");


    public  LoginPage setUserName(String login){
        userName.setValue(login);
        return this;
    }
    public  LoginPage setPassword (String pass){
        password.setValue(pass);
        return this;
    }
    public  LoginPage pressEnterKey (){
        password.pressEnter();
        return this;
    }

    public static void setAuthCookies(AuthorizationResponseModel authResponse) {

        step("Добавляем cookies авторизации", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
            getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        });
    }

}
