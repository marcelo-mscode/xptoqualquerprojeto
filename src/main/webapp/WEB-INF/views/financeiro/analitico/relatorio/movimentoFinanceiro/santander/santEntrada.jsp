<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="corRosaEstranha"><b>SANTANDER MOVIMENTOS DE CAIXA - ENTRADAS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="ndMovSantander" class="ajusteInput2 tiraPaddingData input-70px" type="text" placeholder="ND/NF"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descEntradasSantander" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="dataEntradasSantander" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
       <td class="tiraPaddingData"><input id="valoreEntradasSantander" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="InsereDadosMovimentacao('ndMovSantander','dataEntradasSantander','descEntradasSantander','valoreEntradasSantander','salvaNovaEntrada',${idAnalitico},'santanderEntrada','4');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
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
    
    <c:set var="totalentradasSantader" value="0.00" />
    <c:forEach items="${entradasSantader}" var="entradasSantader">
       <tr>
		 <td class="tiraPaddingData" colspan="1">
              <input id="ndMovSantander${entradasSantader.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasSantader.ndnf}"
              onblur="editaValoresEntradas('editaMovimentacaoFinanceira','ndMovSantander${entradasSantader.idMovBancos}',${entradasSantader.idMovBancos},'ndnf','santanderEntrada','4');"/>
          </td>
      
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoentradasSantader${entradasSantader.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${entradasSantader.descricao}"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','descricaoentradasSantader${entradasSantader.idMovBancos}',${entradasSantader.idMovBancos},'descricao','santanderEntrada','4');"
                />
          </td>
       	  <td class="tiraPaddingData"><input id="dataentradasSantader${entradasSantader.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${entradasSantader.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataentradasSantader${entradasSantader.idMovBancos}');"
                onblur="editaValoresEntradas('editaMovimentacaoFinanceira','dataentradasSantader${entradasSantader.idMovBancos}',${entradasSantader.idMovBancos},'data','santanderEntrada','4');"
                /></td>
          <td class="tiraPaddingData"  <c:if test = "${entradasSantader.valor < 0}">style='color:red'</c:if> >
             <input id="valorentradasSantader${entradasSantader.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${entradasSantader.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresEntradas('editaMovimentacaoFinanceira','valorentradasSantader${entradasSantader.idMovBancos}',${entradasSantader.idMovBancos},'valor','santanderEntrada','4');"
             /> 
          </td>
           <td style="padding: 20px !important;">
		  	<a href="excluiItemAnalitico?idAnalitico=${InfoAnalitico.idAnalitico}&idTabela=${entradasSantader.idMovBancos}&tabela=MovimentacaoBancos"><i class="glyphicon glyphicon-trash"></i></a>
		  </td>
       </tr>
       <c:set var="totalentradasSantader" value="${totalentradasSantader+entradasSantader.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalentradasSantader}" pattern="#,##0.00"/></b>
       </td>
    </tr>
</table>
