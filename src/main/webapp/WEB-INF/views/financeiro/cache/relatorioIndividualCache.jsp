<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<style>


.corFundo{background-color: rgb(230,255,230)}

.centerAling tr>td:nth-child(2) {text-align: center;}


</style>

<c:import url="../../_comum/header.jsp" />



<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li><a href="relatorioCache">Relatórios Caches</a></li>
		    <li class="active">Cachê Equipe</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 20px -14px;text-align: center;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;font-weight: bold;">
			CONTAS CORRENTES - EQUIPE - CACHES MAIO 2016
		</span>
	</div>
</div>



<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 5px 70px 5px;">
  <div style="padding: 55px 0 0px 20px;;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
	 	
	 	<div class="col-md-5" style="padding-left: 0;">
		 <table class="table table-hover table-bordered bordaDestaque" >
		   <tbody class="centerAling">
				<tr>
					<td class="input-80px"></td>
					<td class="input-60px"><b>EVENTO 1</b></td>
				</tr>
				<tr>
					<td>Cliente</td>
					<td class="input-60px">Bayer S/A</td>
				</tr>
				<tr>
					<td>Evento</td>
					<td class="input-60px corFundo">Clube +<br>Cadastro Maio</td>
				</tr>
				<tr>
					<td></td>
					<td class="input-60px corFundo">NF</td>
				</tr>
				<tr>
					<td>Venc. NF</td>
					<td class="input-60px corFundo">16/06/2016</td>
				</tr>
				<tr>
					<td>MARCELO</td>
					<td>63,21</td>
				</tr>
				<tr>
					<td>PEDRO</td>
					<td>63,21</td>
				</tr>
				<tr>
					<td>CELIA</td>
					<td>52,67</td>
				</tr>
				<tr>
					<td>ERIKA 2,5%</td>
					<td>14,96</td>
				</tr>
									
				<tr>
					<td></td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td>265,88</td>
				</tr>

		<!-- TOTAL  -->
				<tr>
					<td></td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td><b>12.265,88</b></td>
				</tr>
		<!--  -->

 		  </tbody>
		 </table>
	    </div>
    </div>
</div>




<c:import url="../../_comum/footer.jsp" />