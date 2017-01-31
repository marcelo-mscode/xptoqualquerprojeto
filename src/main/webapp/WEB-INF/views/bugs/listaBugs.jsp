<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />


<div class="col-md-11" style="margin-top: 30px">
<table class="table table-striped table-td-ajuste table-sem-bold table-hover " style="font-size: 10px;">
		<tbody id="listaJobAjax">
			<tr>
				<th>id Bug</th>
				<th>Tipo do Bug</th>
				<th>Data Envio</th>
				<th>enviadoPor</th>
				<th>urlAnterior</th>
				<th>urlAtual</th>
				<th>solucao</th>
				<th>solucaoPor</th>
			</tr>


			<c:forEach items="${bugs}" var="bugs">
			
				<tr style="font-size: 10px;">
					<td>${bugs.idBugs}</td>
					<td>${bugs.tipoErro}</td>
					<td><fmt:formatDate value="${bugs.criadoEm.time}" pattern="dd/MM/yyyy HH:mm:ss"/> </td>
					<td>${bugs.enviadoPor.nome}</td>
					<td>${bugs.urlAnterior}</td>
					<td>${bugs.urlAtual}</td>
					<td>${bugs.solucao}</td>
					<td>${bugs.solucaoPor}</td>
				</tr>	

			</c:forEach>				
	</tbody>
</table>
</div>







<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

	

<c:import url="../_comum/footer.jsp" />