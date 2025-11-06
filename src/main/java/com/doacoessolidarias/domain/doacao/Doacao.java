package com.doacoessolidarias.domain.doacao;

import com.doacoessolidarias.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // agora SERIAL (auto incremento)
    private Integer id;

    private String titulo;
    private String descricao;
    private String categoria;

    @Enumerated(EnumType.STRING)
    private StatusDoacao status = StatusDoacao.DISPONIVEL;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "doador_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Usuario doador;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public StatusDoacao getStatus() { return status; }
    public void setStatus(StatusDoacao status) { this.status = status; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public Usuario getDoador() { return doador; }
    public void setDoador(Usuario doador) { this.doador = doador; }
}
