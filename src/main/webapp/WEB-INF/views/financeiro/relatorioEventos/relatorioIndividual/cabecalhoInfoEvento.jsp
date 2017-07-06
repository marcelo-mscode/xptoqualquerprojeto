<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-striped table-hover table-bordered bordaDestaque" >
  <tbody id="prospeccaoFiltro">
	<tr>
		<td>Cliente</td>
		<td>${infoLista.idJob.empresa.empresa}</td>
		<td></td>
		<td>Data do Evento</td>
		<td style="background-color: red;color: #fff">06/05/2016 Colocar data</td>
		
	</tr>

	<tr>
		<td>Nome do Evento</td>
		<td>${infoLista.lista}</td>
		<td style="background-color: red;color: #fff">NF</td>
		<td>Local do Evento</td>
		<td style="background-color: red;color: #fff">SP</td>
	</tr>
  </tbody>
</table>