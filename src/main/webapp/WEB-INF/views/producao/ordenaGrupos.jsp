<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
.ordemGrupoElementos{border-bottom:0px solid #ddd;border-top:1px solid #ddd;padding: 5px 10px 5px 0px;background:#fff;height: 30px;text-align: left;line-height: 15px;}
.divOrdemGrupo{background:#ccc;padding: 15px 10px;height: 30px;text-align: left;line-height: 5px;"}

</style>



<c:import url="../_comum/header.jsp" />
<div class="col-md-12 bodyXY" style="height: 35px;">
					
		  <ol class="breadcrumb">
		    <li><a href="#">Menu</a></li>
		    <li><a href="listaProducao">Produção</a></li>
		    <li><a href="editaLista?idLista=${idLista}">Edição de  lista de Produção</a></li>
		    <li class="active">Ordenar Linhas</li>
		  </ol>
					
		</div>
    </div>
</div>
<br /><br />

<div class="row" style="padding: 0px 25px;">

	<div class="ui-widget-content ui-corner-all" style="padding: 25px 25px 25px 25px">
	<a href="editaLista?idLista=${idLista}" class="btn btn-default" style="margin-bottom: 10px;">Voltar</a>
		<c:forEach items="${categoria}" var="categoria">
			<div class="ui-state-default" class="divOrdemGrupo" id="${categoria.idcategoria}" 	style="padding: 5px;margin: 20px 0px;">
				${categoria.categoria}
			
				<img src="resources/images/loader-lista.gif" class="display-none loaderConfirmacao${categoria.idcategoria}" width="15" height="15" alt="loading" style="margin-top: 0px;margin-left: 20px;">
				<span class="col-md-offset-3 display-none msgOk${categoria.idcategoria}" style="font-size: 15px;color: red;">Ordenação concluída !</span>
		
			</div>
			  <div class="ordem">
				
				<c:forEach items="${grupoOrdem}" var="grupo">	
       		        <c:forEach items="${categoria.grupo}" var="grupoCompara">
       		        	<c:if test="${grupo.idgrupo == grupoCompara.idgrupo}">
							<div class="ordemGrupoElementos" id="id-${grupo.idgrupo}">
								<div class="col-md-5">${grupo.grupo}
								</div>
								<div class="col-md-1">
									<img src="resources/images/loader-lista.gif" class="display-none loaderConfirmacao${grupo.idgrupo}" width="15" height="15" alt="loading" style="margin-top: 13px;margin-left: 20px;">
								</div>
								
								<div class="col-md-3">
								</div>							
							
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>			
		 </div>	
			
		</c:forEach>
	</div>    
	
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<c:import url="../_comum/footer.jsp" />  

<script type="text/javascript">
$(function(){
	
	var opcoesOrdem = {
			stop: function (event, ui) {
				axis: 'y',
				ordenaListaProducao(this)
				
			}
	}
	$('.ordem').sortable(opcoesOrdem,{containment : 'parent'});
});

function teste(){
	alert("Finalizada");
}


function ordenaListaProducao(div){
 	var objs = $(div).sortable('serialize', { key: 'id' });
 	var idLista = ${idLista};
 	
 	var id = $(div).prev().attr("id");
 	
    $.ajax({
		method : "POST",
		url : "ordenarGrupos?"+objs+"&idLista="+idLista,
			success : function(data) {
			$(".loaderConfirmacao"+id).fadeOut(800);	
			$(".msgOk"+id).fadeIn(1500);	
			
		},
		beforeSend : function() {
			$(".loaderConfirmacao"+id).fadeIn(500);	
		},
			complete : function() {
			$(".msgOk"+id).fadeOut(4000);
		}
	
	});
   }

</script>