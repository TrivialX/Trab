/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author T-GAMER
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    private String connectionUrl = "jdbc:mysql://localhost:3306/lpoo2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private String dbUser = "root";
    private String dbPwd = "";

    private static ConnectionFactory connectionFactory = null;

    public ConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
