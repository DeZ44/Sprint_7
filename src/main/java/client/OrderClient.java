package client;

import data.OrderData;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {
    private static final String CREATE_ORDER_PATH = "/api/v1/orders";

    public ValidatableResponse createOrder(OrderData order){
        return given()
                .spec(requestSpecification())
                .and()
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH)
                .then();
    }


}
