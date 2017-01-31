<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />




<div class="container"> 

<div class="row col-md-offset-1">


<div class="row">
    <div class="col-md-6 painel ajuste-left " >

		<form action="recebeAnexo" method="post"
			enctype="multipart/form-data">


<input type="hidden" name="idJob" value="776">
<input type="hidden" name="anexoOrigem" value="internoEstr">
<input type="hidden" name="CodInterno" value="1506031-DI001">
<input type="hidden" name="idCriadoPor" value="40">
<input type="hidden" name="alteradoPor" value="40">
<input type="hidden" name="idCriadoPor" value="40">


			<div class="form-inline">
			   <div class="col-md-12">
			   
			   <div class="form-group">
				   <label>Título</label><br />
				   <input class="form-control" type="text" name="AnexoTitulo" style="width: 427px"><br />
			   </div>
			   
			   <br /><label>Departamento</label><br /> <select name="AnexoArea"
					class="form-control">
					<option value="Atendimento">Atendimento</option>
					<option value="Comercial">Comercial</option>
					<option value="Criação">Criação</option>
					<option value="Estratégia">Estratégia</option>
					<option value="Financeiro">Financeiro</option>
					<option value="Produção">Produção</option>
					<option value="Terceiro">Terceiro</option>
					<option value="Tráfego">Tráfego</option>
				</select> <select class="form-control">
					<option>Selecione</option>
					<option>Criar Pasta</option>
				</select> <select name="anexoStatus" class="form-control">
					<option value="arquivoSaida">Arquivo de Saída</option>
					<option value="armazenar">Armazenar</option>
					<option value="layout">Layout</option>
					<option value="cliente">Cliente</option>
					<option value="revisao">Revisão</option>
				</select>
				</div>
			</div>

			<div class="form-group">
			  <div class="col-md-12">
			  <div class="divisor"></div>
				<input type="file" name="NomeAnexo">
			  </div>
		    </div>
			
			<div class="row">
				<div class="form-group ajuste">
				  <div class="col-md-12">				
					<div class="divisor"></div>
					<button type="submit" class="btn btn-info">Enviar</button>
				  </div>
				</div>
			</div>

 		</form>

    </div>
</div>
	
</div>	
	
	
</div>

<c:import url="../_comum/footer.jsp" />