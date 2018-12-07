<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class='container mt-2'>
    <jsp:include page="/WEB-INF/page/breadcrumb.jsp">
        <jsp:param name="title1" value="Aluno"/>
        <jsp:param name="title2" value="Consulta"/>
    </jsp:include>
    <h1 class="text-center pb-3 pt-3">Consulta de Alunos</h1>
    <form class="form-horizontal pb-4" method="post" action="${pageContext.request.contextPath}/aluno/consulta">
        <fieldset>
            <div class="form-group row border">
                <div class="col-md-5 pt-3">
                    <label class="control-label" for="nome">Nome</label>  
                    <div>
                      <input id="nome" maxlength="120" name="nome" type="text" placeholder="Nome do Aluno" class="form-control input-md" >
                      <span class="help-block text-muted">Ex: Rogerio Sobrinho</span>  
                    </div>
                </div>
                <div class="col-md-4 pt-3">
                    <label class="control-label" for="cpf">CPF</label>  
                    <div>
                        <input id="cpf" maxlength="14" name="cpf" type="text" placeholder="CPF do Aluno" class="form-control input-md" >
                      <span class="help-block text-muted">Ex: 999.999.999-99</span>  
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
                  <th scope="col">CPF</th>
                  <th scope="col">Telefone</th>
                  <th scope="col">E-mail</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                    <c:if test="${alunos != null}">
                        <c:forEach items="${alunos}" var="alu">
                            <th scope="row">${alu.id}</th>
                            <td>${alu.nome}</td>
                            <td>${alu.cpf}</td>
                            <td>${alu.telefone}</td>
                            <td>${alu.email}</td>
                            <td>
                                <a href="/JavaliSelvagens_PI3/aluno/alterar?userID=${alu.id}">
                                    <i class="fas fa-pencil-alt"></i>
                                </a>
                            </td>
                            <td>
                                <a href="/JavaliSelvagens_PI3/Matricula/Cadastrar?aluno=${alu.id}">
                                    Gerar Matricula
                                </a>
                            </td>
                        </c:forEach>
                    </c:if>
                </tr>
              </tbody>
            </table>
    </div>
</div>