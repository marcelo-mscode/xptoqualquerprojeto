function reportarBug(tipo){

	var uAtual = window.location.href.toString();
	var uAnterior = document.referrer.toString();
	var urlatual = uAtual.replace(/&/g,"--s--");
	var urlanterior = uAnterior.replace(/&/g,"--s--");
	var tipoErro = tipo;
	
	$(".mais").fadeIn(200);
	
	$.ajax({
		url : "reportarBug?urlAtual=" + urlatual +"&urlAnterior="+urlanterior+"&tipoErro="+tipoErro,
		success : function(data) {
		},
		beforeSend : function() {
			
		},
		complete : function() {
			$(".mais").hide(200);
			$(".botaoVoltar").hide(300);
			$(".back").append("<a href='"+uAnterior+"' class='btn btn-success' style='margin-left: 15%;' >Voltar</a>");
			$('.bug500').append("<span>Obrigado! Clique em voltar e aguarde enquanto analisamos esse erro. ");
		}
	});
}	
	
function inicio(tipo){
	$(document).ready(function() {
		$(".botaoVoltar").css("pointer-events", "none")
		coletando(tipo);
	});
}
	
function coletando(tipo){
	$(".coletando").fadeIn(500);
	
	window.setTimeout(function()
    {
		$(".coletando").fadeOut(500);
    }, 3000);

	window.setTimeout(function()
    {
		enviando(tipo);
    }, 4000);

}

function enviando(tipo){
	$(".coletando").fadeOut(500);
	$(".enviando").fadeIn(500);
window.setTimeout(function()
		    {
			 reportarBug(tipo); 
			 $(".enviando").fadeOut(500);
		    }, 3000);
}
