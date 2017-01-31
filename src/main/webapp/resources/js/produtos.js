/**
 * Javascript relacionado com produtos
 */


// ------------------------- msg de confirmarcao ----------------------------- //

function emailSucesso(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-sucesso-email").slideToggle("slow"); 
	    }, 4000);
	});
}

function emailErro(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-erro-email").slideToggle("slow"); 
	    }, 10000);
	});
}

// -------------------------------------------------------------------------- //



function insereNovoProduto(pegaAction) {
	$("#novoProduto").fadeIn(250);
	$("#salvandoProduto").attr("action",pegaAction);
}

function verificaProduto(){
	var produto = $("#nomeProduto").val();
	
$.ajax({
		method : "POST",
		url : "verificaProduto?produto=" + produto,
		success : function(data) {
			if(data == 1){
				
				$("#nomeProduto").css("border","4px solid red");
				$("#verifica").fadeIn(500);
				$("#jaExiste").toggle(500);
				$("#naoExiste").fadeOut(500);
				$("#cadastraNovoProduto").addClass("is-disabled");
				
			}else{
				$("#nomeProduto").css("border","2px solid green");
				$("#verifica").fadeIn(500);
				$("#jaExiste").fadeOut(500);
				$("#naoExiste").toggle(500);
				$("#cadastraNovoProduto").removeClass("is-disabled");
			}
		}
	});
}