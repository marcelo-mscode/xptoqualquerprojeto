<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
		<c:set var="totalItau" value="${movimentoItau.valorAbertura - movimentoItau.totalTarifas + movimentoItau.totalCreditos - movimentoItau.totalDebitos}" />
		<c:set var="totalCef" value="${movimentoCef.valorAbertura - movimentoCef.totalTarifas + movimentoCef.totalCreditos - movimentoCef.totalDebitos}" />
		<c:set var="totalBradesco" value="${movimentoBradesco.valorAbertura - movimentoBradesco.totalTarifas + movimentoBradesco.totalCreditos - movimentoBradesco.totalDebitos}" />
		<c:set var="totalSantander" value="${movimentoSantander.valorAbertura - movimentoSantander.totalTarifas + movimentoSantander.totalCreditos - movimentoSantander.totalDebitos}" />
	
	<tr>
		<th colspan="6" class="fundoVerde">Contas Correntes:</th>
		<th></th>
	</tr>
	
	<tr>
		<th class="fundoAmarelo input-260px">Saldo Bancário  Itau:</th>
		<th class="fundoAmarelo input-160px"></th>
		<th class="fundoAmarelo input-160px"><fmt:formatDate value="${movimentoItau.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/></th>
		<th class="fundoAmarelo input-160px"></th>
		<th class="fundoAmarelo input-160px">=></th>
		<th class="fundoAmarelo input-160px" style="border: 2px solid #000"><b>
		<fmt:formatNumber value="${totalItau}" pattern="#,##0.00"/>		
		</b></th>
		<th class="input-160px" rowspan="4" style="vertical-align:middle">
		<c:set  var="somaTotalDosbancos" value="${totalItau+totalCef+totalBradesco+totalSantander}"/>
		  	<fmt:formatNumber value="${somaTotalDosbancos}" pattern="#,##0.00"/>
		</th>
	</tr>
	
	<tr>
		<th class="fundoVerde input-260px">Saldo Bancário  C.E.F:</th>
		<th class="fundoVerde input-160px"></th>
		<th class="fundoVerde input-160px"><fmt:formatDate value="${movimentoCef.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/></th>
		<th class="fundoVerde input-160px"></th>
		<th class="fundoVerde input-160px">=></th>
		<th class="fundoVerde input-160px" style="border: 2px solid #000"><b>
		<fmt:formatNumber value="${totalCef}" pattern="#,##0.00"/>	
		</b></th>
		
	</tr>

	<tr>
		<th class="fundoDespesasFixas input-260px">Saldo Bancário  Bradesco:</th>
		<th class="fundoDespesasFixas input-160px"></th>
		<th class="fundoDespesasFixas input-160px"><fmt:formatDate value="${movimentoBradesco.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/></th>
		<th class="fundoDespesasFixas input-160px"></th>
		<th class="fundoDespesasFixas input-160px">=></th>
		<th class="fundoDespesasFixas input-160px" style="border: 2px solid #000;"><b>
		<fmt:formatNumber value="${totalBradesco}" pattern="#,##0.00"/>	
		</b></th>
		
	</tr>

	<tr>
		<th class="fundoRosa input-260px">Saldo Bancário Santander:</th>
		<th class="fundoRosa input-160px"></th>
		<th class="fundoRosa input-160px"><fmt:formatDate value="${movimentoSantander.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/></th>
		<th class="fundoRosa input-160px"></th>
		<th class="fundoRosa input-160px">=></th>
		<th class="fundoRosa input-160px" style="border: 2px solid #000"><b>
		<fmt:formatNumber value="${totalSantander}" pattern="#,##0.00"/>	
		</b></th>
		
	</tr>
	
				
	