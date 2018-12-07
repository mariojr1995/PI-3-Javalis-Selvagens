<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
    <jsp:include page="/WEB-INF/page/breadcrumb.jsp">
        <jsp:param name="title1" value="Turma"/>
        <jsp:param name="title2" value="Cadastro"/>
    </jsp:include>
    <h1 class="text-center pb-3 pt-3">Cadastro de Turmas</h1>
    <form class="form-horizontal pb-4" method="post" action='${url_default}'>
    <fieldset>
    <c:if test="${not empty mensagem}">
        <div class="alert alert-success text-center" role="alert">
            <c:out value="${mensagem}" />
        </div>
    </c:if>
    <div class="alert alert-info text-center" role="alert">
        Os campos com <a href="#" class="alert-link">*</a> são de preenchimento obrigatório.
    </div>
    <div class="form-group row">
        <div class="col-md-6">
            <label class="control-label" for="nome">Nome*</label>  
            <div>
              <input id="nome" name="nome" value="${nome}" type="text" placeholder="Nome da Turma" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: Turma A</span>  
            </div>
        </div>
        <input id="userID" name="userID" value="${id}" type="text" class="form-control input-md" style='display: none;' >
        <div class="col-md-4">
            <label class="control-label" for="periodo">Período*</label>  
            <div>
              <input id="periodo" name="periodo" value="${periodo}" type="text" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: Noturno</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
      <div class="col-md-3">
        <label class="control-label" for="dtinicio">Data Inicio*</label>  
        <div>
          <input id="dtinicio" name="dtinicio" type="date" value="${dtinicio}" placeholder="Data de Inicio" class="form-control input-md" required="">
          <span class="help-block text-muted">Ex: 26/12/2018</span>  
        </div>
      </div>
      <div class="col-md-3">
        <label class="control-label" for="dtfim">Data Fim*</label>  
        <div>
          <input id="dtfim" name="dtfim" type="date" value="${dtfim}" placeholder="Data de Término" class="form-control input-md" required="">
          <span class="help-block text-muted">Ex: 28/12/2018</span>  
        </div>
      </div>
      <div class="col-md-3">
        <label class="control-label" for="capacidade">Capacidade*</label>  
        <div>
          <input id="capacidade" name="capacidade" type="text" value="${capacidade}" placeholder="Capacidade de Alunos" class="form-control input-md" required="">
          <span class="help-block text-muted">Ex: 50</span>  
        </div>
      </div>
    </div>
          
          <div class="form-group  row">
              <div class="col-md-3">
                   <label class="control-label" for="cursos">Curso</label>  
                     <div>
                        <select class="form-control input-md" id="curso" name="curso" data-actions-box="true">
                            <c:if test="${cursos != null}">
                                <c:forEach items="${cursos}" var="cur">
                                    <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>${cur.nome}</option>   
                                </c:forEach>
                             </c:if>
                        </select>
                        <span class="help-block text-muted">Ex: Inglês</span>  
                      </div>
              </div>
          </div>
            <div class="form-group row">
                <div class="col-md-2">
                    <label class="control-label" for="habilitado">Ativo</label>  
                    <input type="checkbox" id="habilitado" ${habilitado == 1 ? 'checked="checked"' : ''} name="habilitado" />
                </div>
            </div>
    <hr/>
    <div class="form-group row">
        <div class="col-md-5 ml-auto">
            <button class="btn btn-outline-secondary"><i class="fas fa-ban mr-2"></i>Cancelar</button>
            <button class="btn btn-outline-primary"><i class="fas fa-save mr-2"></i><c:out value="${btn}"/></button>
        </div>
    </div>
    <hr/>
    </fieldset>
    </form>
        
    </div>