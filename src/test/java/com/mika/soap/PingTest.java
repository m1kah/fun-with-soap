package com.mika.soap;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class PingTest {
    @BeforeAll
    public static void setup() {
        RestAssured.port = 8080;
    }

    @Test
    public void add() {
        when()
            .get("/api/ping")
        .then()
            .log().all()
            .statusCode(200);
    }
}
