/**
 * Arquivo para consultas de JOB
 */


function distribuiTarefas(){
		
		var idEmpresa = $("#selectEmpresa").val();
		var selectJob = $("#selectJob").val();
		var selectEstatus = $("#selectEstatus").val();
		

	if(    $("#inputEmpresa").is(':checked') == true // Job da Empresa
		&& $("#inputJob")    .is(':checked') == false
  	    && $("#inputStatus") .is(':checked') == false
  	    && idEmpresa != null ){
			consultaJobPorEmpresa();	
			
    }else
   
   	if(    $("#inputEmpresa").is(':checked') == true // Único Job
   		&& $("#inputJob")    .is(':checked') == true
        && $("#inputStatus") .is(':checked') == false
        && idEmpresa != null
        && selectJob != null ) {
	       consultaJobPorIdJob();
			
	}else
		
	if(    $("#inputEmpresa").is(':checked') == true // Jobs da Empresa com Condições
		&& $("#inputJob")    .is(':checked') == true
        && $("#inputStatus") .is(':checked') == true
        && idEmpresa != null
        && selectEstatus != null
       
        ||
        
        	$("#inputEmpresa").is(':checked') == true // Jobs da Empresa com Condições
		&& $("#inputJob")     .is(':checked') == false
        && $("#inputStatus")  .is(':checked') == true
        && idEmpresa != null
        && selectEstatus != null
        
	) { consultaJobPorStatus(); }		
	
	else
		
	if(    $("#inputEmpresa").is(':checked') == false // Jobs da Empresa com Condições
		&& $("#inputJob")    .is(':checked') == false
        && $("#inputStatus") .is(':checked') == true
        && idEmpresa != null || idEmpresa == null
        && selectEstatus != null
	        
	) {	consultaTodosJobPorStatus(); }
		
		
	if(
		$("#inputEmpresa")   .is(':checked') == false // Voltar todos os Jobs
		&& $("#inputJob")    .is(':checked') == false
        && $("#inputStatus") .is(':checked') == false	
        
        ||
        
        $("#inputEmpresa")   .is(':checked') == false
		&& $("#inputJob")    .is(':checked') == true
        && $("#inputStatus") .is(':checked') == false
		){
		
		location.reload();
	//	alert("Selecione algum item para Filtrar");
	}
	

}



function consultaJobPorEmpresa(){
	var idEmpresa = $("#selectEmpresa").val();
		$.ajax({
		    method: "POST",
		    url: "ConsultaPorEmpresa?idEmpresa="+idEmpresa,
		    success: function(data) {
		    $("#listaJobAjax").fadeIn(300).html(data);
		    $('#loader-lista').fadeOut(1000);
		    },
		    beforeSend: function(){
		        $('#loader-lista').fadeIn(500).css({display:"inline"});
		      },
		      complete: function(){
		        $('#loader-lista').fadeOut(1000);
		        consultPorIdJob(idEmpresa);
		      }
	    });
}

function consultPorIdJob(idEmpresa){
	
	$.ajax({
    method: "POST",
    url: "ConsultaPorEmpresaComJob?idEmpresa="+idEmpresa,
    success: function(data) {
    $("#selectJob").fadeIn(300).html(data);
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




function consultaJobPorIdJob(idJob){
	
	var idJob = $("#selectJob").val();
	
	$.ajax({
	    method: "POST",
	    url: "ConsultaPorJob?idJob="+idJob,
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

function tiraVirgula(str){
	
	str+= str.toString().replace(",", "");
	return str;
}

function consultaJobPorStatus(){
	
	
	
	var i = montaArrayStatus();
	
	var teste = $("#selectEstatus").val() || [];
	var idEmpresa = $("#selectEmpresa").val();

	$.ajax({
	    method: "POST",
	    url: "ConsultaPorStatus?teste="+i+"&idEmpresa="+idEmpresa,
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

function consultaTodosJobPorStatus(){
	
	var i = montaArrayStatus();
	
	$.ajax({
	    method: "POST",
	    url: "ConsultaTodosPorStatus?teste="+i,
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

function classificarPor(valor){
	
	var ordem = $("#selectOrdem").val();
	
	if( // ----------------------------------------------- Fechou ------------------------------ //
			$("#inputEmpresa")   .is(':checked') == true
	        && $("#inputStatus") .is(':checked') == false	
	        
	){      jobsempresa(ordem)	} // --------------------- Fechou ------------------------------ //
		
		else 
	
	if(
			$("#inputEmpresa")   .is(':checked') == true
	        && $("#inputStatus") .is(':checked') == true	
		
		){  JobsEmpresaCondicao3(ordem)	}
		
	else 
		
		if( // ----------------------------------------------- Fechou ------------------------------ //
				$("#inputEmpresa")   .is(':checked') == false
		        && $("#inputStatus") .is(':checked') == true	
			
		){ todosJobsCondicao3(ordem)	} // ----------------- Fechou ------------------------------ //

	else 
		
		if(
				$("#inputEmpresa")   .is(':checked') == false
		        && $("#inputStatus") .is(':checked') == false	
			
		){ todosJobs(ordem)	}
	
}

function todosJobsCondicao3(ordem){ // ------------------- Fechou -------------------------------- //
	
	var i = montaArrayStatus();
	var valorPor = valorPorClassifica();
	var url =	"ClassificaTodosPorStatus?teste="+i+"&valorPor="+valorPor;
	ajaxApoio(ordem,url);
}




function jobsempresa(ordem){ // ------------------- Fechou -------------------------------- //
	
	if($("#selectEmpresa").val() == null){
			alert("Selecion um cliente na lista.")
	}else {
		var idEmpresa = $("#selectEmpresa").val();
		var valorPor = valorPorClassifica();
		var url =	"classificaPorEmpresa?idEmpresa="+ idEmpresa +"&valorPor="+ valorPor;
		ajaxApoio(ordem,url);
	}
}


function JobsEmpresaCondicao3(ordem){

	if($("#selectEmpresa").val() == null){
			alert("Selecion um cliente na lista.")
	}else {
		var idEmpresa = $("#selectEmpresa").val();
		
		var valorPor = valorPorClassifica();
		
		var i = montaArrayStatus();
		
		var url =	"classificaPorEmpresaComCondicao?teste="+i+"&idEmpresa="+ idEmpresa +"&valorPor="+ valorPor;
		
		ajaxApoio(ordem,url);
	}
	
}



function todosJobs(ordem){
	
	var valorPor = valorPorClassifica();
	var i = montaArrayStatus();
	var url =	"classificaTodosJobs?valorPor="+ valorPor;
	ajaxApoio(ordem,url);

}

function relatorio(ordem){
	var tipo = "relatorio";
		
//	var valorPor = valorPorClassifica();
	var i = montaArrayStatus();
	var url =	"relatorio?tipo="+ tipo;
	ajaxApoio(ordem,url);

}

function imprimeRelatorio(ordem){
	var tipo = "print";
	
//	var valorPor = valorPorClassifica();
	var i = montaArrayStatus();
	var url =	"relatorio?tipo="+ tipo;
	ajaxApoio(ordem,url);
}
























