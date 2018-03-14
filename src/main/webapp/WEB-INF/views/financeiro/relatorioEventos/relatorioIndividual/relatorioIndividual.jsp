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
    <li class="active">Relatório ${infoLista.lista}</li>
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

<c:import url="../../../_comum/footer.jsp" />

<script type="text/javascript">

function mudaCache(idRelatorio, idCachePadrao, idLista){
	
	var novoValorCache = $("#mudaCacheFunc"+idCachePadrao).val();
	
	$.ajax({
		url : "atualizaCacheRelatorioEvento?idRelatorio="+idRelatorio+"&idCachePadrao="+idCachePadrao+"&novoValorCache="+novoValorCache+"&idLista="+idLista,
		success : function(data) {
			$("#"+idDivAjax).html(data);
			
			
		}
	});
	
}	

</script>



