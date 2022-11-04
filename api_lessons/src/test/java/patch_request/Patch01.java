package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									    }
     */

    @Test
    public void Patch01() {
        specJsonPlace.pathParams("pp1", "todos", "pp2", 198);
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataPatch = obj.expectedDataWithMissingKeys(null, "Wash the dishes", null);
        Response response = given().spec(specJsonPlace).contentType(ContentType.JSON).body(expectedDataPatch).when().patch("/{pp1}/{pp2}");
        response.prettyPrint();
        Map<String, Object> expectedData = obj.expectedDataWithAllKeys(10, "Wash the dishes", true);
        response.then().assertThat().statusCode(200).body("title", equalTo(expectedData.get("title")),
                "completed", equalTo(expectedData.get("completed")),
                "userId", equalTo(expectedData.get("userId")));
    }
}
