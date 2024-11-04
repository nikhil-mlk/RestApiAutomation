package petModuleTestCases;
import api.dataCreation.CreatePetModuleData;
import api.endpoints.PetEndPoints;
import api.utlilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TC_Pet_05_PostPetWithUploadImage {
    String finalPayLoad;
    Response response;
    @Test(priority=1, dataProvider = "petData", dataProviderClass = DataProviders.class)
    public void addPetsAndValidateResponse(String id, String categoryId, String categoryName, String name, String photoUrl, String tagId, String tagName, String status) throws JsonProcessingException {
        finalPayLoad=CreatePetModuleData.createPetData(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        PetEndPoints.addNewPet(finalPayLoad);
        response=PetEndPoints.PetPostImage(id);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=2)
    public void validateResponseHeaders()
    {
        response.then()
                .header("content-type","application/json")
                .header("access-control-allow-origin","*");
    }
}
