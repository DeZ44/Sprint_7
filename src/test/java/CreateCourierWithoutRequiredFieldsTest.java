import client.CourierClient;
import data.CourierData;
import data.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CreateCourierWithoutRequiredFieldsTest {

    CourierClient courierClient;
    CourierData courier;

    @Test
    @DisplayName("Check status code create without login /api/v1/courier")
    @Description("Create courier without login")
    @Step("CHeck response")
    public void createCourierWithoutLoginTest() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourierWithoutLogin();
        ValidatableResponse response = courierClient.createCourier(courier);

        response.assertThat()
                .statusCode(400);

    }

    @Test
    @DisplayName("Check status code create without password /api/v1/courier")
    @Description("Checking courier create without password")
    @Step("CHeck response")
    public void createCourierOnlyLoginTest() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourierOnlyLogin();
        ValidatableResponse response = courierClient.createCourier(courier);

        response.assertThat()
                .statusCode(400);

    }
}
