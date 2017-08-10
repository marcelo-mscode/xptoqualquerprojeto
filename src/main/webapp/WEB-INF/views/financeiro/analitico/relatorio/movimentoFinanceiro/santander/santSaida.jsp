<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="corRosaEstranha"><b>SANTANDER MOVIMENTOS DE CAIXA - SAIDAS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataSaidasSantander" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descSaidasSantander" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="valorSaidasSantander" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataSaidasSantander','descSaidasSantander','valorSaidasSantander','salvaNovaSaida',${idAnalitico},'santanderSaida','4');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalsaidasSantander" value="0.00" />
    <c:forEach items="${saidasSantander}" var="saidasSantander">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataSaidasSantander${saidasSantander.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${saidasSantander.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataSaidasSantander${saidasSantander.idMovBancos}');"
                onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','dataSaidasSantander${saidasSantander.idMovBancos}',${saidasSantander.idMovBancos},'data','santanderSaida','4');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoSaidasSantander${saidasSantander.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${saidasSantander.descricao}"
                onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','descricaoSaidasSantander${saidasSantander.idMovBancos}',${saidasSantander.idMovBancos},'descricao','santanderSaida','4');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${saidasSantander.valor < 0}">style='color:red'</c:if> >
             <input id="valorSaidasSantander${saidasSantander.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${saidasSantander.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','valorSaidasSantander${saidasSantander.idMovBancos}',${saidasSantander.idMovBancos},'valor','santanderSaida','4');"
             /> 
          </td>
       </tr>
       <c:set var="totalsaidasSantander" value="${totalsaidasSantander+saidasSantander.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalsaidasSantander}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
