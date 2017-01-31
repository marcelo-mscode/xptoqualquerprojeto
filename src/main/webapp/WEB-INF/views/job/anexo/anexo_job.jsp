<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

  
<c:forEach items="${job}" var="job">


	<c:set var="idJob" value="${job.idJob}" />
	<c:set var="idEmp" value="${job.empresa.idEmpresa}" />
	<c:set var="display" value="${param.display}" />
	<c:set var="empresa" value="${job.empresa.empresa}" />
	<c:set var="titulo" value="${job.titulo}" />
	<c:set var="codJob" value="${job.codJob}" />


<!-- Recebe a origem do anexo, ex.: InternoEstr, Job, internoCriacao -->	
<c:set var="AnexoOrigem" value="${param.AnexoOrigem}" />

<!-- Verifica se o parametro foi passado do Job ou da estratégia  -->
<c:if test="${empty param.JobCodJob}">
	<c:set var="idInterno" value="${param.idCodigoInterno}" />
</c:if>

<c:if test="${not empty param.JobCodJob}">
		<c:set var="idInterno" value="${param.JobCodJob}" />
</c:if>



</c:forEach>  
  
   
<form action="recebeAnexo"  id="recebeAnexo" method="post" enctype="multipart/form-data" class="anexo"
style="display: ${display}; padding-bottom: 20px;">


<input type="hidden" name="idJob" value="${idJob}">
<input type="hidden" name="anexoOrigem" value="${AnexoOrigem}">
<input type="hidden" name="CodInterno" value="${idInterno}">


<input type="hidden" name="idCriadoPor" value="40">
<input type="hidden" name="alteradoPor" value="40">
<input type="hidden" name="idCriadoPor" value="40">
<input type="hidden" name="idEmp" value="${idEmp}">


			<div class="form-inline">
						

							<div class="form-group">
								
							<input class="form-control"
							type="text" name="AnexoTitulo" style="width: 427px" placeholder="Nome do Anexo"><br />
							</div>

							<br /><br />
							<label>Departamento</label><br />
	
							<select name="anexoarea" class="form-control" onchange="pegaCaminho(this.value,${idJob});">
								<option value="selecione">Selecione...</option>
								<option value="atendimento">Atendimento</option>
								<option value="comercial">Comercial</option>
								<option value="criacao">Criação</option>
								<option value="estrategia">Estratégia</option>
								<option value="financeiro">Financeiro</option>
								<option value="Producao">Produção</option>
								<option value="terceiro">Terceiro</option>
								<option value="trafego">Tráfego</option>
							</select>
							
							
													
			<select class="form-control caminho" name="pastaIntermediaria" onchange="criaCampoPastaIntermediaria(this.value);" id="selectIntermediario">
				<option value="selecione">Selecione</option>
				<option id="criarPasta" value="criaPasta" >Criar Pasta</option>
			</select>
							
			<div style="display:none;padding-left: 20px;" id="divIntermediaria">
				<input type="text" name="pastaIntermediaria" class="form-control" placeholder="Digite o nome da Pasta" style="width: 185px;"/>
				<button class="btn btn-link" style="padding: 0 10px 0 0;" onclick="criaCampoPastaIntermediaria();">Cancelar</button>
			</div>
			
							
			<select name="anexoStatus" class="form-control">
				<option value="arquivoSaida">Arquivo de Saída</option>
				<option value="armazenar">Armazenar</option>
				<option value="layout">Layout</option>
				<option value="cliente">Cliente</option>
				<option value="revisao">Revisão</option>
			</select>
						
	</div>
<br />
					<div class="form-group">
							
							<div class="col-md-4" style="padding-left: 0;width: 440px;">
							 <input type="file" class="filestyle" data-buttonBefore="true" name="NomeAnexo" id="fileAnexo">
							</div>
							
						
					</div>

					<div class="row">
						<div class="form-group">
						
							<div class="col-md-12" style="padding-left: 5px;">
						<hr>		
								<button id="button_anexo" class="btn btn-danger">Salvar Anexo</button>
							</div>
						</div>
					</div>

</form>
