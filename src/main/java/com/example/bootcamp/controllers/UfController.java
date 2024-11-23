package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.UfDto;
import com.example.bootcamp.models.UfVo;
import com.example.bootcamp.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uf")
public class UfController {

    @Autowired
    private UfService ufService;

    @GetMapping
    public ResponseEntity<List<UfVo>> getAllUfs() {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UfVo> getUfById(@PathVariable long id) {
        Optional<UfVo> ufVoOptional = ufService.findById(id);
        return ufVoOptional.map(ufVo -> ResponseEntity.status(HttpStatus.OK).body(ufVo)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UfVo> createUf(@RequestBody UfDto ufDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.save(ufDto)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UfVo> updateUf(@PathVariable long id, @RequestBody UfDto ufDto) {
        Optional<UfVo> ufVoOptional = ufService.findById(id);
        if (ufVoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ufService.update(id, ufDto).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUf(@PathVariable long id) {
        if (ufService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}