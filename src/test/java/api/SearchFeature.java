package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.basic;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class SearchFeature {
    public void searchProductById() {
        searchProductId("/odata/v1/products/search");
    }

    public void wrongEndpoint() {
        searchProductId("/odata/v1/products/sear");
    }

    private void searchProductId(String endpoint) {
        RestAssured.authentication= basic("295eebe843a1747a60f534b98b8a67a6", "26f3bfa5af713aa083be1ccf1ba15f58");
        Map<String, String> queryParams = buildQueryParams();
        Response response = RestAssured.given().log().all()
                .baseUri("http://localhost:5000")
                .header("Content-Type", "application/json")
                .queryParams(queryParams)
                .when()
                .post(endpoint);
        // In ra response để kiểm tra
        response.prettyPrint();

        // Xác minh response
        response.then()
                .statusCode(200); // Kiểm tra mã trạng thái HTTP
        // Kiểm tra xem value có phải là mảng rỗng không
        response.then()
                .body("value", is(not(emptyArray()))); // Kiểm tra mảng rỗng

        // Sau khi xác minh không rỗng, kiểm tra các giá trị trong value
        response.then()
                .body("value[0].ProductTypeId", equalTo(5))
                .body("value[0].Name", equalTo("iPhone Plus"))
                .body("value[0].Price", equalTo(878.0000))
                .body("value[0].Published", equalTo(true))
                .body("value[0].IsShippingEnabled", equalTo(true));
    }

    private Map<String, String> buildQueryParams() {
        Map<String, String> defaultParams = new HashMap<>();
        defaultParams.put("q", "iphone");
        defaultParams.put("i", "1557");
        defaultParams.put("s", "1557");
        defaultParams.put("o", "CreatedOnAsc");
        defaultParams.put("c", "String");
        // thêm các tham số mặc định khác nếu cần

        return defaultParams;
    }

}
