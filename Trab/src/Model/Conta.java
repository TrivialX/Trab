/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author T-GAMER
 */
public abstract class Conta implements ContaInterface {

    private int numero;
    private Cliente cliente;
    private double depositoInicial;
    private double limite;
    private double montanteMinimo;
    private double depositoMinimo;
    private double saldo;

    public enum Tipo {
        Conta_Corrente,
        Conta_Investimento
    }
    public Tipo tipo;

    public Conta() {
    }

    public Conta(int numero, Cliente cliente, double depositoInicial, double limite, double montanteMinimo, double depositoMinimo, double saldo, Tipo tipo) {
        this.numero = numero;
        this.cliente = cliente;
        this.depositoInicial = depositoInicial;
        this.limite = limite;
        this.montanteMinimo = montanteMinimo;
        this.depositoMinimo = depositoMinimo;
        this.saldo = saldo;
        this.tipo = tipo;
    }
     public Conta(Cliente cliente, double depositoInicial, double montanteMinimo, double depositoMinimo, double saldo, Tipo tipo) {
        this.cliente = cliente;
        this.depositoInicial = depositoInicial;
        this.montanteMinimo = montanteMinimo;
        this.depositoMinimo = depositoMinimo;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public Conta(Cliente cliente, double depositoInicial, double limite, double saldo, Tipo tipo) {
        this.cliente = cliente;
        this.depositoInicial = depositoInicial;
        this.limite = limite;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    @Override
    public boolean deposita(double valor) {
        if (valor > 0.0) {
            setSaldo(getSaldo() + valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean saca(double valor) {
        if (valor > 0.0) {
            setSaldo(getSaldo() - valor);
            return true;
        } else {
            return false;
        }
    }

    public double getDepositoInicial() {
        return depositoInicial;
    }

    public double getLimite() {
        return limite;
    }

    public double getMontanteMinimo() {
        return montanteMinimo;
    }

    public double getDepositoMinimo() {
        return depositoMinimo;
    }

    @Override
    public Cliente getDono() {
        return cliente;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void remunera() {
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDepositoInicial(double depositoInicial) {
        this.depositoInicial = depositoInicial;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public void setMontanteMinimo(double montanteMinimo) {
        this.montanteMinimo = montanteMinimo;
    }

    public void setDepositoMinimo(double depositoMinimo) {
        this.depositoMinimo = depositoMinimo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
