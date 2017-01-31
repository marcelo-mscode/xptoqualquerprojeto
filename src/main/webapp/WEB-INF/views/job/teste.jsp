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
	<c:forEach items="${jobs}" var="job">
		<tr>
			<td class="alinhamentoVertical">${job.codJob}</td>
			<td class="alinhamentoVertical"><a href="editaJob?idJob=${job.idJob}&idEmpresa=${job.empresa.idEmpresa}">${job.titulo}</a></td>
			<td class="alinhamentoVertical">${job.empresa.empresa}</td>
			<td class="alinhamentoVertical">${job.contatos.contato}</td>
			
			<c:if test="${empty job.localEvento}">
				<td>Não informado</td>
			</c:if>
			
			<c:if test="${not empty job.localEvento}">
				<c:forEach items="${job.localEvento}" var="localEvento">
					<td class="alinhamentoVertical">
						<fmt:formatDate value="${localEvento.localEventoDataInicio}" pattern="dd/MM/yyyy"/>
					</td>
				
				</c:forEach>
			</c:if>
			
			<td class="alinhamentoVertical"><fmt:formatDate value="${job.criadoEm.time}" pattern="dd/MM/yyyy"/></td>
			<td class="alinhamentoVertical"><fmt:formatDate value="${job.propostaData}" pattern="dd/MM/yyyy"/></td>
			<td class="alinhamentoVertical"><fmt:formatDate value="${job.idStatusAtual.prazoConclusao}" pattern="dd/MM/yyyy"/></td>										
	 		<td class="alinhamentoVertical">${job.idStatusAtual.idStatus.estatus}</td>

		</tr>
</c:forEach>

