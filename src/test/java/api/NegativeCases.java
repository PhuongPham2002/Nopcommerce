package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class NegativeCases {
    int productId = 9999;

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://localhost:5000";
        RestAssured.basePath = "/odata/v1";
        RestAssured.authentication = basic ("295eebe843a1747a60f534b98b8a67a6", "26f3bfa5af713aa083be1ccf1ba15f58");
    }

    @Test
    public void getProductWithInvalidId(){
        given().pathParam("id",productId)
                .when()
                .get("/products({id})")
                .then()
                .statusCode(404);
    }

    @Test
    public void postWithoutRequiredField(){
       Response response= given().header("Content-Type", "application/json")
        .body("{\"Price\":70000.0}")
                .when()
                .post("/products")
                .then()
               .log().all()
                .statusCode(400)
                .body("error.details[0].message",containsString("The Name field is required."))
               .extract().response();
    }

    @Test
    public void postWithWrongDataType(){
        given().header("Content-Type","application/json")
                .body("{\"Name\":test,\"Price\": \"money\"}")
                .when()
                .post("/products")
                .then()
                .log().all()
                .statusCode(422);
    }
}
