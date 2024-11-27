package com.example.bootcamp.repositories;

import com.example.bootcamp.models.PessoaVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaVo, Long> {
    Optional<PessoaVo> findByLogin(String login);
    List<Optional<PessoaVo>> findByStatus(int status);
}
