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


public class ApiSpec {
    public static RequestSpecification commonRequestSpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .log(ALL)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + getAuthResponse(getCredentials()).getToken())
            .build();


    public static ResponseSpecification defaultResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build();

}
