package test;

import dto.request.AdditionRequest;
import dto.request.EntityRequest;
import dto.response.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("API-Test")
@Feature("Получить сущность")
public class GetEntityTest extends BaseTest{
    @Test
    @Description("Тест получения сущности по id")
    public void getEntityTest(){
        EntityRequest request = EntityRequest.builder()
                .title("Get Entity")
                .verified(true)
                .importantNumbers(java.util.List.of(42, 87, 15))
                .addition(AdditionRequest.builder()
                        .additionalInfo("Доп. данные")
                        .additionalNumber(123)
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

        EntityResponse response = given().spec(spec)
                .get("/get/{id}", id)
                .then()
                .statusCode(200)
                .assertThat()
                .extract().as(EntityResponse.class);
    }
}
