package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get03_DeSerialization_Nested_TestData extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
      {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
      }
     */

    @Test
    public void Get01() {
        specHerOku.pathParams("pp1", "booking", "pp2", 91);
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String, Object> expectedBookinDates = obj.bookingDatesAllKeys("2022-07-21", "2022-08-04");
        Map<String, Object> expectedData = obj.expectedDataWithAllKeys("Ivy", "Taylor", 960, true,
                expectedBookinDates, "Breakfast");
        Response response = given().spec(specHerOku).when().get("/{pp1}/{pp2}");
        Map<String, Object> actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

        assertEquals(expectedBookinDates.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookinDates.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));

    }
}
