<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<c:forEach items="${joda}" var="joda" varStatus="loop">
	
					<c:if test="${lista.listaProducao.idJob.idJob == joda[0]}">
						
						<c:if test="${joda[2][2] < 0 || joda[2][2] == 0}">
							<td class="vermelhoFlat" style="color: #fff" >
								<fmt:formatDate value="${lista.listaProducao.idJob.propostaData}" pattern="dd/MM/yyyy HH:mm" />
							</td>
						</c:if>
						
						<c:if test="${joda[2][2] >= 1 && joda[2][2] <= 5 }">
							<td class="amareloFlat">
							
								<fmt:formatDate value="${lista.listaProducao.idJob.propostaData}" pattern="dd/MM/yyyy HH:mm" />
							</td>
						</c:if>
						
						<c:if test="${joda[2][2] > 5 }">
							<td>
								<fmt:formatDate value="${lista.listaProducao.idJob.propostaData}" pattern="dd/MM/yyyy HH:mm" />

<%-- 								Em: ${joda[2][2]} dia(s) --%>

							</td>
						</c:if>
					 </c:if>
					 		
					 		
					
					<c:if test="${lista.listaProducao.idJob.idJob != joda[0]}">
					
					</c:if>
	
</c:forEach>