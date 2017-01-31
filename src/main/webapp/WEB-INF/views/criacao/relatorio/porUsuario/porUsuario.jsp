<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../../_comum/header.jsp" />
<style type="text/css">
	.criacao{background: #f1f1f1;}
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Relatório Listas</li>
		  </ol>					
</div>


<div style="height: 120px;margin-left: 45px;">
	<h3 style="font-family: 'OpenSansLight';padding: 10px;margin: 40px 0px 10px 3px;">RELATÓRIO POR USUÁRIO</h3>
	<div class="col-md-3">
		<select class="form-control" id="listaUsuario">
			<c:forEach items="${listaUsuarios}" var="lsitaUsuarios">
				<option value="${lsitaUsuarios[0]}">${lsitaUsuarios[1]}</option>		
			  </c:forEach>
		</select>
	</div>
	<div class="col-md-2">
		<select class="form-control" id="listaMes">
			<c:forEach items="${mesCriacao}" var="mes">
				<option value="${mes[0]}">${mes[1]}</option>		
			</c:forEach>
		</select>
	</div>
	<div class="col-md-3">
		<select class="form-control" id="listaData">
			<c:forEach items="${dataCricao}" var="data">
				<option value="${data}">${data}</option>		
			</c:forEach>
		</select>
	</div>

	<div class="col-md-1">
		<button onclick="filtraListaUsuario();" class="btn btn-danger" >Filtrar</button>
	</div>

	<div class="col-md-2" id='loader-confirmacao' style="display: none">
			<div class="progress">
			<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0"
				aria-valuemax="120" style="width: 100%">
				<span class="sr-only">100% Complete</span>
			</div>  
			<!-- <img src='resources/images/ajax-loader-fff.gif' width='20'height='20' alt='loading' id='loader-confirmacao'
			style='margin: 0px 10px 5px 5px;display: none'> -->
		</div><p style="margin-top: -20px;font-size: 12px;">Gerando Relatório, aguarde...</p>
	</div>

</div>

	

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 218px;height: 600px;border-top: 1px solid #ccc;padding: 35px 60px;">

	<div class="col-md-12" style="height:800px;margin-bottom:140px;background: #fff;box-shadow:5px 5px 5px #ccc;padding: 20px 40px 70px 40px;border-radius:5px;border: 1px solid #ccc">
		
	</div>
		
</div>

<c:import url="../../../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>