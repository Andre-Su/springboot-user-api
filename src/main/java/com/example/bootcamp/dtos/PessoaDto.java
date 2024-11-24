package com.example.bootcamp.dtos;

import java.util.Set;

public record PessoaDto(
        String nome,
        String sobrenome,
        int status,
        int idade,
        String login,
        String senha,
        Set<EnderecoDto> enderecos
) {
}
