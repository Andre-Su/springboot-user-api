package com.example.bootcamp.repositories;

import com.example.bootcamp.models.MunicipioVo;
import com.example.bootcamp.models.UfVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<MunicipioVo, Long> {
    Optional<MunicipioVo> findByNome(String nome);
    List<Optional<MunicipioVo>> findByUf(UfVo ufVo);
    List<Optional<MunicipioVo>> findByStatus(int status);
}
