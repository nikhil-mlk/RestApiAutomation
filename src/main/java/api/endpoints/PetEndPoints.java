package api.endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import static io.restassured.RestAssured.*;
public class PetEndPoints {
    private static Response response;
    private static Properties properties;
    private static BufferedReader reader;
    public static void loadPropertiesFile() {
        properties = new Properties();
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/routes.properties"));
            properties.load(reader);
            reader.close();
        } catch (Exception a) {
            System.out.println("Not able to locate properties file");
        }
    }
    public static Response addNewPet(String serializedPayload) {
        loadPropertiesFile();
        response=given()
                .headers("accept", "application/json").contentType(ContentType.JSON)
                .baseUri(properties.getProperty("postPetUrl"))
                .body(serializedPayload)
                .when()
                .post();
        return response;
    }
    public static Response getPetByStatus(String status)
    {
        response=given()
                .header("accept","application/json")
                .baseUri(properties.getProperty("getPetUrl"))
                .queryParam("status",status)
                .when()
                .get("/findByStatus");
        return response;
    }
    public static Response getPetIds(String petID )
    {
        int petIdInt = (int)Double.parseDouble(petID);
        loadPropertiesFile();
        response=given()
                .header("accept", "application/json")
                .pathParam("petId",petIdInt)
                .when()
                .get(properties.getProperty("getPetIdURL"));
        return response;
    }
    public static Response updatePet(String petID, String name, String status)
    {
        int petIdInt = (int)Double.parseDouble(petID);
        loadPropertiesFile();
        response=given()
                .header("accept","application/json")
                .header("Content-Type","application/x-www-form-urlencoded")
                .pathParam("petId",petIdInt)
                .formParam("name",name)
                .formParam("status",status)
                .when()
                .post(properties.getProperty("updatePetWithFormDataURL"));
        return response;
    }
    public static Response PetPostImage(String petID)
    {
        File uploadFile=new File("testData/RestAPI.jpg");
        int petIdInt = (int)Double.parseDouble(petID);
        response=given()
                .header("accept","application/json")
                .header("Content-Type","multipart/form-data")
                .pathParam("petId",petIdInt)
                .multiPart("file",uploadFile)
                .when()
                .post(properties.getProperty("postPetWithImageURL"));
        return response;
    }
}
