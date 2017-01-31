/*  -----------------------------------------------------------------------------    */
/**
 *Formulario CriacaoDetalhesItem 
 */
$("#detalhesItem").submit(function() {
	var obsDetalhesItem = $("#obsDetalhesItem").val();
	if(seFalse($("#obsDetalhesItem"),obsDetalhesItem) == false){return false;}
	return true;
});
$("#delegar").submit(function() {
	var delegaItem = $("#delegaItem").val();
	if(seFalse($("#delegaItem"),delegaItem) == false){return false;}
	return true;
});
$("#dependencia").submit(function() {
	var dependenciaItem = $("#dependenciaItem").val();
	if(seFalse($("#dependenciaItem"),dependenciaItem) == false){return false;}
	return true;
});

$("#fechaPendencia").submit(function() {
	var obsPendencia = $("#obsPendencia").val();
	var statusPendencia = $("#statusPendencia").val();
	if(seFalse($("#statusPendencia"),statusPendencia) == false){return false;}
	if(seFalse($("#obsPendencia"),obsPendencia) == false){return false;}
	return true;
});

/*  -----------------------------------------------------------------------------    */




/*  -----------------------------------------------------------------------------    */
function seVazio(campoInput,campoParaComparar){
	if(campoParaComparar == " " || campoParaComparar == null || campoParaComparar == ""){
		(campoInput).css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Campos Obrigatórios.</span>");
		$(".errors").show();
		return false}
	else{return true}
}
function seFalse(idCampo, valor){
	if(seVazio(idCampo,valor) == false){
		return false;}
}

function seItemNaoSelecionadoVazio(campoInput,campoParaComparar){
	if(campoParaComparar == "" || campoParaComparar == null ){
		(campoInput).css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Selecione um Item.</span>");
		$(".errors").show();
		return false}
	
	else{return true}
}

function seFalseItem(idCampo, valor){
	if(seItemNaoSelecionadoVazio(idCampo,valor) == false){
		return false;}
}

//----------------------------------- Funções Ajax ----------------------------------------- // 

//Inico Dependencia item
function iniciaTempoDependencia(idCriacaoItemHistoricoPendencia){
	$.ajax({
	    method: "POST",
	    url: "iniciaTempoPendencia?idCriacaoItemHistoricoPendencia="+idCriacaoItemHistoricoPendencia,
	    success: function(data) {
	    	$("#iniciaTempoPendencia").text(data);
	    	$(".block").attr("disabled","disabled");
	    	location.reload();
	    },
	    beforeSend: function(){
	      },
	      complete: function(){
	      }
    });
}
//Termino Dependencia item
function terminaTempoDependencia(idCriacaoItemHistoricoPendencia){
	
	$.ajax({
	    method: "POST",
	    url: "terminaTempoPendencia?idCriacaoItemHistoricoPendencia="+idCriacaoItemHistoricoPendencia,
	    success: function(data) {
	    	$("#terminaTempoPendencia").text(data);
	    	$(".block").attr("disabled","disabled");
	    	location.reload();
	    },
	    beforeSend: function(){
	      },
	      complete: function(){
	      }
    });
}

/**
Item Ajax
*/

//Inico item
function iniciaTempoItem(idCriacaoItem){
	$.ajax({
	    method: "POST",
	    url: "iniciaTempo?idCriacaoItem="+idCriacaoItem,
	    success: function(data) {
	    	$("#itemIniciaAjax").html(data);
	    	$("#botaoIniciarItem").css({"opacity":".8","pointer-events":"none"});
	    	$("#fechaDivDetalhesItem").css({"opacity":".8","pointer-events":"none"});
	    	$("#delegarDivDetalhesItem").css({"opacity":".8","pointer-events":"none"});
	    	$("#dependenciaDivDetalhesItem").css({"opacity":".8","pointer-events":"none"});
	    	$("#terminarItemAjax").css({"opacity":"1","pointer-events":"visible"});
	    	alert("Contagem do tempo iniciado.");
	    	location.reload();
	    },
	    beforeSend: function(){
	      },
	      complete: function(){
	      }
    });
}

//Termino item
function terminaTempoItem(idCriacaoItem,idHistoricoItem){
	$.ajax({
	    method: "POST",
	    url: "finalizaTempo?idCriacaoItem="+idCriacaoItem+"&idHistoricoItem="+idHistoricoItem,
	    success: function(data) {
	    	
	    	$("#itemTerminaAjax").html(data);
	    	$("#terminarItemAjax").css({"opacity":".8","pointer-events":"none"});
		    $("#detalhesItem").css({"opacity":"1","pointer-events":"visible"});
			alert("Contagem do tempo finalizada.");
			location.reload();
	    },
	    beforeSend: function(){
	      },
	      complete: function(){
	      }
    });
}

/*function concluiListaCriacaoPorAjax(idCriacaoLista) {
	$.ajax({
		method : "POST",
		url : "fechaListas?idCriacaoLista="+idCriacaoLista,
		success : function(data) {
			$("#listaCriacaoConcluida").fadeIn(300).html(data);
		}
	});
};
*/

function exibeEmpresaCriacao(idEmpresa){
	
	if(idEmpresa == "selecione"){
		alert("Selecione uma empresa.")
	}else{
	
		$.ajax({
			method : "POST",
			url : "exibeEmpresaCriacao?idEmpresa="+idEmpresa,
			success : function(data) {
				$("#jobFechados").fadeIn(300).html(data);
			}
		});
	}

}






