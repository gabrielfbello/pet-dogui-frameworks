package br.com.unipar.frameworks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTelefone;

    @ManyToOne
    private Cliente cliente;

    @Column(length = 20)
    private String telefone;

    @Column(length = 20)
    private String tipo;
}
