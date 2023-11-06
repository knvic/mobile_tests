package com.demoqa.collections;

import com.demoqa.api.authorization.models.AuthorizationResponseModel;
import com.demoqa.collections.models.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static com.demoqa.specs.ApiSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CollectionsApi  {

    public static void deleteAllBooks(AuthorizationResponseModel authResponse) {
        step("Удаляем все книги из профайла", () -> given()
                .spec(commonRequestSpec)
                .queryParams("UserId", authResponse.getUserId())
                .when()
                .basePath("BookStore/v1")
                .delete("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204));
    }

    public static void addBookToTheCollection(AuthorizationResponseModel authResponse, BookModel book) {

        step("Добавляем книгу в профайл", () -> given()
                .spec(commonRequestSpec)
                .body(generateAddBookRequest(authResponse, book))
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .spec(defaultResponseSpec)
                .statusCode(201));
    }

    public static void addBookUnauthorized(AuthorizationResponseModel authResponse, BookModel book) {
        step("Добавление книги без авторизации. Проверка кода и сообщения в ответе", () -> given()
                .spec(unauthorizedRequestSpec)
                .body(generateAddBookRequest(authResponse, book))
                .when()
                .basePath("/BookStore/v1")
                .post("/Books")
                .then()
                .spec(defaultResponseSpec)
                .statusCode(401)
                .body("code", is("1200"))
                .body("message", is("User not authorized!")));
    }

    public static void addExistingBook(AuthorizationResponseModel authResponse, BookModel book) {
        step("Добавление книги, которая уже есть в профайле. Проверка кода и сообщения в ответе", () -> given()
                .spec(commonRequestSpec)
                .body(generateAddBookRequest(authResponse, book))
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .spec(defaultResponseSpec)
                .statusCode(400)
                .body("code", is("1210"))
                .body("message", is("ISBN already present in the User's Collection!")));
    }

    public static void deleteBookFromTheCollection(AuthorizationResponseModel authResponse, BookModel book) {
        DeleteBookModel deleteBookData = new DeleteBookModel();
        deleteBookData.setUserId(authResponse.getUserId());
        deleteBookData.setIsbn(book.getIsbn());

        step("Удаление книги из профайла", () -> given()
                .spec(commonRequestSpec)
                .body(deleteBookData)
                .when()
                .basePath("BookStore/v1")
                .delete("/Book")
                .then()
                .spec(defaultResponseSpec)
                .statusCode(204));
    }

    public static ArrayList<BookModel> getBooks() {

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

    public static BookModel getRandomBook() {
        ArrayList<BookModel> books = getBooks();


        return step("Выбоор случайной книги из списка используя комбинацию классов ThreadLocal и Random для достижения " +
                "лучшей производительности в многопоточной среде.", () -> books.get(ThreadLocalRandom.current().nextInt(0, books.size() - 1) + 1));
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
