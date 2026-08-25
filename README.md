# Учебный Java-проект

Минимальный проект для изучения Java и запуска тестов. Работает на Windows и macOS.

## Что нужно установить

1. **Java 21** — язык, на котором написан проект.
2. **Maven** — программа для сборки проекта и запуска тестов.

---

## Шаг 1: Проверить, что Java установлена

Откройте терминал (командную строку) и введите:

**Windows (cmd или PowerShell):**
```cmd
java -version
```

**macOS (Терминал):**
```bash
java -version
```

Должна появиться строка с версией, например: `openjdk version "21"` или `java version "21"`.  
Если команда не найдена — установите [Java 21](https://adoptium.net/).

---

## Шаг 2: Проверить, что Maven установлен

В том же терминале введите:

**Windows:**
```cmd
mvn -version
```

**macOS:**
```bash
mvn -version
```

Должна появиться информация о Maven.  
Если команда не найдена — установите [Maven](https://maven.apache.org/download.cgi).

---

## Шаг 3: Сборка проекта

Перейдите в папку проекта (там, где лежит файл `pom.xml`), затем выполните:

**Windows:**
```cmd
mvn compile
```

**macOS:**
```bash
mvn compile
```

Команда `mvn compile` скомпилирует код. В конце должно быть `BUILD SUCCESS`.

---

## Шаг 4: Запуск приложения

Сначала соберите проект (если ещё не собирали), затем запустите класс:

**Windows:**
```cmd
mvn compile
java -cp target/classes com.learning.App
```

**macOS:**
```bash
mvn compile
java -cp target/classes com.learning.App
```

Вы должны увидеть:
- «Проект успешно запущен!»
- Пример: 2 + 3 = 5

---

## Шаг 5: Запуск тестов

**Windows:**
```cmd
mvn test
```

**macOS:**
```bash
mvn test
```

Команда запустит тесты JUnit 5. В конце должно быть `BUILD SUCCESS` и сообщение о количестве пройденных тестов.

---

## Структура проекта

```
java-learning-project/
├── README.md
├── pom.xml
├── .gitignore
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/learning/
│   │           └── App.java
│   └── test/
│       └── java/
│           └── com/learning/
│               └── AppTest.java
```

- **pom.xml** — настройки проекта и зависимости (в том числе JUnit 5).
- **App.java** — класс с методом `main` и методом `add` (сложение двух чисел).
- **AppTest.java** — тест, который проверяет метод `add`.

Удачи в обучении!

---

## Задание для интенсива

Напиши автотест на создание питомца через API Petstore: Swagger — https://petstore.swagger.rv-school.ru/, базовый адрес API — `https://petstore.swagger.rv-school.ru/api/v3`. Для HTTP-запросов подключи REST Assured в `pom.xml` (блок из урока 5).

**Название:** Создание нового питомца через API

**Предусловия:** подготовлены данные питомца `{"id": 1, "name": "Buddy", "status": "available"}`

**Шаги:** отправить POST-запрос на эндпоинт `/pet` с подготовленными данными.

**Ожидаемый результат:**
- статус ответа: 200;
- ответ содержит данные созданного питомца;
- в ответе присутствуют поля `id = 1`, `name = "Buddy"`, `status = "available"`.

**Как сдать:** новый тестовый класс рядом с `AppTest.java` (например `src/test/java/com/learning/PetTest.java`), метод с аннотацией `@Test`. Работай в отдельной ветке и открой pull request в `main` — робот прогонит тесты и напишет результат прямо в PR. Полная инструкция — на странице урока 5 в боте.
