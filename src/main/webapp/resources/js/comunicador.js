function pegaComunicadoresPorIdContato(idContato){
	$.ajax({
        url: "ListaComunicadores?idContato="+idContato,
      	    success: function(data){
      	    $(".listacomunicadores").html(data);
      	    $("#ConcluiContato").removeAttr("disabled");
            }
   });
}	


/// ----------------  Edita comunicador de Contato Existente por Ajax --------------------------------------/////
function transformaColunaEmLinha(idComunicador, comunicador, contatos){
	var conteudo = "#"+idComunicador;
	var input = "<input  style='width: 260px;' id='dinamicoComunicador' type='text' value='"+ comunicador +"' onblur='editaCelulaComunicadorEditar("+idComunicador+")' />";
	$(conteudo).empty().html( input );	
};

function editaCelulaComunicadorEditar(idComunicador){
	
	var comunicador = $("#dinamicoComunicador").val();
	var idContato = $("#xrComunicador").val();

	$.ajax({
		    url: "editaCelulaComunicadorEditar?idComunicador="+idComunicador+"&comunicador="+comunicador+"&idContato="+idContato, // envia id do contato para action buscar as referencias desse contato
		    success: function(data){
		    $(".retornoComunicador").html(data);
		   
		    }
		});
		
}
//-----------------------------------------------------------------------------------////

/// ----------------  Edita comunicador de Novo Contato Sendo Criado por Ajax --------------------------------------/////
function clicou(idComunicador, comunicador, contatos){
	var conteudo = "#"+idComunicador;
	var input = "<input  style='width: 260px;' id='dinamicoComunicador' type='text' value='"+ comunicador +"' onblur='editaComunicador("+idComunicador+")' />";
	$(conteudo).empty().html( input );	
};

function editaComunicador(idComunicador){
	
	var comunicador = $("#dinamicoComunicador").val();
	var idContato = $("#xrComunicador").val();

	$.ajax({
		    url: "editaComunicador?idComunicador="+idComunicador+"&comunicador="+comunicador+"&idContato="+idContato, // envia id do contato para action buscar as referencias desse contato
		    success: function(data){
		    $(".retornoContato").html(data);
		   
		    }
		});
		
}
function apagaComunicador(idComunicador,idContato){
	
	$.ajax({
	    url: "apagaComunicador?idComunicador="+idComunicador+"&idContato="+idContato, // envia id do contato para action buscar as referencias desse contato
	    success: function(data){
	    	$(".retornoContato").html(data);
	    }
	});
	
}

//-----------------------------------------------------------------------------------////
// ---> Exibe a caixa de email e telefone para novo comunicador de contato
function contato(comunicador){
	if(comunicador == "telefone"){
		$("#OutrophoneContato").show(500);
		$("#OutroContatoEmail").css("display","none");
		
		$("#phoneContato").show(500);
		$("#ContatoEmail").css("display","none");
	}
	if(comunicador == "email"){
		$("#OutroContatoEmail").show(500);
		$("#OutrophoneContato").css("display","none");
		
		$("#ContatoEmail").show(500);
		$("#phoneContato").css("display","none");
		
	}	
	if(comunicador == "cancelar"){
		$("#OutrophoneContato").hide(500).attr("value"," ");
		$("#OutroContatoEmail").hide(500).attr("value"," ");
		
		$("#phoneContato").hide(500).attr("value"," ");
		$("#ContatoEmail").hide(500).attr("value"," ");
	}
	event.preventDefault();
}
/// ------------------------------------------------------------------------------ ////
function SalvaComunicadorNovo(tipo){
	
	var idEmp = $("#idEmNovo").val();
	var nome = escape($("#OutrocontatoNovo").val());
	
/*	var endereco = escape(document.getElementById('sNome').value);   
*/	
	
	
	var cargo = $("#OutrocargoNovo").val();
	var telefone = $("#Outrophone").val();
	var descricao = $("#OutrodescricaoNovo").val();
	var email = $("#OutroemailNovo").val();
	var idContatoTeste = $("#OutroidContato").val();

	
    $.ajax({
        url: "salvaNovoComunicador?idContato="+idContatoTeste+"&telefone="+telefone+"&comunicadorDesc="+descricao+"&email="+email, // envia id do contato para action buscar as referencias desse contato
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",    
        success: function(data){
      	    
      	    $(".retornoComunicador").html(data);
      	    $("input[name='telefoneNovo']").val("");
      	    $("#emailNovo").val("");
      	    $("#ConcluiContato").removeAttr("disabled");
        }
  
  });
};

/// ------------------------------------------------------------------------------////
function apagaComunicadorEditar(idComunicador,idContato){
	
	$.ajax({
	    url: "apagaComunicadorEditar?idComunicador="+idComunicador+"&idContato="+idContato, // envia id do contato para action buscar as referencias desse contato
	    success: function(data){
	    	$(".retornoComunicador").html(data);
	    }
	});
	
}
/// ------------------------------------------------------------------------------////

function editaContato(){

	var OutrocontatoNovo = $("#OutrocontatoNovo").val();
	
	if(OutrocontatoNovo == null || OutrocontatoNovo == "" || OutrocontatoNovo == " "){
		$("#OutrocontatoNovo").css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Nome Obrigatório.</span>");
		$(".errors").show();
		
		return false;
	}
	
	var url = location.href;
	var titulo = $("input[name='titulo']").val();
	
	
	if($("#novoHabilitado").is(':checked')){
		var novoHabilitado = true;
	}else{
		var novoHabilitado = false;
	}	
		
	
	var idEmp = $("#OutroidEmpNovo").val();
	var idContato = $("#OutroidContato").val();
	var OutrocontatoNovo = $("#OutrocontatoNovo").val();
	

	
	
	var OutrocargoNovo = $("#OutrocargoNovo").val();
	var novaObs = $("#novaObs").val();
	
	$.ajax({
		url: "editaContato?OutroidEmpNovo="+idEmp+"&novoHabilitado="+novoHabilitado+"&idContato="+idContato+"&OutroContatoNovo="+OutrocontatoNovo+"&OutrocargoNovo="+OutrocargoNovo+"&novaObs="+novaObs,
		contentType: "application/x-www-form-urlencoded;charset=UTF-8", 
		sucess: function(data){
		},
		beforeSend: function(){
	        $('#editarContato').text("Editando ...").attr("disabled","disabled");
        },
        complete: function(){
	        $('#editarContato').removeAttr("disabled").text("Editar Contato");
				if(url.indexOf("/job")==-1) {
					$("#novaObs").parent().append("<span class='sucess'><i class='glyphicon glyphicon-ok' style='top: 2px;padding-right: 7px;'></i>&nbspContato atualizado.</span>");
					location.reload();	
				} else {
					$("#novaObs").parent().append("<span class='sucess'><i class='glyphicon glyphicon-ok' style='top: 2px;padding-right: 7px;'></i>&nbspContato atualizado. Feche e selecione o cliente novamente</span>");
				}
		}
	
	});
	
};
	
function ativaDesativaComunicador(){
	if($("#checarAtivo").is(':checked')){
		$("#novoHabilitado").attr("checked",false);
	}else{
		$("#novoHabilitado").attr("checked",true);
	}	
}






	