<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:forEach items="${infoLista.idJob.localEvento}" var="locEvento">
	<c:set  var="localEvento" value="${locEvento.local}" />
</c:forEach>

<table class="table table-striped table-hover table-bordered bordaDestaque" >
  <tbody id="prospeccaoFiltro">
	<tr>
		<td>Nome do Evento</td>
		<td>Cliente</td>
		<td style="text-align: center">
			<c:if test="${relatorio.ndFatDireto == false}">NF</c:if>
			<c:if test="${relatorio.ndFatDireto == true}">ND</c:if>
		</td>
		<td>Data do Evento</td>
		<td>Local do Evento</td>
		
	</tr>

	<tr>
		<td><b>${infoLista.lista}</b></td>
		<td><b>${infoLista.idJob.empresa.empresa}</b></td>
		<td style="text-align: center"><b>${infoInterna.nfInterna}</b></td>
		<td><b><fmt:formatDate value="${infoLista.dataDoEvento.time}" pattern="dd/MM/yyyy"/></b></td>
		<td><b>${localEvento}</b></td>
	</tr>
  </tbody>
</table>