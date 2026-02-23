package com.learning;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Простой тест для метода add класса App.
 */
class AppTest {

    @Test
    void addReturnsSumOfTwoNumbers() {
        assertEquals(5, App.add(2, 3));
        assertEquals(0, App.add(-1, 1));
        assertEquals(-5, App.add(-2, -3));
        assertEquals(-145, App.add(2, -147));
    }
    @Test
    void createPetTest() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";
        String requestBody = """
                {
                "id":1,
                "name":"Buddy",
                "status":"available"
                }
                """;
        Response response = RestAssured
                .given()
                .header("Content-Type","application/json")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .extract().response();
        assertEquals (200, response.statusCode(), "Неверный статус код");
        assertEquals (1, response.jsonPath().getInt("id"), "Неверный id питомца");
        assertEquals ("Buddy", response.jsonPath().getString("name"));
        assertEquals ("available", response.jsonPath().getString("status"));
    }
}
