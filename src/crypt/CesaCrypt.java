package crypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class CesaCrypt {

    private static final Map<Character, Integer> map = new HashMap<>();
    private final StringBuilder resumeSHA1Decrypt = new StringBuilder();

    private CesaCrypt() {}

    public static CesaCrypt getInstance() {
        for (int n = 97, i = 0; n <= 122; n++, i++)
            map.put((char)n, i);
        return new CesaCrypt();
    }

    public String encrypt(final String msg, final int numeroDecasas) {
        validateParams(msg, numeroDecasas);
        StringBuilder builder = new StringBuilder();

        System.out.println(map);
        final char[] chars = msg.toLowerCase().toCharArray();
        for (int i = 0; i <= chars.length - 1; i++) {
            if ((!IfIsNumeber(chars[i]) && !IfIsDont(chars[i])) && !isSpace(chars[i])) {
                for (char c: map.keySet()) {
                    if (chars[i] == c) {
                        builder.append(getChar(Math.abs((getCodePoint(c) + numeroDecasas) % 26)));
                        break;
                    }
                }
            } else {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

    private int getCodePoint(char c) {
        int i = 0;
        for (Map.Entry<Character, Integer> ch: map.entrySet())
            if (c == ch.getKey()) {
                i = ch.getValue();
                break;
            }
        return i;
    }

    private char getChar(int n) {
        char c = 0;
        for (Map.Entry<Character, Integer> ch: map.entrySet())
            if (n == ch.getValue()) {
                c = ch.getKey();
                break;
            }
        return c;
    }

    private boolean IfIsNumeber(final int codePoint) {
        return (codePoint >= 48 && codePoint <= 57) ? true : false;
    }

    private boolean IfIsDont(final int codePoint) {
        return codePoint == 46 ? true : false;
    }

    public String decrypt(final String msgEncrypt, final int numeroDecasas) {
        validateParams(msgEncrypt, numeroDecasas);

        StringBuilder builder = new StringBuilder();
        final char[] chars = msgEncrypt.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ((!IfIsNumeber(chars[i]) && !IfIsDont(chars[i])) && !isSpace(chars[i])) {
                for (char c: map.keySet()) {
                    if (chars[i] == c) {
                        builder.append(getChar(Math.abs((getCodePoint(c) - numeroDecasas) % 26)));
                        break;
                    }
                }
            } else {
                builder.append(chars[i]);
            }
        }
        String dec = builder.toString().toLowerCase();
        createResumeSHA1(dec);
        return dec;
    }

    private boolean isVirgula(int i) {
        return i == 44 ? true: false;
    }

    private void validateParams(final String msg,final int n) {
        Objects.requireNonNull(msg, "A menssagem nao pode ser nulla!");
        if (msg.equals("") || n <= 1)
            throw new IllegalArgumentException("Argumentos invalidos!");
    }

    private void createResumeSHA1(final String msg) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            final byte[] bytes = digest.digest(msg.getBytes("UTF-8"));
            for (byte b : bytes) {
                resumeSHA1Decrypt.append(String.format("%02x", 0xFF & b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritimo nao encontrado!");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encode nao suportado");
        }
    }

    public String getResumeSHA1() {
        return resumeSHA1Decrypt.toString();
    }

    private boolean isSpace(final int codePoint) {
        return codePoint == 32 ? true : false;
    }
}
