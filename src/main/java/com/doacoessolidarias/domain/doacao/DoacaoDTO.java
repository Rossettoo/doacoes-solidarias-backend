package com.doacoessolidarias.domain.doacao;

public class DoacaoDTO {

    private String titulo;
    private String descricao;
    private String categoria;
    private Integer doadorId;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getDoadorId() { return doadorId; }
    public void setDoadorId(Integer doadorId) { this.doadorId = doadorId; }
}
