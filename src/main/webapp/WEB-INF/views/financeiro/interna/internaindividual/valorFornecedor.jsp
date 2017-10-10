<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${itensInterna.diferenca == itensInterna.valorItem || itensInterna.valorContratacao != '0.00'}">
	<input name="" id="valorF${itensInterna.idProducao}" class="ajusteInput" type="text"
		value="<fmt:formatNumber value="${itensInterna.valorContratacao}" pattern="#,##0.00"/>"
		onblur="valorF('valorF',${itensInterna.idProducao});" />
</c:if>

<c:if
	test="${itensInterna.diferenca != itensInterna.valorItem && itensInterna.valorContratacao == '0.00'}">
	<input name="" id="valorF${itensInterna.idProducao}"
		class="ajusteInput" type="text" value="<fmt:formatNumber value="${itensInterna.valorItem}" pattern="#,##0.00"/>"
		onblur="valorF('valorF',${itensInterna.idProducao});" />
</c:if>