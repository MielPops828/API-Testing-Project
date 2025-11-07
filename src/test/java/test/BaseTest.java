package test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import util.Configuration;

public abstract class BaseTest {

    protected Configuration config;
    protected RequestSpecification spec;

    @BeforeClass
    public void setup(){
        config = ConfigFactory.create(Configuration.class);
        spec = new RequestSpecBuilder()
                .setBaseUri(config.getUrl())
                .setBasePath(config.getPath())
                .setContentType(ContentType.JSON)
                .build();
    }
}
