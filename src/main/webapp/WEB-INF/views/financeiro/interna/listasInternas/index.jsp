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
	
	<div class="col-md-12" style="margin: 25px 0px 25px -14px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">PLANILHAS INTERNAS</span>
	</div>
	
<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;">
 <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;">
	<table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed" style="">
	  <tbody id="prospeccaoFiltro">
			<tr>
				<td colspan="6"
					style="text-align: right; padding-right: 68px !important">
					<h3 style="margin: 0">${ano}</h3>
				</td>
			</tr>

			<tr style="background: #f1f1f1 !important">
			<th style="padding-left: 20px !important">Evento</th>
			<th>Empresa</th>
			<th>Data Evento</th>
		</tr>
		
		<c:forEach items="${data}" var="data">
                    <fmt:formatDate value="${data.time}" pattern="yyyy" var="anoAtual" />
                    <c:if test="${ano == anoAtual}">
                       <tr>
                          <td colspan="6">
                             <fmt:formatDate value="${data.time}" pattern="MM" var="mesEvento"/>
                             <c:forEach items="${mesesReferencia}" var="mesReferencia" varStatus="count">
                                <c:if test="${count.count == mesEvento}">
                                   <b>${mesReferencia}</b>						  				
                                </c:if>
                             </c:forEach>
                          </td>
                       </tr>
                       <fmt:formatDate value="${data.time}" pattern="yyyy/MM" var="mesAno" />
                       <c:forEach items="${listasInternas}" var="listasInternas">
                          <fmt:formatDate value="${listasInternas.dataDoEvento.time}" pattern="yyyy/MM" var="mesAnoEvento" />
                          <tr>
                             <c:if test="${mesAno == mesAnoEvento}">
                                <td style="padding-left: 20px !important"><a href="internaIndividual?idLista=${listasInternas.idLista}">${listasInternas.lista}</a></td>
								<td>${listasInternas.idJob.empresa.empresa}</td>
								<td><fmt:formatDate value="${listasInternas.dataDoEvento.time}" pattern="dd/MM/yyyy"/></td>
                             </c:if>
                          </tr>
                       </c:forEach>
                    </c:if>
          </c:forEach>
		
		
		
		
		
		
		
		
		
		
		
		
		

	  </tbody>
	</table>
  </div>		
</div>
	
	
	
	
	
	
	
</div>	
   
   



<c:import url="../../../_comum/footer.jsp" />
   
   