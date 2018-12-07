<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <jsp:include page="/WEB-INF/page/breadcrumb.jsp">
            <jsp:param name="title1" value="Curso"/>
            <jsp:param name="title2" value="Cadastro"/>
        </jsp:include>

        <h1 class="text-center pb-3 pt-3">Cadastro de Cursos</h1>
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
                  <input id="nome" name="nome" value="${nome}" type="text" placeholder="Nome do Curso" class="form-control input-md" required="">
                  <span class="help-block text-muted">Ex: Curso de Inglês</span>  
                </div>
            </div>
            <input id="id" name="id" value="${id}" type="text" class="form-control input-md" style='display: none;' >
            <div class="col-md-4">
                <label class="control-label" for="descricao">Descrição</label>  
                <div>
                  <input id="v" name="descricao" value="${descricao}" type="text" class="form-control input-md" required=""> 
                </div>
            </div>
        </div>

        <div class="form-group row">
          <div class="col-md-3">
            <label class="control-label" for="valor">Valor*</label>  
            <div>
              <input id="valor" name="valor" type="text" value="${valor}" placeholder="Valor do Curso" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: 900.00</span>  
            </div>
          </div>
          <div class="col-md-3">
            <label class="control-label" for="tipo">Tipo*</label>  
            <div>
              <input id="tipo" name="tipo" type="text" value="${tipo}" placeholder="Tipo de Curso" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: Médio</span>  
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