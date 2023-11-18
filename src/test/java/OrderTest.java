import data.OrderData;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final ArrayList<String> color;

    private final ArrayList<String> colors;


    OrderData order;



    public OrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        colors = new ArrayList<String>();
        switch (color) {
            case "1":
                colors.add(new String("BLACK"));
                break;
            case "2":
                colors.add(new String("GREY"));
                break;
            case "3":
                colors.add(new String("BLACK"));
                colors.add(new String("GREY"));
                break;
        }

        this.color = colors;
    }



    @Parameterized.Parameters
    public static Object[][] postNewOrder() {
        return new Object[][]{
                {/*firstName*/ "апр",/*lastName*/ "аывп", /*address*/ "ав34ип", /*metroStation*/ "авипропрп", /*phone*/ "авпо4564п", /*rentTime*/ 5, /*deliveryDate*/ "авип", /*comment*/ "ав564ип", "0"},
                {/*firstName*/ "gdf",/*lastName*/ "аывп", /*address*/ "авип", /*metroStation*/ "аповип", /*phone*/ "авип", /*rentTime*/ 1, /*deliveryDate*/ "авкегнип", /*comment*/ "ав456ип", "1"},
                {/*firstName*/ "родбролд",/*lastName*/ "аывп", /*address*/ "авиукеп", /*metroStation*/ "авип", /*phone*/ "авип", /*rentTime*/20, /*deliveryDate*/ "ав45ип", /*comment*/ "авип456", "2"},
                {/*firstName*/ "апрплорр",/*lastName*/ "аывп", /*address*/ "авип!вкаы", /*metroStation*/ "авипроп", /*phone*/ "ави546п", /*rentTime*/ 6, /*deliveryDate*/ "авеноип", /*comment*/ "авиарпп", "3"}

        };
    }

    @Before
    public void setup() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Create order /api/v1/orders")
    @Description("Checking create order is very important")
    @Step("CHeck response")
    public void orderTest() {

        order = new OrderData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = given()
                .body(order)
                .when()
                .post("/api/v1/orders");
        response.then().statusCode(201)
                       .body("track", notNullValue());
    }





}
