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
import View.JanelaContaView;
import java.util.List;

/**
 *
 * @author T-GAMER
 */
public class ContaController {

    private ContaDao contadao;
    private ClienteDao clientedao;
    private JanelaContaView view;

    public ContaController(JanelaContaView view, ContaDao contadao) {
        this.view = view;
        this.contadao = contadao;
        this.view.setController(this);
    }

    private void initController() {
        this.view.setController(this);
        this.view.initView();
    }

    
    public void adicionarConta() {
        try {
            Conta conta = view.getContaFormulario();
            contadao.inserir(conta);
            view.inserirContaView(conta);
            view.apresentaInfo("Adicionado com sucesso!");
        } catch (Exception ex) {
            view.apresentaErro("Erro ao adicionar conta.");
        }
    }

    public void atualizarConta() {
        try {
            Conta conta = view.getContaAtualizada();
            contadao.atualizar(conta);
            view.atualizarConta(conta);
        } catch (Exception ex) {
            view.apresentaErro("Erro ao atualizar conta.");
        }
    }

    public void buscarConta() {
        try {
            String cpf = view.getContaParaBuscar();
            Conta conta = contadao.buscar(cpf);
            view.apresentarConta(conta);
        } catch (Exception ex) {
            view.apresentaErro("Conta não encontrada.");
        }
    }

    public void sacarConta() {                                                                
        try {                                        
            Conta conta = view.getContaParaSacar();
            double valor = view.getValorParaSacar();
            conta.saca(valor);
            contadao.atualizar(conta);
            view.apresentarConta(conta);
        } catch (Exception ex) {
            view.apresentaErro("Não foi possivel efetuar o saque.");
        }

    public void depositarConta() {
        try {
            Conta conta = view.getContaParaDepositar();
            double valor = view.getValorParaDepositar();
            conta.deposita(valor);
            contadao.atualizar(conta);
            view.apresentarConta(conta);
        } catch (Exception ex) {
            view.apresentaErro("Não foi possivel efetuar o depósito.");
        }
    }

    public void remuneraConta() {
        try {
            Conta conta = view.getContaParaRemunerar();
            if (conta.getSaldo() > 0.0) {
                conta.remunera();
                contadao.atualizar(conta);
                view.apresentarConta(conta);
            } else {
                view.apresentaErro("Conta com saldo negativo!");
            }
        } catch (Exception ex) {
            view.apresentaErro("Não foi possivel efetuar a remuneração.");
        }
    }


    
        
}
