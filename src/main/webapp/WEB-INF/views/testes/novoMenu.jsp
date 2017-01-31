<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html lang="pt">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<title>SysLocc</title>
	<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/syslocc.css"  />" />
	<link rel="stylesheet" href="<c:url value="resources/css/jquery-ui-1.8.18.custom.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/novosyslocclayout.css" />" />

</head>


<style>

.ui-sortable {color:red}
.ui-sortable-helper {color:blue}
.ui-state-highlight {padding:  5px 10px;}



</style>


<body>

<div class="row" style="margin-top: 0">

	
	<div class="col-md-12 shadow" style="box-shadow: 0px 10px 30px 0px #AEAEAE;">
		
<!-- --------------------------------------------------------------------------------------------------------  -->				
<nav class="navbar navbar-default navbar-static-top tira-padding">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
   
        <a class="navbar-brand" href="index.html">Página Inicial</a>

        
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        
        <ul class="nav navbar-nav">
	        <li class="dropdown">
	            <span style="color:#fff !important">JOB</span>
	            <ul class="dropdown-menu" role="menu">
	              
	              <li><a href="listajob">Jobs</a></li>
	              <li><a href="producao.html">Listas de Produção</a></li>
	              <li class="divider"></li>
	              <li><a href="#" class="disab">Demanda Interna</a></li>
	              <li><a href="#" class="disab">Orçamentos</a></li>
	            </ul>
	       </li>
        </ul>
        
             
        <ul class="nav navbar-nav">
          
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" style="padding-left: 0;" >JOB</a>
            <ul class="dropdown-menu" role="menu">
              
              <li><a href="listajob">Jobs</a></li>
              <li><a href="listaProducao">Listas de Produção</a></li>
              <li class="divider"></li>
              <li><a href="#" class="disab">Demanda Interna</a></li>
              <li><a href="#" class="disab">Orçamentos</a></li>
            </ul>
          </li>
        </ul>
	
			


	
      
  	  <ul class="nav navbar-nav">
  	  	  <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">CADASTROS</a>
            <ul class="dropdown-menu" role="menu">
            	
              <li><a href="empresa.html">Empresa</a></li>
              <li><a href="produto.html">Produto</a></li>
              <li><a href="usuario.html">Usuário</a></li>
              <li><a href="tag.html">Tags</a></li>
              <li><a href="marcas.html">Marcas</a></li>
              <li class="divider"></li>
              <li><a href="imposto">Configuração de Impostos</a></li>
              <!-- <li><a href="#" class="disab">Perfil</a></li> -->
            </ul>
          </li>
        </ul>		
	
		<ul class="nav navbar-nav">
          
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">CONSULTAS</a>
            <ul class="dropdown-menu" role="menu">
              
              <li class="disab"><a href="listajob">Jobs</a></li>
              <li><a href="listaProducao">Listas de Produção</a></li>
              <li><a href="listaempresa">Empresas</a></li>
              <li><a href="#" class="disab">Prospecção</a></li>
              <li class="divider"></li> 
              <li ><a href="<c:url value="/listaProdutos" />">Produtos</a></li>
              <li ><a href="<c:url value="/listausuario" />">Usuários</a></li>
              <li ><a href="<c:url value="/tag" />">Tags</a></li>
              <li ><a href="<c:url value="/marcas" />">Marcas</a></li>
              <!--<li><a href="#">Orçamentos</a></li> -->
            </ul>
          </li>
        </ul>	
		
		 <ul class=	"nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">PRODUÇÃO</a>
            <ul class="dropdown-menu" role="menu">
              <li class="disab"><a href="<c:url value="/menuProducao" />">Produção</a></li>
             </ul>
              
       	  </li>
     	 </ul> 
     	 
     	 <ul class=	"nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">TESTES</a>
            <ul class="dropdown-menu" role="menu">
              <li class="disab"><a href="<c:url value="/testes" />">Retorno Array</a></li>
              <li class="disab"><a href="<c:url value="/testesLayout" />">Layout</a></li>
              <li class="disab"><a href="<c:url value="/tabelas" />">Tabelas</a></li>
              <li class="disab"><a href="<c:url value="/novoMenu" />">Novo Menu</a></li>
             </ul>
              
       	  </li>
     	 </ul>
     	 
     	 
     	 
     	 
     	  	

        <ul class="nav navbar-nav">
          
          <li class="dropdown">
            <a href="#" class="dropdown-toggle disab" data-toggle="dropdown" role="button" aria-expanded="false">FINANCEIRO</a>
            <ul class="dropdown-menu" role="menu">
              <li>Em desenvolvimento</li>
              <!-- <li><a href="#">Novo</a></li>
              <li><a href="#">Jobs</a></li>
              <li><a href="#">Produção</a></li>
              <li><a href="#">Demanda Interna</a></li>
              <!-- <li class="divider"></li>
              <li><a href="#">Orçamentos</a></li> -->
            </ul>
          </li>
        </ul>
		<ul class="nav navbar-nav navbar-right">
          <li><a href="#">SAIR</a></li>
          
        </ul>
  	  <!--  <ul class=	"nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle disab" data-toggle="dropdown" role="button" aria-expanded="false">PRODUÇÃO</a>
         </li>
      </ul>  -->      
             <!-- <li>Em desenvolvimento</li>
              <li><a href="#">Novo</a></li>
              <li><a href="#">Jobs</a></li>
              <li><a href="#">Produção</a></li>
              <li><a href="#">Demanda Interna</a></li> 
              --> <!-- <li class="divider"></li> 
              <li><a href="#">Orçamentos</a></li>
            </ul>
          </li>
        </ul>

        
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">SAIR</a></li>
          
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>    




<!-- --------------------------------------------------------------------------------------------------------  -->				
		
		
		
		<div class="col-md-12 bodyXY" style="height: 35px;">
					
				<ol class="breadcrumb">
				    <li><a href="#">Menu</a></li>
				    <li><a href="listaempresa">Empresas</a></li>
				    <li class="active">Nova Empresa</li>
				</ol>
					
		</div>
    </div>
</div>
<br /><br />



<div class="row" style="padding: 50px">
	<div class="ordem ui-widget-content ui-corner-all" style="padding: 45px 25px">
		<c:forEach items="${categoria}" var="categoria">
			<div class="ui-state-default" id="id-${categoria[0]}" style="padding: 5px 10px;">${categoria[1]}
			
			 </div>
		</c:forEach>
	</div>
	<a onclick="ordenar();" class="btn btn-success">Mandar</a>
</div>
<br /><br />

<c:import url="../_comum/footer.jsp" />        

<script type="text/javascript">
$(function(){

	var opcoesOrdem = {
			stop: function (event, ui) {
				axis: 'y'
			}
	}
	$('.ordem').sortable(opcoesOrdem);
});

function ordenar(){
			var objSerie2 = $('.ordem').sortable('serialize', {key: 'id'});		
		
			alert(objSerie2);
			
/* 			$.ajax({
				url : "testeId?" + objSerie2,
				success : function(data) {
					$("#produtoAtuacao").html(data);
				}
			}); */
}
 
</script>

