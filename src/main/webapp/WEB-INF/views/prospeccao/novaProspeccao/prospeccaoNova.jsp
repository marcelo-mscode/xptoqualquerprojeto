<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />
<style type="text/css">
.prospeccao{background: #f1f1f1;}
.inputCheckbox{width: 15px;height: 15px;vertical-align: sub;margin-bottom: 10px !important;}
.boxFiltro{background: #fff;padding: 15px;box-shadow: 5px 5px 5px #ccc;margin-right: 12px;height: 205px;}
.escondeSolicitante{display: none}
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li><a href="<c:url value="/prospeccoes" />">Prospecção</a></li>
		    <li class="active">Nova Prospecção</li>
		  </ol>					
</div>

<div style="height: 65px;margin-left: 45px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;padding-bottom: 10px;">
	<span style="font-family: 'OpenSansLight';font-size: 25px;margin-right: 46px;">NOVA PROSPECÇÃO</span>
	</div>
</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 218px;height: 600px;border-top: 1px solid #ccc;padding: 35px 60px;">


<div class="col-md-12" style="box-shadow: 5px 5px 5px #ccc;border: 1px solid #e6e6e6;padding: 15px 15px 30px 15px;">
<div>
<form action="salvaProspeccao" method="post">
   
   <input name="concluido" value="0" type="hidden"/>
	
	<div class="row">
		<div class="col-md-3">	
			<span>Título</span>
			<input name="titulo" type="text" class="form-control input-480px"/>
		</div>	
	</div>	
	
	<div class="row">
		<div class="col-md-5">	
			<span>Cliente</span><br>
			<select name="idEmpresaTrans" id="sempresaFornecedor" class="form-control input-480px" onchange="procuraContatoProspeccao(this.value);">
				<option value="selecione">Selecione</option>
		         <c:forEach items="${empresas}" var="empresas">
		            <option value="${empresas[0]}">${empresas[1]}</option>
		         </c:forEach>
			</select>
		</div>	
		<div class="col-md-3" style="padding: 25px 0 0 50px;">
			<a onclick="insereNovoFornecedor();" class="btn btn-link">Inserir</a> | 
			<a onclick="editaFornecedor();" class="btn btn-link">Editar</a>
		</div>
	</div>
		
	<div class="row">
		<div class="form-group col-md-8">
		<label for="">Contatos</label><br>
			<img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader display-none" alt="loading" />
	        <div class="divisor"></div>
			<div class="info-contato" style="height: 10px;" ></div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-5">	
			<span>Probabilidade</span>
			<select name="probabilidade" class="form-control input-120px">
				<option value="100">100%</option>
				<option value="90">90%</option>
				<option value="75">75%</option>
				<option value="50">50%</option>
				<option value="25">25%</option>
				<option value="0">0%</option>
			</select>
		</div>	
	</div>	
	
	<div class="row">
	<br>
		<div class="col-md-5">	
			<button type="submit" class="btn btn-danger">Salvar Prospeccção</button>
		</div>
	</div>
	
</form>	
</div>		
	
	
	
	
</div>



</div>

<div class="col-md-12 alpha60 div-confirmacao" id="editaClienteProspect" style="left: 0;position: fixed">
	<%-- <c:import url="../editaClienteProspect/editaClienteProspect.jsp" /> --%>
</div>
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="novoFornecedor" style="left: 0;position: fixed">
	<c:import url="../novoClienteProspect/novoFornecedor.jsp" />
	</div>
</div>


<c:import url="../../_comum/footer.jsp" />	
<script type="text/javascript" src="<c:url value="resources/js/prospeccaoEditaCliente.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/prospeccaoEditaEmpresa.js" />"></script>
<script>
function emailSucesso(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-sucesso-email").slideToggle("slow"); 
	    }, 1000);
	});
}
function emailErro(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-erro-email").slideToggle("slow"); 
	    }, 10000);
	});
}
</script>


