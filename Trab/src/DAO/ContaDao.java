/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cliente;
import Model.Conta;
import Model.Conta.Tipo;
import Model.ContaCorrente;
import Model.ContaInvestimento;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author T-GAMER
 */
public class ContaDao {

    private String inserir = null;
    private String update = null;
    Conta conta;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

  
    public void inserir(Conta conta) throws SQLException {
        if (conta.getTipo().equals(Tipo.Conta_Investimento)) {
            inserir = "INSERT into conta (clinte_id, tipo_conta, deposito_inicial, saldo, montante_minimo, deposito_minimo) values (?,?,?,?,?,?)";
        } else {
            inserir = "INSERT into conta (cliente_id, tipo_conta, deposito_inicial, saldo, limite) values (?,?,?,?,?)";
        }
        try ( Connection connection = getConnection();  PreparedStatement stmtAdiciona = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS);) {
            stmtAdiciona.setInt(1, conta.getDono().getId());
            stmtAdiciona.setString(2, conta.getTipo().toString());
            stmtAdiciona.setBigDecimal(3, BigDecimal.valueOf(conta.getDepositoInicial()));
            stmtAdiciona.setBigDecimal(4, BigDecimal.valueOf(conta.getDepositoInicial()));
            if (conta.getTipo().equals(Tipo.Conta_Investimento)) {
                stmtAdiciona.setBigDecimal(5, BigDecimal.valueOf(conta.getMontanteMinimo()));
                stmtAdiciona.setBigDecimal(6, BigDecimal.valueOf(conta.getDepositoMinimo()));
            } else {
                stmtAdiciona.setBigDecimal(5, BigDecimal.valueOf(conta.getLimite()));
            }
            stmtAdiciona.execute();
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            conta.setNumero(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Conta conta) throws SQLException {
        if (conta.getTipo().equals(Tipo.Conta_Investimento)) {
            update = "UPDATE conta SET tipo_conta, saldo=?, montante_minimo=?, deposito_minimo=? WHERE id=?";
        } else {
            update = "UPDATE conta SET tipo_conta, saldo=?, limite=?, WHERE id=?";
        }
        try ( Connection connection = getConnection();  PreparedStatement stmtAtualiza = connection.prepareStatement(update);) {
            stmtAtualiza.setString(1, conta.getTipo().toString());
            stmtAtualiza.setBigDecimal(2, BigDecimal.valueOf(conta.getSaldo()));
            if (conta.getTipo().equals(Tipo.Conta_Investimento)) {
                stmtAtualiza.setBigDecimal(3, BigDecimal.valueOf(conta.getMontanteMinimo()));
                stmtAtualiza.setBigDecimal(4, BigDecimal.valueOf(conta.getDepositoMinimo()));
                stmtAtualiza.setInt(5, conta.getNumero());
            } else {
                stmtAtualiza.setBigDecimal(3, BigDecimal.valueOf(conta.getLimite()));
                stmtAtualiza.setInt(4, conta.getNumero());
            }
            stmtAtualiza.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Conta buscar(String cpf) throws SQLException {       
        String busca = "Select * from conta INNER JOIN cliente WHERE conta.cliente_id = cliente.id and CPF=?";
        try ( Connection connection = getConnection();  PreparedStatement stmtBusca = connection.prepareStatement(busca); ) {
            stmtBusca.setString(1, cpf);
            ResultSet rs = stmtBusca.executeQuery();
            System.out.println("Número de linhas retornadas: " + rs.getRow());
            
            while (rs.next()) {
                
                String tipo = rs.getString("tipo_conta");
                if (tipo.equals(Tipo.Conta_Corrente.toString())) {
                    
                    conta = new ContaCorrente();
                    conta.setNumero(rs.getInt("id"));
                    conta.setTipo(Tipo.valueOf(rs.getString("tipo_conta")));
                    BigDecimal di = rs.getBigDecimal("deposito_inicial"); //NULLS no BD
                    
                    if (di != null) {
                        conta.setDepositoInicial(di.doubleValue());
                    } else {
                        conta.setDepositoInicial(0.0);
                    }
                    BigDecimal lim = rs.getBigDecimal("limite");
                    if (lim != null) {
                      
                        conta.setLimite(lim.doubleValue());
                    } else {
                        conta.setLimite(0.0);
                    }
                    BigDecimal sd = rs.getBigDecimal("saldo");
                    if (sd != null) {
                        conta.setSaldo(sd.doubleValue());
                    } else {
                        conta.setSaldo(0.0);
                    }
                } else {
                    conta = new ContaInvestimento();
                    conta.setNumero(rs.getInt("id"));
                    conta.setTipo(Tipo.valueOf(rs.getString("tipo_conta")));
                    BigDecimal di = rs.getBigDecimal("deposito_inicial");
                    if (di != null) {
                        conta.setDepositoInicial(di.doubleValue());
                    } else {
                        conta.setDepositoInicial(0.0);
                    }
                    BigDecimal dm = rs.getBigDecimal("deposito_minimo");
                    if (dm != null) {
                        conta.setDepositoMinimo(dm.doubleValue());
                    } else {
                        conta.setDepositoMinimo(0.0);
                    }
                    BigDecimal mi = rs.getBigDecimal("montante_minimo");
                    if (mi != null) {
                        conta.setMontanteMinimo(mi.doubleValue());
                    } else {
                        conta.setMontanteMinimo(0.0);
                    }
                    BigDecimal sd = rs.getBigDecimal("saldo");
                    if (sd != null) {
                        conta.setSaldo(sd.doubleValue());
                    } else {
                        conta.setSaldo(0.0);
                    }

                }
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cliente_id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setCPF(rs.getString("cpf"));
                cliente.setRG(rs.getString("rg"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSalario(rs.getDouble("salario"));
                conta.setCliente(cliente);
            }
            System.out.println("1");
            return conta;

        } catch (SQLException e) {
            System.out.println("2");
            throw new RuntimeException(e);
        }
    }

    public void deletar(Conta conta) throws SQLException {
        System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        String delete = "DELETE FROM conta WHERE id = ?";
        try ( Connection connection = getConnection();  PreparedStatement stmtDelete = connection.prepareStatement(delete);) {
            stmtDelete.setInt(1, conta.getNumero());
            stmtDelete.executeUpdate();
        }
    }
}
