package com.example.bootcamp.repositories;

import com.example.bootcamp.models.UfVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UfRepository extends JpaRepository<UfVo, Long> {
}
