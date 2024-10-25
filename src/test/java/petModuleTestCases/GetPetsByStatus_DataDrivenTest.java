package petModuleTestCases;
import api.dataCreation.CreatePetModuleData;
import api.endpoints.PetEndPoints;
import api.utlilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasItem;
public class GetPetsByStatus_DataDrivenTest {
    String finalPayLoad;
    Response response;
    @Test(priority=1, dataProvider = "petData", dataProviderClass = DataProviders.class)
    public void addPetsAndValidateResponse(String id, String categoryId, String categoryName, String name, String photoUrl, String tagId, String tagName, String status) throws JsonProcessingException {
        finalPayLoad=CreatePetModuleData.createPetData(id, categoryId, categoryName, name, photoUrl, tagId, tagName, status);
        response=PetEndPoints.addNewPet(finalPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=2)
    public void getAvailablePetsAndValidateStatusCode()
    {
        response=PetEndPoints.getPetByStatus("available");
        response.then()
                .statusCode(200)
                .body("id",hasItem(101));
    }
    @Test(priority=3)
    public void getPendingPetsAndValidateStatusCode()
    {
        response=PetEndPoints.getPetByStatus("pending");
        response.then()
                .statusCode(200)
                .body("id",hasItem(102));
    }
    @Test(priority=4)
    public void getSoldPetsAndValidateStatusCode()
    {
        response=PetEndPoints.getPetByStatus("sold");
        response.then()
                .statusCode(200)
                .body("id",hasItem(103));
    }
    @Test(priority=5)
    public void validateResponseHeaders()
    {
        response.then()
                .header("Content-Type","application/json")
                .header("Transfer-Encoding","chunked");
    }
}
