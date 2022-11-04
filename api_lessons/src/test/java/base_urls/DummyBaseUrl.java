package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyBaseUrl {
    protected RequestSpecification specDummy;

    @Before
    public void SetUp() {
        specDummy = new RequestSpecBuilder().
                setBaseUri("https://dummy.restapiexample.com/api/v1").
                build();
    }

}
