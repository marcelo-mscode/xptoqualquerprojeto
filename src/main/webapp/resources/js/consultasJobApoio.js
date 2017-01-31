/* Funções de apoio para a consulta da listagem de Jobs  *
 * 
 */

function ajaxApoio(ordem, url){
	
	$.ajax({
	    method: "POST",
	    url: url,
	    success: function(data) {
	    $("#listaJobAjax").fadeIn(300).html(data);
	    $('#loader-lista').fadeOut(1000);
	    },
	    beforeSend: function(){
	        $('#loader-lista').fadeIn(500).css({display:"inline"});
	      },
	      complete: function(){
	        $('#loader-lista').fadeOut(1000);
	      }
    });
	 
}


function montaArrayStatus(){
	var i="";
	var hexvalues = [];
	$('#selectEstatus :selected').each(function(i, selectedElement) {
	 hexvalues[i] = $(selectedElement).val();
	});
	var i = hexvalues;
	return i;
}

function valorPorClassifica(){
	
	var selectPor = $("#selectPor").val();
	var valorPor ="";
	var ordem = $("#selectOrdem").val();
	
	if(selectPor == "0"){
		valorPor = "propostaData"; 
	}else
		if(selectPor == "1"){
			valorPor = "codJob";
	}else 
		if(selectPor == "2"){
			valorPor = "j.contatos.contato";
	}else 
		if(selectPor == "3"){
			valorPor = "titulo";
	}else 
		if(selectPor == "4"){
			valorPor = "j.idStatusAtual.idStatus.estatus";
	}else 
		if(selectPor == "5"){
			valorPor = "j.empresa.empresa";
	}
	
	valorPor = valorPor + " " +ordem;
	return valorPor;
}