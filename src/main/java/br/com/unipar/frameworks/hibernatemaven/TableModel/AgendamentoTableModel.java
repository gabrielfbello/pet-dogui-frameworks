package br.com.unipar.frameworks.hibernatemaven.TableModel;

import br.com.unipar.frameworks.model.Agendamento;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AgendamentoTableModel extends AbstractTableModel {

    private final List<Agendamento> agendamentos;
    private final String[] columnNames = {"Pet", "Serviço", "Data", "Hora"};

    public AgendamentoTableModel(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public int getRowCount() {
        return agendamentos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Agendamento agendamento = agendamentos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return agendamento.getPet().getNome();
            case 1:
                return agendamento.getServicosAsString();
            case 2:
                return agendamento.getDatahora().toLocaleString();
            default:
                throw new IllegalArgumentException("Coluna inválida");
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Agendamento getAgendamento(int rowIndex) {
        return agendamentos.get(rowIndex);
    }
}