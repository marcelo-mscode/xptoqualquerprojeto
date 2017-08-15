<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			
			<tr>
				<td></td>
				<td>Saldo Bancário Itau</td>
				<td>
				 <c:set  var="totalSaldosAnteriores" value="${movimentoItau.valorAbertura + movimentoCef.valorAbertura + movimentoBradesco.valorAbertura + movimentoSantander.valorAbertura}" />
				<fmt:formatDate value="${movimentoItau.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
				<td>=></td>
				<td><fmt:formatNumber value="${movimentoItau.valorAbertura}" pattern="#,##0.00"/></td>
				<td rowspan="4" class="totalBancos"><fmt:formatNumber value="${totalSaldosAnteriores}" pattern="#,##0.00"/></td>
			</tr>
			<tr>
				<td></td>
				<td>Saldo Bancário CEF</td>
				<td><fmt:formatDate value="${movimentoCef.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
				<td>=></td>
				<td><fmt:formatNumber value="${movimentoCef.valorAbertura}" pattern="#,##0.00"/></td>
			</tr>
			<tr>
				<td></td>
				<td>Saldo Bancário Bradesco</td>
				<td><fmt:formatDate value="${movimentoBradesco.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
				<td>=></td>
				<td><fmt:formatNumber value="${movimentoBradesco.valorAbertura}" pattern="#,##0.00"/></td>
			</tr>

			<tr>
				<td></td>
				<td>Saldo Bancário Santander</td>
				<td><fmt:formatDate value="${movimentoSantander.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
				<td>=></td>
				<td><fmt:formatNumber value="${movimentoSantander.valorAbertura}" pattern="#,##0.00"/></td>
				
			</tr>