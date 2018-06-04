function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}
function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}
function mcep(v){
    v=v.replace(/\D/g,"")                    //Remove tudo o que não é dígito
    v=v.replace(/^(\d{2})(\d)/,"$1.$2")      //Coloca um "." entre dois digitos
    return v
}


//------------------------------------------------------------------------------------ // 
$("#salvaCategoria").submit(function() {
	var salvaCategoriaCategoria = $("#salvaCategoriaCategoria").val();

	if(seFalse($("#salvaCategoriaCategoria"),salvaCategoriaCategoria) == false){return false;}
		
	return true;
});

$("#impostoValida").submit(function() {
	var impostoTitulo = $("#testeNameImposto").val();
	var imposto = $("#testeImposto").val();

	if(seFalse($("#testeNameImposto"),impostoTitulo) == false){return false;}
	if(seFalse($("#testeImposto"),imposto)== false){return false;}
		
	return true;
});


$("#salvaEmpresa").submit(function() {
	var nomeEmpresa = $("#nomeEmpresa").val();
	var razaoSocial = $("#razaoSocial").val();
	var cnpj = $("#cnpj").val();
//	var telefoneEmpresa = $("#telefoneEmpresa").val();

	if(seFalse($("#nomeEmpresa"),nomeEmpresa) == false){return false;}
	if(seFalse($("#razaoSocial"),razaoSocial)== false){return false;}
	if(seFalse($("#cnpj"),cnpj)== false){return false;}
//	if(seFalse($("#telefoneEmpresa"),telefoneEmpresa)== false){return false;}
	return true;
});

$("#formEmpresaFornecedor").submit(function() {
	
	var nomeEmpresa = $("#nomeEmpresa").val();
	var razaoSocial = $("#razaoSocial").val();
	var cnpj = $("#cnpj").val();
//	var telefoneEmpresa = $("#telefoneEmpresa").val();

	if(seFalse($("#nomeEmpresa"),nomeEmpresa) == false){return false;}
	if(seFalse($("#razaoSocial"),razaoSocial)== false){return false;}
	if(seFalse($("#cnpj"),cnpj)== false){return false;}
//	if(seFalse($("#telefoneEmpresa"),telefoneEmpresa)== false){return false;}
	
	
	if($("#tipoFornecedor").is(':checked') == true){
		var fornecedorTipoPagamento = $("#fornecedorTipoPagamento").val();
		if(seFalse($("#fornecedorTipoPagamento"),fornecedorTipoPagamento) == false){return false;}
    }
	
});


$("#salvaNovaTag").submit(function() {
	var novaTag = $("#novaTag").val();
	if(seFalse($("#novaTag"),novaTag) == false){return false;}
	return true;
});


$("#salvaMarca").submit(function() {
	var novaMarca = $("#novaMarca").val();
	if(seFalse($("#novaMarca"),novaMarca) == false){return false;}
	return true;
});

$("#salvaImposto").submit(function() {
	var impostoTitulo = $("#impostoTitulo").val();
	var imposto = $("#imposto").val();
	
	
	if(seFalse($("#impostoTitulo"),impostoTitulo) == false){return false;}
	if(seFalse($("#imposto"),imposto) == false){return false;}
	
	return true;
});

$("#cadastraLista").submit(function() {

	var tituloLista = $("#tituloLista").val();
	var selectLista = $("#selectLista").val();
	var selectJobsEmpresa = $("#selectJobsEmpresa").val();

	if(seFalse($("#tituloLista"),tituloLista) == false){return false;}
	if(seFalse($("#selectLista"),selectLista) == false){return false;}
	if(seFalse($("#selectJobsEmpresa"),selectJobsEmpresa) == false){return false;}

	
	return true;
});


function verificaTituloemNovaLista(idBotao){
		$(idBotao).removeAttr("disabled");
}


/* ----------------------------------------------------------------------------------*/
//Formulário de item
//Salvar novo item
$("#cadastraNovoItem").submit(function() {
	var produtoAtuacao = $("#produtoAtuacao").val();
	var empresaFornecedor = $("#empresaFornecedor").val();
	if(seFalseItem($("#produtoAtuacao"),produtoAtuacao) == false){return false;}
	if($("#empresaFornecedor").val() == "SelecioneFornecedor"){
		$("#empresaFornecedor").css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Selecione um Fornecedor.</span>");
		$(".errors").show();
		return false;
	}
	return true;
});

//Atualiza Status do Job
$("#cadastraStatus").submit(function() {
	var produtoAtuacao = $("#produto2").val();
	if($("#produto2").val() == "selecione"){
		$("#produto2").css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Selecione o Produtor 2.</span>");
		$(".errors").show();
		return false;
	}
	return true;
});

// Salva Novo Local do Evento em Job
$("#evento").submit(function() {
	
	var localEventoNome = $("#localEventoNome").val();
	
	if( localEventoNome  == "" || localEventoNome  == null || localEventoNome  == " "){
		$("#localEventoNome").css("border","2px solid red")
   	    .parent().append("<span class='errors'> *Insira o nome do local do Evento.</span>");
		$(".errors").show();
		return false;
	}
	return true;
});






/* ----------------------------------------------------------------------------------*/
//Formulário de item
//Atualizar
function salvaEfechar(boolean){
	$("#salvarEfechar").val(boolean);
	var custoItemunitario = $("#custoItemunitario").val();
	var bvFornecedorValorEdita = $("#bvFornecedorValorEdita").val();
	
	$("#atualizaDetalhesItem").submit(function(){
			/*if($("#custoItemunitario").val() == "0,00" || $("#custoItemunitario").val() == null || $("#custoItemunitario").val() == "" || $("#custoItemunitario").val() == "0"){
				if($("#bvFornecedorValorEdita").val() == "0,00" || $("#bvFornecedorValorEdita").val() == null || $("#bvFornecedorValorEdita").val() == ""){
					$("#custoItemunitario").css("border","2px solid red");
					$("#bvFornecedorValorEdita").css("border","2px solid red");
					$("#camposUnitarios").show(100);
					return false;
				}	
				return true;
		}*/
	});
}

 /*  -----------------------------------------------------------------------------    */
/* ----------------------------------------------------------------------------------*/
//Formulário de Salvar Produto
//Atualizar
$("#salvandoProduto").submit(function() {
	
	var nomeProduto = $("#nomeProduto").val();
	var unidadeProduto = $("#unidadeProduto").val();
	var generoProduto = $("#generoProduto").val();
	
	if( nomeProduto  == "" || nomeProduto  == null || nomeProduto  == " "){
		msg("#nomeProduto");
		return false;
	}

	if( unidadeProduto  == "SelecioneUnidade"){
		msg("#unidadeProduto");
		return false;
	}
	if( generoProduto  == "selecioneGenero"){
		msg("#generoProduto");
		return false;
	}
	
	return true;
});

function msg(item){
	
	$(item).css("border","2px solid red")
	    .parent().append("<span class='errors'> *Item obrigatório.</span>");
	$(".errors").show();
	
	
}



/*  -----------------------------------------------------------------------------    */








$("#salvaGrupoLista").submit(function() {
	var salvatituloLista = $("#salvatituloLista").val();
	if(seFalse($("#salvatituloLista"),salvatituloLista) == false){return false;}
	return true;
});

function verificaCampo(){
	var custoItemunitario = $("#custoItemunitario").val();
	if(custoItemunitario =="" || custoItemunitario == null || custoItemunitario == "0,00"){
		$("#custoItemunitario").css("border","2px solid red")
   	    .parent().append("<span class='errors'><br />*Campo Obrigatório.</span>");
		$(".errors").show();
		return false
	}else{
		$(".errors").remove();
		$("#custoItemunitario").css("border","1px solid #ccc");
	}
}

/*  -----------------------------------------------------------------------------    */
function seVazio(campoInput,campoParaComparar){
	if(campoParaComparar == " " || campoParaComparar == null || campoParaComparar == ""){
		(campoInput).css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Campo Obrigatório.</span>");
		$(".errors").show();
		return false}
	else{return true}
}
function seFalse(idCampo, valor){
	if(seVazio(idCampo,valor) == false){
		return false;}
}




function seItemNaoSelecionadoVazio(campoInput,campoParaComparar){
	if(campoParaComparar == "" || campoParaComparar == null ){
		(campoInput).css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Selecione um Item.</span>");
		$(".errors").show();
		return false}
	
	else{return true}
}



function seFalseItem(idCampo, valor){
	if(seItemNaoSelecionadoVazio(idCampo,valor) == false){
		return false;}
}


//---------------------------------------------------------------------------- // 




