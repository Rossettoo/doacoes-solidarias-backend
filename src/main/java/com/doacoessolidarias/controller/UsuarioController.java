package com.doacoessolidarias.controller;

import com.doacoessolidarias.domain.usuario.Usuario;
import com.doacoessolidarias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    // Listar todos os usuários
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
    }

    @GetMapping("/{id}/doacoes")
    public List<com.doacoessolidarias.domain.doacao.Doacao> listarDoacoesPorUsuario(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id)
                .map(com.doacoessolidarias.domain.usuario.Usuario::getDoacoes)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }
}
