<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<style>
.botaoOrcamento{width: 52px;height: 36px;padding: 0;font-size: 13px;line-height: 13px;margin-left: 20px;}
</style>


<c:set  value="${0}" var="custo"/>	
<c:set  value="${0}" var="preco"/>	
<c:set  value="${0}" var="bv"/>	
<c:forEach items="${produtoGrupo}" var="produtoGrupo">

<c:set  value="${produtoGrupo.custoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias) + custo}" var="custo"/>
<c:set  value="${produtoGrupo.precoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias) + preco}" var="preco"/>
<c:set  value="${produtoGrupo.bvFornecedorValor + bv}" var="bv"/>


</c:forEach>

<div class="col-md-12 bodyXY" style="height: 35px;">
					
   <ol class="breadcrumb">
	    <li><a href="#">Menu</a></li>
	    <li><a href="listaProducao">Produção</a></li>
	    <li class="active"><a href="editaLista?idLista=${lista[3]}">Edição de lista de Produção</a></li>
	    <li class="active">Editar Linha</li>
  </ol>
					
		</div>
    </div>
</div>
<br /><br />

<div class="container shadow-com-padding" style="background: #fff;margin-top: 20px"> 
 	<div class="row" style="margin-top: 0;background-color: #f2f2f2;border-bottom: 1px solid #ccc;padding: 10px;">
	  <div class="col-md-12 tira-padding" style="font-family: 'OpenSansLight'">
	  	<h4><a href="editaLista?idLista=${lista[3]}&#ancoraGrupo${grupo.idgrupo}" class="botao-voltar" style="margin-left: 15px;padding: 5px 20px 5px 20px;"><i class="glyphicon glyphicon-menu-left"></i></a>
	  	Lista: <strong style="margin-right: 15px">${lista[0]}</strong>
	  	Cliente: <strong style="margin-right: 15px">${empresa}</strong>
	  	Lista cod. <strong	>${lista[1]} .${lista[2]}</strong></h4>
	  
	  
	  </div>
	</div>

	<div class="col-md-12 bodyXY" style="height: 805px;padding:0px 15px !important">
		<div class="row" style="margin-left: 5px;">	
			<table class="table table-condensed col-md-5" >
				<tr class="border-top-table-1">
					<td><p class="quebraTexto" style="width: 300px;margin: 0">Linha:  <strong>${grupo.grupo}</strong></p></td>
					<td>Custo:  <strong>R$ <fmt:formatNumber value="${custo}" pattern="#,##0.00" /></strong></td>
					<td>Preço:  <strong>R$ <fmt:formatNumber value="${preco}" pattern="#,##0.00" /></strong></td>
					<td>[R$<fmt:formatNumber value="${preco - custo }" pattern="#,##0.00" />]</td>
					<td>[cl R$ <fmt:formatNumber value="${bv}" pattern="#,##0.00" />]</td>
				
				</tr>
			</table>
	   </div>
<div>

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><h4>Itens</h4></a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"><h4>Descrições</h4></a></li>
  </ul>

  <!-- Tab panes -->
<c:if test="${lista[4].idlistaEstatus != 1 }"> 
  <div class="tab-content is-disabled" style="border-bottom: 1px solid #ddd;padding: 10px 17px 30px 10px;">
</c:if>  
<c:if test="${lista[4].idlistaEstatus == 1 }"> 
  <div class="tab-content" style="border-bottom: 1px solid #ddd;padding: 10px 17px 30px 10px;">
</c:if>  
    <div role="tabpanel" class="tab-pane active" id="home">
	  <div class="row">
		<div class="col-md-4"><!--  <input type="checkbox" onclick="valorFechadoLinha();" />Valor fechado desta linha --></div>
		<div class="col-md-6"></div>
		<div class="col-md-2">
		  <c:if test="${lista[4].idlistaEstatus != 1 }">
		    <a href="#" class="btn btn-info">Inserir custo</a>
		  </c:if>
		  <c:if test="${lista[4].idlistaEstatus == 1 }">
			<a href="inserirDadosItem?idGrupo=${grupo.idgrupo}" class="btn btn-info" style="margin-left: 60px;">Inserir custo</a>
		  </c:if>
		</div>
	  
	  
	  
	  </div>

<form class="display-none" id="valorFechadoLinha">	  
	  <div class="row">
	  	<div class="col-md-12 form-inline">
	  	Custo NET Total <input type="text" class="form-control real" style="width:115px"/>&nbsp&nbsp
	  	Preço Total <input type="text" class="form-control real" style="width:115px"/>&nbsp&nbsp
	  	<input type="checkbox" />&nbspIncide Impostos 
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-md-12 form-inline">
	  		<input type="checkbox" />&nbspCusto possui BV do Fornecedor
	  	</div>
	  </div>	
	  
	  <div class="row">
	  
	  	<div class="col-md-12 form-inline">
	  	<div class="divisor"></div>
	  		<button class="btn btn-danger" type="submit">Salvar Valor Fechado da Linha</button>
	  	<div class="divisor"></div>
	  	</div>
	  </div>	
</form>	  
	  
	  
	  	
	  <div class="row">
	  	<div class="col-md-12" style="border: 1px solid #ddd;border-radius:4px;padding: 10px 15px 0  15px;margin-top: 10px;margin-bottom: 20px;" >
	  		<!-- <p>Não existem itens nesta linha.</p> -->
			 
			<div class="col-md-12" style="padding: 5px 0">
	  			<table class="table table-bordered table-hover">	
	  			  <tr class="active">
	  			  <!-- <tr style="background: #BDC3C7;color:#2C3E50"> -->
	  			  
	  			  
	  			  	<th style="text-align: center;">DQ</th>
	  			  
	  			  
	  			  	<th>Item</th>
	  			  	<th>Custo</th>
	  			  	<th>Preço</th>
	  			  	<!-- <th>Margem</th> -->
	  			  	<th>Qtd.</th>
	  			  	<th>Dia.</th>
	  			  	<th>Cl Forn.</th>
	  			  	<th>Obs.</th>
	  			  	<th>Imposto</th>
	  			  	<th>Atualizado</th>
	  			 
	  			 
	  			 <c:if test="${lista[4].idlistaEstatus != 1 }">	
	  			 
	  			 </c:if>
	  			 <c:if test="${lista[4].idlistaEstatus == 1 }">	
	  			  	<th>Excluir</th>
	  			 </c:if>
	  			 
	  			 
	  			 
	  			  </tr>
	  			  
				  <c:forEach items="${produtoGrupo}" var="produtoGrupo">
				  	<tr class="meio-Linha">	
				  	
			  	
				  	<td style="text-align: center;">

				  		<c:if test="${not empty produtoGrupo.determQuantidade }">
				  		<c:set var="determQuantid"  value="${produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias}" />
					  		<input type="radio" checked="checked" name="determina" onclick="determinaQuantidade(${produtoGrupo.idProdutoGrupo},${produtoGrupo.idGrupo.idgrupo})">
				  		</c:if>

				  		<c:if test="${empty produtoGrupo.determQuantidade }">
					  		<input type="radio" name="determina" onclick="determinaQuantidade(${produtoGrupo.idProdutoGrupo}, ${produtoGrupo.idGrupo.idgrupo})">
				  		</c:if>
				  		
				  	</td>
				  	
				  	
				  	
				  	<c:if test="${lista[4].idlistaEstatus != 1 }">
				  	<td class="col-md-2"><a href="#" >${produtoGrupo.produto}</a></td>
				  	</c:if>
				  	<c:if test="${lista[4].idlistaEstatus == 1 }">
	  			  	<td class="col-md-2"><a href="descricaoItem?idProdutoGrupo=${produtoGrupo.idProdutoGrupo}&idGrupo=${produtoGrupo.idGrupo.idgrupo}" >${produtoGrupo.produto}</a></td>
	  			  	</c:if>
	  			  	
	  			  	
	  			  	<td>
		  			  	<fmt:formatNumber value="${produtoGrupo.custoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias)}" pattern="#,##0.00" />
	  			  	</td>
	  			  	<td class="azul"><fmt:formatNumber value="${produtoGrupo.precoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias)}" pattern="#,##0.00" /></td>
	  			  	<!-- <td>Margem</td> -->
	  			  	<td>${produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias}</td>
	  			  	<td>${produtoGrupo.diarias}</td>
	  			  	<td><%-- <fmt:formatNumber value="${produtoGrupo.bvFornecedorValor}" pattern="#,##0.00" /> --%></td>
	  			  	<td>${produtoGrupo.observacoes}</td>
	  			  	<td>
	  			  		<c:if test="${produtoGrupo.imposto == true}">
	  			  		Com Imposto
	  			  		</c:if>

	  			  		<c:if test="${produtoGrupo.imposto == false}">
	  			  		SEM Imposto
	  			  		</c:if>
	  			  	
	  			  	
	  			  	</td>
	  			  	<td><fmt:formatDate value="${produtoGrupo.alteradoEm.time}" pattern="dd/MM/yyyy HH:mm"/> </td>
	  			  	
	  			  	<c:if test="${lista[4].idlistaEstatus != 1 }">	
	  			 
	  			    </c:if>
	  			    
	  			    <c:if test="${lista[4].idlistaEstatus == 1 }">	
		  			  	<td style="text-align: center;font-size: 20px;"><a href="excluiItem?idProdutoGrupo=${produtoGrupo.idProdutoGrupo}&idGrupo=${grupo.idgrupo}" id=""
		  			  	       style="color:red;">
		                	   <i class="glyphicon glyphicon-remove"></i>
		              		</a>
		              	</td>
	              	</c:if>
	              	
	              	
				  </c:forEach>
	
	
				<c:if test="${not empty produtoGrupo}">
					  <tr>
					  	<td colspan="11"></td>
					  </tr>	
	
	
					  <tr>
					  	<td style="text-align: center;">
		
							<c:if test="${detPadrao == null}">
						  		<input type="radio" name="determina" onclick="determinaQuantidadePadrao(${grupo.idgrupo})" id="radioPadrao">
							</c:if>			  	

							<c:if test="${detPadrao != null}">
					  			<input type="radio" checked="checked" name="determina" onclick="determinaQuantidadePadrao(${grupo.idgrupo})" id="radioPadrao">
							</c:if>					  		
						  		
					  	</td>
					  	
					  	<td colspan="3"> Quantidade Determinante Padrão</td>
					  	
					  	<td style="padding: 0">
					  	   <input type="text" <c:if test="${detPadrao == null}"> value="1" </c:if><c:if test="${detPadrao != null}">
					  	   value="${detPadrao.quantDetermPadrao}"</c:if> class="input-quantDeterm" id="defaultQuant"  onblur="determinaQuantidadePadrao(${grupo.idgrupo})">
					  	</td>
					  	
					  	
					  	<td style="padding: 0">
					  	    <input type="text" <c:if test="${detPadrao == null}"> value="1" </c:if><c:if test="${detPadrao != null}">
					  	   value="${detPadrao.diariasPadrao}"</c:if> class="input-quantDeterm" id="defaultDiaria" onblur="determinaQuantidadePadrao(${grupo.idgrupo})">
					  	</td>
					  	<td colspan="5"><i class="glyphicon glyphicon-ok ok display-none" style="color: rgb(46, 204, 113);margin-left: 10px"></i>
					  		
					  	</td>
					  </tr>
				  </c:if> 
					
				  <tr>
				  	<td colspan="11"></td>
				  </tr>		
				
				  <%-- <tr>
				  	<td></td>	
				  	<td colspan="2">Orçamento de outro Fornecedor</td>
				  	<td style="padding: 0" colspan="2">
				  	<input id="grupoOrcamento" type="hidden" value="${idGrupo}" />
				  	<input id="idOrcamento" type="hidden" value="${orcamento.idOrcamento}" />
				  	<input id="valorFornecedor" value='<fmt:formatNumber value='${orcamento.valorOrcamento}' pattern='#,##0.00'/>' type="text" class="input-quantDeterm input-120px" id="defaultDiaria" placeholder="Valor Orçamento"></td>
					
				  	<td colspan="4" style="padding: 0">
				  		<select id="fornecedorOrcamento" class="form-control border-none">
				  			<c:if test="${orcamento != null}">
					  			<option value="${orcamento.empresa.idEmpresa}">${orcamento.empresa.empresa}</option>
				  			</c:if>
				  			<c:forEach items="${fornecedores}" var="fornecedores">
								<option value="${fornecedores[0]}">${fornecedores[1]}</option>
				  			</c:forEach>
				  		</select>
				  	</td>
				  	<td style="padding: 0;width: 80px"><button class="btn btn-default botaoOrcamento" onclick="SalvaNovoOrcamento();">Salvar</button></td>
				  	<td colspan="4"><i class="glyphicon glyphicon-ok okForn display-none" style="color: rgb(46, 204, 113);margin-left: 10px"></i></td>
				  </tr>			
				  <tr>
				  	<td colspan="11"><span id="erroOrcamento" class="col-md-offset-3" style="color: red;display: none">Coloque um valor !</span></td>
				  
				  </tr>	
					 --%>
				</table>
				
										
					<div id="determinanteQuantidade">
							<c:if test="${not empty produtoGrupo}">
								<table class="table table-bordered table-hover" style="width: 400px; font-size: 12px;">
									<tr>
										<th>Valor Unitário: </th>
										<th>Preço Total: </th>
										<th>Quantidade determinante: </th>
									</tr>
									<tr>
										<td><span id="valorUnit"><fmt:formatNumber value="${vUnitario}" pattern="#,##0.00" /></span></td>
										<td><span id="precoTotal"><fmt:formatNumber value="${precoT}" pattern="#,##0.00" /></span></td>
										<td><span id="QuantDeterMin">${quantD}</span></td>
									</tr>
								</table>
						  </c:if>				
						 <c:if test="${empty produtoGrupo}">
						 </c:if>				
				     </div>	
		       	
				
				
				
				
			</div>
	  	</div>
		
		<h4>Orçamentos</h4>				
		<table class="table table-bordered table-hover">
		   <tr>
		      <td style="padding: 0">
		      	<select class="form-control border-none" id="idProdutoGrupo">
				    <c:forEach items="${produtoGrupo}" var="produtoGrupo" >
		      			<option value="${produtoGrupo.idProdutoGrupo}">${produtoGrupo.produto}</option>
		    		</c:forEach>
		      	</select>
		      </td>
		      <td style="padding: 0" colspan="2">
		         <input id="grupoOrcamento" type="hidden" value="${idGrupo}" />
		         <%-- <input id="idOrcamento" type="hidden" value="${orcamento.idOrcamento}" /> --%>
		         <input id="valorFornecedor" type="text" class="input-quantDeterm input-120px" id="defaultDiaria" placeholder="Valor Orçamento" style="height: 37px">
		      </td>
		      <td style="padding: 0">
		         <select id="fornecedorOrcamento" class="form-control border-none">
		            <c:forEach items="${fornecedores}" var="fornecedores">
		               <option value="${fornecedores[0]}">${fornecedores[1]}</option>
		            </c:forEach>
		         </select>
		      </td>
		      <td style="padding: 0;"><button class="btn btn-default botaoOrcamento" onclick="SalvaNovoOrcamento();" style="height: 37px" >Salvar</button></td>
		      <td id="colorOk" style="width: 55px"><i class="glyphicon glyphicon-ok okForn display-none" style="color: #fff;margin-left: 10px"></i></td>
		   </tr>
		   <tr>
		   	  <td></td>	
		      <td colspan="5"><span id="erroOrcamento" style="color: red;display: none">Coloque um valor !</span></td>
		   </tr>
		</table>
		
		<table class="table table-bordered table-hover">
		   <tr>
		      <th>Item</th>
		      <th>Valor</th>
		      <th>Fornecedor</th>
		      <th style="text-align: center;">Apagar</th>
		   </tr>
		   <c:forEach items="${orcamento}" var="orcamento">
		      <tr>
		         <td>${orcamento.produto.produto}</td>
		         <td>
		            <fmt:formatNumber value='${orcamento.valorOrcamento}' pattern='#,##0.00'/>
		         </td>
		         <td>
		            ${orcamento.empresa.empresa}
		         </td>
		         <td style="text-align: center; font-size: 20px;">
		         	<a href="apagaOrcamentoFornecedor?idOrcamento=${orcamento.idOrcamento}&idGrupo=${idGrupo}" id="" style="color: red;">
		         	   <i class="glyphicon glyphicon-remove"></i>
		            </a>
		         </td>
		      </tr>
		   </c:forEach>
		</table>
				  
	  </div>
	</div>

    <div role="tabpanel" class="tab-pane" id="profile">
    	<div class="row">
    		<div class="col-md-5">


<c:if test="${lista[4].idlistaEstatus != 1 }">
</c:if>
<c:if test="${lista[4].idlistaEstatus == 1 }">
	<form action="atualizaDescricaoEmCategoria" method="post" class="form-horizontal" style="font-size: 16px">
	<!-- <form action="atualizaDescricaoCategoria" method="post" class="form-horizontal" style="font-size: 16px"> -->
</c:if>


			<input type="hidden" name="idListaTransiente" value="${grupo.idLista.idLista}" />
			<input type="hidden" name="idgrupo" value="${grupo.idgrupo}" />
					<!-- Text input-->
						<div class="control-group">
						  
						  <div class="controls margim-bottom5">
  						    <label class="control-label" for="tituloLinha">Título da Linha</label>
						    <input id="tituloLinha" name="grupo" type="text" value="${grupo.grupo}" class="form-control">
						  </div>
						</div>

						<div class="control-group">
							<label class="control-label" for="categoria">Categoria</label>
							<div class="controls margim-bottom5">
								<select id="categoria" name="idCategoriaTransiente" class="form-control">
									<option value="${grupo.idCategoria.idcategoria}">${grupo.idCategoria.categoria}</option>
									<c:forEach items="${categorias}" var="categorias">
										<c:if test="${grupo.idCategoria.idcategoria == categorias.idcategoria}"></c:if>
										<c:if test="${grupo.idCategoria.idcategoria != categorias.idcategoria}">
											<option value="${categorias.idcategoria}">${categorias.categoria}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>

 
						<div class="control-group">
							<label class="control-label" for="categoria">Categoria Bayer</label>
							<div class="controls margim-bottom5">
								<select id="categoriaBayer" name="idgrupoCategoriaBayerTransiente" class="form-control">
									<option value="${grupo.grupoCategoriaBayer.idGrupoCategoria}">${grupo.grupoCategoriaBayer.categoria}</option>
									<c:forEach items="${categoriasBayer}" var="categoriasBayer">
										<c:if test="${grupo.grupoCategoriaBayer.idGrupoCategoria == categoriasBayer.idGrupoCategoria}"></c:if>
										<c:if test="${grupo.grupoCategoriaBayer.idGrupoCategoria != categoriasBayer.idGrupoCategoria}">
											<option value="${categoriasBayer.idGrupoCategoria}">${categoriasBayer.categoria}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="categoria">Categoria Galderma</label>
							<div class="controls margim-bottom5">
								<select id="categoriaBayer" name="idgrupoCategoriaBayerTransiente" class="form-control">
									<option value="${grupo.grupoCategoriaGalderma.idCategoriaGalderma}">${grupo.grupoCategoriaGalderma.categoria}</option>
									<c:forEach items="${categoriasGalderma}" var="categoriasGalderma">
										<c:if test="${grupo.grupoCategoriaGalderma.idCategoriaGalderma == categoriasGalderma.idCategoriaGalderma}"></c:if>
										<c:if test="${grupo.grupoCategoriaGalderma.idCategoriaGalderma != categoriasGalderma.idCategoriaGalderma}">
											<option value="${categoriasGalderma.idCategoriaGalderma}">${categoriasGalderma.categoria}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						
						
					<!-- Multiple Checkboxes -->
						<div class="divisor"></div>
						<div class="control-group">
						  
						  <div class="controls margim-bottom5">
						    <label>
						    	
						    	<c:if test="${grupo.incideAdministracao == true && grupo.feeReduzido ==  false}">
						    	  <input id="feeNormal" type="checkbox" name="incideAdministracao" checked="checked" style="width: 20px;height: 17px">
						    	</c:if>

						    	<c:if test="${grupo.incideAdministracao == true && grupo.feeReduzido ==  true}">
						    	  <input id="feeNormal" type="checkbox" name="incideAdministracao" style="width: 20px;height: 17px">
						    	</c:if>

						    	<c:if test="${grupo.incideAdministracao == false}">
						    	  <input id="feeNormal" type="checkbox" name="incideAdministracao" style="width: 20px;height: 17px">
						    	</c:if>

						      Incide FEE
						    </label>
						    <br>	


						    <label style="margin-top: 10px;">
						    	<c:if test="${grupo.feeReduzido == true}">
						    	  <input id="feeReduzido" type="checkbox" name="feeReduzido" checked="checked" style="width: 20px;height: 17px">
						    	</c:if>
						    	<c:if test="${grupo.feeReduzido == false}">
						    	  <input id="feeReduzido" type="checkbox" name="feeReduzido" style="width: 20px;height: 17px">
						    	</c:if>
						      Incide FEE Reduzido
						    </label>
						  
						    <label class="checkbox" for="checkboxes-1" style="margin-left: 20px;">
						      <c:if test="${grupo.opcional == true}">
						    	  <input type="checkbox" name="opcional" checked="checked" style="width: 20px;height: 17px">
						    </c:if>
						
						    <c:if test="${grupo.opcional == false}">
						    	  <input type="checkbox" name="opcional" style="width: 20px;height: 17px">
						    </c:if>
						      
						      Opcional
						    </label>
						  </div>
						</div>
						
						<!-- Textarea -->
						<div class="control-group">
						  <label class="control-label" for="informacoes">Informações</label>
						  <div class="controls margim-bottom5">                     
						    <textarea id="informacoes" name="informacoes" class="form-control" rows="5">${grupo.informacoes}</textarea>
						  </div>
						</div>
						
						<!-- Textarea -->
						<div class="control-group">
						  <label class="control-label" for="necessidades">Necessidades</label>
						  <div class="controls">                     
						    <textarea id="necessidades" name="necessidades" class="form-control" rows="5">${grupo.necessidades}</textarea>
						  </div>
						</div>
						
						<!-- Button -->
						<div class="control-group">
						  <label class="control-label" for=""></label>
						  <div class="controls margim-bottom5">
						   <c:if test="${lista[4].idlistaEstatus != 1 }">
						    <a href="#" class="btn btn-danger">Salvar</a>
						   </c:if>
						   
						   <c:if test="${lista[4].idlistaEstatus == 1 }">
						    <button type="submit" name="" class="btn btn-danger">Salvar</button>
						   </c:if>

						  </div>
						</div>
						
						
</form>   		
    		
    		</div>
    	</div>
    
    
    
    
    
    
    </div>
  </div>

</div>
 	
 	
 	
    
  </div><!--  -->
</div>



</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/listaProducaoAjax.js" />"></script>

