package br.com.unipar.frameworks.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;

    @ManyToOne
    @JoinColumn(name = "id_pet")
    private Pet pet; //aqui vai o pet nao o cliente

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario usuario;

    private String obs;

    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "agendamento")
    private List<AgendamentoServico> listaServicos = new ArrayList<>();

    public Agendamento() {
    }

    public Agendamento(Date datahora, Pet pet, Usuario usuario, String obs, BigDecimal valorTotal) {
        this.datahora = datahora;
        this.pet = pet;
        this.usuario = usuario;
        this.obs = obs;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<AgendamentoServico> getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(List<AgendamentoServico> listaServicos) {
        this.listaServicos = listaServicos;
    }

    public String toStringListaServicos() {
        String servicos = "";
        for (AgendamentoServico listaServico : listaServicos) {
            servicos = servicos + ". " + listaServico.getServico().getDescricao() + ", ";
        }
        if (servicos.length() > 2) {
            servicos.substring(0, servicos.length() - 2);
        }
        return servicos;
    }

    public List<Servico> getServico() {
        List<Servico> servicos = new ArrayList<>();
        for (AgendamentoServico agendamentoServico : listaServicos) {
            servicos.add(agendamentoServico.getServico());
        }
        return servicos;
    }

    public String getServicosAsString() {
        StringBuilder servicosStr = new StringBuilder();
        for (AgendamentoServico agendamentoServico : listaServicos) {
            servicosStr.append(agendamentoServico.getServico().getNome()).append(", ");
        }

        if (servicosStr.length() > 0) {
            servicosStr.setLength(servicosStr.length() - 2);
        }

        return servicosStr.toString();
    }
}
