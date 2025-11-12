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
@Feature("Обновление сущности и ее дополнение")
public class PatchEntityTest extends BaseTest {
    @Test
    @Description("Тест обновления сущности и ее дополнения")
    public void patchEntityTest(){
        EntitySteps step = new EntitySteps(spec);
        SoftAssert softAssert = new SoftAssert();
        EntityRequest original = EntityRequest.builder()
                .title("Patch Entity")
                .verified(true)
                .importantNumbers(java.util.List.of(1, 2))
                .addition(AdditionRequest.builder()
                        .additionalInfo("old info")
                        .additionalNumber(1)
                        .build())
                .build();

        int entityId = step.initEntity(original);

        EntityRequest updated = EntityRequest.builder()
                .title("Updated Patch")
                .verified(false)
                .importantNumbers(java.util.List.of(99))
                .addition(AdditionRequest.builder()
                        .additionalInfo("new info")
                        .additionalNumber(999)
                        .build())
                .build();

        given().spec(spec)
                .body(updated)
                .patch("/patch/{id}", entityId)
                .then()
                .statusCode(204);

        EntityResponse response = step.getEntityById(entityId);

        softAssert.assertEquals(response.getTitle(), updated.getTitle(), "Название сущности не совпадает");
        softAssert.assertEquals(response.isVerified(), updated.isVerified(), "Флаг не совпадает");
        softAssert.assertEquals(response.getImportantNumbers(), updated.getImportantNumbers(), "Список чисел не совпадает");
        softAssert.assertEquals(response.getAddition().getAdditionalInfo(), updated.getAddition().getAdditionalInfo(), "Дополнительная информация не совпадает");
        softAssert.assertEquals(response.getAddition().getAdditionalNumber(), updated.getAddition().getAdditionalNumber(), "Дополнительное число не совпадают");
        softAssert.assertAll();
    }
}
