package com.example.bootcamp.repositories;

import com.example.bootcamp.models.PessoaVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaVo, Long> {
    Optional<PessoaVo> findByLogin(String login);
}
