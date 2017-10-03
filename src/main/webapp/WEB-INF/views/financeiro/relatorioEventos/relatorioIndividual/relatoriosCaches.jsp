<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
.ajusteInput {
    border: none;
    height: 20px;
    padding: 18px;
    width: 75px;
    text-align: center;
</style>

 <table class="table table-striped table-hover table-bordered bordaDestaque">
	  <tbody id="prospeccaoFiltro">
	  <tr>
	  	<td colspan="3" align="center">Cachês</td>
	  </tr>
		<tr style="background: #f1f1f1 !important">
			<td>Nome</td>
			<td style="width: 50px;text-align: center;">%</td>
			<td>Valor</td>
	 	
	 	<c:forEach items="${relatorioCaches}" var="relatorioCaches">
		 	<tr>
				<td>${relatorioCaches.cachePadrao.nomeFunc}-${relatorio.idRelatorioEvento}</td>
				<td style="padding: 0;margin: 0;text-align: center;">
					<input class="ajusteInput"  value="${relatorioCaches.razaoPorcentagem*100}" onblur="mudaCache(${relatorio.idRelatorioEvento}, ${relatorioCaches.cachePadrao.idCachePadrao});" id="mudaCacheFunc${relatorioCaches.cachePadrao.idCachePadrao}"/>
				</td>
				<td><fmt:formatNumber value="${relatorioCaches.valor}" pattern="#,##0.00"/></td>
		 	</tr>	
	 	 </c:forEach>
	 	
	<c:forEach var="i" begin="1" end="4">
  		<tr>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
		</tr>
	</c:forEach>
		
		<tr class="bordaDestaque">
			<td>TOTAL</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.totalCache}" pattern="#,##0.00"/></td>
		</tr>
		
		<tr>
			<td>Equipe In</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.cacheEquipIn}" pattern="#,##0.00"/></td>
		</tr>
		
		<tr>
			<td>Equipe Ex</td>
			<td colspan="2"></td>
		</tr>
		
		<tr>
			<td>Ma e Pe</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.diretoria1}" pattern="#,##0.00"/></td>
		</tr>
		
		<tr>
			<td>Célia</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.diretoria2}" pattern="#,##0.00"/></td>
		</tr>
	  </tbody>
</table>
