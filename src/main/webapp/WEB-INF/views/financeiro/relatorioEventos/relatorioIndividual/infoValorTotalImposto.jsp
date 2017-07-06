<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-striped table-hover table-bordered bordaDestaque" >
	  <tbody id="prospeccaoFiltro">
		<tr>
			<td colspan="2" class="descricao" style="border-left: 2px solid #ccc !important">Valor Locco Agência =></td>
			<td class="descricao"><b><fmt:formatNumber value="${relatorio.valorLoccoAgenc}" pattern="#,##0.00"/></b></td>
		</tr>
		<tr>
			<td class="descricao" style="border-left: 2px solid #ccc !important">% Imposto =></td>
			<td class="descricao"><b>${infoInterna.impostoInterna}%</b></td>
			<td class="descricao"><b><fmt:formatNumber value="${relatorio.impostoSobreValorLoccoAgencia}" pattern="#,##0.00"/></b></td>
		</tr>
	  </tbody>
</table>