package com.example.bootcamp.repositories;

import com.example.bootcamp.models.BairroVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BairroRepository extends JpaRepository<BairroVo, Long> {
    Optional<BairroVo> findByNome(String nome);
}
