package com.example.bootcamp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_BAIRRO")
public class BairroVo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEQUENCE_BAIRRO")
    @Column(name = "CODIGO_BAIRRO")
    private long codigoBairro;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 3)
    private int status;

    //municipio @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private MunicipioVo municipio;

    //endereco @OneToMany
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @OneToMany(mappedBy = "bairro", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<EnderecoVo> endereco;

    public long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MunicipioVo getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioVo municipio) {
        this.municipio = municipio;
    }

    public Set<EnderecoVo> getEndereco() {
        return endereco;
    }

    public void setEndereco(Set<EnderecoVo> endereco) {
        this.endereco = endereco;
    }
}
