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
// Entrada de valores
function InsereDadosMovimentacao(ndnf, data,desc,valor1,action, idAnalitico,idDivAjax,idBanco) {
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var datas = $("#"+data).val();
	var ndnf1 = $("#"+ndnf).val();
	
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
		url : action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&ndnf="+$("#"+ndnf).val()+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores Entradas
function editaValoresEntradas(action,campo,idTabela,tipoCampo,idDivAjax,idBanco) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");

	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};


//Saida de valores
function insereDadosMovimentacaoSaidas(data,desc,valor1,action, idAnalitico,idDivAjax,idBanco) {
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
		url : action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores Saidas
function editaValoresSaidas(action,campo,idTabela,tipoCampo,idDivAjax,idBanco) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");

	
	console.log(action);
	console.log(campo);
	console.log(idTabela);
	console.log(tipoCampo);
	console.log(idDivAjax);
	console.log(idBanco);
	
	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Saldos em movimento financeiros Bancos
function editaSaldos(campo,idAnalitico,tipoCampo,idBanco) {
	
	var valor = $("#"+campo).val();
	
	console.log(campo);
	console.log(valor);
	console.log(idAnalitico);
	console.log(tipoCampo);
	console.log(idBanco);
	
	$.ajax({
		url : "editaSaldosBancos?valor="+valor+"&idAnalitico="+idAnalitico+"&tipoCampo="+tipoCampo+"&idBanco="+idBanco,
		success : function(data) {
			/*$("#"+idDivAjax).html(data);*/
		}
	});
};




