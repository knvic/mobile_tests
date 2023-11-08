package com.demoqa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.api.authorization.AuthorizationResponseModel;
import com.demoqa.api.models.BookModel;

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


    public  void checkBookIsPresent(BookModel book) {


            open("/profile");
            fieldBook.shouldHave(Condition.text(book.getTitle()));

    }
    public void checkBookIsNotPresent(BookModel book) {


        open("/profile");
        fieldBook.shouldNotHave(Condition.text(book.getTitle()));

    }
}
