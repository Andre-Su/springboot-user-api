package com.example.bootcamp.repositories;

import com.example.bootcamp.models.MunicipioVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<MunicipioVo, Long> {
}
