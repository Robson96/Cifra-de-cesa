import crypt.CesaCrypt;
import http.ClientHTTP2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        CesaCrypt cesaCrypt = CesaCrypt.getInstance();
        String decrypt =
                cesaCrypt.decrypt(
                        "tq jzf lcp rztyr esczfrs spww, vppa rztyr. fyvyzhy"
                        , 11);
        System.out.println(decrypt);



    }
}
