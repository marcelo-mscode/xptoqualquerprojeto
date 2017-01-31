<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- - - - - - - - - - Container - - - - - - - - - - - -->

<div class="container"> 

  <div class="row">
      <div class="col-md-6 painel" style="left: 25%;padding: 0 30px;margin-top: 30px;">

			<div style="padding: 15px 0;border-bottom: 1px solid #e5e5e5">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
					<span aria-hidden="true" style="font-size: 50px; font-family: 'OpenSansLight'; font-weight: normal;" onclick="location.reload();">×</span>
				</button>
				<h4 class="modal-title fontLight" id="myModalLabel">CADASTRO DE NOVO PRODUTO</h4>
			</div>

			<div class="col-md-12" style="padding: 25px 0 40px 0;">
            <form action="salvaProdutoLista" method="post" id="salvandoProduto">
            	<input type="hidden" value="${idGrupo}" name="idGrupo">
            
                <div class="form-inline">
                    <input type="checkbox" name="habilitado" checked="checked" class="checkbox">&nbsp;&nbsp;Ativo
                </div>
                
                <div class="form-group">
                <div class="divisor"></div>
                	* Nome do Produto:
                    <input type="text" name="produto" class="form-control novoPto" placeholder="Produto" id="nomeProduto" onblur="verificaProduto();">
                    <span style="display: none;color: red" id="emBranco">*Não deixa esse campo em branco<i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                    <span style="display: none;color: red" id="jaExiste">*Já existe um produto com esse nome cadastrado <i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                    <span style="display: none;color:green" id="naoExiste">*Produto disponível <i class="glyphicon glyphicon-ok" style="top: 0px;left: 5px;color: green;"></i></span>
                    
                    
                    
                </div>
                
                <div class="form-group">
                 * Unidade:		
                  <select name="idUnid" id="unidadeProduto" class="form-control">
                    
                    <option value="SelecioneUnidade">Selecione a Unidade do produto</option>
                    <c:forEach items="${unidades}" var="unidades">
                    	
                    	<option value="${unidades.id}">${unidades.unidade}</option>
                    
                    </c:forEach>
                   
                  </select>
                </div> 

                 <div class="form-group">
                * Gênero:
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
                  Tag:
                  <select class="form-control input-160px" name="idAtuacaoEmLista" id="">
                  				<option value="selecione">Selecione</option>
                           <c:forEach items="${tags}" var="tags">
		           		       	<option value="${tags.idAtuacao}">${tags.atuacao}</option>
		                  </c:forEach>
                    </select> 
                </div>
                <div class="divisor"></div>   
                <button class="btn btn-danger is-disabled" id="cadastraNovoProduto">Salvar Produto</button>  
                <a class="btn btn-info" onclick="location.reload();">Cancelar</a>  
            </form>
        </div>
      </div>  

  </div>

 <div class="divisor-fino"></div>

</div>

</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<c:import url="../_comum/footer.jsp" />

