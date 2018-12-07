/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Curso;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.CursoDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Curso;
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
@WebServlet(name = "CursoPesquisa", urlPatterns = {"/curso/consulta"})
public class CursoPesquisa extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/curso/consulta.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String tipo = request.getParameter("tipo");
     
   
        try {
            List<Curso> cursos;
            CursoDAO cursoDAO = new CursoDAO();
            if(!nome.isEmpty())
                cursos = cursoDAO.procurarNome(nome);
            else
                cursos = cursoDAO.procurarNome(tipo);
            
            if(cursos.size() > 0)
                request.setAttribute("cursos", cursos);
            else
                request.setAttribute("mensagem", "Nenhum curso foi localizado");
            
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
        } 
        
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/curso/consulta.jsp");
        dispatcher.forward(request, response);
        
    }

}
