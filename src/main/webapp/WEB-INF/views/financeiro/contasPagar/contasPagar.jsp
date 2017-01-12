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
.temContratacao{border-left: 1px solid red}


</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Contas Pagar</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">CONTAS PAGAR</span>
	</div>
</div>

<div id="criacaoListas" class="efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;width: 200%;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
 	<div id="contasPagarAjax" class="" style="width: 40%;padding-right: 0;border-right: 2px solid #ccc;">

	<table>
		<tr>
			<td></td>
		</tr>
	</table>
	
	<%-- <table class="table table-striped table-hover table-condensed" >
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important;text-align: center !important;">
			<td colspan="5" style="text-align: center !important;"><h4>Fornecedores - Eventos do Mês</h4></td>
		</tr>
		<tr style="background: #f1f1f1 !important;" class="cabecalhoLista">
			<td align="left">NF/ND</td>
			<td align="left">Vencimento</td>
			<td align="left">Descrição</td>
			<td align="left" colspan="2">Valor</td>
		</tr>
	

		 <c:set var="valorTotal" value="0.00" />			

		 <c:forEach items="${idListas}" var="idListas">

		 	<tr>
		 		<td colspan="5" align="left"><h4><b>${idListas[1]}</b></h4></td>
		 	</tr>
		 
			<c:forEach items="${novaLista}" var="novaLista" varStatus="loop">
				<c:if test="${idListas[0] == novaLista[3]}">
					<tr id="valor${loop.count}">
						<td>${novaLista[7]}</td>
						
						<td>TemContratacao : ${novaLista[7]}</td>
						<td>Tem Mesmo Fornecedor: ${novaLista[8]}</td>
						<td>ValorPgto Contratacao: ${novaLista[9]}</td>
						<td>Valor Contratacao: ${novaLista[10]}</td>
						
						
						
						<td <c:if test="${novaLista[7] == true}">class="temContratacao"</c:if>>${novaLista[2]}</td>

						<td><fmt:formatDate value="${novaLista[0]}"   pattern="dd/MM/yyyy" /></td>
						<td>${novaLista[1]}</td>
						<td><fmt:formatNumber value="${novaLista[4]}" pattern="#,##0.00" /></td>
						<td><a onclick="pagaContas(${idListas[0]},${novaLista[5]},${novaLista[6]},valor${loop.count});" class="btn btn-success">PAGAR</a> </td>
						<c:set var="valorTotal" value="${valorTotal+novaLista[4]}" />			
					</tr>
				</c:if>
			</c:forEach>
		 </c:forEach>	
		</table> 
		<h1><fmt:formatNumber value="${valorTotal}" pattern="#,##0.00" /></h1> --%>
		
		<c:import url="contasPagarMesAtual.jsp" />

	</div>
	
	<c:import url="contasPagarMesAnterior.jsp">
	
	
	</c:import>
	
	
	
	
	
	
		
  </div>		
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


						











<c:import url="../../_comum/footer.jsp" />