package api.dataCreation;
import api.payloads.userPayLoad.UserPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class CreateUserModuleData {
    private static UserPayload userPayload;
    private static ObjectMapper objectMapper;
    private static String finalPayLoad;

    public static String createUserdata(String userId, String uName, String fName, String lName, String emailId, String pas, String ph) {
        try {
            userPayload = new UserPayload();
            userPayload.setId((Double.valueOf(userId)).intValue());
            userPayload.setUsername(uName);
            userPayload.setFirstName(fName);
            userPayload.setLastName(lName);
            userPayload.setEmail(emailId);
            userPayload.setPassword(pas);
            userPayload.setPhone(ph);
            objectMapper = new ObjectMapper();
            finalPayLoad = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }
        return finalPayLoad;
    }
    public static UserPayload deseralizeData(String serializedPayload){
        try
        {
            userPayload=objectMapper.readValue(serializedPayload,UserPayload.class);
            System.out.println( userPayload.getId());

          /*  userPayload.getId();
            userPayload.getUsername();
            userPayload.getFirstName();
            userPayload.getLastName();
            userPayload.getEmail();
            userPayload.getPassword();
            userPayload.getPhone();*/
        }
        catch(JsonProcessingException e)
        {
            System.err.println(e);
        }
        return userPayload;
    }


}



