/**
 * 
 */
//ND
function editaND(idLista) {
	var valor = $("#editaND").is(':checked');
	$.ajax({
		url : "editaNd?idLista="+idLista+"&valor="+valor,
		success : function(data) {

			if(data == 'erro'){
				$('#wellErro').fadeIn(200);
			}
		}
	});	
};

