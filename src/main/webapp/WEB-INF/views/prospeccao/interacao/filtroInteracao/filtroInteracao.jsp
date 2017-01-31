<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<tr class="" style="background: #f1f1f1 !important">
		<th>Empresa</th>
		<th>Título</th>
		<th>Interação</th>
		<th>Contato</th>
		<th>Data</th>
		<th>Próx Interação</th>
		<th>Nome</th>
</tr>

<c:forEach items="${interacoes}" var="interacoes">
			<%-- <c:if test="${loop.count >= qtdDias || qtdDias == null}"></c:if>

			<c:if test="${loop.count <= qtdDias || qtdDias == null}"> --%>
			<tr>
				<td class="fontInteracaoSize">
					<c:forTokens items="${interacoes.idProspeccao.idEmpresa.empresa}" delims=" " var="word" varStatus="status">  
	                   <c:if test="${status.count < 3}">  
	                     <c:out value="${word}" escapeXml="false"/>  
	                   </c:if>  
	                </c:forTokens>
				</td>
			    
			    <td class="fontInteracaoSize">
			    <a href="edicaoProspeccao?idProspeccao=${interacoes.idProspeccao.idProspeccao}">
			    <c:forTokens items="${interacoes.idProspeccao.titulo}" delims=" " var="word" varStatus="status">  
                   <c:if test="${status.count < 4}">  
                     <c:out value="${word}" escapeXml="false"/>  
                   </c:if>  
                </c:forTokens>
			    
			    </a></td>
				
				<td class="fontInteracaoSize" style="width: 370px !important;">
				
				<c:forTokens items="${interacoes.interacao}" delims=" " var="word" varStatus="status">  
                   <c:if test="${status.count < 7}">  
                     <c:out value="${word}" escapeXml="false"/>  
                   </c:if>  
                </c:forTokens>
				</td>
				
				<td class="fontInteracaoSize">
				<c:if test="${interacoes.idContato.idContato == 1}"></c:if>
				
				<c:if test="${interacoes.idContato.idContato != 1}">
				   <c:forTokens items="${interacoes.idContato.contato}" delims=" " var="word" varStatus="status">  
	                   <c:if test="${status.count < 3}">  
	                     <c:out value="${word}" escapeXml="false"/>  
	                   </c:if>  
	                </c:forTokens>	
				</c:if>
				</td>
				
				
				<td class="fontInteracaoSize"><fmt:formatDate value="${interacoes.dataInteracao.time}" pattern="dd/MM/yyyy HH:mm"/></td>
				
				<td class="fontInteracaoSize">
				
					<c:if test="${interacoes.dataProximaInteracao  != '0001-01-01 00:00:00.0'}">
						<fmt:formatDate value="${interacoes.dataProximaInteracao}" pattern="dd/MM/yyyy HH:mm"/>
					</c:if>
					
					<c:if test="${interacoes.dataProximaInteracao  == '0001-01-01 00:00:00.0'}">
					</c:if>					
				</td>
				
				
				<td class="fontInteracaoSize">
				<c:forTokens items="${interacoes.idUsuario.nome}" delims=" " var="word" varStatus="status">  
                   <c:if test="${status.count < 2}">  
                     <c:out value="${word}" escapeXml="false"/>  
                   </c:if>  
                </c:forTokens>	
				
					
					
				</td>
			</tr>
			<%-- </c:if> --%>
</c:forEach>