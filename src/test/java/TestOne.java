import io.restassured.RestAssured;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestOne {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://aqa-api.javacode.ru/api/auth/login";

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "klyuev_alexey");
        requestParams.put("password", "4~gBt(K4siN<bTR");

        String jsonSchemaPath = "src/test/resources/jsonSchema/userSchema.json";

        String jsonSchema = "{\n" +
                "    \"token\": \"\",\n" +
                "    \"refresh_token\": \"\",\n" +
                "    \"user\": {\n" +
                "        \"_id\": ,\n" +
                "        \"teams\": [\n" +
                "            \n" +
                "        ],\n" +
                "        \"username\": \"\",\n" +
                "        \"email\": \"[at]exceed-team.com (auto generated)\",\n" +
                "        \"roles\": [\n" +
                "            \"admin\"\n" +
                "        ],\n" +
                "        \"positions\": [\n" +
                "            \n" +
                "        ],\n" +
                "        \"plain_password\": \"\",\n" +
                "        \"cities\": [\n" +
                "            \n" +
                "        ],\n" +
                "        \"companies\": [\n" +
                "            \n" +
                "        ],\n" +
                "        \"work_history\": [\n" +
                "            \n" +
                "        ],\n" +
                "        \"edu_history\": [\n" +
                "            \n" +
                "        ],\n" +
                "        \"cd\": \"2024-08-22T12:43:57.855Z\",\n" +
                "        \"password\": \"8eee7019a472affe2cd0e2925529196aa1f0e84da39597caddbf76b56796c3e1760a0844df6a144c520f78f27a7db4369db866db2a4e06dab0490d55d872a978\",\n" +
                "        \"name\": \"undefined undefined\"\n" +
                "    }\n" +
                "}\n";

        System.out.println(requestParams.toString());

        // Выполнение запроса с базовой авторизацией
        given().
                header("Content-Type", "application/json"). // Установка заголовка Content-Type
                body(requestParams.toString()). // Установка тела запроса
                when().
                    post().
                then().
                    statusCode(200).
                    body(matchesJsonSchemaInClasspath(jsonSchemaPath));
    }
}

