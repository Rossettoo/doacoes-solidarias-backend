package com.doacoessolidarias.security;

import com.doacoessolidarias.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioSecurityService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioSecurityService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var u = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        // Se tiver enum de papel: new SimpleGrantedAuthority("ROLE_" + u.getTipoUsuario().name());

        return new org.springframework.security.core.userdetails.User(
                u.getEmail(),
                u.getSenhaHash(),
                authorities
        );
    }
}
