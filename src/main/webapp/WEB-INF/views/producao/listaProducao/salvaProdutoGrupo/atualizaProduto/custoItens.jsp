<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="custoProduto" value="${detalhesItem.custoProduto}" />
<c:set var="precoSNF" value="${detalhesItem.custoProduto + detalhesItem.bv}" /> 
<c:set var="bv" value="${detalhesItem.bv}" />



<div class="col-md-4" style="border-right: 15px solid #fff; font-size: 12px; line-height: 20px; background-color: #F2F2F2; padding-bottom: 4px; height: 165px;">

	<div class="form-inline" style="margin-top: 10px">
		Custo Net Unit. R$&nbsp
		<input id="custoItemunitario" type="text" name="custoProdutoTransiente" class="form-control" style="height: 24px; width: 110px;"
			value="<fmt:formatNumber value='${custoProduto}' pattern="#,##0.00" />" onblur="calculaPrecoSNF();">
	</div>

	<div class="divisor"></div>
	<div class="form-inline">
		CL. Venda R$ &nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp
		<input type="text" name="bvTransiente" onblur="calculaPrecoSNF();" id="bvFornecedorValorEdita" class="form-control"
		style="height: 24px; width: 110px;" value="<fmt:formatNumber value="${bv}" pattern="#,##0.00" />">
	</div>
	
	<div class="divisor-fino"></div>
	
	<div class="form-inline"
		style="background: #e4eef2; padding: 3px 3px 3px 2px;">
		Preço Unit. s/ NF R$
		
		<span id="precoUnitSemNF">
		
			 <span id="custoTotalSFNCalculado">
			 	<fmt:formatNumber value="${precoSNF}" pattern="#,##0.00" />
			 </span>
			 &nbsp&nbsp
			 [<span id="tiraUnitPeloBv">
			 	<fmt:formatNumber value="${bv}" pattern="#,##0.00" />
			 </span>] 
		 
		 </span>
		 
	</div>

	<div class="form-inline">
		Incide Imposto &nbsp&nbsp
		<c:if test="${detalhesItem.imposto == true}">
			<input type="checkbox" checked="checked" name="imposto" id="incideimpostoCheckbox" onclick="calculaPrecoSNF();">
		</c:if>

		<c:if test="${detalhesItem.imposto == false}">
			<input type="checkbox" name="imposto" id="incideimpostoCheckbox" onclick="calculaPrecoSNF();">
		</c:if>

		&nbsp<span>% ${Impostogrupo.imposto}</span>
	</div>

	<div class="form-inline" style="background: #e4eef2; padding: 3px 3px 3px 2px;">
		Preço Unit. Final R$ <span id="precoUnitFinal">
		  <c:if test="${detalhesItem.imposto == true}">
				<fmt:formatNumber value="${formulaImposto}" pattern="#,##0.00" />
		  </c:if>
		  
		  <c:if test="${detalhesItem.imposto == false}">
				<fmt:formatNumber value="${precoSNF}" pattern="#,##0.00" />
			</c:if>

		</span>
	</div>

</div>