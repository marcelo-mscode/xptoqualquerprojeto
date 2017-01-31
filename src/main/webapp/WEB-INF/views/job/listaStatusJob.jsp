<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  
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

