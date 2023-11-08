package com.demoqa.api.models;

import com.demoqa.api.authorization.AuthorizationResponseModel;
import static com.demoqa.api.specs.LoginSpec.loginRequestSpec;
import static com.demoqa.api.specs.LoginSpec.loginResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static AuthorizationResponseModel getAuthResponse(String credentials) {
        return given()
                .spec(loginRequestSpec)
                .body(credentials)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(AuthorizationResponseModel.class);
    }

}
