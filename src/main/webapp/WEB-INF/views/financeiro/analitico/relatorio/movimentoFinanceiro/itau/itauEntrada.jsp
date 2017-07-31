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
       <td class="tiraPaddingData" colspan="3"><input id="descMovItau" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="dataMovItau" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
       <td class="tiraPaddingData"><input id="valorMovItau" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="InsereDadosMovimentacao('ndMovItau','dataMovItau','descMovItau','valorMovItau','salvaNovaEntrada',${InfoAnalitico.idAnalitico},'outrasdespesas');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
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
              <input id="descricaoOutrasdespesas${entradasItau.idFinancItauEntrada}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasItau.nfnd}"
              onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','descricaoOutrasdespesas${entradasItau.idFinancItauEntrada}',${entradasItau.idFinancItauEntrada},'descricao','outrasdespesas');"/>
          </td>

       	  <td class="tiraPaddingData">
             <input id="descricaoOutrasdespesas${entradasItau.idFinancItauEntrada}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasItau.descricao}"
                onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','descricaoOutrasdespesas${entradasItau.idFinancItauEntrada}',${entradasItau.idFinancItauEntrada},'descricao','outrasdespesas');"
             />
          </td>
          
          <td class="tiraPaddingData"  <c:if test = "${entradasItau.valor < 0}">style='color:red'</c:if> >
          		
          		<input id="dataOutrasdespesas${entradasItau.idFinancItauEntrada}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${entradasItau.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataOutrasdespesas${entradasItau.idFinancItauEntrada}');"
                onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','dataOutrasdespesas${entradasItau.idFinancItauEntrada}',${entradasItau.idFinancItauEntrada},'data','outrasdespesas');"
                />
          </td>

          <td class="tiraPaddingData"  <c:if test = "${entradasItau.valor < 0}">style='color:red'</c:if> >
          		<input id="valorOutrasdespesas${entradasItau.idFinancItauEntrada}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${entradasItau.valor}" pattern="#,##0.00"/>"
             	onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','valorOutrasdespesas${entradasItau.idFinancItauEntrada}',${entradasItau.idFinancItauEntrada},'valor','outrasdespesas');"
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
