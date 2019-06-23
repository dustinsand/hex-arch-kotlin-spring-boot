package com.hexarchbootdemo.adapter.ingress.rest

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.hamcrest.CoreMatchers.hasItems
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VoterControllerTestIT(@LocalServerPort val port: Int) {

    val requestSpec: RequestSpecification = RequestSpecBuilder()
            .setBaseUri("http://localhost:$port")
            .addFilter(RequestLoggingFilter())
            .addFilter(ResponseLoggingFilter())
            .build()

    @Test
    fun `Verify Voter first name is present in response`() {
        RestAssured.given()
                .spec(requestSpec)
                .`when`()
                .get("/voters?name=shimono")
                .then()
                .statusCode(200)
                .body("firstInitial", hasItems("D"),
                        "lastName", hasItems("Shimono")
                )
    }
}

