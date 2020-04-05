package com.hexarchbootdemo.adapter.input.rest

import com.hexarchbootdemo.domain.model.SocialSecurityNumber
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
class VoterControllerIT(@LocalServerPort val port: Int) {

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
                .get("/voters?lastName=shimono")
                .then()
                .statusCode(200)
                .body("firstInitial", hasItems("D"),
                        "lastName", hasItems("Shimono"),
                        "socialSecurityNumber", hasItems("111-45-6789", "222-45-6789")

                )
    }

    @Test
    fun `Verify valid Voter is saved`() {
        RestAssured.given()
                .spec(requestSpec)
                .given()
                .contentType(ContentType.JSON)
                .body("""
                    { "firstName": "John", "lastName": "Doe", "socialSecurityNumber": "123-45-6789" }
                    """)
                .`when`()
                .post("/voters")
                .then()
                .statusCode(201)
                .header("Location", startsWith("/voters/"))

        RestAssured.given()
                .spec(requestSpec)
                .`when`()
                .get("/voters?lastName=doe")
                .then()
                .statusCode(200)
                .body("firstInitial", hasItems("J"),
                        "lastName", hasItems("Doe")

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
                .post("/voters")
                .then()
                .statusCode(422)
    }
}
