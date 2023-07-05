/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author T-GAMER
 */
public class ContaCorrente extends Conta implements ContaInterface {

    public ContaCorrente() {
    }

    public ContaCorrente(int id, Cliente cliente, double depositoInicial, double limite, double montanteMinimo, double depositoMinimo, double saldo, Tipo tipo) {
        super(id, cliente, depositoInicial, limite, montanteMinimo, depositoMinimo, saldo, tipo);
    }

    public ContaCorrente(Cliente cliente, double depositoInicial, double limite, double saldo, Tipo tipo) {
        super(cliente, depositoInicial, limite, saldo, tipo);
    }
    

    @Override
    public void setLimite(double limite) {
        super.setLimite(limite);
    }

    @Override
    public void remunera() {
        double saldoAtual = super.getSaldo();
        if (saldoAtual > 0.0) {
            super.setSaldo(saldoAtual += Math.abs(saldoAtual * 0.01));
        }
        /* Não deveria retornar um booleano caso o saldo fosse negativo para tratar mensagem na view e não remunerar?
        Usando math.abs para qualquer caso cria a situação de remuneração maior para saldo negativos maiores;
        Não usando, a remuneração acrescenta valores negativos a conta;
         */
    }

    @Override
    public boolean saca(double valor) {
        double SaldoCorrigido = getSaldo() - valor;
        if (valor > 0 && SaldoCorrigido >= -getLimite()) {
            super.saca(valor);
            return true;
        }                   //tratar na view - Mostrar mensagem na tela informando o usuário.
        return false;

    }

    @Override
    public void setTipo(Tipo tipo) {
        super.tipo = Tipo.Conta_Corrente;
    }

    @Override
    public boolean deposita(double valor) {
        return super.deposita(valor);
    }

}
