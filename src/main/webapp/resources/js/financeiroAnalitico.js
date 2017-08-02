function editaCamposFinanceiro(desc,valor1,action, idAnalitico,idDivAjax) {
	
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	
	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&valor="+valor+"&descricao="+descricao,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

function editaCamposFinanceiroDespesas(data,desc,valor1,action, idAnalitico,idDivAjax) {
	
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var datas = $("#"+data).val();
	
	if(datas == '' || datas ==' ' || datas == null){
		$("#"+data).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}

	
	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores 
function editaCamposAnalitico(action,campo,idTabela,tipoCampo,idDivAjax) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");
	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores Despesas
function editaCamposAnaliticoDespesas(action,campo,idTabela,tipoCampo,idDivAjax) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");
	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

// ---------------------------------------------------------------------------------------- //
// MOVIMENTO FINANCEIRO
function InsereDadosMovimentacao(idBanco,ndnf, data,desc,valor1,action, idAnalitico,idDivAjax) {
	
	alert("Cheguei ");
	
	/*var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var datas = $("#"+data).val();
	var ndnf1 = $("#"+ndnf).val();

	
	
	alert(action+"?idBanco="+idBanco+"&idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&ndnf="+$("#"+ndnf).val());
	*/
	
	
	/*if(datas == '' || datas ==' ' || datas == null){
		$("#"+data).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	
	$.ajax({
		url : action+"?idBanco="+idBanco+"&idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&ndnf="+$("#"+ndnf).val(),
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});*/
};

//Edita Valores Entradas
function editaValoresEntradasItau(action,campo,idTabela,tipoCampo,idDivAjax) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");

	console.log()
	
	
	/*$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});*/
};









