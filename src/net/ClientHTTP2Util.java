package net;

import okhttp3.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Properties;

public final class ClientHTTP2Util {

    public ClientHTTP2Util() {
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

    public static String sendJson() throws IOException, InterruptedException, URISyntaxException {
        String score;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                        "answer",
                        "answer.json",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("answer.json"))).build();
        Request request = new Request.Builder()
                .url("https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=" + getTokenForRequest())
                .method("POST", body).build();
        try {
            Response response = client.newCall(request).execute();
            score = response.body().string();
            System.out.println(response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return score;
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
        return prop.getProperty("token");
    }
}
