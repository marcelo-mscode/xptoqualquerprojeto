<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 


<ol class="breadcrumb col-md-offset-1">
    <li><a href="#">Menu</a></li>
    <li class="active">Marcas</li>
</ol>



      <div class="row col-md-offset-1">
          <div class="col-md-7 painel ajuste-left">
            <div class="col-md-10">

              <div class="form-inline">
              
              <button type="button" class="btn btn-info" data-toggle="modal" data-target="#Tags">Nova Marca</button>
              
                  <div class="divisor"></div>
                  <table class="table table-striped table-td-ajuste table-sem-bold" style="font-size: 12px;">

                  <c:forEach items="${marcas}" var="marcas">
                  
                  	<tr><td>
                  	<%-- <a href="#" onclick="modaltag('informacaoTag?id=${marcas.marca}');">${marcas.marca}</a> --%>
                  	${marcas.marca}
                  	</td></tr> 
                  
                  </c:forEach>
              
                </table>  
              </div>

            </div>    
          </div>
      </div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<!-- - - - - - - - - - Fim Container - - - - - - - - - -->

<!-- Modal -->
<div class="modal fade" id="Tags" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Criar Nova Marca</h4>
      </div>
      <div class="modal-body">
          

            <label for="">Marca</label>
          	
      <form action="salvaMarca" method="post" id="salvaMarca">	
          	
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



<c:import url="../_comum/footer.jsp" />