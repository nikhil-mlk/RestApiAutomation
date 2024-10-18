package api.utlilities;
import org.testng.annotations.DataProvider;
public class DataProviders {
    @DataProvider(name="userData")
    public Object[][] createUserDataProvider()
    {
        return ReadExcel.readUserDataFromExcelFile("TestData_UserModule","create user", 2);
    }
    @DataProvider(name="userNames")
    public Object[] getUserNamesProvider()
    {
        return ReadExcel.getUserNames("TestData_UserModule","create user");
    }
}
