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
.fontInteracaoSize{font-size: 14px}
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Interações</li>
		  </ol>					
</div>

<div style="height: 350px;margin-left: 45px;">
	
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;border-bottom: 1px solid #ccc;padding-bottom: 10px;">
	<span style="font-family: 'OpenSansLight';font-size: 25px;margin-right: 46px;">INTERAÇÕES</span>
		<a href="<c:url value="/prospeccoes" />" class="btn btn-danger">Listar Prospecções</a>
	</div>
	<form action="filtraInteracao" method="post" id="filtraInteracoes">
		<div class="col-md-2 boxFiltro">	
			<input name="idEmpresaConfere" type="checkbox" class="inputCheckbox" />&nbsp&nbspCliente
			<select name="idEmpresa" class="form-control" id="listaUsuario" size="7" style="height: 142px;">
				<c:forEach items="${empresas}" var="empresas">
	            	<option value="${empresas[0]}">${empresas[1]}</option>
	            </c:forEach>
			</select>
		</div>
	
		<div class="col-md-2 boxFiltro">	
			<input name="tituloConfere" type="checkbox" class="inputCheckbox"/>&nbsp&nbspTítulo<br>
			<!-- <textarea name="" rows="6" cols="8" class="form-control"></textarea> -->
			<select name="titulo" class="form-control" id="preencheTitulo" size="7" style="height: 142px;font-size: 12px;padding: 5px;">
			</select>
		</div>

	<div class="col-md-2 boxFiltro">	
			<input name="dataConfere" type="checkbox" class="inputCheckbox"/>&nbsp&nbspData
			<select name="tipoData" class="form-control" id="listaUsuario">
				<option value="interacao">Interação</option>
				<option value="agendamento">Agendamento</option>
			</select>
			Inicio&nbsp&nbsp<input name="data" type="date" class="form-control" />
			Exibir qtos dias<input name="qtoDias" type="text" class="form-control">
			
		</div>
	
	
		<div class="col-md-2 boxFiltro">	
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
		</div>
		<p style="margin-top: -20px;font-size: 12px;">Gerando filtro, aguarde...</p>
	</div>

</div>

<div id="criacaoListas" class="col-md-12" style="font-family: 'OpenSansLight';background: #fff;border-top: 1px solid #ccc;margin-bottom: 100px;">
  <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;margin-top: 40px;">
	<table class="table table-striped table-hover table-condensed">
	  <tbody id="interacoesFiltro">
	   <tr class="" style="background: #f1f1f1 !important">
			<th>Empresa</th>
			<th>Título</th>
			<th>Interação</th>
			<th>Contato</th>
			<th>Data</th>
			<th>Próx Interação</th>
			<th>Nome</th>
		</tr>

		<c:forEach items="${interacoes}" var="interacoes">
			<tr>
				<td class="fontInteracaoSize">
					<c:forTokens items="${interacoes.idProspeccao.idEmpresa.empresa}" delims=" " var="word" varStatus="status">  
	                   <c:if test="${status.count < 4}">  
	                     <c:out value="${word}" escapeXml="false"/>  
	                   </c:if>  
	                </c:forTokens>
				</td>
			    
			    <td class="fontInteracaoSize">
			    <a href="edicaoProspeccao?idProspeccao=${interacoes.idProspeccao.idProspeccao}">
			    
			    <c:forTokens items="${interacoes.idProspeccao.titulo}" delims=" " var="word" varStatus="status">  
                   <c:if test="${status.count < 3}">  
                     <c:out value="${word}" escapeXml="false"/>  
                   </c:if>  
                </c:forTokens>
			    
			    
			    </a></td>
				
				<td class="fontInteracaoSize" style="width: 370px !important;">
				
				<c:forTokens items="${interacoes.interacao}" delims=" " var="word" varStatus="status">  
                   <c:if test="${status.count < 7}">  
                     <c:out value="${word}" escapeXml="false"/>  
                   </c:if>  
                </c:forTokens>
				</td>
				
				<td class="fontInteracaoSize">
				<c:if test="${interacoes.idContato.idContato == 1}"></c:if>
				
				<c:if test="${interacoes.idContato.idContato != 1}">
				   <c:forTokens items="${interacoes.idContato.contato}" delims=" " var="word" varStatus="status">  
	                   <c:if test="${status.count < 3}">  
	                     <c:out value="${word}" escapeXml="false"/>  
	                   </c:if>  
	                </c:forTokens>	
				</c:if>
				</td>
				
				
				<td class="fontInteracaoSize"><fmt:formatDate value="${interacoes.dataInteracao.time}" pattern="dd/MM/yyyy HH:mm"/></td>
				
				<td class="fontInteracaoSize">
				
					<c:if test="${interacoes.dataProximaInteracao  != '0001-01-01 00:00:00.0'}">
						<fmt:formatDate value="${interacoes.dataProximaInteracao}" pattern="dd/MM/yyyy HH:mm"/>
					</c:if>
					
					<c:if test="${interacoes.dataProximaInteracao  == '0001-01-01 00:00:00.0'}">
					</c:if>					
				</td>
				
				
				<td class="fontInteracaoSize">
				<c:forTokens items="${interacoes.idUsuario.nome}" delims=" " var="word" varStatus="status">  
                   <c:if test="${status.count < 2}">  
                     <c:out value="${word}" escapeXml="false"/>  
                   </c:if>  
                </c:forTokens>	
				
					
					
				</td>
			</tr>
		 </c:forEach>	
	   </tbody>
	</table>
  </div>
</div>

<c:import url="../../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/interacoes.js" />"></script>



<!-- 
<script>

var $w = $(window);

$w.on("scroll", function(){
   if( $w.scrollTop() > 400 ) {
	   $(".menuTabela").css({
		   "position":"fixed",
		   "top":"0"});
	   
       console.log('Maior que 300');
   }
});



</script> -->
