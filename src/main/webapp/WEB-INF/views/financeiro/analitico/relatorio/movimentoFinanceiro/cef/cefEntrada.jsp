<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="verdeClaroFlat"><b>CEF MOVIMENTOS DE CAIXA - ENTRADAS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="ndMovCEF" class="ajusteInput2 tiraPaddingData input-70px" type="text" placeholder="ND/NF" value="1211"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descEntradasCEF" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição" value="Teste CEF"/></td>
       <td class="tiraPaddingData"><input id="dataEntradasCEF" type="date"  class="ajusteInput2 tiraPaddingData input-140px" value="1980-04-23" /></td>		
       <td class="tiraPaddingData"><input id="valorEntradasCEF" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor" value="1200,00"/></td>
       <td><button onclick="InsereDadosMovimentacao('ndMovCEF','dataEntradasCEF','descEntradasCEF','valorEntradasCEF','salvaNovaEntrada',${idAnalitico},'cefEntrada','2');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>NF/ND</td>
       <td colspan="3">Descrição</td>
       <td>Vencimento</td>
       <td colspan="2">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalentradasCEF" value="0.00" />
    <c:forEach items="${entradasCEF}" var="entradasCEF">
       <tr>
          <td class="tiraPaddingData" colspan="3">
              <input id="ndnfCEF${entradasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasCEF.ndnf}"
              onblur="editaValoresEntradas('editaMovimentacaoFinanceira','ndnfCEF${entradasCEF.idMovBancos}',${entradasCEF.idMovBancos},'ndnf','cefEntrada','2');"/>
          </td>

       	  <td class="tiraPaddingData">
             <input id="descricaoEntradaCEF${entradasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasCEF.descricao}"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','descricaoEntradaCEF${entradasCEF.idMovBancos}',${entradasCEF.idMovBancos},'descricao','cefEntrada','2');"
             />
          </td>
          
          <td class="tiraPaddingData"  <c:if test = "${entradasCEF.valor < 0}">style='color:red'</c:if> >
          		<input id="dataEntradaCEF${entradasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${entradasCEF.data}" pattern="dd/MM"/>"
                onclick="mudaCampoData('dataEntradaCEF${entradasCEF.idMovBancos}');"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','dataEntradaCEF${entradasCEF.idMovBancos}',${entradasCEF.idMovBancos},'data','cefEntrada','2');"
                />
          </td>

          <td class="tiraPaddingData"  <c:if test = "${entradasCEF.valor < 0}">style='color:red'</c:if> >
          		<input id="valorCEF${entradasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${entradasCEF.valor}" pattern="#,##0.00"/>"
             	onblur="editaValoresEntradas('editaMovimentacaoFinanceira','valorCEF${entradasCEF.idMovBancos}',${entradasCEF.idMovBancos},'valor','cefEntrada','2');"
            	 /> 
          </td>
       </tr>
       <c:set var="totalentradasCEF" value="${totalentradasCEF+entradasCEF.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalentradasCEF}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
