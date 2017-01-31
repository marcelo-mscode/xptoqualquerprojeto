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
		    <li class="active"><b>Tarefas</b></li>
		</ol>
	</div>
</div>
<!--
 <div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 26px;background: #fff">
	<h4 style="font-family: 'OpenSansLight'">LISTA DE TAREFAS</h4>
</div>
 -->

<div class="container">
  <div class="row" style="background-color: #fff;padding: 0px 0 20px 0;box-shadow: -1px 2px 9px 0px #AEAEAE;margin-top: 45px;">	
	<div class="col-md-12"> 
	<h3 style="font-family: 'OpenSansLight';text-transform: uppercase;">tarefas</h3>
		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
			<tr>
				<th>Tarefa</th>
				<th>Lista</th>
				<th>Descrição Tarefa</th>
				<th>Responsável</th>
				<th>Status</th>
			</tr>
		<c:forEach items="${tarefas}" var="tarefas">
		<tr >
			<td style="font-size: 12px!important"><a href="exibeDetalhesDoItem?idCriacaoItem=${tarefas.idCriacaoItemLista}">${tarefas.tituloItem}</a></td>
			<td style="font-size: 12px!important">${tarefas.criacaoLista.listaTitulo}</td>
			<td style="font-size: 12px!important">
			<c:if test="${empty tarefas.informaoesItem}"></c:if>				
				<c:if test="${not empty tarefas.informaoesItem}">				
				
						<c:forTokens items="${tarefas.informaoesItem}" delims=" " var="word" varStatus="status">  
                           <c:if test="${status.count < 8}">  
                               <c:out value="${word}" escapeXml="false"/>  
                           </c:if>  
                        </c:forTokens>
						<span style="font-size: 10px;">...</span>
						
				</c:if>
			
			</td>
			
	 		<td style="font-size: 12px!important">${tarefas.responsavelItem.nome}</td>
			<td style="font-size: 12px!important">${tarefas.criacaoItemStatus.status}</td>
		</tr>
		</c:forEach>
		</table>	
	  </div>
	</div>
	
	<div class="row" style="background-color: #fff;padding: 0px 0 20px 0;box-shadow: -1px 2px 9px 0px #AEAEAE;margin-top: 25px">	
	<div class="col-md-12"> 
	<h3 style="font-family: 'OpenSansLight';text-transform: uppercase;">tarefas Finalizadas</h3>
		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
			<tr>
				<th>Tarefa</th>
				<th>Lista</th>
				<th>Descrição Tarefa</th>
				<th>Responsável</th>
				<th>Status</th>
				<th>Data Finalização</th>
			</tr>
		<c:forEach items="${finalizados}" var="finalizados">
			<tr>
				<td style="font-size: 12px!important"><a href="exibeDetalhesDoItem?idCriacaoItem=${finalizados.idCriacaoItemLista}">${finalizados.tituloItem}</a></td>
				<td style="font-size: 12px!important">${finalizados.criacaoLista.listaTitulo}</td>
				<td style="font-size: 12px!important">${finalizados.informaoesItem}</td>
		 		<td style="font-size: 12px!important">${finalizados.responsavelItem.nome}</td>
				<td style="font-size: 12px!important" class="verde">${finalizados.criacaoItemStatus.status}</td>
				<td style="font-size: 12px!important"><fmt:formatDate value="${finalizados.termino.time}" pattern="dd/MM/yyyy HH:mm"/></td>
			</tr>
		</c:forEach>
		</table>	
	  </div>
	</div>
	
	
	
</div>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>
	