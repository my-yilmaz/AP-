package get_requests;

import base_urls.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.dummy.DummyDataPojo;
import pojos.dummy.DummyResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get00_A extends DummyBaseUrl {
     /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */
//**********************************************************************************
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employee/1
    When
        I send GET REQUEST to the URL
    Then
       Status code is 200
    And
       "employee_name" is "Tiger Nixon"
    And
       "employee_salary" is 320800
    And
       "employee_age" is 61
    And
       "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void Get01() {
        specDummy.pathParams("pp1", "employee", "pp2", 1);
        DummyDataPojo dataPojo = new DummyDataPojo("Tiger Nixon", 320800, 61, "");
        DummyResponseBodyPojo expectedData = new DummyResponseBodyPojo("success", dataPojo, "Successfully! Record has been fetched.");
        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");
        DummyResponseBodyPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(), DummyResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
    }
}
