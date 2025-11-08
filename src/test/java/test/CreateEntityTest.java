package test;

import dto.request.AdditionRequest;
import dto.request.EntityRequest;
import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

@Epic("API-Test")
@Feature("Создание сущности")
public class CreateEntityTest extends BaseTest{
    @Test
    @Description("Тест создания новой сущности")
    public void createEntityTest(){
        AdditionRequest addition = AdditionRequest.builder()
                .additionalInfo("Дополнительные сведения")
                .additionalNumber(123)
                .build();

        EntityRequest request = EntityRequest.builder()
                .title("Заголовок сущности")
                .verified(true)
                .importantNumbers(Arrays.asList(42, 87, 15))
                .addition(addition)
                .build();

        Response response = given(spec)
                .body(request)
                .post("/create")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Неверный код ответа");

        int entityId = Integer.parseInt(response.asString());
        Assert.assertTrue(entityId > 0, "Неверный ID созданной сущности");
    }
}
