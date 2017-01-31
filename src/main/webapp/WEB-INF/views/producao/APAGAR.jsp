<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />
	<c:set  value="${0}" var="custo"/>	
	<c:set  value="${0}" var="preco"/>	
	<c:set  value="${0}" var="bv"/>	
	<c:forEach items="${produtoGrupo}" var="produtoGrupo">
	<c:set  value="${produtoGrupo.custoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias) + custo}" var="custo"/>
	<c:set  value="${produtoGrupo.precoProduto * (produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias) + preco}" var="preco"/>
	<c:set  value="${produtoGrupo.bvFornecedorValor + bv}" var="bv"/>
	<c:set value="${produtoGrupo.custoComBvFornecedor}" var="custoComBvFornecedor" />
</c:forEach>
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

<div class="container shadow-com-padding" style="background: #fff"> 
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
					<td>Linha:  <strong>${grupo}</strong></td>
					<td>Custo:  <strong>R$ <fmt:formatNumber value="${custo}" pattern="#,##0.00" /></strong></td>
					<td>Preço:  <strong>R$ <fmt:formatNumber value="${preco}" pattern="#,##0.00" /></strong></td>
					<td>[R$<fmt:formatNumber value="${preco - custo }" pattern="#,##0.00" />]</td>
					<td>[bv R$ <fmt:formatNumber value="${bv}" pattern="#,##0.00" />]</td>
				
				</tr>
			</table>
	   </div>


<p>ITEM: <a onclick="efeitoToogle(tagsItem)" style="cursor: pointer;font-size: 16px">${produto}</a></p>
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

 <form action="salvaDetalhesItem" method="post" id="cadastraNovoItem"> 
 <input type="hidden" value="${idGrupo}" name="idGrupoTransiente" />
 <input type="hidden" name="salvarEfechar" id="salvarEfechar" />            
                <div class="col-md-5">
     	           Produtos
                   <select  class="form-control" name="idprodutoTransiente" id="produtoAtuacao" onchange="InformacaoProduto(this.value);" size="6">
                      <c:forEach items="${produtos}" var="produtos">
                      		<option value="${produtos[0]}">${produtos[1]}</option>
                      </c:forEach>
                    </select> 
                </div>
          </div>  
      </div>
<!--       Fim Filtro -->

<!--           Desc Item     -->
<div class="row">
       <div class="form-group col-md-12 tira-padding painel" style="padding: 10px 0;">
                            
                <div class="col-md-4" style="font-size:12px;color:#000;border-right:15px solid #fff;background-color: #F2F2F2 !important">
                    <h5>Informações de   cadastro</h5>
                    <p>Custo cadastro R$ <span id="custoCadastro"></span></p>
                    <p>Margem % <span>0,00</span></p>
                    <p>Preço s/ NF R$ <span id="precoSemNota"></span></p>
                    
                    
                     <div class="checkbox ">
		               <input type="checkbox" id="CheckbvFornecedor" name="custoComBvFornecedor">
		               Custo com BV forn. incluso
					
						<div class="controls display-none" id="bvFornecedor">
							Valor BV forn. incluso
							<select id="" name="bvFornecedorEmPorcentagem" class="form-control">
								<option value="1">%</option>
								<option value="0">R$</option>
							</select>
						
						<input class="form-control" name="bvFornecedorTransiente" value="0,00">
						</div>
				    </div>
			</div>	
	 	</div>	
<!--                Custo Itens -->
            <div class="col-md-4" style="border-right:15px solid #fff;font-size: 12px;line-height: 20px;background-color: #F2F2F2;padding-bottom: 4px;">
                      <div class="form-inline" style="margin-top:10px">
                      Custo Net Unit. R$&nbsp
                      
                      <input id="custoItemunitario" type="text" name="custoProdutoTransiente" class="form-control real" style="  height: 24px;width: 110px;" 
                      value="<fmt:formatNumber value='${detalhesItem.custoProduto}' pattern="#,##0.00" />"
                      >
                      </div>

                    <div class="form-inline">
                    Marg. Padr.(Over)
                    &nbsp&nbsp
                    <c:if test="${detalhesItem.margemPadrao == true}">
                    	<input type="checkbox" checked="checked">
                    </c:if>
                    
                    <c:if test="${detalhesItem.margemPadrao == false}">
                    	<input type="checkbox" >
                    </c:if>
                    
                    
                    
                    &nbsp% <span>0,00</span>
                    </div>

                    <div class="form-inline">
                      BV. Venda R$
                      &nbsp&nbsp&nbsp&nbsp&nbsp
                      <input type="text" name="bvTransiente" class="form-control real" style="height: 24px;width: 110px;"
                      value="<fmt:formatNumber value="${detalhesItem.bv}" pattern="#,##0.00" />"
                      >
                    </div>
                    
                    <div class="form-inline" style="background:#e4eef2">
                      Preço Unit. s/ NF R$ <span id="precoUnitSemNF">0,00</span>&nbsp&nbsp<span>[0,00]</span>
                    </div>
                    
                    <div class="form-inline">
                    Incide Imposto
                    &nbsp&nbsp
                    <c:if test="${detalhesItem.imposto == true}">
                    	<input type="checkbox" checked="checked" name="imposto">
                    </c:if>
                    
                    <c:if test="${detalhesItem.imposto == false}">
                    	<input type="checkbox" name="imposto">
                    </c:if>
                    
                    &nbsp<span>% ${Impostogrupo.imposto}</span>
                    </div>                  

                    <div class="form-inline" style="background:#e4eef2">
                      Preço Unit. Final R$ <span id="precoUnitFinal">0,00</span>
                    </div>

              </div>
               <!-- Fim Custo Itens -->

              <!--  Qtd Custo   -->
              <div class="col-md-4" style="font-size:12px;line-height: 27px;background-color: #F2F2F2;padding-bottom: 1px;">
                  <div class="form-inline" style="margin-top:10px">
                    Quantidade&nbsp&nbsp
                      <input id="quantidade1" onblur="calculaValorTotal();" type="text" class="form-control" style="height: 24px;width: 50px;font-size: 11px;" value="${detalhesItem.quantidade}" name="quantidade">
                    X <input id="quantidade2" onblur="calculaValorTotal();" type="text" class="form-control" style="  height: 24px;width: 50px;font-size: 11px;" value="${detalhesItem.quantidade2}" name="quantidade2"> = 1 Unid.
                    
                    <div class="form-inline" style="margin-left:22px">
                        X Diárias
                        <input id="diarias"   onblur="calculaValorTotal();"type="text" class="form-control" style="  height: 24px;width: 50px;font-size: 11px;" value="${detalhesItem.diarias}" name="diarias">
                    </div>
                    
                    <div class="form-group">
                      Custo total: R$
                      <span id="custoTotal">
                      
                      <fmt:formatNumber value="${detalhesItem.custoProduto*(detalhesItem.quantidade * detalhesItem.quantidade2 * detalhesItem.diarias)}" />
                      
                      </span>
                      
                    </div>
					<div class="divider"></div>
                    <div class="form-group">
                      Valor s/ NF R$ <span id="valorSemNF">0,00</span>&nbsp[0,00]
                    </div>
					<div class="divider"></div>
                    <div class="form-group" style="font-weight:bold">
                      Valor Final: R$ <span id="valorFinal">32.425,42</span>
                    </div>
                      
                  </div>  
              </div>
   </div>
</div>           
              <!--  Fim Qtd Custo -->
<!--  - - - - - - - Desc Fornecedores - - - - - - - - - -  -->

		
     <div class="row">
          <div class="form-group col-md-12 tira-padding" style="padding: 15px 0px 10px 0;">
                  <div class="col-md-2">
                  Fornecedores <br>  
                  <span style="font-size:12px">( Relacionado&nbsp<input type="checkbox"> )</span>
                  </div>

                  <div class="col-md-4">

                    <select  multiple="" class="form-control" name="" id="">
                      <c:forEach items="${fornecedores}" var="fornecedores">
                      	 <option value="${fornecedores[0]}">${fornecedores[1]}</option>
                      </c:forEach>
                    </select>                   

                  </div>

                  <div class="col-md-2">
                  <a href="#">&nbsp&nbsp&nbsp&nbspInserir >></a><br><br>
                  <a href="#"><< Remover </a>
                  </div>
 
                  <div class="col-md-3">
                      <select  multiple="" class="form-control" name="" id="">
                        <option value=""></option>

                      </select>  
                  </div>
              
  


              </div>
          </div>
<!-- - - - - - - - - Fim Desc Fornecedores - - - -- - --> 

<!-- - - - - - - - - - Observação - - - - - - - - - -  -->
          <div class="row" style=" margin: 0 0 15px 0;">
              <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">
                <div class="col-md-6">
                    Observações <br>
                    <textarea rows="5" class="form-control" name="observacoes">${detalhesItem.observacoes}</textarea>
                </div>
              </div>
            


          </div>
<!-- - - - - - - - - - - - Fim Observação - - - - - - - -->


<!-- - - - - - - - - - - - Salvar - - - - - - - - - - - -->
            <div class="row" style=" margin: 0 0 15px 0;">
	            <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 15px 0 15px 0;">
	
	                    <div class="col-md-4">
	                      <button class="btn btn-danger" onclick="salvaEfechar(0);">Salvar</button>
	                      <button class="btn btn-danger" id="Cadeiras2" onclick="salvaEfechar(1);">Salvar e Fechar</button>
	                      <a href="editaLinha?idGrupo=${idGrupo}" class="btn btn-info" id="Cadeiras3">Cancelar</a>
	                    </div>
</form>                   
	                    <div class="col-md-5" style="line-height: 25px;">
			              <p>Atualizado por Marcelo - em 19/08/2015 15:04</p>		
	        			</div>
	           </div>
           </div>
</div>          

<br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />          
          
          