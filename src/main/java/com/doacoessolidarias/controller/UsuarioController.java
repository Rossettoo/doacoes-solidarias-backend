package com.doacoessolidarias.controller;

import com.doacoessolidarias.domain.usuario.Usuario;
import com.doacoessolidarias.domain.doacao.Doacao;
import com.doacoessolidarias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
    }

    @GetMapping("/{id}/doacoes")
    public List<Doacao> listarDoacoesPorUsuario(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id)
                .map(Usuario::getDoacoes)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }
}
