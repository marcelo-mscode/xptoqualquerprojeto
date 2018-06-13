<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   

<!DOCTYPE html>
<html lang="pt">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="<c:url value="resources/images/iconS.ico" />" />

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
	<title>SysLocc</title>
	<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/syslocc.css"  />" />
	<link rel="stylesheet" href="<c:url value="resources/css/jquery-ui-1.8.18.custom.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/novosyslocclayout.css" />" />
	<link rel="stylesheet" href="<c:url value="resources/css/font-awesome.min.css" />" />
	
</head>
<body>

<security:authentication property="principal" var="user"/>

<div class="row" style="margin: 0;padding: 0">

  <div class="col-md-12 ajusteMenuFinanceiro" style="padding: 0;">
		
<!-- --------------------------------------------------------------------------------------------------------  -->				
<nav class="navbar navbar-default navbar-static-top tira-padding  ajusteFinanceiroBar"  style="margin-bottom: 0;height: 45px;">
    <div class="container-fluid" style="padding-right: 0">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
   
        <a class="navbar-brand" href="index.html" style="margin-top: -1px;border-right: 1px solid #ddd;
         				   margin-right: 15px;margin-left: 5px;padding-right: 20px;padding-left: 0px;">
	        <i class="glyphicon glyphicon-home"></i>
        </a>

        
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" style="padding-left: 17px;" >JOB</a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="listajob">Jobs</a></li>
              <li><a href="listaProducao">Listas de Produção</a></li>
              <li class="divider"></li>
              <li><a href="job">Novo Job</a></li>
              <!-- <li><a href="#" class="disab">Demanda Interna</a></li> -->
              <!-- <li><a href="#" class="disab">Orçamentos</a></li> -->
            </ul>
          </li>
        </ul>
      
  	  <ul class="nav navbar-nav">
  	  	  <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">CADASTRAR</a>
            <ul class="dropdown-menu" role="menu">
            	
              <li><a href="empresa.html">Empresa</a></li>
              <li><a href="produto.html">Produto</a></li>
             
             <security:authorize access="hasRole('ROLE_ADMIN')"> 
	              <li><a href="usuario.html">Usuário</a></li>
             </security:authorize>
             
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
              
              <!-- <li class="disab"><a href="listajob">Jobs</a></li>
              <li><a href="listaProducao">Listas de Produção</a></li> -->
              <li><a href="listaempresa?first=0&max=100">Empresas</a></li>
              <!-- <li><a href="#" class="disab">Prospecção</a></li> -->
              <li class="divider"></li> 
              <li ><a href="<c:url value="/listaProdutos" />">Produtos</a></li>
              <li ><a href="<c:url value="/listausuario" />">Usuários</a></li>
              <li ><a href="<c:url value="/tag" />">Tags</a></li>
              <li ><a href="<c:url value="/marcas" />">Marcas</a></li>
              <!--<li><a href="#">Orçamentos</a></li> -->
            </ul>
          </li>
        </ul>	
        
<%-- <security:authorize access="hasRole('ROLE_ADMIN')"> --%> 	
		
		 <ul class=	"nav navbar-nav">
          <li class="dropdown producao">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">PRODUÇÃO</a>
            <ul class="dropdown-menu" role="menu">
              <li class="disab"><a href="<c:url value="/menuProducao" />">Produção</a></li>
             </ul>
              
       	  </li>
     	 </ul> 
     	 
<%-- </security:authorize>  --%>    	 
     	
     	
     	 <ul class=	"nav navbar-nav">
          <li class="dropdown criacao">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">CRIAÇÃO</a>
            <ul class="dropdown-menu" role="menu">
               <li class="disab"><a href="<c:url value="/exibeLista" />">Criação</a></li>
                
                <security:authorize access="hasRole('ROLE_ADMIN_CRIACAO') or hasRole('ROLE_ADMIN')">
	               <li class="dropdown-submenu">
	               	    <a tabindex="-1" href="#">Relatório</a>
		                <ul class="dropdown-menu">
		                  <li><a tabindex="-1" href="<c:url value="/porLista" />" >Por Lista</a></li>
		                  <li><a tabindex="-1" href="<c:url value="/porUsuario" />" >Por Usuario</a></li>
		                </ul>
	               </li>
				 </security:authorize>
			 
			 </ul>
		   </li>
         </ul>
              
       	
     	 
     	 
     	 <ul class=	"nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="margin-right: 5px">PENDÊNCIAS
            	<span class="badge notificacao" ></span>
            </a>
            <ul class="dropdown-menu" role="menu">
              <li class="disab"><a href="<c:url value="/exibePendencias" />">Pendências <span class="badge pendencia" ></span> </a></li>
              <li class="disab"><a href="<c:url value="/exibeTarefas" />">Tarefas <span class="badge notificacaoTarefa" ></span> </a></li>
           <%--    <li class="disab"><a href="<c:url value="/exibePendencias" />">Demanda Interna <span class="badge notifDemanda" ></span> </a></li> --%>
             </ul>
              
       	  </li>
     	 </ul> 
	 
	 <security:authorize access="hasRole('ROLE_ADMIN')">  
     	 <ul class=	"nav navbar-nav">
          <li class="dropdown prospeccao">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="margin-right: 5px">
	          PROSPECÇÃO
            </a>
            <ul class="dropdown-menu" role="menu">
              <li class="disab"><a href="<c:url value="/prospeccoes" />">Prospecções</a></li>
              <li class="disab"><a href="<c:url value="/novaProspeccao" />">Nova Prospecção</a></li>
             </ul>
       	  </li>
     	 </ul> 
     </security:authorize>	 
     	 
    
    <security:authorize access="hasRole('ROLE_ADMIN')"> 	 
        <ul class="nav navbar-nav">
          <li class="dropdown financeiro">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">FINANCEIRO</a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="<c:url value="resumoMesIndex" />">Resumo do Mês</a></li>
               <li class="divider"></li>
              <li><a href="<c:url value="listasInternas" />">Planilha interna</a></li>
              <li><a href="<c:url value="contasPagar" />">Contas Pagar</a></li>
              <li><a href="<c:url value="contasReceber" />">Contas Receber</a></li>
              <li><a href="<c:url value="relatorioEventosIndex" />">Relatório de Eventos</a></li>
              <li class="dropdown-submenu">
                <a tabindex="-1" href="<c:url value="/indexAnalitico" />">Analítico</a>
                <ul class="dropdown-menu">
                  <li><a tabindex="-1" href="<c:url value="/indexAnalitico" />" >Relatórios</a></li>
                  <li><a tabindex="-1" href="<c:url value="/novoAnalitico" />" >Novo Relatório</a></li>
                </ul>
              </li>
              <li class="divider"></li>
	         <%--  <li><a href="<c:url value="relatorioCache" />">Relatório Caches</a></li> --%>
	          <li><a href="<c:url value="cachePadrao" />"><i class="glyphicon glyphicon-cog" style="top: 2px;margin-right: 5px"></i>Cache Padrão</a></li>
			  <%-- <li><a href="<c:url value="cachePadrao" />"><i class="glyphicon glyphicon-cog" style="top: 2px;margin-right: 5px"></i>Categorias</a></li> --%>
            </ul>
          </li>
        </ul>
    </security:authorize>    
        

<!--         <ul class="nav navbar-nav" style="float: right;background: #f05739;color: #ecf0f1;height: 49px;"> -->
        <ul class="nav navbar-nav ajusteFinanceiro" style="float: right;height: 49px;">
          
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="color: #777">
            <i class="glyphicon glyphicon-user" style="color: #777;font-size: 17px;margin-right: 5px;top: 1px;"></i>
            Olá ${user.usuario.nome}</a>
            
			<ul class="dropdown-menu" role="menu">
			
			 <security:authorize access="hasRole('ROLE_DESENVOLVEDOR')">
					
							<li class="disab"><a
								href="<c:url value="/listaBugs" />">Bugs</a></li>
							<li class="disab"><a
								href="<c:url value="/admSistema" />">Atualizações no
									Sistema</a></li>
							<li class="disab"><a
								href="<c:url value="/testesLayout" />">Layout</a></li>
							<li class="disab"><a href="<c:url value="/tabelas" />">Tabelas</a></li>
							<li class="disab"><a href="<c:url value="/novoMenu" />">Novo
									Menu</a></li>
						
  		    </security:authorize>

			<security:authorize access="hasRole('ROLE_ADMIN')"> 	 
              <li><a href="<c:url value="/admSistema" />"><i class="glyphicon glyphicon-cog" style="top: 2px;padding-right: 10px;"></i>Configurações</a></li>
            </security:authorize>
              
              
              
              <li><a href="<c:url value="/logout" />">SAIR</a></li>
        	</ul>
        </li>
        </ul>	
        
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>    
<!-- --------------------------------------------------------------------------------------------------------  -->				

</div>
</div>

