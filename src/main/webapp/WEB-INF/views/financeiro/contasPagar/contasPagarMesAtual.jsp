<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<table class="table table-striped table-hover table-condensed" >
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important;text-align: center !important;">
			<td colspan="5" style="text-align: center !important;color:red"><h4>Fornecedores - Eventos do Mês</h4></td>
		</tr>
		<tr style="background: #f1f1f1 !important;" class="cabecalhoLista">
			<td align="left">NF/ND</td>
			<td align="left">Vencimento</td>
			<td align="left">Descrição</td>
			<td align="left" colspan="2">Valor</td>
		</tr>
	
	
		 <c:set var="valorTotal" value="0.00" />			
	
		 <c:forEach items="${idListas}" var="idListas">
	
		 	<tr>
		 		<td colspan="5" align="left"><h4><b>${idListas[1]}</b></h4></td>
		 	</tr>
		 
			<c:forEach items="${novaLista}" var="novaLista" varStatus="loop">
				<c:if test="${idListas[0] == novaLista[3]}">
					<tr id="valor${loop.count}">
						<%-- <td>${novaLista[7]}</td> --%>
						
						<%-- <td>TemContratacao : ${novaLista[7]}</td>
						<td>Tem Mesmo Fornecedor: ${novaLista[8]}</td>
						<td>ValorPgto Contratacao: ${novaLista[9]}</td>
						<td>Valor Contratacao: ${novaLista[10]}</td> --%>
						
						
						
						<td <c:if test="${novaLista[7] == true}">class="temContratacao"</c:if>>${novaLista[2]}</td>
	
						<td><fmt:formatDate value="${novaLista[0]}"   pattern="dd/MM/yyyy" /></td>
						<td>${novaLista[1]}</td>
						<td><fmt:formatNumber value="${novaLista[4]}" pattern="#,##0.00" /></td>
						<td><a onclick="pagaContas(${idListas[0]},${novaLista[5]},${novaLista[6]},valor${loop.count});" class="btn btn-success">PAGAR</a> </td>
						<c:set var="valorTotal" value="${valorTotal+novaLista[4]}" />			
					</tr>
				</c:if>
			</c:forEach>
		 </c:forEach>	
	  </table>
	  <h1><fmt:formatNumber value="${valorTotal}" pattern="#,##0.00" /></h1> 