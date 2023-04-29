
package br.com.unipar.frameworks.model;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AgendamentoServico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Servico servico;
    
    @ManyToOne
    @JoinColumn(name = "id_agendamento")
    private Agendamento agendamento;
    
    private BigDecimal valorUnitario;

    public AgendamentoServico() {
    }
    
    public AgendamentoServico(Servico servico, Agendamento agendamento, BigDecimal valorUnitario) {
        this.servico = servico;
        this.agendamento = agendamento;
        this.valorUnitario = valorUnitario;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
