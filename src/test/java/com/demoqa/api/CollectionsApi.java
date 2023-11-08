package com.demoqa.api;

import com.demoqa.api.authorization.AuthorizationResponseModel;
import com.demoqa.api.models.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static com.demoqa.api.specs.ApiSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


public class CollectionsApi  {

    public  void deleteAllBooks(AuthorizationResponseModel authResponse) {

                given()
                .spec(commonRequestSpec)
                .queryParams("UserId", authResponse.getUserId())
                .when()
                .basePath("BookStore/v1")
                .delete("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    public void addBookToTheCollection(AuthorizationResponseModel authResponse, BookModel book) {


                given()
                .spec(commonRequestSpec)
                .body(generateAddBookRequest(authResponse, book))
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .spec(defaultResponseSpec)
                .statusCode(201);
    }


    public  void deleteBookFromTheCollection(AuthorizationResponseModel authResponse, BookModel book) {
        DeleteBookModel deleteBookData = new DeleteBookModel();
        deleteBookData.setUserId(authResponse.getUserId());
        deleteBookData.setIsbn(book.getIsbn());

        given()
                .spec(commonRequestSpec)
                .body(deleteBookData)
                .when()
                .basePath("BookStore/v1")
                .delete("/Book")
                .then()
                .spec(defaultResponseSpec)
                .statusCode(204);
    }

    public  ArrayList<BookModel> getBooks() {

        return step("Получение списка доступных книг", () -> given()
                .spec(commonRequestSpec)
                .when()
                .basePath("BookStore/v1")
                .get("/Books")
                .then()
                .spec(defaultResponseSpec)
                .statusCode(200)
                .extract().as(BooksListModel.class).getBooks());
    }

    public  BookModel getRandomBook() {
        ArrayList<BookModel> books = getBooks();
        return books.get(ThreadLocalRandom.current().nextInt(0, books.size() - 1) + 1);
    }

    private static AddBookRequestModel generateAddBookRequest(AuthorizationResponseModel authResponse, BookModel book) {
        ISBNModel isbn = new ISBNModel();
        AddBookRequestModel body = new AddBookRequestModel();

        isbn.setIsbn(book.getIsbn());

        ArrayList<ISBNModel> listOfISBNs = new ArrayList<>();
        listOfISBNs.add(isbn);

        body.setUserId(authResponse.getUserId());
        body.setCollectionOfIsbns(listOfISBNs);

        return body;
    }
}
