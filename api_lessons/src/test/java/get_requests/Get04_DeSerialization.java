package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get04_DeSerialization extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/2965
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                {
                 https://gorest.co.in/public/v1/users/2965
        {
    "meta": null,
    "data": {
        "id": 2965,
        "name": "Suryakant Mahajan",
        "email": "mahajan_suryakant@brown-veum.org",
        "gender": "male",
        "status": "active"
    }
}
     */

    @Test
    public void Get01() {
        specGoRest.pathParams("pp1", "users", "pp2", 2965);
        GoRestTestData obj = new GoRestTestData();
        Map<String, String> expectedDataKey = obj.expectedDataKey("Suryakant Mahajan", "mahajan_suryakant@brown-veum.org",
                "male", "active");
        Map<String, Object> expectedData = obj.expectedData(null, "data");
        Response response = given().spec(specGoRest).when().get("/{pp1}/{pp2}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(expectedDataKey.get("name"),((Map)actualData.get("data")).get("name"));
        assertEquals(expectedDataKey.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(expectedDataKey.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(expectedDataKey.get("status"),((Map)actualData.get("data")).get("status"));

    }
}
