package com.doacoessolidarias.controller;

import com.doacoessolidarias.dto.RequisicaoLoginDTO;
import com.doacoessolidarias.dto.RespostaLoginDTO;
import com.doacoessolidarias.security.JwtUtil;
import com.doacoessolidarias.domain.usuario.Usuario;
import com.doacoessolidarias.domain.usuario.TipoUsuario;
import com.doacoessolidarias.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager autenticador;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository userRepo;
    private final PasswordEncoder encoder;

    public AutenticacaoController(AuthenticationManager autenticador,
                                  JwtUtil jwtUtil,
                                  UsuarioRepository userRepo,
                                  PasswordEncoder encoder) {
        this.autenticador = autenticador;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RequisicaoLoginDTO dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body("Email já cadastrado");
        }
        Usuario u = new Usuario();
        u.setEmail(dto.getEmail());
        u.setSenhaHash(encoder.encode(dto.getSenha()));
        u.setTipoUsuario(TipoUsuario.DOADOR);
        userRepo.save(u);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaLoginDTO> login(@RequestBody @Validated RequisicaoLoginDTO dto) {
        try {
            Authentication auth = autenticador.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
            );
            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new RespostaLoginDTO("Autenticado com sucesso", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(new RespostaLoginDTO("Credenciais inválidas", null));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal UserDetails user) {
        if (user == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(user.getUsername());
    }
}
