package com.example.bootcamp.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_ENDERECO")
public class EnderecoVo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEQUENCE_ENDERECO")
    @Column(name = "CODIGO_ENDERECO")
    private long codigoEndereco;

    @Column(nullable = false, length = 60)
    private String nomeRua;

    @Column(nullable = false, length = 10)
    private int numero;

    @Column(nullable = false, length = 20)
    private String complemento;

    @Column(nullable = false, name = "CEP", length = 10)
    private String cep;

    //pessoa @OneToMany
    //@JsonProperty(access = JsonProperty.Access.AUTO)
    @ManyToOne
    @JoinColumn(name = "CODIGO_PESSOA")
    private PessoaVo pessoa;

    //bairro @OneToMany
    //@JsonProperty(access = JsonProperty.Access.AUTO)
    @ManyToOne
    @JoinColumn(name = "CODIGO_BAIRRO")
    private BairroVo bairro;

    public BairroVo getBairro() {
        return bairro;
    }

    public void setBairro(BairroVo bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(long codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public PessoaVo getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaVo pessoa) {
        this.pessoa = pessoa;
    }
}
