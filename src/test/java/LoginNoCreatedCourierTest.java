import client.CourierClient;
import data.CourierCredentials;
import data.CourierData;
import data.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;


public class LoginNoCreatedCourierTest {
    CourierClient courierClient;
    CourierData courier;

    @Before
    public void setup() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
    }


    @Test
    @DisplayName("Courier Cant Login with bad password /api/v1/courier/login")
    @Description("Checking courier login with bad password is very important")
    @Step("CHeck response")
    public void badPassCourierCantLoginTest() {
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.badPassword(courier));
        responseLogin.assertThat()
                .statusCode(404);

    }

    @Test
    @DisplayName("Not Found Courier Cant Login /api/v1/courier/login")
    @Description("Checking not found courier login")
    @Step("CHeck response")
    public void notFoundCourierCantLoginTest() {
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.from(courier));
        responseLogin.assertThat()
                .statusCode(404);

    }


    @Test
    @DisplayName("Courier Cant Login without password /api/v1/courier/login")
    @Description("Checking courier login without password")
    @Step("CHeck response")
    public void courierCantLoginWithoutPasswordTest() {
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.of(courier));
        responseLogin.assertThat()
                .statusCode(400);

    }

    @Test
    @DisplayName("Courier Cant Login without login /api/v1/courier/login")
    @Description("Checking courier login without login")
    @Step("CHeck response")
    public void courierCantLoginWithoutLoginTest() {
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.noLogin(courier));
        responseLogin.assertThat()
                .statusCode(400);

    }

}

