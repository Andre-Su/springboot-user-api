package com.example.bootcamp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_UF")
public class UfVo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEQUENCE_UF")
    @Column(name = "CODIGO_UF")
    private long codigoUf;

    @Column(nullable = false, length = 3)
    private String sigla;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 3)
    private int status;
    // Associations
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @OneToMany(mappedBy = "uf", fetch = FetchType.LAZY)
    private Set<MunicipioVo> municipios;

    public long getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(long codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    public Set<MunicipioVo> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Set<MunicipioVo> municipios) {
        this.municipios = municipios;
    }
}
