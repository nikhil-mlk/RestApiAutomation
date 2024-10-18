package api.endpoints;
import api.payload.UserPayload;
import io.restassured.response.Response;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;
import static io.restassured.RestAssured.*;
public class UserEndPoints {
    static Response response=null;
    private static Properties properties;
    private static BufferedReader reader;
    public static void loadPropertiesFile()
    {
        properties = new Properties();
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/routes.properties"));
            properties.load(reader);
            reader.close();
        }
        catch(Exception a) {
            System.out.println("Not able to locate properties file");
        }
    }
    public static Response createUser(UserPayload payload) {
        loadPropertiesFile();
        response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .baseUri(properties.getProperty("postUrl"))
                .body(payload)
                .when()
                .post();
        return response;
    }
    public static Response getUser(String userName) {
        loadPropertiesFile();
        response = given()
                .header("accept", "application/json")
                .pathParam("username",userName)
                .when()
                .get(properties.getProperty("getUrl"));
        return response;
    }
    public static Response updateUser(String userName,UserPayload payload)
    {
        loadPropertiesFile();
        response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(properties.getProperty("putUrl"));
        return response;
    }
    public static Response deleteUser(String userName)
    {
        loadPropertiesFile();
        response = given()
                .header("accept", "application/json")
                .pathParam("username",userName)
                .when()
                .delete(properties.getProperty("deleteUrl"));
        return response;
    }
}
