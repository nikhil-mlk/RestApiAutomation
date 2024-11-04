package api.endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;
import static io.restassured.RestAssured.*;
public class UserEndPoints {
    static Response response = null;
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
    public static Response createUser(String serializedPayload) {
        loadPropertiesFile();
        response = given()
                .headers("accept", "application/json").contentType(ContentType.JSON)
                .baseUri(properties.getProperty("postUrl"))
                .body(serializedPayload)
                .when()
                .post();
        return response;
    }
    public static Response getUser(String userName) {
        loadPropertiesFile();
        response = given()
                .header("accept", "application/json")
                .pathParam("username", userName)
                .when()
                .get(properties.getProperty("getUrl"));
        return response;
    }
    public static Response updateUser(String userName, String serializedPayload) {
        loadPropertiesFile();
        response = given()
                .headers("accept", "application/json").contentType(ContentType.JSON)
                .pathParam("username", userName)
                .body(serializedPayload)
                .when()
                .put(properties.getProperty("putUrl"));
        return response;
    }
    public static Response deleteUser(String userName) {
        loadPropertiesFile();
        response = given()
                .header("accept", "application/json")
                .pathParam("username", userName)
                .when()
                .delete(properties.getProperty("deleteUrl"));
        return response;
    }
}
