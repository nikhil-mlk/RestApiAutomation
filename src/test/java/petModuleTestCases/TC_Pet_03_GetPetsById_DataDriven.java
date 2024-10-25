package petModuleTestCases;
import api.dataCreation.CreatePetModuleData;
import api.endpoints.PetEndPoints;
import api.utlilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItem;
public class TC_Pet_03_GetPetsById_DataDriven {
    String finalPayLoad;
    Response response;
    Faker faker;
    @Test(priority=1, dataProvider = "petData", dataProviderClass = DataProviders.class)
    public void addPetsAndValidateResponse(String id, String categoryId, String categoryName, String name, String photoUrl, String tagId, String tagName, String status) throws JsonProcessingException {
        finalPayLoad=CreatePetModuleData.createPetData(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        response=PetEndPoints.addNewPet(finalPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=2, dataProvider = "petIds", dataProviderClass = DataProviders.class)
    public void getPetsUsingIds(String id)
    {
        response=PetEndPoints.getPetIds(id);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=3)
    public void invalidPetId()
    {
        faker=new Faker();
        String fakeId=faker.number().digits(6);
        response=PetEndPoints.getPetIds(fakeId);
        Assert.assertEquals(response.getStatusCode(),404);
    }







}
