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
 	<div class="col-md-12" style="padding-right: 0;border-right: 2px solid #ccc;">
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
		<c:set var="valorTotal" value="0" />
		<c:forEach items="${infoInterna}" var="infoInterna">
			<c:forEach items="${listaReceber}" var="listaReceber">
			  <c:if test="${infoInterna.lista.idLista == listaReceber.idLista}">
				<tr class="cabecalhoLista">
					<td>
						<c:if test="${listaReceber.ndFatDireto == true}">ND</c:if> 
						<c:if test="${listaReceber.ndFatDireto == false}">NF</c:if> 
						${infoInterna.nfInterna}
					</td>

					<td class="descricao">
						${infoInterna.lista.lista} 
						<c:if test="${listaReceber.ndFatDireto == true}">(ND)</c:if>
					</td>
					<td>
						<fmt:formatDate value="${infoInterna.dataPagamento}" pattern="dd/MM/yyyy" />
						<a onclick="mudaDataContasReceber(${infoInterna.nfInterna}, '${infoInterna.lista.lista}', ${infoInterna.idInfoInterna});" style="cursor:pointer;padding 10px;margin-left: 10px;" ><i class="glyphicon glyphicon-pencil" style="top:0px"></i></a>
					</td>

					<td>
					
					<fmt:formatNumber value="${listaReceber.valorLoccoAgenc}" pattern="#,##0.00"/>
					<input value='<fmt:formatNumber value="${listaReceber.valorLoccoAgenc}" pattern="#,##0.00"/>' id="valorAlterar${infoInterna.idInfoInterna}" type="hidden">
					</td>


					<td> 
						<select class="form-control" name="tipoBanco" id="tipoBancoContaReceber${listaReceber.idRelatorioEvento}">
							<option value="0">Banco</option>
							<option value="1">Itau</option>
							<option value="2">CEF</option>
							<option value="3">Bradesco</option>
							<option value="4">Santander</option>
						</select>
					</td>
					<td><button class="btn btn-success" onclick="receberContas(${listaReceber.idRelatorioEvento}, ${infoInterna.lista.idLista},${listaReceber.ndFatDireto});">Receber</button></td>
						<!-- </form> -->
				</tr>
				  <c:set var="valorTotal" value="${valorTotal+listaReceber.valorLoccoAgenc}" /> 
			  </c:if>
			  
			  
			  
			 </c:forEach>
		 </c:forEach>

		<%--  <c:set var="valorTotal" value="0" />
		 <c:forEach items="${listaReceber}" var="listaReceber">
			 <c:set var="valorTotal" value="${valorTotal+listaReceber.valorLoccoAgenc}" /> 
		 </c:forEach> --%>
	    
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
	
  </div>		
</div>

<style>
 .navegacaoResumoMes{padding-top: 8px;font-size: 12px;position: fixed;bottom: 0px;background-color: #fff;border: 2px solid #ccc;height: 55px;}
 .navegacaoLink{color: green;text-transform: uppercase;}	
 .tiraBordaTabela tr td {border-top: none !important; border-left: 1px solid #ddd;background-color: #f5f5f5}
 .acctive{background-color: #ddd !important;font-weight: bold;}	
 .tiraBordaTabela tr td a {text-decoration: none;}
 .tiraBordaTabela tr td a:hover{font-weight: bold;}
  #toTop{display: none !important;}
 .sub-div-confirmacao{margin-top: 15% !important; }
 .fontSansLight{font-family: 'OpenSansLight';line-height: 30px;margin-top: 10px;}
</style>

<div class="col-md-12 navegacaoResumoMes">
	<table class="table tiraBordaTabela">
		<tr>
			<td class="input-95px"><a href="resumoMesIndex" class="navegacaoLink">Resumo mês</a></td>
			<td class="input-120px"><a href="contasPagar" class="navegacaoLink">Contas a pagar</a></td>
			<td class="input-140px acctive">CONTAS A RECEBER</td>
			<td class=""><a href="contasReceber" class="navegacaoLink"> - </a></td>
		</tr>
	</table>
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="erroReceberConta" style="position: fixed; display: none;background-color: rgba(255, 255, 255, 0.8);">
	<div class="col-md-4"></div>
	<div class="col-md-12 sub-div-confirmacao" style="height: 190px !important;box-shadow: 0px 2px 18px 10px #ccc">
		
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true" style="font-size: 35px; color: #F7E967"></span>
		<h3 class="fontSansLight">Não é possível receber essa conta.</h3>
		<h5>Verifique a data de vencimento ou gere um Analítico para o mês e ano do vencimento informados.</h5>
		<br>
		<button type="button" class="btn btn-success" onclick="location.reload();" style="margin-top: 8px;">Voltar</button>
	</div>
	<div class="col-md-4"></div>
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="sucessoReceberConta" style="position: fixed; display: none;background-color: rgba(255, 255, 255, 0.8);">
	<div class="col-md-4"></div>
	<div class="col-md-12 sub-div-confirmacao" style="height: 190px !important;box-shadow: 0px 2px 18px 10px #ccc">
		<span class="glyphicon glyphicon-ok" aria-hidden="true" style="font-size: 35px; color: #5cb85c;margin-top: 35px;"></span>
		<h3 class="fontSansLight">Conta recebida com sucesso.</h3>
		<h5 class="fontSansLight">Recarregando página ...</h5>
	</div>
	<div class="col-md-4"></div>
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="mudaDataVencimentoModel" style="position: fixed; display: none;background-color: rgba(255, 255, 255, 0.8);">
	<div class="col-md-4"></div>
	<div class="col-md-12 sub-div-confirmacao" style="height: 210px !important;box-shadow: 0px 2px 18px 10px #ccc">
		
		<button type="button" class="close" data-dismiss="modal" style="font-size: 30px" onclick="location.reload();">
         X
        </button>
        
        
		<h4 class="fontSansLight">ALTERAR DATA DE VENCIMENTO</h4>	
				
		<table class="table table-striped table-hover table-condensed" >
			<tr>
				<td>NF/ND</td>
				<td>Descrição</td>
				<td>Vencimento</td>
				<td>Valor</td>
			</tr>
			<tr style="border-bottom: 1px solid #ddd;">
				<td><span id="NDNFalterar"></span></td>
				<td><span id="Eventoalterar"></span></td>
				<td>
					<input type="date" style="border: 1px solid #ddd;border-radius: 4px;height: 22px;" id="dataParaAlterar">
					<input id="idInfoInterna" type="hidden">
				</td>
				<td><span id="Valoralterar"></span></td>
			</tr>
		</table>
		<button class="btn btn-success btnAlterar" onclick="alterardata();">ALTERAR</button>
	</div>
	<div class="col-md-4"></div>
</div>





<c:import url="../../_comum/footer.jsp" />

<script type="text/javascript">

function receberContas(idRelatorioEvento,idLista,ndnf) {
	
	var tipoBanco = $("#tipoBancoContaReceber"+idRelatorioEvento).val();
	
	if(tipoBanco == 0){
		alert("Selecione um Banco");
		$("#tipoBancoContaReceber"+idRelatorioEvento).css("border", "2px solid red");
		return false;
	}
	
	
	 $.ajax({
		url : "receberConta?idLista="+idLista+"&tipoBanco="+tipoBanco+"&ndnf="+ndnf,
		success : function(data) {
			$("#sucessoReceberConta").fadeIn(800);
			setTimeout(function () {
		        window.location.reload(1);}, 2000);
		},
		error: function(){
			$("#erroReceberConta").fadeIn(800);
			
		}
	});
};

function mudaDataContasReceber(nf,evento,idInfoInterna){

	var valor =  $("#valorAlterar"+idInfoInterna).val();
	
	$("#mudaDataVencimentoModel").fadeIn(500);
	$("#NDNFalterar").append(nf);
	$("#Eventoalterar").append(evento);
	$("#Valoralterar").append(valor);
	$("#idInfoInterna").attr('value',idInfoInterna);
};


function alterardata(){
	
	var data = $("#dataParaAlterar").val();
	var idInfoInterna = $("#idInfoInterna").val();
	
	if(data == null || data == ''){
		$("#dataParaAlterar").css("border","2px solid red");
		alert("Preencha com uma data válida");
		return false;
	}
	$.ajax({
		url : "alterarDataVencimento?idInfoInterna="+idInfoInterna+"&data="+data,
		success : function(data) {
			location.reload();
		}
	});
 };

</script>



