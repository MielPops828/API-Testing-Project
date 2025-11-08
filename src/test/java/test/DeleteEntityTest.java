package test;

import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
@Epic("API-Test")
@Feature("Удаление сущности")
public class DeleteEntityTest extends BaseTest{
    @Test
    @Description("Тест удаления сущности по id")
    public void deleteEntityTest(){
        List<EntityResponse> entitiesList = given(spec)
                .get("/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("entity", EntityResponse.class);
        int entityId = entitiesList.get(0).getId();

        Response response = given(spec)
                .delete("/delete/" + entityId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .extract()
                .response();
    }
}
