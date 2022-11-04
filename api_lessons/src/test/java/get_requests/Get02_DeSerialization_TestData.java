package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get02_DeSerialization_TestData extends JsonPlaceHolderBaseUrl {
    //De-Serialization: JSON formatından Java objesine çevirme.
    //Serialization: Java objesini JSON formatına çevirme.
    // De-Serialization ve Serialization iki türlü yapılır:
    //Gson: Google tarafından üretiliştir.
    //Object Mapper: Daha popüler ***

     /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void get01() {
        specJsonPlace.pathParams("pp1","todos","pp2",2);
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        Map<String,Object> expectedData =obj.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui",false);
        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        Map<String, Object> actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));
    }
}
