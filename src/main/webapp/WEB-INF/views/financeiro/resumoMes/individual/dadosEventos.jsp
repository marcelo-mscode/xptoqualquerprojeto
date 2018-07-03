<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<tr>
				<th class="input-260px">EVENTOS: CLIENTE</th>
				<th class="input-160px">NOME DO EVENTO</th>
				<th class="input-160px">DATA</th>
				<th class="input-160px">LOCAL</th>
				<th class="input-160px">EVENTO</th>
				<th class="input-160px">Valor</th>
				<th>Análise vertical</th>
			</tr>
			
			<c:forEach items="${infoEvento}" var="infoEvento" varStatus="loop">
			
				<c:forEach items="${infoLista}" var="infoLista">
			
					<c:if test="${infoEvento.idLista == infoLista.idLista}">
					   <c:if test="${infoEvento.ndFatDireto == true}"><tr class="error"></c:if>	
					   <c:if test="${infoEvento.ndFatDireto == false}"><tr></c:if>	
							<td>${infoLista.idJob.empresa.empresa}</td>
							<td>${infoLista.lista}</td>
							<td>${infoEvento.mesEvento}</td>
							<td>SP</td>
							<td>${loop.count}</td>
							<td><fmt:formatNumber value="${infoEvento.valorLoccoAgenc}" pattern="#,##0.00"/></td>
							<td>
								<c:if test="${infoEvento.ndFatDireto == false}">NF</c:if>
								<c:if test="${infoEvento.ndFatDireto == true}">ND</c:if>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
			
			<tr>
				<td colspan="6"></td>
				<td style="padding: 20px;font-weight: bold;"><fmt:formatNumber value="${somaTotalEventos}" pattern="#,##0.00"/></td>
			</tr>