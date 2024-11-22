package com.example.bootcamp.repositories;

import com.example.bootcamp.models.PessoaVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaVo, Long> {
}
