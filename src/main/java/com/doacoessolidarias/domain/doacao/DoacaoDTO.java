package com.doacoessolidarias.domain.doacao;

import java.util.UUID;

public class DoacaoDTO {
    private String titulo;
    private String descricao;
    private String categoria;
    private UUID doador;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public UUID getDoador() { return doador; }
    public void setDoador(UUID doador) { this.doador = doador; }
}
