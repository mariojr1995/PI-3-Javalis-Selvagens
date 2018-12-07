<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
    <jsp:include page="/WEB-INF/page/breadcrumb.jsp">
        <jsp:param name="title1" value="Unidade"/>
        <jsp:param name="title2" value="Cadastro"/>
    </jsp:include>
    <h1 class="text-center pb-3 pt-3">Cadastro de Unidades</h1>
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
            <label class="control-label" for="nome">Razão Social*</label>  
            <div>
              <input id="nome" name="nome" maxlength="120" value="${nome}" type="text" placeholder="Nome da Unidade" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: Tads school 1</span>  
            </div>
        </div>
        <input id="userID" name="userID" value="${id}" type="text" class="form-control input-md" style='display: none;' >
        <div class="col-md-4">
            <label class="control-label" for="nomefantasia">Nome Fantasia*</label>  
            <div>
              <input id="nomefantasia" maxlength="60" name="nomefantasia" value="${nomefantasia}" type="text" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: TADS JAVALIS SCHOOL</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
      <div class="col-md-3">
        <label class="control-label" for="cnpj">CNPJ*</label>  
        <div>
          <input id="cnpj" name="cnpj" maxlength="18" type="text" value="${cnpj}" placeholder="CPF do Aluno" class="form-control input-md" required="">
          <span class="help-block text-muted">Ex: 99.999.999/9999-99</span>  
        </div>
      </div>
        <div class="form-group row">
            <div class="col-md-2">
                <label class="control-label" for="habilitado">Ativo</label>  
                <input type="checkbox" id="habilitado" ${habilitado == 1 ? 'checked="checked"' : ''} name="habilitado" />
            </div>
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