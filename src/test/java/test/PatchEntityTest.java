package test;

import dto.request.AdditionRequest;
import dto.request.EntityRequest;
import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
@Epic("API-Test")
@Feature("Обновление сущности и ее дополнение")
public class PatchEntityTest extends BaseTest {
    @Test
    @Description("Тест обновления сущности и ее дополнения")
    public void createEntityTest(){
        List<EntityResponse> entitiesList = given(spec)
                .get("/getAll")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("entity", EntityResponse.class);

        int entityId = entitiesList.get(2).getId();

        AdditionRequest addition = AdditionRequest.builder()
                .additionalInfo("Обновленные сведения")
                .additionalNumber(987)
                .build();

        EntityRequest request = EntityRequest.builder()
                .title("Обновленный заголовок сущности")
                .verified(true)
                .importantNumbers(Arrays.asList(15, 15, 51))
                .addition(addition)
                .build();

        Response response = given(spec)
                .body(request)
                .patch("/patch/" + entityId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .extract()
                .response();
    }
}
