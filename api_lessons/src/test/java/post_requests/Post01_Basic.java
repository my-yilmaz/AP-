package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01_Basic extends JsonPlaceHolderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)
        When
            I send POST Request to the Url
        Then
            Status code is 201 {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                              }
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void Post01() {
        specJsonPlace.pathParam("pp1","todos");
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        Map<String, Object> expecetData=obj.expectedDataWithAllKeys(55,"Tidy your room",false);
        Response response=
                given().
                spec(specJsonPlace).
                contentType(ContentType.JSON).
                body(expecetData).
                when().
                post("/{pp1}");
        response.prettyPrint();
        Map<String,Object> actualData=response.as(HashMap.class);
        assertEquals(201,response.getStatusCode());
        assertEquals(expecetData.get("userId"),actualData.get("userId"));
        assertEquals(expecetData.get("title"),actualData.get("title"));
        assertEquals(expecetData.get("completed"),actualData.get("completed"));
    }
}
