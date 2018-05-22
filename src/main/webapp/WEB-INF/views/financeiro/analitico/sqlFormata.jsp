<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>

input{ margin-right: 10px !important } 

</style>

<c:import url="../../_comum/header.jsp" />

<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Listas Analíticos</li>
		  </ol>					
</div>

<div class="col-md-12" style="height: 235px;border-bottom: 1px solid #ccc;padding: 50px 50px">
	<form action="sqlFormataTexto" method="POST">
		
		<input type="text" name="dia" value="12"/>Dia<br><br>
		<input type="text" name="descricao" />Descrição<br><br>
		<input type="text" name="valor" />Valor<br><br>
		<input type="submit" name="Enviar" />
		
	</form>
	
</div>

<div class="col-md-12" style="height: 80px;border: 1px solid #ccc;padding: 10px;margin-top: 50px">
	
		${descricao}
</div>

<c:import url="../../_comum/footer.jsp" />