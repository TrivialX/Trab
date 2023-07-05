/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author T-GAMER
 */
public class Cliente implements Comparable<Cliente> {

    public int id;
    private String nome;
    private String sobrenome;
    private String RG;
    private String CPF;
    private String endereco;
    private double salario;

    public Cliente(int id, String nome, String sobrenome, String RG, String CPF, String endereco, double salario) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.RG = RG;
        this.CPF = CPF;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Cliente() {
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

     @Override
    public int compareTo(Cliente cliente) {
        return this.nome.compareTo(cliente.nome);
    }

    public int compareToSobrenome(Cliente cliente) {
        int result = this.sobrenome.compareTo(cliente.sobrenome);
        
        if (result == 0) {
            // Se os sobrenomes forem iguais, compare pelo nome
            result = this.nome.compareTo(cliente.nome);
        }
        
        return result;
    }

    public int compareToSalario(Cliente cliente) {
        return Double.compare(cliente.getSalario(), this.salario);
    }
    
    
    public static List<Cliente> ordenaNome(List<Cliente> clientes) {
        Collections.sort(clientes);
        return clientes;
    }

    public static List<Cliente> ordenaSobrenome(List<Cliente> clientes) {
        // Ordenação por sobrenome (ordem alfabética)
        Collections.sort(clientes, Cliente::compareToSobrenome);
        return clientes;
    }

    public static List<Cliente> ordenaSalario(List<Cliente> clientes) {
        // Ordenação por salário (do maior para o menor)
        Collections.sort(clientes, Cliente::compareToSalario);
        return clientes;
    }
}
