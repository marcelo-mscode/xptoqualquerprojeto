/**
 * 
 */
/**
 * 
 */
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

function procuraContatoProspeccao(id) {
	$.ajax({
		url : "buscaContatoProspeccao?idEmpresa=" + id, // envia id do contato para action
										// buscar as referencias desse contato
		success : function(data) {
			$('.info-contato').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

};