<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="amareloFlat"><b>MOVIMENTOS DE CAIXA - ENTRADA</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="ndMovItau" class="ajusteInput2 tiraPaddingData input-70px" type="text" placeholder="ND/NF"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descMovItau" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"  value="teste de script"/></td>
       <td class="tiraPaddingData"><input id="dataMovItau" type="date"  class="ajusteInput2 tiraPaddingData input-140px" value="2017-02-02"/></td>		
       <td class="tiraPaddingData"><input id="valorMovItau" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor" value="200.00"/></td>
       <td><button onclick="InsereDadosMovimentacao('ndMovItau','dataMovItau','descMovItau','valorMovItau','salvaNovaEntrada',${InfoAnalitico.idAnalitico},'entradasItau','1');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
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
    
    <c:set var="totalentradasItau" value="0.00" />
    <c:forEach items="${entradasItau}" var="entradasItau">
       <tr>
          <td class="tiraPaddingData" colspan="3">
              <input id="ndnfItau${entradasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasItau.ndnf}"
              onblur="editaValoresEntradas('editaMovimentacaoFinanceira','ndnfItau${entradasItau.idMovBancos}',${entradasItau.idMovBancos},'ndnf','entradasItau');"/>
          </td>

       	  <td class="tiraPaddingData">
             <input id="descricaoEntradaItau${entradasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasItau.descricao}"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','descricaoEntradaItau${entradasItau.idMovBancos}',${entradasItau.idMovBancos},'descricao','entradasItau');"
             />
          </td>
          
          <td class="tiraPaddingData"  <c:if test = "${entradasItau.valor < 0}">style='color:red'</c:if> >
          		<input id="dataEntradaItau${entradasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${entradasItau.data}" pattern="dd/MM"/>"
                onclick="mudaCampoData('dataOutrasdespesas${entradasItau.idMovBancos}');"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','dataEntradaItau${entradasItau.idMovBancos}',${entradasItau.idMovBancos},'data','entradasItau');"
                />
          </td>

          <td class="tiraPaddingData"  <c:if test = "${entradasItau.valor < 0}">style='color:red'</c:if> >
          		<input id="valorOutrasdespesas${entradasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${entradasItau.valor}" pattern="#,##0.00"/>"
             	onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','valorOutrasdespesas${entradasItau.idMovBancos}',${entradasItau.idMovBancos},'valor','entradasItau');"
            	 /> 
          </td>
       </tr>
       <c:set var="totalentradasItau" value="${totalentradasItau+entradasItau.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalentradasItau}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
