package crypt;

import java.util.function.Function;

public class CifraCesar {

    private final int n;

    public CifraCesar() {
        this.n = 3;
    }

    public CifraCesar(int n) {
        this.n = n > 0 ? n : 3;
    }

    /**
     * En(x)=(x+n) mod 26
     *
     * @param mensagem
     * @return String com senha encriptada
     */
    public String encriptar(String mensagem) {
        return cifraCesar(mensagem, c -> (char) (c + n));
    }

    /**
     * En(x)=(x-n) mod 26
     *
     * @param mensagem
     * @return String com senha desencriptada
     */
    public String desencriptar(String mensagem) {
        return cifraCesar(mensagem, c -> (char) (c - n));
    }

    private String cifraCesar(String mensagem, Function<Character, Character> function) {
        char[] msg = mensagem.toCharArray();
        StringBuilder mensagemEncriptada = new StringBuilder();
        for (char c : msg) mensagemEncriptada.append((c != 32) ? function.apply(c) : " ");
        return mensagemEncriptada.toString();
    }

}
