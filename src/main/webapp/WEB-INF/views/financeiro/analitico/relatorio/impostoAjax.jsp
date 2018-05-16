<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-hover table-bordered">
   <tr>
      <td colspan="5" align="center" class="corEscritorio"><b>OUTROS IMPOSTOS</b></td>
   </tr>
   <tr>
      <td class="tiraPaddingData"><input id="descImpostos" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
      <td class="tiraPaddingData"><input id="valorImpostos" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
      <td><button onclick="editaCamposFinanceiro('descImpostos','valorImpostos','salvaNovoImposto',${idAnalitico},'impostos');" class="btn btn-default botaoMais">+</button> </td>
   	  <td style="padding: 15px !important;"><input type="checkbox" checked="checked"/> </td>	
   </tr>
   <tr>
      <td colspan="2">Descrição</td>
      <td>Valor</td>
      <td>Fixo</td>
      <td>Excluir</td>
   </tr>
   <tr>
      <td colspan="5"></td>
   </tr>
   <c:set var="totalImpostos" value="0.00" />
   <c:forEach items="${impostos}" var="impostos">
      <tr>
         <td colspan="2" class="tiraPaddingData">
            <input id="descricaoImpostos${impostos.idFinancImpostos}" class="ajusteInput2 input-160px tiraPaddingData" value="${impostos.descricao}"
               onblur="editaCamposAnalitico('editaFinancImposto','descricaoImpostos${impostos.idFinancImpostos}',${impostos.idFinancImpostos},'descricao','impostos');"
               />
         </td>
         <td class="tiraPaddingData"  <c:if test = "${impostos.valor < 0}">style='color:red'</c:if> >
            <input id="valorImpostos${impostos.idFinancImpostos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${impostos.valor}" pattern="#,##0.00"/>"
            onblur="editaCamposAnalitico('editaFinancImposto','valorImpostos${impostos.idFinancImpostos}',${impostos.idFinancImpostos},'valor','impostos');"
            /> 
         </td>
         
	     <td style="padding: 15px !important;">
		    <input type="checkbox" <c:if test = "${impostos.fixo == true}"> checked = checked</c:if > />
	     </td>
                           
         
         
         
         <td style="padding: 20px !important;">
          	<a href="excluiItemAnalitico?idAnalitico=${idAnalitico}&idTabela=${impostos.idFinancImpostos}&tabela=FinancImpostos">
          	<i class="glyphicon glyphicon-trash"></i></a>
         </td>
      </tr>
      <c:set var="totalImpostos" value="${totalImpostos+impostos.valor}" />
   </c:forEach>
   <tr>
      <td colspan="2"></td>
      <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
         <b>
            <fmt:formatNumber value="${totalImpostos}" pattern="#,##0.00"/>
         </b>
      </td>
      <td colspan="2"></td>
   </tr>
</table>