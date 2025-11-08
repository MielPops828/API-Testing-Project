package test;

import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
@Epic("API-Test")
@Feature("Получить список сущностей")
public class GetAllEntityTest extends BaseTest{
    @Test
    @Description("Тест получения списка всех сущностей")
    public void getAllEntityTest(){
        Response response = given(spec)
                .get("/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        List<EntityResponse> entities = response.jsonPath().getList("entity", EntityResponse.class);
        Assert.assertFalse(entities.isEmpty(), "Сущности не найдены");
        Assert.assertNotNull(entities.get(0).getAddition());
    }
}
