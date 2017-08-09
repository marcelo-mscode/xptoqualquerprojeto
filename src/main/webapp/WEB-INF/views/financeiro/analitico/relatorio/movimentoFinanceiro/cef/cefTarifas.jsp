<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="verdeClaroFlat"><b>CEF MOVIMENTOS DE CAIXA - TARIFAS/CPMF</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataTarifasCEF" type="date"  class="ajusteInput2 tiraPaddingData input-140px"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descTarifasCEF" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="valorTarifasCEF" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataTarifasCEF','descTarifasCEF','valorTarifasCEF','salvaNovaTarifa',${idAnalitico},'cefTarifas','2');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totaltarifasCEF" value="0.00" />
    <c:forEach items="${tarifasCEF}" var="tarifasCEF">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataTarifasCEF${tarifasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${tarifasCEF.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataTarifasCEF${tarifasCEF.idMovBancos}');"
                onblur="editaValoresSaidas('editaTarifas','dataTarifasCEF${tarifasCEF.idMovBancos}',${tarifasCEF.idMovBancos},'data','cefTarifas','2');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaotarifasCEF${tarifasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${tarifasCEF.descricao}"
                onblur="editaValoresSaidas('editaTarifas','descricaotarifasCEF${tarifasCEF.idMovBancos}',${tarifasCEF.idMovBancos},'descricao','cefTarifas','2');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${tarifasCEF.valor < 0}">style='color:red'</c:if> >
             <input id="valortarifasCEF${tarifasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${tarifasCEF.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresSaidas('editaTarifas','valortarifasCEF${tarifasCEF.idMovBancos}',${tarifasCEF.idMovBancos},'valor','cefTarifas','2');"
             /> 
          </td>
       </tr>
       <c:set var="totaltarifasCEF" value="${totaltarifasCEF+tarifasCEF.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totaltarifasCEF}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
