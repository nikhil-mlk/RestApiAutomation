package api.dataCreation;
import api.endpoints.PetEndPoints;
import api.payloads.petPayLoad.Category;
import api.payloads.petPayLoad.PetPayload;
import api.payloads.petPayLoad.Tag;
import api.utlilities.CommonUtilities;
import api.utlilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CreatePetModuleData {
    private static Response response;
    private static ObjectMapper objectMapper;
    private static String finalPayLoad;
    public static String createPetData(String id, String categoryId, String categoryName, String name, String photoUrl, String tagId, String tagName, String status) throws JsonProcessingException
    {
        PetPayload payload=new PetPayload();
        payload.setId((Double.valueOf(id)).intValue());
        //----- Category -----
        Category category=new Category();
        category.setId((Double.valueOf(categoryId)).intValue());
        category.setName(categoryName);
        payload.setCategory(category);
        //--------------
        payload.setName(name);
        //----- Photo URL -----
        List<String> listOfPhotoUrl=CommonUtilities.breakStringAndConvertToList(photoUrl);
        payload.setPhotoUrls(listOfPhotoUrl);
        //----------- Tag ------------
        Tag tag=new Tag();
        List<Tag> listOfTags=new ArrayList<>();
        tag.setId((Double.valueOf(tagId)).intValue());
        tag.setName(tagName);
        listOfTags.add(tag);
        payload.setTags(listOfTags);
        //---------
        payload.setStatus(status);
        objectMapper=new ObjectMapper();
        finalPayLoad=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
        return finalPayLoad;
    }

}

