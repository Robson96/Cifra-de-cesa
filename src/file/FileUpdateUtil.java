package file;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public final class FileUpdateUtil {

    private FileUpdateUtil() {}

    public static void updateFile(String dec, String resume, int n) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("answer.json");
        Json json = gson.fromJson(reader, Json.class);
        json.setDecifrado(dec);
        json.setNumeroDeCasas(n);
        json.setResumoCriptografico(resume);
        String s = gson.toJson(json);
        PrintStream printStream = new PrintStream("answer.json");
        printStream.write(s.getBytes("UTF-8"));
        printStream.close();
    }
}