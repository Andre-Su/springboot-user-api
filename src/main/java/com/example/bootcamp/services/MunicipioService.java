package com.example.bootcamp.services;

import com.example.bootcamp.dtos.MunicipioDto;
import com.example.bootcamp.models.MunicipioVo;
import com.example.bootcamp.repositories.MunicipioRepository;
import com.example.bootcamp.repositories.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;
    @Autowired
    private UfRepository ufRepository;

    public Optional<MunicipioVo> findById(long id){
        return municipioRepository.findById(id);
    }

    public Optional<MunicipioVo> findByNome(String nome) {
        return municipioRepository.findByNome(nome);
    }

    public Optional<MunicipioVo> findByUFId(long codigoUF) {
        return municipioRepository.findByUf(ufRepository.findById(codigoUF).get());
    }

    public boolean existsById(long id){
        return municipioRepository.existsById(id);
    }

    public List<MunicipioVo> getAllMunicipios() {
        return municipioRepository.findAll();
    }

    public MunicipioVo getMunicipioById(long id) {
        return municipioRepository.findById(id).orElse(null);
    }

    public MunicipioVo createMunicipio(MunicipioDto municipioDto) {
        MunicipioVo municipioVo = new MunicipioVo();
        municipioVo.setNome(municipioDto.nome());
        municipioVo.setStatus(municipioDto.status());
        municipioVo.setUf(ufRepository.findById(municipioDto.codigoUF()).get());

        return municipioRepository.save(municipioVo);
    }

    public MunicipioVo updateMunicipio(long id, MunicipioDto municipioDto) {
        MunicipioVo existingMunicipio = municipioRepository.findById(id).orElse(null);
        if (existingMunicipio != null) {
            existingMunicipio.setNome(municipioDto.nome());
            existingMunicipio.setStatus(municipioDto.status());
            existingMunicipio.setUf(ufRepository.findById(municipioDto.codigoUF()).get());
            return municipioRepository.save(existingMunicipio);
        }
        return null;
    }

    public void deleteMunicipio(long id) {
        municipioRepository.deleteById(id);
    }
}