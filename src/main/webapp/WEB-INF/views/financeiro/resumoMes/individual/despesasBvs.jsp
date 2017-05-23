<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<tr>
				<th class="fundotabela3 input-260px">BV's:</th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px">( + )</th>
				<th class="input-160px"> - </th>
				<th>0,0</th>
			</tr>

			<tr>
				<td colspan="7"></td>
			</tr>

			<tr>
				<th class="fundotabela3">Custo de Terceiros</th>
				<th class="input-160px"></th>
				<th>a pagar =></th>
				<th><fmt:formatNumber value="${custoTerceiros}" pattern="#,##0.00"/></th>
				<th>( - )</th>
				<th><fmt:formatNumber value="${pgtoExternas}" pattern="#,##0.00"/></th>
				<th></th>
			</tr>
			<tr>
				<td colspan="7"></td>
			</tr>

			<tr>
				<th class="fundotabela3">Faturamento mês</th>
				<th class="input-160px"></th>
				<th></th>
				<th></th>
				<th>=></th>
				<th><fmt:formatNumber value="${faturamentoMes}" pattern="#,##0.00"/></th>
				<th></th>
			</tr>
			<tr>
				<td colspan="7"></td>
			</tr>
			
			<tr>
				<th class="fundotabela3">Impostos:</th>
				<th></th>
				<th></th>
				<th></th>
				<th>( - )</th>
				<th><fmt:formatNumber value="${impostos}" pattern="#,##0.00"/></th>
				<th>42,10</th>
			</tr>
			<tr>
				<td colspan="7"></td>
			</tr>
			
			<tr>
				<th class="fundotabela3">Crédito de impostos:</th>
				<th></th>
				<th></th>
				<th>I. renda retido</th>
				<th>( - )</th>
				<th> - </th>
				<th></th>
			</tr>
			<tr>
				<td colspan="7"></td>
			</tr>

			<tr>
				<td colspan="5"></td>
				<td style="color: red;font-weight: bold;"><fmt:formatNumber value="${totalCustosFaturamentos}" pattern="#,##0.00"/></td>
				<td></td>
			</tr>
			
			<tr>
				<th class="fundotabela3 totalBancos" rowspan="3">Cachês:</th>
				<th>Equipe</th>
				<th></th>
				<th></th>
				<th>( - )</th>
				<th><fmt:formatNumber value="${somaCacheEquipe}" pattern="#,##0.00"/></th>
				<th></th>
			</tr>
			
			<tr>
				<th>Marcelo e Pedro</th>
				<th></th>
				<th></th>
				<th></th>
				<th><fmt:formatNumber value="${somaCacheDiretoria}" pattern="#,##0.00"/></th>
				<th></th>
			</tr>

			<tr>
				<th>TOTAL</th>
				<th></th>
				<th></th>
				<th></th>
				<th><fmt:formatNumber value="${somaCacheTotal}" pattern="#,##0.00"/></th>
				<th></th>
			</tr>
			
			
			<tr>
				<td colspan="7"></td>
			</tr>
			
			<tr class="fundotabela3">
				<th>Lucro Operacional:</th>
				<th>=></th>
				<th>=></th>
				<th>=></th>
				<th>=></th>
				<th style="font-weight: bold;">19.965,87</th>
				<th>34,5</th>
			</tr>