package api.utlilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
public class CommonUtilities {
    public static List<String> breakStringAndConvertToList(String str)
    {
        String[] stringArray=str.split(",");
        List<String> list=Arrays.asList(stringArray);
        return list;
    }
}
