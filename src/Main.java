import crypt.CesaCrypt;
import http.ClientHTTP2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        CesaCrypt cesaCrypt = CesaCrypt.getInstance();

        String encrypt = cesaCrypt.encrypt("abcdefghijlmnopqrstuvxzywk", 3);
        String decrypt = cesaCrypt.decrypt(encrypt, 13);
        System.out.println(decrypt);
    }
}
