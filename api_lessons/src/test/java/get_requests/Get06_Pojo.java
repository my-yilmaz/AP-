package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.restFul.BookingDatesPojo;
import pojos.restFul.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get06_Pojo extends HerOkuAppBaseUrl {
      /*
        Given
            https://restful-booker.herokuapp.com/booking/56
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                          {
                       "firstname": "Sally",
                       "lastname": "Brown",
                       "totalprice": 111,
                       "depositpaid": true,
                       "bookingdates": {
                           "checkin": "2013-02-23",
                           "checkout": "2014-10-23"
                       },
                       "additionalneeds": "Breakfast"
                    }
     */

    @Test
    public void Get01() {
        specHerOku.pathParams("pp1", "booking", "pp2", 56);
        BookingDatesPojo expectedDataBT = new BookingDatesPojo("2013-02-23", "2014-10-23");
        BookingPojo expectedData = new BookingPojo("Sally", "Brown", 111, true, expectedDataBT, "Breakfast");
        Response response = given().spec(specHerOku).when().get("/{pp1}/{pp2}");
        response.prettyPrint();
        BookingPojo acualData = response.as(BookingPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstname(),acualData.getFirstname());
        assertEquals(expectedData.getLastname(),acualData.getLastname());
        assertEquals(expectedData.getTotalprice(),acualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),acualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),acualData.getAdditionalneeds());
        assertEquals(expectedData.getBookingdates().getCheckin(),acualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),acualData.getBookingdates().getCheckout());

    }
}
