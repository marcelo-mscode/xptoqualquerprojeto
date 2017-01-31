/**
 * 
 */

$(document).ready(function(){
	$('#filtraInteracoes').validate({
		
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
				url: "filtraInteracao",
				data: dados,
				before: $("#loader-confirmacao").fadeIn(500),
				success: function( data )
				{   
					$("#interacoesFiltro").html(data);
					$("#loader-confirmacao").fadeOut(800);	
					preencheTitulo();
				},error: function (error) {
/*					$("#tableParcelas").css("border","2px solid red");
					$("#tableParcelas").css("opacity","0.8");//define opacidade inicial
*/	              }
			});
			return false;
		}
	});
});


function preencheTitulo() {
	
		$.ajax({
			method : "POST",
			url : "preencheTitulo",
			success : function(data) {
				$("#preencheTitulo").fadeIn(300).html(data);
			}
		});
};













