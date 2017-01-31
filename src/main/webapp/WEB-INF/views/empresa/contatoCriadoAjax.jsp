<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="contador" value="0" />

<c:forEach items="${comunicador}" var="total">
	<c:set var="contador" value="${ contador + 1 }" />	
</c:forEach>

<input type="hidden" id="idContatoCriado" value="${contatos}">	

<table class="table table-bordered exibeComunicador">
	<c:forEach items="${comunicador}" var="comunicador">
		<tr>
			<td id="${comunicador.idComunicador}">${comunicador.comunicador}</td>
			<td><input type="hidden" id="xrComunicador" value="${comunicador.contato.idContato}"> <a href="#" onclick="clicou('${comunicador.idComunicador}','${comunicador.comunicador}','${contatos}');">Editar</a></td>

			<c:if test="${contador == '1'}">	
	        </c:if>
	        <c:if test="${contador != '1'}">
				<td><a href="#" onclick="apagaComunicador(${comunicador.idComunicador},${contatos});" > X </a></td>
			</c:if>
			
			
       </tr>
	</c:forEach>
</table>


<%-- <table class="table table-bordered exibeComunicador">
	<c:forEach items="${contato}" var="contato">
			<c:forEach items="${contato.comunicador}" var="comunicador">
			  <tr>
				<td id="${comunicador.idComunicador}">${comunicador.comunicador}</td>
			 	
			 	<td>
			 	  <input type="hidden" id="xrComunicador" value="${contato.idContato}">
			 	  	<a href="#" onclick="transformaColunaEmLinha('${comunicador.idComunicador}','${comunicador.comunicador}','${contatos}');">
			 	  	  Editar
			 	  	</a>
			 	</td>
				
				<c:if test="${contador == '1'}">	
	            </c:if>
				<c:if test="${contador != '1'}">	
					<td><a href="#" onclick="apagaComunicadorEditar(${comunicador.idComunicador},${contato.idContato});" > X </a></td>
	            </c:if>
	        
	        
	        
	         </tr>
		</c:forEach>
	</c:forEach>
</table>   --%>
				