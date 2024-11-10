import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class TestTwo {
    public static void main(String[] args) {
        RestAssured.baseURI="https://aqa-api.javacode.ru/api/user-auth1";

        String jsonSchemaPath = "src/test/resources/userSchema/userSchemaTwo.json";

        String token = "your_bearer_token_here";
        String bodiRequest = "{ \"customData\": { \"isCV\": true, \"salesOpenTime\": \"2024-09-27T19:00:00.000Z\", \"salesStatus\": \"active_search\" }, \"first_name\": \"test\", \"surname\": \"test\", \"email\": \"test\", \"username\": \"test\", \"plain_password\": \"test\", \"roles\": \"admin\" }";

//        Response response = RestAssured.
                given().
                    header("Authorization", "token ").
                    header("Content-Type", "application/json").
                    body(bodiRequest).
                    auth().oauth2(token).
                when().
                    post().
                then().statusCode(201).
                body(matchesJsonSchemaInClasspath(jsonSchemaPath));

    }
}
