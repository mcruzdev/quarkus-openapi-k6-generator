package com.github.mcruzdev.quarkus.openapi.k6.generator.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QuarkusOpenapiK6GeneratorResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/quarkus-openapi-k6-generator")
                .then()
                .statusCode(200)
                .body(is("Hello quarkus-openapi-k6-generator"));
    }
}
