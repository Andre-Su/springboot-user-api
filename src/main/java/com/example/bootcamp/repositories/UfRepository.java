package com.example.bootcamp.repositories;

import com.example.bootcamp.models.UfVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UfRepository extends JpaRepository<UfVo, Long> {
    Optional<UfVo> findByNome(String nome);
    Optional<UfVo> findBySigla(String sigla);
    List<Optional<UfVo>> findByStatus(int status);
}
