package com.demoqa.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.demoqa.api.authorization.AuthorizationApi.getAuthResponse;
import static com.demoqa.helpers.CustomAllureListener.withCustomTemplates;
import static com.demoqa.tests.TestData.getCredentials;

import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class ApiSpec {
 public static RequestSpecification commonRequestSpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .log(ALL)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + getAuthResponse(getCredentials()).getToken())
            .build();


    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification missingPasswordResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification defaultResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build();

    public  static RequestSpecification unauthorizedRequestSpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .log(ALL)
            .setContentType(JSON)
            .build();
}
