<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />
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
		    <li class="active">Cachê Padrão</li>
		  </ol>					
</div>

<div style="height: 35px;">
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">
			CACHÊ PADRÃO		
		</span>
	</div>
</div>



<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 5px 70px 5px;">
  <div style="padding: 55px 0 0px 20px;;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
	 	
	 	<div class="col-md-5" style="padding-left: 0;">
		 <table class="table table-hover table-bordered bordaDestaque" >
		   <tbody id="prospeccaoFiltro">
			 <tr>
				<td class="input-140px">Funcionário</td>
				<td class="input-80px">Cachê %</td>
				<td>Tipo Cache</td>
				<td>Apagar</td>
			 </tr>
			 
			 <c:forEach items="${caches}" var="caches">
			 <tr>
				<td style="padding: 0"><input id="nomeFunc${caches.idCachePadrao}" type="text" value="${caches.nomeFunc}" class="ajusteFuncInput input-140px" onblur="editaCache('editaCacheNome','nome${caches.idCachePadrao}','${caches.idCachePadrao}');"></td>
				<td style="padding: 0"><input id="porcent${caches.idCachePadrao}" type="text" value="${caches.porcentagem}" class="ajusteFuncInput input-80px" onblur="editaCache('editaCacheNome','porcent${caches.idCachePadrao}','${caches.idCachePadrao}');"></td>
				<td>${caches.tipoCache}</td>
				<td style="text-align: center;"><a href="deletaCache?idCachePadrao=${caches.idCachePadrao}" style="color: red;"><i class="glyphicon glyphicon-remove"></i></a></td>
			 </tr>
			 </c:forEach>
			</tbody>
		  </table>	
			<div  style="height: 40px;">
				<p style="display: none" id="sucesso">	Alterado com Sucesso <i class="glyphicon glyphicon-ok" style="top: 1px;color: green;font-size: 20px;margin-left: 10px;"></i></p>
			</div>		  
		  
		</div>	
	
	 	<div class="col-md-3" style="padding: 35px 25px 25px 25px;border: 2px solid #ccc">
	 	<h3 style="margin-top: 0 !important;">Novo Cache</h3>
	 		
	 	<form action="cadastraNovoCache" method="post">
				<div class="form-group">
					 
					<label for="exampleInputEmail1">
						Nome Funcionário
					</label>
					<input type="text" class="form-control" name="nomeFunc" />
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">
						Porcentagem
					</label>
					<input type="text" class="form-control" name="porcentagem" />
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1">
						Categoria
					</label>
					<select class="form-control" name="tipoCache">
					 	  <option value="FUNCIONARIO">FUNCIONARIO</option>
					 	  <option value="DIRETORIA1">DIRETORIA1</option>
					 	  <option value="DIRETORIA2">DIRETORIA2</option>
				 	</select>
				</div>
			
				<button type="submit" class="btn btn-danger">
					Cadastrar
				</button>
			</form>	
	</div> 	
		
  </div>		
</div>		
<c:import url="../../_comum/footer.jsp" />
