package userModuleTestCases;
import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utlilities.DataProviders;
import api.utlilities.ReadExcel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class CreateUser_DataDrivenTesting {
    UserPayload userPayload;
    Response response;
    @Test(priority=1, dataProvider = "userData", dataProviderClass = DataProviders.class)
    public void createUserdata(String userId, String Uname, String fName, String lName, String emailId, String pas, String ph){
        userPayload=new UserPayload();
        userPayload.setId((Double.valueOf(userId)).intValue());
        System.out.println(userPayload.getId());
        userPayload.setUsername(Uname);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(emailId);
        userPayload.setPassword(pas);
        userPayload.setPhone(ph);
        response=UserEndPoints.createUser(userPayload);
        response.then().log().all().toString();
        System.out.println(response);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
    public void getUserNames(String userName)
    {
        response=UserEndPoints.getUser(userName);
        System.out.println(response.asPrettyString());
    }
    @Test(priority=3, dataProvider = "userNames", dataProviderClass = DataProviders.class)
    public void deleteUsers(String userName)
    {
        response=UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.statusCode(),200);
    }
}
