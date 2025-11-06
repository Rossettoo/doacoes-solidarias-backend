package com.doacoessolidarias.service;

import com.doacoessolidarias.domain.doacao.Doacao;
import com.doacoessolidarias.domain.doacao.DoacaoDTO;
import com.doacoessolidarias.domain.doacao.StatusDoacao;
import com.doacoessolidarias.domain.usuario.Usuario;
import com.doacoessolidarias.repository.DoacaoRepository;
import com.doacoessolidarias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Doacao criarDoacao(DoacaoDTO dto) {
        Usuario doador = usuarioRepository.findById(dto.getDoadorId())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado com ID: " + dto.getDoadorId()));

        Doacao doacao = new Doacao();
        doacao.setTitulo(dto.getTitulo());
        doacao.setDescricao(dto.getDescricao());
        doacao.setCategoria(dto.getCategoria());
        doacao.setStatus(StatusDoacao.DISPONIVEL);
        doacao.setDoador(doador);

        return doacaoRepository.save(doacao);
    }

    // Listar todas as doações
    public List<Doacao> listarDoacoes() {
        return doacaoRepository.findAll();
    }

    // Buscar doações por doador
    public List<Doacao> listarPorDoador(Integer doadorId) {
        return doacaoRepository.findByDoadorId(doadorId);
    }

    // Atualizar status da doação
    public Doacao atualizarStatus(Integer id, StatusDoacao novoStatus) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada com ID: " + id));
        doacao.setStatus(novoStatus);
        return doacaoRepository.save(doacao);
    }

    // Deletar doação
    public void deletarDoacao(Integer id) {
        if (!doacaoRepository.existsById(id)) {
            throw new RuntimeException("Doação não encontrada com ID: " + id);
        }
        doacaoRepository.deleteById(id);
    }
}
