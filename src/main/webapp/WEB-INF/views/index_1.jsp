<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/syslocc.css"  />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-1.8.18.custom.css" />" />


</head>
<body>
Bem vindos! &nbsp

De novo.


<form action="salva" method="post">

<input type="checkbox" checked="checked" name="habilitado"><br>
<input type="text" name="nome" placeholder="Nome"><br>
<input type="text" name="email" placeholder="Email@email"><br>
<input type="text" name="ramal" placeholder="Ramal"><br>
<select name="departamento">
<option value="Atendimento">Atendimento</option>
<option value="Comercial">Comercial</option>
</select>

<br>
<input type="text" name="Cargo" placeholder="Cargo"><br>
<input type="text" name="Usuário" placeholder="Usuário"><br>
<input type="text" name="Senha" placeholder="Senha"><br>

<button class="btn btn-danger" type="submit">Salvar</button>


</form>
</body>
</html>