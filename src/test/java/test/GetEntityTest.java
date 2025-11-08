package test;

import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("API-Test")
@Feature("Получить сущность")
public class GetEntityTest extends BaseTest{
    @Test
    @Description("Тест получения сущности по id")
    public void getEntityTest(){
        int entityId = 5;
        Response response = given(spec)
                .get("/get/" + entityId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        EntityResponse entity = response.as(EntityResponse.class);
        Assert.assertEquals(entity.getId(), entityId);
        Assert.assertNotNull(entity.getTitle());
        Assert.assertNotNull(entity.getAddition());
    }
}
