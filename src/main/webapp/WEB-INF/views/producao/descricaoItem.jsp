<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<c:set  value="${0}" var="custo"/>	
<c:set  value="${0}" var="preco"/>	
<c:set  value="${0}" var="bv"/>	
<c:forEach items="${produtoGrupo}" var="produtoGrupo">
	<c:set value="${produtoGrupo.custoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias) + custo}" var="custo"/>
	<c:set value="${produtoGrupo.precoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias) + preco}" var="preco"/>
	<c:set value="${produtoGrupo.bvFornecedorValor + bv}" var="bv"/>
	<c:set value="${produtoGrupo.custoComBvFornecedor}" var="custoComBvFornecedor" />
	<c:set value="${produtoGrupo.idProduto}" var="idProduto" />
	<c:set value="${produtoGrupo.produto}" var="produto" />
	<c:set value="${produtoGrupo.empresa.empresa}" var="fornecedor" />
	<c:set value="${produtoGrupo.isReembolsoDespesas()}" var="reembolsoDespesas" />
</c:forEach>


	<c:set  value="${0}" var="custos"/>	
	<c:set  value="${0}" var="bvs"/>	
	<c:set  value="${0}" var="precos"/>	

	<c:set  value="${detalhesItem.precoProduto * (detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" var="precos"/>
	<c:set  value="${detalhesItem.custoProduto * (detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" var="custos"/>




		<div class="col-md-12 bodyXY" style="height: 35px;">
		
			<ol class="breadcrumb">
				<li><a href="#">Menu</a></li>
				<li><a href="listaProducao">Produção</a></li>
				<li class="active"><a href="editaLista?idLista=${lista[3]}">Edição de lista de Produção</a></li>
				<li class="active"><a href="editaLinha?idGrupo=${idGrupo}">Editar Linha</a></li>
				<li class="active">Editar Item</li>
			</ol>
		
		</div>
    </div>
</div>
<br /><br />

<div class="container shadow-com-padding" style="background: #fff;margin-bottom: 200px;"> 
 	<div class="row" style="margin-top: 0;background-color: #f2f2f2;border-bottom: 1px solid #ccc;">
	  <div class="col-md-12 tira-padding" style="font-family: 'OpenSansLight'">
	  	<h4><a href="editaLinha?idGrupo=${idGrupo}" class="botao-voltar" style="margin-left: 0"><i class="glyphicon glyphicon-menu-left"></i></a>
	  	Lista: <strong style="margin-right: 15px">${lista[0]}</strong>
	  	Cliente: <strong style="margin-right: 15px">${empresa}</strong>
	  	Lista cod. <strong	>${lista[1]} .${lista[2]}</strong></h4>
	  
	  
	  </div>
	</div>
	
	<div class="col-md-12 bodyXY" style="padding:0px 15px !important">
		<div class="row" style="margin-left: 5px;">	
			<table class="table table-condensed col-md-5" >
				<tr class="border-top-table-1">
					<td>Linha:  <strong>${grupo[0]}</strong></td>
					<td>Custo:  <strong>R$ <fmt:formatNumber value="${custo}" pattern="#,##0.00" /></strong></td>
					<td>Preço:  <strong>R$ <fmt:formatNumber value="${preco}" pattern="#,##0.00" /></strong></td>
					<td>[R$<fmt:formatNumber value="${preco - custo }" pattern="#,##0.00" />]</td>
					<td>[cl R$ <fmt:formatNumber value="${bv}" pattern="#,##0.00" />]</td>
				
				</tr>
			</table>
	   </div>


<p>ITEM: <a onclick="efeitoToogle(tagsItem)" style="cursor: pointer;font-size: 16px">${detalhesItem.produto}</a></p>
<div class="row display-none" id="tagsItem">
		<div class="form-group col-md-12 tira-padding painel" style="background-color: #F2F2F2;
		    padding-bottom: 20px;">
        	<div class="divisor"></div>
                <div class="col-md-3" >
                    Tags
                    <select name="" id="" class="form-control" onchange="produtoAtuacao(this.value);">
                      <option value="selecioneProduto">Selecione um produto</option>
                      <c:forEach items="${tags}" var="tags">
                      	<option value="${tags.idAtuacao}">${tags.atuacao}</option>
                      </c:forEach>
                    </select>
                </div>

  <form action="atualizaDetalhesItem" method="post" id="atualizaDetalhesItem" > 
      <input type="hidden" value="${idGrupo}" name="idGrupoTransiente" id="idGrupoHidden"/>              
      <input type="hidden" value="${idProdutoGrupo}" name="idProdutoGrupo" />              
      <input type="hidden" name="salvarEfechar" id="salvarEfechar" />              
                <div class="col-md-5">
     	           Produtos
                   <select  class="form-control" name="idprodutoTransiente" id="produtoAtuacao" onchange="InformacaoProduto(this.value);" size="6">
                     	    <option value="${detalhesItem.idProduto.idproduto}" selected="selected">${detalhesItem.idProduto.produto}</option>
                      <c:forEach items="${produtos}" var="produtos">
                      
                           <c:if test="${produtos[0] == idProduto.idproduto}"></c:if>
                           
                           <c:if test="${produtos[0] != idProduto.idproduto}">
	                           <option value="${produtos[0]}">${produtos[1]}</option>
                           </c:if>
                      </c:forEach>
                    </select> 
                </div>
                
                <!-- <div class="col-md-3" >
                	<br>
                	<a onclick="insereNovoProduto('atualizaProdutoLista');" class="btn btn-primary">Inserir Produto</a>
                </div> -->
                
                
                
          </div>  
      </div>
<!--       Fim Filtro -->

<!--           Desc Item     -->
<div class="row">
       <div class="form-group col-md-12 tira-padding painel" style="padding: 10px 0;">
                            
                <div class="col-md-4" style="font-size:12px;color:#000;border-right:15px solid #fff;background-color: #F2F2F2 !important;height: 195px;">
                    <h5>Informações de   cadastro</h5>
                    <p>Custo cadastro R$ <span id="custoCadastro">0,00</span></p>
                    <p>Margem % <span>0,00</span></p>
                    <p>Preço s/ NF R$ <span id="precoSemNota">0,00</span></p>
                    <div class="checkbox ">

                     <c:if test="${custoComBvFornecedor == true}"> 	
	                    <label>
	                      <!-- <input type="checkbox" id="CheckbvFornecedor" name="custoComBvFornecedor" checked="checked">
	                      Custo com BV forn. incluso -->
	                    </label>
	                    <div class="controls" id="bvFornecedor">
					</c:if>
					
					 <c:if test="${custoComBvFornecedor == false}"> 	                    
                    	<label> 
                      		<!-- <input type="checkbox" id="CheckbvFornecedor" name="custoComBvFornecedor">Custo com BV forn. incluso -->
                    	</label>
						<div class="controls display-none" id="bvFornecedor">	
					</c:if>
					
						Valor CL forn. incluso
							<select id="" name="bvFornecedorEmPorcentagem" class="form-control">
								<c:if test="${detalhesItem.bvFornecedorEmPorcentagem == true}">
									<option value="1">%</option>
									<option value="0">R$</option>
								</c:if>
								
								<c:if test="${detalhesItem.bvFornecedorEmPorcentagem == false}">
									<option value="0">R$</option>
									<option value="1">%</option>
								</c:if>
							</select>


				<c:if test="${empty detalhesItem.bvFornecedor}">
					<input class="form-control" name="bvFornecedorTransiente" value="0,00">
				</c:if>							
				
				<c:if test="${not empty detalhesItem.bvFornecedor}">
					<input class="form-control" name="bvFornecedorTransiente" value="<fmt:formatNumber value='${detalhesItem.bvFornecedor}' pattern="#,##0.00" />" >
				</c:if>							

				</div>
			</div>	
	 	</div>
	 		
<span style="position: absolute;margin-top: -2%;color: red;display: none;font-size: 13px" id="camposUnitarios">*Preencha um desses campos em vermelho com um valor</span>
<c:import url="listaProducao/salvaProdutoGrupo/atualizaProduto/custoItens.jsp" /> <!-- Custo Itens -->


<c:import url="listaProducao/salvaProdutoGrupo/atualizaProduto/produtoGrupoQuantidades.jsp" /> <!--  Qtd Custo   -->            
<c:import url="listaProducao/salvaProdutoGrupo/atualizaProduto/fornecedores.jsp" />  <!-- Desc Fornecedores -->
    

<!-- - - - - - - - - - Observação - - - - - - - - - -  -->
<div class="row" style=" margin: 0 0 15px 0;">
    <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">
      <div class="col-md-6">
          Observações <br>
          <textarea rows="5" class="form-control voltar" name="observacoes">${detalhesItem.observacoes}</textarea>
      </div>
    </div>
</div>
<!-- - - - - - - - - - - - Fim Observação - - - - - - - -->


<!-- - - - - - - - - - - - Salvar - - - - - - - - - - - -->
            <div class="row" style=" margin: 0 0 15px 0;">
	            <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 17px 0 5px 0;">
	
	                    <div class="col-md-4">
 	                      <button class="btn btn-danger" id="Cadeiras2" onclick="salvaEfechar(1);">Salvar e Fechar</button>
	                      <button class="btn btn-danger" onclick="salvaEfechar(0);">Salvar</button>
	                      <a href="editaLinha?idGrupo=${idGrupo}" class="btn btn-info" id="Cadeiras3">Cancelar</a>
	                    </div>
</form>                   
	                    <div class="col-md-5" style="line-height: 32px;">
			              <p>Atualizado por: ${detalhesItem.usuario.nome}
			               - em  <fmt:formatDate value="${detalhesItem.alteradoEm.time}" pattern="dd/MM/yyyy HH:mm:ss"/>  </p>		
	        			</div>
	           </div>


<div class="col-md-12 alpha60 div-confirmacao" id="novoProduto" style="left: 0;">
	<c:import url="novoProduto/novoProduto.jsp" />
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="novoFornecedor" style="left: 0;">
	<c:import url="novoFornecedor/novoFornecedor.jsp" />
</div>

</div>













<c:import url="../_comum/footer.jsp" />          
          
<script type="text/javascript" src="<c:url value="resources/js/produtos.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/fornecedor.js" />"></script>          
          
          
          
 <script type="text/javascript">
 $(document).bind('keydown',function(e){
		/* if(e.which == 8 ){
			return false;
		}
		else{
			return true;
		};
		 */
		$("#empresaFornecedor").bind('keydown',function(e){
			if(e.which == 8 ){
				return false;
			}
			else{
				return true;
			}
		});
});

$(document).ready(function(){
     $('.voltar').bind('keydown',function(e){
     		if(e.which == 8){
     			var str = $(this).val();
     			str = str.substring(0,(str.length - 1));
     			$(this).val(str);
     		}else{
     		}
     })       
});

</script>
 