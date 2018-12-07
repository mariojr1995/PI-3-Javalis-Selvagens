/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.dao;

import br.com.javalisselvagens.javaliselvagens_pi3.model.Matricula;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rubens.huneke
 */
public class MatriculaDAO implements IServico<Matricula> {
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
    
    //Ininsere matricula
    @Override
    public  void cadastrar(Matricula matricula)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO MATRICULA (IDALUNO, IDCURSO, IDTURMA, FORMAPGTO, DATAMATRICULA, OBS, STATUS) "
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
            preparedStatement.setLong(1, matricula.getAluno().getId());
            preparedStatement.setLong(2, matricula.getCurso().getIdCurso());
            preparedStatement.setLong(3, matricula.getTurma().getIdTurma());
            preparedStatement.setString(4, matricula.getFormaPgto());
            preparedStatement.setDate(5, (Date) matricula.getDataMatricula());
            preparedStatement.setString(6, matricula.getObs());
            preparedStatement.setInt(7, matricula.getStatus());
            
            //Executa o comando no banco de dados
            preparedStatement.execute();
        } catch (Exception e){
            
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
    
    //atualizar matricula
    @Override
    public void atualizar(Matricula matricula)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE MATRICULA SET "
                + "FORMAPAGTO = ?, OBS=?, STATUS = ?"
                + "WHERE (IDMATRICULA = ?)";
        
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
            preparedStatement.setString(1, matricula.getFormaPgto());
            preparedStatement.setString(2, matricula.getObs());
            preparedStatement.setInt(3, matricula.getStatus());
            preparedStatement.setLong(4, matricula.getIdMatricula());
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
    
    
    // deletar matricula
    @Override
     public void excluir(long id, int status)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE MATRICULA SET STATUS = ? "
                + "WHERE (IDMATRICULA = ?)";
        
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
    
    //Obter matricula
     @Override
    public  Matricula obter(long idMatricula)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM MATRICULA "
                + " WHERE (IDMATRICULA=? or IDALUNO =? or IDCURSO=? OR IdTURMA =?)";

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
            preparedStatement.setLong(1, idMatricula);
            preparedStatement.setLong(2, idMatricula);
            preparedStatement.setLong(3, idMatricula);
            preparedStatement.setLong(4, idMatricula);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(result.getLong("IDMATRICULA"));
                
                AlunoDAO alunoDAO = new AlunoDAO();
                matricula.setAluno(alunoDAO.obter(result.getLong("IDALUNO")));
                
                CursoDAO cursoDAO = new CursoDAO();
                matricula.setCurso(cursoDAO.obter(result.getLong("IDCURSO")));
                
                TurmaDAO turmaDAO = new TurmaDAO();
                matricula.setTurma(turmaDAO.obter(result.getLong("IDTURMA")));
                
                matricula.setFormaPgto(result.getString("FORMAPAGTO"));
                matricula.setDataMatricula(result.getDate("DATAMATRICULA"));
                matricula.setObs(result.getString("OBS"));
                matricula.setStatus(result.getInt("STATUS"));
                                                
                //Retorna o resultado
                return matricula;
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

    @Override
    public List<Matricula> procurarNome(String nome) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}