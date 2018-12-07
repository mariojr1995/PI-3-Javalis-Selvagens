/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Matricula;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.MatriculaDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Matricula;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rogerio Sobrinho
 */
@WebServlet(name = "MatriculaCadastrar", urlPatterns = {"/Matricula/Cadastrar"})
public class MatriculaCadastrar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("Aluno", "");
        request.setAttribute("Curso", "");
        request.setAttribute("formaPagamento", "");
        request.setAttribute("url_default", request.getContextPath() + "/Matricula/Cadastrar");
        request.setAttribute("btn", "Cadastrar");
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/Matricula/cadastro/cadastro.jsp");
        dispatcher.forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        
        String nome = request.getParameter("nome");
        String nomefantasia = request.getParameter("nomefantasia");
        String cnpj = request.getParameter("cnpj");
        String hab = request.getParameter("habilitado");
        int habilitado = "on".equals(hab) ? 1 : 0;

        try {
            
        /*    Unidade unidade = new Unidade();
            unidade.setCNPJ(cnpj);
            unidade.setNomeFantasia(nomefantasia);
            unidade.setRazaoSocial(nome);
            unidade.setStatus(habilitado);
            
            UnidadeDAO unidadeDAO = new UnidadeDAO();
            unidadeDAO.atualizar(unidade);
            request.setAttribute("mensagem", "Cadastro atualizado com sucesso!");
            */
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", "Não foi possível alterar o cadastro - Exception: " + ex.toString());
        } 
        
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/Matricula/consulta.jsp");
        dispatcher.forward(request, response);
        
    }

}
