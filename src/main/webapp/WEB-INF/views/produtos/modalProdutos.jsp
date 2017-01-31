<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>


  <div class="row">
      <div class="col-md-10 painel ajuste-left">
        <div class="col-md-10">
            <form action="atualizaProduto" method="post">
                <div class="form-inline">
                    
                </div>
               <c:forEach items="${produtos}" var="produtos">
               
               <input type="checkbox" name="habilitado" checked="checked" class="checkbox">Ativo
               
               <input type="hidden" name="idproduto"  value="${produtos.idproduto }"> 
                <div class="form-group">
                <div class="divisor"></div>
                    <input type="text" name="produto" class="form-control" value="${produtos.produto}" placeholder="${produtos.produto}">
                </div>
               
                 <div class="form-group">
                  <select name="idUnid" id="" class="form-control">
                    
                    	<option value="${produtos.unidade.id}">${produtos.unidade.unidade}</option>
    
                    	<c:forEach items="${unidade}" var="unidade" >
	                    	<option value="${unidade.id}">${unidade.unidade}</option>
                    	</c:forEach>
                    	
                    	
                    	
                  </select>
                
                </div> 
				
                  <div class="form-group">
                   <select name="idGenero" id="" class="form-control">
                	     	<option value="${produtos.genero.idgenero}">${produtos.genero.genero}</option>
	                 		
	                 		<c:forEach items="${genero}" var="genero" >
	                    		<option value="${genero.idgenero}">${genero.genero}</option>
                    	    </c:forEach>   
	                   
	 
	              </select> 
	              
                </div> 
 
                <div class="form-group">
                    <input type="text" class="form-control real"
                    value="<fmt:formatNumber value="${produtos.custoPadrao}" pattern="#,##0.00"/>"
                    name="custoPadrao1" placeholder="Custo Padrão" style="width:120px;">
                </div>

                 <div class="form-group">
                  <input type="text" class="form-control real"
                  value="<fmt:formatNumber value="${produtos.precoPadrao}" pattern="#,##0.00"/>"
                  name="precoPadrao1" placeholder="Preço Padrão" style="width:120px;">
                  </div>
                
				
				
				
				
                 <div class="form-group">
                    <textarea name="produtodescricao" 
                    id="" rows="5" class="form-control" placeholder="Descrição do Produto">${produtos.produtodescricao}</textarea>
                </div>
                 
                 
				
                <div class="form-group">
                
                  <p>Última atualização: <br/>
                  	<fmt:formatDate type="both" value="${produtos.dataAtualizacao.time}" />
                  	- Usuário que atualizou
                  </p>
                </div>
          </c:forEach>       
                <button class="btn btn-danger">Salvar Produto</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>  


            </form>
        </div>
      </div> 
     </div> 
     
     
     
</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<c:import url="../_comum/footer.jsp" />     