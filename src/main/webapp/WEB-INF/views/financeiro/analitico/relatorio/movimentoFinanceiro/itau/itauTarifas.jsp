<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="8" align="center" class="amareloFlat"><b>MOVIMENTOS DE CAIXA - TARIFAS/CPMF</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataItauTarifas" type="date"  class="ajusteInput2 tiraPaddingData input-140px"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descItauTarifas" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="valorItauTarifas" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataItauTarifas','descItauTarifas','valorItauTarifas','salvaNovaTarifa',${idAnalitico},'itauTarifas','1');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
       <td></td>	
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
	   <td>Fixo</td>
       <td>Excluir</td>
       
    </tr>
    <tr>
       <td colspan="8"></td>
    </tr>
    
    <c:set var="totaltarifasItau" value="0.00" />
    <c:forEach items="${tarifasItau}" var="tarifasItau">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataTarifasItau${tarifasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${tarifasItau.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataTarifasItau${tarifasItau.idMovBancos}');"
                onblur="editaValoresSaidas('editaTarifas','dataTarifasItau${tarifasItau.idMovBancos}',${tarifasItau.idMovBancos},'data','itauTarifas','1');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoTarifasItau${tarifasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${tarifasItau.descricao}"
                onblur="editaValoresSaidas('editaTarifas','descricaoTarifasItau${tarifasItau.idMovBancos}',${tarifasItau.idMovBancos},'descricao','itauTarifas','1');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${tarifasItau.valor < 0}">style='color:red'</c:if> >
             <input id="valortarifasItau${tarifasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${tarifasItau.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresSaidas('editaTarifas','valortarifasItau${tarifasItau.idMovBancos}',${tarifasItau.idMovBancos},'valor','itauTarifas','1');"
             /> 
          </td>
          <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>	
		  <td style="padding: 20px !important;"><a href=""><i class="glyphicon glyphicon-trash"></i></a></td>
       </tr>
       <c:set var="totaltarifasItau" value="${totaltarifasItau+tarifasItau.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totaltarifasItau}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
