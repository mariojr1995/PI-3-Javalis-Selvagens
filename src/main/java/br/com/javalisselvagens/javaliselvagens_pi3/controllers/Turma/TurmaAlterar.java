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
@WebServlet(name = "TurmaAlterar", urlPatterns = {"/turma/alterar"})
public class TurmaAlterar extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
             
        String turmaID = request.getParameter("ID");
        Long id = Long.parseLong(turmaID);
        
         CursoDAO cursoDAO = new CursoDAO();
        try {
            List<Curso> cursos = cursoDAO.cursoAll();
            if(cursos.size() > 0){
                request.setAttribute("cursos", cursos);
            }
        } catch (Exception ex) {
            Logger.getLogger(TurmaCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            Turma turma = turmaDAO.obter(id);
            
            request.setAttribute("id", id);
            request.setAttribute("nome", turma.getNomeTurma());
            request.setAttribute("periodo", turma.getPeriodo());
            request.setAttribute("dtinicio", turma.getDataInicio());
            request.setAttribute("dtfim", turma.getDataFim());
            request.setAttribute("capacidade", turma.getCapacidade());
            request.setAttribute("habilitado", turma.getStatus());
            request.setAttribute("cursos", turma.getIdCurso());
            request.setAttribute("url_default", request.getContextPath() + "/turma/alterar");
            request.setAttribute("btn", "Atualizar");
        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(AlunoAlterarServlets.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/turma/cadastro.jsp");
            dispatcher.forward(request, response);
        }
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/turma/cadastro.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
               
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String periodo = request.getParameter("periodo");
        String dtinicio = request.getParameter("dtinicio");
        String dtfim = request.getParameter("dtfim");
        String capacidade = request.getParameter("capacidade");
        String hab = request.getParameter("habilitado");
        int status = "on".equals(hab) ? 1 : 0;
     
        try {
            
            Turma turma = new Turma();
            turma.setIdCurso(Long.parseLong(id));
            turma.setNomeTurma(nome);
            turma.setPeriodo(periodo);
            
            Date dt;
            java.sql.Date sqldtinicio;
        
            dt = new SimpleDateFormat("yyyy-mm-dd").parse(dtinicio);
            sqldtinicio = new java.sql.Date(dt.getTime()); 
            turma.setDataInicio(sqldtinicio);
            
            java.sql.Date sqldtfim;
            dt = new SimpleDateFormat("yyyy-mm-dd").parse(dtfim);
            sqldtfim = new java.sql.Date(dt.getTime()); 
            turma.setDataFim(sqldtfim);
            
            turma.setCapacidade(Integer.parseInt(capacidade));
            turma.setStatus(status);
            
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.atualizar(turma);
            request.setAttribute("mensagem", "Cadastro atualizado com sucesso!");
            
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", "Não foi possível alterar o cadastro - Exception: " + ex.toString());
        } 
        
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/turma/consulta.jsp");
        dispatcher.forward(request, response);
        
    }
}
