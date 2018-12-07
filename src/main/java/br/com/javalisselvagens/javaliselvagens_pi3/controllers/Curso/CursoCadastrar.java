/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Curso;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.CursoDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Curso;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
@WebServlet(name = "CursoCadastrar", urlPatterns = {"/curso/cadastrar"})
public class CursoCadastrar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("nome", "");
        request.setAttribute("descricao", "");
        request.setAttribute("valor", "");
        request.setAttribute("tipo", "");
        request.setAttribute("habilitado", 1);
        request.setAttribute("url_default", request.getContextPath() + "/curso/cadastrar");
        request.setAttribute("btn", "Cadastrar");    

        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/curso/cadastro.jsp");
        dispatcher.forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String valor = request.getParameter("valor");
        String tipo = request.getParameter("tipo");
        String hab = request.getParameter("habilitado");
        int status = "on".equals(hab) ? 1 : 0;
        
        try {
            
            Curso curso = new Curso();
            curso.setNome(nome);
            curso.setDescricao(descricao);
            curso.setValor(Float.parseFloat(valor));
            curso.setTipo(tipo);
            curso.setStatus(status);
            
            CursoDAO cursoDAO = new CursoDAO();
            cursoDAO.cadastrar(curso);
            request.setAttribute("mensagem", "Curso cadastrado com sucesso!");
            
        } catch (ParseException ex) {
           // Logger.getLogger(AlunoInclusaoServlets.class.getName()).log(Level.SEVERE, null, ex);
           request.setAttribute("mensagem", ex.toString());
        } catch (Exception ex) {
            //Logger.getLogger(AlunoInclusaoServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
        }
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/curso/cadastro.jsp");
        dispatcher.forward(request, response);
        
    }

}
