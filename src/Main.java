import crypt.CesaCrypt;
import crypt.CifraCesar;

public class Main {
    public static void main(String[] args) {
        CesaCrypt cesaCrypt = CesaCrypt.getInstance();

        String encrypt = cesaCrypt.encrypt("abcdefghijlmnopqrstuvxzywk", 3);
        String decrypt = cesaCrypt.decrypt("D OLJHLUD UDSRVD PDUURP VDOWRX VREUH R FDFKRUUR FDQVDGR", 3);
        System.out.println(encrypt);
        System.out.println(decrypt);

        CifraCesar cifraCesar = new CifraCesar();
        String encriptar = cifraCesar.encriptar("a ligeira raposa marrom saltou sobre o cachorro cansado");
        System.out.println(encriptar);
        System.out.println(cifraCesar.desencriptar(encriptar));
    }
}
