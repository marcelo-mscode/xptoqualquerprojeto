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

<div style="width: 100%;border-bottom: 1px solid #ddd;padding: 12px 28px;">
		
		
		<table class="table table-striped table-bordered">
		  <tbody>
			<tr>
				<th>Empresa</th>
				<th>Nome Contato</th>
				<th>Contato</th>
			</tr>
			
				<c:forEach items="${listaContatos}" var="listaContatos" varStatus="loop">
					<tr>
						<td>${listaContatos.empresa.empresa}</td>
						<td>${listaContatos.contato}</td>
						<td>
							<table>
								<c:forEach items="${listaContatos.comunicador}" var="comunicador">
									<tr>
											<td>${comunicador.comunicador}</td>
									</tr>
								</c:forEach>
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