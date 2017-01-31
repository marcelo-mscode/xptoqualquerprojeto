<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />


<div class="container"> 

Comunicador.



<table class="table table-striped table-td-ajuste table-sem-bold">
	<tr>
		<th>Nome</th>
		<th>Cargo</th>
		<th>Tipo Contato</th>
	
	</tr>


	<c:forEach items="${comunicador}" var="comunicador" varStatus="contador">
	
		<tr>
		
			<%-- <c:if test="${contador.count < 2}"> --%>
				<td>${comunicador.contato.contato}</td>
				<td>${comunicador.contato.cargo}</td>
		    <%-- </c:if> --%>
		    
		    <%-- <c:if test="${contador.count >= 2}">
				<td>&nbsp</td>
		    <%-- </c:if> --%>	
			

			
			<td>${comunicador.comunicador}</td>

		</tr>
	</c:forEach>
</table>


<c:import url="../_comum/footer.jsp" />