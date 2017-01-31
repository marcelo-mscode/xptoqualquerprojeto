<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<tr>
	<th>Cod Job</th>
	<th>Nome da Lista</th>
	<th>Empresa</th>
	<th>Job</th>
	<th>Responsável</th>
	<th>Status</th>
	<th>Prazo para Proposta</th>
	<th>Data Fechamento</th>
</tr>

<c:forEach items="${listaFechadaEmpresa}" var="listaPorEmpresa">

	<c:if test="${listaPorEmpresa.criacaoStatus.status !='Fechado'}">  </c:if>
	
	<c:if test="${listaPorEmpresa.criacaoStatus.status =='Fechado'}">
	<tr>	
			<td>${listaPorEmpresa.listaProducao.idJob.codJob}</td>	
			<td><a href="exibeItens?idCriacaoItemLista=${listaPorEmpresa.idCriacaoLista}">${listaPorEmpresa.listaTitulo}</a></td>
			<td>${listaPorEmpresa.listaProducao.idJob.empresa.empresa}</td>
			<td>${listaPorEmpresa.listaProducao.idJob.titulo}
			<td>${listaPorEmpresa.usuarioReponsável.nome}</td>
			<td>${listaPorEmpresa.criacaoStatus.status}</td>
			<td><fmt:formatDate value="${listaPorEmpresa.listaProducao.idJob.propostaData}" pattern="dd/MM/yyyy HH:mm" /></td>
			<td><fmt:formatDate value="${listaPorEmpresa.dataFechamento.time}" pattern="dd/MM/yyyy HH:mm" /></td>
	</tr>		
	</c:if>
	
	
</c:forEach>