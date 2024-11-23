package com.example.bootcamp.dtos;

public record EnderecoDto(
        String cep,
        String complemento,
        String nomeRua,
        int numero,
        long codigoBairro,
        long codigoPessoa
) {
}
