/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ClienteDao;
import DAO.ContaDao;
import Model.Cliente;
import Model.Conta;
import View.JanelaClienteView;
import java.util.List;

/**
 *
 * @author T-GAMER
 */
public class ClienteController {

    private JanelaClienteView view;
    private ClienteDao clientedao;

    public ClienteController(JanelaClienteView view, ClienteDao clientedao) {
        this.view = view;
        this.clientedao = clientedao;
        initController();
    }

    private void initController() {
        this.view.setController(this);
        this.view.initView();
    }

    public void listarCliente() {
         System.out.print("15");
        try {
            List<Cliente> lista = this.clientedao.listar();
            view.mostrarListaClientes(lista);
        } catch (Exception ex) {
            ex.printStackTrace();
            view.apresentaErro("Erro ao listar clientes.");
        }
    }
     public void listarClienteSemConta() {
        try {
            //view.limparClienteAtualizar();
            List<Cliente> lista = this.clientedao.listarClienteSemConta();
            view.mostrarListaClientes(lista);
        } catch (Exception ex) {
            ex.printStackTrace();
            view.apresentaErro("Erro ao listar clientes.");
        }
    }

   public void adicionarCliente() {
        try {
            Cliente cliente = view.getClienteFormulario();
            clientedao.inserir(cliente);
            view.inserirClienteView(cliente);
            //view.apresentaInfo("Adicionado com sucesso!");
        } catch (Exception ex) {
            view.apresentaErro("Erro ao adicionar cliente.");
        }
    }

    public void atualizarCliente() {
        try {
            Cliente cliente = view.getClienteFormularioManipula();
            if (cliente == null) {
                view.apresentaInfo("Selecione um cliente na tabela para atualizar.");
                return;
            }
            System.out.print(cliente.getId());
            clientedao.atualizar(cliente);
            view.atualizarCliente(cliente);

        } catch (Exception ex) {
            view.apresentaErro("Erro ao atualizar cliente.");
        }
    }

    public void deletarCliente() {
        try {
            Cliente cliente = view.getClienteFormulario();
            ContaDao cntdao = new ContaDao();       
            Conta conta = cntdao.buscar(cliente.getCPF());           
            cntdao.deletar(conta);
            clientedao.deletar(cliente);
            view.excluirClienteView(cliente);
        } catch (Exception ex) {
            view.apresentaErro("Erro ao excluir Cliente.");
        }
    }

}
