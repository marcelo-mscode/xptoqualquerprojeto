<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
<style type="text/css">
.financeiro{background: #f1f1f1;}
.cabecalhoLista {text-align: center !important;line-height: 33px !important;}
.cabecalhoLista > td {line-height: 33px !important;}
.descricao {text-align: left;}


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

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">


	<table class="table table-striped table-hover table-condensed" >
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important;text-align: center !important;">
			<td colspan="5" style="text-align: center !important;">Fornecedores - Eventos do Mês</td>
		</tr>
		<tr style="background: #f1f1f1 !important;" class="cabecalhoLista">
			<td align="left">NF/ND</td>
			<td align="left">Vencimento</td>
			<td align="left">Descrição</td>
			<td align="left" colspan="2">Valor</td>
		</tr>
			
		 <c:forEach items="${idListas}" var="idListas">

		 	<tr>
		 		<td colspan="5" align="left"><h4><b>${idListas[1]}</b></h4></td>
		 	</tr>
		 
			<c:forEach items="${novaLista}" var="novaLista">
				<c:if test="${idListas[0] == novaLista[3]}">
					<tr>
						<%-- <td>${novaLista[3]}</td> --%>
						<td>${novaLista[2]}</td>
						<td><fmt:formatDate value="${novaLista[0]}"   pattern="dd/MM/yyyy" /></td>
						<td>${novaLista[1]}</td>
						<td><fmt:formatNumber value="${novaLista[4]}" pattern="#,##0.00" /></td>
						<td><a href="" class="btn btn-success">PAGAR</a> </td>
					</tr>
				</c:if>
			</c:forEach>
		 </c:forEach>	

		</table>

	</div>
</div>          