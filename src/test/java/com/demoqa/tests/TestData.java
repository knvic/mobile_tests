package com.demoqa.tests;

import com.demoqa.web.BaseWebTest;
import org.json.JSONObject;

public class TestData extends BaseWebTest {
    public static String login = config.getLogin();
    public static String password =config.getPassword() ;

    public static String getCredentials() {
        return new JSONObject()
                .put("userName", login)
                .put("password", password)
                .toString();
    }

}