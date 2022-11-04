package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.goRest.GoRestDataPojo;
import pojos.goRest.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get07_Pojo extends GoRestBaseUrl {
    /*
        Given

        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        Andhttps://gorest.co.in/public/v1/users/2508
            Response body should be like
           {
                "meta": null,
                "data": {
                    "id": 2508,
                    "name": "Amish Arora",
                    "email": "amish_arora@stark-champlin.co",
                    "gender": "male",
                    "status": "active"
                }
            }
    */

    @Test
    public void Get01() {
        specGoRest.pathParams("pp1", "users", "pp2", 2508);
        GoRestDataPojo expectedDataDP = new GoRestDataPojo(2508,"Amish Arora", "amish_arora@stark-champlin.co", "male", "active");
        GoRestResponseBodyPojo expectedData = new GoRestResponseBodyPojo(null, expectedDataDP);
        Response response = given().spec(specGoRest).when().get("/{pp1}/{pp2}");
        response.prettyPrint();
        GoRestResponseBodyPojo actualData = response.as(GoRestResponseBodyPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataDP.getId(), actualData.getData().getId());
        assertEquals(expectedDataDP.getName(), actualData.getData().getName());
        assertEquals(expectedDataDP.getEmail(), actualData.getData().getEmail());
        assertEquals(expectedDataDP.getGender(), actualData.getData().getGender());
        assertEquals(expectedDataDP.getStatus(), actualData.getData().getStatus());

    }
}
