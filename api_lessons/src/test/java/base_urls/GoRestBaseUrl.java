package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {

    protected RequestSpecification specGoRest;
    @Before
    public void SetUp(){
        specGoRest=new RequestSpecBuilder().
                setBaseUri("https://gorest.co.in/public/v1/").
                build();
    }
}
