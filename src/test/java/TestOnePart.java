import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
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
    @DisplayName("Test 2 - AddUser")
    @CsvFileSource(resources = "Pairwise.csv")
    @ParameterizedTest



    public void testAddUser(int id,
                            String first_name,
                            String surname,
                            String email,
                            String username,
                            String plain_password,
                            String roles,
                            String isCV,
                            String salesOpenTime,
                            String salesStatus) {
        RestAssured.baseURI = "https://aqa-api.javacode.ru/api";
        String jsonSchemaPath = "userSchemaTwo.json";

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "klyuev_alexey");
        requestParams.put("password", "4~gBt(K4siN<bTR");

        String bodyRequest = "{" +
                "customData" + ": "+ "{" +
                "isCV" + ": " + isCV + "," +
                "salesOpenTime" + ": "  + salesOpenTime + "," +
                "salesStatus" + ": " + salesStatus + "" +
                "}," +
                "first_name" + ": " +  first_name + "," +
                "surname" + ": " + surname + "," +
                "email" + ":" + email + "," +
                "username" + ": " + username + "," +
                "plain_password" + ": " + plain_password + "," +
                "roles" + ": " + roles + "" +
                "}";

        System.out.println(bodyRequest);

        String result =
                given().
                        auth().oauth2(
                                given().
                                        header("Content-Type", "application/json"). // Установка заголовка Content-Type
                                        body(requestParams.toString()). // Установка тела запроса
                                        when().
                                        post("/auth/login").
                                        then().
                                        statusCode(200).
                                        extract().path("token")
                        ).
                        header("Content-Type", "application/json"). // Установка заголовка Content-Type
                        body(bodyRequest). // Установка тела запроса
                        when().
                        post("/user-auth1").
                        then().
                        statusCode(200).
                        body(matchesJsonSchemaInClasspath(jsonSchemaPath)).
                        extract().body().asPrettyString();
        System.out.println(result);
    }
}

