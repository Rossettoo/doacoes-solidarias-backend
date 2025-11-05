package com.doacoessolidarias.service;

import com.doacoessolidarias.domain.usuario.Usuario;
import com.doacoessolidarias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(java.util.UUID id) {
        return usuarioRepository.findById(id);
    }

    public void deletarUsuario(java.util.UUID id) {
        usuarioRepository.deleteById(id);
    }
}
