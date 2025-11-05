package com.doacoessolidarias.repository;

import com.doacoessolidarias.domain.doacao.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DoacaoRepository extends JpaRepository<Doacao, UUID> {
}
