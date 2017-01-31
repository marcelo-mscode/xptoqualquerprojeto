<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   




<%-- <div id="escura" style="border: 1px solid red;width: 50%;height: 800px;background: #fff;position: absolute;z-index: 9999;margin-top: -60%;margin-left: 20%;display: none" >

	<div class="col-md-1">
		<a href="<c:url value="/logout" />">SAIR</a>
	</div>

</div>
 --%>

<!-- <div>

<div class="col-md-12" style="bottom: 0;position: fixed;text-align: center;background: red">
	<h1 style="color: yellow;font-family: 'OpenSansLight'">AMBIENTE DE TESTE</h1>
</div>

</div>l
 -->


<script type="text/javascript" src="<c:url value="resources/js/pace.min.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/jquery-1.11.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/bootstrap.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/bootstrap-filestyle.min.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/jquery-ui.min.js" />"></script>

<script type="text/javascript" src="<c:url value="resources/js/jquery.maskMoney.min.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/jquery.maskedinput.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/validator.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/syslocc.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/comunicador.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/validaFormularios.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/jquery.validate.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/notificacoes.js" />"></script>
<%-- <script type="text/javascript" src="<c:url value="resources/js/jquery.nicescroll.min.js" />"></script> --%>

<script>

$(document).ready(function(){
	
	$.getJSON("notificacao",
		function(result) {
		
			if(result > 9 && result.z != 0){
				$(".notificacao").css("padding","4px 3px");
				$(".pendencia").css("padding","4px 3px");
				$(".notificacaoTarefa").css("padding","4px 3px");
			}		
			else{

			}
			
		    if(result.z != 0){
				$(".notificacao").html(result.z);
			}
			if(result.x != 0){
				$(".pendencia").html(result.x);
			}
			if(result.y != 0){
			  	$(".notificacaoTarefa").html(result.y);
			}
			
	});
});

</script>
</body>
</html>
    
    