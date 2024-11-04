package userModuleTestCases;
import api.dataCreation.CreateUserModuleData;
import api.endpoints.UserEndPoints;
import api.payloads.userPayLoad.UserPayload;
import api.utlilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
public class Test {
    Response response;
    String finalPayLoad;
    UserPayload userPayload;
    @org.testng.annotations.Test(priority=1, dataProvider = "userData", dataProviderClass = DataProviders.class)
    public void createUsers(String id, String userName, String firstName, String lastName, String email, String password, String phone)
    {
        finalPayLoad=CreateUserModuleData.createUserdata(id,userName,firstName,lastName,email,password,phone);
        System.out.println(finalPayLoad);
        response=UserEndPoints.createUser(finalPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @org.testng.annotations.Test(priority=2)
    public void deserializeFinalPayLoad()
    {
        userPayload=CreateUserModuleData.deseralizeData(finalPayLoad);
    }

}
