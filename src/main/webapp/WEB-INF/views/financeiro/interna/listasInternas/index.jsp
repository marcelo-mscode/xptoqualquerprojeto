<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../../_comum/header.jsp" />
<style type="text/css">
.financeiro{background: #f1f1f1;}
/* .inputCheckbox{width: 15px;height: 15px;vertical-align: sub;margin-bottom: 10px !important;}
.boxFiltro{background: #fff;padding: 15px;box-shadow: 0px 1px 14px 3px #ccc;margin-right: 12px;height: 205px;} */
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Planilhas Internas</li>
		  </ol>					
</div>

<div style="height: 350px;">
	
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">PLANILHAS INTERNAS</span>
	</div>
	
<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;">
	<table class="table table-striped table-hover table-condensed" style="">
	  <tbody id="prospeccaoFiltro">
		<tr style="background: #f1f1f1 !important">
			<th></th>
			<th>Evento</th>
			<th>Empresa</th>
			<th>Data Evento</th>
			<th>Itens fechados</th>
		</tr>
		
		<c:forEach items="${listasInternas}" var="listasInternas" varStatus="loop">
			<tr>
				<td>${loop.count}</td>
			    <td><a href="internaIndividual?idLista=${listasInternas.idLista}">${listasInternas.lista}</a></td>
				<td>${listasInternas.idJob.empresa.empresa}</td>
				<td>16/06/2015</td>
				<td>
					${fn:length(listasInternas.producaoP)}
				</td>
			</tr>		
		</c:forEach>
			
		
		
		<%-- <c:forEach items="${prospeccoes}" var="prospeccoes">
			<tr>
			    <td><a href="edicaoProspeccao?idProspeccao=${prospeccoes.idProspeccao}">${prospeccoes.titulo}</a></td>
				<td>
					<c:forTokens items="${prospeccoes.idEmpresa.empresa}" delims=" " var="word" varStatus="status">  
	                   <c:if test="${status.count < 3}">  
	                     <c:out value="${word}" escapeXml="false"/>  
	                   </c:if>  
	                </c:forTokens>
				</td>
			
				<c:forEach items="${prospeccoes.interacao}" var="interacao" varStatus="loop">
				  <c:if test="${loop.last}">	
					<td>
						<c:forTokens items="${interacao.interacao}" delims=" " var="word" varStatus="status">  
		                   <c:if test="${status.count < 6}">  
		                     <c:out value="${word}" escapeXml="false"/>  
		                   </c:if>
	                    </c:forTokens>	
					</td>
					<td><fmt:formatDate value="${interacao.dataInteracao.time}" pattern="dd/MM/yyyy hh:mm"/></td>
					<td>
						<c:if test="${interacao.dataProximaInteracao  != '0001-01-01 00:00:00.0'}">
							<fmt:formatDate value="${interacao.dataProximaInteracao}" pattern="dd/MM/yyyy HH:mm"/>
						</c:if>
						<c:if test="${interacao.dataProximaInteracao  == '0001-01-01 00:00:00.0'}"></c:if>
					</td>
				  </c:if>
				</c:forEach>
			</tr>
		</c:forEach>	 --%>
	  </tbody>
	</table>
  </div>		
</div>
	
	
	
	
	
	
	
</div>	
   
   



<c:import url="../../../_comum/footer.jsp" />
   
   