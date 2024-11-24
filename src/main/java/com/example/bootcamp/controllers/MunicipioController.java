package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.MunicipioDto;
import com.example.bootcamp.models.MunicipioVo;
import com.example.bootcamp.services.MunicipioService;
import com.example.bootcamp.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;
    @Autowired
    private UfService ufService;

    @GetMapping
    public ResponseEntity<List<MunicipioVo>> getAllMunicipios(
            @RequestParam(value = "id", defaultValue = "-1") long id,
//            @RequestParam(value = "sigla", defaultValue = "-1") Long codigoUF,
            @RequestParam(value = "nome", defaultValue = "-1") String nome) {

        if (!(id == -1)){
            Optional<MunicipioVo> municipioVo = municipioService.findById(id);
            if (municipioVo.isPresent()){
                List<MunicipioVo> municipioVoList = new ArrayList<>();
                municipioVoList.add(municipioVo.get());
                return ResponseEntity.status(HttpStatus.OK).body(municipioVoList) ;
            } else
                return ResponseEntity.notFound().build();
        }
        /* if (!(codigoUF == -1)) {
            List<Optional<MunicipioVo>> municipioVoList = municipioService.findByUFId(codigoUF);
            List<MunicipioVo> municipioVos = new ArrayList<>();
            for (Optional<MunicipioVo> municipioVoOptional: municipioVoList){
                if (municipioVoOptional.isPresent()) {
                    MunicipioVo municipioVo = municipioVoOptional.get();
                    municipioVos.add(municipioVo);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(municipioVos) ;
        } */
        if (!(nome.equals("-1"))) {
            Optional<MunicipioVo> municipioVo = municipioService.findByNome(nome);
            if (municipioVo.isPresent()){
                List<MunicipioVo> municipioVoList = new ArrayList<>();
                municipioVoList.add(municipioVo.get());
                return ResponseEntity.status(HttpStatus.OK).body(municipioVoList) ;
            } else
                return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(municipioService.getAllMunicipios());
    }

    @PostMapping
    public ResponseEntity<MunicipioVo> createMunicipio(@RequestBody MunicipioDto municipioDto) {
        if (ufService.existsById(municipioDto.codigoUF())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(municipioService.createMunicipio(municipioDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<MunicipioVo> updateMunicipio(@RequestParam(value = "id", defaultValue = "-1") long id, @RequestBody MunicipioDto municipioDto) {
        if (municipioService.existsById(id) && ufService.existsById(municipioDto.codigoUF())) {
            return ResponseEntity.status(HttpStatus.OK).body(municipioService.updateMunicipio(id, municipioDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteMunicipio(@RequestParam(value = "id", defaultValue = "-1") long id) {
        municipioService.deleteMunicipio(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}