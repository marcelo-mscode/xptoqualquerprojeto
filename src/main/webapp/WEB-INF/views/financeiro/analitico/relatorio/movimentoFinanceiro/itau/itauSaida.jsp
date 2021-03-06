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
       <td class="tiraPaddingData"><input id="dataMovSaidaSantander" type="date"  class="ajusteInput2 tiraPaddingData input-140px"/></td>		
       <td class="tiraPaddingData" colspan="3"><input id="descMovSaidaSantander" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
       <td class="tiraPaddingData"><input id="valorMovSaidaSantander" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
       <td><button onclick="insereDadosMovimentacaoSaidas('dataMovSaidaSantander','descMovSaidaSantander','valorMovSaidaSantander','salvaNovaSaida',${idAnalitico},'saidasItau','1');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
       <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>	
    </tr>
    <tr>
       <td>DATA</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
       <td>Fixo</td>
	   <td>Excluir</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalsaidasItau" value="0.00" />
    <c:forEach items="${saidasItau}" var="saidasItau">
       <tr>
       	  <td class="tiraPaddingData"><input id="dataMovSaidaItau${saidasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${saidasItau.data}" pattern="dd/MM"/>" type="text"
                onclick="mudaCampoData('dataMovSaidaItau${saidasItau.idMovBancos}');"
                onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','dataMovSaidaItau${saidasItau.idMovBancos}',${saidasItau.idMovBancos},'data','saidasItau','1');"
                />
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoSaidaItau${saidasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData input-140px" value="${saidasItau.descricao}"
                onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','descricaoSaidaItau${saidasItau.idMovBancos}',${saidasItau.idMovBancos},'descricao','saidasItau','1');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${saidasItau.valor < 0}">style='color:red'</c:if> >
             <input id="valorSaidaItau${saidasItau.idMovBancos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${saidasItau.valor}" pattern="#,##0.00"/>"
             onblur="editaValoresSaidas('editaMovimentacaoFinanceiraSaidas','valorSaidaItau${saidasItau.idMovBancos}',${saidasItau.idMovBancos},'valor','saidasItau','1');"
             /> 
          </td>
                    
          <td style="padding: 15px !important;">
          	<input type="checkbox" <c:if test="${saidasItau.fixo == true }" >checked="checked"</c:if>
          	id="chkEditasaidasItau${saidasItau.idMovBancos}"
			onclick="editaCheckedFixo('editaFixo',${InfoAnalitico.idAnalitico},${saidasItau.idMovBancos},'saidasItau',66778);"
          	
          	/>
          </td>	
          
          
		  <td style="padding: 20px !important;">
		  	<a href="excluiItemAnalitico?idAnalitico=${InfoAnalitico.idAnalitico}&idTabela=${saidasItau.idMovBancos}&tabela=MovimentacaoBancosSaidas "><i class="glyphicon glyphicon-trash"></i></a>
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
