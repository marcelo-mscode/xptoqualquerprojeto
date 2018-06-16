/**
 * Todo código javascript relaciona com Empresa
 * 
 */
//---------------- Busca Lista de Empresa por nome ------------------ //
/*$( "#target" ).keyup(function() {
	var nome = $("#target").val();
	var idEvento = $("#idEvento").val();
	  $.ajax({
			url : "busca?nome=" + nome,
			success : function(data) {
			$("#clientesAjax").fadeIn(300).html(data);
			},
			beforeSend : function() {
				$('#loader-lista').fadeIn(1500);
				$("#target").prop("disabled",true);
			},
			complete : function() {
				$('#loader-lista').fadeOut(200);
				$('#nextConsulta').fadeOut(200);
				$("#target").prop("disabled",false);
			}
		});
});*/

function buscaClientesNova() {
	var nome = $("#buscaEmpresaPorEmpresa").val();
		  $.ajax({
			url : "busca?nome=" + nome,
			success : function(data) {
			$("#clientesAjax").fadeIn(300).html(data);
			},
			beforeSend : function() {
				$('#loader-lista').fadeIn(500);
				$("#buscaEmpresaPorEmpresa").prop("disabled",true);
			},
			complete : function() {
				$('#loader-lista').fadeOut(200);
				$('#nextConsulta').fadeOut(200);
				$("#buscaEmpresaPorEmpresa").prop("disabled",false);
			}
		});
}






// ------------------------------------------------------------------- //
function buscaClientes(){
	var n = todos();
		
	if(n == 0){
		urlAux("busca?nome=", $('#nextConsulta').fadeIn(500));
	}else{
		var c = "buscaClientes?tipo="+n;
		urlAux(c ,$('#nextConsulta').fadeOut(500));
	}
}

function todos(){
	    var checkbox = $('input:checkbox[name^=mcheckbox]:checked');
	    if(checkbox.length > 0){
	        var val = [];
	        checkbox.each(function(){
	            val.push($(this).val());
	        });
	    }else{
	    	return 0;
	    } 
	    
	    var num = 0;
	    for(i=0; i < val.length; i++){
	    	num += Number(val[i]);
	   	}
	    return num;
}


function urlAux(action,efeito){
	$.ajax({
		url : action,
		success : function(data) {
		$("#clientesAjax").fadeIn(300).html(data);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(1500);
		},
		complete : function() {
			$('#loader-lista').fadeOut(500);
			efeito;
		}
	});
}

function listagemEmpresasConsultas(action,efeito){
	$.ajax({
		url : action,
		success : function(data) {
		$("#clientesAjax").fadeIn(300).html(data);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(1500);
		},
		complete : function() {
			$('#loader-lista').fadeOut(500);
			efeito;
		}
	});
}

function buscaListagemEmpresasConsultas(){
	var n = todos();
		
	if(n == 0){
		
		location.reload();
		
	}else{
		
		$.ajax({
			url : "buscaListagemEmpresasConsultas?tipo="+n,
			success : function(data) {
			$("#listagemEmpresasConsulta").fadeIn(800).html(data);
			$("#listagemDemorada").fadeOut(300);
			},
			beforeSend : function() {
				$('#listagemEmpresasConsulta').fadeOut(100);
				$('#listagemEmpresasCarregando').fadeIn(500);

				if(n == 4){
					$("#listagemDemorada").fadeIn(300);
				}
				
			},
			complete : function() {
				$('#listagemEmpresasCarregando').fadeOut(500);
			}
		});
	}
}

function exportaExcelEmpresas() {
	
	var n = todos();
	
	if(n == 0){
		
		location.reload();
		
	}else{

		$("#planilhaExcelEmpresas")
		.text("Gerando Listagem ...")
		.css("pointer-events", "none")
		.prepend(
		"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
		
		if(n == 4){
			$("#listagemDemorada").fadeIn(300);
		}
		
		
		$.ajax({
			url : "geraExcelEmpresas?tipo="+n,
			success : function(data) {
				
				$("#geraExcel").fadeIn(300).html(data);
				$("#listagemDemorada").fadeOut(300);
			}
		});
	}
}












