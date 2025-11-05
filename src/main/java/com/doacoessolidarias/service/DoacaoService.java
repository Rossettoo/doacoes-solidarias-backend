package com.doacoessolidarias.service;

import com.doacoessolidarias.domain.doacao.Doacao;
import com.doacoessolidarias.domain.doacao.DoacaoDTO;
import com.doacoessolidarias.domain.usuario.Usuario;
import com.doacoessolidarias.repository.DoacaoRepository;
import com.doacoessolidarias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Criar e salvar doação usando DTO
    public Doacao salvarDoacao(DoacaoDTO dto) {
        Doacao doacao = new Doacao();

        // Preenche os campos da doação
        doacao.setTitulo(dto.getTitulo());
        doacao.setDescricao(dto.getDescricao());
        doacao.setCategoria(dto.getCategoria());

        // Busca o usuário (doador) pelo ID e associa à doação
        if (dto.getDoador() != null) {
            Optional<Usuario> doador = usuarioRepository.findById(dto.getDoador());
            doador.ifPresent(doacao::setDoador);
        }

        return doacaoRepository.save(doacao);
    }

    // Listar todas as doações
    public List<Doacao> listarDoacoes() {
        return doacaoRepository.findAll();
    }

    // Buscar doação por ID
    public Optional<Doacao> buscarPorId(UUID id) {
        return doacaoRepository.findById(id);
    }

    // Deletar doação
    public void deletarDoacao(UUID id) {
        doacaoRepository.deleteById(id);
    }
}
