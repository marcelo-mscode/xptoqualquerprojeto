function alertaND(idLista){
	
	$("#idListaGeraND").attr('value',idLista);
	$("#editaNDModel").fadeIn(500);
};


/**
 * 
 */
//ND
function editaND() {
	var idLista = $("#idListaGeraND").val();
	
	$("#editaND").attr("disabled","disabled");

	$.ajax({
		url : "editaNd?idLista="+idLista,
		success : function(data) {
			
			if(data == "ok"){
				location.reload();
			}
			if(data == 'erro'){
				$('#wellErro').fadeIn(200);
			}
		}
	});
};

