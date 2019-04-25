package router.connectors;

import java.util.HashMap;

public interface HttpConnection {
    String doGet(String url);
    int doPost(String url, HashMap<String, String> formData);
}
