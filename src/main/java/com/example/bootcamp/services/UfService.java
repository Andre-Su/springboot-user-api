package com.example.bootcamp.services;

import com.example.bootcamp.dtos.UfDto;
import com.example.bootcamp.models.UfVo;
import com.example.bootcamp.repositories.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UfService {

    @Autowired
    private UfRepository ufRepository;

    public List<UfVo> findAll() {
        return ufRepository.findAll();
    }

    public Optional<UfVo> findById(long id) {
        return ufRepository.findById(id);
    }

    public UfVo save(UfDto ufDto) {
        UfVo ufVo = new UfVo();
        ufVo.setSigla(ufDto.sigla());
        ufVo.setNome(ufDto.nome());
        ufVo.setStatus(ufDto.status());
        return ufRepository.save(ufVo);
    }

    public Optional<UfVo> update(long id, UfVo ufVo) {
        return ufRepository.findById(id).map(existingUf -> {
            existingUf.setSigla(ufVo.getSigla());
            existingUf.setNome(ufVo.getNome());
            existingUf.setStatus(ufVo.getStatus());
            return ufRepository.save(existingUf);
        });
    }

    public boolean delete(long id) {
        return ufRepository.findById(id).map(uf -> {
            ufRepository.delete(uf);
            return true;
        }).orElse(false);
    }
}