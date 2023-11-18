import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static constans.BaseUri.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    @DisplayName("Get list order /api/v1/orders")
    @Description("Checking get order list is very important")
    @Step("CHeck response")
    public void getListOrder(){
        Response response = given()
                .get("/api/v1/orders?limit=10&page=0");
        response.then()
                .statusCode(200)
                .assertThat().body("orders", notNullValue());
    }
}
