package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.UfDto;
import com.example.bootcamp.models.UfVo;
import com.example.bootcamp.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uf")
public class UfController {

    @Autowired
    private UfService ufService;

    @GetMapping
    public List<UfVo> getAllUfs() {
        return ufService.findAll();
    }

    @GetMapping("/?id={id}")
    public ResponseEntity<UfVo> getUfById(@PathVariable long id) {
        return ufService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UfVo> createUf(@RequestBody UfDto ufDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.save(ufDto)) ;
    }

    @PutMapping("/?id={id}")
    public ResponseEntity<UfVo> updateUf(@PathVariable long id, @RequestBody UfDto ufDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ufService.update(id, ufDto).get());
    }

    @DeleteMapping("/?id={id}")
    public ResponseEntity<Void> deleteUf(@PathVariable long id) {
        if (ufService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}