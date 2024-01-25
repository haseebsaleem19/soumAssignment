package testCases;

import config.ApplicationConfigReader;
import config.EndpointURLs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Test cases for the product API, covering positive and negative scenarios.
 * The test cases use TestNG for test execution and RestAssured for API testing.
 *
 * TestNG @Test annotation is used with parameters:
 * invocationCount: Specifies the number of times this test method should be invoked.
 * threadPoolSize: Specifies the number of threads to be used when executing this test method in parallel.
 */
@Listeners(extentReport.ExtentReportListener.class)
public class productApiTests {

    // Define the API URL by combining base URL, path, API version, and endpoint
    private static final String url = ApplicationConfigReader.getProtocol() +
            ApplicationConfigReader.getBaseUrl() + ApplicationConfigReader.getPath() +
            ApplicationConfigReader.getApiVersion() + EndpointURLs.product;

    /**
     * Positive test case to verify success for a valid request.
     * This test sends a GET request with valid parameters and checks for a successful response.
     * It validates the status code, success message, and presence of necessary fields in the response body.
     */

    @Test (invocationCount = 1, threadPoolSize = 1)
    public void verifySuccessForValidRequest() {
        given()
                .queryParam("page number", "1")
                .queryParam("size", ApplicationConfigReader.getSizeOfProduct())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Get Products successfully"))
                .body("responseData.info", hasKey("total"))
                .body("responseData.info", hasKey("pages"))
                .body("responseData.results", hasSize(greaterThan(0)))
                .body("responseData.results[0].product_images", hasSize(greaterThan(0)))
                .body("responseData.results[0].description", not(emptyString()));
    }

    /**
     * Negative test case to verify bad request for null parameters.
     * This test sends a GET request with null parameters and checks for a proper error response.
     * It validates the status code, failure message, and error details in the response body.
     */
    @Test (invocationCount = 1, threadPoolSize = 1)
    public void verifyBadRequestForNullParameters() {
        given()
                .queryParam("page number", "")
                .queryParam("size", "")
                .when()
                .get(url)
                .then()
                .statusCode(400)
                .body("status", equalTo("fail"))
                .body("violations[0].code", equalTo(400))
                .body("violations[0].message", equalTo("Fail to get product with pagination"));
    }

    /**
     * Negative test case to verify bad request for invalid size parameters.
     * This test sends a GET request with an invalid size parameter and checks for a proper error response.
     * It validates the status code, failure message, and error details in the response body.
     */
    @Test (invocationCount = 1, threadPoolSize = 1)
    public void verifyBadRequestForInvalidSizeParameters() {
        given()
                .queryParam("page number", 1)
                .queryParam("size", "haseeb")
                .when()
                .get(url)
                .then()
                .statusCode(400)
                .body("status", equalTo("fail"))
                .body("violations[0].code", equalTo(400))
                .body("violations[0].message", equalTo("Fail to get product with pagination"));
    }

    /**
     * Test to verify the count of objects in the results array.
     * This test sends a GET request and validates the count of objects in the response.
     * It checks the status code, success message, and the expected count of results in the response body.
     */
    @Test (invocationCount = 1, threadPoolSize = 1)
    public void verifyObjectCountInResultsArray() {
        given()
                .queryParam("page number", 1)
                .queryParam("size", ApplicationConfigReader.getSizeOfProduct())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body("message", equalTo("Get Products successfully"))
                .body("responseData.results.size()", equalTo(Integer.parseInt(ApplicationConfigReader.getSizeOfProduct())));
    }

    /**
     * Test to verify total and pages in the response.
     * This test sends a GET request, extracts values from the response, and validates total and pages.
     * It checks the status code, success message, and performs assertions on total and pages using TestNG.
     */
    @Test (invocationCount = 1, threadPoolSize = 1)
    public void verifyPerPagesValue() {
        int total = 0, pages = 0, finalValue = 0;

        // Send a GET request and extract values from the response
        Response response = given()
                .queryParam("page number", 1)
                .queryParam("size", ApplicationConfigReader.getSizeOfProduct())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body("message", equalTo("Get Products successfully"))
                .body("responseData.results.size()", equalTo(Integer.parseInt(ApplicationConfigReader.getSizeOfProduct())))
                .extract().response();

        // Extract values from the response and store them in variables
        total = response.path("responseData.info.total");
        pages = response.path("responseData.info.pages");
        finalValue = (int) Math.ceil((double) total / Integer.parseInt(ApplicationConfigReader.getSizeOfProduct()));

        // Verify total and pages using TestNG Assert
        Assert.assertEquals(pages, finalValue);
    }

    /**
     * Test to verify localization using a different header.
     * This test sends a GET request with a different Accept-Language header for localization testing.
     * It checks the status code and validates specific localized fields in the response body.
     */
    @Test (invocationCount =1,  threadPoolSize = 1)
    public void verifyLocalizationUsingDifferentHeader() {
        given()
                .header("Accept-Language", "ar")
                .queryParam("page number", "1")
                .queryParam("size", ApplicationConfigReader.getSizeOfProduct())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body("responseData.results[0].answer_to_questions_ar", not(emptyString()))
                .body("responseData.results[0].grade_ar", not(emptyString()));
    }

    /**
     * Negative test case to verify a different API method.
     * This test sends a PUT request with parameters and checks for an expected failure response.
     * It validates the status code, indicating a bad request, due to wrong API method.
    */
    @Test (invocationCount = 1, threadPoolSize = 1)
    public void verifyPutApiMethodNotAllowedInGetContext() {
        given()
                .header("Accept-Language", "ar")
                .queryParam("page number", "1")
                .queryParam("size", ApplicationConfigReader.getSizeOfProduct())
                .when()
                .put(url)
                .then()
                // Verifying the response status code is 400 (Bad Request)
                .statusCode(400);
    }
}
