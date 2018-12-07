/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Turma;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.CursoDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.dao.TurmaDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Curso;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Turma;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "TurmaPesquisa", urlPatterns = {"/turma/consulta"})
public class TurmaPesquisa extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/turma/consulta.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
     
        
        try {
            List<Turma> turmas;
            TurmaDAO turmaDAO = new TurmaDAO();
            turmas = turmaDAO.turmaAll();
        
            if(turmas.size() > 0)
                request.setAttribute("turmas", turmas);
            else
                request.setAttribute("mensagem", "Nenhum curso foi localizado");
            
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
        } 
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/turma/consulta.jsp");
        dispatcher.forward(request, response);
        
    }
}
