package test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Epic("API-Test")
@Feature("Удаление сущности")
public class DeleteEntityTest extends BaseTest{
    @Test
    @Description("Тест удаления сущности по id")
    public void createEntityTest(){
        int entityId = 6;
        Response response = given(spec)
                .delete("/delete/" + entityId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .extract()
                .response();
    }
}
