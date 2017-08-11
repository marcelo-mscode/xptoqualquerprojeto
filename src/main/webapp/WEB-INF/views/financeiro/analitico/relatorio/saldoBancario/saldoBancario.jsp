<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-bordered table-hover bordaDestaque" style="width: 59%;">
  <tbody>
     <tr>	
     	<td colspan="13" align="center" class="corEscritorio" style="border-right: 2px solid #ccc"><b>SALDO BANCÁRIO</b></td>
      </tr>
      <tr>
      	<td><b>Saldo ITAU</b></td>
      	<td><fmt:formatDate value="${movimentoItau.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
      	<td><fmt:formatNumber value="${movimentoItau.valorAbertura}" pattern="#,##0.00"/></td>
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoItau.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoItau.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoItau.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo Itau</b></td>
      	<td>31/01/2017</td>
      	<td><fmt:formatNumber value="${movimentoItau.valorAbertura -
      								   movimentoItau.totalTarifas + 
      								   movimentoItau.totalCreditos - 
      								   movimentoItau.totalDebitos}" pattern="#,##0.00"/></td>
      	<td> 53.238,00 ?????</td>
      </tr>
      
      <tr>
      	<td><b>Saldo CEF</b></td>
      	<td><fmt:formatDate value="${movimentoCef.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
      	<td><fmt:formatNumber value="${movimentoCef.valorAbertura}" pattern="#,##0.00"/></td>
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoCef.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoCef.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoCef.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo CEF</b></td>
      	<td>31/01/2017</td>
      	<td><fmt:formatNumber value="${movimentoCef.valorAbertura -
      								   movimentoCef.totalTarifas + 
      								   movimentoCef.totalCreditos - 
      								   movimentoCef.totalDebitos}" pattern="#,##0.00"/></td>
      	<td> 53.238,00 ?????</td>
      </tr>
      
      <tr>
      	<td><b>Saldo BRADESCO</b></td>
      	<td><fmt:formatDate value="${movimentoBradesco.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
      	<td><fmt:formatNumber value="${movimentoBradesco.valorAbertura}" pattern="#,##0.00"/></td>
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoBradesco.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoBradesco.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoBradesco.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo BRADESCO</b></td>
      	<td>31/01/2017</td>
      	<td><fmt:formatNumber value="${movimentoBradesco.valorAbertura -
      								   movimentoBradesco.totalTarifas + 
      								   movimentoBradesco.totalCreditos - 
      								   movimentoBradesco.totalDebitos}" pattern="#,##0.00"/></td>
      	<td> 53.238,00 ?????</td>
      </tr>

      <tr>
      	<td><b>Saldo SANTANDER</b></td>
      	<td><fmt:formatDate value="${movimentoSantander.dataAberturaCaixa}" pattern="dd/MM/yyyy"/></td>
      	<td><fmt:formatNumber value="${movimentoSantander.valorAbertura}" pattern="#,##0.00"/></td>
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoSantander.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoSantander.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoSantander.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo SANTANDER</b></td>
      	<td>31/01/2017</td>
      	<td><fmt:formatNumber value="${movimentoSantander.valorAbertura -
      								   movimentoSantander.totalTarifas + 
      								   movimentoSantander.totalCreditos - 
      								   movimentoSantander.totalDebitos}" pattern="#,##0.00"/></td>
      	<td> 53.238,00 ?????</td>
      </tr>
      
      
      
      
   </tbody>
</table>		