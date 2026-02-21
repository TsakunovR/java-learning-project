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
    }

    @Test
    void addNewPetReturnsSuccess() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";

        String requestBody = """
                {
                  "id": 1,
                  "name": "QA_minnie",
                  "category": {
                    "id": 1,
                    "name": "Dogs"
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

        assertEquals(200, response.statusCode(), "Successful operation");

        assertEquals(1, response.jsonPath().getInt("id"));
        assertEquals("QA_minnie", response.jsonPath().getString("name"));
        assertEquals("available", response.jsonPath().getString("status"));
    }
}

/**
1. Отправить POST-запрос на эндпоинт /pet с подготовленными данными.


Ожидаемый результат:
Статус ответа: 200
Ответ содержит данные созданного питомца.
В ответе присутствуют поля:
id = 1
name = “Buddy”
status = “available”
 */
