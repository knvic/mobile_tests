package com.demoqa.helpers;


import com.demoqa.api.authorization.AuthorizationResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.demoqa.api.models.AuthorizationApi.getAuthResponse;
import static com.demoqa.helpers.TestData.getCredentials;
import static com.demoqa.pages.LoginPage.setAuthCookies;


public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

        setAuthCookies(authResponse);
    }
}
