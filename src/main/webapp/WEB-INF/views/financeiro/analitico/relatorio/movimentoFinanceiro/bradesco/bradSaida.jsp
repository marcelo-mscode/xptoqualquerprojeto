<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="corMarronEstranha"><b>BRADESCO MOVIMENTOS DE CAIXA - SAIDAS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataSaidasBradesco" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descSaidasBradesco" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="valorSaidasBradesco" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataSaidasBradesco','descSaidasBradesco','valorSaidasBradesco','salvaNovaSaida',${idAnalitico},'bradescoSaida','3');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalsaidasBradesco" value="0.00" />
    <c:forEach items="${saidasBradesco}" var="saidasBradesco">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataSaidasBradesco${saidasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${saidasBradesco.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataSaidasBradesco${saidasBradesco.idMovBancos}');"
                onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','dataSaidasBradesco${saidasBradesco.idMovBancos}',${saidasBradesco.idMovBancos},'data','bradescoSaida','3');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaosaidasBradesco${saidasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${saidasBradesco.descricao}"
                onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','descricaosaidasBradesco${saidasBradesco.idMovBancos}',${saidasBradesco.idMovBancos},'descricao','bradescoSaida','3');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${saidasBradesco.valor < 0}">style='color:red'</c:if> >
             <input id="valorsaidasBradesco${saidasBradesco.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${saidasBradesco.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','valorsaidasBradesco${saidasBradesco.idMovBancos}',${saidasBradesco.idMovBancos},'valor','bradescoSaida','3');"
             /> 
          </td>
           <td style="padding: 20px !important;">
		  	<a href="excluiItemAnalitico?idAnalitico=${InfoAnalitico.idAnalitico}&idTabela=${saidasBradesco.idMovBancos}&tabela=MovimentacaoBancosSaidas "><i class="glyphicon glyphicon-trash"></i></a>
		  </td>
       </tr>
       <c:set var="totalsaidasBradesco" value="${totalsaidasBradesco+saidasBradesco.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalsaidasBradesco}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
