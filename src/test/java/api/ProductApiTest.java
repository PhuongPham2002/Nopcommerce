package api;

import commons.helpers.CommonHelper;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductApiTest {
    int productId;
    int invalidId = 100000;
    String newName = "Product" + CommonHelper.generateRandomNumber();
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:5000";
        RestAssured.basePath = "/odata/v1";
        RestAssured.authentication = basic("295eebe843a1747a60f534b98b8a67a6", "26f3bfa5af713aa083be1ccf1ba15f58");
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();

    }

    @Test(priority = 1)
    public void createProduct() throws IOException {
        Map<String, Object> requestBody = new HashMap<>();
        Product product = new Product(newName, 6000.0000f);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("Name", equalTo(newName))
                .body("Price", equalTo(6000.0000f))
                .extract().response();
        response.prettyPrint();
        productId = response.jsonPath().getInt("Id");
    }

    @Test(priority = 2)
    public void getProductDetail() {
        Response response = given()
                .when()
                .get("/products(" + productId + ")")
                .then()
                .statusCode(200)
                .body("Id", equalTo(productId))
                .body("Name", equalTo(newName))
                .body("Price", equalTo(6000.0000f))
                .extract().response();
        response.prettyPrint();
    }

    @Test(priority = 3)
    public void updateProduct() throws IOException {
        String updateBody = new String(Files.readAllBytes(Paths.get("src/test/resources/testData/json/updateProduct.json")));
        updateBody = updateBody.replace("id", String.valueOf(productId));
        updateBody = updateBody.replace("updateName", newName);
        given()
                .header("Content-Type", "application/json")
                .body(updateBody)
                .when()
                .put("/products(" + productId + ")")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }

    @Test(priority = 4)
    public void deleteProduct() {
        given().when().delete("/products(" + productId + ")")
                .then().statusCode(anyOf(is(200), is(204)));
    }

    @Test(priority = 5)
    public void verifyInvalidProduct() {
        given()
                .when()
                .get("/products(" + productId + ")")
                .then()
                .statusCode(404);
    }

    @Test
    public void endToEndCRUDProduct() throws IOException {

        //Create product
        Map<String, Object> requestBody = new HashMap<>();
        Product product = new Product(newName, 6000.0000f);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("Name", equalTo(newName))
                .body("Price", equalTo(6000.0000f))
                .extract().response();
        //response.prettyPrint();
        productId = response.jsonPath().getInt("Id");

        //Verify created product
        given().queryParam("$filter", "Name eq '" + newName + "'")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("value[0].Id", equalTo(productId))
                .body("value[0].Name", equalTo(newName))
                .body("value[0].Price", equalTo(6000.0000f))
                .extract().response();


//        String updateBody = new String(Files.readAllBytes(Paths.get("src/test/resources/testData/json/updateProduct.json")));
//        updateBody = updateBody.replace("id", String.valueOf(productId));
//        updateBody = updateBody.replace("updateName", newName);
//
//        given().header("Content-Type", "application/json")
//                        .body(updateBody)
//                        .when()
//                        .put("/products(" + productId + ")")
//                        .then()
//                        .statusCode(anyOf(is(200), is(204)));
//        given().
//                when().delete("/products(" + productId + ")")
//                .then().statusCode(anyOf(is(200), is(204)));
//        given()
//                .when()
//                .get("/products(" + productId + ")")
//                .then()
//                .statusCode(404);
    }
    @Test
    public void responseHandling(){
        Response response = given().when().get("/products");
       //int id = response.jsonPath().getInt("id");
        //response.prettyPrint();
//       List<Map<String,Object>> listProduct = response.jsonPath().getList("value");
//       int productTypeID = (Integer) listProduct.get(1).get("ProductTypeId");
//        System.out.println(productTypeID);
//
//        int productTypeID1 = response.jsonPath().getInt("value[0].ProductTypeId");
//        System.out.println(productTypeID1);
        List<Product> products = response.jsonPath().getList("value",Product.class);
        System.out.println(products.get(1).getName());
        System.out.println(products.get(1).getPrice());


    }


    }
