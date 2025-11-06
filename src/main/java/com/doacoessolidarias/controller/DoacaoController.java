package com.doacoessolidarias.controller;

import com.doacoessolidarias.domain.doacao.Doacao;
import com.doacoessolidarias.domain.doacao.DoacaoDTO;
import com.doacoessolidarias.domain.doacao.StatusDoacao;
import com.doacoessolidarias.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
@CrossOrigin(origins = "*")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    // Criar nova doação
    @PostMapping
    public Doacao criarDoacao(@RequestBody DoacaoDTO dto) {
        return doacaoService.criarDoacao(dto);
    }

    // Listar todas
    @GetMapping
    public List<Doacao> listarDoacoes() {
        return doacaoService.listarDoacoes();
    }

    // Listar por ID do doador
    @GetMapping("/doador/{doadorId}")
    public List<Doacao> listarPorDoador(@PathVariable Integer doadorId) {
        return doacaoService.listarPorDoador(doadorId);
    }

    // Atualizar status da doação
    @PatchMapping("/{id}/status")
    public Doacao atualizarStatus(@PathVariable Integer id, @RequestParam StatusDoacao status) {
        return doacaoService.atualizarStatus(id, status);
    }

    // Deletar doação
    @DeleteMapping("/{id}")
    public void deletarDoacao(@PathVariable Integer id) {
        doacaoService.deletarDoacao(id);
    }
}
