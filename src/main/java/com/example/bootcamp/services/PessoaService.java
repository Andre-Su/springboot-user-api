package com.example.bootcamp.services;

import com.example.bootcamp.dtos.EnderecoDto;
import com.example.bootcamp.dtos.PessoaDto;
import com.example.bootcamp.models.EnderecoVo;
import com.example.bootcamp.models.PessoaVo;
import com.example.bootcamp.repositories.EnderecoRepository;
import com.example.bootcamp.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    private EnderecoRepository enderecoRepository;

    public PessoaVo save(PessoaDto pessoaDto) {
        PessoaVo pessoa = new PessoaVo();
        pessoa.setNome(pessoaDto.nome());
        pessoa.setStatus(pessoaDto.status());
        pessoa.setSobrenome(pessoaDto.sobrenome());
        pessoa.setIdade(pessoaDto.idade());
        pessoa.setLogin(pessoaDto.login());
        pessoa.setSenha(pessoaDto.senha());

        for(EnderecoDto enderecoDto:pessoaDto.enderecos()){
            EnderecoVo endereco = new EnderecoVo();
            endereco.setNomeRua(enderecoDto.nomeRua());
            endereco.setNumero(enderecoDto.numero());
            endereco.setCep(enderecoDto.cep());
            endereco.setPessoa(pessoa);
            enderecoRepository.save(endereco);
            pessoa.getEnderecos().add(endereco);
        }
        return pessoaRepository.save(pessoa);
    }

    public List<PessoaVo> findAll() {
        return pessoaRepository.findAll();
    }

}
