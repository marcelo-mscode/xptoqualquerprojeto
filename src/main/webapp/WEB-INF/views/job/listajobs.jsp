<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li class="active"><b>Lista de Jobs</b></li>
		</ol>
	</div>
</div>

	<div class="col-md-12 efeitoDegradeParaCima" style="padding-left: 58px;">
	
		<div class="col-md-2" style="padding-left: 0;padding-top: 10px;">
			<input type="checkbox" id="inputEmpresa" style="width: 15px;height: 15px;">&nbsp&nbspCliente</input>
			<select class="form-control" size="5" style="width: 160px;font-size: 13px" id="selectEmpresa">
				<c:forEach items="${empresas}" var="empresas">
					<option value="${empresas[0]}">${empresas[1]}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-2" style="padding-left: 0;padding-top: 10px;">
			<input type="checkbox" id="inputJob" style="width: 15px;height: 15px;">&nbsp&nbspJob</input>
			
			<select class="form-control" size="5" style="width: 160px;font-size: 13px" id="selectJob">


			</select>
		</div>
		
		<div class="col-md-2" style="padding-left: 0;padding-top: 10px;">
			<input type="checkbox" id="inputStatus" checked="checked" style="width: 15px;height: 15px;">&nbsp&nbspStatus</input>
			
			<select class="form-control" size="5" style="width: 160px;font-size: 13px" id="selectEstatus" multiple="multiple">
				
						<option value="9" selected="selected">Adiado</option>
						<option value="8" selected="selected">Aguardando</option>
						<option value="1" selected="selected">Aprovado</option>
						<option value="2">Cancelado</option>
						<option value="4">Concluido</option>
						<option value="6" selected="selected">Des.Proposta</option>
						<option value="5" selected="selected">Execução</option>
						<option value="3">Não Aprovado</option>
						<option value="7" selected="selected">Novo Job</option>
			</select>
		</div>
		
			
	
		<div class="col-md-1" style="padding-left: 0;">
			<button class="btn btn-danger" onclick="distribuiTarefas();" style="top: 35px;position: relative;">Filtrar</button>
		</div>
		
		<div class="col-md-2" style="padding-left: 0;padding-top: 14px;margin-left: 5px;margin-top: 1px;border-right: 1px solid #ddd;">
			<p>Ordem:
			<select class="form-control" style="width: 160px;font-size: 13px" id="selectOrdem">
					<option value="desc">Decrescente</option>
					<option value="asc">Crescente</option>
			</select>
			
			
			
			<p>Classificar Coluna por:
			<select class="form-control" style="width: 160px;font-size: 13px" id="selectPor" onchange="classificarPor(this.value);">
					<option value="1">Cod Job</option>
					<option value="0">Prazo Interno</option>
					<option value="2">Contato</option>
					<option value="3">Título</option>
					<option value="4">Status</option>
					<option value="5">Empresa</option>
			</select>
			
			<!-- <div class="row">
				<button onclick="relatorio();" class="btn btn-info">Relatório</button>
			</div> -->
		</div>
		
		
		<div class="col-md-2" style="margin-left: 15px;margin-top: 4px">
			
			<button onclick="relatorio();" class="btn btn-info" style="margin-top: 30px;">
			<i class="glyphicon glyphicon-inbox" style="top: 1px;margin-right: 10px;"></i>
			Relatório</button>
		
		</div>
		
		
		

	</div>		
		
	<div class="bodyXY">
			<!--  -->
			<br /><br /><br /><br /><br /><br /><br />
			
		<div class="" style="margin-left: 58px">
			<div class="divisor"></div>
			<div class="divisor"></div>		
	
				<a href="job" class="btn btn-danger" style="margin-top: 20px;">Novo Job</a>
				<img class="col-md-offset-5" src="resources/images/loader-lista.gif" width="30" height="30" alt="loading" id="loader-lista" style="margin-right: -2px;display: none">
				<div class="divisor"></div>
					
		</div>
	</div>

<!-- - - - - - - - - - Fim Container - - - - - - - - - -->

<div class="estilo-painel-job" style="background:#fff;padding: 10px;border-top: 1px solid #ddd;padding-top: 30px;margin-top: 0">
	<table class="table table-striped table-td-ajuste table-sem-bold table-hover " style="font-size: 13px;">
		<tbody id="listaJobAjax">
			<tr>
				<th>Cod Job</th>
				<th style="width: 340px">Título</th>
				<th>Empresa</th>
				<th>Contato</th>
				<th>Dt Prevista</th>
				<th>Dt Entrada</th>
				<th>Dt Proposta</th>
				<th>Prazo Inter</th>								
				<th>Status</th>
			</tr>
		<c:forEach items="${jobs}" var="jobs">
			<tr>
				<td class="alinhamentoVertical">${jobs.codJob}</td>
				<td class="alinhamentoVertical"><a href="editaJob?idJob=${jobs.idJob}&idEmpresa=${jobs.idEmpresa}">${jobs.titulo}</a></td>
				<td class="alinhamentoVertical">${jobs.empresa}</td>
				<td class="alinhamentoVertical">${jobs.contato}</td>
				<td class="alinhamentoVertical"><fmt:formatDate value="${jobs.localEventoDataInicio}" pattern="dd/MM/yyyy"/></td>
				<td class="alinhamentoVertical"><fmt:formatDate value="${jobs.criadoEm.time}" pattern="dd/MM/yyyy"/></td>
				<td class="alinhamentoVertical"><fmt:formatDate value="${jobs.propostaData}" pattern="dd/MM/yyyy"/></td>
				<td class="alinhamentoVertical"><fmt:formatDate value="${jobs.prazoConclusao}" pattern="dd/MM/yyyy"/></td>
				<td class="alinhamentoVertical">${jobs.estatus}</td>
			</tr>
		 </c:forEach>
		</tbody>
	</table>	
</div>

<br /><br /><br /><br /><br /><br /><br />
<br /><br /><br /><br /><br /><br /><br />

<%-- 	<td>${jobs.idJob}</td>
		<td class="alinhamentoVertical">${jobs.idEmpresa}</td> --%>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/consultasJobApoio.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/consultasJob.js" />"></script>




