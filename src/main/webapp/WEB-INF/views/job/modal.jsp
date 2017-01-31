<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css" />" />
<link rel="stylesheet" href="<c:url value="resources/css/syslocc.css"  />" />
<link rel="stylesheet" href="<c:url value="resources/css/jquery-ui-1.8.18.custom.css" />" />
<script type="text/javascript" src="<c:url value="resources/js/jquery-1.11.2.min.js" />"></script>


 <style type="text/css">
        div {
            width: 600px;
            height: 600px;
        }
        .loader {
            display: none;
            float: left;
        }
    </style>




<script type="text/javascript">

function modalt(id){
	console.log();
	$.ajax("jobModal?f=" + id).done(function(dados){
		console.log("Voltei");
		$("#chama").html(dados);


		$('.modal').modal('toggle');
			

		
	})
		
	
		
		
};

</script>




</head>
<body>

<a href="#" onclick="modalt('A | B');">Modal</a>


<br><br><br>



      <img src="http://www.eventwalls.ca/images/gallery-loader.gif" class="loader" alt="loading" />
      <input type="button" value="AJAX!">
      <div>&nbsp;</div>
 
  <script type="text/javascript">
      $('input').click(function(){ //Quando clicado no elemento input
          $.ajax({
              url: 'jobModal',
              success: function(data) {
                $('div').html(data);
                //alert(data);
              },
              beforeSend: function(){
                $('.loader').css({display:"block"});
              },
              complete: function(){
                $('.loader').delay(2000).css({display:"none"});
              }
        });
      });
  </script>


<!-- Button trigger modal -->


<!-- Modal -->
<form action="" method="post">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal de Teste ok!</h4>
      </div>
      <div class="modal-body">
          <div id="chama">
          	
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Salvar Tag</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
       
      </div>
    </div>
  </div>
</div>
</form>	













<script type="text/javascript" src="<c:url value="resources/js/bootstrap.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/jquery-ui.min.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/syslocc.js" />"></script>
</body>
</html>