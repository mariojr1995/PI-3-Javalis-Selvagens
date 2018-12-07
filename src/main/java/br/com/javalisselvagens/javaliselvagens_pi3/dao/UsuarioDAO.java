/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Usuario;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rogerio Sobrinho
 */
public class UsuarioDAO {
    
    private static Connection obterConexao() throws ClassNotFoundException, SQLException{
        //
        Connection conn = null;
        // Passo 1: Registar Driver JBDC
        Class.forName("com.mysql.jdbc.Driver");
        // Passo 2: Obter a conexão
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tadsschoolbd",
                "root",
                "");
        
        return conn;
    }
    
    public Usuario obter(String login) throws SQLException, ClassNotFoundException {
        Connection connection = obterConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("select usu_codigo, usu_nome, usu_login, usu_senha, usu_adm from usuario where usu_login = ?");
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"), rs.getString("usu_login"), rs.getString("usu_senha"), rs.getBoolean("usu_adm"));
            }
        } catch (SQLException ex) {
        } finally {
              //Se o statement ainda estiver aberto, realiza seu fechamento
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return null;
    }
    
    public Usuario obter(Object... chave) throws SQLException, ClassNotFoundException {
        if (chave[0] instanceof Integer) {
            Connection conn = obterConexao();
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement("select usu_codigo, usu_nome, usu_login, usu_senha, usu_adm from usuario where usu_codigo = ?");
                ps.setInt(1, (Integer) chave[0]);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return new Usuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"), rs.getString("usu_login"), rs.getString("usu_senha"), rs.getBoolean("usu_adm"));
                }
            } catch (SQLException ex) {
            } finally {
                    //Se o statement ainda estiver aberto, realiza seu fechamento
                    if (ps != null && !ps.isClosed()) {
                        ps.close();
                    }
                    //Se a conexão ainda estiver aberta, realiza seu fechamento
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
            }
        }
        return null;
    }
    
    public List<Usuario> obterAll() {
        try {
            return obterAll(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
    public List<Usuario> obterAll(int top) throws ClassNotFoundException, SQLException {
        if (top < 0) {
            return null;
        }
        List<Usuario> lista = null;
        Connection conn = obterConexao();
        Statement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.createStatement();
            rs = ps.executeQuery("select " + (top > 0 ? "top " + top : "") + "usu_codigo, usu_nome, usu_login, usu_senha, usu_adm from usuario");
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"), rs.getString("usu_login"), rs.getString("usu_senha"), rs.getBoolean("usu_adm")));
            }
        } catch (SQLException ex) {
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
              if (ps != null && !ps.isClosed()) {
                  ps.close();
              }
              //Se a conexão ainda estiver aberta, realiza seu fechamento
              if (conn != null && !conn.isClosed()) {
                  conn.close();
              }
        }
        return lista;
    }
}