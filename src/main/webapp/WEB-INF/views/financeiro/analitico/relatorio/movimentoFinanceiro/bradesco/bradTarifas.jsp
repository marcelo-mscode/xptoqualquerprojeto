<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="corMarronEstranha"><b>BRADESCO MOVIMENTOS DE CAIXA - TARIFAS/CPMF</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataBradesco" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descBradesco" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="valorBradesco" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataBradesco','descBradesco','valorBradesco','salvaNovaTarifa',${idAnalitico},'bradescoTarifas','3');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalBradesco" value="0.00" />
    <c:forEach items="${tarifasBradesco}" var="tarifasBradesco">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataBradesco${tarifasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${tarifasBradesco.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataBradesco${tarifasBradesco.idMovBancos}');"
                onblur="editaValoresSaidas('editaTarifas','dataBradesco${tarifasBradesco.idMovBancos}',${tarifasBradesco.idMovBancos},'data','bradescoTarifas','3');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoBradesco${tarifasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${tarifasBradesco.descricao}"
                onblur="editaValoresSaidas('editaTarifas','descricaoBradesco${tarifasBradesco.idMovBancos}',${tarifasBradesco.idMovBancos},'descricao','bradescoTarifas','3');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${tarifasBradesco.valor < 0}">style='color:red'</c:if> >
             <input id="valorBradesco${tarifasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${tarifasBradesco.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresSaidas('editaTarifas','valorBradesco${tarifasBradesco.idMovBancos}',${tarifasBradesco.idMovBancos},'valor','bradescoTarifas','3');"
             /> 
          </td>
       </tr>
       <c:set var="totalBradesco" value="${totalBradesco+tarifasBradesco.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalBradesco}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
