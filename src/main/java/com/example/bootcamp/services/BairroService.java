package com.example.bootcamp.services;

import com.example.bootcamp.models.BairroVo;
import com.example.bootcamp.repositories.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    public List<BairroVo> findAll() {
        return bairroRepository.findAll();
    }

    public Optional<BairroVo> findById(long id) {
        return bairroRepository.findById(id);
    }

    public BairroVo save(BairroVo bairroVo) {
        return bairroRepository.save(bairroVo);
    }

}
