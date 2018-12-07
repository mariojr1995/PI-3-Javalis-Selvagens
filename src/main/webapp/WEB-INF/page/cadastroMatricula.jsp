<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/page/breadcrumb.jsp">
    <jsp:param name="title1" value="Matricula"/>
    <jsp:param name="title2" value="Cadastrar"/>
</jsp:include>
<h1 class="text-center pb-3 pt-3">Matriculas</h1>
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
            <div class="col-md-12">
                <label class="control-label" for="nome">Nome do Aluno*</label>  
                <div class="col-md-6">
                    <input id="nome" name="nome" maxlength="120" value="${nome}" type="text" placeholder="Nome do Aluno" disabled class="form-control input-md" required="">
                </div>
            </div>
            <input id="userID" name="userID" value="${id}" type="text" class="form-control input-md" style='display: none;' >
            <div class="col-md-12">
                <label class="control-label" for="nomefantasia">Turma*</label>  
                <div class="col-md-6">
                    <select class="form-control input-md" id="curso" name="curso" data-actions-box="true">
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>1x - Sem Juros</option>   
                    </select> 
                </div>
            </div>
        </div>

        <div class="form-group row">
            <div class="col-md-3">
                <label class="control-label" for="cnpj">Parcelamento</label>  
                <div>
                    <select class="form-control input-md" id="curso" name="curso" data-actions-box="true">
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>1x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>2x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>3x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>4x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>5x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>6x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>7x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>8x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>9x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>10x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>11x - Sem Juros</option>   
                        <option value=${cur.idCurso} ${curso == cur.idCurso ? 'selected="selected"' : ''}>12x - Sem Juros</option>   
                    </select> 
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-3">
                <label class="control-label" for="cnpj">Numero do Cartão</label>  
                <div>
                    <input id="nome" name="nome" maxlength="120" value="${nome}" type="text" placeholder="Nome da Unidade" class="form-control input-md" required="">
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-3">
                <label class="control-label" for="cnpj">Código de Segurança</label>  
                <div>
                    <input id="nome" name="nome" maxlength="120" value="${nome}" type="text" placeholder="Nome da Unidade" class="form-control input-md" required="">
                </div>
            </div>
        </div>
        <hr/>
        <div class="form-group row">
            <div class="col-md-5 ml-auto">
                <button class="btn btn-outline-secondary"><i class="fas fa-ban mr-2"></i>Cancelar</button>
                <button class="btn btn-outline-primary"><i class="fas fa-save mr-2"></i>Realizar Matricula</button>
            </div>
        </div>
        <hr/>
    </fieldset>
</form>

</div>