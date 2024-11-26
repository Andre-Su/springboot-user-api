package com.example.bootcamp.controllers;

import com.example.bootcamp.dtos.BairroDto;
import com.example.bootcamp.models.BairroVo;
import com.example.bootcamp.services.BairroService;
import com.example.bootcamp.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<BairroVo>> getBairro(
            @RequestParam(value = "id", defaultValue = "-1") long id,
            @RequestParam(value = "nome", defaultValue = "") String nome
    ) {
        if (id != -1){
            Optional<BairroVo> bairroVoOptional = bairroService.findById(id);
            if (bairroVoOptional.isPresent()){
                List<BairroVo> bairroVoList = new ArrayList<>();
                bairroVoList.add(bairroVoOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(bairroVoList) ;
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (!nome.isEmpty()){
//            List<Optional<BairroVo>> bairroVoOptionalList = bairroService.findByNome(nome);
            List<BairroVo> bairroVoList = new ArrayList<>();
            for (Optional<BairroVo> bairroVoOptional:bairroService.findByNome(nome)){
                bairroVoOptional.ifPresent(bairroVoList::add);
            }
            if (!bairroVoList.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(bairroVoList) ;
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(bairroService.findAll());
    }

    @PostMapping
    public ResponseEntity<BairroVo> createBairro(@RequestBody BairroDto bairroDto) {
        if(municipioService.existsById(bairroDto.codigoMunicipio())) {
            return ResponseEntity.status(HttpStatus.OK).body(bairroService.save(bairroDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping
    public ResponseEntity<BairroVo> updateBairro(@RequestParam(value = "id", required = false) long id, @RequestBody BairroDto bairroDto) {
        Optional<BairroVo> bairroVoOptional = bairroService.findById(id);
        if(bairroVoOptional.isPresent() && municipioService.existsById(bairroDto.codigoMunicipio())) {
            return ResponseEntity.status(HttpStatus.OK).body(bairroService.updateBairro(id, bairroDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam(value = "id", required = false) long id) {
        if (bairroService.exitsById(id)) {
            bairroService.deleteBairro(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
