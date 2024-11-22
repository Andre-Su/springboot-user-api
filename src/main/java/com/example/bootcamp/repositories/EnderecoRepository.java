package com.example.bootcamp.repositories;

import com.example.bootcamp.models.EnderecoVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoVo, Long> {
}
