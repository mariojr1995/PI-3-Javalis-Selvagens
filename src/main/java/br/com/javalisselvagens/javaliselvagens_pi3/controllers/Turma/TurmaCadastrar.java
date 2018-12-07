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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "TurmaCadastrar", urlPatterns = {"/turma/cadastrar"})
public class TurmaCadastrar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        CursoDAO cursoDAO = new CursoDAO();
        try {
            List<Curso> cursos = cursoDAO.cursoAll();
            if(cursos.size() > 0){
                request.setAttribute("cursos", cursos);
            }
        } catch (Exception ex) {
            Logger.getLogger(TurmaCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("nome", "");
        request.setAttribute("periodo", "");
        request.setAttribute("dtinicio", "");
        request.setAttribute("dtfim", "");
        request.setAttribute("capacidade", "");
        request.setAttribute("habilitado", 1);
        request.setAttribute("url_default", request.getContextPath() + "/turma/cadastrar");
        request.setAttribute("btn", "Cadastrar");
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/turma/cadastro.jsp");
        dispatcher.forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String periodo = request.getParameter("periodo");
        String dtinicio = request.getParameter("dtinicio");
        String dtfim = request.getParameter("dtfim");
        String capacidade = request.getParameter("capacidade");
        String idcurso = request.getParameter("curso");
        String hab = request.getParameter("habilitado");
        int status = "on".equals(hab) ? 1 : 0;
        
        try {
            
            Turma turma = new Turma();
            turma.setNomeTurma(nome);
            turma.setPeriodo(periodo);
            
            //Data Inicio
            Date dt;
            java.sql.Date sqldtinicio;          
            
            dt = new SimpleDateFormat("yyyy-MM-dd").parse(dtinicio);
            sqldtinicio = new java.sql.Date(dt.getTime()); 
            turma.setDataInicio(sqldtinicio);
            
            //Data Fim
            java.sql.Date sqldtfim;
            dt = new SimpleDateFormat("yyyy-MM-dd").parse(dtfim);
            sqldtfim = new java.sql.Date(dt.getTime()); 
            turma.setDataFim(sqldtfim);

            turma.setCapacidade(Integer.parseInt(capacidade));
            turma.setStatus(status);
            turma.setIdCurso(Long.parseLong(idcurso));
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.cadastrar(turma);
            request.setAttribute("mensagem", "Turma cadastrada com sucesso!");
            
        } catch (ParseException ex) {
           // Logger.getLogger(AlunoInclusaoServlets.class.getName()).log(Level.SEVERE, null, ex);
           request.setAttribute("mensagem", ex.toString());
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            //Logger.getLogger(AlunoInclusaoServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", ex.toString());
            System.out.println(ex.getMessage());
        }
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/turma/cadastro.jsp");
        dispatcher.forward(request, response);
        
    }
}
