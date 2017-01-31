<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>  

<c:import url="../_comum/header.jsp" />


<script type="text/javascript" src="resources/js/jquery-1.11.2.min.js"></script>	


<script type="text/javascript">
function emailSucesso(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-sucesso-email").slideToggle("slow"); 
	    }, 4000);
	});
}

</script>


<div class="col-md-12 bodyXY" style="height: 35px;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li>Lista de Usuários</li>
		</ol>
	</div>

</div>

	<c:if test="${msg.get(0) == 'sucesso'}">
	<div class="msg-email msg-sucesso-email" id="msg-sucesso-email" style="top: 50px !important;left: 27% !important;">${msg.get(1)}</div>
	<script type="text/javascript">emailSucesso();</script>
</c:if>	

<div class="row">
<div class="col-md-6 painel estilo-painel-job" style="margin-left: 35px;">
	<div class="col-md-12" style="padding: 25px;">
		<div class="form-inline">
		
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="<c:url value="/usuario" />" class="btn btn-info"  >Novo Usuário</a>
	</security:authorize>	
		
			<div class="divisor"></div>
			<table class="table table-striped table-td-ajuste table-sem-bold"
						style="font-size: 12px;">
					<tr>
						<th>Nome do Usuário</th>
						<th>Departamento</th>
						<th>Status</th>
					<security:authorize access="hasRole('ROLE_ADMIN')">	
							<th>Excluir</th>
					</security:authorize>
					</tr>
				<c:forEach items="${usuarios}" var="usuario">
					<tr>
						
						<c:if test="${usuario.arquivoX == 3}">
							
						</c:if>
											
					<c:if test="${usuario.arquivoX != 3}">
						<td><a href="#" onclick="modalUser('informacaoUsuario?id=${usuario.idUsuario}');">${usuario.nome}</a></td>
						<td>${usuario.departamento.departamento}</td>

 						<c:if test="${usuario.habilitado}">
							<td >Ativo</td>
							<!-- <td class="disab">Desativar</td> -->
						</c:if>

						<c:if test="${!usuario.habilitado}">
							<td class="disab">Desativado</ td>
							<!-- <td class="disab">Ativar</td> -->
						</c:if>
					
					
					<security:authorize access="hasRole('ROLE_ADMIN')">	
						<td><a href="removeUsuario?id=${usuario.idUsuario}">Excluir</a></td>
					</security:authorize>
					
					
					
					</c:if>	
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
  </div>
</div>

</div>
<br /><br /><br /><br /><br />
<br /><br /><br /><br />
<br /><br /><br /><br />
<br /><br /><br /><br />







<!-- - - - - - - - - - Fim Container - - - - - - - - - -->



<div class="modal fade" id="25" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="text-align: center">
        
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
        	<span aria-hidden="true" style="font-size: 50px;font-family: 'OpenSansLight';font-weight: normal;">&times;</span>
        </button>
        
        <h4 class="modal-title" id="myModalLabel" style="font-family: 'OpenSansLight'">ATUALIZAÇÃO DE USUÁRIO</h4>
        <img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader" alt="loading" />
      </div>
      <div class="modal-body">
        
        <div class="modalUser"></div>
       
      </div>
          </div>
  </div>
</div>


<c:import url="../_comum/footer.jsp" />