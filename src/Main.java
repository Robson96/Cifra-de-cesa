import crypt.CesaCrypt;
import file.FileUpdateUtil;
import net.ClientHTTP2Util;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        CesaCrypt cesaCrypt = CesaCrypt.getInstance();
        String decrypt =
                cesaCrypt.decrypt(
                        "tq jzf lcp rztyr esczfrs spww, vppa rztyr. fyvyzhy"
                        , 11);
        FileUpdateUtil.updateFile(decrypt, cesaCrypt.getResumeSHA1(), 11);
        String score = ClientHTTP2Util.sendJson();
        System.out.println(score);

    }
}
