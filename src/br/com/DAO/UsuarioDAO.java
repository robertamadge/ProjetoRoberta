package br.com.DAO;

import br.com.model.Usuario;
import br.com.util.ConnectionFactory;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UsuarioDAO {
    public Boolean autenticar(Usuario usuario) {
        String sql = "select id from roberta.usuario u2  " +
                "where u2.email ilike '?'  " +
                "and u2.senha = '?' ;";
        Connection conexao = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public Usuario consultarEmail(Usuario usuario) {
        String sql = "select id, nome, email, senha from roberta.usuario u2  " +
                "where u2.email ilike ?;";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ResultSet rs = ps.executeQuery();
            Usuario u = new Usuario();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }
            return u;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public boolean cadastrar(Usuario usuario) {
        String sql = "insert into roberta.usuario  " +
                "(nome , email , senha) " +
                "values( ? , ? , ? );";
        Connection conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }

    }
}


