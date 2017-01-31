<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
	.criacao{background: #f1f1f1;}
</style>

<c:import url="../../../_comum/header.jsp" />
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Relatório Listas</li>
		  </ol>					
</div>


<div style="height: 120px;margin-left: 65px;">
	<h3 style="font-family: 'OpenSansLight';padding: 10px;margin: 40px 0px 10px 0px;">RELATÓRIO POR LISTA</h3>
	<div class="col-md-2">
		<select class="form-control" id="listaEmpresa">
			<c:forEach items="${empresa}" var="empresa">
				<option value="${empresa[0]}">${empresa[1]}</option>		
			  </c:forEach>
		</select>
	</div>
	<div class="col-md-2">
		<select class="form-control" id="listaMes">
			<c:forEach items="${mesCriacao}" var="mes">
				<option value="${mes[0]}">${mes[1]}</option>		
			</c:forEach>
		</select>
	</div>
	<div class="col-md-2">
		<select class="form-control" id="listaData">
			<c:forEach items="${dataCriacao}" var="dataCriacao">
				<option value="${dataCriacao}">${dataCriacao}</option>		
			</c:forEach>
		</select>
	</div>

	<div class="col-md-1">
		<button onclick="filtraLista();" class="btn btn-danger" >Filtrar</button>
	</div>
	
	<div class="col-md-2" id='loader-confirmacao' style="display: none">
			<div class="progress">
			<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0"
				aria-valuemax="120" style="width: 100%">
				<span class="sr-only">100% Complete</span>
			</div>  
		</div><p style="margin-top: -20px;font-size: 12px;">Gerando Relatório, aguarde...</p>
	</div>
	
	
</div>



<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 218px;height: 600px;border-top: 1px solid #ccc;padding: 35px 60px;">

	
			<table class="table table-hover">
			<tr style="font-size: 15px;">
				<th>Lista</th>
				<th>Empresa</th>
				<th>Dt Entrada</th>
				<th>Dt Fechamento</th>
				<th>Total Horas Usadas</th>
			</tr>
			
			<c:forEach items="${lista}" var="lista">
				<tr>
					<td><a href="porListaIndividual?idCriacaoLista=${lista.idCriacaoLista}">${lista.listaTitulo}</a></td>
					<td>${lista.listaProducao.idJob.empresa.empresa}</td>
					<td><fmt:formatDate value="${lista.dataCriacao.time}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${lista.dataFechamento.time}" pattern="dd/MM/yyyy"/></td>
 				
					<c:if test="${lista.tempoTotal != null}">
						<td>${lista.tempoTotal}</td>
					</c:if>
					
					<c:if test="${lista.tempoTotal == null}">
						<c:forEach items="${totalHorasListas}" var="totalHorasListas">
							<c:if test="${totalHorasListas.idCriacaoLista == lista.idCriacaoLista}">
							  <td>${totalHorasListas.teste[0]}Hs ${totalHorasListas.teste[1]}Min</td>
							</c:if>
						</c:forEach>
					</c:if>
					
				</tr>
 		    </c:forEach>
			</table>
</div>


<c:import url="../../../_comum/footer.jsp" />




















