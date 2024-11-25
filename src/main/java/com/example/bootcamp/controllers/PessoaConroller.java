package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.PessoaDto;
import com.example.bootcamp.models.PessoaVo;
import com.example.bootcamp.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaConroller {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaVo>> getPessoa(
            @RequestParam(value = "id", defaultValue = "-1") long id,
            @RequestParam(value = "id", defaultValue = "") String login
    ) {
        if (id != -1) {
            Optional<PessoaVo> pessoa = pessoaService.findById(id);
            if (pessoa.isPresent()) {
                List<PessoaVo> pessoaVos = new ArrayList<>();
                pessoaVos.add(pessoa.get());
                return ResponseEntity.status(HttpStatus.OK).body(pessoaVos);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        if (!login.isEmpty()) {
            Optional<PessoaVo> pessoa = pessoaService.findByLogin(login);
            if (pessoa.isPresent()) {
                List<PessoaVo> pessoaVos = new ArrayList<>();
                pessoaVos.add(pessoa.get());
                return ResponseEntity.status(HttpStatus.OK).body(pessoaVos);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());

    }

    @PostMapping
    public ResponseEntity<PessoaVo> createPessoa(@RequestBody PessoaDto pessoaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaDto));
    }

    @PutMapping
    public ResponseEntity<PessoaVo> updatePessoa(
            @RequestParam(value = "id", required = false) long id,
            @RequestBody PessoaDto pessoaDto
    ) {
        if (pessoaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(id, pessoaDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePessoa(@RequestParam(value = "id", required = false) long id) {
        if (pessoaService.existsById(id)) {
            pessoaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
