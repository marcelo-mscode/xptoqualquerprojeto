<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />




<div class="container"> 
<p>Teste</p>

<%-- <table border="1">
	<tr>
		
		<th>Id Job</th>
		<th>Cod Job</th>
		
		<th>Status</th>
		
	</tr>
	


<c:forEach items="${jobs}" var="job">
		<tr>
		    <td>${job.idJob}</td>
		    <td>${job.codJob}</td>
		    
	<c:forEach items="${job.jobStatus}" var="status" varStatus="id">
			
			<c:if test="${id.last}">
					<td>É o último</td>					
					<td>${status.idStatus.estatus}</td>	
			
			</c:if>
			
			
			
			
			<td>${id.count}</td>	
			
	</c:forEach>        
  	    </tr>
</c:forEach>        

</table> --%>

<table class="table table-striped table-td-ajuste table-sem-bold"
						style="font-size: 12px;">
						<tbody>
							<tr>
								
								<th>Título</th>
								<th>Empresa</th>
								<th>Contato</th>
								<th>Código do Job</th>
								<!-- <th>Data Prevista</th> -->
								<th>Data de Entrada</th>
								<th>Data de Proposta</th>
								<th>Status</th>
								<!-- <th>Prazo Interno</th> -->
								
							</tr>
						<c:forEach items="${jobs}" var="job">
							<tr>
								<td><a href="editaJob?idJob=${job.idJob}&idEmpresa=${job.empresa.idEmpresa}">${job.titulo}</a></td>
								<td>${job.empresa.empresa}</td>
								<td>${job.contato.contato}</td>
								<td>${job.codJob}</td>
								<%-- <td></td>
								<td><fmt:formatDate value="${job.jobStatus.criadoData.time}" pattern="dd/MM/yyyy"/></td> --%>
								<td><fmt:formatDate value="${job.criadoEm.time}" pattern="dd/MM/yyyy"/></td>
								<td><fmt:formatDate value="${job.propostaData}" pattern="dd/MM/yyyy"/>
								
<c:forEach items="${job.jobStatus}" var="status" varStatus="id">
			
			<c:if test="${id.last}">
					<td>${status.idStatus.estatus}</td>	
			
			</c:if>
			
</c:forEach>  


						        
						        
						        
						        
						        
						        <!-- td> </td> -->
							</tr>
						</c:forEach>

					   </tbody>
					</table>


</div>



<c:import url="../_comum/footer.jsp" />