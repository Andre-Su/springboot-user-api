package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.MunicipioDto;
import com.example.bootcamp.models.MunicipioVo;
import com.example.bootcamp.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public List<MunicipioVo> getAllMunicipios() {
        return municipioService.getAllMunicipios();
    }

    @GetMapping("/?id={id}")
    public MunicipioVo getMunicipioById(@PathVariable long id) {
        return municipioService.getMunicipioById(id);
    }

    @PostMapping
    public ResponseEntity<MunicipioVo> createMunicipio(@RequestBody MunicipioDto municipioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(municipioService.createMunicipio(municipioDto)) ;
    }

    @PutMapping("/?id={id}")
    public MunicipioVo updateMunicipio(@PathVariable long id, @RequestBody MunicipioDto municipioDto) {
        return municipioService.updateMunicipio(id, municipioDto);
    }

    @DeleteMapping("/?id={id}")
    public void deleteMunicipio(@PathVariable long id) {
        municipioService.deleteMunicipio(id);
    }
}