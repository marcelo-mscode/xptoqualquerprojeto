<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />


<!-- - - - - - - - - - Container - - - - - - - - - - - -->
	<div style="height: 80px;background: #e74c3c" class="">
		<div class="row" style="margin-top: 0px;margin-left: 5px">
			<div class="col-md-12" style="margin-top: 10px">
				<span style="font-size: 25px;color: #fff;font-family:'OpenSansLight' ">PRODUÇÃO - Configuração de Carta de Contratação</span>
			</div>
		</div>
		
		<!-- <div class="col-md-12" style="color:#fff !important">
			<ol class="breadcrumb">
			    <li><a href="#" style="color:#ecf0f1 !important">Menu</a></li>
			    <li class="active" style="color:#ecf0f1 !important"><a href="menuProducao" style="color:#ecf0f1 !important">Listas de Produção Aprovadas</a></li>
			    <li class="active" style="color:#bdc3c7">Itens da Lista</li>
			</ol>
		</div> -->
   </div>	

<div class="container"> 

<br />	
<div class="col-md-10 painel shadow-com-padding">
<div class="divisor"></div>
<div class="divisor"></div>
<form action="salvaCarta" method="post">
	<input type="hidden" value="${carta.idCarta}" name="idCarta">

	<div class="row">
		<div class="col-md-12">
			<div class="col-md-4">
			<input class="form-control border-none" name="dataCabecalho" value='São Paulo, ${diahoje} de ${mesAtual} de ${anoAtual}'>
			</div>
		</div>
	</div>	
	
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-5">
				<textarea rows="4" cols="45" class="form-control" name="empresaCabecalho">${carta.empresaCabecalho}</textarea>
			</div>
		</div>
	</div>	

	<div class="row">
		<div class="col-md-3">
			<div class="col-md-12">
				<input type="text" name="empCab1" class="form-control border-none" value="${carta.empCab1}" >
			</div>
		</div>
		
		
		
	</div>	
	
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12 form-inline">
				<input type="text" name="empCab2" class="form-control input-260px border-none" value="${carta.empCab2}" >
				<select class="form-control font-bold" name="empCab3">
					<option value="a COMPRA dos itens abaixo,">a COMPRA dos itens abaixo,</option>
					<option value="a LOCAÇÃO dos itens abaixo,">a LOCAÇÃO dos itens abaixo,</option>
					<option value="a Contratação de serviço de Promotora,">a Contratação de serviço de Promotora,</option>
				</select>
				<input type="text" name="empCab4" class="form-control input-260px border-none" value="${carta.empCab4}" >
			</div>
		</div>
	</div>	
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12 form-inline">
				<input type="text" name="empCab5" class="form-control input-440px border-none font-bold" value="${carta.empCab5}" >
				<input type="text" name="empCab6" class="form-control input-180px border-none" value="${carta.empCab6}" >
				<input type="text" name="empCab7" class="form-control input-180px border-none font-bold" value="${carta.empCab7}" >
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12 form-inline">
				<input type="text" name="empCab8" class="form-control input-440px border-none" value="${carta.empCab8}" >
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12 form-inline">
				<input type="text" name="empCab9" class="form-control input-560px border-none" value="${carta.empCab9}" >
			</div>
		</div>
	</div>
	
	<div class="row">
	
		<div class="col-md-12">
			<div class="col-md-12">
				<input type="text" name="descricao" class="form-control input-120px border-none font-bold" value="${carta.descricao}" >
			</div>
		</div>
	</div>
	<div class="row" style="margin-left: 25px">
			<div class="col-md-12">
				<input class="form-control border-none" name="descItem" value="${carta.descItem}" >
			</div>
	</div>
	<div class="row" style="margin-left: 25px">
			<div class="col-md-12 form-inline">
				<input class="form-control border-none input-160px" name="dataEntregaTexto" value="${carta.dataEntregaTexto}" >	
				<input class="form-control border-none input-220px data" name="dataEntrega" value="${carta.dataEntrega}" >	
			</div>			
	</div>					
	
	<div class="row" style="margin-left: 25px">
		<div class="col-md-12">					
					<input class="form-control border-none" name="localEntrega" value="${carta.localEntrega}" >	
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">					
		   <div class="col-md-12 form-inline font-bold">					
			<input class="form-control border-none input-120px" name="valorTotalTexto" value="${carta.valorTotalTexto}" >	
			<input class="form-control border-none input-220px" name="valorTotal" value="${carta.valorTotal}" >	
			<input class="form-control border-none input-480px" name="valorTotalTexto2" value="${carta.valorTotalTexto2}" >	
		   </div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">					
				<input class="form-control border-none input-220px" name="condicaofaturamento" value="${carta.condicaofaturamento}" >	
				<input class="form-control border-none input-180px" name="condicaofaturamentoDias" value="${carta.condicaofaturamentoDias}" >	
				<input class="form-control border-none input-120px" name="dataFaturamento" value="${carta.dataFaturamento}" >	
		    </div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">					
				<strong><input class="form-control border-none input-660px" name="dadosEmissaoNota" value="${carta.dadosEmissaoNota}" ></strong>
		    </div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">					
				<strong><input class="form-control border-none input-220px" name="dadosEmissaoNotaResponsavel" value="${carta.dadosEmissaoNotaResponsavel}" ></strong>	
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">					
				<input class="form-control border-none input-480px" name="loccoRazaoSocial" value="${carta.loccoRazaoSocial}" >	
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">					
				<input class="form-control border-none input-560px" name="loccoEndereco" value="${carta.loccoEndereco}" >	
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">					
				<input class="form-control border-none input-480px" name="loccoCnpj" value="${carta.loccoCnpj}" >	
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">	
			 <p style="color: red;">*Atenção:<br>
			 Nas Informações importantes abaixo, o texto <span style="font-weight: bold;">"/GTAV-MR"</span> é uma palavra coringa para o sistema,<br> essa palavra não poderá ser alterada, o restante poderá alterar normalmente.</p>
			</div>
			

		    <div class="col-md-12 form-inline">	
				<textarea class="form-control font-bold" rows="12" cols="120" name="informacoesImportantes" style="font-size: 12px;text-decoration: underline;">${carta.informacoesImportantes}</textarea>
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">	
		        <input class="form-control border-none input-580px" name="esclarecimentos" value="${carta.esclarecimentos}" >				
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">	
			    <input class="form-control border-none input-180px" name="atenciosamente" value="${carta.atenciosamente}" >					
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">	
		    	<input class="form-control border-none input-220px" name="responsavelContratacao" value="${carta.responsavelContratacao}" >					
		    </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">					
		    <div class="col-md-12 form-inline">
			    <input class="form-control border-none input-120px" name="deAcordo" value="${carta.deAcordo}" >					
		    </div>
		</div>
	</div>
	
	<br />
	
	<div class="row">
		<div class="col-md-6">
		  &nbsp&nbsp&nbsp&nbsp<button type="submit" class="btn btn-danger">Salvar Carta</button>
		</div>
		<!-- <div class="col-md-6">
		  &nbsp&nbsp&nbsp&nbsp<button type="submit" class="btn btn-success">Gerar Carta</button>
		</div> -->
	</div>
	<br />
</form>	

	<div class="row">
	</div>
</div>



</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->




<c:import url="../../_comum/footer.jsp" />
