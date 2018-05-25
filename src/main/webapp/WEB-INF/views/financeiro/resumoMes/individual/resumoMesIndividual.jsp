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
.descricao {text-align: left;}
.temContratacao{border-left: 1px solid red}
.descEventoClientes > tr, .descEventoClientes > tr > th {text-align: center}
.semPadding > tr > th {padding: 0 !important}


.totalBancos{vertical-align: middle !important;text-align: center;font-weight: bold;border:1px solid #ddd}
.fundotabela3{background-color: rgb(204,255,255) }
.fundoDespesasFixas{background-color: rgb(255,204,153) }
.fundoAmarelo{background-color: rgb(255,255,153)}
.fundoRosa{background-color: rgb(255,153,204)}
.fundoVerde{background-color: rgb(204,255,204)}

.colorRed{color: red}

</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li><a href="resumoMesIndex">Lista Resumos Mês</a></li>
		    <li class="active">Resumo Mês XXXX</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;" align="center">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">Resumo do Mês de XXXXXX</span>
	</div>
</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;">
	<div style="padding: 0 20px 0 20px;">
		<table class="table table-striped table-hover table-condensed" style="border:1px solid #ddd">
		  <tbody id="prospeccaoFiltro">
			
			<c:import url="saldosBancarios.jsp" />
	
		</tbody>
	</table>
</div>

<div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;">
	<table class="table table-striped table-hover table-condensed" style="margin-top: 20px; border: 1px solid #ddd">
		  <tbody class="descEventoClientes">
				<c:import url="dadosEventos.jsp" />	
		  </tbody>
	</table>
	
	<table class="table table-striped table-hover table-condensed" style="border: 1px solid #ddd"">
		  <tbody class="descEventoClientes">
			
			<c:import url="despesasBvs.jsp" />	 <tr><td colspan="7"></td></tr>
	
		</tbody>
	</table>
	
	<table class="table table-striped table-hover table-condensed" style="border: 1px solid #ddd"">
		  <tbody class="descEventoClientes">

			<c:import url="despesasFixas.jsp" /> <tr><td colspan="7"></td></tr>
			<c:import url="despesasVariaveis.jsp" /> <tr><td colspan="7"></td></tr>

		</tbody>
	</table>


	<table class="table table-striped table-hover table-condensed" style="border: 1px solid #ddd"">
		  <tbody class="descEventoClientes">
			<c:import url="contasBvsTotal.jsp" /> <tr><td colspan="7"></td></tr>
		</tbody>
	</table>

	<table class="table table-striped table-hover table-condensed" style="border: 1px solid #ddd"">
		  <tbody class="descEventoClientes">
			<c:import url="resumoFinal.jsp" /> <tr><td colspan="7"></td></tr>
		</tbody>
	</table>
	
	<table class="table table-striped table-hover table-condensed" style="border: 1px solid #ddd"">
		  <tbody class="descEventoClientes semPadding">
			<c:import url="contasCorrente.jsp" /> <tr><td colspan="7"></td></tr>
		</tbody>
	</table>
	
</div>
	
	
</div>

<style>
 .navegacaoResumoMes{padding-top: 8px;font-size: 12px;position: fixed;bottom: 0px;background-color: #fff;border: 2px solid #ccc;height: 55px;}
 .navegacaoLink{color: green;font-weight: bold;text-transform: uppercase;}	
 .navegacaoLink{color: green;font-weight: bold;text-transform: uppercase;}	
 .tiraBordaTabela tr td {border-top: none !important; border-left: 1px solid #ddd;background-color: #f5f5f5}
 .active{background-color: #ddd !important}	
 #toTop{display: none !important;}
</style>

<div class="col-md-12 navegacaoResumoMes">
	<table class="table tiraBordaTabela">
		<tr>
			<td class="input-95px active"><a href="resumoMesIndividual?mes=01&ano=2017" class="navegacaoLink">Resumo mês</a></td>
			<td class="input-80px"><a href="analiticoIndividual?idAnalitico=3" class="navegacaoLink">Analítico</a></td>
			<td class="input-120px"><a href="contasPagar" class="navegacaoLink">Contas a pagar</a></td>
			<td class=""><a href="contasReceber" class="navegacaoLink">Contas a receber</a></td>
		</tr>
	</table>
</div>


<c:import url="../../../_comum/footer.jsp" />