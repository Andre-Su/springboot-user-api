package com.example.bootcamp.repositories;

import com.example.bootcamp.models.MunicipioVo;
import com.example.bootcamp.models.UfVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<MunicipioVo, Long> {
    Optional<MunicipioVo> findByNome(String nome);
    Optional<MunicipioVo> findByUf(UfVo ufVo);
}
