<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-striped table-hover table-bordered bordaDestaque">
  <tbody id="prospeccaoFiltro">
	<tr>		
		<td colspan="4" align="center" style="border-right: 0px solid #ccc;">BV'S</td>
		<td></td>
	</tr>
	<tr style="background: #f1f1f1 !important">
		<td>Fornecedor</td>
		<td class="descricao">Valor</td>
		<td class="descricao">Pagto</td>
		<td class="descricao">R$</td>
		<td class="descricao">À pagar</td>
	</tr>


	<c:forEach items="${relBVS}" var="relatorioBVS"> 
			<tr>
				<td>${relatorioBVS.nomeFornecedor}</td>
				<td class="ajusteValores"><fmt:formatNumber value="${relatorioBVS.valorFornecedor}" pattern="#,##0.00"/></td>
				<td class="ajusteValores">
	
						<fmt:formatNumber value="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca}" pattern="#,##0.00"/>

<%-- 					<c:if test="${relatorioBVS.idFornecedor != '6961'}">
						<fmt:formatNumber value="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca}" pattern="#,##0.00"/>
					</c:if>
					<c:if test="${relatorioBVS.idFornecedor == '6961'}">
						-
					</c:if>
--%>

				</td>
				<td class="ajusteValores">
					<c:if test="${relatorioBVS.diferenca != '0.00'}">
						<fmt:formatNumber value="${relatorioBVS.diferenca}" pattern="#,##0.00"/>
					</c:if>
	
					<c:if test="${relatorioBVS.diferenca == '0.00'}">
						-
					</c:if>
					
				</td>
				<td class="ajusteValores">
				<c:if test="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca != '0.00'}">
					<fmt:formatNumber value="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca}" pattern="#,##0.00"/>
				</c:if>
				
				<c:if test="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca == '0.00'}">
					-
				</c:if>
				</td>
			</tr>
	</c:forEach>
	
	<c:if test="${depesasEventos != null}">
		<tr>
			<td>Despesas VT</td>
			<td></td>
			<td class="ajusteValores"><fmt:formatNumber value="${depesasEventos}" pattern="#,##0.00"/></td>
			<td class="ajusteValores">(<fmt:formatNumber value="${depesasEventos}" pattern="#,##0.00"/>)</td>
			<td></td>
		</tr>
	</c:if>
	
	

	<c:forEach var="i" begin="1" end="3">
	  		<tr>
				<td style="padding: 18px;"></td>
				<td style="padding: 18px;"></td>
				<td style="padding: 18px;"></td>
				<td style="padding: 18px;"></td>
				<td style="padding: 18px;"></td>
			</tr>
	</c:forEach>


	<tr>
		<td class="colorBlue fontBold">Mcontribuição</td>
		<td></td>
		<td class="colorBlue fontBold"><fmt:formatNumber value="${relatorio.margemContribuicao}" pattern="#,##0.00"/></td>
		<td class="colorBlue fontBold">(<fmt:formatNumber value="${relatorio.margemContribuicao}" pattern="#,##0.00"/>)</td>
		<td></td>
	</tr>
	
	<tr>
		<td class="colorRed fontBold">Custo Telefone</td>
		<td></td>
		<td class="colorRed fontBold"><fmt:formatNumber value="${relatorio.custoTelefone}" pattern="#,##0.00"/></td>
		<td class="colorRed fontBold">(<fmt:formatNumber value="${relatorio.custoTelefone}" pattern="#,##0.00"/>)</td>
		<td></td>
	</tr>
	
	<tr>
		<td>FEE Reduzido 7%</td>
		<td><fmt:formatNumber value="${relatorio.feeReduzido}" pattern="#,##0.00"/></td>
		<td></td>
		<td><fmt:formatNumber value="${relatorio.feeReduzido}" pattern="#,##0.00"/></td>
		<td></td>
	</tr>
	
	
	<tr>
		<td>FEE 14%</td>
		<%-- <td><fmt:formatNumber value="${infoLista.administracaoValor}" pattern="#,##0.00"/></td> --%>
		<td><fmt:formatNumber value="${relatorio.fee}" pattern="#,##0.00"/></td>
		<td></td>
		<td><fmt:formatNumber value="${relatorio.fee}" pattern="#,##0.00"/></td>
		<td></td>
	</tr>
	
	<tr>
		<td>Imposto cliente 22,90%</td>
		
		<c:if test="${relatorio.ndFatDireto == false}">
			<td><fmt:formatNumber value="${infoLista.impostoValor}" pattern="#,##0.00"/></td>
			<td></td>
			<td><fmt:formatNumber value="${infoLista.impostoValor - relatorio.impostoSobreValorLoccoAgencia}" pattern="#,##0.00"/></td>
		</c:if>
		<c:if test="${relatorio.ndFatDireto == true}">
			<td>0,00</td>
			<td></td>
			<td>0,00</td>
		</c:if>
		
		<td></td>
	</tr>
	
	
	
	<tr class="bordaDestaque">
		<td><b>TOTAL</b></td>
		<td><b><fmt:formatNumber value="${relatorio.valorLoccoAgenc}" pattern="#,##0.00"/></b></td>
		<td><b><fmt:formatNumber value="${relatorio.pgtoExternas}" pattern="#,##0.00"/></b></td>
		<td><b><fmt:formatNumber value="${relatorio.totalDiferenca}" pattern="#,##0.00"/></b></td>
		<td>
	
		<c:set var="valorApagar" value="" />
		<c:forEach items="${relBVS}" var="relatorioBVS"> 
			<c:set var="valorApagar" value="${valorApagar + (relatorioBVS.valorFornecedor - relatorioBVS.diferenca)}" />
		</c:forEach>
			<b><fmt:formatNumber value="${valorApagar}" pattern="#,##0.00"/></b>
		</td>
		
		
	</tr>
	<tr>
		<td colspan="5" style="padding: 18px"></td>
	</tr>
	<tr>
		<td></td>
		<td  class="esquerda topo" colspan="2" >Liq. Imp - total pagto</td>
		<td class="direita topo"><fmt:formatNumber value="${relatorio.totalDiferenca}" pattern="#,##0.00"/></td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td class="esquerda" colspan="2" >Total Caches int. e  Ext</td>
		<td class="direita"><fmt:formatNumber value="${relatorio.totalCachesIntExt}" pattern="#,##0.00"/></td>
		<td></td>
	</tr>
	
	<tr>
		<td ></td>
		<td class="esquerda" colspan="2"></td>
		<td class="direita"><fmt:formatNumber value="${relatorio.diferencaCacheFuncionariosTotalPgto}" pattern="#,##0.00"/></td>
			<td></td>
		</tr>
		
		
	</tbody>
</table>






