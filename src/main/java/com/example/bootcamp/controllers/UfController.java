package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.UfDto;
import com.example.bootcamp.models.UfVo;
import com.example.bootcamp.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uf")
public class UfController {

    @Autowired
    private UfService ufService;

    @GetMapping
    public ResponseEntity<List<UfVo>> getAllUfs(
            @RequestParam(value = "id", defaultValue = "-1") long id,
            @RequestParam(value = "sigla", defaultValue = "") String sigla,
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "status", defaultValue = "-1") int status
    ) {
        if (!(id == -1)){
            Optional<UfVo> ufVoOptional = ufService.findById(id);
            if (ufVoOptional.isPresent()){
                List<UfVo> ufVoList = new ArrayList<>();
                ufVoList.add(ufVoOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(ufVoList) ;
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (!(sigla.isEmpty())) {
            Optional<UfVo> ufVoOptional = ufService.findBySigla(sigla);
            if (ufVoOptional.isPresent()) {
                List<UfVo> ufVoList = new ArrayList<>();
                ufVoList.add(ufVoOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(ufVoList);
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (!(nome.isEmpty())) {
            Optional<UfVo> ufVoOptional = ufService.findByNome(nome);
            if (ufVoOptional.isPresent()){
                List<UfVo> ufVoList = new ArrayList<>();
                ufVoList.add(ufVoOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(ufVoList) ;
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (!(status == -1)) {
            List<UfVo> ufVoList = new ArrayList<>();
            for (Optional<UfVo> ufVo: ufService.findByStatus(status)){
                if (ufVo.isPresent()) {
                    ufVoList.add(ufVo.get());
                }
            }
            if (!ufVoList.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(ufVoList) ;
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
        }
        return ResponseEntity.status(HttpStatus.OK).body(ufService.findAll());
    }

    @PostMapping
    public ResponseEntity<UfVo> createUf(@RequestBody UfDto ufDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.save(ufDto)) ;
    }

    @PutMapping
    public ResponseEntity<UfVo> updateUf(@RequestParam(value = "id", required = false) long id, @RequestBody UfDto ufDto) {
        Optional<UfVo> ufVoOptional = ufService.findById(id);
        if (ufVoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ufService.update(id, ufDto).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUf(@RequestParam(value = "id", required = false) long id) {
        if (ufService.existsById(id)){
            ufService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}