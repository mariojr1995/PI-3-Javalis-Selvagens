/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Curso;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.CursoDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Curso;
import java.io.IOException;
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
@WebServlet(name = "CursoAlterar", urlPatterns = {"/curso/alterar"})
public class CursoAlterar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String cursoID = request.getParameter("ID");
        Long id = Long.parseLong(cursoID);
        
        try {
            CursoDAO cursoDAO = new CursoDAO();
            Curso aluno = cursoDAO.obter(id);
            
            request.setAttribute("nome", aluno.getNome());
            request.setAttribute("descricao", aluno.getDescricao());
            request.setAttribute("valor", aluno.getValor());
            request.setAttribute("tipo", aluno.getTipo());
            request.setAttribute("id", aluno.getIdCurso());
            request.setAttribute("habilitado", aluno.getStatus());
            request.setAttribute("url_default", request.getContextPath() + "/curso/alterar");
            request.setAttribute("btn", "Atualizar");
        
        } catch (Exception ex) {
            //Logger.getLogger(AlunoAlterarServlets.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/curso/cadastro.jsp");
            dispatcher.forward(request, response);
        }
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/curso/cadastro.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String valor = request.getParameter("valor");
        String tipo = request.getParameter("tipo");
        String hab = request.getParameter("habilitado");
        int status = "on".equals(hab) ? 1 : 0;
     
        try {
            
            Curso curso = new Curso();
            curso.setIdCurso(Long.parseLong(id));
            curso.setNome(nome);
            curso.setDescricao(descricao);
            curso.setValor(Float.parseFloat(valor));
            curso.setTipo(tipo);
            curso.setStatus(status);
            
            CursoDAO cursoDAO = new CursoDAO();
            cursoDAO.atualizar(curso);
            request.setAttribute("mensagem", "Cadastro atualizado com sucesso!");
            
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", "Não foi possível alterar o cadastro - Exception: " + ex.toString());
        } 
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/curso/cadastro.jsp");
        dispatcher.forward(request, response);
        
    }
}
