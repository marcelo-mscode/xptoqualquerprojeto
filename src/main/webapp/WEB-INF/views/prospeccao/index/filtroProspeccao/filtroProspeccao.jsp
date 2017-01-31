<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


		<tr style="background: #f1f1f1 !important">
			<th>Título</th>
			<th>Empresa</th>
			<th>Última Interação</th>
			<th>Data</th>
			<th>Agendamento</th>
		</tr>
	
		<c:forEach items="${prospeccoes}" var="prospeccoes">
			<tr>
			    <td><a href="edicaoProspeccao?idProspeccao=${prospeccoes.idProspeccao}">${prospeccoes.titulo}</a></td>
				<td>
					<c:forTokens items="${prospeccoes.idEmpresa.empresa}" delims=" " var="word" varStatus="status">  
	                   <c:if test="${status.count < 3}">  
	                     <c:out value="${word}" escapeXml="false"/>  
	                   </c:if>  
	                </c:forTokens>
				</td>
			
				<c:forEach items="${prospeccoes.interacao}" var="interacao" varStatus="loop">
				  <c:if test="${loop.last}">	
					<td>
						<c:forTokens items="${interacao.interacao}" delims=" " var="word" varStatus="status">  
		                   <c:if test="${status.count < 6}">  
		                     <c:out value="${word}" escapeXml="false"/>  
		                   </c:if>
	                    </c:forTokens>	
					</td>
					<td><fmt:formatDate value="${interacao.dataInteracao.time}" pattern="dd/MM/yyyy hh:mm"/></td>
					<td>
						<c:if test="${interacao.dataProximaInteracao  != '0001-01-01 00:00:00.0'}">
							<fmt:formatDate value="${interacao.dataProximaInteracao}" pattern="dd/MM/yyyy HH:mm"/>
						</c:if>
						<c:if test="${interacao.dataProximaInteracao  == '0001-01-01 00:00:00.0'}"></c:if>
					</td>
				  </c:if>
				</c:forEach>
				
				
			</tr>
		</c:forEach>	