<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
		    <li class="active"><b>Lista de Pendências</b></li>
		</ol>
	</div>
</div>


<div class="container">
  <div class="row" style="background-color: #fff;padding: 0px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;margin-top: 45px">	
	<div class="col-md-12"> 
	<h3 style="font-family: 'OpenSansLight'">Lista de Pendências</h3>
		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
			<tr>
				<th>Item</th>
				<th>Lista</th>
				<th>Descrição Pendencia</th>
				<th>Responsável</th>
				<th>Data Entrada Pendência</th>
				<th>Data Limite</th>
				<th>Status</th>
			</tr>
		<c:forEach items="${pendencias}" var="pendencias">
			<tr>
			<td><a href="exibeHistoricoItem?idHistoricoItem=${pendencias.idCriacaoItemHistoricoPendencia}">${pendencias.criacaoItemLista.tituloItem}</a></td>
			<td>${pendencias.criacaoItemLista.criacaoLista.listaTitulo}</td>
			<td>${pendencias.descPendencia}</td>
			<td>${pendencias.responsavelItem.nome}</td>
			<td><fmt:formatDate value="${pendencias.dataEnvioPendencia.time}" pattern="dd/MM/yyyy - HH:mm" /></td>
			<td><fmt:formatDate value="${pendencias.dataLimite}" pattern="dd/MM/yyyy - HH:mm"/>					
			<td>${pendencias.criacaoItemStatus.status}</td>	
				</tr>
		</c:forEach>
		</table>	
	  </div>
	</div>
	
	<div class="row" style="background-color: #fff;padding: 0px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;margin-top: 25px">	
	<div class="col-md-12"> 
	<h3 style="font-family: 'OpenSansLight'">Lista de Pendências Finalizadas</h3>
		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
			<tr>
				<th>Item</th>
				<th>Descrição Pendencia</th>
				<th>Responsável</th>
				<th>Data Entrada Pendência</th>
				<th>Status</th>
				<th>Data Limite</th>
				<th>Data Finalização</th>
			</tr>
		<c:forEach items="${finalizados}" var="finalizados">
			<tr>
			<td><a href="exibeHistoricoItem?idHistoricoItem=${finalizados.idCriacaoItemHistoricoPendencia}">${finalizados.criacaoItemLista.tituloItem}</a></td>
			<td>${finalizados.descPendencia}</td>
			<td>${finalizados.responsavelItem.nome}</td>
			<td><fmt:formatDate value="${finalizados.dataEnvioPendencia.time}" pattern="dd/MM/yyyy - HH:mm" /></td>
			<td class="verde">${finalizados.criacaoItemStatus.status}</td>
			<td><fmt:formatDate value="${finalizados.dataLimite}" pattern="dd/MM/yyyy HH:mm"/>	
			<td><fmt:formatDate value="${finalizados.dataTermino.time}" pattern="dd/MM/yyyy - HH:mm" /></td>	
				</tr>
		</c:forEach>
		</table>	
	  </div>
	</div>
	
	
	
</div>








<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>
	