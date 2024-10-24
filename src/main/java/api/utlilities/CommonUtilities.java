package api.utlilities;
import java.util.Arrays;
import java.util.List;
public class CommonUtilities {
    public static List<String> breakStringAndConvertToList(String str)
    {
        String[] stringArray=str.split(",");
        List<String> list=Arrays.asList(stringArray);
        return list;
    }
}
