package test;

import dto.request.AdditionRequest;
import dto.request.EntityRequest;
import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

@Epic("API-Test")
@Feature("Получить сущность")
public class GetEntityTest extends BaseTest{
    @Test
    @Description("Тест получения сущности по id")
    public void getEntityTest(){
        EntitySteps step = new EntitySteps(spec);
        SoftAssert softAssert = new SoftAssert();
        EntityRequest request = EntityRequest.builder()
                .title("Get Entity")
                .verified(true)
                .importantNumbers(java.util.List.of(42, 87, 15))
                .addition(AdditionRequest.builder()
                        .additionalInfo("Доп. данные")
                        .additionalNumber(123)
                        .build())
                .build();

        int entityId = step.initEntity(request);

        EntityResponse response = given().spec(spec)
                .get("/get/{id}", entityId)
                .then()
                .statusCode(200)
                .extract().as(EntityResponse.class);

        softAssert.assertEquals(response.getId(), entityId, "ID сущности не совпадает");
        softAssert.assertEquals(response.getTitle(), request.getTitle(), "Название сущности не совпадает");
        softAssert.assertEquals(response.isVerified(), request.isVerified(), "Флаг не совпадает");
        softAssert.assertEquals(response.getImportantNumbers(), request.getImportantNumbers(), "Список чисел не совпадает");
        softAssert.assertEquals(response.getAddition().getAdditionalInfo(), request.getAddition().getAdditionalInfo(), "Дополнительная информация не совпадает");
        softAssert.assertEquals(response.getAddition().getAdditionalNumber(), request.getAddition().getAdditionalNumber(), "Дополнительное число не совпадают");
        softAssert.assertAll();
    }
}
