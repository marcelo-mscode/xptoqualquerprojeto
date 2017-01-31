<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../../../_comum/header.jsp" />

<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Listas Analíticos</li>
		  </ol>					
</div>
<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-size: 12px;font-family: 'OpenSansLight';padding:35px 5px 70px 5px;">
   <div style="padding: 15px 0 55px 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
      
      <div class="row">
	      <div class="col-md-3" style="margin-left: 20px;">
		      <h2 class="">Listas Analíticos</h2>
	      </div>
	      
	      <div class="col-md-1">
	      	<a href="novoAnalitico" class="btn btn-default" style="margin-top: 20px;">Novo Analítco</a>
		  </div>    
      </div>
	  
      <div class="row" style="margin-top: 20px;margin-left: 20px;">
		  <div  style="width: 80%;">
	      <table class="table table-hover">
	         <tbody>
	           
	           <c:forEach items="${analiticoAno}" var="analiticoAno">
	           		<tr>
	           			<td colspan="10"><h5>${analiticoAno}</h5></td>
	           		</tr>
		           <c:forEach items="${analitico}" var="analitico">
		           	  <c:if test="${analiticoAno ==  analitico.anoA}">
			            <tr>
			            	<td  style="padding-left: 40px"><a href="analiticoIndividual?idAnalitico=${analitico.idAnalitico}">${analitico.mesA}</a></td>
			            	<td colspan="10"></td>
			            </tr>
		           	  </c:if>	
		            </c:forEach> 
	            </c:forEach> 
	        
	         </tbody>
	      </table>
		  </div>  
	  </div>  
   </div>
</div>
<c:import url="../../../_comum/footer.jsp" />				
