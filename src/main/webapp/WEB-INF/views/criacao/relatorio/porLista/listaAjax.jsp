<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<table class="table table-hover">
			<tr style="font-size: 15px;">
				<th>Lista</th>
				<th>Dt Entrada</th>
				<th>Dt Fechamento</th>
				<th>Total Horas Usadas</th>
			</tr>
			
			<c:forEach items="${lista}" var="lista">
				<tr>
					<td><a href="porListaIndividual?idCriacaoLista=${lista.idCriacaoLista}">${lista.listaTitulo}</a></td>
					<td><fmt:formatDate value="${lista.dataCriacao.time}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${lista.dataFechamento.time}" pattern="dd/MM/yyyy"/></td>
					
					<c:if test="${lista.tempoTotal != null}">
						<td>${lista.tempoTotal}</td>
					</c:if>
					
					<c:if test="${lista.tempoTotal == null}">					
					<c:forEach items="${totalHorasListas}" var="totalHorasListas">
						<c:if test="${totalHorasListas.idCriacaoLista == lista.idCriacaoLista}">
								<td>${totalHorasListas.teste[0]}Hs ${totalHorasListas.teste[1]}Min</td>
						</c:if>
					</c:forEach>
					</c:if>

				</tr>
 		    </c:forEach>
		</table>
