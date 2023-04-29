package br.com.unipar.frameworks.hibernatemaven;

import br.com.unipar.frameworks.model.Cidade;
import br.com.unipar.frameworks.model.Cliente;
import br.com.unipar.frameworks.model.DAO.CidadeDAO;
import br.com.unipar.frameworks.model.DAO.ClienteDAO;
import br.com.unipar.frameworks.model.Endereco;
import br.com.unipar.frameworks.model.util.EntityManagerUtil;

public class HibernateMaven {

    public static void main(String[] args) {
        
        EntityManagerUtil.getEntityManagerFactory();
        
        Cliente cliente = new Cliente("ZecaUrubu","40028922", "zeca_urubu@gmail.com", Boolean.TRUE);
        
        Cidade cidade = new CidadeDAO().findById(4127700L);
        Endereco endereco = new Endereco(cliente, cidade, "Rua dos Bobos", "0", "Diagonal", "85999-999");
       
        
        cliente.getEnderecos().add(endereco);
        
        new ClienteDAO().save(cliente);
        
        EntityManagerUtil.closeEntityManagerFactory();
        
    }
}
