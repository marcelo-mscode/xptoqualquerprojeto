<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../../_comum/header.jsp" />
<style type="text/css">
.financeiro{background: #f1f1f1;}
.cabecalhoLista {text-align: center !important;line-height: 33px !important;}
.cabecalhoLista > td {line-height: 33px !important;}
.descricao {text-align: left;}
.temContratacao{border-left: 1px solid red}
.bordaGrossa{border: 2px solid #000 !important}


</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Lista Resumos Mês</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">Lista de Resumos do Mês</span>
	</div>
</div>


<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 5px 70px 5px;">
  <div style="padding: 35px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
	 	<div class="col-md-5" style="padding-left: 0;">
		  <table class="table table-hover table-bordered bordaDestaque" >
		   <tbody id="prospeccaoFiltro">

			<c:forEach items="${anos}" var="anos">
				<tr>
				  <td class="input-140px" style="font-size: 25px; font-weight: bold">${anos}</td>
				</tr>
				<c:forEach items="${analitico}" var="analitico">
				  <c:if test="${anos == analitico.get('analitico.ano')}">
					 <tr>
						 <td>
							<a href="resumoMesIndividual?mes=${analitico.get('analitico.mesReferencia')}&ano=${analitico.get('analitico.ano')}">${analitico.get("analitico.mes")}</a>
						 </td> 	
					 </tr>
				  </c:if>	
				</c:forEach>	 
			</c:forEach>	 
			
			</tbody>
		  </table>	
		</div>
	</div>
</div>		


<c:import url="../../../_comum/footer.jsp" />