<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../../../_comum/header.jsp" />
<style type="text/css">
   .financeiro{background: #f1f1f1;}
   .cabecalhoLista {text-align: center !important;line-height: 33px !important;}
   .cabecalhoLista > td {line-height: 33px !important;}
   .descricao {text-align: center;}
   .bordaDestaque{border: 2px solid #ccc;}
   .direita{border-right: 2px solid #ccc !important;}
   .esquerda{border-left: 2px solid #ccc  !important;}
   .topo{border-top: 2px solid #ccc  !important;}
   .ajusteValores{padding-left: 25px !important;}
   .ajusteFuncInput{border: none;height: 20px;padding: 18px;}
   .juntaColunas{padding: 0px 0 8px 0 !important;}
   .ajusteMenuFinanceiro, .bodyXY{width: 150% !important; }
   .ajusteFinanceiro{float: none !important;}
   .ajusteFinanceiroBar{position: fixed;width:150%;}
   .ajusteInput2{border: none;height: 35px;width: 90px;padding: 0 0 0 5px;}
   .ajusteInput2td{padding: 0}
   .ajusteTelefone{}
   .botaoMais{width: 70px;font-size: 23px;padding: 0;font-weight: 800;}
   .botaoMaisDespesa{width: 50px;}
   .tiraPaddingData{padding: 0px 0px 0px 5px  !important;height: 50px !important}
  	
	
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;margin-top: 50px">
   <ol class="breadcrumb">
      <li><a href="index.html">Menu</a></li>
      <li><a href="indexAnalitico">Relatorios Analítico</a></li>
      <li class="active">Analítico ${InfoAnalitico.mesA} ${InfoAnalitico.anoA}</li>
   </ol>
</div>

<div id="criacaoListas" class="efeitoDegrade" style="margin-top:34px;font-size: 12px;font-family: 'OpenSansLight';padding:35px 5px 70px 5px;width: 250%">
   <div style="padding: 15px 0 55px 20px;;box-shadow: 0px 0px 30px 5px #ccc;margin-top: 42px;padding-right: 40px;">
      <h2 class="">Analítico ${InfoAnalitico.mesA} ${InfoAnalitico.anoA}</h2>
		
		
	  <c:import url="saldoBancario/saldoBancario.jsp" />
      
      <table class="table table-bordered bordaDestaque" >
         <tbody id="prospeccaoFiltro">
            <tr>	
            	<td colspan="4" align="center" class="corEscritorio" style="border-right: 2px solid #ccc"><b>CUSTO FIXO LOCCO AGENCIA</b></td>
            	<td colspan="2" align="center" class="corEscritorio"><b>CUSTO VARIÁVEL LOCCO AGENCIA</b></td>
            	<td colspan="3" align="center" class="amareloFlat"><b>MOVIMENTAÇÃO ITAU</b></td>
            </tr>
            <tr>
            	<td class="juntaColunas ajusteTelefone" id="impostos">
                  <table class="table table-hover table-bordered">
                     <tr>
                        <td colspan="3" align="center" class="corEscritorio"><b>OUTROS IMPOSTOS</b></td>
                     </tr>
                     <tr>
                        <td class="tiraPaddingData"><input id="descImpostos" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorImpostos" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiro('descImpostos','valorImpostos','salvaNovoImposto',${InfoAnalitico.idAnalitico},'impostos');" class="btn btn-default botaoMais">+</button> </td>
                     </tr>
                     <tr>
                        <td colspan="2">Descrição</td>
                        <td>Valor</td>
                     </tr>
                     <tr>
                        <td colspan="3"></td>
                     </tr>
                     <c:set var="totalImpostos" value="0.00" />
                     <c:forEach items="${impostos}" var="impostos">
                        <tr>
                           <td colspan="2" class="tiraPaddingData">
                              <input id="descricaoImpostos${impostos.idFinancImpostos}" class="ajusteInput2 input-160px tiraPaddingData" value="${impostos.descricao}"
                                 onblur="editaCamposAnalitico('editaFinancImposto','descricaoImpostos${impostos.idFinancImpostos}',${impostos.idFinancImpostos},'descricao','impostos');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${impostos.valor < 0}">style='color:red'</c:if> >
                              <input id="valorImpostos${impostos.idFinancImpostos}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${impostos.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnalitico('editaFinancImposto','valorImpostos${impostos.idFinancImpostos}',${impostos.idFinancImpostos},'valor','impostos');"
                              /> 
                           </td>
                        </tr>
                        <c:set var="totalImpostos" value="${totalImpostos+impostos.valor}" />
                     </c:forEach>
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                           <b>
                              <fmt:formatNumber value="${totalImpostos}" pattern="#,##0.00"/>
                           </b>
                        </td>
                     </tr>
                  </table>
    			</td>
            
               <td class="juntaColunas ajusteTelefone" id="escritorio">
                  <table class="table table-hover table-bordered">
                     <tr>
                        <td colspan="3" align="center" class="corEscritorio"><b>ESCRITÓRIO</b></td>
                     </tr>
                     <tr>
                        <td class="tiraPaddingData"><input id="descEscritorio" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorEscritorio" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiro('descEscritorio','valorEscritorio','salvaNovoEscritorio',${InfoAnalitico.idAnalitico},'escritorio');" class="btn btn-default botaoMais">+</button> </td>
                     </tr>
                     <tr>
                        <td colspan="2">Descrição</td>
                        <td>Valor</td>
                     </tr>
                     <tr>
                        <td colspan="3"></td>
                     </tr>
                     <c:set var="totalEscritorio" value="0.00" />
                     <c:forEach items="${escritorio}" var="escritorio">
                        <tr>
                           <td colspan="2" class="tiraPaddingData">
                              <input id="descricao${escritorio.idFinancEscritorio}" class="ajusteInput2 input-160px tiraPaddingData" value="${escritorio.descricao}"
                                 onblur="editaCamposAnalitico('editaEscritorio','descricao${escritorio.idFinancEscritorio}',${escritorio.idFinancEscritorio},'descricao','escritorio');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${escritorio.valor < 0}">style='color:red'</c:if> >
                              <input id="valor${escritorio.idFinancEscritorio}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${escritorio.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnalitico('editaEscritorio','valor${escritorio.idFinancEscritorio}',${escritorio.idFinancEscritorio},'valor','escritorio');"
                              /> 
                           </td>
                        </tr>
                        <c:set var="totalEscritorio" value="${totalEscritorio+escritorio.valor}" />
                     </c:forEach>
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                           <b>
                              <fmt:formatNumber value="${totalEscritorio}" pattern="#,##0.00"/>
                           </b>
                        </td>
                     </tr>
                  </table>
               </td>
               <td class="juntaColunas ajusteTelefone" id="telefones">
                  <table class="table table-hover table-bordered">
                     <tr class="input-140px">
                        <td colspan="3" align="center" class="corTelefone"><b>TELEFONES</b></td>
                     </tr>
                     <tr>
                        <td class="tiraPaddingData"><input id="descTelefone" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorTelefone" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiro('descTelefone','valorTelefone','salvaNovoTelefone',${InfoAnalitico.idAnalitico},'telefones');" class="btn btn-default botaoMais">+</button> </td>
                     </tr>
                     <tr>
                        <td class="input-180px" colspan="2">Descrição</td>
                        <td class="input-30px">Valor</td>
                     </tr>
                     <tr>
                        <td colspan="3"></td>
                     </tr>
                     <c:set var="totalTelefone" value="0.00" />
                     <c:forEach items="${telefone}" var="telefone">
                        <tr>
                           <td colspan="2" class="tiraPaddingData">
                              <input id="descricaoTelefone${telefone.idFinancTelefone}" class="ajusteInput2 input-160px tiraPaddingData" value="${telefone.semCategoria}"
                                 onblur="editaCamposAnalitico('editaTelefone','descricaoTelefone${telefone.idFinancTelefone}',${telefone.idFinancTelefone},'descricao','telefones');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${telefone.valor < 0}">style='color:red'</c:if> >
                              <input id="valorTelefone${telefone.idFinancTelefone}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${telefone.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnalitico('editaTelefone','valorTelefone${telefone.idFinancTelefone}',${telefone.idFinancTelefone},'valor','telefones');"
                              /> 
                           </td>
                        </tr>
                        <c:set var="totalTelefone" value="${totalTelefone+telefone.valor}" />
                     </c:forEach>
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                           <b>
                              <fmt:formatNumber value="${totalTelefone}" pattern="#,##0.00"/>
                           </b>
                        </td>
                     </tr>
                  </table>
               </td>
              
               <td class="juntaColunas ajusteTelefone" id="folha">
                  <table class="table table-hover table-bordered">
                     <tr class="input-140px">
                        <td colspan="3" align="center" class="corFolhaPgto"><b>FOLHA PGTO</b></td>
                     </tr>
                     <tr>
                        <td class="tiraPaddingData"><input id="descFolha" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorFolha" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiro('descFolha','valorFolha','salvaNovoFolha',${InfoAnalitico.idAnalitico},'folha');" class="btn btn-default botaoMais">+</button> </td>
                     </tr>
                     <tr>
                        <td class="input-180px" colspan="2">Descrição</td>
                        <td class="input-30px">Valor</td>
                     </tr>
                     <tr>
                        <td colspan="3"></td>
                     </tr>
                     <c:set var="totalFolha" value="0.00" />
                     <c:forEach items="${folha}" var="folha">
                        <tr>
                           <td colspan="2" class="tiraPaddingData">
                              <input id="descricaoFolha${folha.idFinancFolha}" class="ajusteInput2 input-160px tiraPaddingData" value="${folha.descricao}"
                                 onblur="editaCamposAnalitico('editaFolha','descricaoFolha${folha.idFinancFolha}',${folha.idFinancFolha},'descricao','folha');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${folha.valor < 0}">style='color:red'</c:if> >
                              <input id="valorFolha${folha.idFinancFolha}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${folha.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnalitico('editaFolha','valorFolha${folha.idFinancFolha}',${folha.idFinancFolha},'valor','folha');"
                              /> 
                           </td>
                        </tr>
                        <c:set var="totalFolha" value="${totalFolha+folha.valor}" />
                     </c:forEach>
                     <tr>
                     
                     
                     
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                        	<b><fmt:formatNumber value="${totalFolha}" pattern="#,##0.00"/></b>
                        </td>
                     </tr>
                  </table>
               </td>
                <td class="juntaColunas ajusteTelefone" id="despesas">
                  <table class="table table-hover table-bordered">
                     <tr>
                        <td colspan="4" align="center" class="corFolhaPgto"><b>DESPESAS GERAIS</b></td>
                     </tr>
                     <tr>
                     	<td class="tiraPaddingData"><input id="dataDespesas" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
                        <td class="tiraPaddingData"><input id="descDespesas" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorDespesas" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiroDespesas('dataDespesas','descDespesas','valorDespesas','salvaNovoDespesas',${InfoAnalitico.idAnalitico},'despesas');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
                     </tr>
                     <tr>
                     	<td>Data</td>
                        <td>Descrição</td>
                        <td colspan="2">Valor</td>
                     </tr>
                     <tr>
                        <td colspan="4"></td>
                     </tr>
                     <c:set var="totalDespesas" value="0.00" />
                     <c:forEach items="${despesas}" var="despesas">
                        <tr>
                        	<td class="tiraPaddingData"><input id="dataDespesas${despesas.idFinancDespesas}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${despesas.data}" pattern="dd/MM"/>" type="text"
                                 onclick="mudaCampoData('dataDespesas${despesas.idFinancDespesas}');"
                                 onblur="editaCamposAnaliticoDespesas('editaDespesas','dataDespesas${despesas.idFinancDespesas}',${despesas.idFinancDespesas},'data','despesas');"
                                 /></td>
                           <td class="tiraPaddingData">
                              <input id="descricaoDespesas${despesas.idFinancDespesas}" class="ajusteInput2 tiraPaddingData input-120px " value="${despesas.descricao}"
                                 onblur="editaCamposAnaliticoDespesas('editaDespesas','descricaoDespesas${despesas.idFinancDespesas}',${despesas.idFinancDespesas},'descricao','despesas');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${despesas.valor < 0}">style='color:red'</c:if> >
                              <input id="valorDespesas${despesas.idFinancDespesas}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${despesas.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnaliticoDespesas('editaDespesas','valorDespesas${despesas.idFinancDespesas}',${despesas.idFinancDespesas},'valor','despesas');"
                              /> 
                           </td>
                        </tr>
                        <c:set var="totalDespesas" value="${totalDespesas+despesas.valor}" />
                     </c:forEach>
                     <tr>
                     
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                        	<b><fmt:formatNumber value="${totalDespesas}" pattern="#,##0.00"/></b>
                        </td>
                     </tr>
                  </table>
               </td>
               <td class="juntaColunas ajusteTelefone" id="outrasdespesas">
                  <table class="table table-hover table-bordered">
                     <tr>
                        <td colspan="4" align="center" class="corFolhaPgto"><b>OUTRAS DESPESAS</b></td>
                     </tr>
                     <tr>
                     	<td class="tiraPaddingData"><input id="dataOutrasDespesas" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
                        <td class="tiraPaddingData"><input id="descOutrasDespesas" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorOutrasDespesas" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiroDespesas('dataOutrasDespesas','descOutrasDespesas','valorOutrasDespesas','salvaNovoOutrasDespesas',${InfoAnalitico.idAnalitico},'outrasdespesas');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
                     </tr>
                     <tr>
                     	<td>Data</td>
                        <td>Descrição</td>
                        <td colspan="2">Valor</td>
                     </tr>
                     <tr>
                        <td colspan="4"></td>
                     </tr>
                     <c:set var="totalOutrasdespesas" value="0.00" />
                     <c:forEach items="${outrasdespesas}" var="outrasdespesas">
                        <tr>
                        	<td class="tiraPaddingData"><input id="dataOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${outrasdespesas.data}" pattern="dd/MM"/>" type="text"
                                 onclick="mudaCampoData('dataOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}');"
                                 onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','dataOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'data','outrasdespesas');"
                                 /></td>
                           <td class="tiraPaddingData">
                              <input id="descricaoOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData input-120px" value="${outrasdespesas.descricao}"
                                 onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','descricaoOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'descricao','outrasdespesas');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${outrasdespesas.valor < 0}">style='color:red'</c:if> >
                              <input id="valorOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${outrasdespesas.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','valorOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'valor','outrasdespesas');"
                              /> 
                           </td>
                        </tr>
                        <c:set var="totalOutrasdespesas" value="${totalOutrasdespesas+outrasdespesas.valor}" />
                     </c:forEach>
                     <tr>
                     
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                        	<b><fmt:formatNumber value="${totalOutrasdespesas}" pattern="#,##0.00"/></b>
                        </td>
                     </tr>
                  </table>
               </td>
               
<!--  ------------------------------------------------------------------------  -->
	               
               <td class="juntaColunas ajusteTelefone" id="outrasdespesas">
                  <c:import url="movimentoFinanceiro/itauEntrada.jsp" />
               </td>
               
               <td class="juntaColunas ajusteTelefone" id="outrasdespesas">
                  <c:import url="movimentoFinanceiro/itauSaida.jsp" />
               </td>

               <td class="juntaColunas ajusteTelefone" id="outrasdespesas">
                  <c:import url="movimentoFinanceiro/itauTarifas.jsp" />
               </td>

<!--  ------------------------------------------------------------------------  -->

               
            </tr>
         </tbody>
      </table>
   </div>
</div>

<div class="col-md-4" style="padding:15px;font-size:30px;text-align:center ;position:fixed ;bottom: 0;background-color: #fff;border: 1px solid #ccc;height: 60px">

 <c:set var="totalGeral" value="${totalImpostos+totalEscritorio+totalTelefone+totalFolha+totalDespesas+totalOutrasdespesas}" />
 <fmt:formatNumber value="${totalGeral}" pattern="#,##0.00"/>
 <a onclick="location.reload();" ><i class="glyphicon glyphicon-refresh" style="font-size: 20px;float: right;top: 9px"></i></a>	

</div>

<c:import url="../../../_comum/footer.jsp" />
<script type="text/javascript" src="
<c:url value="resources/js/financeiroAnalitico.js" />"></script>










