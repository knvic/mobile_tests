package com.demoqa.helpers;


import com.demoqa.api.authorization.models.AuthorizationResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.demoqa.api.authorization.AuthorizationApi.getAuthResponse;
import static com.demoqa.tests.TestData.getCredentials;
import static com.demoqa.web.pages.LoginPage.setAuthCookies;


public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

        setAuthCookies(authResponse);
    }
}
