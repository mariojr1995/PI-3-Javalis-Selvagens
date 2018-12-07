/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javalisselvagens.javaliselvagens_pi3.controllers.Unidade;

import br.com.javalisselvagens.javaliselvagens_pi3.dao.UnidadeDAO;
import br.com.javalisselvagens.javaliselvagens_pi3.model.Unidade;
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
@WebServlet(name = "InstituicaoAlterarServlets", urlPatterns = {"/instituicao/alterar"})
public class InstituicaoAlterarServlets extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
             String cursoID = request.getParameter("ID");
        Long id = Long.parseLong(cursoID);
        
        try {
            UnidadeDAO unidadeDAO = new UnidadeDAO();
            Unidade unidade = unidadeDAO.obter(id);
            
            request.setAttribute("id", id);
            request.setAttribute("nome", unidade.getRazaoSocial());
            request.setAttribute("nomefantasia", unidade.getNomeFantasia());
            request.setAttribute("cnpj", unidade.getCNPJ());
            request.setAttribute("habilitado", unidade.getStatus());
            request.setAttribute("url_default", request.getContextPath() + "/instituicao/alterar");
            request.setAttribute("btn", "Atualizar");
        
        } catch (Exception ex) {
            RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/consulta/consulta.jsp");
            dispatcher.forward(request, response);
        }
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/Instituicao/cadastro/cadastro.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String nomefantasia = request.getParameter("nomefantasia");
        String cnpj = request.getParameter("cnpj");
        String hab = request.getParameter("habilitado");
        int status = "on".equals(hab) ? 1 : 0;
     
        try {
            
            Unidade unidade = new Unidade();
            unidade.setIdUnidade(Long.parseLong(id));
            unidade.setNomeFantasia(nomefantasia);
            unidade.setRazaoSocial(nome);
            unidade.setCNPJ(cnpj);
            unidade.setStatus(status);
            
            UnidadeDAO unidadeDAO = new UnidadeDAO();
            unidadeDAO.atualizar(unidade);
            request.setAttribute("mensagem", "Cadastro atualizado com sucesso!");
            
        } catch (Exception ex) {
            //Logger.getLogger(AlunoPesquisaServlets.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensagem", "Não foi possível alterar o cadastro - Exception: " + ex.toString());
        } 

        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "/WEB-INF/Instituicao/consulta/consulta.jsp");
        dispatcher.forward(request, response);
        
    }

}
