<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class='container mt-2'>
    <jsp:include page="/WEB-INF/page/breadcrumb.jsp">
        <jsp:param name="title1" value="Curso"/>
        <jsp:param name="title2" value="Consulta"/>
    </jsp:include>
    <h1 class="text-center pb-3 pt-3">Consulta de Cursos</h1>
    <form class="form-horizontal pb-4" method="post" action="${pageContext.request.contextPath}/curso/consulta">
        <fieldset>
            <div class="form-group row border">
                <div class="col-md-5 pt-3">
                    <label class="control-label" for="nome">Nome</label>  
                    <div>
                      <input id="nome" name="nome" type="text" placeholder="Nome" class="form-control input-md" >
                      <span class="help-block text-muted">Ex: Curso de Inglês - 1º Ano</span>  
                    </div>
                </div>
                <div class="col-md-4 pt-3">
                    <label class="control-label" for="cpf">Tipo</label>  
                    <div>
                      <input id="cpf" name="cpf" type="text" placeholder="Tipo do Curso" class="form-control input-md" >
                      <span class="help-block text-muted">Ex: Inglês</span>  
                    </div>
                </div>
                <div class="col-md-2 pt-5 pb-5">
                <button class="btn btn-outline-primary"><i class="fas fa-search mr-2"></i>Pesquisar</button>
                </div>
            </div>
        </fieldset>
    </form>
    <c:if test="${not empty mensagem}">
        <div class="alert alert-info text-center" role="alert">
            <c:out value="${mensagem}" />
        </div>
    </c:if>
    <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Nome</th>
                  <th scope="col">Valor</th>
                  <th scope="col">Tipo</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                    <c:if test="${cursos != null}">
                        <c:forEach items="${cursos}" var="cur">
                            <th scope="row">${cur.idCurso}</th>
                            <td>${cur.nome}</td>
                            <td>${cur.valor}</td>
                            <td>${cur.tipo}</td>
                            <td>
                                <a href="/JavaliSelvagens_PI3/curso/alterar?ID=${cur.idCurso}">
                                    <i class="fas fa-pencil-alt"></i>
                                </a>
                            </td>
                        </c:forEach>
                    </c:if>
                </tr>
              </tbody>
            </table>
    </div>
</div>