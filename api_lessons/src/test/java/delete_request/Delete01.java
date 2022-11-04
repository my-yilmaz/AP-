package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void Delete01() {
        specJsonPlace.pathParams("pp1", "todos", "pp2", 198);
        Response response = given().spec(specJsonPlace).when().delete("/{pp1}/{pp2}");
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.getStatusCode());
        assertTrue(actualData.isEmpty());
    }
}
