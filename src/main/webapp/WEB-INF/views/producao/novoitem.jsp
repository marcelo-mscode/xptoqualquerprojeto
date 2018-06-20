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
	<c:set  value="${produtoGrupo.custoComBvFornecedor}" var="custoComBvFornecedor" />
	<%-- <c:set  value="${produtoGrupo.reembolsoDespesas}" var="reembolsoDespesa" /> --%>
	
	
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

<div class="container shadow-com-padding" style="background: #fff;margin-bottom: 160px;padding-bottom: 45px;margin-top: 15px;"> 
	<div class="row" style="margin-top: 0;background-color: #f2f2f2;border-bottom: 1px solid #ccc;">
	  <div class="col-md-12 tira-padding" style="font-family: 'OpenSansLight'">
	  	<h4><a href="editaLinha?idGrupo=${idGrupo}" class="botao-voltar" style="margin-left: 17px;padding: 4px 15px;"><i class="glyphicon glyphicon-menu-left"></i></a>
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
	   
<div class="row">
<h3 style="font-family: 'OpenSansLight'">NOVO ITEM</h3>
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
                
 <form action="salvaDetalhesItem" method="post" id="cadastraNovoItem" onsubmit="salvarItemBotao.disabled = true; return true;">
 <input type="hidden" value="${idGrupo}" name="idGrupoTransiente" id="idGrupoHidden"/>
 <input type="hidden" name="salvarEfechar" id="salvarEfechar" />
                <div class="col-md-5">
                Produtos*
                   <select  class="form-control" name="idprodutoTransiente" id="produtoAtuacao" onchange="InformacaoProduto(this.value);" size="6">
                      <c:forEach items="${produtos}" var="produtos">
                      		<option value="${produtos[0]}">${produtos[1]}</option>
                      </c:forEach>
                    </select> 
                </div>
                
                <div class="col-md-3" >
                	<br>
                	<a onclick="insereNovoProduto('salvaProdutoLista');" class="btn btn-primary">Inserir Produto</a>
                </div>
                
          </div>  
      </div>
<!--       Fim Filtro -->

<!--           Desc Item     -->

<div class="row">
      <div class="form-group col-md-12 tira-padding painel" style="padding: 10px 0;">

                                
                <div class="col-md-4" style="height: 148px;font-size:12px;color:#000;border-right:15px solid #fff;background-color: #F2F2F2 !important;padding-bottom: 186px !important;">
                    <h5>Informações de   cadastro</h5>
                    <p>Custo cadastro R$ <span id="custoCadastro"></span></p>
                    <p>Margem % <span></span></p>
                    <p>Preço s/ NF R$:<span id="precoSemNota"></span></p>
                </div>

<!--                Custo Itens -->
<span style="position: absolute;margin-top: -1%;color: red;font-size: 13px;display: none;z-index: 9999" id="camposUnitarios">*Preencha um desses campos em vermelho com um valor</span>              
              <div class="col-md-4" style="border-right:15px solid #fff;font-size: 12px;line-height: 20px;background-color: #F2F2F2;padding-bottom: 15px;">
                      <div class="form-inline" style="margin-top:10px">
                      Custo Net Unit. R$&nbsp
                     
                      	<input id="custoItemunitario" type="text" class="form-control" style="height: 24px;width: 110px;"
                      	name="custoProdutoTransiente" value="0,00" onblur="calculaPrecoSNF();">
                    
                      
                      </div>
<div class="divisor"></div>	
<!--                     <div class="form-inline">
                  Marg. Padr.(Over)
                    &nbsp&nbsp
                    	<input type="checkbox"  checked="checked">
                    &nbsp% <span>0,00</span>
                    </div>
 -->

                    <div class="form-inline">
                      CL. Venda R$
                      &nbsp&nbsp&nbsp&nbsp&nbsp	&nbsp&nbsp&nbsp&nbsp
                    <input type="text" class="form-control" onblur="calculaPrecoSNF();" id="bvFornecedorValorEdita" style="height: 24px;width: 110px;" name="bvTransiente" value="0,00">
                    </div>
 <div class="divisor-fino"></div>                  
                    <div class="form-inline" style="background:#e4eef2">
                      Preço Unit. s/ NF R$
                      <span id="custoTotalSFNCalculado">0,00</span>&nbsp&nbsp
                      [<span id="tiraUnitPeloBv">0,00</span>]
                    </div>
                    
                    <div class="form-inline">
                    Incide Imposto
                    &nbsp&nbsp
                    <input type="checkbox" checked="checked" name="imposto" id="incideimpostoCheckbox" onclick="calculaPrecoSNF();">
                    &nbsp<span>% ${impostoGrupo.imposto}</span>
                    </div>                  
<!-- Reembolso de despesas  -->
                    <div class="form-inline" style="margin-bottom: 5px;">
                    Reembolso de Despesas Bayer
                    &nbsp&nbsp
                    <input type="checkbox" name="reembolsoDespesas" id="reembolsoCheckbox">
                    </div>                  
<!--  -->

                    <div class="form-inline" style="background:#e4eef2">
                      Preço Unit. Final R$ <span id="precoUnitFinal">0,00</span>
                    </div>

              </div>
               <!-- Fim Custo Itens -->

              <!--  Qtd Custo   -->
              <div class="col-md-4" style="font-size:12px;line-height: 27px;background-color: #F2F2F2;padding-bottom: 40px;">
                  <div class="form-inline" style="margin-top:10px">
                    Quantidade&nbsp&nbsp
                    <!-- <input id="quantidade1" onblur="calculaValorTotal();"  type="text" class="form-control voltar"  style="height: 24px;width: 50px;font-size: 11px;padding: 5px;" name="quantidade" value="1"> -->
                    <input id="quantidade1" onblur="calculaValorTotal();"  type="text" class="form-control voltar"  style="height: 24px;width: 50px;font-size: 11px;padding: 5px;" name="quantidadeTransiente" value="1">
                    
                    
                  <!-- X <input id="quantidade2" onblur="calculaValorTotal();" type="text" class="form-control voltar" style="  height: 24px;width: 50px;font-size: 11px;padding: 5px;" name="quantidade2" value="1"> -->
                  X <input id="quantidade2" onblur="calculaValorTotal();" type="text" class="form-control voltar is-disabled" style="  height: 24px;width: 50px;font-size: 11px;padding: 5px;background-color: #ccc;" name="quantidade2Transiente" value="1">
                  = <span id="QtdUnidadeItem"> 1 </span>&nbsp<span id="tipoUnidadeItem"> Unid.</span>
                    
                   <div class="form-inline" style="margin-left:22px">
                        X Diárias
                        <input id="diarias" onblur="calculaValorTotal();" type="text" class="form-control voltar" style="  height: 24px;width: 50px;font-size: 11px;padding: 5px;" name="diariasTransiente" value="1" >
                    </div>


                    
                    <div class="form-group">
                      Custo total: R$
                      <span id="custoTotal">0,00 </span>
                      
                    </div>
					<div class="divider"></div>
                    <div class="form-group">
                      Valor s/ NF R$ <span id="valorSemNF">0,00</span>&nbsp[ <span id="bvValor">0,00</span> ]
                    </div>
					<div class="divider"></div>
                    <div class="form-group" style="font-weight:bold">
                       Valor Final: R$ <span id="valorFinal">0,00</span>
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
                  Fornecedores* <br>  
                 <!--  <span style="font-size:12px">( Relacionado&nbsp<input type="checkbox"> )</span> -->
                  </div>

                  <div class="col-md-6" id="forncedorListaAjax">

                    <select size="" class="form-control input-260px" name="idEmpresaTransiente" id="empresaFornecedor">
                    <option value="SelecioneFornecedor">Selecione um Fornecedor</option>
                      <c:forEach items="${fornecedores}" var="fornecedores">
                      	 <option value="${fornecedores[0]}">${fornecedores[1]}</option>
                      </c:forEach>
                    </select>                   

                  </div>
                  
                  <div class="col-md-3" >
                	<a onclick="insereNovoFornecedor();" class="btn btn-primary">Inserir Fornecedor</a>
                  </div>
                  
                  

              </div>
          </div>
<!-- - - - - - - - - Fim Desc Fornecedores - - - -- - --> 

<!-- - - - - - - - - - Observação - - - - - - - - - -  -->
          <div class="row" style=" margin: 0 0 15px 0;">
              <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">
                <div class="col-md-6">
                    Observações <br>
                    <textarea rows="5" class="form-control voltar" name="observacoes"></textarea>
                </div>
              </div>
            


          </div>
<!-- - - - - - - - - - - - Fim Observação - - - - - - - -->


<!-- - - - - - - - - - - - Salvar - - - - - - - - - - - -->
          <div class="row" style=" margin: 0 0 15px 0;">
              <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">

                    <div class="col-md-4">
                      <button class="btn btn-danger" id="Cadeiras2" onclick="salvaEfechar(1);" name="salvarItemBotao">Salvar e Fechar</button>
                      <button class="btn btn-danger" onclick="salvaEfechar(0);" name="salvarItemBotao">Salvar</button>
                      <a href="editaLinha?idGrupo=${idGrupo}" class="btn btn-info" id="Cadeiras3">Cancelar</a>
                    </div>
</form>            
                    
                   <div class="col-md-5" style="line-height: 25px;">
			             			
	        	   </div>
	           </div>
           </div>
</div>        
  
<div class="col-md-12 alpha60 div-confirmacao" id="novoProduto" style="left: 0;">
	<c:import url="novoProduto/novoProduto.jsp" />
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="novoFornecedor" style="left: 0;">
	<c:import url="novoFornecedor/novoFornecedor.jsp" />
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>


 
<script type="text/javascript" src="<c:url value="resources/js/produtos.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/fornecedor.js" />"></script>

 
<script type="text/javascript">
 /* $(document).bind('keydown',function(e){

	 	if(e.which == 8 ){
			return false;
		}
		else{
			return true;
		};
}); */

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




<c:import url="../_comum/footer.jsp" />  

<script>
function emailSucesso(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-sucesso-email").slideToggle("slow"); 
	    }, 1000);
	});
}
function emailErro(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-erro-email").slideToggle("slow"); 
	    }, 10000);
	});
}
</script>


<c:if test="${msg.get(0) == 'sucesso'}">
	<div class="msg-email msg-sucesso-email" id="msg-sucesso-email" >${msg.get(1)}</div>
	<script type="text/javascript">emailSucesso();</script>
</c:if>	

<c:if test="${msg.get(0) == 'erro'}">
	<div class="msg-email msg-erro-email" id="msg-erro-email">${msg.get(1)}</div>
	<script type="text/javascript">emailErro();</script>
</c:if>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br>