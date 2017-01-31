<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
	.criacao{background: #f1f1f1;}
</style>
<c:import url="../../../_comum/header.jsp" />
<div class="col-md-12 bodyXY" style="height: 35px;">
					
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li><a href="porLista">Relatório Listas</a></li>
		    <li class="active">Lista Individual</li>
		    <%-- <li class="active">${somaDoTempoTotalPendencia[0][0]}Hs${somaDoTempoTotalPendencia[0][1]}Min</li> --%>
		  </ol>					
<!-- <div style="height: 50px">
	LISTA INDIVIDUAL
</div>
 -->

</div>

<c:forEach items="${itens}" var="itens">
	<c:set value="${itens.criacaoLista.listaTitulo}" var="listaTitulo" />
	<c:set value="${itens.criacaoLista.tempoTotal}" var="tempoTotal" />
</c:forEach>



<div class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 218px;height: 600px;border-top: 1px solid #ccc;padding: 35px 60px;">
		
	<div class="col-md-10" style="margin-bottom:140px;background: #fff;box-shadow:5px 5px 5px #ccc;padding: 50px 40px;border-radius:5px;margin-left:10%;border: 1px solid #ccc">
		<h4 style="font-weight: bolder;margin-bottom: 60px;color: #f05736;">${listaTitulo}
		
		<span style="float: right;padding-right: 76px">Total Horas: 
			
			<c:if test="${tempoTotal != null}">
				${tempoTotal}
			</c:if>
			<c:if test="${tempoTotal == null}">
				<td>${totalHorasListas.teste[0]}Hs ${totalHorasListas.teste[1]}Min</td>
			</c:if>		
		
		</span></h4>	


		<table class="table table-hover">
			<tr>
				<th>ITEM</th>
				<th>PRODUTOR</th>
				<th>HORAS TRABALHADAS</th>
			</tr>		
			
			<c:forEach items="${itens}" var="itens">
				<tr>
					<td style="width: 345px!important">${itens.tituloItem}</td>
					<td style="width: 245px!important">
						<c:choose>
							<c:when test="${itens.responsavelItem != null}">
								${itens.responsavelItem.nome}
							</c:when>	
							
							<c:when test="${itens.liderCriacao == null}">
								${itens.criacaoLista.usuarioReponsável.nome}
							</c:when>
							
							<c:when test="${itens.responsavelItem == null}">
								${itens.liderCriacao.nome}
							</c:when>
						</c:choose>
					</td>
					<td style="width: 267px!important">
						
						<c:if test="${empty itens.tempoTotalItem}">
							<c:forEach items="${totalHorasItens}" var="horaItens">
								<c:if test="${horaItens.idCriacaoLista == itens.idCriacaoItemLista}">
									${horaItens.teste[0]}hs${horaItens.teste[1]}min
								</c:if>
							</c:forEach>
						</c:if>
						
						<c:if test="${not empty itens.tempoTotalItem}">
							${itens.tempoTotalItem}
						</c:if>
					
					</td>
				</tr>		
			</c:forEach>

		</table>
	</div>	
		

</div>



<c:import url="../../../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/criacaoValidaFormularios.js" />"></script>