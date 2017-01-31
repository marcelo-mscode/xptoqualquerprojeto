<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:choose>
	
      <c:when test="${empty fornecedores}">
			<option value="" style="color: red !important;">Não existe item Relacionado</option>
	  </c:when>
	
      <c:otherwise>

		<c:forEach items="${fornecedores}" var="produto">
			<option value="${produto.idproduto}">${produto.produto}</option>
	    </c:forEach>

	  </c:otherwise>
	
</c:choose>	




	
<%-- <c:choose>
	
      <c:when test="${empty fornecedores.listaDeProdutos}">
			<option value="" style="color: red !important;">Não existe item Relacionado</option>
	  </c:when>
	
      <c:otherwise>

		<c:forEach items="${fornecedores.listaDeProdutos}" var="produto">
			<option value="${produto.idproduto}">${produto.produto}</option>
	    </c:forEach>

	  </c:otherwise>
	
</c:choose>	
	 --%>
	
	
	
	
	