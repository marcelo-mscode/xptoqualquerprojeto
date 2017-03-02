<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
<style>
	.ajustaCheckBox{height: 18px;width: 18px}
	.ajustaDivChecked{margin-bottom: 0px;margin-left: 22px;margin-top: 8px;line-height: 25px;}
	.categoriaEmpresasLista{margin: 10px 0 10px 0;background-color: #e6e6e6;padding: 10px;border-radius: 5px;margin-left: 15px;}
</style>

<c:forEach items="${lista}" var="lista">
	<c:set value="${lista.idLista}" var="idLista" />
	<c:set value="${lista.administracao}" var="administracao" />
	<c:set value="${lista.feeReduzido}" var="feeReduzido" />
	<c:set value="${lista.margemPadrao}" var="margemPadrao" />
	<c:set value="${lista.formaPagamento}" var="formaPagamento" />
	<c:set value="${lista.lista}" var="titulo" />
	<c:set value="${lista.idJob.empresa.empresa}" var="empresa" />
	<c:set value="${lista.idJob.titulo}" var="tituloJob"/>
	<c:set value="${lista.listaCod}" var="codLista"/>
	<c:set value="${lista.revisao}" var="revisao"/>
	<c:set value="${lista.valorTotal}" var="valorTotal"/>
	<c:set value="${lista.impostoValor}" var="impostoValor"/>
	<c:set value="${lista.subTotalCusto}" var="subTotalCusto"/>
	<c:set value="${lista.administracaoValor}" var="administracaoValor"/>
	<c:set value="${lista.administracaoValor}" var="administracaoValor"/>
	<c:set value="${lista.idlistaEstatus.idlistaEstatus}" var="idlistaEstatus"/>
	<c:set value="${lista.numCenarioGalderma}" var="numCenarioGalderma"/>
	<c:set value="${lista.infoConsolidadoGalderma}" var="infoConsolidadoGalderma"/>
	
	
	
	
</c:forEach>
<!-- ---------------------------------------------------------------- -->
<div class="col-md-12 bodyXY" style="height: 35px;">
					
		  <ol class="breadcrumb" style="padding-left: 25px;">
		    <li><a href="index.html">Home</a></li>
		    <li><a href="listaProducao">Produção</a></li>
		    <li class="active">Edição de  lista de Produção</li>
		  </ol>
		</div>
    </div>
</div>
<br /><br />





<div class="col-md-12">


<div>
	<div class="row col-md-12">
		<a href="" style="float: right">Galderma</a>
	</div>
	
	<div class="row">
		<div class="col-md-6"></div>
		
		<div class="col-md-6">
		
			<input type="text"  onblur="editaInfoGalderma();" />
		
			<textarea id="infoGalderma" class="form-control" rows="3" cols="8" name="infoConsolidadoGalderma" onblur="infoGalderma(${idLista});">${infoConsolidadoGalderma}</textarea>
			<a href="geraCenarioGalderma?idLista=${idLista}" class="btn btn-default" style="float: right;margin-top: 5px">Gerar Cenário</a>
		</div>
		<div class="col-md-3">
		</div>
	</div>

</div>

 



<div class="container shadow-com-padding col-md-12" style="background: #fff;margin-top: 15px;"> 

<%-- <h1>Usuario: ${usuarioSessao}</h1> --%>

<div class="row" style="margin-top: 0;background-color: #f2f2f2;border-bottom: 1px solid #ccc;">
	<div id="itemEnviadoCriacao" style="z-index: 9999;position: absolute;"></div>
	<div class="col-md-6 tira-padding" style="font-family: 'OpenSansLight'">
	    <h3 style="margin-top: 11px">PLANILHA INTERNA
		  <c:if test="${idlistaEstatus == 2 }"> 
		  	( Fechada )
		  </c:if>
		  <c:if test="${idlistaEstatus == 4 }"> 
		  	( em revisão )
		  </c:if>
		  
		  <c:if test="${idlistaEstatus == 5 }"> 
		  	( Planilha Aprovada )
		  </c:if>
		  
		  </h3>
	 </div>
	 <div class="col-md-6 tira-padding" style="font-family: 'OpenSansLight'">
		  <a href="excelGalderma?idLista=${idLista}" class="btn btn-default" style="margin: 5px 0 0 330px">
			  	<img alt="" src="<c:url value="resources/images/excel.png" />" width="18"  height="18" >
			  	Excel galderma
		  </a>
		  <a href="geraCenarioGalderma?idLista=${idLista}" class="btn btn-default" style="float: right;margin-top: 5px">Gerar Cenário</a>
	 </div> 
	 
</div>
   
   <div class="row"style="padding: 5px 0;">
	<div class="col-md-12" style="padding-left: 10px;">
			
		<div class="col-md-12 tira-padding ">
			<a href="printProducao?idLista=${idLista}" class="btn btn-default" >Gerar Planilha do Cliente</a>
			<a onclick="exportaExcelAjax(${idLista});" class="btn btn-default" id="planilhaGerarExcel">
			<img alt="" src="<c:url value="resources/images/excel.png" />" width="18"  height="18" > 
				Excel
			</a>
			
		
			<a onclick="exportaExcelAjaxBayer(${idLista});" class="btn btn-default" id="planilhaGerarExcelBayer">
				<img alt="" src="<c:url value="resources/images/excel.png" />" width="18"  height="18" > 
				Excel Bayer
			</a>

			<a onclick="exportaExcelAjaxNovoBayer(${idLista});" class="btn btn-default" id="planilhaGerarNovoExcelBayer">
				<img alt="" src="<c:url value="resources/images/excel.png" />" width="18"  height="18" > 
				Excel Novo Bayer
			</a>

			
			
			
			<button type="button" class="btn btn-default" onclick="efeitoToogle(exibeConfiguracaoPlanilha);">
			Configurações<i class="glyphicon glyphicon-cog" style="font-size: 16px;margin-left: 4px;"></i>
			</button>
			
			
			<c:if test="${idlistaEstatus == 1}"> 
					<%-- <button onclick="concluiPlanilhaPorAjax(${idLista});" type="button" class="btn btn-conclui-lista">Concluir Planilha</button> --%>
					<button onclick="confirmaConclusaoPlanilha();" type="button" class="btn btn-conclui-lista">Concluir Planilha</button>
			</c:if>
			
			<c:if test="${idlistaEstatus == 2 || idlistaEstatus == 4 || idlistaEstatus == 5 }">
					<button onclick="duplicaPlanilhaPorAjax(${idLista});" class="btn btn-danger" id="duplicaPlanilha" >
					<img src="resources/images/loader-confirmacao.gif" class="display-none" width="20" height="20" alt="loading" id="loader-confirmacao">
					Duplicar Planilha
					</button> 
			 </c:if>
			
			
			<c:if test="${idlistaEstatus == 2}">
				<button onclick="revisarPlanilhaPorAjax(${idLista});" type="button" class="btn btn-danger" id="revisarPlanilha" >Revisar Planilha</button> 
			</c:if>
	
			<%-- <c:if test="${idlistaEstatus == 2}">
				<button onclick="aprovarPlanilhaPorAjax(${idLista});" type="button" class="btn btn-primary" id="aprovarPlanilha" style="float: right;">Aprovar Para Produção</button> 
			</c:if>
	
			<c:if test="${idlistaEstatus == 5}">
				<button disabled="disabled" type="button" class="btn btn-primary" id="aprovarPlanilha" style="float: right;"	>Aprovado Para Produção</button> 
			</c:if> --%>
	
		</div>
		
		<div class="col-md-3">
		  
		</div>
		
 	</div>
  </div>
	
	
 <div class="col-md-12" style="height:5px"></div>

<!-- Gerar Planilha-->
	
<div class="divisor"></div>
<div class="row">   
  <table class="table table-striped table-td-ajuste" style="font-size: 12px;">

    <tr>
	    <td>Título da lista</td>
	    
	   <c:if test="${numCenarioGalderma != null}">
	    <td style="text-align: center;background-color: #ffff80;">Cenário</td>
	   </c:if> 
	   
	   
	    <td>Cliente</td>
	    <td>Job</td>
	    <td>Código da Lista</td>
    </tr>
    <tr>
	    <td style="font-weight:bold;border-right:1px solid #ccc;font-size: 15px;" id="colunaNomeLista">
		    <input type="text" value="${titulo}" style="width: 90%;border: none;" onblur="atualizaNomeLista();" id="nomeLista">
	    </td>
		<c:if test="${numCenarioGalderma != null}">
	    	<td style="font-weight:bold;border-right:1px solid #ccc;font-size: 15px;text-align: center;background-color: #ffffcc;">${numCenarioGalderma}</td>
		</c:if>
	    <td style="font-weight:bold;border-right:1px solid #ccc;font-size: 15px;">${empresa}</td>
	    <td style="font-weight:bold;border-right:1px solid #ccc;font-size: 15px;">${tituloJob}</td>
	    <td style="font-weight:bold;border-right:1px solid #ccc;font-size: 15px;">${codLista} .${revisao}</td>
    </tr>

  </table>


<c:set var="subtotal" value="0" />  
<c:forEach items="${categoria}" var="categoria">
			
			
			<c:set var="total" value="0" />  
			<c:forEach items="${categoria.grupo}" var="categorias">
				<c:if test="${categorias.opcional == true }"></c:if>
				
				<c:if test="${categorias.opcional == false }">
					<c:set var="total" value="${total + categorias.grupoValorIncideImposto}" />
				</c:if>
 		    
 		    </c:forEach>
			
			
			
			<c:set var="locco" value="0" />  
			<c:forEach items="${categoria.grupo}" var="fatDireto">
				<c:if test="${fatDireto.opcional == true }"></c:if>
				
				<c:if test="${fatDireto.opcional == false }">
			    	<c:set var="locco" value="${locco + fatDireto.grupoValorNaoIncideImposto}" />
				</c:if>
			</c:forEach>
			
			
				
			<c:set var="totalsub"  value="${total + locco}" />
			
			
			<c:set var="totalFatLocco" value="${totalFatLocco + total}" />
    		<c:set var="totalFatDireto" value="${totalFatDireto + locco}" />
			
			<c:set var="subtotal"  value="${subtotal + total + locco}" />
			
</c:forEach>

  <div class="col-md-12" style="height:10px"></div>  
<table class="table table-striped table-td-ajuste" style="font-size: 12px;">
   <tr class="active">
	 <td style="border-right:1px solid #ccc">Custo:&nbsp<span class="bolder" ><fmt:formatNumber value="${subTotalCusto}" pattern="#,##0.00"/></span></td>
	 <td style="border-right:1px solid #ccc">Preço: <span class="bolder"><fmt:formatNumber value="${subtotal}" pattern="#,##0.00"/></span></td>
	 <td style="border-right:1px solid #ccc">Fee:&nbsp<span class="bolder" id="administracaoValor"><fmt:formatNumber value="${administracaoValor}" pattern="#,##0.00"/></span></td>
	 <td style="border-right:1px solid #ccc">Imposto:&nbsp<span class="bolder" id="impostoValor"><fmt:formatNumber value="${impostoValor}" pattern="#,##0.00"/></span></td>
	 <td style="border-right:1px solid #ccc">Total:&nbsp<span class="bolder" id="valorTotal">
	 	<fmt:formatNumber value="${valorTotal}" pattern="#,##0.00"/></span>
	 	<span>&nbsp[&nbsp<fmt:formatNumber value="${(subtotal - subTotalCusto)+administracaoValor}" pattern="#,##0.00"/>&nbsp&nbsp]</span>
	 </td>
	 
	 <td style="border-right:1px solid #ccc">[cl&nbsp&nbsp<fmt:formatNumber value="${subtotal - subTotalCusto}" pattern="#,##0.00"/> ]</td>
   </tr>
</table>
   	
  
  
 </div>
 
<!-- display none   --> 
<div class="row display-none" id="exibeConfiguracaoPlanilha"
style="border-top: 3px solid #ccc;border-bottom: 0px solid #ccc;padding-bottom: 15px;background-color: #f8f8f8">  
		  <div class="col-md-12" style="padding-top: 10px;padding-left: 10px;">
		  	
		  	<div class="row" style="padding-left: 10px;">
			<h5 style="margin-left: 1px;">Configurações</h5>
<c:if test="${idlistaEstatus == 1 }"> 			
<form action="atualizaConfiguracaoLista" method="post" id="atualizaConfiguracaoLista">
</c:if>
			<input type="hidden" value="${idLista}" name="idLista" id="idListaValor">
			
		
				<div class="col-md-12" style="padding-left: 0;">
		
				<div class="form-group">
				  	<div class="input-group">
					    <span class="input-group-addon colorTopText">Fee&nbsp%</span>
					    <input type="text" class="form-control input-60px" value="${administracao}" name="administracaoTransiente">
				  	</div>
				</div>


				<div class="form-group">
				  	<div class="input-group">
					    <span class="input-group-addon colorTopText">Fee Reduzido&nbsp%</span>
					    <input type="text" class="form-control input-60px" value="${feeReduzido}" name="feeReduzidoTransiente">
				  	</div>
				</div>

		
				<div class="form-group">
				  	<div class="input-group">
					    <span class="input-group-addon colorTopText">Forma de Pagamento</span>
					    <input type="text" class="form-control input-420px" value="${formaPagamento}" name="formaPagamento">
				  	</div>
				</div>

				<div class="form-group">
				  	<div class="input-group">
				  	  <c:if test="${idlistaEstatus == 1 }"> 
					    <button class="btn btn-danger">Salvar Configuração</button>
					    &nbsp&nbsp<a class="btn btn-info" onclick="efeitoToogle(exibeConfiguracaoPlanilha);">Cancelar</a>
					    
				  	  </c:if>	
				  	</div>
				</div>
				
			  	</div>
</form>	

				
			<div class="divisor"></div>	
				<div class="form-group">
				  	
		
					    <table class="table table-condensed table-hover">
					      <tr>
					      	<td colspan="5" class="tableTrGrupoPreto colorTopText">Categorias</td>
					      </tr>
					      <tr class="">
					    	<!-- <th >Ord</th> -->
					    	<th class="input-220px">Categoria</th>
					    	<th >Imposto %</th>
					    	<th >Imposto Título</th>
					     	<th><c:if test="${idlistaEstatus == 1 }"> Editar</c:if></th>
					      </tr>
					      
					      <c:forEach items="${categoria}" var="categoria">
						     <tr id="categoriaAtualizada${categoria.idcategoria}" class="meio-Linha"> 
						     	
						       <%-- // <td class="input-50px"><img src="resources/images/configuracao-loader.gif" class="loader${categoria.idcategoria} display-none" width="20" height="20"  alt="loading">&nbsp${categoria.categoriaOrdem}</td> --%>
						        <td class="input-220px">${categoria.categoria}</td>
						        <td class="input-180px">${categoria.imposto} %</td>
						        <td class="input-340px">${categoria.idImposto.impostoTitulo} (${categoria.imposto} %)</td>
						        <td>
						           <c:if test="${idlistaEstatus == 1 }"> 
						        	<a class="btn btn-link" onclick="efeitoToogle(editacategoria${categoria.idcategoria})">Editar</a>
						          </c:if>
						        </td>
						     
						     </tr> 

			<tr class="display-none" id="editacategoria${categoria.idcategoria}">
	
	        <input type="hidden" value="${idLista}" name="idListaTransiente" id="idListaTransiente">
	        
	        <input type="hidden" value="${categoria.impostoTitulo}" name="impostoTitulo" id="impostoTitulo${categoria.idcategoria}">
	        
	        
	       <%--  <td><input class="form-control input-50px" value="${categoria.categoriaOrdem}" name="categoriaOrdem" id="categoriaOrdem${categoria.idcategoria}"></td> --%>
	        <td><input class="form-control input-220px" value="${categoria.categoria}" name="categoria" id="categoria${categoria.idcategoria}"></td>
			<td class="input-180px"></td>						       
	       
	        <td  class="input-340px">
	        	
	         <select  class="form-control" name="idImpostoTrasiente" id="idImpostoTrasiente${categoria.idcategoria}">

	         <option value="${categoria.idImposto.idimposto}">${categoria.idImposto.impostoTitulo} (${categoria.idImposto.imposto} %)</option>
	         
	         	<c:set value="${categoria.idImposto.idimposto}" var="idImpostoTituloAtual" />
				
				<c:forEach items="${imposto}" var="imposto">
					<c:if test="${idImpostoTituloAtual ==  imposto.idimposto}"></c:if>
					
					<c:if test="${idImpostoTituloAtual !=  imposto.idimposto}">
				   		<option value="${imposto.idimposto}">${imposto.impostoTitulo} (${imposto.imposto} %)</option>
				   </c:if>
				   
			    </c:forEach>
			    
			 </select>
				       
			<td><button class="btn btn-danger" onclick="editaCategoriaConfiguracao(${categoria.idcategoria});">Salvar Configuração</button></td>
						        <td>
						        
						        </td>
						     <!--  </form> -->  
						        
						      </tr>
				   		      
					      </c:forEach>
					    </table>
					    
				</div>
				
					     
  	   </div>  
	</div>
				<div class="col-md-12" style="border: 1px solid #ddd;padding: 10px 0 10px 7px;border-radius: 5px;background: #fff"> 
					
					<a  href="carregaListaOrdenar?idLista=${idLista}" class="btn btn-danger">Ordenar Categorias</a>&nbsp&nbsp
				    <a  href="carregaListaGruposOrdenar?idLista=${idLista}" class="btn btn-danger">Ordenar Linhas</a>
				
				</div>
</div>

 <c:if test="${idlistaEstatus == 1 }"> 
<div class="row" style="border-top: 3px solid #ccc;">
  <div class="col-md-12 tira-padding" style="line-height: 40px;">

      <div class="col-md-8 tira-padding" style="padding: 10px 10px ">

        <i class="glyphicon glyphicon-pencil"></i> 
        <button class="btn btn-link" id="TiraCategoria">Inserir Categoria</button>
      </div>

    <div class="col-md-8 form-group  tira-padding categoria display-none" style="padding-bottom: 15px;">
					<div class="divisor"><hr></div>
		<form action="salvaCategoria" method="post" id="salvaCategoria"  onsubmit="criaCategoriaBotao.disabled = true; return true;">
				   <input type="hidden" name="idListaTransiente" value="${idLista}" />
		
					<div class="col-md-6 tira-padding ">
						<input type="text" class="form-control" placeholder="Nome da Categoria" name="categoria" id="salvaCategoriaCategoria">
					</div>


					<div class="col-md-8 tira-padding" style="margin: 10px 0;">
						<select name="idImpostoTrasiente" id="" class="form-control">
							<c:forEach items="${imposto}" var="imposto">
								<option value="${imposto.idimposto}">${imposto.impostoTitulo} (${imposto.imposto} %)</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-6 tira-padding">
						<input class="btn btn-danger" type="submit"	value="Criar Categoria" name="criaCategoriaBotao">
					</div>
		</form>
		
		</div>
      </div>
    </div>
</c:if>
 	</div>
</div>
 
<!-- Fim Gerar Planilha -->

<div class="col-md-12" style="height:5px"></div>

<!-- Edicção Planilha-->
 <div class="row">
 <div class="col-md-12 painel ajuste-left shadow-com-padding" style="margin: 10px 0 0 0">

 <div class="col-md-12" style="padding: 0">
 
 

 
  
<div class="col-md-12" style="height:10px"></div>  
      

<table class="table table-striped table-td-ajuste ordem" style="font-size: 12px;border:1px solid #ccc">
  
	 <tr>   
	    <th class="input-160px">Linha</th>
	    <th>Fat Locco</th>
	    <th>Fat Direto</th>
	    <th>Opcional</th>
	    <th>Informações</th>
	    <th>Não inclusos no custo</th>
	
	<c:if test="${idlistaEstatus == 1}">   
	    <th>Excluir</th>
	    <th>Criação</th>
	</c:if>
	
	
	</tr>
    <div class="ordem">
    
    	<c:set var="subtotal" value="0" />
    	<c:set var="totalFatLocco" value="0" />
    	<c:set var="totalFatDireto" value="0" />
      
<c:forEach items="${categoria}" var="categoria">
		<tr>
		
		
		  <td colspan="8" style="background: #ccc" >
		        	<a name="ac${categoria.idcategoria}"></a>
		            <strong>${categoria.categoria}</strong>
          </td>
		
		</tr>
		
	  <c:forEach items="${grupoOrdem}" var="grupo">	
  		   <c:forEach items="${categoria.grupo}" var="grupoCompara">	

			
			<c:if test="${grupo.idgrupo == grupoCompara.idgrupo}">
				<tr class="meio-Linha">

				<c:choose>
					<c:when test="${grupo.incideAdministracao == true && grupo.feeReduzido == false && grupo.grupoCategoriaBayer == null ||
									grupo.incideAdministracao == true && grupo.feeReduzido == false && grupo.grupoCategoriaBayer.idGrupoCategoria == 1}">
				        			<td>
    				</c:when>
					<c:when test="${grupo.incideAdministracao == true && grupo.feeReduzido == false && grupo.grupoCategoriaBayer != null ||
									grupo.incideAdministracao == true && grupo.feeReduzido == false && grupo.grupoCategoriaBayer.idGrupoCategoria != 1}">
				         			<td style="border-left: 2px solid #f1c40f;">
    				</c:when>

					<c:when test="${grupo.incideAdministracao == false && grupo.feeReduzido == false && grupo.grupoCategoriaBayer == null ||
									grupo.incideAdministracao == false && grupo.feeReduzido == false && grupo.grupoCategoriaBayer.idGrupoCategoria == 1}">
				        			<td style="border-right: 2px solid #e74c3c;">
				    </c:when>
					<c:when test="${grupo.incideAdministracao == false && grupo.feeReduzido == false && grupo.grupoCategoriaBayer != null ||
									grupo.incideAdministracao == false && grupo.feeReduzido == false && grupo.grupoCategoriaBayer.idGrupoCategoria != 1}">
							        <td style="border-right: 2px solid #e74c3c;border-left: 2px solid #f1c40f;">
				    </c:when>

					<c:when test="${grupo.feeReduzido == true && grupo.grupoCategoriaBayer == null && grupo.grupoCategoriaBayer == null ||
									grupo.feeReduzido == true && grupo.grupoCategoriaBayer.idGrupoCategoria == 1}">
				        			<td style="border-right: 2px solid #3498db;">
				    </c:when>
					<c:when test="${grupo.feeReduzido == true && grupo.grupoCategoriaBayer != null ||
									grupo.feeReduzido == true && grupo.grupoCategoriaBayer.idGrupoCategoria != 1}">
				        			<td style="border-right: 2px solid #3498db;border-left: 2px solid #f1c40f;">
				    </c:when>
				    
			    </c:choose>

					   <a name="ancoraGrupo${grupo.idgrupo}"></a>
		          	   <a href="editaLinha?idGrupo=${grupo.idgrupo}" id="Cadeiras" type="button"
		          	   class="btn btn-link quebraTexto input-120px;" style="font-size: 13px !important;padding-left: 0;">${grupo.grupo}</a>
		          
		          </td>

<!-- Fat LoCCO  -->		          	
		          <td>
		          	<c:if test="${grupo.opcional ==  false && grupo.opcionalGalderma == false || grupo.opcional ==  false && grupo.opcionalGalderma == true}">
		          		<fmt:formatNumber value="${grupo.grupoValorIncideImposto}" pattern="#,##0.00"/>
		          	</c:if>
		          	
		          	<c:if test="${grupo.opcional ==  true && grupo.opcionalGalderma == true ||
		          		          grupo.opcional ==  true && grupo.opcionalGalderma == false ||
		          		          grupo.opcional ==  false && grupo.opcionalGalderma == true}">
		          	</c:if>
		         
		          </td>
<!--  -->		          	

<!-- Fat Direto -->		          	
		          <td>
		          	 <c:if test="${grupo.opcional == true or grupo.opcionalGalderma == true}"></c:if>
		          	 <c:if test="${grupo.grupoValorNaoIncideImposto > 1 && grupo.opcional == false || grupo.grupoValorNaoIncideImposto < 0 && grupo.opcional == false}">
		          	 	<fmt:formatNumber value="${grupo.grupoValorNaoIncideImposto}" pattern="#,##0.00"/>
		          	 </c:if>	
		          </td>
<!-- -->		          	

<!-- Opcional -->		          	
		          <td>
		          	<c:if test="${grupo.opcional == true or grupo.opcionalGalderma == true}">
		          		<c:if test="${grupo.grupoValorIncideImposto == 0}">
		          			<fmt:formatNumber value="${grupo.grupoValorNaoIncideImposto}" pattern="#,##0.00"/>
		          		</c:if>
		          		<c:if test="${grupo.grupoValorIncideImposto > 0 || grupo.grupoValorIncideImposto < 0}">
		          			<fmt:formatNumber value="${grupo.grupoValorIncideImposto}" pattern="#,##0.00"/>
		          		</c:if>
		          	</c:if>
		          	
		            <c:if test="${grupo.opcional == false or grupo.opcionalGalderma == false}"></c:if>	
		          </td>
<!--  -->		          
		          <td><p class="quebraTexto" style="width: 380px;">${grupo.informacoes}</p></td>
		          <td><p class="quebraTexto" style="width: 160px">${grupo.necessidades}</p></td>
		          
		
				  <c:if test="${idlistaEstatus == 1}"> 
					          <td style="text-align: center;font-size: 	20px;">
					          	 
					          	 <c:if test="${grupo.criacao == true}">
					          		<a onclick="excluiCategoria(${grupo.idgrupo},${idLista})" id="" style="color:red;">
				                	   <i class="glyphicon glyphicon-remove"></i>
				              		</a>
					          	 </c:if>

					          	 <c:if test="${grupo.criacao == false}">
					          		<a href="excluiCategoria?idGrupo=${grupo.idgrupo}&idLista=${idLista}" id="" style="color:red;">
				                	   <i class="glyphicon glyphicon-remove"></i>
				              		</a>
					          	 </c:if>
				              	 
				              </td>
	 		      </c:if>          
	              
	              
		          <c:if test="${idlistaEstatus == 1}">     
					  <td style="text-align: center;" id="criacaoLista${grupo.idgrupo}">
					  
					  <c:if test="${grupo.criacao == false}">
					  		<input type="checkbox" style="width: 15px;height: 15px" onclick="criaLista(${idLista},${grupo.idgrupo});"/>
					  </c:if>
					  <c:if test="${grupo.criacao == true}">
					  		<input type="checkbox" style="width: 15px;height: 15px" checked="checked" disabled="disabled" />
					  </c:if>
					  
					  
					   </td>			          
			     </c:if>     
		          
		          
		        </tr>
		
				<!-- Taxa de Serviço -->        
			         <c:if test="${grupo.txISS > 0 ||  grupo.txServico > 0}">
			         	<tr>
			         		<td align="right">Taxa Serviço (%)</td>
			         		<td>${grupo.txISS}</td>
			         		<td>Taxa ISS</td>
			         		<td>${grupo.txServico}</td>
			         		<td colspan="4">
			         	</tr>
			         </c:if>
				<!-- Taxa de Serviço -->        
	    
	         </c:if>  
					         
		         
		         
				        
			</c:forEach>	
		</c:forEach>	
<!-- -----------------------------------------------------------------------------------------------------------------------------------------  -->		          
 
 <c:if test="${idlistaEstatus == 1 }"> 
  <tr>
      <td colspan="8">
      
      <div class="col-md-12 tira-padding " >
         
         <div class="row">
	        <div class="col-md-9" id="novaLinhaEfeito${categoria.idcategoria}"></div> 
	          
	        <div class="col-md-2 tira-padding" style="margin-left:20px;/* text-align: right */padding-right: 0;" id="novaLinha">
	
	          <i class="glyphicon glyphicon-pencil"></i> 
	          <button class="btn btn-link"  onclick="insereLinha(exibelinha${categoria.idcategoria},${categoria.idcategoria});" id="">Inserir linha</button>
	
	        </div>
        </div>
      <div class="col-md-8 form-group tira-padding" id="exibelinha${categoria.idcategoria}" style="display:none">
          <hr>
 <form action="SalvaGrupo" method="post" id="salvaGrupoLista" onsubmit="salvarGrupoBotao.disabled = true; return true;"> 
 		
 			<input type="hidden" value="${idLista}" name="idListaTransiente">
 			<input type="hidden" value="${categoria.idcategoria}" name="idCategoriaTransiente">
 			
 
          <div class="col-md-8">
              <input type="text" class="form-control" placeholder="Título da linha" name="grupo" id="salvatituloLista">
          </div>
          
          <div class="col-md-8 ">
              <div class="checkbox col-md-offset-1 tira-padding ajustaDivChecked">
                <input id="feeNormal" type="checkbox" class="form-group ajustaCheckBox"  name="incideAdministracao" checked="checked"> Incide Fee            
              </div>
          </div>
          
<%-- <security:authorize access="hasRole('ROLE_ADMIN')"> --%>          
          <div class="col-md-8 ">
              <div class="checkbox col-md-offset-1 tira-padding ajustaDivChecked">
                <input id="feeReduzido" type="checkbox" class="form-group ajustaCheckBox" name="feeReduzido"><span>Fee Reduzido</span>             
              </div>
          </div>
<%-- </security:authorize> --%>
          <div class="col-md-8">
              <div class="checkbox col-md-offset-1 tira-padding ajustaDivChecked">
                <input type="checkbox" class="form-group ajustaCheckBox" name="opcional"> Opcional da Planilha            
              
                <input type="checkbox" class="form-group ajustaCheckBox" name="opcionalGalderma" style="margin-left: 20px"><b style="margin-left: 45px">Opcional Galderma</b>           
              </div>
          </div>


<%-- <security:authorize access="hasRole('ROLE_ADMIN')">  --%>	 
          <div class="col-md-7 categoriaEmpresasLista">
          	  <span>CATEGORIAS BAYER</span>
              <div class="checkbox col-md-offset-1 tira-padding " style="margin: 8px 0;">
                <select name="idgrupoCategoriaBayerTransiente" class="form-control">
                	<c:forEach items="${categoriasBayer}" var="bayer">
	                	<option value="${bayer.idGrupoCategoria}">${bayer.categoria}</option>
                	</c:forEach>
                </select>           
              </div>
          </div>
          
          <div class="col-md-7 categoriaEmpresasLista">
          	<span>CATEGORIAS GALDERMA</span>
              <div class="checkbox col-md-offset-1 tira-padding" style="margin: 8px 0;">
                <select name="idCategoriaGaldermaTransiente" class="form-control">
                	<c:forEach items="${categoriasGalderma}" var="galderma">
	                	<option value="${galderma.idCategoriaGalderma}">${galderma.categoria}</option>
                	</c:forEach>
                </select>
              </div>
          </div>
		
          <div class="col-md-7 categoriaEmpresasLista">
        	<p>TAXAS GALDERMA</p>	
              <div class="col-md-3 col-md-offset-1 tira-padding" style="margin: 8px 0;">
                Taxa de Serviço<input class="form-control" value="0" name="txServico" />
              </div>
              <div class="col-md-4 col-md-offset-1 tira-padding" style="margin: 8px 15px;">
                Taxa de ISS<input class="form-control" value="0"  name="txISS"/>
              </div>
          </div>
<%-- </security:authorize> --%> 
          
          <div class="col-md-7">
              <div class="form-group">
                <textarea class="form-control" placeholder="Texto da Planilha" rows="4" name="informacoes" style="width: 475px;"></textarea>
              </div>

          </div>

          <div class="col-md-7" style="margin-bottom: 8px">
              <div class="form-group">
                <textarea class="form-control" placeholder="Necessidades" rows="4" name="necessidades" style="width: 475px;"></textarea>
              </div>
          </div>  
          
	  	<div class="col-md-12" style="margin: 20px 0px;">
          <div class="col-md-8 tira-padding">
                <input class="btn btn-danger" type="submit" value="Salvar linha" name="salvarGrupoBotao" >
                <a class="btn btn-info" onclick="insereLinha(exibelinha${categoria.idcategoria},${categoria.idcategoria});">Cancelar</a>
          </div>
         </div> 

</form>
			
		</div>
     </div>
      
</div>


    </td>
  </tr>
</c:if>
<!-- -----------------------------------------------------------------------------------------------------------------------------------------  -->		          
		
		<tr style="background-color: #F7F3F7">
		  
		  	
		  	
			<c:set var="total" value="0" />  

			<c:forEach items="${categoria.grupo}" var="categorias">
				<c:if test="${categorias.opcional == true}"></c:if>
				<c:if test="${categorias.opcional == false}">
					<c:set var="total" value="${total + categorias.grupoValorIncideImposto}" />
				</c:if>
 		    </c:forEach>
			
			
				<c:set var="locco" value="0" />  
			<c:forEach items="${categoria.grupo}" var="fatDireto">
				<c:if test="${fatDireto.opcional  == true}">
				</c:if>
				<c:if test="${fatDireto.opcional  == false}">
					<c:set var="locco" value="${locco + fatDireto.grupoValorNaoIncideImposto}" />
				</c:if>
			</c:forEach>
				
				
							
			<c:set var="totalsub"  value="${total + locco}" />
			<c:set var="totalFatLocco" value="${totalFatLocco + total}" />
    		<c:set var="totalFatDireto" value="${totalFatDireto + locco}" />
			
			<c:set var="subtotal"  value="${subtotal + total + locco}" />
			
		
		  <td >	
			<span style="padding-left: 0px">${categoria.categoria}:  <strong><fmt:formatNumber value="${totalsub}" pattern="#,##0.00" /></strong></span> 
          </td>
          
	      <td>	 
	    	 <fmt:formatNumber value="${total}" pattern="#,##0.00" />
	      </td>

				     <c:set var="locco" value="0" />  
				
	
			<c:forEach items="${categoria.grupo}" var="fatDireto">
						<c:if test="${fatDireto.opcional  == true}">
							<c:set var="teste" value="${1}" />
	
						</c:if>
						<c:if test="${fatDireto.opcional  == false}">
							<c:set var="teste" value="${0}" />
							<c:set var="locco" value="${locco + fatDireto.grupoValorNaoIncideImposto}" />
						</c:if>
			
				</c:forEach>
		 
		  <td>	 
		 	  <fmt:formatNumber value="${locco}" pattern="#,##0.00" />
	      </td>

          <td></td>
          <td></td>
          <td></td>
    
    <c:if test="${idlistaEstatus == 1}"> 
          <td></td> <td></td>
	</c:if>
	
		</tr>
		
		<tr><td colspan="8"></td></tr>
		
		</c:forEach>
		
		
	    <tr>
			<td style="font-size: 15px">
			
				Sub Total: <fmt:formatNumber value="${subtotal}" pattern="#,##0.00" />
			</td>
			
			<td style="font-size: 15px"><fmt:formatNumber value="${totalFatLocco}" pattern="#,##0.00" /></td>
			<td style="font-size: 15px"><fmt:formatNumber value="${totalFatDireto}" pattern="#,##0.00" /></td>
			<td></td>
			<td colspan="4"></td>
			
		</tr>

</table>


  

 </div>
</div>
</div> 
</div> 
<!-- Fim Edição Planilha -->

<div class="col-md-12 alpha60 div-confirmacao" id="ConfirmaConclusaoPlanilha" style="position: fixed;">
	<div class="col-md-4"></div>


	<div class="col-md-4 sub-div-confirmacao">
		<div class="col-md-12">
			<i class="glyphicon glyphicon-question-sign"
			style="font-size: 43px;color: #e74c3c;border: 1px solid #e74c3c;border-radius: 57px;padding: 3px;"></i>
			<h4>Deseja realmente concluir a planilha ?</h4>
		</div>
		
		<div class="col-md-12 div-botao-confirmacao">
			<a onclick="concluiPlanilhaPorAjax(${idLista});" class="btn btn-danger botao-confirmacao" style="font-size: 18px;">Concluir</a>
			<a onclick="location.reload();" class="btn btn-default botao-confirmacao" style="font-size: 18px;">Cancelar</a>
		</div>
		
	</div>
	
	<div class="col-md-4"></div>
</div>




<div class="col-md-12 alpha60 div-confirmacao" id="listaConcluida" style="position: fixed;"></div>


<div class="col-md-12 alpha60 div-confirmacao" id="geraExcel" style="position: fixed;"></div>

<div class="col-md-12 alpha60 div-confirmacao" id="excluiGrupo" style="position: fixed;">

	<div class="col-md-4"></div>
	<div class="col-md-4 sub-div-ordena" style="height: 210px!important;margin-top: 20%!important;">
		<div class="col-md-12" style="height: 75px;margin-bottom: 20px;">
			<h4>Excluir Linha</h4>
			<p style="line-height: 20px;">Esse item está sendo produzido na Criação.<br> Deseja realmente excluir ?</p>
		</div>

		<div class="col-md-12 div-botao-confirmacao">
			
			<a id="excliuGrupoItem" class="btn btn-danger botao-confirmacao">
			  Excluir
			</a>
		
			<a onclick="location.reload();" class="btn btn-default botao-confirmacao">Cancelar</a>
		</div>
		

		</div>		

</div>





<div class="col-md-12 alpha60 div-confirmacao" id="ordenaLista">


	<div class="col-md-12"></div>
	<div class="col-md-12 sub-div-ordena">
		<div class="col-md-12">
			<h4>ORDENAR LISTA</h4>
		</div>

		<div class="col-md-12 div-botao-confirmacao">
			<a onclick="ordenaListaCarregaLista();" class="btn btn-success botao-confirmacao">Ordenar Categorias</a>
			<a onclick="toogle();" class="btn btn-default botao-confirmacao">Ordenar Linhas</a>
		</div>
		
<!-- ------------------------- -->
		
		<div id="boxOrdenacao" class="col-md-12 div-botao-confirmacao" style="border-top: 1px solid gray;border-bottom: 1px solid gray;height: 450px">

			

<!-- ------------------------- -->

		</div>		

	</div>
	<div class="col-md-4"></div>




</div>





<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<c:import url="../_comum/footer.jsp" />

<script type="text/javascript">

function infoGalderma(idLista) {
		
		var info = $("#infoGalderma").text();
		
		$.ajax({
			url : "editaInfoGaldermaTexto?idLista="+idLista+"&info="+info,
			success : function(data) {
				/* $("#ConfirmaPagamento").fadeOut(500);
				$("#contasPagarAjax").html(data); */
				
				alert(data);
			},
			beforeSend : function() {
				/* $("#ConfirmaPagamento").fadeIn(500); */
			},
			complete : function() {
				/* $("#ConfirmaPagamento").fadeOut(500); */
			}
		});
		
	}
</script>

<script type="text/javascript" src="<c:url value="resources/js/listaProducaoAjax.js" />"></script>