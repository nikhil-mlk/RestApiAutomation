package api.endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.BufferedReader;
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
        response = given()
                .headers("accept", "application/json").contentType(ContentType.JSON)
                .baseUri(properties.getProperty("postPetUrl"))
                .body(serializedPayload)
                .when()
                .post();
        return response;
    }


}
