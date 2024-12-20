package userModuleTestCases;
import api.endpoints.UserEndPoints;
import api.payloads.userPayLoad.UserPayload;
import api.utlilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Create_Delete_Users_DataDrivenTest {
    UserPayload userPayload;
    Response response;
    ObjectMapper objectMapper;
    String finalPayload;
    @Test(priority=1, dataProvider = "userData", dataProviderClass = DataProviders.class)
    public void createUsers(String userId, String Uname, String fName, String lName, String emailId, String pas, String ph) throws JsonProcessingException {
        userPayload=new UserPayload();
        userPayload.setId((Double.valueOf(userId)).intValue());
        System.out.println(userPayload.getId());
        userPayload.setUsername(Uname);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(emailId);
        userPayload.setPassword(pas);
        userPayload.setPhone(ph);
        objectMapper=new ObjectMapper();
        finalPayload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
        response=UserEndPoints.createUser(finalPayload);
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
