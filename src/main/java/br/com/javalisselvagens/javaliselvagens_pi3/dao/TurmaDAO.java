/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.dao;

import static br.com.javalisselvagens.javaliselvagens_pi3.dao.CursoDAO.obterConexao;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Curso;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Turma;
import java.sql.Connection;
import java.sql.Date;
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
public class TurmaDAO implements IServico<Turma> {
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
    
    //Ininsere turma
    @Override
    public void cadastrar(Turma turma)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO TURMA (NOMETURMA, DATAINICIO, DATAFIM, PERIODO, CAPACIDADE, STATUSTURMA, IDCURSO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
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
            preparedStatement.setString(1, turma.getNomeTurma());
            preparedStatement.setDate(2, turma.getDataInicio());
            preparedStatement.setDate(3, turma.getDataFim());
            preparedStatement.setString(4, turma.getPeriodo());
            preparedStatement.setInt(5, turma.getCapacidade());
            preparedStatement.setInt(6, turma.getStatus());
            preparedStatement.setLong(7, turma.getIdCurso());
 
            
            //Executa o comando no banco de dados
            preparedStatement.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
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
    
    //atualizar turma
    @Override
    public void atualizar(Turma turma)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE TURMA SET NOMETURMA = ?,"
                + "DATAINICIO = ?, DATAFIM = ?, PERIODO = ?, CAPACIDADE = ?, STATUSTURMA = ?"
                + "WHERE (IDTURMA = ?)";
        
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
            
            preparedStatement.setString(1, turma.getNomeTurma());
            preparedStatement.setDate(2, turma.getDataInicio());
            preparedStatement.setDate(3, turma.getDataFim());
            preparedStatement.setString(4, turma.getPeriodo());
            preparedStatement.setInt(5, turma.getCapacidade());
            preparedStatement.setInt(6, turma.getStatus());
            preparedStatement.setLong(7, turma.getIdTurma());
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
    public Turma obter(long idTurma)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM TURMA "
                + " WHERE (idTurma=?)";

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
            preparedStatement.setLong(1, idTurma);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Turma turma = new Turma();
                turma.setIdTurma(result.getLong("idTurma"));
                turma.setNomeTurma(result.getString("nomeTurma"));
                turma.setDataInicio(result.getDate("dataInicio"));
                turma.setDataFim(result.getDate("dataFim"));
                turma.setPeriodo(result.getString("periodo"));
                turma.setCapacidade(result.getInt("capacidade"));
                turma.setStatus(result.getInt("statusTurma"));
                
                return turma;
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
    
    
    public List<Turma> turmaAll()
            throws SQLException, Exception {

        String sql = "SELECT * FROM TURMA;";

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
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            List<Turma> turmas = new ArrayList<Turma>();

            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD

                Turma turmaAll = new Turma();
                turmaAll.setIdTurma(result.getInt("idTurma"));
                turmaAll.setNomeTurma(result.getString("nomeTurma"));
                turmaAll.setPeriodo(result.getString("periodo"));
                turmaAll.setCapacidade(result.getInt("capacidade"));
                turmas.add(turmaAll);
            }

            return turmas;

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
    
    
    // Obter lista de turmas
    @Override
    public List<Turma> procurarNome(String consulta)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM TURMA "
                + " WHERE (NOMETURMA = ?)";

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
            preparedStatement.setString(3, consulta);
            preparedStatement.setString(4, consulta);
            preparedStatement.setString(5, consulta);
            
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            List<Turma> turmas = new ArrayList<Turma>();
            
            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Turma turma = new Turma();
                turma.setIdTurma(result.getLong("ID"));
                turma.setNomeTurma(result.getString("NOMETURMA"));
                turma.setDataInicio(result.getDate("DATAINICIO"));
                turma.setDataFim(result.getDate("DATAFIM"));
                turma.setPeriodo(result.getString("PERIODO"));
                turma.setCapacidade(result.getInt("CAPACIDADE"));
                turma.setPeriodo(result.getString("PERIODO"));
                turma.setStatus(result.getInt("STATUSTURMA"));
                turmas.add(turma);
            }
            
            return turmas;

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
    
    @Override
    public void excluir(long id, int status) throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE TURMA SET STATUS = ? "
                + "WHERE (IDTURMA = ?)";
        
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
        
}