<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />
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


</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li><a href="relatorioEventosIndex">Relatório de Eventos</a></li>
		    <li class="active">Relatório ${infoLista.lista}</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">
			EVENTO 3 - MÊS<br> 
			<c:forEach items="${infoLista.idJob.localEvento}" var="localEvento"> 
				<fmt:formatDate value="${localEvento.localEventoDataInicio}" pattern="dd/MM/yyyy"/><br>  
			</c:forEach>
		</span>
	<a href="internaIndividual?idLista=${infoLista.idLista}" style="float: right;">Planilha Interna: ${infoLista.lista}</a>
	</div>
	
	
</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 5px 70px 5px;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
 	<div class="col-md-9" style="padding-left: 0;">
	<table class="table table-striped table-hover table-bordered bordaDestaque" >
	  <tbody id="prospeccaoFiltro">
		<tr>
			<td>Cliente</td>
			<td>${infoLista.idJob.empresa.empresa}</td>
			<td></td>
			<td>Data do Evento</td>
			<td>06/05/2016</td>
			
		</tr>

		<tr>
			<td>Nome do Evento</td>
			<td>${infoLista.lista}</td>
			<td>NF</td>
			<td>Local do Evento</td>
			<td>SP</td>
		</tr>
	  </tbody>
	</table>
	</div>
	
	<div class="col-md-3" style="padding-left: 0;">
	<table class="table table-striped table-hover table-bordered bordaDestaque" >
	  <tbody id="prospeccaoFiltro">
		<!-- <tr style="background: #f1f1f1 !important;text-align: center !important;">
			<td colspan="5" style="text-align: center !important;">CONTAS A RECEBER<br>Notas Fiscais e Debitos</td>
		</tr> -->
		<tr>
			<td colspan="2" class="descricao" style="border-left: 2px solid #ccc !important">Valor Locco Agência =></td>
			<td class="descricao"><b><fmt:formatNumber value="${relatorio.valorLoccoAgenc}" pattern="#,##0.00"/></b></td>
		</tr>

		<tr>
			<td class="descricao" style="border-left: 2px solid #ccc !important">% Imposto =></td>
			<td class="descricao" style="background-color: red;color: #fff"><b>15,95%</b></td>
			<td class="descricao"><b><fmt:formatNumber value="${relatorio.impostoSobreValorLoccoAgencia}" pattern="#,##0.00"/></b></td>
		</tr>
	  </tbody>
	</table>
	</div>
	
	<div class="col-md-3" style="padding-left: 0;">
	 <table class="table table-striped table-hover table-bordered bordaDestaque">
	  <tbody id="prospeccaoFiltro">
	  <tr>
	  	<td colspan="3" align="center">Cachês</td>
	  </tr>
		<tr style="background: #f1f1f1 !important">
			<td>Nome</td>
			<td>%</td>
			<td>Valor</td>
	 	
	 	<c:forEach items="${relatorioCaches}" var="relatorioCaches">
		 	<tr>
				<td>${relatorioCaches.cachePadrao.nomeFunc}</td>
				<td>${relatorioCaches.cachePadrao.porcentagem}%</td>
				<td><fmt:formatNumber value="${relatorioCaches.valor}" pattern="#,##0.00"/></td>
		 	</tr>	
	 	 </c:forEach>
	 	
	<c:forEach var="i" begin="1" end="4">
  		<tr>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
		</tr>
	</c:forEach>
		
		<tr class="bordaDestaque">
			<td>TOTAL</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.totalCache}" pattern="#,##0.00"/></td>
		</tr>
		
		<tr>
			<td>Equipe In</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.cacheEquipIn}" pattern="#,##0.00"/></td>
		</tr>
		
		<tr>
			<td>Equipe Ex</td>
			<td colspan="2"></td>
		</tr>
		
		<tr>
			<td>Ma e Pe</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.diretoria1}" pattern="#,##0.00"/></td>
		</tr>
		
		<tr>
			<td>Célia</td>
			<td colspan="2"><fmt:formatNumber value="${relatorio.diretoria2}" pattern="#,##0.00"/></td>
		</tr>
	  </tbody>
	 </table>
	</div>
	
	<div class="col-md-6" style="padding-left: 0;">	
	<table class="table table-striped table-hover table-bordered bordaDestaque">
	  <tbody id="prospeccaoFiltro">
		<tr>		
			<td colspan="4" align="center" style="border-right: 0px solid #ccc;">BV'S</td>
			<td></td>
		</tr>
		<tr style="background: #f1f1f1 !important">
			<td>Fornecedor</td>
			<td class="descricao">Valor</td>
			<td class="descricao">Pagto</td>
			<td class="descricao">R$</td>
			<td class="descricao">À pagar</td>
		</tr>
	
	
		<c:forEach items="${relBVS}" var="relatorioBVS"> 
		<tr>
			<td>${relatorioBVS.nomeFornecedor}</td>
			<td class="ajusteValores"><fmt:formatNumber value="${relatorioBVS.valorFornecedor}" pattern="#,##0.00"/></td>
			<td class="ajusteValores">
				<c:if test="${relatorioBVS.idFornecedor != '6961'}">
					<fmt:formatNumber value="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca}" pattern="#,##0.00"/>
				</c:if>
				<c:if test="${relatorioBVS.idFornecedor == '6961'}">
					-
				</c:if>
			</td>
			<td class="ajusteValores">
				<c:if test="${relatorioBVS.diferenca != '0.00'}">
					<fmt:formatNumber value="${relatorioBVS.diferenca}" pattern="#,##0.00"/>
				</c:if>

				<c:if test="${relatorioBVS.diferenca == '0.00'}">
					-
				</c:if>
				
			</td>
			<td class="ajusteValores">
			<c:if test="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca != '0.00'}">
				<fmt:formatNumber value="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca}" pattern="#,##0.00"/>
			</c:if>
			
			<c:if test="${relatorioBVS.valorFornecedor - relatorioBVS.diferenca == '0.00'}">
				-
			</c:if>
			
			
			
			</td>
		</tr>
		</c:forEach>
		
		<c:forEach var="i" begin="1" end="3">
  		<tr>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
		</tr>
		</c:forEach>
		
		<tr>
			<td>Mcontribuição</td>
			<td></td>
			<td><fmt:formatNumber value="${relatorio.margemContribuicao}" pattern="#,##0.00"/></td>
			<td>(<fmt:formatNumber value="${relatorio.margemContribuicao}" pattern="#,##0.00"/>)</td>
			<td></td>
		</tr>
		
		<tr>
			<td>Custo Telefone</td>
			<td></td>
			<td><fmt:formatNumber value="${relatorio.custoTelefone}" pattern="#,##0.00"/></td>
			<td>(<fmt:formatNumber value="${relatorio.custoTelefone}" pattern="#,##0.00"/>)</td>
			<td></td>
		</tr>
		
		<tr>
			<td>FEE 14%</td>
			<td><fmt:formatNumber value="${infoLista.administracaoValor}" pattern="#,##0.00"/></td>
			<td></td>
			<td><fmt:formatNumber value="${infoLista.administracaoValor}" pattern="#,##0.00"/></td>
			<td></td>
		</tr>

		<tr>
			<td>Imposto cliente 22,90%</td>
			<td><fmt:formatNumber value="${infoLista.impostoValor}" pattern="#,##0.00"/></td>
			<td></td>
			<td><fmt:formatNumber value="${infoLista.impostoValor - (infoLista.valorTotal * 0.1595)}" pattern="#,##0.00"/></td>
			<td></td>
		</tr>
		


		<tr class="bordaDestaque">
			<td><b>TOTAL</b></td>
			<td><b><fmt:formatNumber value="${infoLista.valorTotal}" pattern="#,##0.00"/></b></td>
			<td><b><fmt:formatNumber value="${relatorio.pgtoExternas}" pattern="#,##0.00"/></b></td>
			<td><b><fmt:formatNumber value="${relatorio.totalDiferenca}" pattern="#,##0.00"/></b></td>
			<td>

			<c:set var="valorApagar" value="" />
			<c:forEach items="${relBVS}" var="relatorioBVS"> 
				<c:set var="valorApagar" value="${valorApagar + (relatorioBVS.valorFornecedor - relatorioBVS.diferenca)}" />
			</c:forEach>
				<b><fmt:formatNumber value="${valorApagar}" pattern="#,##0.00"/></b>
			</td>
			
			
			
			
		</tr>
		<tr>
			<td colspan="5" style="padding: 18px"></td>
		</tr>
		<tr>
			<td></td>
			<td  class="esquerda topo" colspan="2" >Liq. Imp - total pagto</td>
			<td class="direita topo"><fmt:formatNumber value="${relatorio.totalDiferenca}" pattern="#,##0.00"/></td>
			<td></td>
		</tr>
		
		<tr>
			<td></td>
			<td class="esquerda" colspan="2" >Total Caches int. e  Ext</td>
			<td class="direita"><fmt:formatNumber value="${relatorio.totalCachesIntExt}" pattern="#,##0.00"/></td>
			<td></td>
		</tr>
		
		<tr>
			<td ></td>
			<td class="esquerda" colspan="2"></td>
			<td class="direita"><fmt:formatNumber value="${relatorio.diferencaCacheFuncionariosTotalPgto}" pattern="#,##0.00"/></td>
			<td></td>
		</tr>
		
		
	</tbody>
	</table>
	</div>
		
	<div class="col-md-3" style="padding-left: 0;">
	<table class="table table-striped table-hover table-bordered bordaDestaque">
	  <tbody id="prospeccaoFiltro">
	  <tr>
	  	<td align="center" colspan="2" >Liquido de Impostos =></td>
	  	<td align="center"><b><fmt:formatNumber value="${infoLista.valorTotal - (infoLista.valorTotal * infoInterna.impostoInterna)}" pattern="#,##0.00"/></b></td>
	  </tr>
	  <tr>
	  	<td colspan="3" style="padding: 18px"></td>
	  </tr>
	  
	  <tr>
	  	<td colspan="3" align="center">RESUMO</td>
	  </tr>
	  
	  
	  <tr>
	 		<td></td>
	 		<td></td>
	 		<td></td>
	 	</tr>
		<tr>
			<td>Serviços</td>
			<td></td>
			<td><fmt:formatNumber value="${infoLista.valorTotal}" pattern="#,##0.00"/></td>
		</tr>	
	 		
		<tr>
			<td>Impostos</td>
			<td>( - )</td>
			<td><fmt:formatNumber value="${infoLista.valorTotal * 0.1595}" pattern="#,##0.00"/></td>
		</tr>	
	 		
		<tr>
			<td>Vlr Liquido</td>
			<td></td>
			<td><fmt:formatNumber value="${infoLista.valorTotal - (infoLista.valorTotal * 0.1595)}" pattern="#,##0.00"/></td>
		</tr>	
	 	
	 	
		<tr>
			<td>Caches</td>
			<td>(-)</td>
			<td><fmt:formatNumber value="${relatorio.totalCachesComTelefone}" pattern="#,##0.00"/></td>
		</tr>	
	 	
	 	
		<tr>
			<td>BV'S</td>
			<td>(+)</td>
			<td>-</td>
		</tr>	
	 	
	 	<tr>
	 		<td colspan="2"></td>
	 		<td></td>
	 	</tr>

		<tr>
			<td colspan="3">Pagamentos</td>
		</tr>	
	 	
		<tr>
			<td>Internas</td>
			<td>(-)</td>
			<td>-</td>
		</tr>	
	 	
	 	
		<tr>
			<td>Externas</td>
			<td>(-)</td>
			<td><fmt:formatNumber value="${relatorio.pgtoExternas}" pattern="#,##0.00"/></td>
		</tr>	
	 	
	 	<c:forEach var="i" begin="1" end="1">
  		<tr>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
			<td style="padding: 18px;"></td>
		</tr>
		</c:forEach>
	 	
	 	<tr>
	 		<td colspan="2" style="padding: 8px;"></td>
	 		<td style="padding: 8px;"></td>
	 	</tr>
		<tr>
			<td colspan="2">Giro Inicial s/ Telefone</td>
			<td><fmt:formatNumber value="${relatorio.giroEvento.giroSemTelefone}" pattern="#,##0.00"/></td>
		</tr>	
	 	<tr>
	 		<td colspan="2" style="padding: 11px;"> </td>
	 		<td style="padding: 11px;"></td>
	 	</tr>
	 	
		<tr class="bordaDestaque">
			<td colspan="2" >Giro</td>
			<td><fmt:formatNumber value="${relatorio.giroEvento.giroComTelefone}" pattern="#,##0.00"/></td>
		</tr>	
		
		<tr>
			<td style="padding: 18px"  colspan="3" ></td>
		</tr>
		<tr>
			<td>VENC</td>
			<td>NFE</td>
			<td>VALOR</td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${infoLista.infoInterna.dataPagamento}" pattern="dd/MM/yyyy"/> </td>
			<td>${infoLista.infoInterna.nfInterna}</td>
			<td><fmt:formatNumber value="${infoLista.valorTotal}" pattern="#,##0.00"/></td>
		</tr>
		
		

		</tbody>
		</table>
	</div>	
		
		
		
  </div>		
</div>



<c:import url="../../_comum/footer.jsp" />
