<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
<style type="text/css">
	.criacao{background: #f1f1f1;}
</style>
<div class="col-md-12 bodyXY" style="height: 35px;">
					
		  <ol class="breadcrumb">
		    <li><a href="#">Menu</a></li>
		    <li><a href="exibeLista">Listas de Criação</a></li>
		    <li class="active">Itens da Lista</li>
		  </ol>					
		</div>
</div>
</div>
<br />
<br />

<div class="container" style="margin: 0;width: 100%;">
 <div class="row" style="background-color: #fff;padding: 0px 0 20px 0;box-shadow: -1px 2px 9px 0px #AEAEAE;">

 	<c:forEach items="${itens}" var="itens">
 		<c:set value="${itens.criacaoLista.listaTitulo}"  var="tituloLista" />
 		<c:set value="${itens.criacaoLista.idCriacaoLista}"  var="idLista" />
 		<c:set value="${itens.criacaoLista.listaProducao.idJob.titulo}"  var="jobTitulo" />
 		<c:set value="${itens.criacaoLista.criacaoStatus.status}"  var="statusLista" />
 		<c:set value="${itens.criacaoLista.usuarioReponsável.idUsuario}"  var="idUsuario" />
 	</c:forEach>
 	
 	<div class="col-md-12" > 
 	 <h4 style="font-family: 'OpenSansLight';font-size: 20px">Itens da Lista: <span style="font-weight: bolder;">${tituloLista}</span> 
 	 
 	 <c:if test="${statusLista == 'Fechado'}">
		<span  style="font-family: 'OpenSansLight';font-size: 20px">(Lista Fechada)</span> 	 
 	 </c:if>
 	 <br>
 	 Job: <span style="font-weight: bolder;">${jobTitulo}</span>
 	 </h4>
 	 
 	 
 	 	
		<table class="table table-striped table-td-ajuste table-hover table-condensed">
			<tr class="">
				<th class="input-200px">Item</th>
				<th class="input-340px">Informações</th>
				<th class="input-60px" style="text-align: center;">Qtd.</th>
				<th>Lider</th>
				<th>2°</th>
				<th class="input-60px" style="text-align: center;">Versão</th>
				<th class="input-120px">Dt Entrada</th>
				<th class="input-140px">Status</th>
				<th class="input-50px" style="text-align: center;">3D</th>
			</tr>

			<c:forEach items="${itens}" var="itens">
  			 <tr>

				<td class="font-tabela"><a href="exibeDetalhesDoItem?idCriacaoItem=${itens.idCriacaoItemLista}">${itens.tituloItem}</a></td>
				<td class="font-tabela" style="font-size: 10px !important">
				<c:if test="${empty itens.informaoesItem}"></c:if>				
				<c:if test="${not empty itens.informaoesItem}">				
				
						<c:forTokens items="${itens.informaoesItem}" delims=" " var="word" varStatus="status">  
                           <c:if test="${status.count < 8}">  
                               <c:out value="${word}" escapeXml="false"/>  
                           </c:if>  
                        </c:forTokens>
						<span style="font-size: 10px;">...</span>
						
				</c:if>

				</td>

					<c:forEach items="${itens.grupo.produtoGrupo}" var="produtoGrupo">
						<c:set value="${produtoGrupo.quantidade}" var="qtd" />
						<c:set value="${produtoGrupo.quantidade2}" var="qtd1" />
						<c:set value="${produtoGrupo.idGrupo.idgrupo}" var="idGrupo" />
					</c:forEach>
					
					
					<c:if test="${itens.tituloItem == 'Template' || itens.tituloItem == 'CD/Tranfer' || itens.tituloItem == 'PPT de apresentação' }">
						<td style="text-align: center;"> ------ </td>
					</c:if>
											
					
					<c:if test="${itens.tituloItem != 'Template' && itens.tituloItem != 'CD/Tranfer' && itens.tituloItem != 'PPT de apresentação' }">
					
						<c:if test="${itens.quantidade != null}">
							<td style="text-align: center;">${itens.quantidade}</td>
						</c:if>

						<c:if test="${itens.quantidade == null}">
							<td style="text-align: center;">${qtd * qtd1}</td>
						</c:if>
	
					
					</c:if>
					
				    <td class="font-tabela">${itens.criacaoLista.usuarioReponsável.nome}</td>
				
					<c:choose>
					    <c:when test="${itens.itemDelegado == false}">
					        <td class="font-tabela">
					        	${itens.responsavelItem.nome}
						    </td>
					    </c:when>
	
					    <c:when test="${itens.responsavelItem.idUsuario eq itens.criacaoLista.usuarioReponsável.idUsuario}">
					        <td class="font-tabela">
					        	${itens.responsavelItem.nome}
						    </td>
					    </c:when>
					    
						<c:otherwise>
							<td class="font-tabela" style="border-right : 2px solid red">
								${itens.responsavelItem.nome}
						    </td>
						</c:otherwise>
				    </c:choose>
									
				
				<td class="font-tabela" style="text-align: center;">${itens.versao}</td>
				<td class="font-tabela"><fmt:formatDate value="${itens.dataCriacao.time}" pattern="dd/MM/yyyy HH:mm"/></td>
				
					<c:choose>
					    <c:when test="${itens.criacaoItemStatus.status == 'Fechado' or itens.criacaoItemStatus.status == 'Excluído'}">
					        <td class="font-tabela verde">
					    </c:when>
		
					    <c:when test="${itens.criacaoItemStatus.status == 'Pendente'}">
					        <td class="font-tabela amarelo">
					    </c:when>
		
					    <c:when test="${itens.criacaoItemStatus.status == 'Interrompido'}">
					        <td class="font-tabela brancoFlat">
					    </c:when>
	
					    <c:when test="${itens.criacaoItemStatus.status == 'Em Execução'}">
					        <td class="font-tabela azul">
					    </c:when>
	
					    <c:when test="${itens.criacaoItemStatus.status == 'Excluido do Job'}">
					        <td class="font-tabela verde">
					    </c:when>
					    
					    <c:otherwise>
					        <td class="font-tabela">
					    </c:otherwise>
					</c:choose>
				
				 <c:forEach items="${itens.criacaoItemPendencia}" var="resp">
					<c:set value="${resp.responsavelItem.nome}" var="responsavel" />
				 </c:forEach>
			
				<c:if test="${itens.criacaoItemStatus.status == 'Pendente'}">
					${itens.criacaoItemStatus.status}<br>com: ${responsavel}    
				</c:if>
				
				<c:if test="${itens.criacaoItemStatus.status != 'Pendente'}">
					${itens.criacaoItemStatus.status}
				</c:if>	
				
				</td>
				
				<td style="text-align: center;">
				
				
				    <c:if test="${itens.item3D == false && itens.criacaoItemStatus.status == 'Em aberto'
				    		   || itens.item3D == false && itens.criacaoItemStatus.status == 'Interrompido'
				    		   || itens.item3D == false && itens.criacaoItemStatus.status == 'Pendente'
				    		   || itens.item3D == false && itens.criacaoItemStatus.status == 'Em Execução'
				    		   || itens.item3D == false && itens.criacaoItemStatus.status == 'Pendencia Finalizada'
				    }">
					 	<input type="checkbox" onclick="cria3D(${itens.idCriacaoItemLista});">
				   </c:if>
   
				</td>
				
			<tr>	
			</c:forEach>
			
			
		</table>
</div>
<div class="col-md-12">		
			
			<div class="col-md-4">
			
				<c:if test='${empty fechaLista && statusLista != "Fechado"}'>
					<a onclick="concluiListaCriacaoPorAjax(${idLista})" class="btn btn-danger" style="padding: 10px" >Fechar Lista de Criação</a>
				</c:if>
				
				<c:if test='${not empty fechaLista || statusLista == "Fechado"}'>
					<a onclick="" class="btn btn-danger is-disabled" style="padding: 10px" >Fechar Lista de Criação</a>
				</c:if>
				
			</div>
			
			<div class="col-md-6" >
				
				<form action="salvarDi" method="post" class="display-none" id="novaDi" style="padding: 20px;border: 1px solid #ddd;">
				<h4>Nova Demanda Interna</h4>	
				<input type="hidden" value="${idLista}" name="idCriacaoListaTransiente">
				<input type="hidden" value="${idGrupo}" name="idGrupoTransiente">
				<input type="hidden" value="${idUsuario}" name="responsavelItemTransiente">
				
					Item: <input type="text" class="form-control" name="tituloItem"/>
					Informações do item: <textarea rows="5" class="form-control" name="informaoesItem"></textarea>
					Quantidade:<input type="text" class="form-control input-80px" name="quantidadeTransiente"/>
					<div class="divisor-fino"></div>
						<button class="btn btn-info">Salvar</button>
				</form>
			</div>	 
			
			<div class="col-md-2">
				<c:if test='${statusLista != "Fechado"}'>
					<button class="btn btn-danger" style="padding: 10px;float: right;" onclick="efeitoToogle(novaDi)" >Criar Demanda Interna</button>
				</c:if>
				
				<c:if test='${statusLista == "Fechado"}'>
					<button class="btn btn-danger is-disabled" style="padding: 10px;float: right;" onclick="" >Criar Demanda Interna</button>
				</c:if>
				
				
				
			</div>
</div>				
				
		
					
	
	
	
	
	
	
	
	
	
	
	
	
	
	</div>	
	
  </div>
  
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div class="col-md-12 alpha60 div-confirmacao" id="listaCriacaoConcluida" style="position: fixed!important;"></div>

<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>
