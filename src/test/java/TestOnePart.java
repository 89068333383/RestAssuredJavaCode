import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestOnePart {


    private String token = "";
    @Test(enabled = false)

    public void testChekLogin() {
        RestAssured.baseURI = "https://aqa-api.javacode.ru/api/auth";

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "klyuev_alexey");
        requestParams.put("password", "4~gBt(K4siN<bTR");

        String jsonSchemaPath = "userSchema.json";

        // Выполнение запроса с базовой авторизацией
        String res =
                given().
                        header("Content-Type", "application/json"). // Установка заголовка Content-Type
                        body(requestParams.toString()). // Установка тела запроса
                        when().
                            post("/login").
                        then().
                            statusCode(200).
                            body(matchesJsonSchemaInClasspath(jsonSchemaPath)).
                        extract().body().asPrettyString();
        System.out.println(res);
    }

//    @org.junit.jupiter.api.Test
//    @DisplayName("Test 2 - AddUser")
//    @CsvFileSource(resources = "Pairwise.csv")
//    @ParameterizedTest


    @Test
    @Disabled
    public void testAddUser(
//            int id,
//            String first_name,
//                            String surname,
//                            String email,
//                            String username,
//                            String plain_password,
//                            String roles,
//                            String isCV,
//                            String salesOpenTime,
//                            String salesStatus
    ) {
        RestAssured.baseURI = "https://aqa-api.javacode.ru/api";
        String jsonSchemaPath = "userSchemaTwo.json";

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "klyuev_alexey");
        requestParams.put("password", "4~gBt(K4siN<bTR");

        String bodyRequestTest=" {\n" +
                "    \"customData\": {\n" +
                "        \"isCV\": true,\n" +
                "        \"salesOpenTime\": \"2024-09-27T19:00:00.000Z\",\n" +
                "        \"salesStatus\": \"active_search\"\n" +
                "    },\n" +
                "    \"first_name\": \"st2\",\n" +
                "    \"surname\": \"st3\",\n" +
                "    \"email\": \"st4\",\n" +
                "    \"username\": \"st5\",\n" +
                "    \"plain_password\": \"st6\",\n" +
                "    \"roles\": \"admin\"\n" +
                "}\n";

//        String bodyRequest = "{\n" +
//                "    \"customData\": {\n" +
//                "        \"isCV\": " + isCV + ",\n" +
//                "        \"salesOpenTime\": \""  + salesOpenTime + "\",\n" +
//                "        \"salesStatus\": \"" + salesStatus + "\"\n" +
//                "    },\n" +
//                "    \"first_name\": \"" + first_name + "\",\n" +
//                "    \"surname\": \"" + surname + "\",\n" +
//                "    \"email\": \"" + email + "\",\n" +
//                "    \"username\": \"" + username + "\",\n" +
//                "    \"plain_password\": \"" + plain_password + "\",\n" +
//                "    \"roles\": \"" + roles + "\"\n" +
//                "}";

//        System.out.println(bodyRequest);


        getToken();

        String result =
                given().
                        contentType(ContentType.JSON).
                        header("Authorization",token). // Установка заголовка Content-Type
                        body(bodyRequestTest). // Установка тела запроса
                        when().
                        post("/user-auth1").
                        then().
//                        statusCode(200).
//                        body(matchesJsonSchemaInClasspath(jsonSchemaPath)).
                        extract().body().asPrettyString();
        System.out.println(result);
    }

    @Test
    @Disabled
    public void testAddQuestion() {
        String bodyRequestText = "{\"ответ-вопрос\":\"вопрос-ответ\"}";

        RestAssured.baseURI = "https://aqa-api.javacode.ru/api";
        String jsonSchemaPath = "userSchemaThree.json";

        getToken();

        String result =
                given().
                        contentType(ContentType.JSON).
                        header("Authorization",token). // Установка заголовка Content-Type
                        body(bodyRequestText). // Установка тела запроса
                        when().
                        post("/theme-question").
                        then().
                        statusCode(200).
                        body(matchesJsonSchemaInClasspath(jsonSchemaPath)).
        extract().body().asPrettyString();
        System.out.println(result);

    }

    @Test
    @DisplayName("редактирование вопроса")
    @Disabled
    public void testEditQuestion(){
        String bodyRequestText = "{\n" +
                "  \"currentLTS\": \"\",\n" +
                "  \"changeKey\": \"version\",\n" +
                "  \"question\": \"1006\",\n" +
                "  \"LTP\": {\n" +
                "    \"data\": {\n" +
                "      \"jsDetails\": \"\",\n" +
                "      \"comment\": \"\",\n" +
                "      \"quizes\": [],\n" +
                "      \"hints\": [],\n" +
                "      \"type\": \"\",\n" +
                "      \"videos\": [],\n" +
                "      \"name\": \"тест\",\n" +
                "      \"hashTags\": [],\n" +
                "      \"title\": \"\",\n" +
                "      \"answer\": \"тест тест\",\n" +
                "      \"facts\": [\n" +
                "        {\n" +
                "          \"name\": \"тест\",\n" +
                "          \"desc\": \"тест\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"useCases\": [\n" +
                "        {\n" +
                "          \"name\": \"тест\",\n" +
                "          \"desc\": \"тест\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"originalDuplicateId\": \"\",\n" +
                "      \"questionId\": \"1006\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"versionDetails\": {\n" +
                "    \"patch\": 0,\n" +
                "    \"subVersion\": 0,\n" +
                "    \"version\": 1,\n" +
                "    \"versionStr\": \"1.0.0\"\n" +
                "  }\n" +
                "}";

        RestAssured.baseURI = "https://aqa-api.javacode.ru/api";
        String jsonSchemaPath = "userSchemaFour.json";
        getToken();

        String result =
                given().
                        contentType(ContentType.JSON).
                        header("Authorization",token). // Установка заголовка Content-Type
                        body(bodyRequestText). // Установка тела запроса
                        when().
                        post("/create-lts").
                        then().
//                        statusCode(200).
//                        body(matchesJsonSchemaInClasspath(jsonSchemaPath)).
                        extract().body().asPrettyString();
        System.out.println(result);

    }

    @Test
//    @Disabled
    public void testAddQuiz(){
        String bodyRequestText ="{\n" +
                "    \"answerType\": \"quiz\",\n" +
                "    \"isValid\": true,\n" +
                "    \"name\": \"test\",\n" +
                "    \"files\": [],\n" +
                "    \"variations\": [\n" +
                "        {\n" +
                "            \"name\": \"\",\n" +
                "            \"isCorrect\": true\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        RestAssured.baseURI = "https://aqa-api.javacode.ru/api";
        String jsonSchemaPath = "userSchemaFour.json";
        getToken();

        String result =
                given().
                        contentType(ContentType.JSON).
                        header("Authorization",token). // Установка заголовка Content-Type
                        body(bodyRequestText). // Установка тела запроса
                        when().
                        post("/quiz").
                        then().
                        statusCode(200).
//                        body(matchesJsonSchemaInClasspath(jsonSchemaPath)).
        extract().body().asPrettyString();
        System.out.println(result);

    }

    private void getToken(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "klyuev_alexey");
        requestParams.put("password", "4~gBt(K4siN<bTR");
        this.token = given().
                header("Content-Type", "application/json"). // Установка заголовка Content-Type
                        body(requestParams.toString()). // Установка тела запроса логин -паоль
                        when().
                post("/auth/login").
                then().
                statusCode(200).
                extract().path("token");

        System.out.println(token);
    }


}

