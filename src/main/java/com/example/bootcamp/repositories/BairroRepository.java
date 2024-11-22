package com.example.bootcamp.repositories;

import com.example.bootcamp.models.BairroVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<BairroVo, Long> {
}
