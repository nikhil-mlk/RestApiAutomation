package userModuleTestCases;
import api.dataCreation.CreateUserModuleData;
import api.endpoints.UserEndPoints;
import api.utlilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
public class TC_User_01_CreateAndDeleteUsers {
    Response response;
    String finalPayLoad;
    @org.testng.annotations.Test(priority=1, dataProvider = "userData", dataProviderClass = DataProviders.class)
    public void createUsers(String id, String userName, String firstName, String lastName, String email, String password, String phone)
    {
        finalPayLoad=CreateUserModuleData.createUserdata(id,userName,firstName,lastName,email,password,phone);
        response=UserEndPoints.createUser(finalPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @org.testng.annotations.Test(priority=2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
    public void deleteUsers(String userName)
    {
        response=UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.statusCode(),200);
    }
}
