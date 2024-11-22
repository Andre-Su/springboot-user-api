package com.example.bootcamp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_PESSOA")
public class PessoaVo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEQUENCE_PESSOA")
    @Column(name = "CODIGO_PESSOA")
    private long codigoPessoa;

    @Column(nullable = false, length = 3)
    private int status;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false, length = 3)
    private int idade;

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false, length = 50)
    private String senha;

    // endereco @OneToMany
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<EnderecoVo> enderecos = new HashSet<>();

    public long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<EnderecoVo> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoVo> enderecos) {
        this.enderecos = enderecos;
    }
}
