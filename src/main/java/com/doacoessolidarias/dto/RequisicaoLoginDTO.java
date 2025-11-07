package com.doacoessolidarias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RequisicaoLoginDTO {

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
