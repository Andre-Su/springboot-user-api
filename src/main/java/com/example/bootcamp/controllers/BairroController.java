package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.BairroDto;
import com.example.bootcamp.models.BairroVo;
import com.example.bootcamp.services.BairroService;
import com.example.bootcamp.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bairro")
public class BairroController {
    @Autowired
    private BairroService bairroService;
    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<List<BairroVo>> getAllBairros() {
        return ResponseEntity.status(HttpStatus.OK).body(bairroService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<BairroVo> getBairroById(@RequestParam(value = "id", required = false) long id) {
        Optional<BairroVo> bairroVoOptional = bairroService.findById(id);
        return bairroVoOptional.map(bairroVo -> ResponseEntity.status(HttpStatus.OK).body(bairroVo))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/nome")
    public ResponseEntity<BairroVo> getBairroByNome(@RequestParam(value = "nome", required = false) String nome) {
        Optional<BairroVo> bairroVoOptional = bairroService.findByNome(nome);
        return bairroVoOptional.map(bairroVo -> ResponseEntity.status(HttpStatus.OK).body(bairroVo)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<BairroVo> createBairro(@RequestBody BairroDto bairroDto) {
        if(municipioService.existsById(bairroDto.codigoMunicipio())) {
            return ResponseEntity.status(HttpStatus.OK).body(bairroService.save(bairroDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<BairroVo> updateBairro(@PathVariable long id, @RequestBody BairroDto bairroDto) {
        Optional<BairroVo> bairroVoOptional = bairroService.findById(id);
        if(bairroVoOptional.isPresent() && municipioService.existsById(bairroDto.codigoMunicipio())) {
            return ResponseEntity.status(HttpStatus.OK).body(bairroService.updateBairro(id, bairroDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
