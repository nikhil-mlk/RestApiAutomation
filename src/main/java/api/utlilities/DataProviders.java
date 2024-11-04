package api.utlilities;
import org.testng.annotations.DataProvider;
public class DataProviders {
    @DataProvider(name="userData")
    public Object[][] createUserDataProvider()
    {
        return ReadExcel.readDataFromExcelFile("TestData","create user", 2);
    }
    @DataProvider(name="userNames")
    public Object[] getUserNamesProvider()
    {
        return ReadExcel.getUserNames("TestData","create user");
    }
    @DataProvider(name="petData")
    public Object[] addPetProvider()
    {
        return ReadExcel.readDataFromExcelFile("TestData","add pet",2);
    }
    @DataProvider(name="petIds")
    public Object[] getPetIdsDataProvider()
    {
        return ReadExcel.getPetIds("TestData","add pet");
    }
}
