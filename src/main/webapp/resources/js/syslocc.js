$(document)
		.ready(
				function() {

					/*$("html").niceScroll();*/

					$(".real").maskMoney({
						symbol : "R$",
						decimal : ",",
						thousands : "."
					});

					$(".pop").focus(function() {

						$(this).popover('show');

					});

					$(".pop").blur(function() {

						$(this).popover('hide');

					});

					// ---- Plugin de ancora para o top --//
					$('body')
							.append(
									'<div id="toTop" class="btn btn-info"><span class="glyphicon glyphicon-chevron-up" style="top: 2px; margin-right: 5px;"></span>Voltar para o topo</div>');
					$(window).scroll(function() {
						if ($(this).scrollTop() != 0) {
							$('#toTop').fadeIn();
						} else {
							$('#toTop').fadeOut();
						}
					});
					$('#toTop').click(function() {
						$("html, body").animate({
							scrollTop : 0
						}, 900);
						return false;
					});
					// ---- Fim ancora para o top --//

				});

// ------- Fim Popver ---------//
/*
 * $(document).bind('keydown',function(e){ if(e.which == 8 ){ return false; }
 * else{ return true; }; });
 */

// -------Calendário --------//
$(document)
		.ready(
				function() {
					$(".data")
							.datepicker(
									{
										dateFormat : 'dd/mm/yy',
										dayNames : [ 'Domingo', 'Segunda',
												'Terça', 'Quarta', 'Quinta',
												'Sexta', 'Sábado' ],
										dayNamesMin : [ 'D', 'S', 'T', 'Q',
												'Q', 'S', 'S', 'D' ],
										dayNamesShort : [ 'Dom', 'Seg', 'Ter',
												'Qua', 'Qui', 'Sex', 'Sáb',
												'Dom' ],
										monthNames : [ 'Janeiro', 'Fevereiro',
												'Março', 'Abril', 'Maio',
												'Junho', 'Julho', 'Agosto',
												'Setembro', 'Outubro',
												'Novembro', 'Dezembro' ],
										monthNamesShort : [ 'Jan', 'Fev',
												'Mar', 'Abr', 'Mai', 'Jun',
												'Jul', 'Ago', 'Set', 'Out',
												'Nov', 'Dez' ],
										nextText : 'Próximo',
										prevText : 'Anterior'

									});

				});
// --------fim calendario ----- //

/*
 * $( "#interacao" ).click(function() { $( "#formInteracao" ).toggle(100); });
 */
$("#interacao").click(function() {
	$(".interacao").toggle(100);
});

$("#prospectInteracao").click(function() {
	$(".formInteracaoClass").toggle(100);
});

$("#anexo").click(function() {
	$(".anexo").toggle(100);
});

$("#chamaEstrategia").click(function() {
	$("#estrategia").toggle(100);
});

$("#chamaEvento").click(function() {
	$("#evento").toggle(100);
});

$("#chamaDemanda").click(function() {
	$("#formDemanda").toggle(100);
});

$("#Cadeiras").click(function() {
	$(".cadeiras").toggle(300);
});

$("#Cadeiras2").click(function() {
	$(".cadeiras").toggle(300);
});

$("#Cadeiras3").click(function() {
	$(".cadeiras").toggle(300);
});

$("#tiraLinha").click(function() {
	$(".linha").toggle(300);
});

/*
 * ------------------- Exibir Nova Linha em Lista de Produção
 * -----------------------
 */

function insereLinha(item, idItem2) {
	var idItem = item;
	var idItem2 = idItem2;

	$(idItem).toggle(500);
	$("#novaLinhaEfeito" + idItem2).toggle(500);
}

function valorFechadoLinha() {
	$("#valorFechadoLinha").toggle(200);
}

/* ---------------------------------------------------------------------------------- */

$("#TiraCategoria").click(function() {
	$(".categoria").toggle(300);
});

$("#tiraTag").click(function() {
	$(".tag").toggle(300);
});

$("#tiraContato").click(function() {
	$(".contato").toggle(300);
});

$("#tiraMarcas").click(function() {
	$(".marcas").toggle(300);
});

$("#tiraImposto").click(function() {
	$(".imposto").toggle(300);
});

// ------------ Scripts para Modal ------------------//

// ----------------------------- Modal de Tags
// ------------------------------------------------ //

// JS para preencher valor e placeholder da tela de cadastros de Tag
function modaltag(id) {

	$(".form-tag").attr("placeholder", id);
	$(".form-tag").val(id);
	$('.modal').modal('toggle');
};

// --------------------------- Fim Modal Tags
// ------------------------------------------------ //

// ----------------------------- Modal de Impostos
// ------------------------------------------------ //

// JS para Exibir os dados dos impostos para edição
function modalConfig(url) {
	$('.modal').modal('toggle');

	$.ajax({
		url : url,
		success : function(data) {
			$("#chama").html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

	/*
	 * $(".form-tag").attr("placeholder", id); $(".form-tag").val(id);
	 */

};

// --------------------------- Fim Modal Tags
// ---------------------------------------------------- //

// ----------------------------- Modal de Usuarios
// ------------------------------------------------ //

// JS para
function modalUser(id) {
	/*
	 * $(".form-tag").attr("placeholder", id); $(".form-tag").val(id);
	 */
	$('.modal').modal('toggle');

	$.ajax({
		url : id,
		success : function(data) {
			$('.modalUser').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

};

// --------------------------- Fim Modal Usuários
// ---------------------------------------------- //

// ----------------------------- Modal de Tags
// ------------------------------------------------ //
// JS para
function modaltag(url) {

	$('.modal').modal('toggle');

	$.ajax({
		url : url,
		success : function(data) {
			$('.modalTags').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});
};

// -------------------------------------------------------------------------------------------
// //

function modalNovatag(url) {

	$('.modal').modal('toggle');

	$.ajax({
		url : url,
		success : function(data) {
			$('.modalTags').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});
};

// --------------------------- Fim Modal Tags
// ---------------------------------------------- //

// ----------------------------- Modal de Produtos
// ------------------------------------------------ //

// JS para Exibir modal dos produtos para atualização
function modalProduto(id) {

	$('.modal').modal('toggle');

	$.ajax({
		url : id,
		success : function(data) {
			$('.modalProduto').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

};
// --------------------------- Fim Modal Produtos
// ---------------------------------------------- //

// ----------------------------- Modal de LocalEvento
// ------------------------------------------------ //

// JS para Exibir modal dos produtos para atualização
function modalLocalEvento(id) {

	$('#ModalEvento').modal('toggle');

	$.ajax({
		url : id,
		success : function(data) {
			$('.modal-body').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

};
// --------------------------- Fim Modal LocalEvento
// ---------------------------------------------- //

// ----------------------------- Modal de AlteraEstrategia
// ------------------------------------------------ //

// JS para Exibir Modal de Estratégias
function internoAlterar(id) {

	$('#ModalEstrategia').modal('toggle');

	$.ajax({
		url : id,
		success : function(data) {
			$('.modal-body-estrategia').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

};
// --------------------------- Fim Modal AlteraEstrategia
// ---------------------------------------------- //

// ----------------------------- Modal de Demanda
// ------------------------------------------------ //

// JS para Exibir Modal de Estratégias
function internoAlterarDemanda(id) {

	$('#ModalDemanda').modal('toggle');

	$.ajax({
		url : id,
		success : function(data) {
			$('.modal-body-estrategia').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

};
// --------------------------- Fim Modal Demanda
// ---------------------------------------------- //
// ------------------ Busca contato em novo Job por id do cliente
// --------------- //

function procuraContato(id) {

	$.ajax({
		url : "buscaContato?id=" + id, // envia id do contato para action
										// buscar as referencias desse contato
		success : function(data) {
			
			$('.info-contato').html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(2000).css({
				display : "none"
			});
		}
	});

};

// --------------------------- Fim busca contato
// ------------------------------------- //

// -------------------------- Verifica Diretorios para anexo
// ----------------------- //

$(":file").filestyle({
	buttonBefore : true
});

function pegaCaminho(departamento, idJob) {
	$.ajax({
		url : "caminhoPastas?nomeDepartamento=" + departamento + "&idJob="
				+ idJob,
		contentType : "application/x-www-form-urlencoded;charset=ISO-8859-15",
		success : function(data) {
			$('.caminho').html(data);
		}
	});

};

// --------------------------------------------------------------------------------
// //

function criaCampoPastaIntermediaria(valorSelect) {

	if (valorSelect == null || valorSelect == "") {
		$("#selectIntermediario").show(500).attr("name", "pastaIntermediaria");

		$("#divIntermediaria").hide(1000).css("display", "none").removeAttr(
				"name");
		event.preventDefault();
	}

	if (valorSelect == "criaPasta") {
		$("#selectIntermediario").hide(500).removeAttr("name");

		$("#divIntermediaria").show(1000).css("display", "inline").attr("name",
				"pastaIntermediaria");
	}
}

// --------------------------------------------------------------------------------
// //
$("#recebeAnexo")
		.submit(
				function() {

					if ($("input[name='AnexoTitulo'").val() == null
							|| $("input[name='AnexoTitulo'").val() == "") {

						$("input[name='AnexoTitulo'")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<span class='errors'>*Coloque um nome para o anexo.</span>");

						return false;
					}

					if ($("select[name='anexoarea']").val() == "selecione") {
						$("select[name='anexoarea']")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<br><span class='errors'>*Selecione o departamento.</span>");
						return false;
					}

					if ($("select[name='pastaIntermediaria']").val() == "selecione") {

						/* $('span').css("display","none"); */
						$("select[name='pastaIntermediaria']")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<br><span class='errors'>*Selecione uma pasta ou Crie uma.</span>");
						return false;
					}
					if ($("#fileAnexo").val() == null
							|| $("#fileAnexo").val() == "") {

						$("#fileAnexo")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<br><span class='errors'>*Selecione o Arquivo.</span>");
						return false;
					}

					$("#button_anexo").attr('disabled', 'disabled');
					$("#button_anexo").text("Salvando anexo, aguarde...");

				});

// -----------------------------------------------------------------------------------
// //
$("#agendarInteracao").click(function() {

	if ($(this).is(':checked') == false) {

		$("#dataInteracao").removeAttr("disabled");
		$("#horaInteracao").removeAttr("disabled");

	} else {

		$("#dataInteracao").attr("disabled", "disabled");
		$("#horaInteracao").attr("disabled", "disabled");

		/* $("#dataInteracao").attr("disabled","disabled") */
	}

});

$("#internaInteracao").click(function() {

	var name = "idContatoContato";

	if ($(this).is(':checked') == false) {

		$("#usuarioInteracao").css("display", "none").removeAttr("name");
		$("#contatoInteracao").css("display", "block").attr("name", name);

	} else {

		$("#usuarioInteracao").css("display", "block").attr("name", name);
		$("#contatoInteracao").css("display", "none").removeAttr("name");

	}

});

// ---------------- Eventos de Toggle -------------------------- //

$("#comunicacaoInterna").click(function() {
	if ($(this).is(':checked') == false) {
		$("#comunicaUsuario").toggle(200);
	} else {
		$("#comunicaUsuario").toggle(200);
	}
});

$("input[name='fornecedor']").click(function() {
	if ($(this).is(':checked') == false) {
		$("#fornecedorForm").toggle(200);
	} else {
		$("#fornecedorForm").toggle(200);
	}
});

// -------------------------------------------------------------- //

$("#notificar")
		.click(
				function() {

					if ($(this).is(':checked') == true) {

						$(".notificar").css("display", "none").toggle(100)/* .removeAttr("name") */;

					} else {
						$(".notificar").css("display", "block").toggle(100);

					}

				});

// ------------------ Validação formulário Interação
// ---------------------------------//

$("#formInteracao").submit(
		function() {

			if ($("#intercaoTexteArea").val() == ""
					|| $("#intercaoTexteArea") == null) {
				$("#intercaoTexteArea").css("border", "2px solid red");
				$(".errors").text("*Preencher a Descrição").show().fadeOut(
						25000);

				return false;
			}
			if ($("#agendarInteracao").is(':checked') == false) {

				if ($("#dataInteracao").val() == ""
						|| $("#dataInteracao").val() == null) {
					$("input[name='pd']").css("border", "2px solid red");
					$(".errors_data").show().fadeOut(25000);
					return false;
				}
				if ($("#horaInteracao").val() == ""
						|| $("#horaInteracao").val() == null) {
					$("input[name='ph']").css("border", "2px solid red");
					$(".errors_data").show().fadeOut(25000);
					return false;
				}

			}

			$("#button_interacao").text("Salvando, por favor aguarde ...");
			$('.loader').css({
				display : "block"
			});
			$("#button_interacao").attr('disabled', 'disabled');

			return true;
		});

// ------------------ Validação formulário Estratégias
// ---------------------------------//

$("#formEstrategia")
		.submit(
				function() {

					if ($("input[name='internoTitulo']").val() == ""
							|| $(".null") == null) {

						$("input[name='internoTitulo']")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<span class='errors'>*Preencher Titulo Item</span>");
						$(".errors").show().fadeOut(25000);

						return false;
					}

					if ($("#internoDescricao").val() == ""
							|| $("#internoDescricao") == null) {

						$("#internoDescricao")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<span class='errors'>*Preencher Linha Criativa</span>");
						$(".errors").show().fadeOut(25000);

						return false;
					}

					$("#button_estrategia").text("Salvando ...");
					$('.loader').css({
						display : "block"
					});
					$("#button_estrategia").attr('disabled', 'disabled');

					return true;
				});
// ------------------------------------ JS Para tratar inserção de contatos em
// empresas -------------- ------------------------------------------- //
$('#salvaContato').validator();

// Máscara para telefone celular ou residencial
$('.phone').focusout(function() {
	var phone, element;
	element = $(this);
	element.unmask();
	phone = element.val().replace(/\D/g, '');
	if (phone.length > 10) {
		element.mask("(99) 99999-999?9");
	} else {
		element.mask("(99) 9999-9999?9");
	}
}).trigger('focusout');

$("#salvaContato")
		.submit(
				function() {

					if ($("input[name='contato']").val() == ""
							|| $("input[name='contato']") == null) {
						$("input[name='contato']")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<span class='errors'>*Preencher nome do Contato</span>");
						$(".errors").show();
						return false;
					}

					$("#button_contato").text("Salvando ...");
					$("#button_contato").attr('disabled', 'disabled');

					return true;

					$.ajax({
						url : "salvaContato?ok=Teste",
						success : function() {
							alert("Contato Salvo com sucesso");
						}
					});

				});
//-------------------------------------- Salva Contato por Ajax ---------------------------------------------- //

function falsoVerdadeiro(valor){
	if (valor == ""  || valor == null || valor == " "){
		return false
	}else{
		return true;
	}
}

function confereContatoNovo(){
	var contatoNome = falsoVerdadeiro($("#contatoNovo").val());
	if(contatoNome == false){
			msgErrors($("#contatoNovo"),"*Preencher nome do Contato.");
		return false;
	}else{
		return true;
	}
}

function confereCargo(){
	var cargo = falsoVerdadeiro($("#cargoNovo").val());
	if(cargo == false){
			msgErrors($("#cargoNovo"),"*Preencher cargo.");
		return false;
	}else{
		return true;
	}
}


function confereEmail(){
	var email = falsoVerdadeiro($("#emailNovo").val());
	if(email == false){
		msgErrors($("#emailNovo"),"*Email Obrigátorio.");
		return false;
	}else{
		return true;
	}
}

function confereTelefone(){
	var phone = falsoVerdadeiro($("#phone").val());
	if(phone == false){
		msgErrors($("#phone"),"*Telefone Obrigátorio.");
		return false;
	}else{
		return true;
	}
}

// -------------------------/////
function SalvaContatoAjax(tipo) {
	
	if($("#ativoDesativado").is(':checked')){
		var habilitado = true;
	}else{
		var habilitado = false;
	}
	
	// se tipo email confere email
	if(tipo == "email"){
		var a =	confereContatoNovo();
		
		/*var b = confereCargo();*/
		var b = true;
		
		var c = confereEmail();
		if(a == true && b == true && c == true){
			salvaContatosAjaxNovo();
			limpaForm();
			/*$(".errors").fadeOut(15);
			$("#contatoNovo").css("border", "2px solid #ddd");*/
		}
	}
	
	// se tipo telefone confere email
	if(tipo == "telefone"){
		var a =	confereContatoNovo();
  	  /*var b = confereCargo();*/
		var b = true;
		var c =	confereTelefone();
		if(a == true && b == true && c == true){
			salvaContatosAjaxNovo();
			limpaForm();
			/*$(".errors").fadeOut(15);
			$("#contatoNovo").css("border", "1px solid #ddd");
			$("#cargoNovo").css("border", "1px solid #ddd");
			$("#emailNovo").css("border", "1px solid #ddd");
			$("#phone").css("border", "1px solid #ddd");*/
		}	
	}

};

function salvaContatosAjaxNovo(){
	
	var idEmp = $("#idEmNovo").val();
	var nome = $("#contatoNovo").val();
	var cargo = $("#cargoNovo").val();
	var telefone = $("#phone").val();
	var descricao = $("#descricaoNovo").val();
	var email = $("#emailNovo").val();
	var idContatoTeste = $("#idContatoCriado").val();
	
	if($("#ativoDesativado").is(':checked')){
		var habilitado = true;
	}else{
		var habilitado = false;
	}
	
	$.ajax({
		url : "salvaContatoAjax?idContatoTeste=" + idContatoTeste + "&idEmp="
				+ idEmp + "&nome=" + nome + "&cargo=" + cargo + "&telefone="
				+ telefone + "&descricao=" + descricao + "&email=" + email+"&habilitado="+habilitado,
																			
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			$(".retornoContato").html(data);
			$("input[name='telefoneNovo']").val("");
			$("#emailNovo").val("");
			$("#ConcluiContato").removeAttr("disabled");
			$(".sucess").show(500);

		}

	});
}

function msgErrors(idElemento,msg){
	
	$(idElemento).css("border", "2px solid red")
	.parent()
	.append("<span class='errors'>"+ msg +"</span>");
	$(".errors").show();
}

function limpaForm(){
	$(".errors").css("display", "none");
	$("#contatoNovo").css("border", "1px solid #ddd");
	$("#cargoNovo").css("border", "1px solid #ddd");
	$("#emailNovo").css("border", "1px solid #ddd");
	$("#phone").css("border", "1px solid #ddd");
}



// / ---------------- Exclui comunicador por Ajax  // --------------------------------------/////
function apagaComunicador(idComunicador, idContato) {

	$.ajax({
		url : "apagaComunicador?idComunicador=" + idComunicador + "&idContato="
				+ idContato, // envia id do contato para action buscar as
								// referencias desse contato
		success : function(data) {
			$(".retornoContato").html(data);
		}
	});

}

function editaComunicador(comunicador, idComunicador, idContato) {
	var comunicador = $("#comunicador")
	$.ajax({
		url : "apagaComunicador?idComunicador=" + idComunicador + "&idContato="
				+ idContato, // envia id do contato para action buscar as
								// referencias desse contato
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			$(".retornoContato").html(data);
		}
	});

}
// /
// ---------------------------------------------------------------------------------///
function editaCelulaComunicador(idComunicador) {

	
	var comunicador = $("#dinamicoComunicador").val();
	var idContato = $("#xrComunicador").val();

	$.ajax({
		url : "editaComunicador?idComunicador=" + idComunicador
				+ "&comunicador=" + comunicador + "&idContato=" + idContato, // envia
																				// id
																				// do
																				// contato
																				// para
																				// action
																				// buscar
																				// as
																				// referencias
																				// desse
																				// contato
		success : function(data) {
			$(".retornoContato").html(data);
		}
	});
}
//--------------------------------------------------------------------------------
////

function editaCelulaComunicador(nomeEmpresa) {

	var nomeEmpresa = nomeEmpresa;

	$.ajax({
		url : "populaListajobPorCliente?nomeEmpresa=" + nomeEmpresa,
		success : function(data) {
			$("#selectJobsEmpresa").html(data);
		}
	});
}

//--------------------------------------------------------------------------------
//// Copiar Lista existente

function exibeJobPorEmpresa (nomeEmpresa) { // Exibe Jobs Por Cliente

	var nomeEmpresa = nomeEmpresa;

	$.ajax({
		url : "populaListajobPorCliente?nomeEmpresa=" + nomeEmpresa,
		success : function(data) {
			$("#copiaJobsEmpresa").html(data);
		}
	});
}


function exibeListasPorJob (idJob) { // Exibe Listas Por Jobs

	var idJob = idJob;

	$.ajax({
		url : "exibeListasPorJob?idJob=" + idJob,
		success : function(data) {
			$("#copiaListasEmpresa").html(data);
		}
	});
}


function copiaLista(){
	
	
	$("#CriarListaOrigem").css("disabled","disabled");
	
	var idListaParaCopiar = $("#copiaListasEmpresa").val();
	var tituloLista = $("#tituloLista").val();
	var selectJobsEmpresa = $("#selectJobsEmpresa").val();
	var selectListaCopiar = $("#selectListaCopiar").val();
	var selectLista = $("#selectLista").val();

	var copiaJobsEmpresa = $("#copiaJobsEmpresa").val();
	var copiaListasEmpresa = $("#copiaListasEmpresa").val();
	
	
	if(selecioneFalse($("#selectListaCopiar"),selectListaCopiar) == false){return false;}
	if(selecioneFalse($("#copiaJobsEmpresa"),copiaJobsEmpresa) == false){return false;}
	if(selecioneFalse($("#copiaListasEmpresa"),copiaListasEmpresa) == false){return false;}
	if(selecioneFalse($("#selectLista"),selectLista) == false){return false;}
	if(selecioneFalse($("#selectJobsEmpresa"),selectJobsEmpresa) == false){return false;}

	
	if(seFalse($("#tituloLista"),tituloLista) == false){return false;}
	
	
	
	
	
	$.ajax({
		url : "copiaLista?idListaParaCopiar=" + idListaParaCopiar +"&tituloLista=" + tituloLista + "&selectJobsEmpresa=" + selectJobsEmpresa,
		success : function(data) {
			$("#listaCopiada").fadeIn(500).html(data);
			$('#efeito-blur').css("-webkit-filter","blur(2px)");
		}
	});
	
}



// ----------------------------------------------------- Fim Funções Ajax
// ------------------------------------------------------------------------ //

// ------------------ Validação formulário Demanda
// ---------------------------------//

$("#formDemanda")
		.submit(
				function() {

					if ($("#internoDemanda").val() == ""
							|| $("#internoDemanda") == null) {

						$("#internoDemanda")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<span class='errors'>*Preencher Titulo do Item</span>");
						$(".errors").show().fadeOut(25000);

						return false;
					}
					if ($("#internoDescricaoDemanda").val() == ""
							|| $("#internoDescricaoDemanda") == null) {

						$("#internoDescricaoDemanda")
								.css("border", "2px solid red")
								.parent()
								.append(
										"<span class='errors'>*Preencher descrição da demanda</span>");
						$(".errors").show().fadeOut(25000);

						return false;
					}

					$("#button_demanda")
							.text("Salvando, por favor aguarde ...");
					$('.loader').css({
						display : "block"
					});
					$("#button_demanda").attr('disabled', 'disabled');

					return true;
				});

// --------------------- Validação Notifica equipe Locco
// ----------------------------- //

$("#notificaEquipe").submit(
		function() {

			$("#button_notifica").text(
					"Enviando Email e salvando, por favor aguarde ...");
			$('.loader_notifica').css({
				display : "block"
			});
			$("#button_notifica").attr('disabled', 'disabled');

			return true;
		});

// ----------------------------------------------------------------------------
// //
function itemProducaoAjax(item, idFornecedor, idLista, idProdutoGrupo) {

	var idItem = item;

	$(idItem).toggle(500);

	$.ajax({
		url : "itemListaAjax?idFornecedor=" + idFornecedor + "&idLista="
				+ idLista + "&idProdutoGrupo=" + idProdutoGrupo, // envia id
																	// do
																	// contato
																	// para
																	// action
																	// buscar as
																	// referencias
																	// desse
																	// contato
		success : function(data) {
			$(idItem).html(data);
		},
		beforeSend : function() {
			$('.loader').css({
				display : "block"
			});
		},
		complete : function() {
			$('.loader').delay(100).css({
				display : "none"
			});
		}
	});
}

// ----------------------------------------------------------------------------
// //
// Funcao Ajax para atualizar valores de Configuracao em Lista de producao

function editaCategoriaConfiguracao(idcategoria) {
	
	
	var editaCategoria = "#editacategoria" + idcategoria;
	$(editaCategoria).toggle(500); // Efeito toggle
	var categoriaAtualizada = "#categoriaAtualizada" + idcategoria; // Retorno
																	// do Ajax
	var categoriaOrdem = $("#categoriaOrdem" + idcategoria).val();
	var categoria = $("#categoria"+idcategoria).val();
	
	
	var idImpostoTrasiente = $("#idImpostoTrasiente" + idcategoria).val();
	var idListaTransiente = $("#idListaTransiente").val();
	var impostoTitulo = $("#impostoTitulo" + idcategoria).val();

	$.ajax({
		method : "POST",
		url : "editaCategoriaConfiguracao?categoria="+categoria+"&idImpostoTrasiente=" + idImpostoTrasiente + "&idcategoria="
				+ idcategoria + "&idListaTransiente=" + idListaTransiente
				+ "&impostoTitulo=" + impostoTitulo,
		success : function(data) {

			$(categoriaAtualizada).html(data);
		},
		beforeSend : function() {
			$('.loader' + idcategoria).css({
				display : "inline"
			});
		},
		complete : function() {
			$('.loader' + idcategoria).delay(100).css({
				display : "none"
			});
			atualizaValoresListaAjax(idListaTransiente);

		}
	});

};

function atualizaValoresListaAjax(idLista) {

	$.getJSON("CalculaValoresListaAjax?idLista=" + idLista, function(result) {
		$("#administracaoValor").val(result.administracaoValor);
		$("#impostoValor").html(result.impostoValor);
		$("#valorTotal").html(result.valorTotal);
	});
}

// ----------------------------------------------------------------------------
// //
// Funcao para confirmar conclusão da lista
function confirmaConclusaoPlanilha() {
	$("#ConfirmaConclusaoPlanilha").fadeIn(250);
}

function concluiPlanilhaPorAjax(idLista) {
	$.ajax({
		method : "POST",
		url : "concluiPlanilha?idLista=" + idLista,
		success : function(data) {
			$("#listaConcluida").fadeIn(300).html(data);
		}
	});
};
// ----------------------------------------------------------------------------
// //
// Funcao para duplicar Lista
function duplicaPlanilhaPorAjax(idLista) {
	$("#duplicaPlanilha")
			.text("Duplicando ...")
			.css("pointer-events", "none")
			.prepend(
					"<img src='resources/images/loader-confirmacao.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		method : "POST",
		url : "duplicaPlanilha?idLista=" + idLista,
		success : function(data) {
			$("#listaConcluida").fadeIn(300).html(data);
		}
	});
};
// ----------------------------------------------------------------------------
// //
// Funcao para Revisar Lista
function revisarPlanilhaPorAjax(idLista) {

	$("#revisarPlanilha")
			.text("Criando Revisão ...")
			.css("pointer-events", "none")
			.prepend(
					"<img src='resources/images/loader-confirmacao.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");

	$.ajax({
		method : "POST",
		url : "revisaPlanilha?idLista=" + idLista,
		success : function(data) {
			$("#listaConcluida").fadeIn(300).html(data);
		}
	});
};
// ----------------------------------------------------------------------------
// //
// Funcao para Revisar Lista
function aprovarPlanilhaPorAjax(idLista) {

	$("#aprovarPlanilha")
			.text("Aprovando Planilha ...")
			.css("pointer-events", "none")
			.prepend(
					"<img src='resources/images/ajax-loader-aprova.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");

	$.ajax({
		method : "POST",
		url : "aprovaPlanilha?idLista=" + idLista,
		success : function(data) {
			$("#listaConcluida").fadeIn(300).html(data);
		}
	});
};

// ----------------------------------------------------------------------------
// //
function produtoAtuacao(idAtuacao) {
	
	if (idAtuacao == "selecioneProduto") {
		$.ajax({
			url : "todosProdutoAtuacao",
			success : function(data) {
				$("#produtoAtuacao").html(data);
			}
		});
	} else {
		
		
		$.ajax({
			url : "produtoAtuacao?idAtuacao=" + idAtuacao,
			success : function(data) {
				$("#produtoAtuacao").html(data);
			}
		});
	}
}
// ---------------------------------------------------------------------------------------
// //
// Consulta Lista por Ajax A-Z
function consultaListasAjax(termo) {

	$.ajax({
		method : "POST",
		url : "consultaListasAjax?termo=" + termo,
		success : function(data) {
			$("#listasAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
		}
	});
}

// Consulta Lista por Ajax Empresa
function consultaListasAjaxEmpresa(termo) {

	$.ajax({
		method : "POST",
		url : "consultaListasAjaxEmpresa?termo=" + termo,
		success : function(data) {
			$("#listasAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
		}
	});
}
// Consulta Lista por Ajax Status
function consultaListasAjaxStatus(termo) {

	$.ajax({
		method : "POST",
		url : "consultaListasAjaxStatus?termo=" + termo,
		success : function(data) {
			$("#listasAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
		}
	});
}
// Consulta Lista por Ajax Status
function consultaListasAjaxStatus(termo) {
	$.ajax({
		method : "POST",
		url : "consultaListasAjaxStatus?termo=" + termo,
		success : function(data) {
			$("#listasAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
		}
	});
}
// Consulta Lista por Ajax Empresa avancada
function consultaAvancadaListasAjaxEmpresa(termo) {

	var base = $("#baseData").val();
	
	$.ajax({
		method : "POST",
		url : "consultaListasAjaxEmpresa?termo=" + termo + "&base="+base,
		success : function(data) {
			$("#listasAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
			preencheLista();
		}
	});

}
function preencheLista() {
	
	var base = $("#baseData").val();
	
	var idEmpresa = $("#listaEmpresaUnicaAjax").val();
	$.ajax({
		method : "POST",
		url : "consultaListasAjaxAvancada?idEmpresa=" + idEmpresa + "&base="+base,
		success : function(data) {
			$("#listaStatusUnicoAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
		}
	});
}
// Consulta Lista por Ajax Lista avancada
function consultaAvancadaListasAjaxlista(idLista) {

	$.ajax({
		method : "POST",
		url : "consultaListasAjaxAvancadaLista?idLista=" + idLista,
		success : function(data) {

			$("#listasAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
		}
	});

}
// Consulta Lista por Ajax Lista Status avancada
function consultaAvancadaListasStatusAjaxlista(idlistaEstatus) {

	var idEmpresa = $("#listaEmpresaUnicaAjax").val();
	$.ajax({
		method : "POST",
		url : "consultaAvancadaListasStatusAjaxlista?idlistaEstatus="
				+ idlistaEstatus + "&idEmpresa=" + idEmpresa,
		success : function(data) {
			$("#listasAjax").fadeIn(300).html(data);
			$('#loader-lista').fadeOut(1000);
		},
		beforeSend : function() {
			$('#loader-lista').fadeIn(500).css({
				display : "inline"
			});
		},
		complete : function() {
			$('#loader-lista').fadeOut(1000);
		}
	});
}
// ---------------- Chama por Ajax o módulo de gerar planilha para excel
// ------------------ //

function exportaExcelAjax(idLista) {
	$("#planilhaGerarExcel")
			.text("Gerando Planilha ...")
			.css("pointer-events", "none")
			.prepend(
					"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		url : "exportaExcel?idLista=" + idLista,
		success : function(data) {

			$("#geraExcel").fadeIn(300).html(data);
		}
	});
}

// ------------------ //
// Gera planilha Excel Modelo Bayer
function exportaExcelAjaxBayer(idLista) {
	$("#planilhaGerarExcelBayer")
	.text("Gerando Planilha ...")
	.css("pointer-events", "none")
	.prepend(
	"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		url : "geradorDeExcel?idLista=" + idLista,
		success : function(data) {
			
			$("#geraExcel").fadeIn(300).html(data);
		}
	});
}

// ------------------ //
// Gera planilha Excel Novo Modelo Bayer
function exportaExcelAjaxNovoBayer(idLista) {
	$("#planilhaGerarNovoExcelBayer")
	.text("Gerando Planilha ...")
	.css("pointer-events", "none")
	.prepend(
	"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		url : "ExcelNovoBayer?idLista=" + idLista,
		success : function(data) {
			
			$("#geraExcel").fadeIn(300).html(data);
		}
	});
}

// ------------------ //
// Gera planilha Excel ListaEmpresas
function exportaExcelEmpresas() {
	$("#planilhaExcelEmpresas")
	.text("Gerando Listagem ...")
	.css("pointer-events", "none")
	.prepend(
	"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		url : "geraExcelEmpresas",
		success : function(data) {
			
			$("#geraExcel").fadeIn(300).html(data);
		}
	});
}

//------------------ //

function excluiCategoria(idGrupo,idLista) {

	$("#excliuGrupoItem").attr('href','excluiCategoria?idGrupo='+idGrupo+'&idLista='+idLista);
	
	
	$("#excluiGrupo").fadeIn(300).html(data);
	
	
	/*$("#planilhaGerarExcel")
			.text("Gerando Planilha ...")
			.css("pointer-events", "none")
			.prepend(
					"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		url : "exportaExcel?idLista=" + idLista,
		success : function(data) {

			$("#geraExcel").fadeIn(300).html(data);
		}
	});*/
}




// ---------------- Preenche por Ajax valor de novo item do Grupo Da Lista
// ------------------ //

function InformacaoProduto(idProduto) {

	var incideimpostoCheckbox = $("#incideimpostoCheckbox").val();
	var custoItemunitario = $("#custoItemunitario").val();
	var idGrupoHidden = $("#idGrupoHidden").val();

	if ($("#incideimpostoCheckbox").is(":checked")) {
		incideimpostoCheckbox = true;
	} else {
		incideimpostoCheckbox = false;
	}

	$.getJSON("infoProdutos?idproduto=" + idProduto + "&incideimpostoCheckbox="
			+ incideimpostoCheckbox + "&custoItemunitario=" + custoItemunitario
			+ "&idGrupo=" + idGrupoHidden, function(result) {
		$("#custoItemunitario").val(result.custo);
		$("#custoCadastro").html(result.custo);
		$("#precoSemNota").html(result.custo);
		$("#precoUnitSemNF").html(result.custo);
		$("#custoTotal").html(result.custo);
		$("#valorSemNF").html(result.custo);
		$("#custoTotalSFNCalculado").html(result.custo);
		$("#valorFinal").html(result.resultadoFinalComImpostoCalculado);
		$("#precoUnitFinal").html(result.resultadoFinalComImpostoCalculado);
		$("#tipoUnidadeItem").html(result.unidade);
	});

}

// ----------------------------------------------------------------------------
// //

$("#CheckbvFornecedor").click(function() {
	if ($("#CheckbvFornecedor").is(':checked') == true) {
		$("#bvFornecedor").toggle(300);
	} else {
		$("#bvFornecedor").toggle(300);
	}

});

// ----------------------------------------------------------------------------
// //
function calculaValorTotal() {
	var qtd1 = $("#quantidade1").val();
	var qtd2 = $("#quantidade2").val();
	var diarias = $("#diarias").val();
	var bvFornecedorValorEdita = $("#bvFornecedorValorEdita").val();
	var custoItemunitario = $("#custoItemunitario").val();
	var precoUnitFinal = $("#precoUnitFinal").text();

	if (custoItemunitario == null || custoItemunitario == "" || custoItemunitario == "0,00") {
		custoItemunitario = "0";
		$("#custoItemunitario").attr("value", "0");
	}else{
		
	}
	
	
	if (bvFornecedorValorEdita == null || bvFornecedorValorEdita == "") {
		bvFornecedorValorEdita = "0,00";
	} else {
		bvFornecedorValorEdita = bvFornecedorValorEdita;
	}

	$.getJSON("calculaValorTotal?qtd1=" + qtd1 + "&qtd2=" + qtd2 + "&diarias="
			+ diarias + "&custoItemunitario=" + custoItemunitario
			+ "&precoUnitFinal=" + precoUnitFinal + "&bvFornecedorValorEdita="
			+ bvFornecedorValorEdita, function(result) {

		$("#custoTotal").html(result.CustounitarioTotalconvertido);  // Custo Total
		$("#valorSemNF").html(result.resultadoFinalSNF); 			 // Valor s/ NF
		$("#bvValor").html(result.bvValor); 					  	 // [ BV valor ]
		$("#valorFinal").html(result.custoTotalQuantidadePordiaria); // Valor Final
		$("#QtdUnidadeItem").html(result.qtdxqtd); 					 // Quantidade das unidades do item 

	});
	
	
	
	
}

// ----------------------------------------------------------------------------
// //
function calculaPrecoSNF() {
	
	var bvFornecedorValorEdita = $("#bvFornecedorValorEdita").val();
	var custoItemunitario = $("#custoItemunitario").val();
	var incideimpostoCheckbox = $("#incideimpostoCheckbox").val();
	var idGrupoHidden = $("#idGrupoHidden").val();

	if (custoItemunitario == null || custoItemunitario == "" || custoItemunitario == "0,00") {
		custoItemunitario = "0";
		$("#custoItemunitario").attr("value", "0");
	}else{
		
	}
	
	
	if ($("#incideimpostoCheckbox").is(":checked")) {

		incideimpostoCheckbox = true;
	} else {
		incideimpostoCheckbox = false;
	}
	if (bvFornecedorValorEdita == null || bvFornecedorValorEdita == ""
			|| bvFornecedorValorEdita == "0,00") {
		bvFornecedorValorEdita = "0,00";
		$("#bvFornecedorValorEdita").attr("value", "0,00");

	} else {
		bvFornecedorValorEdita = bvFornecedorValorEdita;

	}

	$.getJSON("calculaValorSNF?bvFornecedorValorEdita="
			+ bvFornecedorValorEdita + "&custoItemunitario="
			+ custoItemunitario + "&incideimpostoCheckbox="
			+ incideimpostoCheckbox + '&idGrupoHidden=' + idGrupoHidden,
			function(result) {

				$("#custoTotalSFNCalculado")
						.html(result.custoTotalSFNCalculado);
				$("#tiraUnitPeloBv").html(result.custouniTiraBv);
				$("#precoUnitFinal").html(
						result.resultadoFinalComImpostoCalculado);
				calculaValorTotal();

			});
}
// -------------------------------------- Efeito Toogle
// --------------------------------------------------------- //
// Passar id do item para o efeito como argumento
function efeitoToogle(item) {
	var idItem = item;
	$(idItem).toggle(500);
}
function efeitoToogleFast(item) {
	var idItem = item;
	$(idItem).toggle(5);
}

function printpage() {
	window.print()
}

function efeitoToogleDuplo(item,item2) {
	var idItem = item;
	var idItem2 = item2;
	$(idItem).toggle(500);
	$(idItem2).toggle(500);
}




// ------------------------------------------------------------- //

// Cria lista
function criaLista(idLista, idGrupo) {

	$.ajax({
		method : "POST",
		url : "criaLista?idLista=" + idLista + "&idGrupo=" + idGrupo,
		success : function(data) {
			$("#criacaoLista" + idGrupo).html(data);
			alert("Item enviado para Criação.");
			// $("#itemEnviadoCriacao").fadeIn(300).html(data);
		},
		beforeSend : function() {
		},
		complete : function() {
		}
	});
}

function trocaResponsavel(item, idItem2) {
	var idItem = item;
	var idItem2 = idItem2;

	$(idItem).toggle(300);
	$(idItem2).toggle(300);
}

function mudaResponsavel(idResponsavel,idListaCriacao) {
	
	if (idResponsavel == "selecione") {
		alert("Selecione um responsável !");
	} else {

		$.ajax({
			method : "POST",
			url : "mudaResponsavel?idResponsavel=" + idResponsavel+"&idListaCriacao="+idListaCriacao,
			success : function(data) {
				location.reload();
			},
			beforeSend : function() {
			},
			complete : function() {
			}
		});

	}
}

function mudaPar(idResponsavel,idListaCriacao) {
	
	if (idResponsavel == "selecione") {
		alert("Selecione um responsável !");
	} else {

		$.ajax({
			method : "POST",
			url : "mudaPar?idResponsavel=" + idResponsavel+"&idListaCriacao="+idListaCriacao,
			success : function(data) {
				location.reload();
			},
			beforeSend : function() {
			},
			complete : function() {
			}
		});

	}
}

// ---------------------------------------------------------------------------------------------------------------------------------- //

/*function efeitoToogle(item) {
	var idItem = item;
	$(idItem).toggle(500);
}*/


function carregaListaFornecedor(idProduto){
	
	var idEmpresa = $("#idEmpresa"+idProduto).val();
	
	$.ajax({
			method : "POST",
			url : "trocarFornecedor?idEmpresa="+idEmpresa+"&idProduto="+idProduto,
			success : function(data) {
				/*$("#fornecedorAtual").fadeIn(300).html(data);
				$("#sucessFornecedor").fadeIn(500);*/
				location.reload();
			}
		});
}

function trocarFornecedor(idProduto){
	/*var idProduto = $("#idProdutoGrupo").val();*/
	var idEmpresa = $("#idEmpresa"+idProduto).val();
	
	$.ajax({
		method : "POST",
		url : "trocarFornecedor?idEmpresa="+idEmpresa+"&idProduto="+idProduto,
		success : function(data) {
			/*$("#fornecedorAtual").fadeIn(300).html(data);
				$("#sucessFornecedor").fadeIn(500);*/
			location.reload();
		}
	});
}


function calculaPrazo(qtdParcela,nameInput){
	var diasPrazo = $("#diasPrazo"+qtdParcela).val();
	var idLista   = $("#idLista").val();
	
		$.ajax({
		method : "POST",
		url : "calculaPrazo?diasPrazo="+diasPrazo+"&idLista="+idLista+"&nameInput="+nameInput,
		success : function(data) {
			$("#prazoPagamento"+qtdParcela).fadeIn(300).html(data);
		}
	});
}



function calculaPagamento(qtdParcelas,idProdutoGrupo,posicaoCelula,posicaoItem){
	
var valorCelula = 	$("#"+posicaoCelula).val();
var valorContratacao = $("#valorPagamentoMesmoFornecedor").val();
var infoPag = $("input[name='infoPag']:checked").val();

var arrayObj = new Array();


for(var i = 1;i <= qtdParcelas;i++){
	arrayObj.push($("#valorItem"+i).val().replace(",",";")+" ");
}

$.getJSON("calculaPagamento?qtdParcelas=" + qtdParcelas
		   +"&idProdutoGrupo="
		   +idProdutoGrupo
		   +"&posicaoCelula="
		   +posicaoCelula
		   +"&posicaoItem="
		   +posicaoItem
		   +"&valores="
		   +arrayObj
		   +"&valorContratacao="
		   +valorContratacao	   
		   +"&infoPag="+infoPag,

	function(result) {
	
			var tam = result.length;
		
			for(var i = 0;i < tam;i++){
				var num = i+1;
				console.log("valorItem"+num+" : "+result[i]);
				$(".valorItem"+num).removeAttr('attr');

				$(".valorItem"+num).attr('value', result[i]);
			}
		
	
//		$("#valorPagamentoMesmoFornecedor").val(result.valorPagamentoMesmoFornecedor);
	});


//$("#itemValor4").attr('value', '235,60');

}

function localentrega(){
	
	var localEntrega = $("#localEntrega").val();
	var idLista   = $("#idLista").val();
	
	$.ajax({
		method : "POST",
		url : "localEntrega?localEntrega="+localEntrega+"&idLista="+idLista,
		success : function(data) {
			$("#localEvento").fadeIn(300).html(data);
		}
	});
}

function parcelamento(num){
	
	var idProdutoGrupo = $("#produtoGrupo").val();
	var valorContratacaoCalculado = $("#valorPagamentoMesmoFornecedor").val();
	var valorContratacao = $("#contratacaoValor1").val();
	
	var infoForn = $("input[name='infoForn']:checked").val();
	var infoPag = $("input[name='infoPag']:checked").val();
	
	$.ajax({
		method : "POST",
		url : "parcelamento?idProdutoGrupo="+idProdutoGrupo
		+"&parcelas="+num
		+"&valorContratacaoCalculado="+valorContratacaoCalculado
		+"&infoPag="+infoPag
		+"&infoForn="+infoForn
		+"&valorContratacao="+valorContratacao,
		success : function(data) {
			$("#tableParcelas").fadeIn(300).html(data);
		}
	});
	
}





function calculaDiferenca(){
	
	var itemValor1 = $("#itemValor1").val();
	var contratacaoValor1   = $("#contratacaoValor1").val();
	
	$.ajax({
		method : "POST",
		url : "calculaDiferenca?itemValor1="+itemValor1+"&contratacaoValor1="+contratacaoValor1,
		success : function(data) {
			$("#diferenca").fadeIn(300).html(data);
		}
	});
}
// ------------------------------------------------------------------------ //
function gerarWordCarta(idCarta,idFornecedor,idLista) {
	$("#gerarWord")
			.text("Gerando Carta de Contratação ...")
			.css("pointer-events", "none")
			.prepend(
					"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		url : "gerarWordCarta?idCarta=" + idCarta +"&idFornecedor="+idFornecedor+"&idLista="+idLista,
		success : function(data) {

			$("#geraCarta").fadeIn(300).html(data);
		}
	});
}

function gerarOutroWordCarta(idCarta,idFornecedor,idLista,idFornecedorOriginal,idProdutoGrupo) {
	$("#gerarWord")
	.text("Gerando Carta de Contratação ...")
	.css("pointer-events", "none")
	.prepend(
	"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
	$.ajax({
		url : "gerarOutroWordCarta?idCarta=" + idCarta +"&idFornecedor="+idFornecedor+"&idLista="+idLista+"&idFornecedorOriginal="+idFornecedorOriginal+"&idProdutoGrupo="+idProdutoGrupo,
		success : function(data) {
			
			$("#geraCarta").fadeIn(300).html(data);
		}
	});
}

$( "#target" ).keyup(function() {
	
	var texto = $("#target").val();

	  $.ajax({
			url : "busca?texto=" + texto,
			success : function(data) {

			$("#other").fadeIn(300).html(data);
			}
		});
});

// --------------------------------------------------------------------- //
function calculaDiferencaFornecedor(){
	var itemValor = $("#itemValor1").val();
	var contratacaoValor = $("#contratacaoValor1").val();
	var impostoMesmoFornecedor = $("#impostoMesmoFornecedor").val();
	
	var contratacaoValor1 = $("#contratacaoValor1").val();
	
	if(contratacaoValor1 == "0,00" || contratacaoValor1 == null || contratacaoValor1 == ""){
		alert("Preencha o valor de Contratação.")
	}else{
	
	$.getJSON("calculaDiferencaFornecedor?itemValor=" + itemValor
			   +"&contratacaoValor="
			   +contratacaoValor
			   +"&impostoMesmoFornecedor="
			   +impostoMesmoFornecedor,
			function(result) {
		
			$("#valorPagamentoMesmoFornecedor").val(result.valorPagamentoMesmoFornecedor);
		});
	}
}

function calculaDiferencaOutroFornecedor(){
	var diferenca = $("#diferencaValor1").val();
  	 
	var impostoOutroFornecedor = $("#impostoOutroFornecedor").val();
	
$.getJSON("calculaDiferencaOutroFornecedor?diferenca=" + diferenca +"&impostoOutroFornecedor="+ impostoOutroFornecedor,
			function(result) {
			$("#valorPagamentoOutroFornecedor").val(result.valorPagamentoMesmoFornecedor);
		});
}

function atribuirFornecedor(){
	
	var contratacaoValor1 = $("#contratacaoValor1").val();
	
	if(contratacaoValor1 == "0,00" || contratacaoValor1 == null || contratacaoValor1 == ""){
		alert("Preencha o valor de Contratação.")
	}else{
		$("#mesmoFornecedorDiv").fadeIn(500);

		$("#outroFornecedorDiv").fadeOut(250);
		$("#impostoOutroFornecedor").val("0");
		$("#valorPagamentoOutroFornecedor").val("0");
		
	}
}

function atribuirOutroFornecedor(){
	
	var contratacaoValor1 = $("#contratacaoValor1").val();
	
	if(contratacaoValor1 == "0,00" || contratacaoValor1 == null || contratacaoValor1 == ""){
		alert("Preencha o valor de Contratação.")
	}else{
		$("#mesmoFornecedorDiv").fadeOut(250);
		$("#impostoMesmoFornecedor").val("0");
		$("#valorPagamentoMesmoFornecedor").val("0");
		
		$("#outroFornecedorDiv").fadeIn(500);
	}
	
	
}

function apagarAnexo(idAnexo, idJob){
	
	$.ajax({
		url : "apagarAnexo?idAnexo=" + idAnexo + "&idJob=" + idJob,
		success : function(data) {
			$("#anexosAjax").fadeIn(1000).html(data);
			location.reload();
		}
	});
	
	
	
}



//----------------------------------------------------------------------------
////
//Funcao para excluir Empresa
function confirmaExclusaoEmpresa(idEmpresa) {
	$("#excluiEmpresa").fadeIn(250);
	
	$("#idEmpresaValor").val(idEmpresa);
}

function deletaEmpresa() {
	
var idEmpresa = $("#idEmpresaValor").val();
	$.ajax({
		method : "POST",
		url : "deletaEmpresa?idEmpresa=" + idEmpresa,
		success : function(data) {
//			$("#listaConcluida").fadeIn(300).html(data);
			location.reload();
		}
	});
};
//----------------------------------------------------------------------------


function seSelecione(campoInput,campoParaComparar){
	if(campoParaComparar == "selecione"){
		(campoInput).css("border","2px solid red")
   	    .parent().append("<span class='errors'>*Campos Obrigatórios.</span>");
		$(".errors").show();
		return false}
	else{return true}
}
function selecioneFalse(idCampo, valor){
	if(seSelecione(idCampo,valor) == false){
		return false;}
}

// ------------------------------- Ordenacão de Listas de produção -------------------------------------- //

function ordenaLista(){
	$("#ordenaLista").fadeIn(500);
}

function ordenaListaCarregaLista(){
	
	var idLista = $("#idListaTransiente").val();
	$.ajax({
		method : "POST",
		url : "carregaListaOrdenar?idLista=" + idLista,
		success : function(data) {
			$("#boxOrdenacao").fadeIn(500).html(data);
		}
	});
}

function cria3D(idCriacaoItemLista){
	
	$.ajax({
		method : "POST",
		url : "cria3D?idCriacaoItemLista=" + idCriacaoItemLista,
		success : function(data) {
			
			location.reload();
			
		}
	});
}

function efeitoShow(item){
	var idItem = item;
	$(idItem).show(100);
}

function efeitoHide(item){
	var idItem = item;
	
	$("#impostoMesmoFornecedor").val("0");
	$("#valorPagamentoMesmoFornecedor").val("0");
	$("#contratacaoValor1").val("0");
	$("#diferencaValor1").val("0");
	$("#difer").text("0");
	
	$(idItem).hide(100);
	
	
//	$(idItem).toggle(100);
}

function efeitoToogle(item) {
	var idItem = item;
	$(idItem).toggle(500);
}

function verificaLogin(){
			
	var loginUsuario = $("#loginUsuario").val();	
	
	if(loginUsuario == null || loginUsuario == "" || loginUsuario == " "){
		alert("Digite um valor para o campo Login.");
		return false;
	}else{
	
		$.ajax({
	
			url : "verificaLogin?loginUsuario="+loginUsuario,
			success : function(data) {
				if(data == 1){
					
					$("#loginUsuario").css("border","4px solid red");
					$("#verifica").fadeIn(500);
					$("#jaExiste").toggle(500);
					$("#naoExiste").fadeOut(500);
					$("#cadastraNovoUser").addClass("is-disabled");
					
				}else{
					$("#loginUsuario").css("border","1px solid #ddd");
					$("#verifica").fadeIn(500);
					$("#jaExiste").fadeOut(500);
					$("#naoExiste").toggle(500);
					$("#cadastraNovoUser").removeClass("is-disabled");
				}
				
			},
			beforeSend : function() {
			},
			complete : function() {
	/*			location.reload();*/
			}
		});
		
	}	
}

function teste222(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
			$("#msg-erro-email").slideToggle("slow"); 
	    }, 5000);
	});
}



function teste41(){
	$(document).ready(function() {
			window.setTimeout(function()
		    {
				$("#msg-sucesso-email").slideToggle("slow"); 
		    }, 3000);
	});
}

				
// ----------------------------- Efeitos  ---------------------------------- //

$(function(){
    $("#atualizaUsuario").mouseenter(function(){
    	$("#statusAtualizacao").fadeIn(500);     
    });
});

$(function(){
    $("#atualizaUsuario").mouseout(function(){
    	$("#statusAtualizacao").fadeOut(250); 
    });
});


// --------------------------------------------------------------------------- //
//Determina Quantidade
function determinaQuantidade(idProdutoGrupo, idGrupo){
	
	$.getJSON("determinaQuantidade?idProdutoGrupo="
			+ idProdutoGrupo + "&idGrupo="
			+ idGrupo,
			function(result) {

				$("#QuantDeterMin").html(result.determinaQuant);
				$("#precoTotal").html(result.precoTotal);
				$("#valorUnit").html(result.valorUnit);
			});

}

//Determina QuantidadePadrao
function determinaQuantidadePadrao(idGrupo){
	
	var quantidade = $("#defaultQuant").val();
	var diaria = $("#defaultDiaria").val();
	
	$("input[name='determina']").attr("false");

	$("#radioPadrao").attr("checked", "true");
	
	$.getJSON("determinaQuantidadePadrao?"
			+ "&idGrupo="+ idGrupo
			+ "&quantidade="+ quantidade
			+ "&diaria="+ diaria		
			,
			function(result) {
				
				$("#QuantDeterMin").html(result.determinaQuant);
				$("#precoTotal").html(result.precoTotal);
				$("#valorUnit").html(result.valorUnit);
				$(".ok").show(800);
			});
	
}
// --------------------------------------
// Salva Orcamento em Edita linha 
function SalvaNovoOrcamento(idCriacaoItemLista){
	
	var valor = $("#valorFornecedor").val();
	var idFornecedor = $("#fornecedorOrcamento").val();
	var grupoOrcamento = $("#grupoOrcamento").val();
	var idProdutoGrupo = $("#idProdutoGrupo").val();
	
	if(valor == ""){
		$("#valorFornecedor").css("border","1px solid red");
		$("#erroOrcamento").show(100);
		return false;
	}

	$.ajax({
		url : "salvaOrcamentoFornecedor?valor="+valor+"&idFornecedor="+idFornecedor+"&idGrupo="+grupoOrcamento+"&idProdutoGrupo="+idProdutoGrupo,
		success : function(data) {
			location.reload();
		},
		beforeSend : function() {
		},
		complete : function() {
		}
	});
}

// --------------------------------------

/*function validaProducao(){
	$(".consolidaBotao")
		.val("Consolidando ...")
		.css("pointer-events", "none");
	$("#itemImage")
		.prepend(
		"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>");
}

function validaProducaoErro(){
	$(".consolidaBotao")
	.val("Consolidar Item");
	$("#itemImage").hide(500);
}*/




function filtraLista(){

	$.ajax({
		method : "POST",
		url : "filtraListaCriacao?t="+$("#listaEmpresa").val()+"&s="+$("#listaMes").val()+"&r="+$("#listaData").val(),
		success : function(data) {
			$("#criacaoListas").html(data);
		},
		beforeSend : function() {
			$("#loader-confirmacao").fadeIn(500);
		},
		complete : function() {
			$("#loader-confirmacao").fadeOut(800);	
		}
	});
	
}

function filtraListaUsuario(){
	
	$.ajax({
		method : "POST",
		url : "filtraListaUsuario?t="+$("#listaUsuario").val()+"&s="+$("#listaMes").val()+"&r="+$("#listaData").val(),
		success : function(data) {
			$("#criacaoListas").html(data);
		},
		beforeSend : function() {
			$("#loader-confirmacao").fadeIn(500);
		},
		complete : function() {
			$("#loader-confirmacao").fadeOut(800);	
		}
	});
	
}




$(document).ready(function(){
	$('#salvaItemProducao').validate({
		//Campos com o name preenchido
		rules: { 
			pEntrega: { required: true}
		},
		//Mensagens que aparecerao abaixo dos names
		messages: {
			pEntrega: { required: 'Preencha a data de entrega'}
		},
		submitHandler: function( form ){
			var dados = $( form ).serialize();
//envio do formulario por ajax
			$.ajax({
				type: "POST",
				url: "salvaItem",
				data: dados,
				before: validaProducao(),
				success: function( data )
				{   
					
					location.reload();
				},error: function (error) {
					$("#tableParcelas").css("border","2px solid red");
					$("#tableParcelas").css("opacity","0.8");//define opacidade inicial
					validaProducaoErro();
	              }
			});
			return false;
		}
	});
});

function validaProducao(){
	$(".consolidaBotao")
		.val("Consolidando ...")
		.css("pointer-events", "none");
	$("#itemImage")
		.prepend(
		"<img src='resources/images/ajax-loader-fff.gif' width='20' height='20' alt='loading' id='loader-confirmacao' style='margin-right: 2px;'>").show(500);
}

function validaProducaoErro(){
	$(".consolidaBotao")
	.val("Consolidar Item").css("pointer-events", "all");
	$("#itemImage").hide(1500);
	$("#itemMsg").prepend("<p class='error'>Verifique as condições de parcelamento!</p>");
}

// --------------------------------------------- //
// Financeiro
//Edita Caches
function editaCache(action, valor,idCachePadrao) {

	valor = $("#nomeFunc"+idCachePadrao).val();
	porcentagem = $("#porcent"+idCachePadrao).val();
	
	if(valor == " " || valor == "" || valor == null){
		alert("Digite um nome para funcionario");
		return null;
	}
	$.ajax({
		url : action+"?valor="+valor+"&porcentagem="+porcentagem+"&idCachePadrao="+idCachePadrao,
		success : function(data) {
			$('#sucesso').fadeIn(200);
			$('#sucesso').fadeOut(5000);
		},
		beforeSend : function() {
			
		},
		complete : function() {
			
		}
	});

};

//Edita Valor Fornecedor
function valorF(action, idProducao) {
	
	var valor = $("#valorF"+idProducao).val()
	
	if(valor == " " || valor == "" || valor == null){
		alert("Digite um valor");
		return null;
	}
	
	$.ajax({
		url : action+"?valor="+valor+"&idProducao="+idProducao,
		success : function(data) {
			if(data == 'ok'){
				$("#msgSucesso").text("Dados inseridos com sucesso, clique em atualizar para recalcular valores !");
				$('#wellAlerta').fadeIn(200);
				$('#wellAlerta').fadeOut(4000);
			}

			if(data == 'erro'){
				$('#wellErro').fadeIn(200);
			}
		},
		beforeSend : function() {
			
		},
		complete : function() {
			
		}
	});
	
};

//Edita Observações
function editaCamposProducaoP(id,action, idProducao) {

	var valor = $("#"+id).val();
	
	/*if(valor == " " || valor == "" || valor == null){
		alert("Digite um valor para esse campo ou deixe em branco !");
		return null;
	}*/
	
	$.ajax({
		url : action+"?texto="+valor+"&idProducao="+idProducao,
		success : function(data) {
			if(data == 'ok'){
				$("#msgSucesso").text("Dados inseridos com sucesso !");
				$('#wellAlerta').fadeIn(200);
				$('#wellAlerta').fadeOut(4000);
			}
			
			if(data == 'erro'){
				$('#wellErro').fadeIn(200);
			}
		}
	});
	
};

//Edita Despesas
function editaCamposDespesas(id,action, idDesp, name) {
	
	var valor = $("#"+id).val();
	

	
	
	/*if(valor == " " || valor == "" || valor == null){
		alert("Digite um valor para esse campo ou deixe em branco !");
		return null;
	}*/
	
	$.ajax({
		url : action+"?valor="+valor+"&idDesp="+idDesp+"&name="+name,
		success : function(data) {
			if(data == 'ok'){
				$("#msgSucesso").text("Dados inseridos com sucesso !");
				$('#wellAlerta').fadeIn(200);
				$('#wellAlerta').hide(100);
			}
			
			if(data == 'erro'){
				$('#wellErro').fadeIn(200);
			}
		}
	});
	
};

function mudaCampoData(id){
	$("#"+id).attr("type","date");
}


function pagaContas(idLista,idFornecedor,qtdDias,idLinha) {
	$.ajax({
		url : "pagaConta?idLista="+idLista+"&idFornecedor="+idFornecedor+"&qtdDias="+qtdDias,
		success : function(data) {
			$("#ConfirmaPagamento").fadeOut(500);
			$("#contasPagarAjax").html(data);
		},
		beforeSend : function() {
			$("#ConfirmaPagamento").fadeIn(500);
		},
		complete : function() {
			$("#ConfirmaPagamento").fadeOut(500);
		}
	});
	
};

function editaInfoGalderma(idLista) {
	
	var info = $("#infoGalderma").text();
	
	
	/*$.ajax({
		url : "editaInfoGalderma?idLista="+idLista+"&info="+idFornecedor,
		success : function(data) {
			$("#ConfirmaPagamento").fadeOut(500);
			$("#contasPagarAjax").html(data);
		},
		beforeSend : function() {
			$("#ConfirmaPagamento").fadeIn(500);
		},
		complete : function() {
			$("#ConfirmaPagamento").fadeOut(500);
		}
	});*/
	
};








