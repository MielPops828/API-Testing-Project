package test;

import dto.request.EntityRequest;
import dto.response.EntityResponse;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class EntitySteps extends BaseTest{

    private RequestSpecification spec;

    @Step("Создание сущности")
    public int initEntity(EntityRequest request){
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
        return Integer.parseInt(idString.trim());
    }

    @Step("Получение сущности по ID")
    public EntityResponse getEntityById(int id) {
        return given()
                .spec(spec)
                .get("/get/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class);
    }
}
