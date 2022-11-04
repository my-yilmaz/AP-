package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {
    public Map<String, String> expectedDataKey(String name, String email, String gender, String status) {
        Map<String, String> expectedDataKey = new HashMap<>();
        expectedDataKey.put("name", name);
        expectedDataKey.put("email", email);
        expectedDataKey.put("gender", gender);
        expectedDataKey.put("status", status);
        return expectedDataKey;
    }

    public Map<String, Object> expectedData(Object meta, String data) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("meta", meta);
        expectedData.put("data", data);
        return expectedData;
    }
}
