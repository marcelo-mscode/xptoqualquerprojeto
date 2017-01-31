<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   

<!DOCTYPE html>
<html lang="pt">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- default header name is X-CSRF-TOKEN -->
	
	<!-- csrf  -->



	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
	<title>SysLocc</title>
	<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/syslocc.css"  />" />
	<link rel="stylesheet" href="<c:url value="resources/css/jquery-ui-1.8.18.custom.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/novosyslocclayout.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/estilo_imprimirRelatorioJob.css" />" media="print" />

</head>
<body style="margin:20px !important;padding:0;background: #fff !important">

    <div style="padding: 12px 0 12px;" class="tira-conteudo">
    	<a href="listajob" class="btn btn-default">Voltar</a>
     	<button class="btn btn-default tira-conteudo" onclick="printpage();">
     	<i class="glyphicon glyphicon-print" style="top: 2px;margin-right: 6px"></i>
     		Imprimir
     	</button>
	</div>
<table class="table table-striped table-td-ajuste table-hover" style="font-size: 9px;page-break-after: auto;width: 100%">

	<tr align="center">
		<td colspan="11" style="font-size: 15px;">Agenda de Eventos Atualizada em <fmt:formatDate value="${data.time}" pattern="dd/MM/yyyy"/> </td>
	</tr>
	<tr align="center" style="font-weight: bold;font-size: 11px">
		<td colspan="2">Quem ?</td>
		<td colspan="2"></td>
		<td colspan="3">Evento</td>
		
		<td colspan="2">Quem ?</td>
		<td colspan="2"></td>
		
	</tr>
	<tr align="center" style="font-weight: bold;font-size: 11px">
		<td style="width: 110px;">Cliente</td>
		<td>Responsável</td>
		<td colspan="2">Evento / Ação</td>
		<td style="width: 50px;">De:</td>
		<td style="width: 50px;">Até:</td>
		<td style="width: 90px;">Local:</td>
		<td>Produz:</td>
		<td>Acompanha</td>
		<td style="width: 60px;">Status:</td>
		<td style="width: 180px;">Obs:</td>
	</tr>

	<c:forEach items="${jobs}" var="jobs">
		<tr align="left" class="fontPrint">
			<td>${jobs.empresa}</td>
			<td class="obs">${jobs.contato}</td>
			<td colspan="2">${jobs.titulo}</td>
			
			<td>
				<c:forEach items="${jobs.listalocalEventoDataInicio}" var="listalocalEventoDataInicio">
					<fmt:formatDate value="${listalocalEventoDataInicio}" pattern="dd/MM/yyyy"/>
				</c:forEach>
			</td>
			
			<td>
				<c:forEach items="${jobs.listalocalEventoDataTermino}" var="listalocalEventoDataTermino">
					<fmt:formatDate value="${listalocalEventoDataTermino}" pattern="dd/MM/yyyy"/>
				</c:forEach>	
			</td>
	
			<td>
				<c:forEach items="${jobs.listaLocalEvento}" var="listaLocalEvento">
					${listaLocalEvento}<br />
				</c:forEach>
			</td>
	
	
			<td>${jobs.produtor1}</td>
			<td>${jobs.produtor2}</td>
			<td>${jobs.estatus}</td>
			<td class="obs">${jobs.obs}</td>
		</tr>
	</c:forEach>	

</table>
<span style="font-size: 8px"><fmt:formatDate value="${data.time}" pattern="dd/MM/yyyy HH:mm:ss"/></span>

<!-- </div> -->
















<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />


















<%-- <table class="table">

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
		
</table>		 --%>	


<c:import url="../_comum/footer.jsp" />



