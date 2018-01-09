<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
	<link rel="shortcut icon" href="<c:url value="resources/images/iconS.ico" />" />
	<title>Planilha Cliente</title>
	<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/syslocc.css"  />" />
	<link rel="stylesheet" href="<c:url value="resources/css/jquery-ui-1.8.18.custom.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/novosyslocclayout.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/estilo_imprimir.css" />" media="print" />

</head>
<body>


<c:forEach items="${lista}" var="lista">
	<c:set value="${lista.idJob.localEvento}" var="localEvento" />
	<c:set value="${lista.idLista}" var="idLista" />
	<c:set value="${lista.administracao}" var="administracao" />
	<c:set value="${lista.margemPadrao}" var="margemPadrao" />
	<c:set value="${lista.formaPagamento}" var="formaPagamento" />
	<c:set value="${lista.lista}" var="titulo" />
	<c:set value="${lista.idJob.empresa.empresa}" var="empresa" />
	<c:set value="${lista.idJob.empresa.logotipo}" var="logotipo" />
	<c:set value="${lista.idJob.titulo}" var="tituloJob"/>
	<c:set value="${lista.listaCod}" var="codLista"/>
	<c:set value="${lista.revisao}" var="revisao"/>
	<c:set value="${lista.valorTotal}" var="valorTotal"/>
	<c:set value="${lista.impostoValor}" var="impostoValor"/>
	<c:set value="${lista.subTotalCusto}" var="subTotalCusto"/>
	<c:set value="${lista.administracaoValor}" var="administracaoValor"/>
	<c:set value="${lista.idlistaEstatus.idlistaEstatus}" var="idlistaEstatus"/>
	
</c:forEach>

<div class="row tira-conteudo">
	<div class="col-md-6">
		<a href="editaLista?idLista=${idLista}"  class="btn btn-default tira-conteudo"><i class="glyphicon glyphicon-menu-left" style="top: 2px;margin-right: 7px;"></i>Voltar</a>
		<button type="button"  onclick="printpage();" class="btn btn-default tira-conteudo"><i class="glyphicon glyphicon-print" style="top: 2px;margin-right: 7px;"></i> Imprimir Planilha do Cliente</button>
	</div>
</div>
<!-- ---------------------------------------------------------------- -->

 <div class="row wrap">
  <div class="col-md-12 painel ajuste-left shadow-com-padding">
	<table class="table tableAjuste" style="font-size: 12px;margin: 0">
  	 <tr> 
		<td rowspan="1" style="line-height: 8px;vertical-align: middle;width:220px; height:85px;border-top: 0">
		<!-- <img src="upload/upload/logoEmpresas/locco2.png" width="190" height="85" /></td> -->	
		<img src="<c:url value="resources/images/locco2.png" />" width="140" height="55" /></td>	
		
		<td style="line-height: 8px;vertical-align: middle;border-top: 0" class="ajusteDadosEvento">
		    <div class="divisor-fino"></div>
		    <p>Evento: <strong>${titulo}</strong></p>
	 		<p>Cod: ${codLista} .${revisao}</p>
	 		<div class="divisor-fino"></div>
	 		<p>Data:
	 		
		<c:forEach items="${localEvento}" var="localEvento">
			 <fmt:formatDate value="${localEvento.localEventoDataInicio}" pattern="dd/MM/yyyy HH:mm"/> - 	 		
			 <fmt:formatDate value="${localEvento.localEventoDataTermino}" pattern="dd/MM/yyyy HH:mm"/>	 		
	 	    </p>
 			<p>Local: ${localEvento.localEventoNome}</p>
 			<p>N° de pessoas: ${localEvento.localEventoQtdPessoas}</p>
			<div class="divisor-fino"></div>
 		</c:forEach>
	 	</td>			

		<c:if test="${empty logotipo}">
			<td style="border-top: 0;"></td>
		</c:if>
		<c:if test="${not empty logotipo}">
		  <td style="border-top: 0;">	
			<img class="imgLogo" src="upload/upload/logoEmpresas/${logotipo}" style="max-width: 170px;max-height: 60px;width: auto;height: auto;float: right;"/>
		  </td>
		</c:if>	
	 </tr>
	 
	  
    </table>

 	<div class="col-md-12" style="padding: 0">
  
    <div class="col-md-12" style="height:10px"></div>  
      
<table class="table table-striped table-td-ajuste ordem font-size-table" style="font-size: 14px;border:1px solid #ccc">
  
<tr>    
    <th style="width: 350px"></th>
    <th>Fat Locco</th>
    <th>Fat Direto</th>
    <th>Opcional</th>
    <th class="ajuste-th">Informações</th>
    <th style="width: 155px;">Não inclusos no custo</th>
</tr>
    

    <div class="ordem">
    
    	<c:set var="subtotal" value="0" />
    	<c:set var="totalFatLocco" value="0" />
    	<c:set var="totalFatDireto" value="0" />
      
		<c:forEach items="${categoria}" var="categoria">
		<tr>

<!-- ------------------------------ Insere nome da Categoria ---------------------------------  -->
		  
         <c:if test="${not empty categoria.grupo}"> <!--  Se 'categoria não' tirar linhas em branco Exibe categoria -->
	
			  <td colspan="6" style="background: #ccc" >
	            <strong>${categoria.categoria}</strong>
	          </td>
          
          

<!-- ------------------------------ ----------------------------------------------------------  -->

		</tr>
		
			<c:forEach items="${categoria.grupo}" var="grupo">	
				<tr class="meio-Linha">
				
				
		          <td>${grupo.grupo}</td>

		          <td>
		          	
		            <c:if test="${grupo.opcional ==  false}">
		          	
		          		<fmt:formatNumber value="${grupo.grupoValorIncideImposto}" pattern="#,##0.00"/>
		          	</c:if>
		          	
		          	<c:if test="${grupo.opcional ==  true}"></c:if>
		          </td>
		          
		          <td>
		          
		          	 <c:if test="${grupo.opcional == true}"></c:if>
		          	 
		          	 <c:if test="${grupo.grupoValorNaoIncideImposto > 1 && grupo.opcional == false || grupo.grupoValorNaoIncideImposto < 0 && grupo.opcional == false}">
		          	 	<fmt:formatNumber value="${grupo.grupoValorNaoIncideImposto}" pattern="#,##0.00"/>
		          	 </c:if>	
		          
		          </td>
		          
		          <td>
		          	<c:if test="${grupo.opcional == true }">
		          		<c:if test="${grupo.grupoValorIncideImposto == 0}">
		          			<fmt:formatNumber value="${grupo.grupoValorNaoIncideImposto}" pattern="#,##0.00"/>
		          		</c:if>
		          		
		          		<c:if test="${grupo.grupoValorIncideImposto > 0 || grupo.grupoValorIncideImposto < 0}">
		          			<fmt:formatNumber value="${grupo.grupoValorIncideImposto}" pattern="#,##0.00"/>
		          		</c:if>
		          		
		          	</c:if>
		            <c:if test="${grupo.opcional == false}"></c:if>	
		          </td>
		          
		          <td class="font-size-td">${grupo.informacoes}</td>
		          <td>${grupo.necessidades}</td>
		          
		   </tr>
			</c:forEach>	
<!-- -----------------------------------------------------------------------------------------------------------------------------------------  -->		          
 
 		<tr style="background-color: #F7F3F7">
		  
		  	
		  	
			<c:set var="total" value="0" />  

			<c:forEach items="${categoria.grupo}" var="categorias">
				<c:if test="${categorias.opcional == true}"></c:if>
				<c:if test="${categorias.opcional == false}">
					<c:set var="total" value="${total + categorias.grupoValorIncideImposto}" />
				</c:if>
 		    </c:forEach>
			
			
				<c:set var="locco" value="0" />  
			<c:forEach items="${categoria.grupo}" var="fatDireto">
				<c:if test="${fatDireto.opcional  == true}">
				</c:if>
				<c:if test="${fatDireto.opcional  == false}">
					<c:set var="locco" value="${locco + fatDireto.grupoValorNaoIncideImposto}" />
				</c:if>
			</c:forEach>
				
				
							
			<c:set var="totalsub"  value="${total + locco}" />
			<c:set var="totalFatLocco" value="${totalFatLocco + total}" />
    		<c:set var="totalFatDireto" value="${totalFatDireto + locco}" />
			
			<c:set var="subtotal"  value="${subtotal + total + locco}" />
			
		
		  <td class="td-categoria">	
			<span><strong>${categoria.categoria}:  <fmt:formatNumber value="${totalsub}" pattern="#,##0.00" /></strong></span> 
          </td>
          
	      <td>	 
	    	 <strong><fmt:formatNumber value="${total}" pattern="#,##0.00" /></strong>
	      </td>

				     <c:set var="locco" value="0" />  
				
	
			<c:forEach items="${categoria.grupo}" var="fatDireto">
						<c:if test="${fatDireto.opcional  == true}">
							<c:set var="teste" value="${1}" />
	
						</c:if>
						<c:if test="${fatDireto.opcional  == false}">
							<c:set var="teste" value="${0}" />
							<c:set var="locco" value="${locco + fatDireto.grupoValorNaoIncideImposto}" />
						</c:if>
			
				</c:forEach>
		 
		  <td>	 
		 	 <strong><fmt:formatNumber value="${locco}" pattern="#,##0.00" /></strong>
	      </td>

          <td></td>
          <td></td>
          <td></td>
         
		</tr>
		
</c:if>	
		
		</c:forEach>
		
		
		
<!--------------------------------------------------------------------------------------------------------- -->		
		
	    <tr>
			<td style="font-size: 14px;" class="total">
				<strong>Sub Total: <fmt:formatNumber value="${subtotal}" pattern="#,##0.00" /></strong>
			</td>
			
			<td style="font-size: 14px" class="total"><strong><fmt:formatNumber value="${totalFatLocco}" pattern="#,##0.00" /></strong></td>
			<td style="font-size: 14px" class="total"><strong><fmt:formatNumber value="${totalFatDireto}" pattern="#,##0.00" /></strong></td>
			<td></td>
			<td colspan="3"></td>
			
		</tr>
		
		<tr>
			<td style="font-size: 14px;" class="total"><strong>Fee:</strong></td>
			<td style="font-size: 14px" class="total"><strong><fmt:formatNumber value="${administracaoValor}" pattern="#,##0.00"/></strong></td>
			<td colspan="5"></td>
		</tr>
		
		<tr>
			<td style="font-size: 14px;" class="total"><strong>Impostos:</strong></td>
			<td style="font-size: 14px" class="total"><strong><fmt:formatNumber value="${impostoValor}" pattern="#,##0.00"/></strong></td>
			<td colspan="5"></td>
		</tr>
		
		<tr>
			<td style="font-size: 16px;background: #ccc" class="total"><strong>Total:</strong></td>
			<td style="font-size: 16px;background: #ccc" class="total"><strong><fmt:formatNumber value="${valorTotal}" pattern="#,##0.00"/></strong></td>
			<td colspan="5" style="font-size: 16px;background: #ccc"></td>
		</tr>
		
		<c:if test="${not empty formaPagamento}">
			<tr style="text-align: center;">
				<td colspan="7">Forma de pagamento: ${formaPagamento}</td>
			</tr>
		</c:if>
		<c:if test="${empty formaPagamento}">
		</c:if>
		
		
		<tr style="text-align:center;">
			<!-- <td colspan="7">Notas Fiscais de Assessoria de evento.<span style="color: red"> Procedimento de emissão de nota conforme normas cliente.</span></td> -->
			<td colspan="7">Notas Fiscais de Serviços de Assessoria de evento. Validade da proposta 15 dias.<span style="color: red"> Procedimento de emissão de nota conforme normas cliente.</span></td>
		</tr>
		

	</table>

<p><fmt:formatDate value="${data}" pattern="dd/MM/yyyy HH:mm"/> ${contrato}</p>
  

 </div>
</div>
</div>
<div class="row tira-conteudo" style="height: 100px;">

</div>

 
<!-- Fim Edição Planilha -->


<c:import url="../_comum/footer.jsp" />

