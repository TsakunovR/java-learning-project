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
    void addReturnsSumOfTwoNumbersWithZero() {
        assertEquals(-10, App.add(-10, 0));
        assertEquals(99, App.add(-1, 100));
        assertEquals(8, App.add(-22, 30));
    }

    @Test
    void createNewPetTest() {
        RestAssured.baseURI = "https://petstore.swagger.rv-school.ru/api/v3";

        //Подготавливаем данные для создания питомца (из задания)
        String requestBody = """
        {
          "id": 1,
          "name": "Buddy",
          "status": "available"  
        }  
    """;

        //Отправляем POST-запрос и получаем ответ
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .extract().response();

        //Проверяем статус код
        assertEquals(200, response.statusCode(), "Неверный статус код");

        //Проверяем, что ответ содержит данные созданного питомца
        assertEquals(1, response.jsonPath().getInt("id"), "Неверный id питомца");
        assertEquals("Buddy", response.jsonPath().getString("name"), "Неверное имя питомца");
        assertEquals("available", response.jsonPath().getString("status"), "Неверный статус питомца");
    }
}
