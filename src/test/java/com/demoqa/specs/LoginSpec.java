package com.demoqa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.demoqa.helpers.CustomAllureListener.withCustomTemplates;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;


public class LoginSpec {
 public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .log().body()
            .contentType(ContentType.JSON)
            .basePath("/Account/v1");


    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();


}
