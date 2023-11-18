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
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
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
    @DisplayName("Courier Can Be Login /api/v1/courier/login")
    @Description("Checking courier login is very important")
    @Step("CHeck response")
    public void courierCanBeLoginTest() {
        ValidatableResponse response = courierClient.createCourier(courier);

        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));

        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.from(courier));
        responseLogin.assertThat()
                .statusCode(200)
                .body("id", notNullValue());

    }

}
