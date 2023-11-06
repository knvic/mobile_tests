package com.demoqa.tests;

import com.demoqa.api.authorization.models.AuthorizationResponseModel;
import com.demoqa.web.BaseWebTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.demoqa.api.authorization.AuthorizationApi.getAuthResponse;
import static com.demoqa.tests.TestData.getCredentials;
import static com.demoqa.web.pages.LoginPage.setAuthCookies;
import static com.demoqa.web.pages.ProfilePage.checkSuccessfulLogin;


public class LoginTest extends BaseWebTest {


        @Story(value = "Authorization")
        @DisplayName("Checking successful authorization")
        @Test
        void successfulLoginTest() {

            AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

            setAuthCookies(authResponse);
            checkSuccessfulLogin(authResponse);
        }
    }


