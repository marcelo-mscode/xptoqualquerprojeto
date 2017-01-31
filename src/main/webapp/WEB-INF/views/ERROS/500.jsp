<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />


<div class="container">

<div class="col-md-2"></div>


<div class="col-md-8" style="height: 800px;margin-top: 10%;background: #fff;padding-top: 50px;box-shadow: 5px 15px 10px 5px #999">
	<img src="resources/images/500.png" style="margin-left: 15%;">
	<br /><br />
	<div class="back">
		<button class="btn btn-primary botaoVoltar" onclick="inicio(500);" style="margin-left: 15%;">REPORTAR</button>
	</div>
		<div class="bug500"style="width: 650px; height: 50px;margin-left: 15%;margin-top: 10px">
			<div class="coletando display-none">
				<img src='resources/images/ajax-loader-fff.gif' width='20'height='20' alt='loading' id='loader-confirmacao'
					style='margin: 0px 10px 5px 5px;'> <span>Coletando Informações ...</span><br />
			</div>

			<div class="enviando display-none">
				<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao'
					style='margin: 0px 10px 5px 5px;'> <span>Enviando ...</span><br />
			</div>

			<div class="mais display-none">
				<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao'
					style='margin: 0px 10px 5px 5px;'> <span>Falta pouco ...</span><br />
			</div>
		</div>

</div>

	<div class="col-md-2"></div>

</div>


<div id="retorno"></div>

<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/bugs.js" />"></script>


