package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppBaseUrl {

    protected RequestSpecification specHerOku;
    @Before
    public void setUp(){
        specHerOku= new
                RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
    }
}
