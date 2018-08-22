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
       	   
       	   <c:set value="0.00" var="marcelo" />
       	   <c:set value="0.00" var="pedro" />
       	   <c:set value="0.00" var="celia" />
       	   <c:set value="0.00" var="locco" />
       	   <c:set value="0.00" var="CClocco" />
       	   <c:set value="0.00" var="total" />
       	   
       	   <c:forEach items="${lista}" var="lista">
       	   <tr>	
            <td class="tiraPaddingData">
            <input id="dataDespesas${lista.idControleDespesas}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${lista.data}" pattern="dd/MM/yyyy"/>" type="text"
                onclick="mudaCampoData('dataDespesas${lista.idControleDespesas}');"
                onblur=""/>
            
            </td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px" value="${lista.descricao}"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px" value="${lista.cliente}"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${lista.marceloValor}" pattern="#,##0.00"/>"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${lista.pedroValor}" pattern="#,##0.00"/>"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${lista.celiaValor}" pattern="#,##0.00"/>"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${lista.loccoValor}" pattern="#,##0.00"/>"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${lista.ccLoccoValor}" pattern="#,##0.00"/>"></td>
           	<td class="tiraPaddingData"><input id="dataDespesas" type="text" class="ajusteInput2 tiraPaddingData input-200px" value="${lista.obs}"></td>
           	<td style="padding: 0;"><button class="btn btn-success" onclick="" style="border-radius: 0;">Salvar</button></td>
           </tr>
           
	           <c:set value="${marcelo + lista.marceloValor}" var="marcelo" />
	           <c:set value="${pedro + lista.pedroValor}" var="pedro" />
	           <c:set value="${celia + lista.celiaValor}" var="celia" />
	           <c:set value="${locco + lista.loccoValor}" var="locco" />
	           <c:set value="${CClocco + lista.ccLoccoValor}" var="CClocco" />
           </c:forEach>
		    <c:set value="${marcelo + pedro + celia + locco + CClocco} " var="total" />	
		   <tr><td></td></tr>
		   <tr>
		   	<td colspan="3"><b>SubTotal 1</b></td>
		   	<td><fmt:formatNumber value="${marcelo}" pattern="#,##0.00"/></td>
		   	<td></td>
		   	<td></td>
		   	<td></td>
		   	<td></td>
		   	<td><fmt:formatNumber value="${total}" pattern="#,##0.00"/></td>
		    <td></td>
		   
		   </tr>
				
			




	      </tbody>
	     </table>
		</div>  
	  </div>  
	  
	  
	  
	  
	  
	  
	  
	  
	   
   </div>
</div>
<c:import url="../../../_comum/footer.jsp" />	