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
.descricao {text-align: left;}
.temContratacao{border-left: 1px solid red;padding-left: 15px !important}
.pLeft{padding-left: 15px !important}
.ajusteFinanceiroBar {position: fixed;width: 150%;}
.ajusteFinanceiro {float: none !important;}
.breadcrumbFinanc{ height: 35px;border-bottom: 1px solid #ccc;margin-top: 49px;width: 150%;position: fixed;z-index: 9}
.textoContasPagar{height: 35px;margin-top: 85px;position: fixed;width: 100%;z-index: 9}
.subtextoContasPagar{margin: -1px 0px 25px -14px;text-align: center;background-color: #f8f8f8;border-bottom: 1px solid #ccc;}

</style>
<div class="col-md-12 bodyXY breadcrumbFinanc">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Contas Pagar</li>
		  </ol>					
</div>

<div class="textoContasPagar">
	<div class=subtextoContasPagar>
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">CONTAS A PAGAR</span>
	</div>
</div>

<div id="criacaoListas" class="efeitoDegrade" style="font-family: 'OpenSansLight';padding:35px 40px 70px 35px;width: 150%;margin-top: 119px;">
 <div style="padding: 20px 20px 40px 20px;box-shadow: 0px 0px 30px 5px #ccc;margin-bottom: 250px;" class="col-md-12">
		<table class="col-md-12">

			<tr>
				<td style="width: 40%;vertical-align: top;">
 				 	<div id="contasPagarAjax" class="" style="padding-right: 0;border-right: 5px solid #ccc;border-left: 5px solid #ccc;">
						<c:import url="contasPagarMesAtual.jsp" />
					</div>	
				</td>
				
				<td style="width: 0%;vertical-align: top;">
 					<div id="contasPagarAjaxMesAnterior" class="" style="padding-right: 0;border-left: 5px solid #ccc;border-right: 5px solid #ccc;">
						<c:import url="contasPagarMesAnterior.jsp" />
					</div>
				</td>
				
			</tr>
		</table>

	</div>
		
  </div>		
						
<div class="col-md-4" style="padding:15px;font-size:30px;text-align:center ;position:fixed ;bottom: 54px;background-color: #fff;border: 1px solid #ccc;height: 60px">
 
 <fmt:formatNumber value="${somaTotal}" pattern="#,##0.00" />
 <a onclick="location.reload();"><i class="glyphicon glyphicon-refresh" style="font-size: 20px;float: right;top: 9px"></i></a>	
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="ConfirmaPagamento" style="position: fixed; display: none;background-color: rgba(255, 255, 255, 0.7);">
	<div class="col-md-4"></div>


	<div class="col-md-4 sub-div-confirmacao" style="height: 85px !important;box-shadow: 0px 2px 18px 10px #ccc">
		Efetuando pagamento ... <br>
		<div class="progress col-md-12" style="margin-top: 20px;">
			<div class="progress-bar progress-bar-striped active"
				role="progressbar" aria-valuenow="45" aria-valuemin="0"
				aria-valuemax="100" style="width: 100%">
				<span class="sr-only">100% Complete</span>
			</div>
		</div>
	</div>
	<div class="col-md-4"></div>
</div>


<style>
 .navegacaoResumoMes{padding-top: 8px;font-size: 12px;position: fixed;bottom: 0px;background-color: #fff;border: 2px solid #ccc;height: 55px;}
 .navegacaoLink{color: green;font-weight: bold;text-transform: uppercase;}	
 .navegacaoLink{color: green;font-weight: bold;text-transform: uppercase;}	
 .tiraBordaTabela tr td {border-top: none !important; border-left: 1px solid #ddd;background-color: #f5f5f5}
 .active{background-color: #ddd !important}	
</style>

<div class="col-md-12 navegacaoResumoMes">
	<table class="table tiraBordaTabela">
		<tr>
			<td class="input-95px"><a href="resumoMesIndividual?mes=01&ano=2017" class="navegacaoLink">Resumo mês</a></td>
			<td class="input-120px active"><a href="contasPagar" class="navegacaoLink">Contas a pagar</a></td>
			<td class=""><a href="contasReceber" class="navegacaoLink">Contas a receber</a></td>
		</tr>
	</table>
</div>


<c:import url="../../_comum/footer.jsp" />
