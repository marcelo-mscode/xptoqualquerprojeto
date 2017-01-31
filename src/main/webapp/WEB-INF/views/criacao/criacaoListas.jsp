<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<c:set value="1" var="cont" />
<style type="text/css">
	.criacao{background: #f1f1f1;}
</style>

<div class="row" style="background-color: #fff;padding: 20px;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
	<div class="col-md-12"> 
	
	  <h3 style="font-family: 'OpenSansLight';font-weight: bolder;">Jobs em Aberto na Criação</h3>
		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
			<tr>
			
				<th>Cod Job</th>
				<th>Nome da Lista</th>
				<th>Empresa</th>
				<th>Job</th>
				<th>Responsável<%-- <i class="glyphicon glyphicon-pencil cor-lapis" onclick="trocaResponsavel(responsavel${cont},listaResponsaveis${cont});" style="cursor: pointer;float: right;margin-right: 5px;
	    				margin-top: -1px;"></i> --%></th>
				<th>2° <%-- <i class="glyphicon glyphicon-pencil cor-lapis" onclick="trocaResponsavel(par${cont},listaPar${cont});" style="cursor: pointer;float: right;margin-right: 5px;
	    				margin-top: -1px;"></i> --%></th>
				<th>Versão</th>
				<th>Status</th>
				<th>Prazo para Proposta</th>
				<!-- <th>Dias para Proposta</th> -->
			</tr>
			<c:forEach items="${lista}" var="lista">
					
				<c:if test="${lista.criacaoStatus.status =='Fechado'}">  </c:if>
	
				<c:if test="${lista.criacaoStatus.status !='Fechado'}">
				
				<tr>
					<td style="font-size: 12px!important;">${lista.listaProducao.idJob.codJob}</td>	
					<td style="font-size: 12px!important;"><a href="exibeItens?idCriacaoLista=${lista.idCriacaoLista}">${lista.listaTitulo}</a></td>
					<td style="font-size: 12px!important;">${lista.listaProducao.idJob.empresa.empresa}</td>
					<td style="font-size: 12px!important;">${lista.listaProducao.idJob.titulo}</td>					
					<td>
					<div class="input-180px" id="responsavel${cont}" style="display: block;font-size: 12px;">${lista.usuarioReponsável.nome}</div>	
					
					<div class="display-none input-160px" id="listaResponsaveis${cont}">
						<select class="form-control" onchange="mudaResponsavel(this.value,${lista.idCriacaoLista});" style="font-size: 12px;width: 161px;">
								<option value="selecione">Selecione Resp.</option>
							<c:forEach items="${listaUsuarios}" var="lsitaUsuarios">
								<option value="${lsitaUsuarios[0]}">${lsitaUsuarios[1]}</option>
							</c:forEach>
						</select>
					 </div>	
					
					 <i class="glyphicon glyphicon-pencil cor-lapis" onclick="trocaResponsavel(responsavel${cont},listaResponsaveis${cont});" style="cursor: pointer;float: right;margin-right: 5px;
	    				margin-top: -17px;"></i>
					</td>
					
					<td>
					<div class="input-180px" id="par${cont}" style="display: block;font-size: 12px;">
						<c:if test="${lista.parReponsavel.nome == null}"> --------- </c:if>
						<c:if test="${lista.parReponsavel.nome != null}">${lista.parReponsavel.nome}</c:if>
					</div>	
					
					<div class="display-none input-160px" id="listaPar${cont}">
						<select class="form-control" onchange="mudaPar(this.value,${lista.idCriacaoLista});" style="font-size: 12px;width: 161px;">
								<option value="selecione">Selecione Resp.</option>
							<c:forEach items="${listaUsuarios}" var="lsitaUsuarios">
								<option value="${lsitaUsuarios[0]}">${lsitaUsuarios[1]}</option>
							</c:forEach>
						</select>
					 </div>	
					 <i class="glyphicon glyphicon-pencil cor-lapis" onclick="trocaResponsavel(par${cont},listaPar${cont});" style="cursor: pointer;float: right;margin-right: 5px;
	    				margin-top: -17px;"></i>
					</td>
					
					
					
					
					<c:set var="versao" value="${lista.versao}" />
						
						<c:if test="${versao > 1 }">
							<td class="amarelo" style="text-align: center;">${versao}</td>
						</c:if>

						<c:if test="${versao == 1 }">
							<td style="text-align: center;">${versao}</td>
						</c:if>
					
					<td>${lista.criacaoStatus.status}</td>


					
	<c:forEach items="${joda}" var="joda" varStatus="loop">
		<c:forEach items="${datas}" var="datas" varStatus="loop2">
			<c:if test="${lista.listaProducao.idJob.idJob == joda[0]}">
			    <c:if test="${datas[0] == joda[0]}">
						
						<c:if test="${datas[2][2] < 0 || datas[2][2] == 0}">
							<td class="vermelhoFlat" style="color: #fff" >
								<fmt:formatDate value="${datas[1]}" pattern="dd/MM/yyyy HH:mm" />
							</td>
						</c:if>
						
						<c:if test="${datas[2][2] >= 1 && datas[2][2] <= 5 }">
							<td class="amareloFlat">
								<fmt:formatDate value="${datas[1]}" pattern="dd/MM/yyyy HH:mm" /><br>
							</td>
						</c:if>
						
						<c:if test="${datas[2][2] > 5 }">
							<td>
							<fmt:formatDate value="${datas[1]}" pattern="dd/MM/yyyy HH:mm" />
							</td>
						</c:if>
				</c:if>
			</c:if>
					 		
					<c:if test="${lista.listaProducao.idJob.idJob != joda[0]}"></c:if>
					
		</c:forEach>
	</c:forEach>


				
				<tr>	
				<c:set value="${cont + 1}" var="cont" />
				  </c:if>
		 </c:forEach>
			
			
			
			
		</table>
						  
	</div>	
  </div>
  
  <div class="divisor"></div>
  <div class="divisor"></div>
  <div class="row" style="background-color: #fff;padding: 20px;box-shadow: -1px 2px 9px 0px #AEAEAE;">
    <div class="col-md-12">
  <h3 style="font-family: 'OpenSansLight';font-weight: bolder;">Jobs Fechados na Criação</h3>
  
  <select class="form-control input-180px" onchange="exibeEmpresaCriacao(this.value)" style="margin-bottom: 10px">
  		<option value="selecione">Selecione Empresa</option>
  	<c:forEach items="${empresas}" var="empresas">
	  	<option value="${empresas[0]}">${empresas[1]}</option>
  	</c:forEach>
  </select>
  
  
  
      <table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
			<tbody   id="jobFechados">
			<tr>
				<th>Cod Job</th>
				<th>Nome da Lista</th>
				<th>Empresa</th>
				<th>Job</th>
				<th>Responsável</th>
				<th>2º</th>
				<th>Versão</th>
				<th>Status</th>
				<th>Pzo Proposta</th>
				<th>Dt Fechamento</th>
			</tr>
		<c:forEach items="${listasFechadas}" var="listasFechadas">
				<c:if test="${listasFechadas.criacaoStatus.status !='Fechado'}"></c:if>
	
				<c:if test="${listasFechadas.criacaoStatus.status =='Fechado'}">
					<tr>
						<td style="font-size: 12px!important;width: 70px">${listasFechadas.listaProducao.idJob.codJob}</td>	
						<td style="font-size: 12px!important;"><a href="exibeItens?idCriacaoLista=${listasFechadas.idCriacaoLista}">${listasFechadas.listaTitulo}</a></td>
						<td style="font-size: 12px!important;">${listasFechadas.listaProducao.idJob.empresa.empresa}</td>
						<td style="font-size: 12px!important;">${listasFechadas.listaProducao.idJob.titulo}
						<td style="font-size: 12px!important;width: 120px">${listasFechadas.usuarioReponsável.nome}</td>
						<td style="font-size: 12px!important;width: 120px">${listasFechadas.parReponsavel.nome}</td>

						<c:set var="versao" value="${listasFechadas.versao}" />
						
						<c:if test="${versao > 1 }">
							<td class="amarelo" style="text-align: center;">${versao}</td>
						</c:if>

						<c:if test="${versao == 1 }">
							<td style="text-align: center;">${versao}</td>
						</c:if>

						<c:if test="${empty versao}">
							<td style="text-align: center;">1</td>
						</c:if>


						<td style="font-size: 12px!important;" class="verde">${listasFechadas.criacaoStatus.status}</td>
						<td style="font-size: 12px!important;"><fmt:formatDate value="${listasFechadas.listaProducao.idJob.propostaData}" pattern="dd/MM/yyyy HH:mm" /></td>
	 				    <td style="font-size: 12px!important;"><fmt:formatDate value="${listasFechadas.dataFechamento.time}" pattern="dd/MM/yyyy HH:mm" /></td>
					</tr>	
				</c:if>
		 </c:forEach>
			
			
			
			</tbody>
		</table>
    
    
    
    </div>
  	
  </div>	


<br><br><br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>

