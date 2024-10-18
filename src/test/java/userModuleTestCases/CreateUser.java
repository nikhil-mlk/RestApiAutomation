package userModuleTestCases;
import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utlilities.ReadExcel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class CreateUser {
    Faker faker;
    UserPayload userPayload;
    Response response;
    String user=null;
    JsonPath jsonPath;
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
    }
    @Test
    public void createFirstUser()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        response=userEndPoints.createUser(userPayload);
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
        System.out.println(response.asPrettyString());
        jsonPath=response.jsonPath();
        String userNameInResponse=jsonPath.get("username");
        Assert.assertEquals(userNameInResponse,user);
    }
    @Test(dependsOnMethods = {"getUser"})
    public void updateUser()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        userPayload.setEmail("helloTesters@gmail.com");
        userEndPoints.updateUser(user,userPayload);
    }
    @Test(dependsOnMethods = {"updateUser"})
    public void getUserAgainAfterUpdation()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        response=userEndPoints.getUser(user);
        System.out.println(response.asPrettyString());
    }
    @Test(dependsOnMethods = {"getUserAgainAfterUpdation"})
    public void deleteuser()
    {
        UserEndPoints userEndPoints=new UserEndPoints();
        response=userEndPoints.deleteUser(user);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.statusCode(),200);
    }

}
