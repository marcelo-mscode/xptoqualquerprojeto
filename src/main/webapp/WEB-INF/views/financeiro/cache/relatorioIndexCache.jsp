<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />



<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li class="active">Cachê Padrão</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">
			RELATÓRIO DE CACHES	
		</span>
	</div>
</div>



<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 5px 70px 5px;">
  <div style="padding: 55px 0 0px 20px;;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
	 	
	 	<div class="col-md-5" style="padding-left: 0;">
		 <table class="table table-hover table-bordered bordaDestaque" >
		   <tbody id="prospeccaoFiltro">
			 <tr>
				<td class="input-140px">2016</td>
			 </tr>
			 <tr>
				<td class="input-140px">
					<table class="table table-hover table-bordered bordaDestaque">
						<tbody id="prospeccaoFiltro">
							<tr>
								<td class="input-140px"><a href="relatorioCacheIndividual">DEZEMBRO</a></td>
								<td class="input-140px">NOVEMBRO</td>
								<td class="input-140px">OUTUBRO</td>
								<td class="input-140px">SETEMBRO</td>
								<td class="input-140px">AGOSTO</td>
								<td class="input-140px">JULHO</td>
								<td class="input-140px">JUNHO</td>
								<td class="input-140px">MAIO</td>
								<td class="input-140px">ABRIL</td>
								<td class="input-140px">MARÇO</td>
								<td class="input-140px">FEVEREIRO</td>
								<td class="input-140px">JANEIRO</td>
							</tr>
						</tbody>
					</table>	
				</td>
			 </tr>

			 <tr>
				<td class="input-140px">2015</td>
			 </tr>
			 <tr>
				<td class="input-140px">
					<table class="table table-hover table-bordered bordaDestaque">
						<tbody id="prospeccaoFiltro">
							<tr>
								<td class="input-140px">DEZEMBRO</td>
								<td class="input-140px">NOVEMBRO</td>
								<td class="input-140px">OUTUBRO</td>
								<td class="input-140px">SETEMBRO</td>
								<td class="input-140px">AGOSTO</td>
								<td class="input-140px">JULHO</td>
								<td class="input-140px">JUNHO</td>
								<td class="input-140px">MAIO</td>
								<td class="input-140px">ABRIL</td>
								<td class="input-140px">MARÇO</td>
								<td class="input-140px">FEVEREIRO</td>
								<td class="input-140px">JANEIRO</td>
							</tr>
						</tbody>
					</table>	
				</td>
			 </tr>
 		  </tbody>
		 </table>
	    </div>
    </div>
</div>




<c:import url="../../_comum/footer.jsp" />