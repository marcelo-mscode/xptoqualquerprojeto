<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

	<select class="form-control caminho" name="pastaIntermediaria" onchange="criaCampoPastaIntermediaria(this.value);" id="selectIntermediario">
			<option value="selecione">Selecione</option>
	
	
			<option id="criarPasta" value="criaPasta">Criar Pasta</option>
	   
	   
	   
	              <c:forEach items="${pastas}" var="pastas">
		      <option value="${pastas}">${pastas}</option>								
	        </c:forEach>
	
	</select>

		