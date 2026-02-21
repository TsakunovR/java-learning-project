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
        assertEquals(-10, App.add(-10, -10));

    }
    @Test
    void CreateStoreOrderTest() {
        RestAssured.baseURI="https://petstore.swagger.rv-school.ru/api/v3";

        String requestbody = """
                {
                  "id": 10,
                  "petId": 198772,
                  "quantity": 7,
                  "shipDate": "2026-02-21T09:47:00.404Z",
                  "status": "approved",
                  "complete": true
                }""";
        Response response= RestAssured
                .given()
                .header("Content-Type","application/json")
                .body(requestbody)
                .when()
                .post("/store/order")
                .then()
                .extract().response();

        assertEquals(200,response.getStatusCode(),"Неверный статус код");
        assertEquals(10,response.jsonPath().getInt("id"), "Неверный id");
        assertEquals(198772,response.jsonPath().getInt("petId"), "Неверный Pet id");
        assertEquals(7,response.jsonPath().getInt("quantity"), "Неверное количество");
        assertEquals("approved",response.jsonPath().getString("status"), "Неверный status заказа");
        assertTrue(response.jsonPath().getBoolean("complete"));

    }
    @Test
    void AddNewPet() {
        RestAssured.baseURI="https://petstore.swagger.rv-school.ru/api/v3";

        String requestbody = """
                {
                  "id": 1,
                  "name": "Buddy",
                  "status": "available",
                }""";
        Response response= RestAssured
                .given()
                .header("Content-Type","application/json")
                .body(requestbody)
                .when()
                .post("/pet")
                .then()
                .extract().response();

        assertEquals(200,response.getStatusCode(),"Неверный статус код");
        assertEquals(1,response.jsonPath().getInt("id"), "Неверный id");;
        assertEquals("Buddy",response.jsonPath().getString("status"), "Неверное имя питомца");
        assertEquals("available",response.jsonPath().getString("status"), "Неверный статус питомца");


    }

}

