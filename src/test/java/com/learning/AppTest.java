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
    void addMoreReturnsSumOfTwoNumbers() {
        assertEquals(30, App.add(10, 20));
        assertEquals(-100, App.add(-101, 1));
        assertEquals(3, App.add(0, 3));
    }

    @Test
    void createPetStoreOrder() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";

        String requestBody = """
                {
                  "id": 15,
                  "petId": 198772,
                  "quantity": 4,
                  "shipDate": "2026-02-22T09:04:12.339Z",
                  "status": "approved",
                  "complete": true
                }
                """;

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/store/order")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode(), "Incorrect status code");
        assertEquals(15, response.jsonPath().getInt("id"), "id does not match");
        assertEquals(198772, response.jsonPath().getInt("petId"), "pet ID does not match");
        assertEquals(4, response.jsonPath().getInt("quantity"), "quantity does not match");
        assertEquals("approved", response.jsonPath().getString("status"), "status is not approved");
        assertTrue(response.jsonPath().getBoolean("complete"), "order not complete");
    }

    @Test
    void createNewPetInStore() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";

        String requestBody = """
                {
                  "id": 1,
                  "name": "Buddy",
                  "category": {
                    "id": 2,
                    "name": "Cats"
                  },
                  "photoUrls": [
                    "string"
                  ],
                  "tags": [
                    {
                      "id": 0,
                      "name": "string"
                    }
                  ],
                  "status": "available"
                }
                """;

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode(), "status code in wrong");
        assertEquals(1, response.jsonPath().getInt("id"), "Id does not match");
        assertEquals("Buddy", response.jsonPath().getString("name"), "Name is different");
        assertEquals("available", response.jsonPath().getString("status"), "Status is not as expected");
    }
}
