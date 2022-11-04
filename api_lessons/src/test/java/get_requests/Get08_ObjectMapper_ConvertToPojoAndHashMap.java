package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.jsonPlaceHolder.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08_ObjectMapper_ConvertToPojoAndHashMap extends JsonPlaceHolderBaseUrl {
     /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */
    @Test
    public void Get01_ConvertToPojo() {
        specJsonPlace.pathParams("pp1","todos","pp2",198);
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        String expectedDataInString=obj.expectedDataInString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedaData= JsonUtil.convertJsonToJavaObject(expectedDataInString,JsonPlaceHolderPojo.class);
        Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        JsonPlaceHolderPojo actualData=JsonUtil.convertJsonToJavaObject(response.asString(),JsonPlaceHolderPojo.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedaData.getUserId(),actualData.getUserId());
        assertEquals(expectedaData.getTitle(),actualData.getTitle());
        assertEquals(expectedaData.getCompleted(),actualData.getCompleted());
    }
    public  void Get02_ConvertToHashMap(){
        specJsonPlace.pathParams("pp1","todos","pp2",198);
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        String expectedDataInString=obj.expectedDataInString(10,"quis eius est sint explicabo",true);
        HashMap<String, Object> expectedaData=JsonUtil.convertJsonToJavaObject(expectedDataInString,HashMap.class);
        Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        HashMap<String, Object> actualData=JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedaData.get("userId"),actualData.get("userId"));
        assertEquals(expectedaData.get("title"),actualData.get("title"));
        assertEquals(expectedaData.get("completed"),actualData.get("completed"));

    }
}
