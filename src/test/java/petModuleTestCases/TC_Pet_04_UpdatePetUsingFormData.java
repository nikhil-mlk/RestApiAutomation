package petModuleTestCases;
import api.dataCreation.CreatePetModuleData;
import api.endpoints.PetEndPoints;
import api.utlilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
public class TC_Pet_04_UpdatePetUsingFormData {
    String finalPayLoad;
    Response response;
    Faker faker;
    @Test(priority=1, dataProvider = "petData", dataProviderClass = DataProviders.class)
    public void addPetsAndValidateResponse(String id, String categoryId, String categoryName, String name, String photoUrl, String tagId, String tagName, String status) throws JsonProcessingException {
        finalPayLoad=CreatePetModuleData.createPetData(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        response=PetEndPoints.addNewPet(finalPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=2)
    public void updatePet_ValidPetId()
    {
        PetEndPoints.updatePet("102","American Bully","available");
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=3)
    public void updatePet_InvalidPetId()
    {
        faker=new Faker();
        String fakeId=faker.number().digits(4);
        response=PetEndPoints.updatePet(fakeId,"American Bully","available");
        response.then()
                .body("code",equalToObject(404));
    }
}
