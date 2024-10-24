package petModuleTestCases;
import api.dataCreation.CreatePetModuleData;
import api.endpoints.PetEndPoints;
import api.utlilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddPetToStore_DataDrivenTest {
    ObjectMapper objectMapper;
    String finalPayLoad;
    Response response;
    @Test(priority=1, dataProvider = "petData", dataProviderClass = DataProviders.class)
    public void addPets(String id, String categoryId, String categoryName, String name, String photoUrl, String tagId, String tagName, String status) throws JsonProcessingException {
        finalPayLoad=CreatePetModuleData.createPetData(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        System.out.println(finalPayLoad);
        response=PetEndPoints.addNewPet(finalPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);
    }



}
