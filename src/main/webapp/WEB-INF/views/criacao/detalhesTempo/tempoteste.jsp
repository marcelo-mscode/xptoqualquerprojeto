<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<p style="margin: 5px 0 0 3px;"><fmt:formatDate value="${tempo2.dataTermino.time}" pattern="dd/MM/yyyy"/></p>
<span style="font-size: 25px;font-weight: bolder;display: block;margin-left: 8px;">
	<fmt:formatDate value="${tempo2.dataTermino.time}" pattern="HH:mm"/>
</span>
