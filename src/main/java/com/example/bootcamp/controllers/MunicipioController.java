package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.MunicipioDto;
import com.example.bootcamp.models.MunicipioVo;
import com.example.bootcamp.services.MunicipioService;
import com.example.bootcamp.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;
    @Autowired
    private UfService ufService;

    @GetMapping
    public ResponseEntity<List<MunicipioVo>> getAllMunicipios() {
        return ResponseEntity.status(HttpStatus.OK).body(municipioService.getAllMunicipios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioVo> getMunicipioById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(municipioService.getMunicipioById(id));
    }

    @PostMapping
    public ResponseEntity<MunicipioVo> createMunicipio(@RequestBody MunicipioDto municipioDto) {
        if (ufService.existsById(municipioDto.codigoUF())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(municipioService.createMunicipio(municipioDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MunicipioVo> updateMunicipio(@PathVariable long id, @RequestBody MunicipioDto municipioDto) {
        if (municipioService.existsById(id) && ufService.existsById(municipioDto.codigoUF())) {
            return ResponseEntity.status(HttpStatus.OK).body(municipioService.updateMunicipio(id, municipioDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMunicipio(@PathVariable long id) {
        municipioService.deleteMunicipio(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}