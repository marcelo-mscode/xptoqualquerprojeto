<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<tr>
	<th>Nome da empresa</th>
	<th>Categoria</th>
	<th>Status</th>
</tr>

<c:forEach items="${empresas}" var="empresas" varStatus="conta">
		<tr >
			<td><a href="infoempresa?id=${empresas[0]}">${empresas[1]}</a></td>
				
			<c:choose>	
					<c:when test="${empresas[2] eq true and empresas[3] eq true and empresas[4] eq true}">
						<td>Cliente/Fornecedor/Prospect</td>
					</c:when>
					
						 <c:when test="${empresas[2] eq true and empresas[3] eq true and empresas[4] eq false}">
						<td>Cliente/Fornecedor</td>
					</c:when>
					
					<c:when test="${empresas[2] eq true and empresas[3] eq false and empresas[4] eq true}">
						<td>Cliente/Prospect</td>
					</c:when>
					
					<c:when test="${empresas[2] eq true and empresas[3] eq false and empresas[4] eq false}">
						<td>Cliente</td>
					</c:when>
					
					
					<c:when test="${empresas[2] eq false and empresas[3] eq true and empresas[4] eq true}">
						<td>Fornecedor/Prospect</td>
					</c:when>

					<c:when test="${empresas[2] eq true and empresas[3] eq true and empresas[4] eq false}">
						<td>Fornecedor/Cliente</td>
					</c:when>
					
					<c:when test="${empresas[2] eq false and empresas[3] eq true and empresas[4] eq false}">
						<td>Fornecedor</td>
					</c:when>


					<c:when test="${empresas[2] eq false and empresas[3] eq false and empresas[4] eq true}">
						<td>Prospect</td>
					</c:when>
					
			</c:choose>
			
			<c:if test="${empresas[5]}">
				<td>Ativa</td>
			</c:if>

			<c:if test="${!empresas[5]}">
				<td class="disab">Desativada</td>
			</c:if>

		</tr>
</c:forEach>