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

    public boolean existsById(long id){
        return ufRepository.existsById(id);
    }

    public List<UfVo> findAll() {
        return ufRepository.findAll();
    }

    public Optional<UfVo> findById(long id) {
        return ufRepository.findById(id);
    }

    public Optional<UfVo> findBySigla(String sigla) {
        return ufRepository.findBySigla(sigla);
    }

    public Optional<UfVo> findByNome(String nome) {
        return ufRepository.findByNome(nome);
    }

    public List<Optional<UfVo>> findByStatus(int status) {
        return ufRepository.findByStatus(status);
    }

    public UfVo save(UfDto ufDto) {
        UfVo ufVo = new UfVo();
        ufVo.setSigla(ufDto.sigla());
        ufVo.setNome(ufDto.nome());
        ufVo.setStatus(ufDto.status());
        return ufRepository.save(ufVo);
    }

    public Optional<UfVo> update(long id, UfDto ufDto) {
        return ufRepository.findById(id).map(existingUf -> {
            existingUf.setSigla(ufDto.sigla());
            existingUf.setNome(ufDto.nome());
            existingUf.setStatus(ufDto.status());
            return ufRepository.save(existingUf);
        });
    }

    public void deleteById(long id) {
        UfVo ufVo = ufRepository.findById(id).get();
        ufVo.setStatus(2);
        ufRepository.save(ufVo);
    }
}