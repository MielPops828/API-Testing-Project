package test;

import dto.request.AdditionRequest;
import dto.request.EntityRequest;
import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
        EntityRequest request = EntityRequest.builder()
                .title("GetAll Entities")
                .verified(true)
                .importantNumbers(java.util.List.of(42, 87, 15))
                .addition(AdditionRequest.builder()
                        .additionalInfo("Доп. данные")
                        .additionalNumber(123)
                        .build())
                .build();

        Response createEntity = given()
                .spec(spec)
                .accept(ContentType.TEXT)
                .body(request)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Response response = given(spec)
                .get("/getAll")
                .then()
                .statusCode(200)
                .extract()
                .response();
        List<EntityResponse> entities = response.jsonPath().getList("entity", EntityResponse.class);
        Assert.assertFalse(entities.isEmpty(), "Сущности не найдены");
        Assert.assertNotNull(entities.get(0).getAddition());
    }
}
