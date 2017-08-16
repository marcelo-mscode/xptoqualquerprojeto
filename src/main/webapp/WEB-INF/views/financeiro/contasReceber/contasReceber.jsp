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


</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Contas Receber</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">CONTAS RECEBER</span>
	</div>
</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
 	<div class="col-md-8" style="padding-right: 0;border-right: 2px solid #ccc;">
	<table class="table table-striped table-hover table-condensed" >
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important;text-align: center !important;">
			<td colspan="6" style="text-align: center !important;">CONTAS A RECEBER<br>Notas Fiscais e Debitos</td>
		</tr>
		<tr style="background: #f1f1f1 !important" class="cabecalhoLista">
			<td>NF/ND</td>
			<td>Descrição</td>
			<td>Vencimento</td>
			<td>Valor</td>
			<td>Banco</td>
			<td></td>
		</tr>
		

	
		<c:forEach items="${infoInterna}" var="infoInterna">
			<c:forEach items="${listaReceber}" var="listaReceber">
			  <c:if test="${infoInterna.lista.idLista == listaReceber.idLista}">
				<tr class="cabecalhoLista">
					<td>NF ${infoInterna.nfInterna}</td>
					<td class="descricao">${infoInterna.lista.lista}</td>
					<td><fmt:formatDate value="${infoInterna.dataPagamento}" pattern="dd/MM/yyyy" /></td>
					<td><fmt:formatNumber value="${listaReceber.valorLoccoAgenc}" pattern="#,##0.00"/> </td>
					<form action="receberConta" method="post" >
					<input type="hidden" value="${infoInterna.lista.idLista}" name="idLista">
					
						<td>
							<select class="form-control" name="tipoBanco">
								<option value="0">Banco</option>
								<option value="1">Itau</option>
								<option value="2">CEF</option>
								<option value="3">Bradesco</option>
								<option value="4">Santander</option>
							</select>
						</td>
						<td><button type="submit" class="btn btn-success">Recebido</button></td>
					</form>
				</tr>
			  </c:if>
			 </c:forEach>
		 </c:forEach>

		 <c:set var="valorTotal" value="0" />
		 <c:forEach items="${listaReceber}" var="listaReceber">
			 <c:set var="valorTotal" value="${valorTotal+listaReceber.valorLoccoAgenc}" /> 
		 </c:forEach>
	    
		 <tr>
		 	<td><b>Total a Receber</b></td>
		 	<td></td>
		 	<td></td>
		 	<td><b><fmt:formatNumber value="${valorTotal}" pattern="#,##0.00"/></b></td>
		 	<td></td>
		 </tr>	
		
	  </tbody>
	</table>
	</div>
	
	<div class="col-md-4" style="padding-left: 0;">
	<table class="table table-striped table-hover table-condensed">
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important;text-align: center !important;">
			<td colspan="5" style="text-align: center !important;height: 51px;line-height: 40px;">Outras Contas</td>
		</tr>
		<tr style="background: #f1f1f1 !important" class="cabecalhoLista">
			<td>NF/ND</td>
			<td>Vencimento</td>
			<td>Descrição</td>
			<td>Valor</td>
			<td></td>
		</tr>
		
		<tr class="cabecalhoLista">
			<td></td>
			<td>05/06/2016</td>
			<td class="descricao">GIRO ITAU 36/26</td>
			<td>900,00</td>
			<td><a href="" class="btn btn-success">Pagar</a></td>
		</tr>
		
		<tr class="cabecalhoLista">
			<td></td>
			<td>05/07/2016</td>
			<td class="descricao">GIRO ITAU 37/26</td>
			<td>900,00</td>
			<td><a href="" class="btn btn-success">Pagar</a></td>
		</tr>
		
		<tr class="cabecalhoLista">
			<td></td>
			<td>05/08/2016</td>
			<td class="descricao">GIRO ITAU 38/26</td>
			<td>900,00</td>
			<td><a href="" class="btn btn-success">Pagar</a></td>
		</tr>
		<tr class="cabecalhoLista">
			<td></td>
			<td>05/09/2016</td>
			<td class="descricao">GIRO ITAU 38/26</td>
			<td>900,00</td>
			<td><a href="" class="btn btn-success">Pagar</a></td>
		</tr>
		<tr class="cabecalhoLista">
			<td></td>
			<td>05/10/2016</td>
			<td class="descricao">GIRO ITAU 38/26</td>
			<td>900,00</td>
			<td><a href="" class="btn btn-success">Pagar</a></td>
		</tr>
		
	</tbody>
	</table>
	</div>	
		
  </div>		
</div>

<style>
 .navegacaoResumoMes{padding-top: 8px;font-size: 12px;position: fixed;bottom: 0px;background-color: #fff;border: 2px solid #ccc;height: 55px;}
 .navegacaoLink{color: green;text-transform: uppercase;}	
 .tiraBordaTabela tr td {border-top: none !important; border-left: 1px solid #ddd;background-color: #f5f5f5}
 .acctive{background-color: #ddd !important;font-weight: bold;}	
 .tiraBordaTabela tr td a {text-decoration: none;}
 .tiraBordaTabela tr td a:hover{font-weight: bold;}
 
</style>

<div class="col-md-12 navegacaoResumoMes">
	<table class="table tiraBordaTabela">
		<tr>
			<td class="input-95px"><a href="resumoMesIndividual?mes=01&ano=2017" class="navegacaoLink">Resumo mês</a></td>
			<td class="input-120px"><a href="contasPagar" class="navegacaoLink">Contas a pagar</a></td>
			<td class="input-140px acctive"><a href="contasReceber" class="navegacaoLink">Contas a receber</a></td>
			<td class=""><a href="contasReceber" class="navegacaoLink"> - </a></td>
		</tr>
	</table>
</div>


<c:import url="../../_comum/footer.jsp" />
