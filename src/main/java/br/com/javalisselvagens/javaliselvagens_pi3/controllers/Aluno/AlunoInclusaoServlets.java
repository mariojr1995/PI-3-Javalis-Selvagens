/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Aluno;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.AlunoDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Aluno;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Endereco;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Responsavel;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author Rogerio Sobrinho
 */
@WebServlet(name = "AlunoServlet", urlPatterns = {"/aluno/cadastro"})
public class AlunoInclusaoServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("mensagem", "");
        request.setAttribute("nome", "");
        request.setAttribute("datanascimento", "");
        request.setAttribute("sexo", "M");
        request.setAttribute("cpf", "");
        request.setAttribute("rg", "");
        request.setAttribute("orgaoemissor", "");
        request.setAttribute("estadoemissor", "");
        request.setAttribute("telefone", "");
        request.setAttribute("celular", "");
        request.setAttribute("email", "");
        request.setAttribute("logradouro", "");
        request.setAttribute("numero", "");
        request.setAttribute("complemento", "");
        request.setAttribute("bairro", "");
        request.setAttribute("cidade", "");
        request.setAttribute("estado", "SP");
        request.setAttribute("habilitado", 1);
        request.setAttribute("responsavel_nome", "");
        request.setAttribute("responsavel_datanascimento", "");
        request.setAttribute("responsavel_sexo", "");
        request.setAttribute("responsavel_cpf", "");
        request.setAttribute("responsavel_rg", "");
        request.setAttribute("responsavel_orgaoemissor", "");
        request.setAttribute("responsavel_estadoemissor", "");
        request.setAttribute("responsavel_telefone", "");
        request.setAttribute("responsavel_celular", "");
        request.setAttribute("url_default", request.getContextPath() + "/aluno/cadastro");
        request.setAttribute("btn", "Cadastrar");

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/Cadastro/Aluno/formulario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String datanascimento = request.getParameter("datanascimento");
        String sexo = request.getParameter("sexo");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String orgaoemissor = request.getParameter("orgaoemissor");
        String estadoemissor = request.getParameter("estadoemissor");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String hab = request.getParameter("habilitado");
        String responsavel_nome = request.getParameter("responsavel_nome");
        String responsavel_datanascimento = request.getParameter("responsavel_datanascimento");
        String responsavel_sexo = request.getParameter("responsavel_sexo");
        String responsavel_cpf = request.getParameter("responsavel_cpf");
        String responsavel_rg = request.getParameter("responsavel_rg");
        String responsavel_orgaoemissor = request.getParameter("responsavel_orgaoemissor");
        String responsavel_estadoemissor = request.getParameter("responsavel_estadoemissor");
        String responsavel_telefone = request.getParameter("responsavel_telefone");
        String responsavel_celular = request.getParameter("responsavel_celular");
        int habilitado = "on".equals(hab) ? 1 : 0;

        Date dt;
        java.sql.Date sqlDateNascimento;
        try {

            dt = new SimpleDateFormat("yyyy-mm-dd").parse(datanascimento);
            sqlDateNascimento = new java.sql.Date(dt.getTime());

            Aluno aluno = new Aluno(nome, sqlDateNascimento, sexo, cpf, rg, orgaoemissor, estadoemissor, telefone, celular, email, habilitado);
            Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, estado);
            aluno.setEndereco(endereco);

            if (responsavel_nome != null && !responsavel_nome.isEmpty()) {
                Date dt2;
                dt2 = new SimpleDateFormat("yyyy-mm-dd").parse(responsavel_datanascimento);
                sqlDateNascimento = new java.sql.Date(dt2.getTime());
                Responsavel responsavel = new Responsavel(responsavel_nome, sqlDateNascimento, responsavel_sexo, responsavel_cpf, responsavel_rg,
                        responsavel_orgaoemissor, responsavel_estadoemissor, responsavel_telefone, responsavel_celular);
                aluno.setResponsavel(responsavel);
            }
            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.cadastrar(aluno);
            request.setAttribute("mensagem", "Aluno cadastrado com sucesso!");

        } catch (ParseException ex) {
            // Logger.getLogger(AlunoInclusaoServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
        } catch (Exception ex) {
            //Logger.getLogger(AlunoInclusaoServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
        }

        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/Cadastro/Aluno/formulario.jsp");
        dispatcher.forward(request, response);

    }
    
}

