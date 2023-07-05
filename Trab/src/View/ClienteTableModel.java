/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class ClienteTableModel extends AbstractTableModel{
    private String[] colunas=new String[]{"id","Nome", "Sobrenome", "CPF","RG", "Endereco", "Salario"};

    private List<Cliente> lista=new ArrayList();

    
    public ClienteTableModel(List<Cliente> lista){
        this.lista=lista;
    }

    public ClienteTableModel(){
    }


    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
        /*if(column==0)
            return true;
        return false;*/
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente customer = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return customer.getId();
            case 1: return customer.getNome();
            case 2: return customer.getSobrenome();
            case 3: return customer.getCPF();
            case 4: return customer.getRG();
            case 5: return customer.getEndereco();
            case 6: return customer.getSalario();
            default : return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Cliente customer = lista.get(row);
        switch (col) {
            case 0:
                customer.setId((Integer) value);
                break;
            case 1:
                customer.setNome((String) value);
                break;
            case 2:
                customer.setSobrenome((String) value);
                break;
            case 3:                
                customer.setCPF((String) value);
                break;
            case 4:
                customer.setRG((String) value);
                break;
            case 5:
                customer.setEndereco((String) value);
                break;
            case 6:
                customer.setSalario((Double) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }

    public boolean removeCliente(Cliente cliente) {
        int linha = this.lista.indexOf(cliente);
        boolean result = this.lista.remove(cliente);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaCliente(Cliente cliente) {
        this.lista.add(cliente);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }

    public void setListaCliente(List<Cliente> clientes) {
        this.lista = clientes;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    
    
    
    public Cliente getCliente(int linha){
        return lista.get(linha);
    }
    
}
