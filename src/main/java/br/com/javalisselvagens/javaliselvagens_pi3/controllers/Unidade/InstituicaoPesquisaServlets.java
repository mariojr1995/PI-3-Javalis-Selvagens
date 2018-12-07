/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Unidade;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.UnidadeDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Unidade;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "InstituicaoPesquisaServlets", urlPatterns = {"/instituicao/consulta"})
public class InstituicaoPesquisaServlets extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/Instituicao/consulta/consulta.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
           String nome = request.getParameter("nome");
           String nomefantasia = request.getParameter("nomefantasia");
     
   
        try {
            List<Unidade> unidades;
            UnidadeDAO unidadeDAO = new UnidadeDAO();
            if(!nome.isEmpty())
                unidades = unidadeDAO.procurarNome(nome);
            else
                unidades = unidadeDAO.procurarNome(nomefantasia);
            
            if(unidades.size() > 0)
                request.setAttribute("unidades", unidades);
            else
                request.setAttribute("mensagem", "Nenhum curso foi localizado");
            
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
        } 

        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/Instituicao/consulta/consulta.jsp");
        dispatcher.forward(request, response);
        
    }
}
