package br.com.unipar.frameworks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cidade {
    
    @Id
    @Column(name = "cod_ibge")
    private Long idCidade;
    @Column(length = 120)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "cod_estado")
    private Estado estado;


    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
