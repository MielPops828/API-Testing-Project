package test;

import dto.request.AdditionRequest;
import dto.request.EntityRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Epic("API-Test")
@Feature("Удаление сущности")
public class DeleteEntityTest extends BaseTest{
    @Test
    @Description("Тест удаления сущности по id")
    public void deleteEntityTest(){
        EntitySteps step = new EntitySteps(spec);
        EntityRequest request = EntityRequest.builder()
                .title("Delete Entity")
                .verified(true)
                .importantNumbers(java.util.List.of(10, 20))
                .addition(AdditionRequest.builder()
                        .additionalInfo("Temp")
                        .additionalNumber(100)
                        .build())
                .build();

        int entityId = step.initEntity(request);

        given()
                .spec(spec)
                .accept(ContentType.TEXT)
                .when()
                .delete("/delete/{id}", entityId)
                .then()
                .statusCode(204);
    }
}
