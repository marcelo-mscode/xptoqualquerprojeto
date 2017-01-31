/**
 * 
 *//*
function editaFornecedor() {

	var empresa = $("#sempresaFornecedor").val();
	
	if(empresa == "selecione"){
		alert("Selecione um Cliente para editar");
	}
	
	
	
	$.ajax({
		method : "POST",
		url : "carregaCliente?idEmpresa=" + empresa,
		success : function(data) {
			
			$("#editaClienteProspect").html(data);
			$("#editaClienteProspect").fadeIn(250);
		}
	});
}

$(document).ready(function(){
	$('#atualizaEmpProspec').validate({
		rules: {
			empresa: { required: true, minlength: 2 },
			RazaoSocial: { required: true, minlength: 2 }
		},
		messages: {
			empresa: { required: 'Preencha o campo empresa', minlength: 'No mínimo 2 letras' },
			RazaoSocial: { required: 'Preencha o campo Razão Social', minlength: 'No mínimo 2 letras' }
			
		},
		submitHandler: function( form ){
			var dados = $( form ).serialize();
			
			$.ajax({
				type: "POST",
				url: "atualizaEmpresaProspeccao",
				data: dados,
				success: function( data )
				{   
					alert();
					$("#ssteste").html(data);
					$("#ssalvaFornecedorLista").attr("disabled", true);
					$("#sssalvou").fadeIn(500);
					$("#ssfechaFornecedorLista").show(1000);
				}
			});
			
			return false;
		}
	});
});
*/