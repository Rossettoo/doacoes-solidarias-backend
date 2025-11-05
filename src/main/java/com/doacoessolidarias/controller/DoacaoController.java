package com.doacoessolidarias.controller;

import com.doacoessolidarias.domain.doacao.Doacao;
import com.doacoessolidarias.domain.doacao.DoacaoDTO;
import com.doacoessolidarias.domain.usuario.Usuario;
import com.doacoessolidarias.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    // Criar doação (recebe DTO com id do doador)
    @PostMapping
    public Doacao criarDoacao(@RequestBody DoacaoDTO dto) {
        return doacaoService.salvarDoacao(dto);
    }

    // Listar todas as doações
    @GetMapping
    public List<Doacao> listarDoacoes() {
        return doacaoService.listarDoacoes();
    }

    // Buscar doação por ID
    @GetMapping("/{id}")
    public Optional<Doacao> buscarPorId(@PathVariable UUID id) {
        return doacaoService.buscarPorId(id);
    }

    // Deletar doação
    @DeleteMapping("/{id}")
    public void deletarDoacao(@PathVariable UUID id) {
        doacaoService.deletarDoacao(id);
    }

    // Buscar o doador de uma doação específica
    @GetMapping("/{id}/doador")
    public Usuario buscarDoadorPorDoacao(@PathVariable UUID id) {
        return doacaoService.buscarPorId(id)
                .map(Doacao::getDoador)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada."));
    }
}
