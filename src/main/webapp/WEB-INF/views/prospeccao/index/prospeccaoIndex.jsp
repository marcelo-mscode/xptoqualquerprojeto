<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />
<style type="text/css">
.prospeccao{background: #f1f1f1;}
.inputCheckbox{width: 15px;height: 15px;vertical-align: sub;margin-bottom: 10px !important;}
.boxFiltro{background: #fff;padding: 15px;box-shadow: 0px 1px 14px 3px #ccc;margin-right: 12px;height: 205px;}
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Prospecção</li>
		  </ol>					
</div>

<div style="height: 350px;margin-left: 45px;">
	
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;border-bottom: 1px solid #ccc;padding-bottom: 10px;">
	<span style="font-family: 'OpenSansLight';font-size: 25px;margin-right: 46px;">PROSPECÇÕES</span>
		<a href="<c:url value="/novaProspeccao" />" class="btn btn-danger">Nova Prospecção</a>
		<a href="<c:url value="/interacoes" />" class="btn btn-danger">Listar Interações</a>
	</div>
<form action="filtraProspeccao" id="filtraProspeccao" method="post">	
	<div class="col-md-2 boxFiltro">	
		<input name="idEmpresaConfere" type="checkbox" class="inputCheckbox" />&nbsp&nbspCliente
		<select name="idEmpresa" class="form-control" id="listaUsuario" size="7" style="height: 142px;">
				<c:forEach items="${empresas}" var="empresas">
	            	<option value="${empresas[0]}">${empresas[1]}</option>
	            </c:forEach>
			</select>
	</div>
	
	<div class="col-md-2 boxFiltro">	
		<input name="dataConfere" type="checkbox" class="inputCheckbox"/>&nbsp&nbspData
		<select name="tipoData" class="form-control">
			<option value="interacao">Interação</option>
			<option value="agendamento">Agendamento</option>
		</select>
		Inicio&nbsp&nbsp<input name="data" type="date" class="form-control"/>
		Exibir qtos dias<input name="qtoDias" type="text" class="form-control">
		
	</div>
	
	<div class="col-md-2 boxFiltro">	
		<input name="sInteracaoCon" type="checkbox" class="inputCheckbox"/>&nbsp&nbspSem interação<br>
		<input name="semInteracao" type="text" class="form-control"/>
		Por mais de (dias)
	</div>
	
	<div class="col-md-2 boxFiltro">	
		<input name="probabilidadeConfere" type="checkbox" class="inputCheckbox"/>&nbsp&nbspProbabilidade
		<select name="probabilidade" class="form-control" style="width: 100px;font-size: 22px;padding:  0 0 0 10px;margin-bottom: 10px">
			<option value=">"> > </option>
			<option value=">="> >= </option>
			<option value="="> = </option>
			<option value="<"> < </option>
			<option value="<="> <= </option>
		</select>

		<select name="porcentagem" class="form-control" style="width: 100px;margin-bottom: 25px">
			<option value="100">100%</option>
			<option value="90">90%</option>
			<option value="75">75%</option>
			<option value="50">50%</option>
			<option value="25">25%</option>
			<option value="0" selected="selected">0%</option>
		</select>
		<input name="concluidos" class="inputCheckbox" type="checkbox" />&nbsp&nbspConcluídos
	</div>

	<div class="col-md-2">
		<button type="submit" class="btn btn-danger" >Filtrar</button>
	</div>
</form>
	<div class="col-md-2" id='loader-confirmacao' style="display: none;margin-top: 15px">
			<div class="progress">
			<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0"
				aria-valuemax="120" style="width: 100%">
				<span class="sr-only">100% Complete</span>
			</div>  
			<!-- <img src='resources/images/ajax-loader-fff.gif' width='20'height='20' alt='loading' id='loader-confirmacao'
			style='margin: 0px 10px 5px 5px;display: none'> -->
		</div>
		<p style="margin-top: -20px;font-size: 12px;">Gerando filtro, aguarde...</p>
	</div>

</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;">
	<table class="table table-striped table-hover table-condensed" style="">
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important">
			<th>Título</th>
			<th>Empresa</th>
			<th>Última Interação</th>
			<th>Data</th>
			<th>Agendamento</th>
		</tr>
	
		<c:forEach items="${prospeccoes}" var="prospeccoes">
			<tr>
			    <td><a href="edicaoProspeccao?idProspeccao=${prospeccoes.idProspeccao}">${prospeccoes.titulo}</a></td>
				<td>
					<c:forTokens items="${prospeccoes.idEmpresa.empresa}" delims=" " var="word" varStatus="status">  
	                   <c:if test="${status.count < 3}">  
	                     <c:out value="${word}" escapeXml="false"/>  
	                   </c:if>  
	                </c:forTokens>
				</td>
			
				<c:forEach items="${prospeccoes.interacao}" var="interacao" varStatus="loop">
				  <c:if test="${loop.last}">	
					<td>
						<c:forTokens items="${interacao.interacao}" delims=" " var="word" varStatus="status">  
		                   <c:if test="${status.count < 6}">  
		                     <c:out value="${word}" escapeXml="false"/>  
		                   </c:if>
	                    </c:forTokens>	
					</td>
					<td><fmt:formatDate value="${interacao.dataInteracao.time}" pattern="dd/MM/yyyy hh:mm"/></td>
					<td>
						<c:if test="${interacao.dataProximaInteracao  != '0001-01-01 00:00:00.0'}">
							<fmt:formatDate value="${interacao.dataProximaInteracao}" pattern="dd/MM/yyyy HH:mm"/>
						</c:if>
						<c:if test="${interacao.dataProximaInteracao  == '0001-01-01 00:00:00.0'}"></c:if>
					</td>
				  </c:if>
				</c:forEach>
				
				
			</tr>
		</c:forEach>	
	  </tbody>
	</table>
  </div>		
</div>

<c:import url="../../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/prospecccao.js" />"></script>