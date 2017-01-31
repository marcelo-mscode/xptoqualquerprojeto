<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li><a href="listaProdutos">Lista de Produtos</a></li>
    		<li class="active">Novo Produto</li>
		</ol>
	</div>
</div>
<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 26px;background: #fff">
	<h4 style="font-family: 'OpenSansLight'">CADASTRAR NOVO PRODUTO</h4>
</div>

<div class="container"> 

  <div class="row">
      <div class="col-md-7 painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;">
        <div class="col-md-10">
            <form action="salvaProduto" method="post" id="salvandoProduto">
                <div class="form-inline">
                    <input type="checkbox" name="habilitado" checked="checked" class="checkbox">&nbsp;&nbsp;Ativo
                </div>
                
                <div class="form-group">
                <div class="divisor"></div>
                	Nome do Produto:
                    <input type="text" name="produto" class="form-control" placeholder="Produto" id="nomeProduto" onblur="verificaProduto();">
                    <span style="display: none;color: red" id="emBranco">*Não deixa esse campo em branco<i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                    <span style="display: none;color: red" id="jaExiste">*Já existe um produto com esse nome cadastrado <i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                    <span style="display: none;color:green" id="naoExiste">*Produto disponível <i class="glyphicon glyphicon-ok" style="top: 0px;left: 5px;color: green;"></i></span>
                </div>
                
                <div class="form-group">
                  Unidade:		
                  <select name="idUnid" id="unidadeProduto" class="form-control">
                    
                    <option value="SelecioneUnidade">Selecione a Unidade do produto</option>
                    <c:forEach items="${unidades}" var="unidades">
                    	
                    	<option value="${unidades.id}">${unidades.unidade}</option>
                    
                    </c:forEach>
                   
                  </select>
                </div> 

                 <div class="form-group">
                 Gênero:
                   <select name="idGenero" id="generoProduto" class="form-control">
                	 
                	 <option value="selecioneGenero">Selecione o gênero do Produto</option>
	 
	                    <c:forEach items="${genero}" var="genero">
	                    	
	                    	<option value="${genero.idgenero}">${genero.genero}</option>
	                    
	                    </c:forEach> 
	 
	              </select> 
                </div> 

                <div class="form-group">
                Custo Padrão:
                    <input type="text" class="form-control real"  name="custoPadrao1" placeholder="Custo Padrão" value="0" style="width:120px;">
                </div>

                <div class="form-group">
                  Preço Padrão:
                  <input type="text" class="form-control real" name="precoPadrao1" placeholder="Preço Padrão" value="0" style="width:120px;">
                  </div>
                
				
                <div class="form-group">
                	Descrição do Produto:
                    <textarea name="produtodescricao" id="" rows="5" class="form-control" placeholder="Descrição ..."></textarea>
                </div>
                <div class="form-group">
                
                  <!-- <p>Última atualização: &nbsp&nbsp&nbsp<span>28/04/2015 15:42 - Marcelo Souza</span></p> -->
                </div>
                  
                <button class="btn btn-danger is-disabled" id="cadastraNovoProduto">Salvar Produto</button>  


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

</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/produtos.js" />"></script>