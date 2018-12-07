/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Aluno;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.AlunoDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Aluno;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "AlunoPesquisaServlets", urlPatterns = {"/aluno/consulta"})
public class AlunoPesquisaServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/Cadastro/Aluno/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
     
   
        try {
            List<Aluno> alunos = new ArrayList<Aluno>();
            alunos = AlunoDAO.Lista(nome, cpf);
            
            if(alunos.size() > 0)
                request.setAttribute("alunos", alunos);
            else
                request.setAttribute("mensagem", "Nenhum aluno foi localizado");
            
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
        } 
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/Cadastro/Aluno/lista.jsp");
        dispatcher.forward(request, response);
        
    }
}