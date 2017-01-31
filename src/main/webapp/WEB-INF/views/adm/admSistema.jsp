<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
<script type="text/javascript" src="resources/js/jquery-1.11.2.min.js"></script>	
<script type="text/javascript">
function emailSucesso(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-sucesso-email").slideToggle("slow"); 
	    }, 2500);
	});
}

function emailErro(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-erro-email").slideToggle("slow"); 
	    }, 10000);
	});
}
</script>


<c:if test="${msg.get(0) == 'sucesso'}">
	<div class="msg-email msg-sucesso-email" id="msg-sucesso-email" >${msg.get(1)}</div>
	<script type="text/javascript">emailSucesso();</script>
</c:if>	

<c:if test="${msg.get(0) == 'erro'}">
	<div class="msg-email msg-erro-email" id="msg-erro-email">${msg.get(1)}</div>
	<script type="text/javascript">emailErro();</script>
</c:if>




<div class="container" style="margin-top: 50px;background: #fff;box-shadow: 5px 5px 5px #ccc;padding: 40px">



		<div class="col-md-12" style="margin-bottom: 30px">
			<h2 style="font-family: 'OpenSansLight';margin-bottom: 25px">CONFIGURAÇÕES DO SISTEMA</h2>
		</div>

		<div class="col-md-12" style="margin-bottom: 30px">
		<h4 style="font-family: 'OpenSansLight';margin-bottom: 5px">Produção</h4>
			<a href="cartaContratacao" class="btn btn-success">Carta Contratação</a>
		</div>
		
<!-- 		<div class="col-md-12" style="margin-bottom: 30px">
			<a href="atualizaHabilitadoEmpresa" class="btn btn-success">Colocar novas Empresas corretamente nas consultas</a>
		</div>
 -->
</div>








<c:import url="../_comum/footer.jsp" />