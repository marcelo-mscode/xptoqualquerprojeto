<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-md-4"></div>
<div class="col-md-6 sub-div-excel">
	<div class="col-md-12">
		<i class="glyphicon glyphicon-alert glyphicon-atencao"></i>
		<h4 style="line-height: 30px;">As linhas abaixo contém itens com<br>Fat Locco e Fat Direto na mesma linha.</h4>
		<span>As informações da Linha no excel ficarão repetidas. Corrija a a</span>
	</div>
	
	<div class="col-md-12 div-botao-confirmacao">
			<ul class="list-group">
			  <c:forEach items="${listaImpostosDiferentes}" var ="itens">
				<li class="list-group-item"><a href="editaLinha?idGrupo=${itens[0]}">${itens[1]}</a></li>
			  </c:forEach>
			</ul>
	
	</div>
	
</div>

<div class="col-md-4"></div>