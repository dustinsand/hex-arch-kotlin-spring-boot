package com.hexarchbootdemo.adapter.input.rest

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.hamcrest.CoreMatchers.hasItems
import org.hamcrest.CoreMatchers.startsWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VoterControllerKotlinFlowIT(@LocalServerPort val port: Int) {

    val requestSpec: RequestSpecification = RequestSpecBuilder()
            .setBaseUri("http://localhost:$port")
            .addFilter(RequestLoggingFilter())
            .addFilter(ResponseLoggingFilter())
            .build()

    @Test
    fun `Verify Voter last name in response`() {
        RestAssured.given()
                .spec(requestSpec)
                .`when`()
                .get("kotlin-reactive-flow/voters?lastName=shimono")
                .then()
                .statusCode(200)
                .body("firstInitial", hasItems("D"),
                        "lastName", hasItems("Shimono"),
                        "socialSecurityNumber", hasItems("111-11-1111", "222-22-2222")

                )
    }

    @Test
    fun `Verify valid Voter is saved`() {
        RestAssured.given()
                .spec(requestSpec)
                .given()
                .contentType(ContentType.JSON)
                .body("""
                    { "firstName": "Kotlin", "lastName": "Flow", "socialSecurityNumber": "999-99-9999" }
                    """)
                .`when`()
                .post("kotlin-reactive-flow/voters")
                .then()
                .statusCode(201)
                .header("Location", startsWith("kotlin-reactive-flow/voters/"))

        RestAssured.given()
                .spec(requestSpec)
                .`when`()
                .get("kotlin-reactive-flow/voters?lastName=flow")
                .then()
                .statusCode(200)
                .body("firstInitial", hasItems("K"),
                        "lastName", hasItems("Flow")

                )
    }

    @Test
    fun `Verify invalid Voter is not saved`() {
        RestAssured.given()
                .spec(requestSpec)
                .given()
                .contentType(ContentType.JSON)
                .body("""
                    { "firstName": "", "lastName": "" }
                    """)
                .`when`()
                .post("kotlin-reactive-flow/voters")
                .then()
                .statusCode(422)
    }
}
