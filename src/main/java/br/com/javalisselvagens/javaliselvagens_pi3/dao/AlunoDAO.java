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
import br.com.javalisselvagens.javaliselvagens_pi3.model.Aluno;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Endereco;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Responsavel;
import java.sql.Date;

/**
 *
 * @author Rogerio Sobrinho
 */
public class AlunoDAO implements IServico<Aluno> {
    
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
    
    //Insere aluno
    @Override
    public void cadastrar(Aluno aluno)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO ALUNO (DT_CADASTRO, NOME, DATANASCIMENTO, SEXO, CPF, RG, ORGAOEMISSOR, ESTADOEMISSOR, TELEFONE, CELULAR, EMAIL, ATIVO) "
                + "VALUES (NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
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
            //preparedStatement.setDate(1, produto.getDatahora());
            preparedStatement.setString (1, aluno.getNome());
            preparedStatement.setDate(2, (Date) aluno.getDataNascimento());
            preparedStatement.setString(3, aluno.getSexo());
            preparedStatement.setString(4, aluno.getCpf());
            preparedStatement.setString(5, aluno.getRg());
            preparedStatement.setString(6, aluno.getOrgao_emissor());
            preparedStatement.setString(7, aluno.getEstado_emissor());
            preparedStatement.setString(8, aluno.getTelefone());
            preparedStatement.setString(9, aluno.getCelular());
            preparedStatement.setString(10, aluno.getEmail());
            preparedStatement.setInt(11, aluno.getAtivo());
            //Executa o comando no banco de dados
            preparedStatement.execute();
            
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            long id = 0;
            if(resultado.next()){
                id = resultado.getLong(1);
                insereEndereco(aluno.getEndereco(), id);
            
                if(aluno.getResponsavel() != null)
                    insereResponsavel(aluno.getResponsavel(),preparedStatement.getGeneratedKeys().getLong(1));
            }
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
    
    //atualizar aluno
    @Override
    public void atualizar(Aluno aluno)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE ALUNO SET NOME = ?, DATANASCIMENTO = ?, SEXO = ?, CPF = ?, RG = ?, ORGAOEMISSOR = ?, ESTADOEMISSOR = ?, TELEFONE = ?, CELULAR = ?, EMAIL = ?, ATIVO = ? "
                + "WHERE (ID = ?)";
        
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
            preparedStatement.setString (1, aluno.getNome());
            preparedStatement.setDate(2, (Date) aluno.getDataNascimento());
            preparedStatement.setString(3, aluno.getSexo());
            preparedStatement.setString(4, aluno.getCpf());
            preparedStatement.setString(5, aluno.getRg());
            preparedStatement.setString(6, aluno.getOrgao_emissor());
            preparedStatement.setString(7, aluno.getEstado_emissor());
            preparedStatement.setString(8, aluno.getTelefone());
            preparedStatement.setString(9, aluno.getCelular());
            preparedStatement.setString(10, aluno.getEmail());
            preparedStatement.setInt(11, aluno.getAtivo());
            preparedStatement.setLong(12, aluno.getId());
            //Executa o comando no banco de dados
            preparedStatement.execute();
            
            atualizaEndereco(aluno.getEndereco(), aluno.getId());
            
            if(aluno.getResponsavel() != null)
                atualizaResponsavel(aluno.getResponsavel(), aluno.getId());
            
            
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
    
    
    // deletar aluno
    @Override
     public void excluir(long id, int status)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE ALUNO SET ATIVO = ? "
                + "WHERE (ID = ?)";
        
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
    
    //Obter aluno
    @Override
    public Aluno obter(long id)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM ALUNO INNER JOIN ENDERECO ON ALUNO.ID = ENDERECO.IDALUNO LEFT OUTER JOIN RESPONSAVEL ON ALUNO.ID = RESPONSAVEL.IDALUNO "
                + " WHERE (ALUNO.ID=?)";

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
            preparedStatement.setLong(1, id);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Aluno aluno = new Aluno();
                aluno.setId(result.getLong("ID"));
                aluno.setNome(result.getString("NOME"));
                aluno.setDataNascimento(result.getDate("DATANASCIMENTO"));
                aluno.setSexo(result.getString("SEXO"));
                aluno.setCpf(result.getString("CPF"));
                aluno.setRg(result.getString("RG"));
                aluno.setOrgao_emissor(result.getString("ORGAOEMISSOR"));
                aluno.setEstado_emissor(result.getString("ESTADOEMISSOR"));
                aluno.setTelefone(result.getString("TELEFONE"));
                aluno.setCelular(result.getString("CELULAR"));
                aluno.setEmail(result.getString("EMAIL"));
                aluno.setAtivo(result.getInt("ATIVO"));
                
                if(result.getString("NOME") != null){
                    Responsavel responsavel = new Responsavel();
                    responsavel.setNome(result.getString("NOME"));
                    responsavel.setDataNascimento(result.getDate("DATANASCIMENTO"));
                    responsavel.setSexo(result.getString("SEXO"));
                    responsavel.setCpf(result.getString("CPF"));
                    responsavel.setRg(result.getString("RG"));
                    responsavel.setOrgao_emissor(result.getString("ORGAOEMISSOR"));
                    responsavel.setEstado_emissor(result.getString("ESTADOEMISSOR"));
                    responsavel.setTelefone(result.getString("TELEFONE"));
                    responsavel.setCelular(result.getString("CELULAR"));

                    aluno.setResponsavel(responsavel);
                }
                
                // RUA =?, NUMERO =?, COMPLEMENTO =?, BAIRRO =?, ESTADO =?
                Endereco endereco = new Endereco();
                endereco.setRua(result.getString("RUA"));
                endereco.setNumero(result.getString("NUMERO"));
                endereco.setComplemento(result.getString("COMPLEMENTO"));
                endereco.setBairro(result.getString("BAIRRO"));
                endereco.setEstado(result.getString("ESTADO"));
              
                aluno.setEndereco(endereco);
                
                //Retorna o resultado
                return aluno;
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
    
    
    // Obter lista de alunos
    public static List<Aluno> Lista(String consulta)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM ALUNO INNER JOIN ENDERECO ON ALUNO.ID = ENDERECO.IDALUNO LEFT OUTER JOIN RESPONSAVEL ON ALUNO.ID = RESPONSAVEL.IDALUNO "
                + " WHERE (ALUNO.NOME = ? OR ALUNO.CPF = ?)";

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
            List<Aluno> alunos = new ArrayList<Aluno>();
            
            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Aluno aluno = new Aluno();
                aluno.setId(result.getLong("ID"));
                aluno.setNome(result.getString("NOME"));
                aluno.setDataNascimento(result.getDate("DATANASCIMENTO"));
                aluno.setSexo(result.getString("SEXO"));
                aluno.setCpf(result.getString("CPF"));
                aluno.setRg(result.getString("RG"));
                aluno.setOrgao_emissor(result.getString("ORGAOEMISSOR"));
                aluno.setEstado_emissor(result.getString("ESTADOEMISSOR"));
                aluno.setTelefone(result.getString("TELEFONE"));
                aluno.setCelular(result.getString("CELULAR"));
                aluno.setEmail(result.getString("EMAIL"));
                aluno.setAtivo(result.getInt("ATIVO"));
                
                if(result.getString("NOME") != null){
                    Responsavel responsavel = new Responsavel();
                    responsavel.setNome(result.getString("NOME"));
                    responsavel.setDataNascimento(result.getDate("DATANASCIMENTO"));
                    responsavel.setSexo(result.getString("SEXO"));
                    responsavel.setCpf(result.getString("CPF"));
                    responsavel.setRg(result.getString("RG"));
                    responsavel.setOrgao_emissor(result.getString("ORGAOEMISSOR"));
                    responsavel.setEstado_emissor(result.getString("ESTADOEMISSOR"));
                    responsavel.setTelefone(result.getString("TELEFONE"));
                    responsavel.setCelular(result.getString("CELULAR"));

                    aluno.setResponsavel(responsavel);
                }
                
                Endereco endereco = new Endereco();
                endereco.setRua(result.getString("RUA"));
                endereco.setNumero(result.getString("NUMERO"));
                endereco.setComplemento(result.getString("COMPLEMENTO"));
                endereco.setBairro(result.getString("BAIRRO"));
                endereco.setEstado(result.getString("ESTADO"));
              
                aluno.setEndereco(endereco);
                alunos.add(aluno);
            }
            
            return alunos;
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
    
    
    // Obter lista de alunos
    public static List<Aluno> Lista(String nome, String cpf)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM ALUNO INNER JOIN ENDERECO ON ALUNO.ID = ENDERECO.IDALUNO LEFT OUTER JOIN RESPONSAVEL ON ALUNO.ID = RESPONSAVEL.IDALUNO "
                + " WHERE (ALUNO.NOME = ? OR ALUNO.CPF = ?)";

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
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            List<Aluno> alunos = new ArrayList<Aluno>();
            
            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD
                
                Aluno aluno = new Aluno();
                aluno.setId(result.getLong("ID"));
                aluno.setNome(result.getString("NOME"));
                aluno.setDataNascimento(result.getDate("DATANASCIMENTO"));
                aluno.setSexo(result.getString("SEXO"));
                aluno.setCpf(result.getString("CPF"));
                aluno.setRg(result.getString("RG"));
                aluno.setOrgao_emissor(result.getString("ORGAOEMISSOR"));
                aluno.setEstado_emissor(result.getString("ESTADOEMISSOR"));
                aluno.setTelefone(result.getString("TELEFONE"));
                aluno.setCelular(result.getString("CELULAR"));
                aluno.setEmail(result.getString("EMAIL"));
                aluno.setAtivo(result.getInt("ATIVO"));
                
                if(result.getString("NOME") != null){
                    Responsavel responsavel = new Responsavel();
                    responsavel.setNome(result.getString("NOME"));
                    responsavel.setDataNascimento(result.getDate("DATANASCIMENTO"));
                    responsavel.setSexo(result.getString("SEXO"));
                    responsavel.setCpf(result.getString("CPF"));
                    responsavel.setRg(result.getString("RG"));
                    responsavel.setOrgao_emissor(result.getString("ORGAOEMISSOR"));
                    responsavel.setEstado_emissor(result.getString("ESTADOEMISSOR"));
                    responsavel.setTelefone(result.getString("TELEFONE"));
                    responsavel.setCelular(result.getString("CELULAR"));

                    aluno.setResponsavel(responsavel);
                }
                
                Endereco endereco = new Endereco();
                endereco.setRua(result.getString("RUA"));
                endereco.setNumero(result.getString("NUMERO"));
                endereco.setComplemento(result.getString("COMPLEMENTO"));
                endereco.setBairro(result.getString("BAIRRO"));
                endereco.setEstado(result.getString("ESTADO"));
              
                aluno.setEndereco(endereco);
                alunos.add(aluno);
            }
            
            return alunos;
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
    
    private static void insereEndereco(Endereco endereco, long idAluno)
             throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO ENDERECO (RUA, NUMERO, COMPLEMENTO, BAIRRO, ESTADO, IDALUNO) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
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
            //preparedStatement.setDate(1, produto.getDatahora());
            preparedStatement.setString (1, endereco.getRua());
            preparedStatement.setString(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getComplemento());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getEstado());
            preparedStatement.setLong(6, idAluno);
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

    private static void insereResponsavel(Responsavel responsavel, long idAluno)         
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO RESPONSAVEL (NOME, DATANASCIMENTO, SEXO, CPF, RG, ORGAOEMISSOR, ESTADOEMISSOR, TELEFONE, CELULAR, IDALUNO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
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
            preparedStatement.setString (1, responsavel.getNome());
            preparedStatement.setDate(2, (Date) responsavel.getDataNascimento());
            preparedStatement.setString(3, responsavel.getSexo());
            preparedStatement.setString(4, responsavel.getCpf());
            preparedStatement.setString(5, responsavel.getRg());
            preparedStatement.setString(6, responsavel.getOrgao_emissor());
            preparedStatement.setString(7, responsavel.getEstado_emissor());
            preparedStatement.setString(8, responsavel.getTelefone());
            preparedStatement.setString(9, responsavel.getCelular());
            preparedStatement.setLong(10, idAluno);
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
    
        private static void atualizaEndereco(Endereco endereco, long idAluno)
             throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE ENDERECO SET RUA =?, NUMERO =?, COMPLEMENTO =?, BAIRRO =?, ESTADO =? "
                + "WHERE (IDALUNO = ?)";
        
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
            //preparedStatement.setDate(1, produto.getDatahora());
            preparedStatement.setString (1, endereco.getRua());
            preparedStatement.setString(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getComplemento());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getEstado());
            preparedStatement.setLong(6, idAluno);
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

    private static void atualizaResponsavel(Responsavel responsavel, long idAluno)         
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "UPDATE RESPONSAVEL NOME =?, DATANASCIMENTO =?, SEXO =?, CPF =?, RG =?, ORGAOEMISSOR =?, ESTADOEMISSOR =?, TELEFONE =?, CELULAR =? "
                + "WHERE (IDALUNO = ?)";
        
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
            preparedStatement.setString (1, responsavel.getNome());
            preparedStatement.setDate(2, (Date) responsavel.getDataNascimento());
            preparedStatement.setString(3, responsavel.getSexo());
            preparedStatement.setString(4, responsavel.getCpf());
            preparedStatement.setString(5, responsavel.getRg());
            preparedStatement.setString(6, responsavel.getOrgao_emissor());
            preparedStatement.setString(7, responsavel.getEstado_emissor());
            preparedStatement.setString(8, responsavel.getTelefone());
            preparedStatement.setString(9, responsavel.getCelular());
            preparedStatement.setLong(10, idAluno);
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

    @Override
    public List<Aluno> procurarNome(String nome) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
