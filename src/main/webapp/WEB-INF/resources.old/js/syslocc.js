 $(function() {
     $(".data").datepicker({
          
          format:"DD/MM/yy",
          language: "pt-BR",
          minViewMode: 0
          
     });
  });

  $( "#chamaEstrategia" ).click(function() {
      $( "#estrategia" ).toggle(100);
  });

  $( "#chamaEvento" ).click(function() {
      $( "#evento" ).toggle(100);
  });

  $( "#chamaDemanda" ).click(function() {
      $( "#demanda" ).toggle(100);
  });
 
 $( "#Cadeiras" ).click(function() {
      $( ".cadeiras" ).toggle(300);
  });

 $( "#Cadeiras2" ).click(function() {
      $( ".cadeiras" ).toggle(300);
  });

 $( "#Cadeiras3" ).click(function() {
      $( ".cadeiras" ).toggle(300);
  });

$("#tiraLinha").click(function() {
  $(".linha").toggle(300);
});

$("#tiraLinha2").click(function() {
  $(".linha2").toggle(300);
});

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


//----------------------------- Modal de Tags ------------------------------------------------ //

//JS para preencher valor e placeholder da tela de cadastros de Tag
function modaltag(id){
	$(".form-tag").attr("placeholder", id);
	$(".form-tag").val(id);
	$('.modal').modal('toggle');
};

//--------------------------- Fim Modal Tags ---------------------------------------------- //


//----------------------------- Modal de Impostos ------------------------------------------------ //

//JS para Exibir os dados dos impostos para edição
function modalConfig(id){
	$(".form-tag").attr("placeholder", id);
	$(".form-tag").val(id);
	$('.modal').modal('toggle');
};

//--------------------------- Fim Modal Tags ---------------------------------------------- //

//----------------------------- Modal de Usuarios ------------------------------------------------ //

//JS para 
function modalUser(id){
	alert(id);/*
	$(".form-tag").attr("placeholder", id);
	$(".form-tag").val(id);
	$('.modal').modal('toggle');*/
};

//--------------------------- Fim Modal Usuários ---------------------------------------------- //











