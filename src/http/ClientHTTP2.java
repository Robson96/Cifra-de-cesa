package http;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Properties;

public final class ClientHTTP2 {
    public static void getJsonFromURL(URI uri) throws IOException, InterruptedException {
        System.out.println(uri.resolve(getToken()));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri.resolve(getToken()))
                .GET()
                .build();

        //pode ser assicrono tbm com o sendAnsyc()
        client.send(request, HttpResponse
                .BodyHandlers
                .ofFile(Paths.get("answer.json")));
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
}
