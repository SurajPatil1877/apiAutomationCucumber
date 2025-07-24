package practiceTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleGetAPI {

    @Test
    public void simpleProductListAPI() {
        //init the request spec class

        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.baseUri("https://automationexercise.com")
                                                .basePath("/api/productsList")
                                                .contentType(ContentType.JSON)
                                                .get();

        System.out.printf(response.asString());

    }

    @Test
    public void defineHeaders() {
        Map<String, Object> headersMap = Map.of(
                "header-one", "value",
                "header-two", "value2");

        Header header = new Header("header-name", "header-value");
        Header header1 = new Header("header-name", "header-value");

        Headers headers = new Headers(header, header1);
        Headers usingList = new Headers(List.of(header, header1));

        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.baseUri("https://automationexercise.com")
                                                .basePath("/api/productsList")
                                                .contentType(ContentType.JSON)
                                                //custom name and value
                                                .header("test-header", "test-value")
                                                //multiple headers with key value
                                                .headers("test-header1", "test-value1", "test-header2", "test-value2")
                                                //using hashmap
                                                .headers(headersMap).
                                                //inbuilt
                                                        header(header).
                                                //inbuilt multiple
                                                        headers(headers).headers(usingList)
                                                .get();

        System.out.printf(response.asString());
    }
}
