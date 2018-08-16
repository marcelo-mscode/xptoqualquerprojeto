<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../../../_comum/header.jsp" />

<style type="text/css">
  
  .ajusteInput2{border: none;height: 35px;width: 90px;padding: 0 0 0 5px;}
  .tiraPaddingData{padding: 0px 0px 0px 5px  !important;height: 33px !important}
	
</style>


<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Analíticos</li>
		    <li class="active">Controle de Despesas</li>
		  </ol>					
</div>
<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-size: 12px;font-family: 'OpenSansLight';padding:35px 5px 70px 5px;">
   <div style="padding: 0px 0 55px 0px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
      
      <div class="row">
	      <div class="col-md-12" style="text-align: center">
		      <h4 class="">CONTROLE DE DESPESAS</h4>
		      <h5 class="">MÊS DE JULHO DE XXXX</h5>
	      </div>
	     
      </div>
	  
      <div class="row" style="margin-top: 20px;">
		  <div style="padding: 0 15px;">
	      <table class="table table-hover table-bordered">
	         <tbody>
	           <tr>
	           	<th>Data</th>
	           	<th>Descrição</th>
	           	<th>Cliente</th>
	           	<th>Marcelo $</th>
	           	<th>Pedro $</th>
	           	<th>Célia $</th>
	           	<th>Locco $</th>
	           	<th>C. Credito Locco</th>
	           	<th>Obs</th>
	           	<th>Pagto</th>
	           	<th>Salvar</th>
	           <tr>
	       
	       	  <tr>	
                <td class="tiraPaddingData"><input id="dataDespesas" type="date" class="ajusteInput2 tiraPaddingData input-140px"></td>
              
	           	<td>Dataaaaaaaaaaaaaa</td>
	           	<td>Data</td>
	           	<td>Data</td>
	           	<td>Data</td>
	           	<td>Data</td>
	           	<td>Data</td>
	           	<td>Data</td>
	           	<td>Data</td>
	           	<td>Data</td>
	           	<td><button class="btn btn-success" onclick="">Salvar</button></td>
	           </tr>

	         </tbody>
	      </table>
		  </div>  
	  </div>  
   </div>
</div>
<c:import url="../../../_comum/footer.jsp" />	