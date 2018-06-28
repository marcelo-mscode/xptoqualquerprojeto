<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<table class="table table-striped table-hover table-bordered bordaDestaque">
	  <tbody id="prospeccaoFiltro">
	  <tr>
	  	<td align="center" colspan="2" >Liquido de Impostos =></td>
	  	<td align="center">
	  		
	  		<c:if test="${relatorio.ndFatDireto == true}">
		  		<b><fmt:formatNumber value="${relatorio.valorLoccoAgenc}" pattern="#,##0.00"/></b>
	  		</c:if>
	  		<c:if test="${relatorio.ndFatDireto == false}">
	  			<b><fmt:formatNumber value="${relatorio.valorLoccoAgenc - (relatorio.valorLoccoAgenc * (infoInterna.impostoInterna/100))}" pattern="#,##0.00"/></b>
	  		</c:if>
	  		
	  		</td>
	  </tr>
	  <tr>
	  	<td colspan="3" style="padding: 18px"></td>
	  </tr>
	  
	  <tr>
	  	<td colspan="3" align="center">RESUMO</td>
	  </tr>
	  
	  
	  <tr>
	 		<td></td>
	 		<td></td>
	 		<td></td>
	 	</tr>
		<tr>
			<td>Serviços</td>
			<td></td>
			<td><fmt:formatNumber value="${relatorio.valorLoccoAgenc}" pattern="#,##0.00"/></td>
		</tr>	
	 		
		<tr>
			<td>Impostos</td>
			<td>( - )</td>
			<td><fmt:formatNumber value="${relatorio.impostoSobreValorLoccoAgencia}" pattern="#,##0.00"/></td>
		</tr>	
	 		
		<tr>
			<td>Vlr Liquido</td>
			<td></td>
			<td><fmt:formatNumber value="${relatorio.valorLoccoAgenc - relatorio.impostoSobreValorLoccoAgencia}" pattern="#,##0.00"/></td>
		</tr>	
	 	
	 	
		<tr>
			<td>Caches</td>
			<td>(-)</td>
			<td><fmt:formatNumber value="${relatorio.totalCachesComTelefone}" pattern="#,##0.00"/></td>
		</tr>	
	 	
	 	
		<tr>
			<td>BV'S</td>
			<td>(+)</td>
			<td>-</td>
		</tr>	
	 	
	 	<tr>
	 		<td colspan="2"></td>
	 		<td></td>
	 	</tr>

		<tr>
			<td colspan="3">Pagamentos</td>
		</tr>	
	 	
		<tr>
			<td>Internas</td>
			<td>(-)</td>
			<td>-</td>
		</tr>	
	 	
	 	
		<tr>
			<td>Externas</td>
			<td>(-)</td>
			<td><fmt:formatNumber value="${relatorio.pgtoExternas}" pattern="#,##0.00"/></td>
		</tr>	
	 	
	 	<c:forEach var="i" begin="1" end="1">
  		<tr>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
		</tr>
		</c:forEach>
	 	
	 	<tr>
	 		<td colspan="2" style="padding: 8px;"></td>
	 		<td style="padding: 8px;"></td>
	 	</tr>
		<tr>
			<td colspan="2">Giro Inicial s/ Telefone</td>
			<td><fmt:formatNumber value="${relatorio.giroEvento.giroSemTelefone}" pattern="#,##0.00"/></td>
		</tr>	
	 	<tr>
	 		<td colspan="2" style="padding: 11px;"> </td>
	 		<td style="padding: 11px;"></td>
	 	</tr>
	 	
		<tr class="bordaDestaque">
			<td colspan="2" >Giro</td>
			<td><fmt:formatNumber value="${relatorio.giroEvento.giroComTelefone}" pattern="#,##0.00"/></td>
		</tr>	
		
		<tr>
			<td style="padding: 18px"  colspan="3" ></td>
		</tr>
		<tr>
			<td>VENC</td>
			<td>NFE</td>
			<td>VALOR</td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${infoLista.infoInterna.dataPagamento}" pattern="dd/MM/yyyy"/> </td>
			<td>${infoLista.infoInterna.nfInterna}</td>
			<td><fmt:formatNumber value="${relatorio.valorLoccoAgenc}" pattern="#,##0.00"/></td>
		</tr>
		
		

		</tbody>
</table>



