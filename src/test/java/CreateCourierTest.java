import client.CourierClient;
import data.CourierCredentials;
import data.CourierData;
import data.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static constans.BaseUri.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CreateCourierTest  {

    CourierClient courierClient;
    CourierData courier;


    @Before
    public void setup() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
        RestAssured.baseURI = BASE_URI;
    }

    @After
    public void deleteCourier() {

        Response response = courierClient.loginCourierForDelete(CourierCredentials.from(courier));

       int id = response.path("id");

        given()
                .pathParam("id", id)
                .header("Content-type", "application/json")
                .when()
                .delete("/api/v1/courier/{id}")
                .then().statusCode(200);


    }

    @Test
    @DisplayName("Check status code and body /api/v1/courier")
    @Description("Checking courier create is very important")
    @Step("CHeck response")
    public void courierCanBeCreatedTest() {
        ValidatableResponse response = courierClient.createCourier(courier);

        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));

    }

    @Test
    @DisplayName("Check status code with duplicate courier /api/v1/courier")
    @Description("Checking duplicate courier create is very important")
    @Step("CHeck response")
    public void duplicateCourierCantBeCreatedTest() {
        courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.createCourier(courier);

        response.assertThat()
                .statusCode(409);

    }



}
