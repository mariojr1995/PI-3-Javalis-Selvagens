<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <jsp:include page="/WEB-INF/page/breadcrumb.jsp">
        <jsp:param name="title1" value="Aluno"/>
        <jsp:param name="title2" value="Cadastro"/>
    </jsp:include>
    <h1 class="text-center pb-3 pt-3">Cadastro de Alunos</h1>
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
              <input id="nome" name="nome" value="${nome}" type="text" placeholder="Nome Completo do Aluno" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: Rogerio Sobrinho</span>  
            </div>
        </div>
        <input id="userID" name="userID" value="${userID}" type="text" class="form-control input-md" style='display: none;' >
        <div class="col-md-4">
            <label class="control-label" for="datanascimento">Data de Nascimento*</label>  
            <div>
              <input id="datanascimento" name="datanascimento" value="${datanascimento}" type="date" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: 26/12/1994</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
      <div class="col-md-3">
        <label class="control-label" for="sexo">Sexo*</label>  
        <div>
          <select class="form-control input-md" id="sexo" name="sexo" data-actions-box="true">
            <option value="M" ${sexo == "M" ? 'selected="selected"' : ''} >Masculino</option>
            <option value="F" ${sexo == "F" ? 'selected="selected"' : ''} >Feminino</option>
            <option value="O" ${sexo == "O" ? 'selected="selected"' : ''} >Outros</option>
            <option value="N" ${sexo == "N" ? 'selected="selected"' : ''} >Não deseja informar</option>
          </select>
        </div>
      </div>
      <div class="col-md-4">
        <label class="control-label" for="cpf">CPF*</label>  
        <div>
          <input id="cpf" name="cpf" type="text" value="${cpf}" placeholder="CPF do Aluno" class="form-control input-md" required="">
          <span class="help-block text-muted">Ex: 999.999.999.99</span>  
        </div>
      </div>
      <div class="col-md-3">
        <label class="control-label" for="rg">RG*</label>  
        <div >
          <input id="rg" name="rg" type="text" value="${rg}" placeholder="RG do Aluno" class="form-control input-md" required="">
          <span class="help-block text-muted">Ex: 99.999.999-9</span>  
        </div>
      </div>
    </div>
    
    <div class="form-group row">
        <div class="col-md-3">
            <label class=" control-label" for="orgaoemissor">Orgão Emissor*</label>  
            <div>
              <input id="orgaoemissor" name="orgaoemissor" placeholder="Orgão Emissor do RG" value="${orgaoemissor}" type="text" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: SSP</span>  
            </div>
        </div>
        <div class="col-md-3">
            <label class="control-label" for="estadoemissor">Estado Emissor*</label>  
            <div>
              <input id="estadoemissor" name="estadoemissor" placeholder="Estado Emissor do RG" value="${estadoemissor}" type="text" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: SP</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-md-3">
            <label class=" control-label" for="telefone">Telefone*</label>  
            <div>
                <input id="telefone" name="telefone" placeholder="Telefone Residêncial" value="${telefone}" type="tel" pattern="[0-9]{2}-[0-9]{4}-[0-9]{4}" class="form-control input-md" required="">
                <span class="help-block text-muted">Ex: 11-5555-5555</span>  
            </div>
        </div>
        <div class="col-md-3">
            <label class="control-label" for="celular">Celular</label>  
            <div>
              <input id="celular" name="celular" type="tel" placeholder="Telefone Celular" value="${celular}" pattern="[0-9]{2}-[0-9]{5}-[0-9]{4}" class="form-control input-md">
              <span class="help-block text-muted">Ex: 11-99999-9999</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-md-5">
            <label class=" control-label" for="email">E-mail*</label>  
            <div>
              <input id="email" name="email" type="email" placeholder="E-mail do Aluno" value="${email}" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: exemplo@exemplo.com.br</span>  
            </div>
        </div>
    </div>
    <hr/>
    <h4 class="text-center pb-3 pt-3">Endereço</h4>
    <div class="form-group row">
        <div class="col-md-4">
            <label class=" control-label" for="logradouro">Logradouro*</label>  
            <div>
              <input id="logradouro" name="logradouro" placeholder="Nome da Rua" value="${logradouro}" type="text" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: Avenida das Nações Unidas</span>  
            </div>
        </div>
        <div class="col-md-2">
            <label class=" control-label" for="numero">Número*</label>  
            <div>
              <input id="numero" name="numero" type="text" placeholder="Nº da Residência, apt, etc.." value="${numero}" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: 1000</span>  
            </div>
        </div>
        <div class="col-md-4">
            <label class=" control-label" for="complemento">Complemento</label>  
            <div>
              <input id="complemento" name="complemento" placeholder="Informações adicionais" value="${complemento}" type="text" class="form-control input-md">
              <span class="help-block text-muted">Ex: Ao lado da Padaria</span>  
            </div>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-4">
            <label class=" control-label" for="bairro">Bairro*</label>  
            <div>
              <input id="bairro" name="bairro" placeholder="Nome do Bairro" type="text" value="${bairro}"  class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: Santo Amaro</span>  
            </div>
        </div>
        <div class="col-md-4">
            <label class=" control-label" for="cidade">Cidade*</label>  
            <div>
              <input id="cidade" name="cidade" placeholder="Nome do Cidade" type="text" value="${cidade}" class="form-control input-md" required="">
              <span class="help-block text-muted">Ex: São Paulo</span>  
            </div>
        </div>
        <div class="col-md-2">
            <label class=" control-label" for="estado">Estado*</label>  
            <div>
              <select class="form-control input-md" id="estado" name="estado" data-actions-box="true">
                <option value="SP" ${estado == "SP" ? 'selected="selected"' : ''}>SP</option>   
                <option value="AC" ${estado == "AC" ? 'selected="selected"' : ''}>AC</option>
                <option value="AL" ${estado == "AL" ? 'selected="selected"' : ''}>AL</option>
                <option value="AM" ${estado == "AM" ? 'selected="selected"' : ''}>AM</option>
                <option value="AP" ${estado == "AP" ? 'selected="selected"' : ''}>AP</option>
                <option value="BA" ${estado == "BA" ? 'selected="selected"' : ''}>BA</option>
                <option value="CE" ${estado == "CE" ? 'selected="selected"' : ''}>CE</option>
                <option value="DF" ${estado == "DF" ? 'selected="selected"' : ''}>DF</option>
                <option value="ES" ${estado == "ES" ? 'selected="selected"' : ''}>ES</option>
                <option value="GO" ${estado == "GO" ? 'selected="selected"' : ''}>GO</option>
                <option value="MA" ${estado == "MA" ? 'selected="selected"' : ''}>MA</option>
                <option value="MG" ${estado == "MG" ? 'selected="selected"' : ''}>MG</option>
                <option value="MS" ${estado == "MS" ? 'selected="selected"' : ''}>MS</option>
                <option value="MT" ${estado == "MT" ? 'selected="selected"' : ''}>MT</option>
                <option value="PA" ${estado == "PA" ? 'selected="selected"' : ''}>PA</option>
                <option value="PB" ${estado == "PB" ? 'selected="selected"' : ''}>PB</option>
                <option value="PE" ${estado == "PE" ? 'selected="selected"' : ''}>PE</option>
                <option value="PI" ${estado == "PI" ? 'selected="selected"' : ''}>PI</option>
                <option value="PR" ${estado == "PR" ? 'selected="selected"' : ''}>PR</option>
                <option value="RJ" ${estado == "RJ" ? 'selected="selected"' : ''}>RJ</option>
                <option value="RN" ${estado == "RN" ? 'selected="selected"' : ''}>RN</option>
                <option value="RO" ${estado == "RO" ? 'selected="selected"' : ''}>RO</option>
                <option value="RR" ${estado == "RR" ? 'selected="selected"' : ''}>RR</option>
                <option value="RS" ${estado == "RS" ? 'selected="selected"' : ''}>RS</option>
                <option value="SC" ${estado == "SC" ? 'selected="selected"' : ''}>SC</option>
                <option value="SE" ${estado == "SE" ? 'selected="selected"' : ''}>SE</option>
                <option value="TO" ${estado == "TO" ? 'selected="selected"' : ''}>TO</option>
              </select>
              <span class="help-block text-muted">Ex: SP</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-md-2">
            <label class="control-label" for="habilitado">Habilitado para Matricula</label>  
            <input type="checkbox" id="habilitado" ${habilitado == 1 ? 'checked="checked"' : ''} name="habilitado" />
        </div>
    </div>
    <hr/>
    <h4 class="text-center pb-3 pt-3">Responsável</h4>
    
     <div class="form-group row">
        <div class="col-md-6">
            <label class="control-label" for="responsavel_nome">Nome</label>  
            <div>
              <input id="responsavel_nome" name="responsavel_nome" value="${responsavel_nome}" type="text" placeholder="Nome Completo do Aluno" class="form-control input-md" >
              <span class="help-block text-muted">Ex: Rogerio Sobrinho</span>  
            </div>
        </div>
        <div class="col-md-4">
            <label class="control-label" for="responsavel_datanascimento">Data de Nascimento</label>  
            <div>
              <input id="responsavel_datanascimento" value="${responsavel_datanascimento}" name="responsavel_datanascimento" type="date" class="form-control input-md">
              <span class="help-block text-muted">Ex: 26/12/1994</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
      <div class="col-md-3">
        <label class="control-label" for="responsavel_sexo">Sexo</label>  
        <div>
          <select class="form-control input-md" value="${responsavel_sexo}" id="responsavel_sexo" name="responsavel_sexo" data-actions-box="true">
            <option value="M" >Masculino</option>
            <option value="F" >Feminino</option>
            <option value="O" >Outros</option>
            <option value="N" >Não deseja informar</option>
          </select>
        </div>
      </div>
      <div class="col-md-4">
        <label class="control-label" for="responsavel_cpf">CPF</label>  
        <div>
          <input id="responsavel_cpf" name="responsavel_cpf" placeholder="CPF do Responsável" value="${responsavel_cpf}" type="text" class="form-control input-md" >
          <span class="help-block text-muted">Ex: 999.999.999.99</span>  
        </div>
      </div>
      <div class="col-md-3">
        <label class="control-label" for="responsavel_rg">RG</label>  
        <div >
          <input id="responsavel_rg" name="responsavel_rg" placeholder="RG do Responsável" value="${responsavel_rg}" type="text" class="form-control input-md" >
          <span class="help-block text-muted">Ex: 99.999.999-9</span>  
        </div>
      </div>
    </div>
    
    <div class="form-group row">
        <div class="col-md-3">
            <label class=" control-label" for="responsavel_orgaoemissor">Orgão Emissor</label>  
            <div>
              <input id="responsavel_orgaoemissor" name="responsavel_orgaoemissor" placeholder="Orgão Emissor do RG" value="${responsavel_orgaoemissor}" type="text" class="form-control input-md">
              <span class="help-block text-muted">Ex: SSP</span>  
            </div>
        </div>
        <div class="col-md-3">
            <label class="control-label" for="responsavel_estadoemissor">Estado Emissor</label>  
            <div>
              <input id="responsavel_estadoemissor" name="responsavel_estadoemissor" placeholder="Estado Emissor do RG"  value="${responsavel_estadoemissor}" type="text" class="form-control input-md">
              <span class="help-block text-muted">Ex: SP</span>  
            </div>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-md-3">
            <label class=" control-label" for="responsavel_telefone">Telefone</label>  
            <div>
                <input id="responsavel_telefone" name="responsavel_telefone" placeholder="Telefone Residêncial" value="${responsavel_telefone}"  type="tel" pattern="[0-9]{2}-[0-9]{4}-[0-9]{4}" class="form-control input-md">
                <span class="help-block text-muted">Ex: 11-5555-5555</span>  
            </div>
        </div>
        <div class="col-md-3">
            <label class="control-label" for="responsavel_celular">Celular</label>  
            <div>
              <input id="responsavel_celular" name="responsavel_celular" placeholder="Telefone Celular" value="${responsavel_celular}" type="tel" pattern="[0-9]{2}-[0-9]{5}-[0-9]{4}" class="form-control input-md">
              <span class="help-block text-muted">Ex: 11-99999-9999</span>  
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