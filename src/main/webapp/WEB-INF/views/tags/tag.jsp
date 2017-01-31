<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 


<ol class="breadcrumb col-md-offset-1">
    <li><a href="#">Menu</a></li>
    <li class="active">Tags</li>
</ol>



      <div class="row col-md-offset-1">
          <div class="col-md-7 painel ajuste-left">
            <div class="col-md-10">

              <div class="form-inline">
              
              <a href="#" onclick="modalNovatag('CarregaNovaTag');" class="btn btn-info" data-toggle="modal" data-target="#Tags">Nova Tag</a>
              <div class="divisor"></div>
              
              
                  <table class="table table-striped table-td-ajuste table-sem-bold" style="font-size: 12px;">
				  <tr>
				  <th>Tag</th>
				  </tr>		
                  <c:forEach items="${tags}" var="tags">
                  
                  	<tr><td><a href="#" onclick="modaltag('informacaoTag?id=${tags.idAtuacao}');">${tags.atuacao}</a></td></tr> 
                  
                  </c:forEach>
              
                </table>  
              </div>

            </div>    
          </div>
      </div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<!-- - - - - - - - - - Fim Container - - - - - - - - - -->

<!-- Modal Edita Tag -->
<div class="modal fade" id="25" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" align="center">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
            <span aria-hidden="true" style="font-size: 50px;font-family: 'OpenSansLight';font-weight: normal;">&times;</span>	
        </button>
        <h4 class="modal-title" id="myModalLabel" style="font-family: 'OpenSansLight'">EDITAR TAG</h4>
      <img src="<c:url value="resources/images/loader.GIF" />" width="40" height="40" class="loader" alt="loading" />
      </div>
      <div class="modal-body">
      
      	<div class="modalTags"></div>
      	
    </div>
  </div>
</div>

<!-- Modal Nova Tag -->
<%-- <div class="modal fade" id="Tags" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
</div> --%>


<c:import url="../_comum/footer.jsp" />