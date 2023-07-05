/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author T-GAMER
 */
public class ContaInvestimento extends Conta implements ContaInterface {

    public ContaInvestimento() {
    }

    public ContaInvestimento(int numero, Cliente cliente, double depositoInicial, double limite, double montanteMinimo, double depositoMinimo, double saldo, Tipo tipo) {
        super(numero, cliente, depositoInicial, limite, montanteMinimo, depositoMinimo, saldo, tipo);
    }

    public ContaInvestimento(Cliente cliente, double depositoInicial, double montanteMinimo, double depositoMinimo, double saldo, Tipo tipo) {
        super(cliente, depositoInicial, montanteMinimo, depositoMinimo, saldo, tipo);
    }
    

    @Override
    public void remunera() {
        double saldoAtual = super.getSaldo();
        if (saldoAtual > 0.0) {
            super.setSaldo(saldoAtual += Math.abs(saldoAtual * 0.02));
        }
    }

    @Override
    public boolean saca(double valor) {
        double saldoCorrigido = super.getSaldo() - valor;
        if (saldoCorrigido >= super.getMontanteMinimo()) {
            super.saca(valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deposita(double valor) {
        if (valor >= super.getDepositoMinimo()) {
            super.deposita(valor);
            return true;
        } else {
            return false;
        }
    }
    @Override
     public void setTipo(Tipo tipo){
        super.tipo = Tipo.Conta_Investimento;
    }


}
