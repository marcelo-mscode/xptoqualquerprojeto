<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<c:forEach items="${itemHistorico}" var="itemHistorico">
  <c:set value="${itemHistorico.idItemHistorico}"  var="idItemCriacao"/>
  <c:set value="${itemHistorico.dataInicio}"  var="dataInicio"/>
  <c:set value="${itemHistorico.dataTermino}"  var="dataTermino"/>
  <c:set value="${itemHistorico.criacaoItemLista.idCriacaoItemLista}"  var="idCriacaoItem"/>
</c:forEach>


<c:forEach items="${somaDoTempoTotal}" var="somaDoTempoTotal">
	<c:set value="${somaDoTempoTotal}" var="somaDoTempoTotal" />
</c:forEach>


<c:forEach items="${somaDoTempoTotalPendencia}" var="somaDoTempoTotalPendencia">
	<c:set value="${somaDoTempoTotalPendencia}" var="somaDoTempoTotalPendencia" />
</c:forEach>




<c:forEach items="${detalheItem.grupo.produtoGrupo}" var="produtoGrupo">
	<c:set value="${produtoGrupo.quantidade}" var="qtd" />
	<c:set value="${produtoGrupo.quantidade2}" var="qtd1" />
</c:forEach>
	
<div class="col-md-12 bodyXY" style="height: 35px;">
					
		  <ol class="breadcrumb">
		    <li><a href="#">Menu</a></li>
		    <li><a href="exibeLista">Listas de Criação</a></li>
		    <li><a href="exibeItens?idCriacaoLista=${detalheItem.criacaoLista.idCriacaoLista}">Itens da Lista</a></li>
		    <li class="active">Detalhes do Item</li>
		    <%-- <li class="active">${somaDoTempoTotalPendencia[0][0]}Hs${somaDoTempoTotalPendencia[0][1]}Min</li> --%>
		  </ol>					
		</div>
</div>
</div>
<br />
<br />

<div class="container">
	<div class="row" style="background-color: #fff;padding: 17px 0 0 0px;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
	 <div class="col-md-12">	
	 
	 <c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus == 5 }">	
	 	<h3 style="margin-top: -5px;">ITEM PENDENTE</h3>
	 </c:if>
	 
	 	
		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
		  <tbody>
			<tr>
				<th>Item</th>
				<th>Lista</th>
				<th>Quantidade</th>
				<th>Responsável</th>
				<th>LP - Lista</th>
				<th>Status</th>
				<th>Versão</th>
				<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus == 2}">
					<th>Nova Versão</th>
				</c:if>
				<th align="center">Tempo de Execução Total</th>
			</tr>
			<tr>
				<td>${detalheItem.tituloItem}</td>
				<td style="font-size: 12px!important">${detalheItem.criacaoLista.listaTitulo}</td>
				
				<c:if test="${detalheItem.demandaInterna == false}">
					<td>${qtd * qtd1}</td>
				</c:if>

				<c:if test="${detalheItem.demandaInterna == true}">
					<td>${detalheItem.quantidade}</td>
				</c:if>
				
				
				
				<td>${detalheItem.criacaoLista.usuarioReponsável.nome}</td>
				<td>${detalheItem.grupo.idLista.listaCod} .${detalheItem.grupo.idLista.revisao}</td>
				<td  style="background-color: #CFECF5">${detalheItem.criacaoItemStatus.status}</td>
				<td>${detalheItem.versao}</td>
				<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus == 2}">
					<td><a href="novaVersao?idItemLista=${idCriacaoItem}">Criar nova Versão</a></td>
				</c:if>
				
				
				<td align="center" style="font-weight: bolder;">
					<c:if test="${empty somaDoTempoTotal}"> ------- </c:if>
				
					<c:if test="${not empty somaDoTempoTotal}">
						${somaDoTempoTotal[0][0]}Hs${somaDoTempoTotal[0][1]}Min
					</c:if>
				</td>
				
		
		
			</tr>
		  </tbody>
		</table>
	  </div>
	</div>
	
	
	
<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus == 5 || detalheItem.criacaoItemStatus.idCriacaoStatus == 4 }">
	<div class="row is-disabled" style="background-color: #fff;padding: 15px 0 20px 0;box-shadow: -1px 2px 9px 0px #AEAEAE;">
</c:if>		

<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus != 5}">
	<div class="row" style="background-color: #fff;padding: 15px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
</c:if>		
	
	<div class="col-md-3" style="z-index:9999">
		<form action="alteracaoInfoItem" method="post">
			<input type="hidden" value="${detalheItem.idCriacaoItemLista}" name="idCriacaoItemLista"/>
				<label>Descrição do Item</label>
				
				<textarea rows="7" cols="35" class="form-control" name="informaoesItem" >${fn:replace(detalheItem.informaoesItem,'<br>', '&#10;')}</textarea>

			<br><button type="submit" class="btn btn-danger">Salvar Alteração</button>
		</form>
		
		
	</div>
	<div class="col-md-1" style="z-index:99;">
			<c:if test="${dataInicio == null }">
				<label>&nbsp</label>
				<button onclick="iniciaTempoItem(${detalheItem.idCriacaoItemLista})" class="btn btn-info input-80px" id="botaoIniciarItem">Iniciar</button>
				<div id="itemIniciaAjax">
				<p style="margin: 5px 0 0 30px;"> -- </p>
				<span style="font-size: 25px;font-weight: bolder;display: block;margin-left:15px;">--:--</span>
			</c:if>
			
			<c:if test="${dataInicio != null }">
				<label>&nbsp</label>
				<button onclick="" class="btn btn-danger input-80px is-disabled" id="botaoIniciarItem">Iniciar</button>
				<div id="itemIniciaAjax">
				<p style="margin: 5px 0 0 3px;"><fmt:formatDate value="${dataInicio.time}" pattern="dd/MM/yyyy"/></p>
				<span style="font-size: 25px;font-weight: bolder;display: block;margin-left: 8px;"><fmt:formatDate value="${dataInicio.time}" pattern="HH:mm"/></span>
			</c:if>
		
			
		</div>
	</div>	
	

	
	<div class="col-md-1">
		<c:if test="${dataInicio == null || dataInicio != null && dataTermino != null }">
			<label>&nbsp</label>
			<a href=""
			class="btn btn-info input-80px is-disabled" id="terminarItemAjax">Terminar</a>
		</c:if>
		<c:if test="${dataInicio != null && dataTermino == null}">
			<label>&nbsp</label>
			<button onclick="terminaTempoItem(${detalheItem.idCriacaoItemLista},${idItemCriacao});"
			class="btn btn-info input-80px" id="terminarItemAjax">Terminar</button>
		</c:if>
		
		
		<div id="itemTerminaAjax">
			<c:if test="${dataTermino == null }">
				<p style="margin: 5px 0 0 30px;"> -- </p>
				<span style="font-size: 25px;font-weight: bolder;display: block;margin-left:15px;">--:--</span>
			</c:if>

			<c:if test="${dataTermino != null }">
				<p style="margin: 5px 0 0 3px;"><fmt:formatDate value="${dataTermino.time}" pattern="dd/MM/yyyy"/></p>
				<span style="font-size: 25px;font-weight: bolder;display: block;margin-left: 8px;"><fmt:formatDate value="${dataTermino.time}" pattern="HH:mm"/></span>
			</c:if>

		
		
		</div>
	</div>
		
		
   <div class="col-md-3" id="fechaDivDetalhesItem">
		<c:if test="${dataInicio == null || dataInicio != null && dataTermino == null ||detalheItem.criacaoItemStatus.idCriacaoStatus == 2 }">
			<form:form servletRelativeAction="" id="detalhesItem" class="is-disabled">	
		
				<input value="${detalheItem.idCriacaoItemLista}" type="hidden" name="idCriacaoItemLista">
				<label>&nbsp</label>
				<input value="${idItemCriacao}" type="hidden" name="criacaoItemHistoricoTransiente">
				<select name="criacaoItemStatusTransiente" class="form-control">
					<option value="2">Finalizar</option>
					<option value="3">Interromper</option>
					<option value="7">Item Excluído</option>
				</select>
				<div class="divisor-fino"></div>
				<textarea  rows="5" cols="5" placeholder="Observações*" class="form-control" name="obs" id="obsDetalhesItem"></textarea>
				<div class="divisor-fino"></div>
				<button type="submit" class="btn btn-danger">Salvar</button>
			</form:form>
			
		</c:if>	
	
	<c:if test="${dataInicio != null  && dataTermino != null && detalheItem.criacaoItemStatus.idCriacaoStatus == 6 || dataInicio != null  && dataTermino != null && detalheItem.criacaoItemStatus.idCriacaoStatus == 3}">
		<form:form servletRelativeAction="mudaStatusItem" id="detalhesItem">	
	
			<input value="${detalheItem.idCriacaoItemLista}" type="hidden" name="idCriacaoItemLista">
			<label>&nbsp</label>
			<input value="${idItemCriacao}" type="hidden" name="criacaoItemHistoricoTransiente">
			<select name="criacaoItemStatusTransiente" class="form-control">
				<option value="2">Finalizar</option>
				<option value="3">Interromper</option>
				<option value="7">Item Excluído</option>
			</select>
			<div class="divisor-fino"></div>
			<textarea  rows="5" cols="5" placeholder="Observações*" class="form-control" name="obs" id="obsDetalhesItem"></textarea>
			<div class="divisor-fino"></div>
			<button type="submit" class="btn btn-danger">Salvar</button>
		</form:form>
	</c:if>
		
	</div>
	
	<div class="col-md-3">
	
<!-- / Trabalhando  / -->
		<label>Informações Úteis</label>
		<form action="infoUteis" method="post">
			<input type="hidden" value="${detalheItem.idCriacaoItemLista}" name="idCriacaoItemLista"/>
			<textarea rows="7" cols="35" class="form-control" name="infoUteis" placeholder="Informações Úteis" >${detalheItem.infoUteis}</textarea>
			<br><button type="submit" class="btn btn-danger">Salvar Informações</button>
		</form>
		
		
	</div>
	
	
	
</div>

<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus == 5 }">	
	<div class="row is-disabled" style="background-color: #fff;padding: 10px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
</c:if>

<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus != 5 }">	
	<div class="row" style="background-color: #fff;padding: 10px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
</c:if>


   <div class="col-md-12 form-inline" id="delegarDivDetalhesItem">	
   		<c:choose>
   			<c:when test="${dataInicio != null && detalheItem.criacaoItemStatus.idCriacaoStatus != 3}">
   				<form action="" method="" id="delegar" class="is-disabled">
   			</c:when>
   			
   			<c:when test="${dataInicio == null || detalheItem.criacaoItemStatus.idCriacaoStatus == 3 }">
   				<form action="delegaItem" method="post" id="delegar">
   			</c:when>
   		
   		</c:choose>
	
		<input value="${detalheItem.idCriacaoItemLista}" type="hidden" name="idCriacaoItemLista">
		Delegar item para<br>
		<select name="responsavelItemTransiente" id="delegaItem" class="form-control input-220px">
			<option value="">Selecione para Delegar</option>
			<c:forEach items="${usuarios}" var="usuarios">
				<option value="${usuarios.idUsuario}">${usuarios.nome}</option>
			</c:forEach>
		</select>
		<button class="btn btn-danger" type="submit" >Salvar</button>
	</form>
   </div>
</div>


<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus == 5 }">	
	<div class="row is-disabled" style="background-color: #fff;padding: 0px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
</c:if>

<c:if test="${detalheItem.criacaoItemStatus.idCriacaoStatus != 5 }">	
	<div class="row" style="background-color: #fff;padding: 0px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
</c:if>

	<c:choose>
   			<c:when test="${dataInicio != null}">
   				<form action="" method="" class="is-disabled">	
   			</c:when>
   			
   			<c:when test="${dataInicio == null}">
   				<form action="dependenciaItem" method="post" id="dependencia">	
   			</c:when>
	</c:choose>


   <div class="col-md-12 form-inline" id="dependenciaDivDetalhesItem">	
	
	<input value="${detalheItem.idCriacaoItemLista}" type="hidden" name="idCriacaoItemLista">
	<br />
	Descrição da dependência<br />
	<textarea rows="5" cols="35"
	placeholder="Descrição do que esse item depende para ser finalizado." name="descPendencia" class="form-control"></textarea>
	
	Depende do(a)
	<select name="responsavelItemTransiente" id="dependenciaItem" class="form-control">
		<option value="">Selecione o dependente</option>
		<c:forEach items="${usuariosDependencia}" var="usuariosDependencia">
			<option value="${usuariosDependencia.idUsuario}">${usuariosDependencia.nome}</option>
		</c:forEach>
	</select>
	
		Data Limite : <input type="text" class="form-control data" style="width: 110px" name="dataLimiteTransiente">
		Hora : <input type="time" class="form-control" style="width: 100px" name="HoraLimiteTransiente">
	<br><br>

	<button type="submit" class="btn btn-danger">Salvar</button>
</form>
</div>

</div>
<div class="row" style="background-color: #fff;padding: 0px 0 20px 0;    box-shadow: -1px 2px 9px 0px #AEAEAE;">
	<div class="col-md-12">		
		<br />
	
		Histórico Item
		<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed">
		  <tr>	
			
			
			<th>Dt Inicio Execução</th>
			<th>Dt Término Execução</th>
			<th>Observação</th>
			<th>Status</th>
			<th>Responsável</th>
			<th>Versão</th>
			<th>Tempo Execução</th>
			<!-- <th>Tempo Execução Teste</th> -->
		  </tr>
		
		<c:forEach items="${historico}" var="historico"> 
		 	
			<c:if test="${historico.obs == null}"></c:if>
			
			<c:if test="${historico.obs != null}">
			  <tr>
			  	
			  	<td><fmt:formatDate value="${historico.dataInicio.time}" pattern="dd/MM/yyyy HH:mm" /></td>
			  	<td><fmt:formatDate value="${historico.dataTermino.time}" pattern="dd/MM/yyyy HH:mm" /></td>
			  	<td>${historico.obs}</td>
				<td>${historico.criacaoItemStatus.status}</td>
				<td>${historico.responsavelItem.nome}</td>
				<td>${historico.versao}</td>
							
				<td>
					
					
					
				 <c:forEach items="${contaTempo}" var="contaTempo">	
					
					<!--
					 contaTempo é um List que contem o histórico de todos os tempos gastos no item 
					 A condição abaixo verifica se o id do historicoDoItem é igual o id do Historico contaTempo.
					
					 // -->
					
						
					<c:if test="${contaTempo[0] == historico.idItemHistorico}">	
						${contaTempo[1][0]}Hs${contaTempo[1][1]}min
					</c:if>
					
					<c:if test="${contaTempo[0] != historico.idItemHistorico}">	
						
					</c:if>
					
				</c:forEach>

				</td>
				
				<!-- Teste -->
				<%-- <td>
					<c:forEach items="${historico.criacaoItemLista.criacaoItemPendencia}" var="testePend"> 
						
						<c:forEach items="${testePend.criacaoItemPendenciaHistorico}" var="hist">
							${hist.versao}
						</c:forEach> - 
				
				
				
					</c:forEach>
				
				</td> --%>
				
				
				
				
			  </tr>	
		  </c:if>
			
		</c:forEach> 
	</table>
		
		
		</div>
</div>	
		









<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>
