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
.colorBlue{color:blue; }
.colorRed{color:red; }
.fontBold{font-weight: bold }
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
  <ol class="breadcrumb">
    <li><a href="index.html">Menu</a></li>
    <li><a href="relatorioEventosIndex">Relatório de Eventos</a></li>
    <li class="">Relatório ${infoLista.lista}</li>
  </ol>					
</div>

<!-- TOPO DA PÁGINA COM INFORMAÇÕES DE DATA E NUMERO DO EVENTO E LINK PARA INTERNA -->	
<div style="height: 35px;">
		<c:import url="numeroDataEventoLinkInterna.jsp" />
</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 5px 70px 5px;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">

<!-- TABELA COM INFORMAÇÕES DO EVENTO -->	
 	<div class="col-md-9" style="padding-left: 0;">
		<c:import url="cabecalhoInfoEvento.jsp" />
	</div>
	
<!-- TABELA INFORMAÇÕES DO VALOR LOCCO AGÊNCIA E DO IMPOSTO -->	
	<div class="col-md-3" style="padding-left: 0;">
		<c:import url="infoValorTotalImposto.jsp" />
	</div>
	
<!-- TABELA CACHES DE FUNCIONÁRIOS COM INFORMAÇÕES DE PAGAMENTO -->	
	<div class="col-md-3" style="padding-left: 0;">
		<c:import url="relatoriosCaches.jsp" />
	</div>
	
<!-- TABELA BVS FORNECEDORES COM INFORMAÇÕES DE PAGAMENTO -->	
    <div class="col-md-6" style="padding-left: 0;">	
		<c:import url="bvsFornecedores.jsp" />
	</div>
		
<!-- TABELA RESUMO DOS VALORES DE PAGAMENTO -->	
	<div class="col-md-3" style="padding-left: 0;">
		<c:import url="resumoValoresPagamentos.jsp" />
	</div>	
  
  </div>		
</div>

<style>
 .navegacaoResumoMes{padding-top: 8px;font-size: 12px;position: fixed;bottom: 0px;background-color: #fff;border: 2px solid #ccc;height: 68px;}
 .navegacaoLink{color: green;font-weight: bold;text-transform: uppercase;}	
 .navegacaoLink{color: green;font-weight: bold;text-transform: uppercase;}	
 .tiraBordaTabela tr td {border-top: none !important; border-left: 1px solid #ddd;background-color: #f5f5f5}
 .activeMenu{background-color: #ddd !important}
 .active{background-color: #007bff !important}
  #toTop{display: none !important;}	
</style>


<div class="col-md-12 navegacaoResumoMes">
	<table class="table tiraBordaTabela">
		<tr>
			<td class="input-95px"><a href="resumoMesIndividual?mes=${mes}&ano=${ano}" class="navegacaoLink">Resumo mês</a></td>
			<td class="input-80px activeMenu"><a href="analiticoIndividual?idAnalitico=${idAnalitico}" class="navegacaoLink">Analítico</a></td>
			<td class="input-95px"><a href="contasPagar" class="navegacaoLink">Contas pagar</a></td>
			<td class="input-95px"><a href="contasReceber" class="navegacaoLink">Contas receber</a></td>
			
			<c:forEach var="i" begin="1" end="${quantRelatorioEventos}">
			
				<c:forEach items="${idsRelatorioEventos}" var="idsRelatorioEventos" varStatus="contador">
				 	<c:if test="${i == contador.count}">
		 				<td style="width: 20px !important;padding: 0px;vertical-align: middle;text-align: center;font-size: 15px;"><a href="relatorioEventoIndividual?idLista=${idsRelatorioEventos}" class="navegacaoLink" style="padding:7px">${i}</a></td>
				 	</c:if>
				</c:forEach>
			
			</c:forEach>
			
		</tr>
	</table>
</div>



<div class="col-md-12 alpha60 div-confirmacao" id="ConfirmaPagamento" style="position: fixed; display: none;background-color: rgba(255, 255, 255, 0.8);">
	<div class="col-md-4"></div>
	<div class="col-md-4 sub-div-confirmacao" style="height: 85px !important;box-shadow: 0px 2px 18px 10px #ccc">
		Recalculando valores do Relatório ... <br>
		<div class="progress col-md-12" style="margin-top: 20px;">
			<div class="progress-bar progress-bar-striped progress-bar-animated active"
				role="progressbar" aria-valuenow="45" aria-valuemin="0"
				aria-valuemax="100" style="width: 100%">
				<span class="sr-only">100% Complete</span>
			</div>
		</div>
	</div>
	<div class="col-md-4"></div>
</div>





<c:import url="../../../_comum/footer.jsp" />

<script type="text/javascript">
function mudaCache(idRelatorio, idCachePadrao, idLista){
	
	var novoValorCache = $("#mudaCacheFunc"+idCachePadrao).val();
	
	$.ajax({
		url : "atualizaCacheRelatorioEvento?idRelatorio="+idRelatorio+"&idCachePadrao="+idCachePadrao+"&novoValorCache="+novoValorCache+"&idLista="+idLista,
		success : function(data) {
			$("#ConfirmaPagamento").fadeOut(500);
		},
		beforeSend : function() {
			$("#ConfirmaPagamento").fadeIn(500);
		},
		complete : function() {
			location.reload();
		}
	});  
};	

function salvaNovoCache(idUsuarioNovoCache,idRelatorioEvento,novaPorcentagemCache){

 	 var idNovoUsuario = $(idUsuarioNovoCache).val();
 	 var novaPorcentagemCaches = $("#novaPorcentagemCache").val().replace(",",".");;
	 var idLista = $("#idLista").val();	

	 
 	$.ajax({
		url : "salvaNovoCache?idRelatorioEvento="+idRelatorioEvento+"&idNovoUsuario="+idNovoUsuario+"&novaPorcentagemCaches="+novaPorcentagemCaches+"&idLista="+idLista,
		success : function(data) {
			$("#ConfirmaPagamento").fadeOut(500);
			location.reload();
		},
		beforeSend : function() {
			$("#ConfirmaPagamento").fadeIn(500);
		},
		complete : function() {
			/*$("#ConfirmaPagamento").fadeOut(500);*/
		}
	});  
 	
};	


</script>
