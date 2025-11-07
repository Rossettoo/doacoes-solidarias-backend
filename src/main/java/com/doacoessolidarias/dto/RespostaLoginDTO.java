package com.doacoessolidarias.dto;

public class RespostaLoginDTO {

    private String mensagem;
    private String token;

    public RespostaLoginDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public RespostaLoginDTO(String mensagem, String token) {
        this.mensagem = mensagem;
        this.token = token;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
