/**
 * 
 */
function tiraLightbox(){
	$("#novoFornecedor").hide('slow');
	$("#editaClienteProspect").hide('slow');
}


function insereNovoFornecedor() {
	$("#novoFornecedor").fadeIn(250);
}

/*function editaFornecedor() {

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
}*/

function verificaFornecedor(){
	var empresa = $("#nomeDaEmpresa").val();
	
$.ajax({
		method : "POST",
		url : "verificaEmpresa?empresa=" + empresa,
		success : function(data) {
			if(data == 1){
				$("#nomeDaEmpresa").css("border","4px solid red");
				$("#verifica").fadeIn(500);
				$("#sjaExisteFornecedor").toggle(500);
				$("#snaoExisteFornecedor").fadeOut(500);
				$("#salvaFornecedorLista").addClass("is-disabled");
				
			}else{
				$("#nomeDaEmpresa").css("border","2px solid green");
				$("#verifica").fadeIn(500);
				$("#sjaExisteFornecedor").fadeOut(500);
				$("#snaoExisteFornecedor").toggle(500);
				$("#ssalvaFornecedorLista").removeClass("is-disabled");
			}
		}
	});
}

$(document).ready(function(){
	$('#ssalvaEmpresaLista').validate({
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
				url: "salvaEmpresaNovaProspeccao",
				data: dados,
				success: function( data )
				{   
					$("#ssalvaFornecedorLista").attr("disabled", true);
					$("#ssalvou").fadeIn(500);
					$("#sfechaFornecedorLista").show(1000);
					$("#sempresaFornecedor").html(data);
				}
			});

			return false;
		}
	});
});



/*$(document).ready(function(){
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

