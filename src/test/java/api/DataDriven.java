package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class DataDriven {

    @BeforeClass
    public void setUp() {
        RestAssured.authentication = basic("295eebe843a1747a60f534b98b8a67a6", "26f3bfa5af713aa083be1ccf1ba15f58");
        ;
        RestAssured.baseURI = "http://localhost:5000";
        ;
        RestAssured.basePath = "/odata/v1";

    }

    @Test
    public void testCreateProductWithInvalidData() {
        Object[][] dataTest = {
                {"", 1000.0f,400},
                {"test", "price",400},
                {"test01", -100.0f,422},
                {"x".repeat(400), 2000.0f, 422},
                {"validName", 2000.0f,201}
        };

        for (Object[] data : dataTest) {
            String name = (String) data[0];
            System.out.println(name);
            Object price = data[1];
            System.out.println(price);
            int expectedStatusCode = (Integer) data[2];
            System.out.println(expectedStatusCode);
            String requestBody = String.format("{\"Name\":\"%s\",\"Price\":%s}", name, price);
            Response response = given().header("Content-Type", "application/json")
                    .body(requestBody)
                    //.body("{\"Name\":" + name + ",\"Price\":" + price + "}")
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(expectedStatusCode)
                    .extract().response();
        }
    }
    @DataProvider (name="Product Data")
    public Object[][] provideLoginData(){
        return new Object[][]{
                {"", 1000.0f,400},
                {"test", "price",400},
                {"test01", -100.0f,201},
                {"x".repeat(500), 2000.0f, 422},
                {"validName", 2000.0f,201}
        };
    }


    @Test (dataProvider = "Product Data")
    public void createProductWithDataProvider(String name, Object price, int expectedStatusCode ){
        String requestBody = String.format("{\"Name\":\"%s\",\"Price\":%s}", name, price);
        Response response = given().header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/products")
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();
    }

}
