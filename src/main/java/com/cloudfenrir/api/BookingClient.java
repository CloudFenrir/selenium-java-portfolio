package com.cloudfenrir.api;

import com.cloudfenrir.api.models.Booking;
import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.utils.FrameworkException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingClient {

    private final RequestSpecification spec;

    public BookingClient() {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("apiBaseUrl"))
                .setContentType(ContentType.JSON)     // what we send
                .setAccept(ContentType.JSON)          // what we want back (restful-booker needs it)
                .build();
    }

    public Response createBooking(Booking booking) {
        return given()
                .spec(spec)
                .body(booking)                        // serialization: Booking → JSON, automatic
                .when()
                .post("/booking");
    }

    public Response getBooking(int id) {
        return given()
                .spec(spec)
                .pathParam("id", id)
                .when()
                .get("/booking/{id}");
    }

    public String getToken() {
        String token = given()
                .spec(spec)
                .body(Map.of("username", ConfigReader.get("apiUsername"),
                        "password", ConfigReader.get("apiPassword")))
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().path("token");

        if (token == null) {
            throw new FrameworkException("Could not get auth token. Check apiUsername and apiPassword");
        }
        return token;
    }

    public Response deleteBooking(int id, String token) {
        return given()
                .spec(spec)
                .cookie("token", token)
                .pathParam("id", id)
                .when()
                .delete("/booking/{id}");
    }
}
