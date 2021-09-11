package tests;

import io.restassured.response.Response;
import com.github.javafaker.Faker;
import models.*;
import helpers.PetApiHelper;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    public class PetTests extends TestBase {

        @DisplayName("Find pet by Id")
        @Test
        public void findPetbyID() throws InterruptedException {

            PetApiHelper petApiHelper = new PetApiHelper();

            //Creating a new random pet for every test to be independent and support parallel execution in CI
            PetModel createPetModel = createNewPet();

            // Waiting for the create process to complete as it is taking few seconds to create
            Thread.sleep(25000);

            //Fetching the Pet by Id created in the above step
            Response getPetResponse = petApiHelper.getPetById(createPetModel.id);
            assertEquals(getPetResponse.statusCode(), 200);
            PetModel getPetByIdResponse = getPetResponse.getBody().as(PetModel.class);
            assertEquals(getPetByIdResponse.id, createPetModel.id);
            assertEquals(getPetByIdResponse.name, createPetModel.name);
        }

        @DisplayName("Update pet name")
        @Test
        public void updatePetName() throws InterruptedException {

            PetApiHelper petApiHelper = new PetApiHelper();

            //Creating a new random pet for every test to be independent and support parallel execution in CI
            PetModel createPetModel = createNewPet();

            // Waiting for the create process to complete as it is taking few seconds to create
            Thread.sleep(25000);

            //Updating the name of the pet
            createPetModel.name = "Sugar";
            Response updateResponse = petApiHelper.updateExistingPet(createPetModel);
            assertEquals(updateResponse.statusCode(), 200);
            PetModel updatePetResponse = updateResponse.getBody().as(PetModel.class);
            assertEquals(updatePetResponse.id, createPetModel.id);
            assertEquals(updatePetResponse.name, "Sugar");
        }

        @DisplayName("Delete a pet")
        @Test
        public void deletePet() throws InterruptedException {

            PetApiHelper petApiHelper = new PetApiHelper();

            //Creating a new random pet for every test to be independent and support parallel execution in CI
            PetModel createPetModel = createNewPet();

            // Waiting for the create process to complete as it is taking few seconds to create
            Thread.sleep(25000);

            //Updating the name of the pet
            Response deleteResponse = petApiHelper.deletePet(createPetModel.id);
            assertEquals(deleteResponse.statusCode(), 200);
        }


        private PetModel createNewPet () {

            Faker faker = new Faker();
            PetApiHelper petApiHelper = new PetApiHelper();
            PetModel createPetModel = new PetModel();
            Category petCategory = new Category();

            List<String> photoUrls = new ArrayList<>();
            PetTags petTag = new PetTags();
            List<PetTags> petTags = new ArrayList<>();

            createPetModel.id = faker.number().numberBetween(1001, 3000);
            petCategory.id = faker.number().numberBetween(10, 100);
            petCategory.name = faker.animal().name();
            createPetModel.name = faker.funnyName().name();
            createPetModel.category = petCategory;
            photoUrls.add("http://petphoto.com");
            createPetModel.photoUrls = photoUrls;

            petTag.id = faker.number().numberBetween(201, 300);
            petTag.name = "Tag 1";
            petTags.add(petTag);
            createPetModel.tags = petTags;
            createPetModel.status = "avaliable";

            Response createResponse = petApiHelper.createNewPet(createPetModel);
            assertEquals(createResponse.statusCode(), 200);

            return createPetModel;
        }

    }



