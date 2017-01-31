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

function editaFornecedor() {
	$("#editaClienteProspect").fadeIn(250);
}

function verificaFornecedor(){
	var empresa = $("#nomeEmpresa").val();
	
$.ajax({
		method : "POST",
		url : "verificaEmpresa?empresa=" + empresa,
		success : function(data) {
			if(data == 1){
				alert();
				$("#nomeEmpresa").css("border","4px solid red");
				$("#verifica").fadeIn(500);
				$("#jaExisteFornecedor").toggle(500);
				$("#naoExisteFornecedor").fadeOut(500);
				$("#salvaFornecedorLista").addClass("is-disabled");
				
			}else{
				$("#nomeEmpresa").css("border","2px solid green");
				$("#verifica").fadeIn(500);
				$("#jaExisteFornecedor").fadeOut(500);
				$("#naoExisteFornecedor").toggle(500);
				$("#salvaFornecedorLista").removeClass("is-disabled");
			}
		}
	});
}

$(document).ready(function(){
	$('#salvaEmpresaLista').validate({
		rules: {
			empresa: { required: true, minlength: 2 },
			RazaoSocial: { required: true, minlength: 2 },
			cnpj: { required: true }
		},
		messages: {
			empresa: { required: 'Preencha o campo empresa', minlength: 'No mínimo 2 letras' },
			RazaoSocial: { required: 'Preencha o campo Razão Social', minlength: 'No mínimo 2 letras' },
			cnpj: { required: 'Preencha o CNPJ' }

		},
		submitHandler: function( form ){
			var dados = $( form ).serialize();

			$.ajax({
				type: "POST",
				url: "salvaEmpresaLista",
				data: dados,
				success: function( data )
				{   
					
					$("#salvaFornecedorLista").attr("disabled", true);
					$("#salvou").fadeIn(500);
					$("#fechaFornecedorLista").show(1000);
					$("#empresaFornecedor").html(data);
				}
			});

			return false;
		}
	});
});


