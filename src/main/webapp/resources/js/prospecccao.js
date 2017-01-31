/**
 * 
 */

$(document).ready(function(){
	
	$('#filtraProspeccao').validate({
		
		//Campos com o name preenchido
		rules: { 
			pEntrega: { required: true},
		},
		//Mensagens que aparecerao abaixo dos names
		messages: {
			pEntrega: { required: 'Preencha a data de entrega'}
		},
		submitHandler: function( form ){
			var dados = $( form ).serialize();
		//envio do formulario por ajax
			$.ajax({
				type: "POST",
				url: "filtraProspeccao",
				data: dados,
				before: $("#loader-confirmacao").fadeIn(500),
				success: function( data )
				{   
					$("#prospeccaoFiltro").html(data);
					$("#loader-confirmacao").fadeOut(800);	
				},error: function (error) {

	            }
			});
			return false;
		}
	});
});





