<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-bordered table-hover" style="margin-top: 20px;">
<tr>
	<th>Parcela</th>
	<th>Prazo Pagamento</th>
	<th>Data</th>
	<th>Valor R$</th>
</tr>

<c:forEach items="${numParcelas}" var="numDaParcela" varStatus="status">
	<c:set var="qtdPar" value="${status.index+1}" />
</c:forEach>


<c:forEach items="${numParcelas}" var="numDaParcela" varStatus="status">


	<tr class="testePegaValorInput">
		<td style="padding: 0; text-align: center; vertical-align: middle; width: 50px;">
		<input type="hidden" value="${numDaParcela}" name="parcela[${status.index}]" />
		${numDaParcela}
		</td>
		
		
		<td style="padding: 0; width: 135px;">
		 	<input type="text" class="input-quantDeterm" style="width: 110px; text-align: center;"
			name="prazo[${status.index}]" value="${diasPrazo}" onblur="calculaPrazo(${numDaParcela},'data[${status.index}]');" id="diasPrazo${numDaParcela}" />
		</td>
		
		
		
		
		<td id="prazoPagamento${numDaParcela}" style="padding: 0; text-align: center; vertical-align: middle; width: 135px;">
			<c:if test="${empty prazoPagamento}">
	            -------------							
	        </c:if>
	        <c:if test="${not empty prazoPagamento}">
	        	<input type="hidden" value="${prazoPagamento}" name="data[${status.index}]" />
				<fmt:formatDate value="${prazoPagamento}" pattern="dd/MM/yyyy" />
			</c:if>
		</td>
	
		<td style="padding: 0">
		
			<c:if test="${not status.last}">
	 		    <input id="valorItem${numDaParcela}" type="text" class="input-quantDeterm valorItem${numDaParcela}" style="width: 110px" name="valor[${status.index}]"
				value='<fmt:formatNumber value="${valorParcela}" pattern="#,##0.00"/>'
				onblur="calculaPagamento(${qtdPar},${idProdutoGrupo},'valorItem${numDaParcela}',${numDaParcela});" />
			</c:if>
	
			<c:if test="${status.last}">
				<input id="valorItem${numDaParcela}" type="text" class="input-quantDeterm valorItem${numDaParcela} is-disabled" style="width: 110px" name="valor[${status.index}]"
				value='<fmt:formatNumber value="${valorParcela}" pattern="#,##0.00"/>' />
			</c:if>
		
		</td>
	</tr>


</c:forEach>
</table>
