package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.restFul.BookingPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;

public class Get09_ObjectMapper extends HerOkuAppBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                            {
                      "firstname": "Jim",
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
        specHerOku.pathParams("pp1", "booking", "pp2", 22);
        HerOkuAppTestData obj = new HerOkuAppTestData();
        String expectedDataString = obj.expectedDataString("Jim", "Brown", 111, true, "2018-01-01", "2019-01-01", "Breakfast");
        BookingPojo expectedData = JsonUtil.convertJsonToJavaObject(expectedDataString, BookingPojo.class);
        Response response = given().spec(specHerOku).when().get("/{pp1}/{pp2}");
        BookingPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(), BookingPojo.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        softAssert.assertEquals(actualData.getLastname(), expectedData.getLastname());
        softAssert.assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin());
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout());
        softAssert.assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());
        softAssert.assertAll();

    }
}
