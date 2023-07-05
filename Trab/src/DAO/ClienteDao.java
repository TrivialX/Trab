/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cliente;
import java.math.BigDecimal;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author T-GAMER
 */
public class ClienteDao {

    private final ConnectionFactory connectionFactory;

    public ClienteDao(ConnectionFactory connectionFactory) {
         this.connectionFactory = connectionFactory;
    }

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

 

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public List<Cliente> listar() throws SQLException {
        String select = "SELECT * FROM Cliente";
        try ( Connection connection = getConnection();  PreparedStatement stmtLista = connection.prepareStatement(select);  ResultSet rs = stmtLista.executeQuery();) {
            List<Cliente> clientes = new ArrayList();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setCPF(rs.getString("cpf"));
                cliente.setRG(rs.getString("rg"));
                cliente.setEndereco(rs.getString("endereco"));
                BigDecimal sl = rs.getBigDecimal("salario");
                if (sl != null) {
                    cliente.setSalario(sl.doubleValue());
                }
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
        public List<Cliente> listarClienteSemConta() {
    String select = "SELECT * FROM Cliente WHERE Cliente.id NOT IN (SELECT Cliente_id FROM conta)";
    try (Connection connection = getConnection();
         PreparedStatement stmtLista = connection.prepareStatement(select);
         ResultSet rs = stmtLista.executeQuery()) {
        
        List<Cliente> clientes = new ArrayList<>();
        
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setSobrenome(rs.getString("sobrenome"));
            cliente.setCPF(rs.getString("cpf"));
            cliente.setRG(rs.getString("rg"));
            cliente.setEndereco(rs.getString("endereco"));
            BigDecimal sl = rs.getBigDecimal("salario");
            
            if (sl != null) {
                cliente.setSalario(sl.doubleValue());
            }
            
            clientes.add(cliente);
        }
        
        return clientes;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public void inserir(Cliente cliente) throws SQLException {
        String inserir = "INSERT into cliente (nome, sobrenome, cpf, rg, endereco, salario) values (?,?,?,?,?,?)";
        try ( Connection connection = getConnection();  PreparedStatement stmtAdiciona = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS);) {
            stmtAdiciona.setString(1, cliente.getNome());
            stmtAdiciona.setString(2, cliente.getSobrenome());
            stmtAdiciona.setString(3, cliente.getCPF());
            stmtAdiciona.setString(4, cliente.getRG());
            stmtAdiciona.setString(5, cliente.getEndereco());
            stmtAdiciona.setBigDecimal(6, BigDecimal.valueOf(cliente.getSalario()));
            stmtAdiciona.execute();
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            cliente.setId(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Cliente cliente) throws SQLException {
        String update = "UPDATE cliente SET nome=?, sobrenome=?, cpf=?, rg=?, endereco=?, salario=? WHERE id=?";
        try ( Connection connection = getConnection();  PreparedStatement stmtAtualiza = connection.prepareStatement(update);) {
            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getSobrenome());
            stmtAtualiza.setString(3, cliente.getCPF());
            stmtAtualiza.setString(4, cliente.getRG());
            stmtAtualiza.setString(5, cliente.getEndereco());
            stmtAtualiza.setDouble(6, cliente.getSalario());
            stmtAtualiza.setInt(7, cliente.getId());
            stmtAtualiza.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Cliente cliente) throws SQLException {
        String delete = "DELETE FROM cliente WHERE id = ?";
        try ( Connection connection = getConnection();  PreparedStatement stmtDelete = connection.prepareStatement(delete);) {
            stmtDelete.setInt(1, cliente.getId());
            stmtDelete.executeUpdate();
        }
    }
}
