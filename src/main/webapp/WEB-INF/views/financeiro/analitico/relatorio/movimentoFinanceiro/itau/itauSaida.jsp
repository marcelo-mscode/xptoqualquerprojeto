<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="amareloFlat"><b>MOVIMENTOS DE CAIXA - SAIDA</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataMovSaidaItau" type="date"  class="ajusteInput2 tiraPaddingData input-140px" value="2017-07-01"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descMovSaidaItau" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição" value="Teste teste" /></td>
       <td class="tiraPaddingData"><input id="valorMovSaidaItau" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"  value="120.58"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataMovSaidaItau','descMovSaidaItau','valorMovSaidaItau','salvaNovaSaida',${InfoAnalitico.idAnalitico},'saidasItau','1');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalsaidasItau" value="0.00" />
    <c:forEach items="${saidasItau}" var="saidasItau">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataOutrasdespesas${saidasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${saidasItau.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataOutrasdespesas${saidasItau.idMovBancos}');"
                onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','dataOutrasdespesas${saidasItau.idMovBancos}',${saidasItau.idMovBancos},'data','outrasdespesas');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoOutrasdespesas${saidasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${saidasItau.descricao}"
                onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','descricaoOutrasdespesas${saidasItau.idMovBancos}',${saidasItau.idMovBancos},'descricao','outrasdespesas');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${saidasItau.valor < 0}">style='color:red'</c:if> >
             <input id="valorOutrasdespesas${saidasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${saidasItau.valor}" pattern="#,##0.00"/>"
             onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','valorOutrasdespesas${saidasItau.idMovBancos}',${saidasItau.idMovBancos},'valor','outrasdespesas');"
             /> 
          </td>
       </tr>
       <c:set var="totalsaidasItau" value="${totalsaidasItau+saidasItau.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalsaidasItau}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
