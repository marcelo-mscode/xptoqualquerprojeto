<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<tr class="active">
	<th>Cod Lista</th>
	<th>R</th>
	<th>Listas</th>
	<th>Cliente</th>
	<th>Job</th>
	<th>Status</th>
</tr>

<c:forEach items="${listas}" var="lista">
	<tr>
		<td>${lista[5]}</td>
		<td>${lista[2]}</td>
		<td class="alinhamentoVertical"><a href="editaLista?idLista=${lista[0]}">${lista[1]}</a></td>
		<td>${lista[3].empresa.empresa}</td>
		<td>${lista[3].titulo}</td>
		<td>${lista[4].listaEstatus}</td>
	</tr>
</c:forEach>