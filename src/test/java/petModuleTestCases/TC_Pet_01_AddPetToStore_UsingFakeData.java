package petModuleTestCases;
import api.endpoints.PetEndPoints;
import api.payloads.petPayLoad.Category;
import api.payloads.petPayLoad.PetPayload;
import api.payloads.petPayLoad.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
public class TC_Pet_01_AddPetToStore_UsingFakeData {
    ObjectMapper objectMapper;
    String finalPayLoad;
    Response response;
    @Test
    public void createPetData() throws JsonProcessingException {
        PetPayload pet1=new PetPayload();
        pet1.setId(101);
        Category category=new Category();
        category.setId(101);
        category.setName("Bull Dogs");
        pet1.setCategory(category);
        pet1.setName("French Bull Dog");
        List<String> listOfPhotoUrls = new ArrayList<>();
        listOfPhotoUrls.add("http://photo1.com");
        listOfPhotoUrls.add("http://photo2.com");
        pet1.setPhotoUrls(listOfPhotoUrls);
        Tag tag1=new Tag();
        List<Tag> tagsList=new ArrayList<>();
        tag1.setId(101);
        tag1.setName("This is tag 1 for French Bull dog");
        Tag tag2=new Tag();
        tag2.setId(102);
        tag2.setName("This is tag 2 for French Bull dog");
        tagsList.add(tag1);
        tagsList.add(tag2);
        pet1.setTags(tagsList);
        pet1.setStatus("available");
        objectMapper=new ObjectMapper();
        finalPayLoad=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pet1);
        response=PetEndPoints.addNewPet(finalPayLoad);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.statusCode(),200);
    }
}
