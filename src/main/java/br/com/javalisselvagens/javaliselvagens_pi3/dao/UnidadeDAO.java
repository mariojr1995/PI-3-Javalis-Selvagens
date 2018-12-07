/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.dao;

import br.com.javalisselvagens.javaliselvagens_pi3.model.Unidade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rubens.huneke
 */
public class UnidadeDAO implements IServico<Unidade> {
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
    
    //Ininsere unidade
    @Override
    public void cadastrar(Unidade unidade)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO UNIDADE (RAZAOSOCIAL, NOMEFANTASIA, CNPJ, STATUSUNIDADE) "
                + "VALUES (?, ?, ?, ?)";
        
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setString(1, unidade.getRazaoSocial());
            preparedStatement.setString(2, unidade.getNomeFantasia());
            preparedStatement.setString(3, unidade.getCNPJ());
            preparedStatement.setInt(4, unidade.getStatus());
            
            //Executa o comando no banco de dados
            preparedStatement.execute();
        } catch (ClassNotFoundException | SQLException e){
            
        }
        finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    //atualizar unidade
    @Override
    public void atualizar(Unidade unidade)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE UNIDADE SET RAZAOSOCIAL = ?, NOMEFANTASIA = ?, CNPJ = ?, STATUSUNIDADE = ?"
                + "WHERE (IDUNIDADE = ?)";
        
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            
            
            //Configura os parâmetros do "PreparedStatement"
            
            preparedStatement.setString(1, unidade.getRazaoSocial());
            preparedStatement.setString(2, unidade.getNomeFantasia());
            preparedStatement.setString(3, unidade.getCNPJ());
            preparedStatement.setInt(4, unidade.getStatus());
            preparedStatement.setLong (5, unidade.getIdUnidade());
            //Executa o comando no banco de dados
            preparedStatement.execute();
            
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    
    // deletar unidade
    @Override
     public void excluir(long id, int status)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE UNIDADE SET STATUSUNIDADE = ? "
                + "WHERE (IDUNIDADE = ?)";
        
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setInt(1, status);
            preparedStatement.setLong(2, id);
            //Executa o comando no banco de dados
            preparedStatement.execute();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    //Obter unidade
    @Override
    public Unidade obter(long idUnidade)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM UNIDADE "
                + " WHERE (IDUNIDADE=?)";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenará os resultados do banco de dados
        ResultSet result = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setLong(1, idUnidade);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Unidade unidade = new Unidade();
                unidade.setIdUnidade(result.getLong("ID"));
                unidade.setRazaoSocial(result.getString("RAZAOSOCIAL"));
                unidade.setNomeFantasia(result.getString("NOMEFANTASIA"));
                unidade.setCNPJ(result.getString("CNPJ"));
                unidade.setStatus(result.getInt("STATUSUNIDADE"));
                
                //Retorna o resultado
                return unidade;
            }
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        //Se chegamos aqui, o "return" anterior não foi executado porque
        //a pesquisa não teve resultados
        //Neste caso, não há um elemento a retornar, então retornamos "null"
        return null;
    }
    
    
    // Obter lista de unidades
    @Override
    public List<Unidade> procurarNome(String consulta)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM UNIDADE "
                + " WHERE (RAZAOSOCIAL = ? OR NOMEFANTASIA = ?)";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenará os resultados do banco de dados
        ResultSet result = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, consulta);
            preparedStatement.setString(2, consulta);
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            List<Unidade> unidades = new ArrayList<Unidade>();
            
            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Unidade unidade = new Unidade();
                unidade.setIdUnidade(result.getLong("IDUNIDADE"));
                unidade.setRazaoSocial(result.getString("RAZAOSOCIAL"));
                unidade.setNomeFantasia(result.getString("NOMEFANTASIA"));
                unidade.setCNPJ(result.getString("CNPJ"));
                unidade.setStatus(result.getInt("STATUSUNIDADE"));
                unidades.add(unidade);
            }
            
            return unidades;

        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}