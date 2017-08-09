<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="corMarronEstranha"><b>BRADESCO MOVIMENTOS DE CAIXA - ENTRADAS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="ndMovBradesc" class="ajusteInput2 tiraPaddingData input-70px" type="text" placeholder="ND/NF"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descMovBradesc" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="dataMovBradesc" type="date"  class="ajusteInput2 tiraPaddingData input-140px"/></td>		
       <td class="tiraPaddingData"><input id="valorMovBradesc" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Valor"/></td>
       <td><button onclick="InsereDadosMovimentacao('ndMovBradesc','dataMovBradesc','descMovBradesc','valorMovBradesc','salvaNovaEntrada',${idAnalitico},'bradescoEntrada','3');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>NF/ND</td>
       <td colspan="3">Descrição</td>
       <td>VENCIMENTO</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalMovBradesc" value="0.00" />
    <c:forEach items="${entradasBradesco}" var="entradasBradesco">
       <tr>
		  <td class="tiraPaddingData" colspan="1">
              <input id="ndnfBradesco${entradasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasBradesco.ndnf}"
              onblur="editaValoresEntradas('editaMovimentacaoFinanceira','ndnfBradesco${entradasBradesco.idMovBancos}',${entradasBradesco.idMovBancos},'ndnf','bradescoEntrada','3');"/>
          </td>     
          
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoMovBradesco${entradasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasBradesco.descricao}"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','descricaoMovBradesco${entradasBradesco.idMovBancos}',${entradasBradesco.idMovBancos},'descricao','bradescoEntrada','3');"
                />
          </td>
       	  <td class="tiraPaddingData"><input id="dataMovBradesc${entradasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${entradasBradesco.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataMovBradesc${entradasBradesco.idMovBancos}');"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','dataMovBradesc${entradasBradesco.idMovBancos}',${entradasBradesco.idMovBancos},'data','bradescoEntrada','3');"
                /></td>
          <td class="tiraPaddingData"  <c:if test = "${entradasBradesco.valor < 0}">style='color:red'</c:if> >
             <input id="valorMovBradesc${entradasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${entradasBradesco.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresEntradas('editaMovimentacaoFinanceira','valorMovBradesc${entradasBradesco.idMovBancos}',${entradasBradesco.idMovBancos},'valor','bradescoEntrada','3');"
             /> 
          </td>
       </tr>
       <c:set var="totalMovBradesc" value="${totalMovBradesc+entradasBradesco.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalMovBradesc}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
