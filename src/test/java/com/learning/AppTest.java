package com.learning;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Простой тест для метода add класса App.
 */
class AppTest {

    @Test
    void addReturnsSumOfTwoNumbers() {
        assertEquals(5, App.add(2, 3));
        assertEquals(0, App.add(-1, 1));
        assertEquals(-5, App.add(-2, -3));
    }
    @Test
    void addReturnsSumOfTwoNumbers2() {
        assertEquals(27, App.add(8, 19));
        assertEquals(-99, App.add(-100, 1));
        assertEquals(-5, App.add(-2, -3));
    }
    @Test
    void createStorOrderTest() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";

        String requestBody = """
                {
                  "id": 10,
                  "petId": 198772,
                  "quantity": 7,
                  "shipDate": "2026-02-21T19:04:35.276Z",
                  "status": "approved",
                  "complete": true
                  }
                """;
        Response response = RestAssured
                .given()
                .header("Content-Type","application/json")
                .body(requestBody)
                .when()
                .post("/store/order")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode(), "неверный статус код");
        assertEquals(10, response.jsonPath().getInt("id"), "неверный id заказа");
        assertEquals(198772, response.jsonPath().getInt("petId"), "неверный petId заказа");
        assertEquals(7, response.jsonPath().getInt("quantity"));
        assertEquals("approved", response.jsonPath().getString("status"));
        assertTrue(response.jsonPath().getBoolean("complete"));

    }
    @Test
    void requestToPetCreate() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";

        String requestBody = """
               {
               "id": 1,
               "name": “Buddy”,
               "status": “available”
               }
               """;
        Response response = RestAssured
                .given()
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode(), "неверный статус код");
        assertEquals(1, response.jsonPath().getInt("id"));
        assertEquals("Buddy", response.jsonPath().getString("name"));
        assertEquals("available", response.jsonPath().getString("status"));
    }

}
