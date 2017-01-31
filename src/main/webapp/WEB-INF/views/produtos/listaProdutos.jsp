<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 


<ol class="breadcrumb col-md-offset-1">
    <li><a href="#">Menu</a></li>
    <li>Produtos</li>
    
</ol>



<div class="row col-md-offset-1">
<div class="col-md-7 painel ajuste-left">

	<div class="col-md-12">

		<div class="form-inline">
			<a href="<c:url value="/produto.html" />" class="btn btn-info">Novo Produto</a>
			<div class="divisor"></div>
			<table class="table table-striped table-td-ajuste table-sem-bold"
						style="font-size: 12px;">
					<tr>
						<th>Nome do Produto</th>
						
						<!-- <th>Custo Padrão</th> -->
						<!-- <th>Preço Padrão</th>
						<th>Unidade</th> -->
						<!-- <th>Remover</th> -->
						
					</tr>
				<c:forEach items="${produtos}" var="produtos">
					<tr>
                        <%--<td><a href="#" onclick="modalProduto('informacaoProduto?id=${produtos.idproduto}');">${produtos.produto}</a></td>--%>
                       <td><a href="<c:url value='informacaoProduto?id=${produtos.idproduto}'/> ">${produtos.produto}</a></td>
												  
						<%-- <td><fmt:formatNumber value="${produtos.custoPadrao}" pattern="R$ #,##0.00"/></td>
						<td><fmt:formatNumber value="${produtos.precoPadrao}" pattern="R$ #,##0.00"/></td>

				   		<td>${produtos.unidade.unidade}</td> --%>
						<%-- <td><a href="removeProduto?id=${produtos.idproduto}">Excluir</a> --%>
						   
						
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</div>






<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->
<div class="modal fade" id="25" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Atualização de Produto</h4>
        <img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader" alt="loading" />
      </div>
      <div class="modal-body">
        
        <div class="modalProduto"></div>
       
      </div>
          </div>
  </div>
</div>










<c:import url="../_comum/footer.jsp" />