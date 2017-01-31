<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 


<ol class="breadcrumb">
    <li><a href="#">Menu</a></li>
    <li class="active"><a href="#">Listas de Produção Aprovadas</a></li>
    <li class="active">Itens da Lista</li>
    
</ol>

<div class="row">
		<div class="col-md-12 painel ajuste-left">
			<div class="col-md-12">

				<div class="form-inline">
				<a href="producao" class="btn btn-info">Nova Lista</a>
				<div class="divisor"></div>
				<h4 style="font-family: 'OpenSansLight';font-size: 25px">Nome da Lista: <strong>UPL Ministração de eventos</strong></h4>
				
					<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed"
						style="font-size: 12px;">
						<tbody>
							<tr class="active">
								
								<th class="col-md-4">Item</th>
								<th>Fat Locco</th>
								<th>Fat Direto</th>
								<th>Fornecedor</th>
								<th>Confirmar Item</th>
							</tr>
							<tr>
							<td colspan="5" style="background: #ccc" ><strong>Equipamentos Hotel</strong></td> 
							</tr>

						<%-- <c:forEach items="${lista}" var="lista"> --%>
							<tr >
								  <%-- <td class="alinhamentoVertical"><a href="editaLista?idLista=${lista.idLista}">${lista.lista}</a></td>
								  <td class="alinhamentoVertical">${lista.revisao}</td>
								  <td>${lista.idJob.empresa.empresa}</td>
								  <td>${lista.idJob.titulo}</td>	
								  <td>Marcelo - programador</td> --%>	
								 
							<!-- 	 <td class="alinhamentoVertical">Sonorização</td>
								 <td class="alinhamentoVertical">15.000,00</td>
								 <td class="alinhamentoVertical">18.000,00</td>
								 <td class="alinhamentoVertical"><a href="#">A Geradora</a></td>	
								 <td class="alinhamentoVertical">Confirmar</td>						 
								  --> 
								 
								 
								
							</tr>
						<%-- </c:forEach> --%>

					   </tbody>
					</table>
				</div>

			</div>
		</div>
	</div>

	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</div>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->




<c:import url="../_comum/footer.jsp" />