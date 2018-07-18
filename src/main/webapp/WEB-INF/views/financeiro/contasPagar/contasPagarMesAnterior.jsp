<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<style>
	.aligVertical{vertical-align: middle !important;}
</style>
	
	<table class="table table-striped table-hover table-bordered" >
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important;text-align: center !important;">
			<td colspan="6" style="text-align: center !important;color:red"><h4>Fornecedores - Relatórios Anteriores</h4></td>
		</tr>
		<tr style="background: #f1f1f1 !important;" class="cabecalhoLista">
			<td align="left" class="pLeft input-160px">NF/ND</td>
			<td align="left" class="input-110px">Vencimento</td>
			<td align="left">Descrição</td>
			<td align="left">Banco</td>
			<td align="left">Valor</td>
			<td align="left">Pagamento</td>
		</tr>
	
		 <c:set var="valorTotal" value="0.00" />			
	
		 <c:forEach items="${idListasAnteriores}" var="idListas">
	
		 	<tr>
		 		<td colspan="5" align="left" class="pLeft"><h4><b>${idListas[1]}</b></h4></td>
		 	</tr>
		 
			<c:forEach items="${listaAnteriores}" var="novaLista" varStatus="loop">
				<c:if test="${idListas[0] == novaLista[3]}">
					<tr id="valor${loop.count}">
						<td <c:if test="${novaLista[7] == true}">class="temContratacao"</c:if> class="pLeft" style="vertical-align: middle !important;">${novaLista[2]}</td>
						<td class="aligVertical"><fmt:formatDate value="${novaLista[0]}"   pattern="dd/MM/yyyy" /></td>
						<td class="aligVertical">${novaLista[1]}</td>
						<td style="width: 125px !important;padding: 0">
						<select class="form-control" name="tipoBanco" id="tipoBanco${loop.count}" style="border: none;height: 49px">
							<option value="0">Banco</option>
							<option value="1">Itau</option>
							<option value="2">CEF</option>
							<option value="3">Bradesco</option>
							<option value="4">Santander</option>
						</select>
						</td>
						
						<!-- Valor para pagar Fornecedor -->
						<td class="aligVertical"><fmt:formatNumber value="${novaLista[4]}" pattern="#,##0.00" /></td>
						
						
						<td>
							<a onclick="funcaoPagaConta(${idListas[0]},${novaLista[5]},${novaLista[6]},'valor${loop.count}','tipoBanco${loop.count}',${loop.count});" class="btn btn-success">PAGAR</a>
							<i class="glyphicon glyphicon-ok" style="font-size:18px;  color: green;margin-left: 15px;display: none;" id="sucesso${loop.count}"></i>
						 </td>
						<c:set var="valorTotal" value="${valorTotal+novaLista[4]}" />			
					</tr>
				</c:if>
			</c:forEach>
		 </c:forEach>	
	
		</table>
		<h1 class="pLeft"><fmt:formatNumber value="${valorTotal}" pattern="#,##0.00" /></h1>
		
		<script type="text/javascript">
		function funcaoPagaConta(idLista,idFornecedor,qtdDias,idLinha,idbanco,contador) {
			
			var banco = $("#"+idbanco).val();
			
		if(banco == 0){
				$("#"+idbanco+contador).css("border","1px solid red");
				alert("Selecione um banco !");
				return false;
			}
			
			$.ajax({
				url : "pagaConta?idLista="+idLista+"&idFornecedor="+idFornecedor+"&qtdDias="+qtdDias+"&idbanco="+banco,
				success : function(data) {
					$("#ConfirmaPagamento").fadeOut(500);
					$("#sucesso"+contador).fadeIn(500);
					location.reload();
				},
				beforeSend : function() {
					$("#ConfirmaPagamento").fadeIn(500);
				},
				complete : function() {
					$("#ConfirmaPagamento").fadeOut(500);
				},
				error : function() {
					alert("Erro aqui");
				}
			});
			
		};
		
		</script>
		
		
		
		
		
		
		
		
		
		
		
