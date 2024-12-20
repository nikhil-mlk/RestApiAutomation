package userModuleTestCases;
import api.endpoints.UserEndPoints;
import api.payloads.userPayLoad.UserPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class TC_User_02_CreateUpdateAndDeleteSingleUser_UsingFakeData {
    Faker faker;
    UserPayload userPayload;
    Response response;
    String user=null;
    JsonPath jsonPath;
    ObjectMapper objectMapper;
    String finalPayload;
    @BeforeClass
    public void createData() throws JsonProcessingException {
        faker=new Faker();
        userPayload=new UserPayload();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(4,5));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        objectMapper=new ObjectMapper();
        finalPayload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
    }
    @Test
    public void createFirstUser()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        response=userEndPoints.createUser(finalPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(dependsOnMethods = {"createFirstUser"})
    public void captureUserName()
    {
        user=userPayload.getUsername();
        System.out.println("User Name: "+user);
    }
    @Test(dependsOnMethods = {"captureUserName"})
    public void getUser()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        response=userEndPoints.getUser(user);
        jsonPath=response.jsonPath();
        String userNameInResponse=jsonPath.get("username");
        Assert.assertEquals(userNameInResponse,user);
    }
    @Test(dependsOnMethods = {"getUser"})
    public void updateUser()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        userPayload.setEmail("helloTesters@gmail.com");
        userEndPoints.updateUser(user,finalPayload);
    }
    @Test(dependsOnMethods = {"updateUser"})
    public void getUserAgainAfterUpdation()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        response=userEndPoints.getUser(user);
    }
    @Test(dependsOnMethods = {"getUserAgainAfterUpdation"})
    public void deleteuser()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        response=userEndPoints.deleteUser(user);
        Assert.assertEquals(response.statusCode(),200);
    }
}
