<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <option value="${fornecedorNovo[0]}">${fornecedorNovo[1]}</option>
	
	<c:forEach items="${fornecedores}" var="fornecedores">
		<option value="${fornecedores[0]}">${fornecedores[1]}</option>
	</c:forEach>

