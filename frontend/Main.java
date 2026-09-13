import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        try {

            // Input that we want to send to Python
            String input = "artificial intelligence";

            // JSON request body
            String json = "{\"input\":\"" + input + "\"}";

            // Create HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Create POST request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:8000/information"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            json,
                            StandardCharsets.UTF_8
                    ))
                    .build();

            // Send request
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Print Python's response
            System.out.println("Response from Python:");
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}