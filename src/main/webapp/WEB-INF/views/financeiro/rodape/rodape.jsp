<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-md-12 navegacaoResumoMes">
	<table class="table tiraBordaTabela">
		<tr>
			<td class="input-95px"><a href="resumoMesIndividual?mes=${mes}&ano=${InfoAnalitico.anoA}" class="navegacaoLink">Resumo mês</a></td>
			<td class="input-80px active">ANALÍTICO</td>
			<td class="input-95px"><a href="contasPagar" class="navegacaoLink">Contas pagar</a></td>
			<td class="input-95px"><a href="contasReceber" class="navegacaoLink">Contas receber</a></td>
			
			<c:forEach var="i" begin="1" end="${quantRelatorioEventos}">
			
				<c:forEach items="${idsRelatorioEventos}" var="idsRelatorioEventos" varStatus="contador">
				 	<c:if test="${i == contador.count}">
		 				<td style="width: 20px !important;padding: 0px;vertical-align: middle;text-align: center;font-size: 15px;">
		 					<a href="relatorioEventoIndividual?idLista=${idsRelatorioEventos.get('relatorio.idLista')}&idRelatorioEvento=${idsRelatorioEventos.get('relatorio.idRelatorioEvento')}" class="navegacaoLink" style="padding:7px">${i}</a>
		 				</td>
				 	</c:if>
				</c:forEach>
			</c:forEach>
		</tr>
	</table>
</div>

