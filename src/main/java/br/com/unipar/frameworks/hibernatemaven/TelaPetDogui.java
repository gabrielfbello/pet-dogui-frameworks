package br.com.unipar.frameworks.hibernatemaven;

import br.com.unipar.frameworks.hibernatemaven.TableModel.AgendamentoTableModel;
import br.com.unipar.frameworks.model.Agendamento;
import br.com.unipar.frameworks.model.DAO.AgendamentoDAO;
import br.com.unipar.frameworks.model.Pet;
import br.com.unipar.frameworks.model.Servico;
import br.com.unipar.frameworks.model.util.EntityManagerUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TelaPetDogui extends javax.swing.JFrame {

    private JPanel painelCadastro;
    private JComboBox<Pet> comboPet;
    private JComboBox<Servico> comboServico;
    private JButton btnAdicionarServico;
    private JButton btnSalvarAgendamento;
    private JButton btnExcluirAgendamento;
    private JButton btnEditarAgendamento;
    private JTextField txtData;
    private JTextField txtHora;
    private JTable tabelaAgendamentos;

    private List<Servico> servicosSelecionados = new ArrayList<>();

    private JLabel lblServicosSelecionados;



    public TelaPetDogui() {
        EntityManagerUtil.getEntityManagerFactory();
        initComponents();
        atualizaListaAgendamentos();
    }

    private void initComponents() {
        setTitle("Pet Dogui - Agendamentos");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        painelCadastro = new JPanel();
        painelCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Agendamento"));
        getContentPane().add(painelCadastro);

        JLabel lblPet = new JLabel("Pet:");
        painelCadastro.add(lblPet);

        comboPet = new JComboBox<>();
        painelCadastro.add(comboPet);

        JLabel lblServico = new JLabel("Serviço:");
        painelCadastro.add(lblServico);

        comboServico = new JComboBox<>();
        painelCadastro.add(comboServico);

        btnAdicionarServico = new JButton("Adicionar Serviço");

        lblServicosSelecionados = new JLabel();
        lblServicosSelecionados.setBounds(10, 130, 300, 30);
        painelCadastro.add(lblServicosSelecionados);


        painelCadastro.add(btnAdicionarServico);
        btnAdicionarServico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarServico();
            }
        });

        JLabel lblData = new JLabel("Data:");
        painelCadastro.add(lblData);

        txtData = new JTextField(10);
        painelCadastro.add(txtData);

        JLabel lblHora = new JLabel("Hora:");
        painelCadastro.add(lblHora);

        txtHora = new JTextField(5);
        painelCadastro.add(txtHora);

        btnSalvarAgendamento = new JButton("Salvar Agendamento");
        painelCadastro.add(btnSalvarAgendamento);
        btnSalvarAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAgendamento();
            }
        });

        tabelaAgendamentos = new JTable();
        getContentPane().add(new javax.swing.JScrollPane(tabelaAgendamentos));

        btnExcluirAgendamento = new JButton("Excluir Agendamento");
        getContentPane().add(btnExcluirAgendamento);
        btnExcluirAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirAgendamento();
            }
        });

        btnEditarAgendamento = new JButton("Editar Agendamento");
        getContentPane().add(btnEditarAgendamento);
        btnEditarAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarAgendamento();
            }
        });

        pack();
    }

    private void atualizaListaAgendamentos() {
        AgendamentoDAO dao = new AgendamentoDAO();
        List<Agendamento> listaAgendamento = dao.findAll();

        AgendamentoTableModel model = new AgendamentoTableModel(listaAgendamento);
        tabelaAgendamentos.setModel(model);
    }

    private void adicionarServico() {
        Servico servicoSelecionado = (Servico) comboServico.getSelectedItem();
        if (servicoSelecionado != null) {
            servicosSelecionados.add(servicoSelecionado);
            atualizarListaServicosGUI();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um serviço para adicionar.");
        }
    }


    private void atualizarListaServicosGUI() {
        StringBuilder servicosTexto = new StringBuilder();
        for (Servico servico : servicosSelecionados) {
            servicosTexto.append(servico.getNome()).append(", ");
        }

        if (servicosTexto.length() > 0) {
            servicosTexto.setLength(servicosTexto.length() - 2);
        }

        lblServicosSelecionados.setText(servicosTexto.toString());
    }

    private void salvarAgendamento() {
        Agendamento agendamento = new Agendamento();
        Pet petSelecionado = (Pet) comboPet.getSelectedItem();
        if (petSelecionado != null) {
            agendamento.setPet(petSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um pet para o agendamento.");
            return;
        }

        atualizaListaAgendamentos();
    }

    private void excluirAgendamento() {
        int selectedRow = tabelaAgendamentos.getSelectedRow();
        if (selectedRow != -1) {
            AgendamentoTableModel model = (AgendamentoTableModel) tabelaAgendamentos.getModel();
            Agendamento agendamento = model.getAgendamento(selectedRow);
            AgendamentoDAO dao = new AgendamentoDAO();
            dao.delete(agendamento);
            atualizaListaAgendamentos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um agendamento para excluir.");
        }
    }

    private void editarAgendamento() {
        int selectedRow = tabelaAgendamentos.getSelectedRow();
        if (selectedRow != -1) {
            AgendamentoTableModel model = (AgendamentoTableModel) tabelaAgendamentos.getModel();
            Agendamento agendamento = model.getAgendamento(selectedRow);

            atualizaListaAgendamentos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um agendamento para editar.");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPetDogui().setVisible(true);
            }
        });
    }

}