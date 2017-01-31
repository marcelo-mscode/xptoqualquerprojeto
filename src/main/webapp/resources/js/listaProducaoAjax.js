/**
 * 
 */

function atualizaNomeLista(){
	var idLista= $("#idListaValor").val();
	var nomeNovaLista= $("#nomeLista").val();
	$.ajax({
		method : "POST",
		url : "atualizaNomeLista?idLista="+idLista+"&nomeNovaLista="+nomeNovaLista,
		success : function(data) {
			$("#colunaNomeLista").fadeIn(300).html(data);
			$("#ok").fadeIn(800);
		}
	});
}

function fee(){
	$("#feeNormal").prop('checked', false);
}

$(function(){
	$("#feeNormal").click(function(){
		$("#feeReduzido").prop('checked', false);
	});
	$("#feeReduzido").click(function(){
      $("#feeNormal").prop('checked', false);
	});	
})
