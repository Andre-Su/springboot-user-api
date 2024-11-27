package com.example.bootcamp.services;

import com.example.bootcamp.dtos.EnderecoDto;
import com.example.bootcamp.dtos.PessoaDto;
import com.example.bootcamp.models.EnderecoVo;
import com.example.bootcamp.models.PessoaVo;
import com.example.bootcamp.repositories.EnderecoRepository;
import com.example.bootcamp.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Optional<PessoaVo> findByLogin(String login){
        return pessoaRepository.findByLogin(login);
    }

    public PessoaVo save(PessoaDto pessoaDto) {
        PessoaVo pessoa = new PessoaVo();
        pessoa.setNome(pessoaDto.nome());
        pessoa.setStatus(pessoaDto.status());
        pessoa.setSobrenome(pessoaDto.sobrenome());
        pessoa.setIdade(pessoaDto.idade());
        pessoa.setLogin(pessoaDto.login());
        pessoa.setSenha(pessoaDto.senha());
        pessoaRepository.save(pessoa);
        for(EnderecoDto enderecoDto:pessoaDto.enderecos()){
            EnderecoVo endereco = new EnderecoVo();
            endereco.setNomeRua(enderecoDto.nomeRua());
            endereco.setNumero(enderecoDto.numero());
            endereco.setComplemento(enderecoDto.complemento());
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


    public Optional<PessoaVo> findById(long id) {
        return pessoaRepository.findById(id);
    }

    public boolean existsById(long id) {
        return pessoaRepository.existsById(id);
    }

    public PessoaVo updatePessoa(long id, PessoaDto pessoaDto) {
        PessoaVo pessoa = pessoaRepository.findById(id).orElse(null);
        if (pessoa!= null) {
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
                    endereco.setComplemento(enderecoDto.complemento());
                    endereco.setCep(enderecoDto.cep());
                    enderecoRepository.save(endereco);
                    pessoa.getEnderecos().add(endereco);
            }
            pessoaRepository.save(pessoa);
        }
        return pessoa;
    }

    public void deleteById(long id) {
        PessoaVo pessoa = pessoaRepository.findById(id).get();
        pessoa.setStatus(2);
        pessoaRepository.save(pessoa);
    }

    public List<Optional<PessoaVo>> findByStatus(int status) {
        return pessoaRepository.findByStatus(status);
    }
}
