package com.demoqa.api.authorization;

import com.demoqa.api.authorization.models.AuthorizationResponseModel;
import static com.demoqa.specs.LoginSpec.loginRequestSpec;
import static com.demoqa.specs.LoginSpec.loginResponseSpec;
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
