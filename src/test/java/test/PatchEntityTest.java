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
@Feature("Обновление сущности и ее дополнение")
public class PatchEntityTest extends BaseTest {
    @Test
    @Description("Тест обновления сущности и ее дополнения")
    public void patchEntityTest(){
        EntityRequest request = EntityRequest.builder()
                .title("Patch Entity")
                .verified(true)
                .importantNumbers(java.util.List.of(1, 2))
                .addition(AdditionRequest.builder()
                        .additionalInfo("old info")
                        .additionalNumber(1)
                        .build())
                .build();

        String idString = given()
                .spec(spec)
                .accept(ContentType.TEXT)
                .body(request)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        int id = Integer.parseInt(idString.trim());

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
                .patch("/patch/{id}", id)
                .then()
                .statusCode(204);
    }
}
