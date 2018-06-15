<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />
<c:set var="email" value="@"/>
<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li><a href="listaempresa?first=0&amp;max=350">Empresas</a></li>
			<li class="active">Lista de Empresas</li>
		</ol>
	</div>
</div>


<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 27px;background: #fff">
	<h4 style="font-family: 'OpenSansLight';display: inline;line-height: 36px;">RELATÓRIO DE EMPRESAS</h4>
	<a onclick="exportaExcelEmpresas();" style="margin-left: 520px;" class="btn btn-default" id="planilhaExcelEmpresas">
	<img alt="" src="resources/images/excel.png" width="18" height="18" style="margin-right: 5px">
	Gerar Relatório Excel</a>
</div>

<div class="row" style="margin-top: 0px;background-color: #fff;border-bottom: 1px solid #ddd">
	<div style="padding: 5px 9px 5px 32px;">
		<input value="1" name="mcheckbox[]" type="checkbox" id="clienteBox" onclick="buscaListagemEmpresasConsultas();" >&nbsp&nbspCliente
		<input value="2" name="mcheckbox[]" type="checkbox" style="margin-left: 25px;" id="prospectBox" onclick="buscaListagemEmpresasConsultas();" >&nbsp&nbspProspect
		<input value="4" name="mcheckbox[]" type="checkbox" style="margin-left: 25px;" id="fornecedorBox" onclick="buscaListagemEmpresasConsultas();" >&nbsp&nbsp&nbsp&nbspFornecedor
	</div>	
</div>

<div style="width: 100%;border-bottom: 1px solid #ddd;padding: 12px 28px;" id="listagemEmpresasConsulta">

		<table class="table table-striped table-bordered">
		  <tbody>
			<tr>
				<th class="input-180px">Empresa</th>
				<th class="input-180px">Telefone Principal</th>
				<th>Contatos</th>
			</tr>
				<c:forEach items="${empresa}" var="empresa">
				<tr>
					<td style="border-right: 1px solid #ddd">${empresa[1]}</td>
					<td style="border-right: 1px solid #ddd">${empresa[2]}</td>
					<td style="padding: 0px !important">
						<table class="table" style="background-color: #f8f8f8;margin: 0">
							<tr>
								<c:forEach items="${contato}" var="contato">
									<c:if test="${empresa[0] == contato[2]}">
									 <tr>
										  <td class="input-220px" style="border-bottom: 0px solid #ddd;vertical-align: middle;">
										  	${contato[1]}
										  </td>
										  <td  style="border-bottom: 0px solid #ddd;">
										  	<table>
												  <c:forEach items="${comunicador}" var="comunicador">
													<c:if test="${contato[0] == comunicador[1]}">
														<tr>
														 <td>
															${comunicador[0]}														
														 </td>	
													    </tr>
													</c:if>
												   </c:forEach>
											 </table>	  
										  </td>
									 </tr> 
									</c:if>
								</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
		  </tbody>
		</table>
</div>

<div class="col-md-12 alpha60 div-confirmacao" id="geraExcel" style="position: fixed;"></div>		


<c:import url="../../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/empresaScript.js" />"></script>