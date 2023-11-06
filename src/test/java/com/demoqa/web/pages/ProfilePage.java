package com.demoqa.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.api.authorization.models.AuthorizationResponseModel;
import com.demoqa.collections.models.BookModel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ProfilePage {

    static SelenideElement

   userNameValue= $("#userName-value"),
    fieldBook=$(".ReactTable");


    public  ProfilePage searchValidLogin(String login){
        userNameValue.shouldHave(text(login));
        return this;
    }

    public static void checkSuccessfulLogin(AuthorizationResponseModel authResponse) {

        step("Checking if the login was successful", () -> {
            open("/profile");
            userNameValue.shouldHave(Condition.text(authResponse.getUsername()));
        });
    }


    public static void checkBookIsPresent(BookModel book) {

        step("Checking book in the collection", () -> {
            open("/profile");
            fieldBook.shouldHave(Condition.text(book.getTitle()));
        });
    }
}
