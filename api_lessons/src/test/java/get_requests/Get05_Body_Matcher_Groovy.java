package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get05_Body_Matcher_Groovy extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Aalok Acharya DDS", "Agastya Somayaji", "Acharyasuta Chattopadhyay DC" are among the users
        And
            The female users are more than or equals to male users
     */

    @Test
    public void get01() {
        specGoRest.pathParam("pp1", "users");
        Response response = given().spec(specGoRest).when().get("/{pp1}");
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Kannan Guneta", "Durga Tagore", "Ramaa Iyer"));
        //1. way *** java
        JsonPath json = response.jsonPath();
        List<String> gender = json.getList("data.gender");
        System.out.println("gender = " + gender);
        int numOfFemales = 0;
        for (String w : gender
        ) {
            if (w.equals("female"))
                numOfFemales++;
        }
        System.out.println("numOfFemales = " + numOfFemales);
        assertTrue(numOfFemales > gender.size() - numOfFemales);

        // 2. way: Groovy languch
        List<String> femaleList=json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println("FemaleList = " + femaleList);

        List<String> maleList=json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("FemaleList = " + maleList);
        assertTrue(femaleList.size()>maleList.size());
    }

}
