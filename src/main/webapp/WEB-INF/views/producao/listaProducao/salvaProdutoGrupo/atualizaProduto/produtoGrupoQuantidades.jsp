<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-md-4" style="font-size:12px;line-height: 27px;background-color: #F2F2F2;padding-bottom: 1px;height: 165px;">
   <div class="form-inline" style="margin-top:10px">Quantidade&nbsp&nbsp

		<input id="quantidade1" onblur="calculaValorTotal();" type="text" class="form-control voltar estiloInput"  value="${fn:replace(quantidade1,'.',',')}" name="quantidadeTransiente">
      X <input id="quantidade2" onblur="calculaValorTotal();" type="text" class="form-control voltar estiloInput is-disabled"  value="${fn:replace(quantidade2,'.',',')}" name="quantidade2Transiente" style="background-color: #ccc;">
      = <span id="QtdUnidadeItem">
      	<c:set var="diar" value="${quantidade1*quantidade2}" />
      	${fn:replace(diar,'.',',')}
        </span>&nbsp<span id="tipoUnidadeItem"> ${detalhesItem.idProduto.unidade.unidade}</span>

      <div class="form-inline" style="margin-left:22px">
         X Diárias
         <input id="diarias" onblur="calculaValorTotal();"type="text" class="form-control voltar" style="  height: 24px;width: 50px;font-size: 11px;padding: 5px;" value="${fn:replace(diarias,'.',',')}" name="diariasTransiente">
      </div>
      
      <div class="form-group" style="font-size: 11px;color: #b2b2b2;">
         Custo total: R$
         <span id="custoTotal">
            <fmt:formatNumber value="${detalhesItem.custoProduto*(detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" pattern="#,##0.00" />
         </span>
      </div>
      <div class="divider"></div>
      <div class="form-group col-md-12" style="background: #e4eef2;padding: 2px 3px 2px 3px;">
         Valor s/ NF R$ 
         <span id="valorSemNF">
            <fmt:formatNumber value="${detalhesItem.precoProduto*(detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" pattern="#,##0.00" />
         </span>
         </span>&nbsp[ 
         <span id="bvValor">
            <fmt:formatNumber value="${detalhesItem.bv*(detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" pattern="#,##0.00" />
         </span>
         </span> ]
      </div>
      <div class="divider"></div>
      <div class="form-group col-md-12" style="background: #e4eef2;padding: 2px 3px 2px 3px;font-weight:bold;margin-top: 5px;">
         Valor Final: R$ 
         <span id="valorFinal">
            <c:if test="${detalhesItem.imposto== true}">
               <fmt:formatNumber value="${formulaImposto *(detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" pattern="#,##0.00" />
            </c:if>
            <c:if test="${detalhesItem.imposto== false}">
               <fmt:formatNumber value="${precoSNF *(detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" pattern="#,##0.00" />
            </c:if>
         </span>
      </div>
   </div>
</div>


</div>
</div>  

