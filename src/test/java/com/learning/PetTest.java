package com.learning;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetTest {

    @Test
    void createPetTest() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";

        String requestBody = """
                {"id": 1,
                 "name": "Buddy",
                  "status": "available"}""";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .log().body()
                .extract().response();

        assertEquals(200, response.statusCode(), "Неверный статус код" );
        assertEquals(1, response.jsonPath().getInt("id"), "Неверный Id заказа");
        assertEquals("Buddy", response.jsonPath().getString("name"), "Неверное имя");
        assertEquals("available", response.jsonPath().getString("status"), "Неверный статус");

    }
}
