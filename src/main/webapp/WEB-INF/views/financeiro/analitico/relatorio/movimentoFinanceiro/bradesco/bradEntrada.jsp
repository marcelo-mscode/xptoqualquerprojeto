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
       <td class="tiraPaddingData"><input id="ndMovBradesc" class="ajusteInput2 tiraPaddingData input-70px" type="text" placeholder="ND/NF" value="998877"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descMovBradesc" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição" value="novas testess"/></td>
       <td class="tiraPaddingData"><input id="dataMovBradesc" type="date"  class="ajusteInput2 tiraPaddingData input-140px"  value="2017-07-07"/></td>		
       <td class="tiraPaddingData"><input id="valorMovBradesc" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor" value="120,00"/></td>
       <td><button onclick="InsereDadosMovimentacao('ndMovBradesc','dataMovBradesc','descMovBradesc','valorMovBradesc','salvaNovaEntrada',${idAnalitico},'bradescoEntrada','3');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>NF/ND</td>
       <td colspan="3">Descrição</td>
       <td>VENC</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalMovBradesc" value="0.00" />
    <c:forEach items="${entradasBradesc}" var="entradasBradesc">
       <tr>
		<td>ND/NF</td>       
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoMovBradesc${entradasBradesc.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasBradesc.descricao}"
                onblur="editaCamposAnaliticoDespesas('editaMovBradesc','descricaoMovBradesc${entradasBradesc.idMovBancos}',${entradasBradesc.idMovBancos},'descricao','MovBradesc');"
                />
          </td>
       	  <td class="tiraPaddingData"><input id="dataMovBradesc${entradasBradesc.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${entradasBradesc.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataMovBradesc${entradasBradesc.idMovBancos}');"
                onblur="editaCamposAnaliticoDespesas('editaMovBradesc','dataMovBradesc${entradasBradesc.idMovBancos}',${entradasBradesc.idMovBancos},'data','MovBradesc');"
                /></td>
          <td class="tiraPaddingData"  <c:if test = "${entradasBradesc.valor < 0}">style='color:red'</c:if> >
             <input id="valorMovBradesc${entradasBradesc.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${entradasBradesc.valor}" pattern="#,##0.00"/>"
             onblur="editaCamposAnaliticoDespesas('editaMovBradesc','valorMovBradesc${entradasBradesc.idMovBancos}',${entradasBradesc.idMovBancos},'valor','MovBradesc');"
             /> 
          </td>
       </tr>
       <c:set var="totalMovBradesc" value="${totalMovBradesc+MovBradesc.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalMovBradesc}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
