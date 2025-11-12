package commons.helpers;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonHelper {
    public static final String USER_DATA_FILE_PATH = "src/test/resources/userData.json";


    public static  void  saveUserData(String email, String password){
        JSONArray userList;
        try {
            String content = Files.readString(Paths.get(USER_DATA_FILE_PATH));
            if (content.isEmpty()) {
                userList = new JSONArray();
            } else {
                userList = new JSONArray(content);
            }
        } catch (IOException e) {
            // Nếu file chưa tồn tại → tạo mới
            userList = new JSONArray();
        }

        JSONObject newUser = new JSONObject();
        newUser.put("Email",email);
        newUser.put("Password",password);
        userList.put(newUser);

        try (FileWriter file = new FileWriter(USER_DATA_FILE_PATH)) {
            file.write(userList.toString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private synchronized static Map<String, String> readDataFromJson(JsonNode jsonNode) {
        Map<String, String> result = new HashMap<>();
        for (JsonNode node : jsonNode) {
            Set<Map.Entry<String, JsonNode>> setNode = node.properties();
            if (setNode.isEmpty()) {
                for (Map.Entry<String, JsonNode> entry : jsonNode.properties()) {
                    JsonNode value = entry.getValue();
                    if (!value.isObject()) {
                        result.put(entry.getKey(), value.asText());
                    }
                }
            } else {
                for (Map.Entry<String, JsonNode> entry : setNode) {
                    JsonNode value = entry.getValue();
                    if (!value.isObject()) {
                        result.put(entry.getKey(), value.asText());
                    } else {
                        readDataFromJson(value);
                    }
                }
            }
        }
        return result;
    }

    }

