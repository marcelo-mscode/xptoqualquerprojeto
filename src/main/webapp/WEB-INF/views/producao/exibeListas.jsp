<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />


		<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">
					
				<ol class="breadcrumb">
			    <li><a href="#">Menu</a></li>
			    <li class="active">Lista de Produção</li>
			    
			</ol>
					
		</div>
    </div>
</div>
<br /><br />
<div class="row painel" style="padding-top: 11px;margin-top: -15px;border-top: 1px solid #ddd;">
		  <div class="col-md-12 ajuste-left" style="padding-top: 3px;margin-left: 23px;">
		  
		  	 <%-- <div class="col-md-12 painel display-none" style="padding-left: 0;margin-top: 20px;" id="consultaAz">
<a onclick="efeitoToogleFast(consultaAz)" class="ajuste-left" style="cursor: pointer;">Consulta Geral de a-z</a>
				   <div class="col-md-8" style="margin-left: -15px;">
				    <div class="input-group">
					    <span class="input-group-addon" id="basic-addon1">Titulo</span>
					    <button  onclick="consultaListasAjax(1);" class="btn btn-default botaoConsulta">A-z</button>
						<button  onclick="consultaListasAjax(2);" class="btn btn-default botaoConsulta">Z-a</button>
					
					    <span class="input-group-addon" id="basic-addon2">Cliente</span>
					    <button onclick="consultaListasAjax(3);" class="btn btn-default botaoConsulta">A-z</button>
						<button onclick="consultaListasAjax(4);" class="btn btn-default botaoConsulta">Z-a</button>
						
						<span class="input-group-addon" id="basic-addon3">Job</span>
					  	<button  onclick="consultaListasAjax(5);" class="btn btn-default botaoConsulta">A-z</button>
						<button  onclick="consultaListasAjax(6);" class="btn btn-default botaoConsulta">Z-a</button>
						
						<span class="input-group-addon" id="basic-addon4">Código Lista</span>
					  	<button  onclick="consultaListasAjax(7);" class="btn btn-default botaoConsulta">A-z</button>
						<button  onclick="consultaListasAjax(8);" class="btn btn-default botaoConsulta">Z-a</button>
			  				
		  				
		  				
		  			 </div>
	  			 </div>
	  			 <div class="col-md-3" style="border: 0px solid gray">
	  			 <select class="form-control col-md-1" id="listaStatusAjax" onchange="consultaListasAjaxStatus(this.value)">
							<option value="selecione">Status</option>
							<c:forEach items="${status}" var="status">
								<option value="${status.idlistaEstatus}">${status.listaEstatus}</option>
							</c:forEach>
						</select>
	  			 </div>
  			 </div> --%>
<div class="divisor"></div>  			 
<span>Filtro Individual:&nbsp&nbsp</span>

<select class="form-control input-260px" id="baseData">
	<option value="base">${anoBase} - Data Atual</option>
	<option value="anteriores">Anteriores a ${anoBase}</option>
</select>

			 
<div class="col-md-12 painel " style="padding-left: 0;padding-left: 0;
    border-top: 0px solid #ddd;margin-top: 15px">
			   
				<div class="col-md-3" style="margin-left: -15px;">
					
					<select class="form-control input-260px" id="listaEmpresaUnicaAjax" onchange="consultaAvancadaListasAjaxEmpresa(this.value)">
						<option value="selecione">Cliente</option>
						<c:forEach items="${empresas}" var="empresas">
							<option value="${empresas[0]}">${empresas[1]}</option>
						</c:forEach>
					</select>
				</div>	
	  		
	  			<div class="col-md-5" style="border: 0px solid gray">	
					<select id="listaStatusUnicoAjax" class="form-control col-md-1" onchange="consultaAvancadaListasAjaxlista(this.value)">
						<option value="selecione">Selecione a Lista</option>
					</select>
				</div>
				
				<div class="col-md-3" style="border: 0px solid gray">	
					<select id="listaStatusUnicoAjax" class="form-control col-md-1" onchange="consultaAvancadaListasStatusAjaxlista(this.value)">
						<option value="selecione">Status Por Empresa</option>
						<c:forEach items="${status}" var="status">
							<option value="${status.idlistaEstatus}">${status.listaEstatus}</option>
						</c:forEach>
					</select>
				</div>
				  	
	  			 </div>
  			 </div>
</div>



<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 




<!-- <div class="row">
		<div class="col-md-12 painel ajuste-left">
			<div class="col-md-12">

				<div class="form-inline">
				<img class="col-md-offset-5" src='resources/images/loader-lista.gif' width='30' height='30' alt='loading' id='loader-lista' style='margin-right: -2px;display: none'>			
				
				<div class="divisor"></div>
	            </div>
	 	    </div>	   
	   </div>	   
</div> -->	   
</div>	   
	
<div class="estilo-painel-job" style="background:#fff;padding: 40px;border-top: 1px solid #ddd;padding-top: 30px;margin-top: 0">				
				<a href="producao" class="btn btn-danger" style="margin: 10px 0">Nova Lista</a>
				<img class="col-md-offset-5" src='resources/images/loader-lista.gif' width='30' height='30' alt='loading' id='loader-lista' style='margin-right: -2px;display: none'>			
				
					<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed"
						style="font-size: 12px;">
						<tbody id="listasAjax">
							<tr class="active">
								
								<th style="width: 120px;">Cod Lista</th>
								<th>R</th>
								<th class="col-md-4">Lista</th>
								<th>Cliente</th>
								<th>Job</th>
								<th style="width: 85px;">Status</th>
							</tr>

							<c:forEach items="${lista2}" var="lista2">
								<tr>
									<td>${lista2.listaCod}</td>
									<td class="alinhamentoVertical">${lista2.revisao}</td>

									<td class="alinhamentoVertical">
										<a href="editaLista?idLista=${lista2.idLista}">${lista2.lista}
											<c:if test="${lista2.numCenarioGalderma != null}"> - Cenário ${lista2.numCenarioGalderma}</c:if>
										 </a>
									</td>
									
									<td>${lista2.empresa}</td>
									<td>${lista2.job}</td>
									<td>${lista2.status}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					
					</div>

	<br><br><br><br><br><br><br><br><br><br><br><br><br>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->

<br><br><br><br><br><br><br><br><br><br><br><br><br>


<c:import url="../_comum/footer.jsp" />