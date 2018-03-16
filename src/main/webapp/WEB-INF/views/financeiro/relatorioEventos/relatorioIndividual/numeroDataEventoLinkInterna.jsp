<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
	<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">
EVENTO 3 - <c:forEach items="${infoLista.idJob.localEvento}" var="localEvento"> 
				<fmt:formatDate value="${localEvento.localEventoDataInicio}" pattern="dd/MM/yyyy"/><br>  
		   </c:forEach>
	</span>
	<a href="internaIndividual?idLista=${infoLista.idLista}" style="float: right;">Planilha Interna: ${infoLista.lista}</a>
</div>