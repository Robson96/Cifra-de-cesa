package file;

public class Json {
    private int numero_de_casas;
    private String token;
    private String cifrado;
    private String decifrado;
    private String resumo_criptografico;

    public int getNumeroDeCasas() {
        return numero_de_casas;
    }

    public void setNumeroDeCasas(int numeroDeCasas) {
        this.numero_de_casas = numeroDeCasas;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCifrado() {
        return cifrado;
    }

    public void setCifrado(String cifrado) {
        this.cifrado = cifrado;
    }

    public String getDecifrado() {
        return decifrado;
    }

    public void setDecifrado(String decifrado) {
        this.decifrado = decifrado;
    }

    public String getResumoCriptografico() {
        return resumo_criptografico;
    }

    public void setResumoCriptografico(String resumo_criptografico) {
        this.resumo_criptografico = resumo_criptografico;
    }

    @Override
    public String toString() {
        return "{" +
                "numero_de_casas=" + numero_de_casas +
                ", token='" + token + '\'' +
                ", cifrado='" + cifrado + '\'' +
                ", decifrado='" + decifrado + '\'' +
                ", resumo_criptografico='" + resumo_criptografico + '\'' +
                '}';
    }
}
