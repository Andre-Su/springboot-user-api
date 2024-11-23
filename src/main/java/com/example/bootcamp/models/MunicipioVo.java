package com.example.bootcamp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_MUNICIPIO")
public class MunicipioVo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEQUENCE_MUNICIPIO")
    @Column(name = "CODIGO_MUNICIPIO")
    private long codigoMunicipio;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 3)
    private int status;

    // Associations
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private UfVo uf;

    // Bairro
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private Set<BairroVo> bairros;

    // Getters and Setters

    public long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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

    public UfVo getUf() {
        return uf;
    }

    public void setUf(UfVo uf) {
        this.uf = uf;
    }

    public Set<BairroVo> getBairros() {
        return bairros;
    }

    public void setBairros(Set<BairroVo> bairros) {
        this.bairros = bairros;
    }
}
