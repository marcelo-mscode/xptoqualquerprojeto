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
.descricao {text-align: center;}
.bordaDestaque{border: 2px solid #ccc;}
.direita{border-right: 2px solid #ccc !important;}
.esquerda{border-left: 2px solid #ccc  !important;}
.topo{border-top: 2px solid #ccc  !important;}
.ajusteValores{padding-left: 25px !important;}

.ajusteFuncInput{border: none;height: 20px;padding: 18px;}

</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li><a href="indexAnalitico">Listas Analíticos</a></li>
		    <li class="active">Novo Analítico</li>
		  </ol>					
</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';padding:35px 5px 70px 5px;">
  <div style="padding: 15px 0 55px 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
  	<h2 class="" style="margin-left: 20px;border-bottom: 1px solid #ccc;padding-bottom: 10px">Criar Novo Analítico</h2>
  	<br>

	<form action="criaNovo" method="post" class="form-inline">
	   <div class="form-group" style="margin-left: 20px;" >
	      <label for="exampleInputName2">Mês</label>
	      <select class="form-control" name="mesA">
         	  <c:forEach items="${mesesAno}" var="mesesAno">
					<option value="${mesesAno}">${mesesAno}</option>
				</c:forEach>
	      </select> 
	   </div>
	   <div class="form-group" style="margin-left: 25px">
	      <label for="exampleInputEmail2">Ano</label>
	      <select class="form-control" name="anoA">
	      	<option value="${ano}">${ano}</option>
	      </select>
	   </div>
	   <button type="submit" class="btn btn-danger" style="margin-left: 25px">Criar</button>
	</form>
  </div>
</div>  


<c:import url="../../../_comum/footer.jsp" />
  
  
  
  