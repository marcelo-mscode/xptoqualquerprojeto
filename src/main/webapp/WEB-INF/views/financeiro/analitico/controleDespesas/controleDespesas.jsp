<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../../../_comum/header.jsp" />

<style type="text/css">
  
  .ajusteInput2{border: none;height: 35px;width: 90px;padding: 0 0 0 0px;}
  .tiraPaddingData{padding: 0px 0px 0px 5px  !important;height: 33px !important}
  .ajusteData{width: 140px !important;}
  .ajustevalores{width: 90px !important}
	
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
           	<th class="ajusteData">Data</th>
           	<th class="input-200px">Descrição</th>
           	<th class="input-200px">Cliente</th>
           	<th class="ajustevalores">Marcelo $</th>
           	<th class="ajustevalores">Pedro $</th>
           	<th class="ajustevalores">Célia $</th>
           	<th class="ajustevalores">Locco $</th>
           	<th class="ajustevalores">C.C Locco</th>
           	<th class="input-200px">Obs</th>
           	<th style =" width: 40px;">Salvar</th>
           <tr>
       	   <tr>	
            <td class="tiraPaddingData"><input id="dataDespesas" type="date" class="ajusteInput2 tiraPaddingData ajusteData "></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px"></td>
           	<td style="padding: 0;"><button class="btn btn-success" onclick="" style="border-radius: 0;">Salvar</button></td>
           </tr>
	      </tbody>
	     </table>
		</div>  
	  </div> 
	  
	  <div class="row" style="margin-top: 20px;">
	   <div style="padding: 0 15px;">
	    <table class="table table-hover table-bordered">
	     <tbody>
           <tr>
           	<th class="ajusteData">Data</th>
           	<th class="input-200px">Descrição</th>
           	<th class="input-200px">Cliente</th>
           	<th class="ajustevalores">Marcelo $</th>
           	<th class="ajustevalores">Pedro $</th>
           	<th class="ajustevalores">Célia $</th>
           	<th class="ajustevalores">Locco $</th>
           	<th class="ajustevalores">C.C Locco</th>
           	<th class="input-200px">Obs</th>
           	<th style =" width: 40px;">Salvar</th>
           <tr>
       	   <tr>	
            <td class="tiraPaddingData"><input id="dataDespesas" type="date" class="ajusteInput2 tiraPaddingData ajusteData "></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px"></td>
           	<td style="padding: 0;"><button class="btn btn-success" onclick="" style="border-radius: 0;">Salvar</button></td>
           </tr>
	      </tbody>
	     </table>
		</div>  
	  </div>  
	  
	  
	  
	  
	  
	  
	  
	  
	   
   </div>
</div>
<c:import url="../../../_comum/footer.jsp" />	