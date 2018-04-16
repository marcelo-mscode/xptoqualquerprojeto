<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
<style>
    .producao{background: #f1f1f1;}
</style>
<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div style="height: 80px;background: #e74c3c" class="">
		<div class="row" style="margin-top: 0px;margin-left: 5px">
			<div class="col-md-12" style="margin-top: 10px">
				<span style="font-size: 25px;color: #fff;font-family:'OpenSansLight' ">PRODUÇÃO</span>
			</div>
		</div>
		
		<div class="col-md-12" style="color:#fff !important">
			<ol class="breadcrumb">
			    <li><a href="menuProducao" style="color:#ecf0f1 !important">Menu</a></li>
			    <li class="active" style="color:#bdc3c7">Listas de Produção Aprovadas</li>
			</ol>
		</div>
   </div>



<div class="container"> 


<c:forEach var="datas" items="${datas}">

<p></p>
<fmt:formatDate value="${datas}" pattern="dd/MM/yyyy HH:mm:ss"/>
</c:forEach>


<div>
	<div class="row">
			<div class="col-md-12 painel ajuste-left">
				<div class="col-md-12">
	
						<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed"
							style="font-size: 12px;">
							<tbody>
								<tr class="active">
									
									<th class="col-md-4">Nome Lista</th>
									<th>Status</th>
									<th>Codigo da Lista</th>
									<th>Data Evento</th>
									<th>Data Aprovação</th>
									<th>Aprovado Por</th>
									
								</tr>
								  <c:forEach items="${lista}" var="lista">				
										<tr >
											 <td class="alinhamentoVertical"><a href="itensProducao?idLIsta=${lista.idLista}">${lista.lista}</a></td>
											 <td class="alinhamentoVertical">-- Aprovado</td>
											 <td class="alinhamentoVertical">${lista.listaCod}</td>
											 <td class="alinhamentoVertical"><fmt:formatDate value="${lista.dataDoEvento.time}" pattern="dd/MM/yyyy"/></td>	
											 <td class="alinhamentoVertical"><fmt:formatDate value="${lista.dataAprovacao.time}" pattern="dd/MM/yyyy HH:mm:ss"/></td>	
											 <td class="alinhamentoVertical">${lista.usuarioAprova.nome}</td>
										</tr>
								  </c:forEach>	
									
						  	<c:forEach items="${data}" var="data">
							  	<c:forEach items="${lista}" var="lista">
							  	 <tr>
							  		<c:if test="${data.time == lista.dataDoEvento.time}">
									  		<td>${lista.listaCod}</td>
							  		</c:if>
	
							  		<c:if test="${data.time != lista.dataDoEvento.time}">
									  		<td>Não é igual</td>
							  		</c:if>
								 </tr>
					  		</c:forEach>
						</c:forEach>
								  	
								  	
						   </tbody>
						</table>
					</div>
	
				</div>
			</div>
		</div>
	
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	</div>
</div>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->




<c:import url="../_comum/footer.jsp" />