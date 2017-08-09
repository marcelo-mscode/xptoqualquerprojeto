<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="verdeClaroFlat"><b>CEF MOVIMENTOS DE CAIXA - SAIDAS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataSaidasCEF" type="date"  class="ajusteInput2 tiraPaddingData input-140px" value="2017-07-07"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descSaidasCEF" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição" value="teste"/></td>
       <td class="tiraPaddingData"><input id="valorSaidasCEF" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor" value="200.00"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataSaidasCEF','descSaidasCEF','valorSaidasCEF','salvaNovaSaida',${idAnalitico},'cefSaida','2');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalsaidasCEF" value="0.00" />
    <c:forEach items="${saidasCEF}" var="saidasCEF">
       <tr>
       	  <td class="tiraPaddingData"><input id="datasaidasCEF${saidasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${saidasCEF.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('datasaidasCEF${saidasCEF.idMovBancos}');"
                onblur="editaCamposAnaliticoDespesas('editasaidasCEF','datasaidasCEF${saidasCEF.idMovBancos}',${saidasCEF.idMovBancos},'data','saidasCEF');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaosaidasCEF${saidasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${saidasCEF.descricao}"
                onblur="editaCamposAnaliticoDespesas('editasaidasCEF','descricaosaidasCEF${saidasCEF.idMovBancos}',${saidasCEF.idMovBancos},'descricao','saidasCEF');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${saidasCEF.valor < 0}">style='color:red'</c:if> >
             <input id="valorsaidasCEF${saidasCEF.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${saidasCEF.valor}" pattern="#,##0.00"/>"
             onblur="editaCamposAnaliticoDespesas('editasaidasCEF','valorsaidasCEF${saidasCEF.idMovBancos}',${saidasCEF.idMovBancos},'valor','saidasCEF');"
             /> 
          </td>
       </tr>
       <c:set var="totalsaidasCEF" value="${totalsaidasCEF+saidasCEF.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalsaidasCEF}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
