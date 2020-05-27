import crypt.CesaCrypt;
import file.FileUpdateUtil;
import net.ClientHTTP2Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        CesaCrypt cesaCrypt = CesaCrypt.getInstance();
        String decrypt =
                cesaCrypt.decrypt(
                        "tq jzf lcp rztyr esczfrs spww, vppa rztyr. fyvyzhy"
                        , 11);
        String s = FileUpdateUtil.updateFile(decrypt, cesaCrypt.getResumeSHA1(), 11);
        System.out.println(s);
        ClientHTTP2Util.sendJson(Paths.get("answer.json"));

    }
}
