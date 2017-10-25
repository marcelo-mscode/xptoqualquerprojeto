<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="corRosaEstranha"><b>SANTANDER MOVIMENTOS DE CAIXA - TARIFAS/CPMF</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataSantander" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descSantander" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="valorSantander" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataSantander','descSantander','valorSantander','salvaNovaTarifa',${idAnalitico},'santanderTarifas','4');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
       <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>		
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
    
    <c:set var="totalSantander" value="0.00" />
    <c:forEach items="${tarifasSantander}" var="tarifasSantander">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataSantander${tarifasSantander.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${tarifasSantander.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataSantander${tarifasSantander.idMovBancos}');"
                onblur="editaValoresSaidas('editaTarifas','dataSantander${tarifasSantander.idMovBancos}',${tarifasSantander.idMovBancos},'data','santanderTarifas','4');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoSantander${tarifasSantander.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${tarifasSantander.descricao}"
                onblur="editaValoresSaidas('editaTarifas','descricaoSantander${tarifasSantander.idMovBancos}',${tarifasSantander.idMovBancos},'descricao','santanderTarifas','4');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${tarifasSantander.valor < 0}">style='color:red'</c:if> >
             <input id="valorSantander${tarifasSantander.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${tarifasSantander.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresSaidas('editaTarifas','valorSantander${tarifasSantander.idMovBancos}',${tarifasSantander.idMovBancos},'valor','santanderTarifas','4');"
             /> 
          </td>
          <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>	
		  <td style="padding: 20px !important;"><a href=""><i class="glyphicon glyphicon-trash"></i></a></td>
       </tr>
       <c:set var="totalSantander" value="${totalSantander+tarifasSantander.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalSantander}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
