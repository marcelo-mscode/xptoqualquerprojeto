<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div class="col-md-4"></div>


<div class="col-md-4 sub-div-confirmacao">
	<div class="col-md-12">
		<i class="glyphicon glyphicon-ok glyphicon-sucesso"></i>
		<h4>Revisão criada com sucesso.</h4>
		<p>Clique em Fechar para acessar a Planilha!</p>
	</div>
	
	<div class="col-md-12 div-botao-confirmacao"><a href="editaLista?idLista=${idNovaLista}" class="btn btn-success botao-confirmacao">Fechar</a></div>
</div>

<div class="col-md-4"></div>
