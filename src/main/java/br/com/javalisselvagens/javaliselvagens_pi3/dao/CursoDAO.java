/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.dao;

import br.com.javalisselvagens.javaliselvagens_pi3.model.Curso;
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
public class CursoDAO implements IServico<Curso> {
    Connection conexao;
  
    public static Connection obterConexao() throws ClassNotFoundException, SQLException{
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

    public CursoDAO(Connection conexao){
        this.conexao= conexao;
    }

    public CursoDAO() {
    }
   
    //insere curso
    @Override
    public void cadastrar(Curso obj)
            throws SQLException, Exception {
            //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO CURSO (nome, descricao, valor, tipo,"
                + " status) "
                + "VALUES (?, ?, ?, ?, ?)";
        
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
            preparedStatement.setString(1, obj.getNome());
            preparedStatement.setString(2, obj.getDescricao());
            preparedStatement.setFloat(3, obj.getValor());
            preparedStatement.setString(4, obj.getTipo());
            preparedStatement.setInt(5, obj.getStatus());
            
            //Executa o comando no banco de dados
            preparedStatement.execute();
        } catch (Exception e){
            throw new Exception("Não foi possível cadastrar, por favor, revise os campos digitados.",e);
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
    
    //atualizar curso
    @Override
    public void atualizar(Curso curso)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE CURSO SET nome = ?, descricao = ?, valor = ?,"
                + " tipo = ?, status = ?"
                + " WHERE (idCurso = ?)";
        
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
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setFloat(3, curso.getValor());
            preparedStatement.setString(4, curso.getTipo());
            preparedStatement.setInt(5, curso.getStatus());
             preparedStatement.setLong (6,curso.getIdCurso());
            //Executa o comando no banco de dados
            preparedStatement.execute();          
            
        } catch (Exception e){
            throw new Exception("Não foi possível atualizar, por favor, revise os campos digitados.",e);
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
    
    //Obter curso
    @Override
    public Curso obter(long idCurso)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM CURSO "
                + " WHERE (idCurso=? OR tipo=?)";
      

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
            preparedStatement.setLong(1, idCurso);
            preparedStatement.setLong(2, idCurso);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Curso curso = new Curso();
                curso.setIdCurso(result.getLong("idCurso"));
                curso.setNome(result.getString("nome"));
                curso.setDescricao(result.getString("descricao"));
                curso.setValor(result.getFloat("valor"));
                curso.setTipo(result.getString("tipo"));
                curso.setStatus(result.getInt("status"));
                                                
                              
                                
                //Retorna o resultado
                return curso;
            }
        } catch (Exception e){
            throw new Exception("Não foi possível obter o curso. Por favor, tente novamente mais tarde (500)",e);
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
    
    
    // Obter lista de curso
    @Override
    public List<Curso> procurarNome(String consulta)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM CURSO "
                + " WHERE (nome=? OR tipo=?) ";

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
            List<Curso> cursos = new ArrayList<Curso>();
            
            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Curso curso = new Curso();
                curso.setIdCurso(result.getLong("idCurso"));
                curso.setNome(result.getString("nome"));
                curso.setDescricao(result.getString("descricao"));
                curso.setValor(result.getFloat("valor"));
                curso.setTipo(result.getString("tipo"));
                curso.setStatus(result.getInt("status"));
                cursos.add(curso);
            }
            
            return cursos;
        } catch (Exception e){
            throw new Exception("Não foi possível obter o curso. Por favor, tente novamente mais tarde (500)",e);
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
    
        public List<Curso> cursoAll()
            throws SQLException, Exception {

        String sql = "SELECT * FROM CURSO ";

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
            List<Curso> cursos = new ArrayList<Curso>();

            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD

                Curso curso = new Curso();
                curso.setIdCurso(result.getLong("idCurso"));
                curso.setNome(result.getString("nome"));
                curso.setDescricao(result.getString("descricao"));
                curso.setValor(result.getFloat("valor"));
                curso.setTipo(result.getString("tipo"));
                curso.setStatus(result.getInt("status"));
                cursos.add(curso);
            }
            //Retorna o resultado
            return cursos;
        } catch (Exception e){
            throw new Exception("Não foi possível obter o curso. Por favor, tente novamente mais tarde (500)",e);
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
    }


    @Override
    public void excluir(long id, int status) 
                 throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE CURSO SET STATUS = ? "
                + "WHERE (idCurso=?)";
        
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