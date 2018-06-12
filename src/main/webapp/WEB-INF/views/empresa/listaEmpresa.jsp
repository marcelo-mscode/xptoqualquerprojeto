<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<style>

.pace{display: none }	

</style>


<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li class="active">Lista de Empresas</li>
		</ol>
	</div>
</div>


<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 27px;background: #fff">
	<h4 style="font-family: 'OpenSansLight';display: inline;line-height: 36px;">LISTA DE EMPRESAS</h4>
	<a href="excelEmpresas" style="margin-left: 520px;" class="btn btn-default" id="planilhaExcelEmpresas">Relatório</a>
</div>

<div style="width: 100%;height: 80px;border-bottom: 1px solid #ddd;padding: 5px 28px;">
	
	<div class="row">
		<div class="col-md-2" style="margin-bottom: 5px;padding-right: 0px;">
		    <input type="text" id="target" class="form-control" placeholder="Pesquisar por nome...">
	   </div>
	   <div class="col-md-1" style="padding-left: 5px;">
		  	<button class="btn btn-default" onclick="buscaClientesNova();">
				Buscar <span class="glyphicon glyphicon-search" style="top: 4px;margin-left: 0px;font-size: 16px;"></span>
			</button> 
	   </div>
	</div>		

	<div class="row" style="margin-left: 4px;padding-left: 5px;">
		<input value="1" name="mcheckbox[]" type="checkbox" id="clienteBox" onclick="buscaClientes();" >&nbsp&nbspCliente
		<input value="2" name="mcheckbox[]" type="checkbox" style="margin-left: 25px;" id="prospectBox" onclick="buscaClientes();" >&nbsp&nbspProspect
		<input value="4" name="mcheckbox[]" type="checkbox" style="margin-left: 25px;" id="fornecedorBox" onclick="buscaClientes();" >&nbsp&nbsp&nbsp&nbspFornecedor
	</div>	
 </div>




<div class="container" style="margin-left: 27px;"> 

<div class="row" style="margin-top: 10px;">
	
		<div class="col-md-8 painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;">
			<div class="col-md-12">

				<div class="form-inline">
				<a href="empresa" class="btn btn-danger" style="margin-top: 15px;">Nova Empresa</a>
	    <img class="col-md-offset-5" src="resources/images/loader-lista.gif" width="30" height="30" alt="loading" id="loader-lista" style="margin-right: -2px;display: none">
				<div class="divisor"></div>
				
					<table class="table table-striped table-td-ajuste table-sem-bold"
						style="font-size: 12px;">
						<tbody id="clientesAjax">
							<tr>
								<th>Nome da empresa</th>
								<!-- <th>CNPJ da empresa</th> -->
								<th>Categoria</th>
								<th>Status</th>
							</tr>
						<c:forEach items="${empresas}" var="empresas" varStatus="conta">
							<tr >
								<td><a href="infoempresa?id=${empresas.idEmpresa}">${empresas.empresa}</a></td>
									
								<c:choose>	
										<c:when test="${empresas.cliente eq true and empresas.fornecedor eq true and empresas.prospect eq true}">
											<td>Cliente/Fornecedor/Prospect</td>
										</c:when>
										
										<c:when test="${empresas.cliente eq true and empresas.fornecedor eq true and empresas.prospect eq false}">
											<td>Cliente/Fornecedor</td>
										</c:when>
										
										<c:when test="${empresas.cliente eq true and empresas.fornecedor eq false and empresas.prospect eq true}">
											<td>Cliente/Prospect</td>
										</c:when>
										
										<c:when test="${empresas.cliente eq true and empresas.fornecedor eq false and empresas.prospect eq false}">
											<td>Cliente</td>
										</c:when>
										
										
										<c:when test="${empresas.cliente eq false and empresas.fornecedor eq true and empresas.prospect eq true}">
											<td>Fornecedor/Prospect</td>
										</c:when>

										<c:when test="${empresas.cliente eq true and empresas.fornecedor eq true and empresas.prospect eq false}">
											<td>Fornecedor/Cliente</td>
										</c:when>
										
										<c:when test="${empresas.cliente eq false and empresas.fornecedor eq true and empresas.prospect eq false}">
											<td>Fornecedor</td>
										</c:when>


										<c:when test="${empresas.cliente eq false and empresas.fornecedor eq false and empresas.prospect eq true}">
											<td>Prospect</td>
										</c:when>
										
								</c:choose>
								
								<c:if test="${empresas.habilitado}">
									<td>Ativa</td>
								</c:if>

								<c:if test="${!empresas.habilitado}">
									<td class="disab">Desativada</td>
								</c:if>

							</tr>
						</c:forEach>

					   </tbody>
					</table>
				</div>

			</div>
		</div>
	
</div>
<div class="col-md-8 painel estilo-painel-job" style="margin-left: -13px;width: 766px;" id="nextConsulta">
  <div class="col-md-12">
  
  <c:if test="${min == 0}">
  	<c:set  value="0" var="minimoPrev" />
  </c:if>

  <c:if test="${min > 0}">
  	<c:set  value="${min - 350}" var="minimoPrev" />
  </c:if>


  <c:if test="${min == 0}">
	  <c:set  value="350" var="minimoNext" />
  </c:if>		

  <c:if test="${min > 0}">
	  <c:set  value="${min + 350}" var="minimoNext" />
 </c:if> 
  
	<ul class="pager">
	  <li class="previous"><a href="listaempresa?first=${minimoPrev}&max=350" style="border-radius: 0;border-radius: 0;padding-bottom: 8px;">
	  <i class="glyphicon glyphicon glyphicon-chevron-left" style="font-size: 19px;"></i> Anterior</a></li>
	 
	  <li class="next"><a href="listaempresa?first=${minimoNext}&max=350" style="border-radius: 0;border-radius: 0;padding-bottom: 8px;">Próximo
	  <i class="glyphicon glyphicon-chevron-right" style="font-size: 19px;"></i></a></li>

	</ul>
  </div>
</div>


	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</div>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->



<div class="col-md-12 alpha60 div-confirmacao" id="excluiEmpresa">
	<div class="col-md-4"></div>


	<div class="col-md-4 sub-div-confirmacao" style="height: 250px;">
		<div class="col-md-12">
			<i class="glyphicon glyphicon-question-sign"
			style="font-size: 43px;color: #e74c3c;border: 1px solid #e74c3c;border-radius: 37px;padding: 5px;"></i>
			<h4>Quer realmente Excluir essa Empresa ?</h4>
			<h6>Essa ação não poderá ser desfeita!</h6>
			<h6>Somente exclua uma empresa se houver registro duplicado, caso contrário apenas desative.</h6>
		</div>
		
		<div class="col-md-12 div-botao-confirmacao">
		    <input type="hidden" id="idEmpresaValor" />
			<a onclick="deletaEmpresa();" class="btn btn-danger botao-confirmacao" style="font-size: 18px;">Excluir</a>
			<a onclick="location.reload();" class="btn btn-default botao-confirmacao" style="font-size: 18px;">Cancelar</a>
		</div>
		
	</div>
	
	<div class="col-md-4"></div>
</div>




<div class="modal fade" id="25" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Atualização de Tag</h4>
      <img src="<c:url value="resources/images/loader.GIF" />" width="80" height="80" class="loader" alt="loading" />
      </div>
      <div class="modal-body">
      
      		<div class="modalTags"></div>
      	
    </div>
  </div>
</div>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/empresaScript.js" />"></script>
<script type="text/javascript" >

paceOptions = {
		  ajax: false, // disabled
		  document: false, // disabled
		  eventLag: false, // disabled
		};
</script>


