package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02_Nested_Data extends HerOkuAppBaseUrl {
    /*
        Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "Mohita",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
                   "additionalneeds": "Breakfast"
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                    "additionalneeds": "Breakfast"
                                             }
     */

    @Test
    public void post01() {
        specHerOku.pathParam("pp1", "booking");
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String,Object> expectedDataBD=obj.bookingDatesAllKeys("2021-09-09","2021-09-21");
        Map<String,Object> expectedData=obj.
                expectedDataWithAllKeys("John","Doe",11111,true, expectedDataBD,"Breakfast");
        Response response=given().spec(specHerOku).contentType(ContentType.JSON).body(expectedData).when().post("/{pp1}");
        response.prettyPrint();
        Map<String, Object> actualData=response.as(HashMap.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),((Map)actualData.get("booking")).get("additionalneeds"));
        assertEquals(expectedDataBD.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedDataBD.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
    }
}
