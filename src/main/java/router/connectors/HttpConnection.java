package router.connectors;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public interface HttpConnection {
    CompletableFuture<String> doGet(String url);
    int doPost(String url, HashMap<String, String> formData);
}
