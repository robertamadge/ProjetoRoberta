package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            conexao.setAutoCommit(false);
            return conexao;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
