<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<c:if test="${statusLista  == 2}">
		<button onclick="aprovarPlanilhaPorAjax(${idLista});" type="button" class="btn btn-primary" id="aprovarPlanilha" style="float: right;">Aprovar Para Produção</button> 
	</c:if>
	
	<c:if test="${statusLista  == 5}">
		<button disabled="disabled" type="button" class="btn btn-primary" id="aprovarPlanilha" style="float: right;">Aprovado Para Produção</button> 
	</c:if>
