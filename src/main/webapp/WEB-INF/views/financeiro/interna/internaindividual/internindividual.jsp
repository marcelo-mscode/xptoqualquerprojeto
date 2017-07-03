<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../../../_comum/header.jsp" />
<style type="text/css">
   .financeiro{background: #f1f1f1;}
   .servicos{background-color: #ffff99 !important;text-align: center}
   .internaLocco{background: #ecffb3!important;text-align: center}
   .infFornecedores{background: #cceeff !important;text-align: center}
   .colorRed{color: red;text-align: right}
   .colorBlue{color: blue;text-align: right;}
   .fundoVerde{background-color: #ccffcc !important;text-align: right}
   .fundoVerdeEscuro{background-color: #98e698 !important;}
   .fundoCinza{background-color: #ccc !important;text-align: center}
   .ladoDireito{text-align: right;}
   .textRight{text-align: right;}
   .table-bordered > thead > tr > th,
   .table-bordered > tbody > tr > th,
   .table-bordered > tfoot > tr > th,
   .table-bordered > thead > tr > td,
   .table-bordered > tbody > tr > td,
   .table-bordered > tfoot > tr > td
   {  border: 1px solid #999!important;}
   .ajusteInput{border: none;height: 20px;padding: 25px;width: 105px;text-align: right;padding-right:8px}
   .textareaXY{width: 100%;height: 100%;}
   .ajusteMenuFinanceiro, .bodyXY{width: 150% !important; }
   .ajusteFinanceiro{float: none !important;}
   .ajusteFinanceiroBar{position: fixed;width:150%;}
   .alinhamentoVertical > td {vertical-align: middle !important;}
   
   
   
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;margin-top: 49px;position: fixed;z-index: 9999;">
   <ol class="breadcrumb">
      <li><a href="index.html">Menu</a></li>
      <li><a href="listasInternas">Planilhas Internas</a></li>
      <li class="active">Planilha Interna ${infoLista.lista}</li>
   </ol>
</div>

<div class="col-md-12" style="margin-top: 11px;text-align: center;">
   <table class="table table-bordered table-hover table-condensed" style="width:98%;position: fixed;margin-top: 72px;background-color: #fff;z-index: 9999;">
      <tr class="alinhamentoVertical">
         <td colspan="4">Evento: ${infoLista.lista}</td>
         <td colspan="2">${infoLista.idJob.empresa.empresa}</td>
         <td colspan="3">Requisitante: ${infoLista.idJob.contatos.contato}</td>
       
         <td colspan="3" class="form-inline">Data Pagto:
         	 <input id="dataPag${infoLista.idLista}" class="form-control input-160px"
         	 onblur="editaCamposProducaoP('dataPag${infoLista.idLista}','infoInternaData',${infoLista.idLista});" onclick="mudaCampoData('dataPag${infoLista.idLista}');" type="text"
			value="<fmt:formatDate value="${infoInterna.dataPagamento}" pattern="dd/MM/yyyy"/>"  >
         </td>
         <td colspan="3" class="form-inline">NF &nbsp&nbsp
         	<input id="nfInterna${infoLista.idLista}" class="form-control input-80px"
         	onblur="editaCamposProducaoP('nfInterna${infoLista.idLista}','infoInternaNf',${infoLista.idLista});" type="text" value="${infoInterna.nfInterna}">
         </td>
         
         <td colspan="3" class="form-inline">Imposto % &nbsp&nbsp
         	<input id="impostoInterna${infoLista.idLista}" class="form-control input-80px"
         	onblur="editaCamposProducaoP('impostoInterna${infoLista.idLista}','impostoInterna',${infoLista.idLista});" type="text" value="${infoInterna.impostoInterna}">
         </td>
         
         
         
      </tr>
   </table>
</div>   
   
   
<div id="criacaoListas" class="efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:10px 0px 70px 10px;">
   <a href="relatorioEventoIndividual?idLista=${infoLista.idLista}" style="float: right;margin-right: 15px;">Relatório Evento: ${infoLista.lista}</a>
   <!-- <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;"> -->
   <ul class="nav nav-tabs" role="tablist" style="margin-top: 150px;">
      <li role="presentation" class="active" style="">
         <a href="#home" aria-controls="home" role="tab" data-toggle="tab" aria-expanded="false" style="background-color: #ffff99;border: 1px solid #999;">
            <h5>Planilha Interna</h5>
         </a>
      </li>
      <li role="presentation" class="" style="z-index: 9;border-bottom: 1px solid #ccc;">
         <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" aria-expanded="true">
            <h5>Despesas</h5>
         </a>
      </li>
   </ul>
   
   
   <div class="tab-content" style="width: 100%;">
      <div role="tabpanel" class="tab-pane active" id="home">
         <table class="table table-bordered table-condensed" style="box-shadow: 0px 1px 14px 3px #ccc;margin-top: -1px;">
            <tbody id="prospeccaoFiltro">
               <tr style="text-align: center">
                  <th class="servicos">Serviços</th>
                  <th class="servicos" colspan="3">Custos</th>
                  <th class="internaLocco" colspan="3">Interna Locco</th>
                  <th class="infFornecedores" colspan="6">Informações Fornecedores</th>
               </tr>
               <tr style="background: #f1f1f1 !important;font-size: 12px">
                  <th class="servicos"></th>
                  <th class="servicos">FatLocco</th>
                  <th class="servicos">SubContratados</th>
                  <th class="servicos">FatDireto</th>
                  <th class="internaLocco colorRed" style="text-align: center;width: 100px;" >Valor Fornecedor</th>
                  <th class="internaLocco" >Valor NF</th>
                  <th class="internaLocco colorBlue" >Diferença</th>
                  <th class="infFornecedores" >Fornecedor</th>
                  <th class="infFornecedores" >Obs:</th>
                  <th class="infFornecedores" >Dif. Imposto</th>
                  <th class="infFornecedores" >Vencimento</th>
                  <th class="infFornecedores" style="width: 70px;" >NF/Recibo</th>
                  <th class="infFornecedores" >Dados</th>
               </tr>
 
               <c:forEach items="${itensInterna}" var ="itensInterna" varStatus="status">

                  <c:if test="${itensInterna.statusProducao != 'ITEMFECHADO'}">
                     <tr style="opacity: 0.5;background-color: #CDE;">
                  </c:if>

                  <c:if test="${itensInterna.statusProducao == 'ITEMFECHADO'}">			
           	       <tr>
                  </c:if>
                  
                  <td>${itensInterna.produtoGrupo.idGrupo.grupo}<%--  - ${itensInterna.produtoGrupo.produto} --%></td>
                 
                <!-- Fat Locco -->  
                  <td class="textRight">
	                  <c:if test="${itensInterna.produtoGrupo.imposto == true && itensInterna.produtoGrupo.idGrupo.incideAdministracao == false}">
	              	    <fmt:formatNumber value="${itensInterna.valorItem}" pattern="#,##0.00"/> 
	                  </c:if>
                  </td>
				
                <!-- SubContratados -->  
                  <td class="textRight">
 	            	 <c:if test="${itensInterna.produtoGrupo.imposto == true && itensInterna.produtoGrupo.idGrupo.incideAdministracao == true}">
	              	  	<fmt:formatNumber value="${itensInterna.valorItem}" pattern="#,##0.00"/> 
	                 </c:if>
                 </td>
				               
               <!-- Fat Direto -->
                  <td class="textRight">
	                  <c:if test="${itensInterna.produtoGrupo.imposto == false}">
	              	    <fmt:formatNumber value="${itensInterna.valorItem}" pattern="#,##0.00"/>
	                  </c:if>
                  </td>
                  
                  <!-- Valor fornecedor -->	
                  <td class="colorRed" style="padding: 0px">
	                  <c:if test="${itensInterna.valorContratacao == '0.00' && itensInterna.idEmpFornecedor.idEmpresa != 6961}">
	                	  <input name="" id="valorF${itensInterna.idProducao}" class="ajusteInput" type="text" value="<fmt:formatNumber value="${itensInterna.valorItem}" pattern="#,##0.00"/>"
	                  onblur="valorF('valorF',${itensInterna.idProducao});" />
	                  </c:if>
	                  <c:if test="${itensInterna.valorContratacao != '0.00'}">
		                  <input name="" id="valorF${itensInterna.idProducao}"  class="ajusteInput" type="text" value="<fmt:formatNumber value="${itensInterna.valorContratacao}" pattern="#,##0.00"/>"
		                  onblur="valorF('valorF',${itensInterna.idProducao});" />
	                  </c:if>
                  </td>
                  <!-- Valor NF -->	
                  <td class="textRight"><fmt:formatNumber value="${itensInterna.valorItem}" pattern="#,##0.00"/></td>
                  <!-- Diferenca -->
                  <c:if test="${itensInterna.diferenca < 0}">
             	     <td class="colorRed">
                  </c:if>				
                  <c:if test="${itensInterna.diferenca >= 0}">
               	   <td class="colorBlue">
                  </c:if>				
                  <c:if test="${itensInterna.valorContratacao == '0.00'}"></c:if>
                  <c:if test="${itensInterna.idEmpFornecedor.idEmpresa == 6961}">
                	  <fmt:formatNumber value="${itensInterna.valorItem}" pattern="#,##0.00"/>
                  </c:if>
                  <c:if test="${itensInterna.valorContratacao != '0.00'}">
                	  <fmt:formatNumber value="${itensInterna.diferenca}" pattern="#,##0.00"/>
                  </c:if>
                  </td>
                  <!-- Focar os esforços aqui !!!!!!!!!!!!!!!!!!!!  -->
             
             
 <%--             <c:if test="${itensInterna.rowSpan != null}">
	              <td rowspan="${itensInterna.rowSpan}" style="vertical-align: middle;font-size: 12px">${itensInterna.idEmpFornecedor.empresa}</td>
	                  <td rowspan="${itensInterna.rowSpan}" style="text-align: center;vertical-align: middle;padding: 0">--%>
	                  <td rowspan="" style="vertical-align: middle;font-size: 12px">${itensInterna.idEmpFornecedor.empresa}</td>
	                  <td rowspan="" style="text-align: center;vertical-align: middle;padding: 0">
	                  
	                  
	                  <textarea id="obs${itensInterna.idProducao}" class="textareaXY"
	                  onblur="editaCamposProducaoP('obs${itensInterna.idProducao}','observacao',${itensInterna.idProducao});"
	                  rows="2" cols="10" style="border: none;overflow: hidden;
	                  text-align: center;">${fn:trim(itensInterna.obsProducao.obs)}</textarea>
	                  
                	  </td>
	               
<%--                  <td rowspan="${itensInterna.rowSpan}" style="vertical-align: middle;padding: 0"> --%>
	                  <td rowspan="" style="vertical-align: middle;padding: 0">
		                  <textarea id="dif${itensInterna.idProducao}" class="textareaXY"
		                  onblur="editaCamposProducaoP('dif${itensInterna.idProducao}','difImpostoProducaoP',${itensInterna.idProducao});"
		                  rows="2" cols="10" style="border: none;overflow: hidden;
		                  text-align: center;">${fn:trim(itensInterna.difImpostoProducaoP.obs)}</textarea>
	                  </td>
	                  
	                  
	                  <%-- <td rowspan="${itensInterna.rowSpan}" style="vertical-align: middle;font-size: 12px"> --%>
	                  <td rowspan="" style="vertical-align: middle;font-size: 12px">
	                  <c:forEach items="${itensInterna.fornecedorFinanceiro.idValorPgtoFornecedor}" var="fornecedorFinanceiro">
	                 	 <fmt:formatDate value="${fornecedorFinanceiro.dtPgotFornecedor.dataPagar}" pattern="dd/MM/yyyy" /><br>
	                  </c:forEach>
	                  </td>
	                  
	                  <%-- <td rowspan="${itensInterna.rowSpan}" style="vertical-align: middle;font-size: 12px;padding: 0"> --%>
	                  <td rowspan="" style="vertical-align: middle;font-size: 12px;padding: 0">
	                   
	                  	<input id="nfRec${itensInterna.idProducao}" class="ajusteInput" type="text" value="${itensInterna.nfRecibo}"
	                  	onblur="editaCamposProducaoP('nfRec${itensInterna.idProducao}','nfRecibo',${itensInterna.idProducao});"
	                  	style="width: 100%;height: 100%;text-align: center">
	                  	
	                  </td>
	                  
	                  <%-- <td rowspan="${itensInterna.rowSpan}" style="vertical-align: middle;"> --%>
	                  <td rowspan="" style="vertical-align: middle;">
	                  
	                  <textarea wrap="off" id="dadosBanc${itensInterna.idProducao}" class="textareaXY"
		                  onblur="editaCamposProducaoP('dadosBanc${itensInterna.idProducao}','dadosBancarios',${itensInterna.idProducao});"
		                  rows="2" cols="10" style="border: none;overflow: hidden;
		                  text-align: center;"><c:if test="${itensInterna.dadosBancarios == null}"><c:forEach items="${itensInterna.idEmpFornecedor.pagamento}" var="pagamento">${pagamento.banco}&#10; Ag:${pagamento.agencia}&#10;c/c:${pagamento.conta}</c:forEach></c:if><c:if test="${itensInterna.dadosBancarios != ''}">${itensInterna.dadosBancarios}</c:if></textarea>
		                  
	                  </td>
 <%--                  </c:if> --%>
                  
                  
                  </tr>		
               </c:forEach>
               
              <c:if test="${totalDepesas != null }">
	               <tr>
		               	<td colspan="1">Despesas Evento</td>
		               	<td colspan="1"></td>
		               	<td colspan="1"></td>
		               	<td colspan="1"></td>
						<td class="colorRed"><fmt:formatNumber value="${totalDepesas}" pattern="#,##0.00"/></td>
		               	<td colspan="1"></td>
		               	<td class="colorRed">(<fmt:formatNumber value="${totalDepesas}" pattern="#,##0.00"/>)</td>
		               	<td colspan="6"></td>
	               </tr>
              </c:if>
               
               <c:forEach items="${despesas}" var="despesas">
                  <tr>
                     <td>${despesas.servicos}</td>
                     <td>
                        <fmt:formatNumber value="${despesas.fatLocco}" pattern="#,##0.00"/>
                     </td>
                     <td>
                        <fmt:formatNumber value="${despesas.fatDireto}" pattern="#,##0.00"/>
                     </td>
                     <td>
                        <fmt:formatNumber value="${despesas.valorFornecedor}" pattern="#,##0.00"/>
                     </td>
                     <td>
                        <fmt:formatNumber value="${despesas.valorNF}" pattern="#,##0.00"/>
                     </td>
                     <td>
                        <fmt:formatNumber value="${despesas.diferenca}" pattern="#,##0.00"/>
                     </td>
                     <td>Fornecedor</td>
                     <td>Obs.:</td>
                     <td>${despesas.difimposto}</td>
                     <td>
                        <fmt:formatDate value="${despesas.dataVencimento}" pattern="dd/MM/yyyy" />
                     </td>
                     <td>${despesas.nfRecibo}</td>
                     <td>${despesas.dadosBancarios}</td>
                  </tr>
               </c:forEach>
               <!-- <tr>
                  <td colspan="12" style="height: 20px;">
                     <a data-toggle="modal" data-target=".bs-example-modal-lg" style="float: right;cursor: pointer;cursor: pointer;padding: 5px;margin-right: 10px;">
                     <i class="glyphicon glyphicon-pencil" style="top: 2px;margin-right: 5px;"></i>
                     Incluir linha
                     </a>
                  </td>
               </tr> -->
               <tr>
                  <td colspan="13" style="height: 20px;"></td>
               </tr>
               <tr>
                  <th class="servicos" style="text-align: left;">SubTotais</th>

                  <th class="servicos">
                     <fmt:formatNumber value="${calculadora.subLoCCo}" pattern="#,##0.00"/>
                  </th>

                  <th class="servicos">
						<fmt:formatNumber value="${calculadora.subContratados}" pattern="#,##0.00"/>
                  </th>

                  <th class="servicos">
                     <fmt:formatNumber value="${calculadora.subDireto}" pattern="#,##0.00"/>
                  </th>

                  <td class="colorRed">
	                  	<c:if test="${totalDepesas != null }">
	                     <fmt:formatNumber value="${calculadora.subValorFornecedor+totalDepesas}" pattern="#,##0.00"/>
	                  	</c:if>
	                  	<c:if test="${totalDepesas == null }">
	                     <fmt:formatNumber value="${calculadora.subValorFornecedor}" pattern="#,##0.00"/>
	                  	</c:if>
                  </td>
                  <td class="textRight">
                     <fmt:formatNumber value="${calculadora.subValorNf}" pattern="#,##0.00"/>
                  </td>
                  
                  <td class="textRight">
                  <c:if test="${totalDepesas == null }">
                     <fmt:formatNumber value="${calculadora.subDiferenca}" pattern="#,##0.00"/>
                  </c:if>
                  
                  <c:if test="${totalDepesas != null }">
                     <fmt:formatNumber value="${calculadora.subDiferenca-totalDepesas}" pattern="#,##0.00"/>
                  </c:if>
                  
                  </td>
                  
                  <td colspan="6"></td>
               </tr>
               
<!-- FEE  -->               
               <tr>
                  <th>Fee ${infoLista.administracao}%</th>
                  <th class="servicos">
                     <fmt:formatNumber value="${calculadora.feeGeral}" pattern="#,##0.00"/>
                  </th>
                  <th></th>
                  <td class="colorRed"></td>
                  <td class="colorRed"></td>
                  <td class="textRight">
                     <fmt:formatNumber value="${calculadora.feeGeral}" pattern="#,##0.00"/>
                  </td>
                  <td class="textRight">
                     <fmt:formatNumber value="${calculadora.feeGeral}" pattern="#,##0.00"/>
                  </td>
                  <td colspan="6"></td>
               </tr>

<!-- FEE REDUZIDO -->               
               <tr>
                  <th>Fee REDUZIDO ${infoLista.feeReduzido}%</th>
                  <th class="servicos">
                     <fmt:formatNumber value="${calculadora.feeGeral}" pattern="#,##0.00"/>
                  </th>
                  <th></th>
                  <td class="colorRed"></td>
                  <td class="colorRed"></td>
                  <td class="textRight">
                     <fmt:formatNumber value="${calculadora.feeGeral}" pattern="#,##0.00"/>
                  </td>
                  <td class="textRight">
                     <fmt:formatNumber value="${calculadora.feeGeral}" pattern="#,##0.00"/>
                  </td>
                  <td colspan="6"></td>
               </tr>
<!--  -->               
               
               
               <tr>
                  <th>Despesas Evento</th>
                  <th></th>
                  <th></th>
                  <th></th>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td colspan="6"></td>
               </tr>
               <tr>
                  <th>SubTotal Geral</th>
                  <th colspan="2" class="textRight">
                     <fmt:formatNumber value="${calculadora.subTotalGeral}" pattern="#,##0.00"/>
                  </th>
                  <th></th>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td colspan="6"></td>
               </tr>
               <tr>
                  <th class="servicos" style="text-align: left;">Impostos 22,9%</th>
                  <th class="servicos textRight">
                     <fmt:formatNumber value="${impostoLista}" pattern="#,##0.00"/>
                  </th>
                  <th class="servicos"></th>
                  <th class="servicos"></th>
                  <th class="servicos colorRed">
                     <fmt:formatNumber value="${calculadora.impostoValorFornecedor}" pattern="#,##0.00"/>
                  </th>
                  <td class="textRight">
                     <fmt:formatNumber value="${impostoLista}" pattern="#,##0.00"/>
                  </td>
                  <td class="colorRed">
                     <fmt:formatNumber value="${calculadora.impostoDiferenca}" pattern="#,##0.00"/>
                  </td>
                  <td colspan="6"></td>
               </tr>
               <tr>
                  <th class="servicos" style="text-align: left;">Total 1</th>
                  <th class="servicos textRight">
                     <fmt:formatNumber value="${calculadora.total1LoCCO}" pattern="#,##0.00"/>
                  </th>
                  <th class="servicos textRight"></th>
                  <th class="servicos textRight"></th>
                  <td></td>
                  <td></td>
                  <th class="servicos">
                  	
                  	<c:if test="${totalDepesas == null }">
                  		<fmt:formatNumber value="${calculadora.total1Diferenca}" pattern="#,##0.00"/>
                  	</c:if>	
                  	<c:if test="${totalDepesas != null }">
                  		<fmt:formatNumber value="${calculadora.total1Diferenca-totalDepesas}" pattern="#,##0.00"/>
                  	</c:if>	
                  
                  
                  </th>
                  <td colspan="6"></td>
               </tr>
               <tr>
                  <th>Total 2</th>
                  <th class="fundoVerde textRight" colspan="2">
                     <fmt:formatNumber value="${calculadora.total2}" pattern="#,##0.00"/>
                  </th>
                  <td></td>
                  <td></td>
                  <td></td>
                  <th class="fundoVerde"></th>
                  <td colspan="6"></td>
               </tr>
            </tbody>
         </table>
         <div class="col-md-11"></div>
         <div class="col-md-1"><a href="atualizaInterna?idLista=${infoLista.idLista}" class="btn btn-danger">Atualizar</a></div>
         <div class="row">
            <div class="col-md-7" style="margin-bottom: 17px;margin-left: -10px;">Última Atualização em: 12/05/2015 9:35:12 - por: Erika Spainer</div>
         </div>
         <div class="col-md-8" style="padding: 0;">
            <table class="table table-bordered table-hover table-condensed" style="box-shadow: 0px 1px 14px 3px #ccc;">
               <tbody id="prospeccaoFiltro">
                  <tr style="text-align: center">
                     <th class="fundoCinza">Pedido Compra</th>
                     <th class="fundoCinza">Faturamento</th>
                     <th colspan="3"></th>
                  </tr>
                  <tr>
                     <th rowspan="5"></th>
                     <th rowspan="5"></th>
                     <th style="text-align: right;">Total Serviços Subcontratados</th>
                     <th style="width: 80px;"></th>
                     <th class="fundoVerdeEscuro ladoDireito" style="width: 180px;">
                        <fmt:formatNumber value="${calculadora.subDireto}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">Fee (${infoLista.administracao}%)</th>
                     <th class="servicos" align="center">${infoLista.administracao}%</th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.feeGeral}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">Custos Internos da Agência</th>
                     <th></th>
                     <th class="fundoVerdeEscuro ladoDireito" >
                        <fmt:formatNumber value="${calculadora.subLoCCo}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">SubTotal</th>
                     <th></th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.subTotalGeral}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">Impostos</th>
                     <th class="fundoVerdeEscuro" style="text-align: center;"> 22,90%</th>
                     <th class="servicos ladoDireito" >
                        <fmt:formatNumber value="${impostoLista}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr class="fundoVerde">
                     <th>linha 10</th>
                     <th>NF1</th>
                     <th class="ladoDireito" >TOTAL NF1</th>
                     <th></th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.total1LoCCO}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th rowspan="5"></th>
                     <th rowspan="5"></th>
                     <th style="text-align: right;">Previsão de Extras sobre Subcontratados (10%)</th>
                     <th class="fundoVerdeEscuro" style="text-align: center;">10%</th>
                     <th class="ladoDireito">
                        <fmt:formatNumber value="${calculadora.prevExtraSubContrat}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">Fee (${infoLista.administracao}%)</th>
                     <th class="servicos" align="center">${infoLista.administracao}%</th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.feeFatNF2}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">Previsão de Extras sobre Custos Internos (10%)</th>
                     <th class="fundoVerdeEscuro" style="text-align: center">10%</th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.prevExtraCustosInternos}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">SubTotal</th>
                     <th></th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.subTotalFatNF2}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr>
                     <th class="ladoDireito">Impostos</th>
                     <th class="fundoVerdeEscuro" style="text-align: center;"> 22,90%</th>
                     <th class="servicos ladoDireito" >
                        <fmt:formatNumber value="${calculadora.impostoFatNF2}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr class="fundoVerde">
                     <th>linha 20</th>
                     <th>NF2</th>
                     <th class="ladoDireito" >TOTAL NF2</th>
                     <th></th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.totalFatNF2}" pattern="#,##0.00"/>
                     </th>
                  </tr>
                  <tr class="fundoCinza">
                     <th colspan="3" style="text-align: center;">TOTAL EVENTO</th>
                     <th></th>
                     <th class="ladoDireito" >
                        <fmt:formatNumber value="${calculadora.totalEvento}" pattern="#,##0.00"/>
                     </th>
                  </tr>
               </tbody>
            </table>
         </div>
      </div>
      <div role="tabpanel" class="tab-pane" id="profile">
        
        <c:import url="despesas.jsp" >
        	<c:param name="idlista" value="${infoLista.idLista}" />
        </c:import>
        
      </div>
   </div>
  	
  	
 <!--  <div class="col-md-12" style="position: fixed;bottom: -10px;background-color: #fff;height: 100px;display: none;box-shadow: 0px -5px 13px #737373;" id="wellAlerta">
   <div class="alert alert-success" role="alert" style="position: fixed;bottom: 40px;width: 30%;margin-left: 2px;box-shadow: 5px 6px 15px #737373;border: 1px solid;">
      <strong id="msgSucesso"></strong>
   </div>
  </div>	
  
   <div class="alert alert-danger alert-dismissible" role="alert" style="position: fixed;top: 150px;right:40px ;width: 50%;margin-left: -35px;display: none" id="wellErro">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      <strong>Ops!</strong> Um erro ocorreu, verifique os valores inseridos e tente novamente.
   </div>
   -->
   <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
      <div class="modal-dialog modal-lg" role="document">
         <div class="modal-content" style="padding: 0 25px">
            <h3></h3>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
            <span aria-hidden="true" style="font-size: 50px; font-family: 'OpenSansLight'; font-weight: normal;" onclick="location.reload();">×</span>
            </button>
            <h3 class="modal-title fontLight" id="myModalLabel">INCLUIR DESPESAS DE PROJETO</h3>
            <form action="despesas" method="post">
               <table class="table">
                  <tr>
                     <td colspan="11">Serviços<br>
                        <input name="servicos" type="text" class="form-control input-540px" />
                        <input type="hidden" class="form-control" name="idLista" value="${infoLista.idLista}"/>
                     </td>
                  </tr>
                  <tr>
                     <!-- <td>Valor<input name="valor" type="text" class="form-control" /></td> -->
                     <td colspan="1" style="width: 120px;">Valor<input name="valorTransiente" type="text" class="form-control input-120px" /></td>
                     <!-- 					<td colspan="1" style="width: 120px;">FatLocco<input name="fatLoccoTransiente" type="text" class="form-control input-120px" /></td>
                        <td colspan="1" style="width: 120px;"> FatDireto<input name="fatDiretoTransiente" type="text" class="form-control input-120px" /></td>
                        -->					
                     <td colspan="1" style="width: 120px;">
                        <table>
                           <tr>
                              <td>Fee<br>
                                 <input name="fee" type="checkbox" class="form-control" style="width: 20px;margin-top: 0;margin-right: 30px;" />
                              </td>
                              <td colspan="1" style="width:65px;">
                                 Imposto<br>
                                 <input name="imposto" type="checkbox" class="form-control" style="width: 20px;margin-top: 0;margin-left: 10px;" />
                              </td>
                              <td colspan="1" style="width: 120px;">
                                 Custo para Locco<br>
                                 <input name="custoLocco" type="checkbox" class="form-control" style="width: 20px;margin-top: 0;" />
                              </td>
                           </tr>
                        </table>
                     </td>
                     <td colspan="7"></td>
                  </tr>
                  <tr>
                     <td>Valor Fornecedor<br><input name="valorFornecedorTransiente" type="text" class="form-control input-120px" /></td>
                     <td>Valor NF<br><input name="valorNFTransiente" type="text" class="form-control" style="width: 230px;" /></td>
                     <td>Diferença<br><input name="diferencaTransiente" type="text" class="form-control input-160px" /></td>
                  </tr>
                  <tr>
                     <td>Fornecedor<br><input type="text" class="form-control" /></td>
                     <td>Dif imposto<br><input name="Difimposto" type="text" class="form-control" /></td>
                     <td>Vencimento<br><input name=dataVencimentoTransiente type="date" class="form-control input-160px"/></td>
                  </tr>
                  <tr>
                     <td>NF/Recibo<br><input name="nfRecibo" type="text" class="form-control" /></td>
                     <td>Dados<br><input name="dadosBancarios" type="text" class="form-control" /></td>
                  </tr>
                  <tr>
                     <td colspan="11" style="height: 20px;">
                        <button type="submit" class="btn btn-danger">Salvar</button>
                        <a class="btn btn-default" onclick="location.reload();">Cancelar</a>
                     </td>
                  </tr>
               </table>
            </form>
         </div>
      </div>
   </div>
</div>
<c:import url="../../../_comum/footer.jsp" />
<script>
$(document).ready(function(){
	$('#formDespesas').validate({
		
		//Campos com o name preenchido
		rules: { 
			dataTransiente: { required: true},
			descricao2: { required: true}
		},
		
		//Mensagens que aparecerao abaixo dos names
		messages: {
			dataTransiente: { required: 'Data necessária'},
			descricao2: { required: 'Preencha o campo de Descrição'}
		},
		submitHandler: function( form ){
			var dados = $( form ).serialize();

//envio do formulario por ajax

			$.ajax({
				type: "POST",
				url: "salvaDespesas",
				data: dados,
				success: function( data )
				{   
						location.reload();
				}
			});

			return false;
		}
	});
});

</script>
