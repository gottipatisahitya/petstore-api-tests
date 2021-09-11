package helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import configProviders.ConfigFileReader;
import models.PetModel;

public class PetApiHelper {
    public Response getPetById(Long petId) {
        return RestAssured.given()
                .contentType("application/json").log().all()
                .when()
                .get(ConfigFileReader.baseUrl + "v2/pet/" + petId)
                .then()
                .extract().response();

    }

    public Response createNewPet(PetModel createPetBody) {
        return RestAssured.given()
                .contentType("application/json").log().all()
                .when()
                .body(createPetBody)
                .post(ConfigFileReader.baseUrl + "v2/pet")
                .then()
                .extract().response();
    }

    public Response updateExistingPet(PetModel updatePetBody) {
        return RestAssured.given()
                .contentType("application/json").log().all()
                .when()
                .body(updatePetBody)
                .put(ConfigFileReader.baseUrl + "v2/pet")
                .then()
                .extract().response();
    }
    public Response deletePet(Long petId) {
        return RestAssured.given()
                .contentType("application/json").log().all()
                .header("api_key","123")
                .when()
                .delete(ConfigFileReader.baseUrl + "v2/pet/"+ petId)
                .then()
                .extract().response();
    }
}
