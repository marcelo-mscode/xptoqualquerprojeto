<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<script type="text/javascript" src="resources/js/jquery-1.11.2.min.js"></script>	
<script type="text/javascript">
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

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li><a href="listaProdutos">Lista de Produtos</a></li>
    		<li class="active">Edição de Produto</li>
		</ol>
	</div>
</div>
<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 27px;background: #fff">
	<h4 style="font-family: 'OpenSansLight'">EDIÇÃO DE PRODUTO</h4>
</div>


<c:if test="${msg.get(0) == 'sucesso'}">
	<div class="msg-email msg-sucesso-email" id="msg-sucesso-email" >${msg.get(1)}</div>
	<script type="text/javascript">emailSucesso();</script>
</c:if>	

<c:if test="${msg.get(0) == 'erro'}">
	<div class="msg-email msg-erro-email" id="msg-erro-email">${msg.get(1)}</div>
	<script type="text/javascript">emailErro();</script>
</c:if>


<div class="container"> 

  <div class="row">
      <div class="col-md-7 painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;">
        <div class="col-md-10">
            
            
            
     <form action="atualizaProduto" method="post" id="salvandoProduto">
  			<c:forEach items="${produtos}" var="produtos">
  			<c:set var="unidadeProduto" value="${produtos.unidade.unidade}"  />
  			<c:set var="idProduto" value="${produtos.idproduto}"  />
  			
  			

  				<input type="hidden" name="idproduto"  value="${produtos.idproduto}"> 
  				<input type="hidden" name="idproduto"  value="${produtos.idproduto}"> 
			
                <div class="form-inline">
                	<c:if test="${produtos.habilitado == true}">
                    	<input type="checkbox" name="habilitado" checked="checked" class="checkbox">&nbsp;&nbsp;Ativo
                    </c:if>
                    
                    <c:if test="${produtos.habilitado == false}">
                    	<input type="checkbox" name="habilitado" class="checkbox">&nbsp;&nbsp;Ativo
                    </c:if>
                    
                    
                
                </div>
                
                <div class="form-group">
                <div class="divisor"></div>
                	Nome do Produto:
                    <input id="nomeProduto" type="text" name="produto" class="form-control" value="${produtos.produto}" placeholder="${produtos.produto}">
                </div>
                
                <div class="form-group">
                  Unidade:	
                  <select name="idUnid" id="" class="form-control">
                    <option value="${produtos.unidade.id}">${unidadeProduto}</option>
                    
                    
                    
                    <c:forEach items="${unidade}" var="unidade">
						<option value="${unidade.id}">${unidade.unidade}</option>            		
                    </c:forEach>
                   
                  </select>
                </div> 

                 <div class="form-group">
                   Gênero:
                   <select name="idGenero" id="" class="form-control">
                	 
                	 <option value="${produtos.genero.idgenero}">${produtos.genero.genero}</option>
	 					
	 					
	                    <c:forEach items="${generos}" var="genero">
	                    	
	                    	<option value="${genero.idgenero}">${genero.genero}</option>
	                    
	                    </c:forEach> 
	 
	              </select> 
                </div> 

                <div class="form-group">
                Custo Padrão:
                    <input type="text" class="form-control real"
                    name="custoPadrao1" placeholder="Custo Padrão" style="width:120px;"
                    value=<fmt:formatNumber value='${produtos.custoPadrao}' pattern="#,##0.00"/> >
                </div>

                <div class="form-group">
                Preço Padrão:
                  <input type="text" class="form-control real" name="precoPadrao1" placeholder="Preço Padrão" style="width:120px;"
                  value=<fmt:formatNumber value='${produtos.precoPadrao}' pattern="#,##0.00"/> > 
                  </div>
   <a name="marcas"></a>               
				
                <div class="form-group">
                Descrição do Produto:
                    <textarea name="produtodescricao" id="" rows="6" class="form-control" placeholder="Descrição ...">${produtos.produtodescricao}</textarea>
                </div>
                <div class="form-group">
                
                  <p style="font-size: 12px">Última atualização: <fmt:formatDate value="${produtos.dataAtualizacao.time}" pattern="dd/MM/yyyy - HH:mm:ss" />
                  </p>
                </div>
           </c:forEach>                    
                <button class="btn btn-danger">Salvar Produto</button>  

      </form>




        </div>
      </div>  

      <!-- <div class="col-md-3 painel ajuste-left">
            
            <div class="form-group">
              <button type="button" class="btn btn-link" >Fornecedores</button>
              <p class="desenvolvimento">Em desenvolvimento</p>
            </div>

      </div> -->


  </div>

 <div class="divisor-fino"></div>

  <div class="row">
      <div class="col-md-7 painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;margin-top: 0px;">
            
            <div class="form-group">
              <button class="btn btn-link" id="tiraTag">[+] Adicionar Tag</button>  
            </div>  
              <div class="col-md-6 display-none tag">  

                 <form action="SalvarTagProduto" method="post">
                  <input type="hidden" value="${idProduto}" name="idprodutoAtuacao"/>                  

                    <select class="form-control" name="idAtuacao" id="">
                           <c:forEach items="${tags}" var="tags">
		           		       	<option value="${tags.idAtuacao}">${tags.atuacao}</option>
		                  </c:forEach>
                    </select>  
                  
                    <div class="form-group">
                      <div class="divisor"></div>
                      <button type="submit" class="btn btn-danger">Adicionar Tag</button>&nbsp&nbsp&nbsp
                      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Tags">Criar Nova Tag</button>
                    </div>
                  </form>

              </div>
			
	
		
		 <div class="col-md-7 painel" style="padding-left: 0;margin-top: 15px">	
		 	<div class="col-md-12">  
				<b>Tags Relacionadas:</b>
				<table class="table table-condensed" style="margin-top: 5px;">
  				  <c:forEach items="${produtoAtuacao}" var="produtoAtuacao">
					<tr>
						<td class="input-160px"><span style="font-style: italic;">${produtoAtuacao.idAtuacao.atuacao}</span></td>
						<td><a href="excluitag?idprodutoatuacao=${produtoAtuacao.idprodutoAtuacao}&idprodutoAtuacao=${idProduto}">Excluir</a></td>
					</tr>
				  </c:forEach>
				</table>
			</div>
		</div>
		
			  
        </div>
  </div>


  <div class="divisor-fino"></div>

  <div class="row">
      <div class="col-md-7 painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;margin-top: 0px;">
    
            <div class="form-group">
              <button class="btn btn-link" id="tiraMarcas">[+] Adicionar Marcas</button> 
              
            </div>  
              <div class="col-md-7 display-none marcas">  

                 <form action="SalvarMarcaProduto" method="post">
                 	<input type="hidden" value="${idProduto}" name="idprodutoAtuacao"/>
                   
                    <select class="form-control" name="idmarca" id="">
                    	<c:forEach items="${marcas}" var="marcas">
		           		       	<option value="${marcas.idmarca}">${marcas.marca}</option>
		                </c:forEach>
                    </select>  
                  
                    <div class="form-group">
                      <div class="divisor"></div>
                      <button type="submit" class="btn btn-danger">Adicionar Marca</button>
                      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Marca">Criar Nova Marca</button>
                    </div>

                  </form>

              </div>

         <div class="col-md-7 painel" style="padding-left: 0">	
		 	<div class="col-md-12">  
				<b>Marcas Relacionadas:</b><br><br>
				<table class="table table-condensed" style="margin-top: 5px;">
  				  <c:forEach items="${produtoMarca}" var="produtoMarca">
					<tr>
						<td class="input-160px"><span style="font-style: italic;">${produtoMarca.idMarca.marca}</span><br></td>
						<td><a href="excluimarca?idmarcaProduto=${produtoMarca.idmarcaProduto}&idprodutoAtuacao=${idProduto}">Excluir</a></td>
					</tr>
				  </c:forEach>
				</table>
				
				
				
				
				<%-- <c:forEach items="${produtoMarca}" var="produtoMarca">
					   <span style="font-style: italic;">- ${produtoMarca.idMarca.marca}</span><br>
				</c:forEach> --%>
			</div>
		</div>
        </div>  
  </div>

</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<!-- Modal -->
<div class="modal fade" id="Tags" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Criar Nova Tag</h4>
      </div>
      <div class="modal-body">
          

            <label for="">Tag</label>
          	
      <form action="salvaNovaTagProduto" method="post" id="salvaNovaTag">	
          	<input type="hidden" value="${idProduto}" name="idprodutoAtuacao"/>
          
            <input type="text" class="form-control" placeholder="Nome da nova Tag" style="width:180px" name="atuacao" id="novaTag" >
            <div class="form-inline">
            </div>
         
              
  
    
      		</div>
      		<div class="modal-footer">
        	<button type="submit" class="btn btn-primary">Salvar Tag</button>
        </form>
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.reload();">Fechar</button>
        
      </div>
    </div>
  </div>
</div>

<!-- Modal edita Marca -->
<div class="modal fade" id="Marca" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Criar Nova Marca</h4>
      </div>
      <div class="modal-body">
          

            <label for="">Marca</label>
          	
      <form action="salvaMarcaProduto" method="post" id="salvaMarca">
            <input type="hidden" value="${idProduto}" name="idprodutoAtuacao"/>	
          	
            <input type="text" class="form-control" placeholder="Nome da nova Marca" style="width:180px" name="marca" id="novaMarca" >
            <div class="form-inline">
            </div>
    
      		</div>
      		<div class="modal-footer">
        	<button type="submit" class="btn btn-primary">Salvar Marca</button>
        </form>
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.reload();">Fechar</button>
        
      </div>
    </div>
  </div>
</div>


</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<c:import url="../_comum/footer.jsp" />