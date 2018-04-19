<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


	<tr>
		<th class="fundoRosa input-260px">Contas a receber:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${contasReceber}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoRosa input-260px">BV's a receber:</th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th style="border: 2px solid #000;"><b> - </b></th>
		<th></th>
	</tr>
	
	<tr>
		<th class="fundoRosa input-260px">Total a Receber</th>
		<th> - </th>
		<th> - </th>
		<th>Total =></th>
		<th> - </th>
		<th style="border: 2px solid #000"><b><fmt:formatNumber value="${contasReceber}" pattern="#,##0.00"/></b></th>
		<th> - </th>
	</tr>
	