package com.example.bootcamp.services;

import com.example.bootcamp.dtos.BairroDto;
import com.example.bootcamp.models.BairroVo;
import com.example.bootcamp.repositories.BairroRepository;
import com.example.bootcamp.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private MunicipioRepository municipioRepository;

    public boolean exitsById(long id){
        return bairroRepository.existsById(id);
    }

    public List<BairroVo> findAll() {
        return bairroRepository.findAll();
    }

    public Optional<BairroVo> findById(long id) {
        return bairroRepository.findById(id);
    }

    public Optional<BairroVo> findByNome(String nome){
        return bairroRepository.findByNome(nome);
    }

    public BairroVo save(BairroDto bairroDto) {
        BairroVo bairroVo = new BairroVo();
        bairroVo.setNome(bairroDto.nome());
        bairroVo.setStatus(bairroDto.status());
        bairroVo.setMunicipio(municipioRepository.findById(bairroDto.codigoMunicipio()).get());
        return bairroRepository.save(bairroVo);
    }

    public void deleteBairro(long id) {
        BairroVo bairroVo = bairroRepository.findById(id).get();
        bairroVo.setStatus(2);
        bairroRepository.save(bairroVo);
    }

    public BairroVo updateBairro(long id, BairroDto bairroDto) {
        Optional<BairroVo> bairroOptional = bairroRepository.findById(id);

        if (bairroOptional.isPresent()) {
            BairroVo bairroVo = bairroOptional.get();
            bairroVo.setNome(bairroDto.nome());
            bairroVo.setStatus(bairroDto.status());
            bairroVo.setMunicipio(municipioRepository.findById(bairroDto.codigoMunicipio()).get());
            return bairroRepository.save(bairroVo);
        }
        return null;
    }
}
