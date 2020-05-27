package net;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class ClientHTTP2Util {

    private ClientHTTP2Util() {
    }

    public static void getJsonFromURL(URI uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri.resolve(getToken()))
                .GET()
                .build();

        //pode ser assicrono tbm com o sendAnsyc()
        client.send(request, HttpResponse
                .BodyHandlers
                .ofFile(Paths.get("answer.json")));
    }

    public static void sendJson(Path path) {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request =
                    HttpRequest.newBuilder(
                            new URI("https://api.codenation.dev/v1/challenge/dev-ps/").resolve(getTokenForRequest()))
                            .setHeader("Content-Type", "multipart/form-data;boundary=boundary;charset=utf8")
                            .setHeader("Content-Disposition", "form-data; name=\"answer\"; filename=\"answer.json\"")
                            .POST(HttpRequest.BodyPublishers.ofFile(path))
                            .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getToken() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("token.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder token = new StringBuilder();
        token.append("generate-data?token=" + prop.get("token"));
        return token.toString();
    }

    private static String getTokenForRequest() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("token.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder token = new StringBuilder();
        token.append("submit-solution?token=" + prop.get("token"));
        return token.toString();
    }
}
