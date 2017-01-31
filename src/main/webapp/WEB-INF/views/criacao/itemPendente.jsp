<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li><a href="exibePendencias">Lista de Pendências</a></li>
		    <li class="active"><b>Pendência</b></li>
		</ol>
	</div>
</div>
<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 26px;background: #fff">
	<h4 style="font-family: 'OpenSansLight'">ITEM PENDENTE</h4>
</div>

<div class="container">
  <div class="row" style="background-color: #fff;padding: 17px 0 0 10px;    box-shadow: -1px 2px 9px 0px #AEAEAE;">	
	<div class="col-md-12"> 

		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
			<tr>
				<th>Item Pendente</th>
				<th>Lista</th>
				<th>Data Entrada Pendência</th>
				<th>Data Limite</th>
				<th>Enviado Por</th>
				<th>Status</th>
			</tr>
		
			<tr>
				<td>${detPendencia.criacaoItemLista.tituloItem}</a></td>
				<td>${detPendencia.criacaoItemLista.criacaoLista.listaTitulo}</td>
				<td><fmt:formatDate value="${detPendencia.dataEnvioPendencia.time}" pattern="dd/MM/yyyy - HH:mm"/></td>
				<td class="azul"><fmt:formatDate value="${detPendencia.dataLimite}" pattern="dd/MM/yyyy - HH:mm"/></td>
				<td>${detPendencia.enviadoPor.nome}</td>
				<td>${detPendencia.criacaoItemStatus.status}</td>
			</tr>
		</table>	

	</div>
</div>


  <div class="row" style="background-color: #fff;padding: 0px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
	<div class="col-md-10">
	   <div class="col-md-4">
		<br><br>
		Descrição da Pendência<br>
		<textarea rows="9" cols="35" class="form-control">${detPendencia.descPendencia}</textarea>
		
	  </div>
		  <div class="col-md-1" style="width: 120px;">
		  	<br><br><br>
		  	
		  	<c:if test="${historicoPendencia.dataInicio == null}">
			  	<button class="btn btn-info input-80px block" 
			  	onclick="iniciaTempoDependencia(${historicoPendencia.idItemHistoricoPendencia});">
			  	Inicio</button>
		  	<p style="margin: 0px 0 0 35px;"> -- </p>
			<span style="font-size: 25px;font-weight: bolder;display: block;margin-left: 20px;">--:--</span>
		  	</c:if>
		  	
		  	<c:if test="${historicoPendencia.dataInicio != null}">
			  	<button class="btn btn-danger input-80px is-disabled" 
			  	onclick="iniciaTempoDependencia(${historicoPendencia.idItemHistoricoPendencia});">
		  	Inicio</button>
		  	<div class="divisor-fino"></div>
		  	<p style="margin: 0px 0 0 3px;"><fmt:formatDate value="${historicoPendencia.dataInicio.time}" pattern="dd/MM/yyyy"/></p>
			<span style="font-size: 25px;font-weight: bolder;display: block;margin-left: 8px;"><fmt:formatDate value="${historicoPendencia.dataInicio.time}" pattern="HH:mm"/></span>
		  	</c:if>
	
		  </div>

<!-- ------------------ -->	  

	  <div class="col-md-1" style="width: 120px;">
	  	<br><br><br>
		<c:if test="${historicoPendencia.dataInicio != null && historicoPendencia.dataTermino == null}">
			  	<button class="btn btn-info input-80px" 
			  	onclick="terminaTempoDependencia(${historicoPendencia.idItemHistoricoPendencia});">
			  	Término</button>
			  	<p style="margin: 0px 0 0 35px;"> -- </p>
			    <span style="font-size: 25px;font-weight: bolder;display: block;margin-left: 20px;">--:--</span>
		  	</c:if>

		  	<c:if test="${historicoPendencia.dataInicio == null || historicoPendencia.dataTermino != null}">
			  	<button class="btn btn-danger input-80px is-disabled" 
			  	onclick="terminaTempoDependencia(${historicoPendencia.idItemHistoricoPendencia});">
		  	Término</button>
		  	
			<div class="divisor-fino"></div>
		     <p style="margin: 0px 0 0 3px;"><fmt:formatDate value="${historicoPendencia.dataTermino.time}" pattern="dd/MM/yyyy"/></p>
			<span style="font-size: 25px;font-weight: bolder;display: block;margin-left: 8px;"><fmt:formatDate value="${historicoPendencia.dataTermino.time}" pattern="HH:mm"/></span>
	  		</c:if>
	  </div>


	  <div class="col-md-4">
	  	<br><br><br>

<c:if test="${detPendencia.criacaoItemStatus.idCriacaoStatus != 2}">
	<form action="fechaPendencia" method="post" id="fechaPendencia">
</c:if>

<c:if test="${detPendencia.criacaoItemStatus.idCriacaoStatus == 2}">
	<form id="fechaPendencia" class="is-disabled">
</c:if>


<input type="hidden" value="${detPendencia.idCriacaoItemHistoricoPendencia}" name="IdCriacaoItemHistoricoPendenciaTransiente" />	
<input type="hidden" value="${detPendencia.criacaoItemLista.idCriacaoItemLista}" name="criacaoItemListaTransiente" />	
<input type="hidden" value="${historicoPendencia.idItemHistoricoPendencia}" name="idItemHistoricoPendenciaTransiente" />	

		<select class="form-control input-180px" id="statusPendencia" name="criacaoItemStatusTransiente" >
			<option value="">Selecione o Status</option>
			<option value="2">Finalizar</option>
			<option value="3">Interromper</option>
		</select>
		<div class="divisor-fino"></div>
		<textarea rows="5" cols="" placeholder="Observações*" class="form-control" id="obsPendencia" name="obsTransiente"></textarea>
		<div class="divisor-fino"></div>

			<button class="btn btn-danger input-80px">Salvar</button>
		
		<div></div>
</form>	
	  </div>

	 </div>
   </div>	

 <div class="row" style="background-color: #fff;padding: 0px 0 20px 0;box-shadow: -1px 2px 9px 0px #AEAEAE;">
	
	  <div class="col-md-12">
		<div class="col-md-8">
	 	<h4>Item : ${detPendencia.criacaoItemLista.tituloItem}</h4>	
	  		Descrição
	  		<textarea rows="5" cols="" class="form-control">${detPendencia.criacaoItemLista.informaoesItem}</textarea>
	  	</div>
	  	<div class="col-md-4">
	  	
			<c:forEach items="${detPendencia.criacaoItemLista.grupo.produtoGrupo}" var="produtoGrupo">
						<c:set value="${produtoGrupo.quantidade}" var="qtd" />
						<c:set value="${produtoGrupo.quantidade2}" var="qtd1" />
			</c:forEach>
			Quantidade: ${qtd*qtd1}
	  	</div>
	  </div>	   
</div>

<div class="row" style="background-color: #fff;padding: 10px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
	<div class="col-md-8">
		Histórico Item
	<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
	  <tr>	
		
		<!-- <th>IdHistorico</th> -->
		<th>Dt Inicio Execução</th>
		<th>Dt Término Execução</th>
		<th>Observação</th>
		<th>Responsável</th>
		<th>Versão</th>
		<th>Status</th>
		<th>Tempo Execução</th>
	  </tr>
	
	<c:forEach items="${historico}" var="historico"> 
	 	
		
		  <tr>
		  	<%-- <td>${historico.idItemHistoricoPendencia}</td> --%>
		  	<td><fmt:formatDate value="${historico.dataInicio.time}" pattern="dd/MM/yyyy HH:mm" /></td>
		  	<td><fmt:formatDate value="${historico.dataTermino.time}" pattern="dd/MM/yyyy HH:mm" /></td>
		  	<td>${historico.obs}</td>
			<td>${historico.responsavelItem.nome}</td>
			<td>${historico.versao}</td>
			<td>${historico.criacaoItemStatus.status}</td>

			<td>
					
				 <c:forEach items="${contaTempo}" var="contaTempo">	
					
					<c:if test="${contaTempo[0] == historico.idItemHistoricoPendencia}">	
						${contaTempo[1][0]}Hs${contaTempo[1][1]}min
					</c:if>
					
					<c:if test="${contaTempo[0] != historico.idItemHistoricoPendencia}">	
						
					</c:if>
					
				</c:forEach>

		   </td>
			
			
			
			
			
			
			
			
			
		  </tr>	
	 

	</c:forEach> 
</table>









<!-- 
	Histórico do Item
	  <table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
		<tr>
			<th>Descrição</th>
			<th>Responsável</th>
			<th>Data</th>
			<th>Observação</th>
		</tr>
		<tr>
			<td>Item enviado para Pendencia</td>
			<td>Santigo</td>
			<td>10/10/205 13:25</td>
			<td> ------ </td>
		</tr>
	   </table>
 -->	</div>
</div>






<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>


<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>
