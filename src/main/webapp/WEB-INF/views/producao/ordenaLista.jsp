<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
<div class="col-md-12 bodyXY" style="height: 35px;">
					
		  <ol class="breadcrumb">
		    <li><a href="#">Menu</a></li>
		    <li><a href="listaProducao">Produção</a></li>
		    <li><a href="editaLista?idLista=${idLista}">Edição de  lista de Produção</a></li>
		    <li class="active">Ordenar Categorias</li>
		  </ol>
					
		</div>
    </div>
</div>
<br /><br />


<div class="row" style="padding: 0px 25px;">


	<div class="ordem ui-widget-content ui-corner-all" style="padding: 25px 25px 25px 25px">
	<a href="editaLista?idLista=${idLista}" class="btn btn-default" style="margin-bottom: 10px">Voltar</a>
		<c:forEach items="${categoria}" var="categoria">
			<div class="ui-state-default" id="id-${categoria[0]}" style="padding: 5px 10px;height: 30px;text-align: left;line-height: 15px;">${categoria[1]}</div>
		</c:forEach>
	</div>    
	
	<div class="col-md-12 ui-widget-content ui-corner-all" style="height: 67px;">
		<div class="col-md-1">
			<a onclick="ordenaListaProducao();" class="btn btn-success" style="margin-top: 15px;color: #fff;" id="ordenarButton">Ordenar</a>
		</div>
		<div class="col-md-1" style="padding: 5px 20px;">
		    <img src="resources/images/loader-lista.gif" class="display-none" width="30" height="30" alt="loading" id="loaderConfirmacao" style="margin-top: 13px;margin-left: 20px;">
		</div>
		<div class="col-md-3" style="padding: 20px;">
			<span class="col-md-offset-3 display-none" id="msgOk" style="font-size: 15px;color: red">Ordenação concluída !</span>
		</div>
	</div>
	
	
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<c:import url="../_comum/footer.jsp" />  

<script type="text/javascript">
$(function(){
	var opcoesOrdem = {
			stop: function (event, ui) {
				axis: 'y'
			}
	}
	$('.ordem').sortable(opcoesOrdem);
});

function ordenaListaProducao(){
	
 	var objs = $('.ordem').sortable('serialize', { key: 'id' });
 	var idLista = ${idLista};
 	
	$.ajax({
		url : "ordenar?" + objs + "&idLista = " + idLista,
			success : function(data) {
			$("#msgOk").fadeIn(500);	
 			$("#loaderConfirmacao").fadeOut(500);	
			$("#ordenarButton").text("Ordenar");
		},
		
		beforeSend : function() {
			
			$("#loaderConfirmacao").fadeIn(500);	
			$("#ordenarButton").text("Ordenar");
		},
			complete : function() {
				/* location.reload(); */
		}
	
	});

}

</script>