<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:forEach items="${estrategia}" var="estrategia">

	<c:set var="titulo" value="${estrategia.internoTitulo}" />
	<c:set var="idInterno" value="${estrategia.idInterno}" />
	<c:set var="idJob" value="${estrategia.idJob.idJob}" />
	<c:set var="empresa" value="${estrategia.idJob.empresa.empresa}" />
	<c:set var="idEmp" value="${estrategia.idJob.empresa.idEmpresa}" />
	<c:set var="codInterno" value="${estrategia.codInterno}" />
	<c:set var="internoDescricao" value="${estrategia.internoDescricao}" />
	<c:set var="prazoConclusao" value="${estrategia.prazoConclusao}" />
	<c:set var="idResponsavel" value="${estrategia.responsavel.idUsuario}" />
	<c:set var="nomeResponsável" value="${estrategia.criadoPor.nome}" />
	<c:set var="responsavel" value="${estrategia.responsavel.nome}" />
	<c:set var="CriadoData" value="${estrategia.criadoData}" />
	<c:set var="concluidoPor" value="${estrategia.concluidoPor.nome}" />
	<c:set var="concluidoData" value="${estrategia.concluidoData}" />
	

</c:forEach>



<div class="row">
	<div class="divisor"></div>
	<div class="col-md-10 col-md-offset-1" id="estrategia">


		<form action="salvaEstrategia" method="post">

			<input type="hidden" name="JobId" value="${idJob}">
			<input type="hidden" name="idInterno" value="${idInterno}">
			<input type="hidden" name="internoTipo" value="internoEstr">
			<input type="hidden" name="idEmp" value="${idEmp}" />
   			<input type="hidden" name="concluido" value="0" />

			<div class="col-md-12" style="background: #ccc; padding-left: 5px">



				<span>Interno</span> <span style="color: red; font-weight: bold">${codInterno}</span>
				<input type="hidden" name="codInterno" value="${codInterno}">


			</div>

			<div class="form-inline ">
				<span style="font-size: 16px">JOB - </span> <strong
					style="font-size: 16px">${empresa} - ${titulo} </strong><br> <br>
			</div>

			<div class="form-group">
				<label for="  ">Item</label> <input type="text" name="internoTitulo"
					class="form-control" value="${titulo}">
			</div>



			<div class="form-group">
				<label for="  ">Linha Criativa</label>
				<textarea class="form-control" name="internoDescricao" rows="5">${internoDescricao}</textarea>
			</div>


			<div class="form-group">
				<label for="">Prazo para conclusão</label>
			</div>


			<div class="form-inline">
				<div class="form-group">
					<input class="form-control input-data data" name="pConclusao"
						value="<fmt:formatDate value="${prazoConclusao}" pattern="dd/MM/yyyy"/>"
						placeholder="__/__/____">
				</div>
				<div class="form-group">
					<input type="time" class="form-control input-data"
						name="hConclusao"
						value="<fmt:formatDate value="${prazoConclusao}" pattern="HH:mm"/>"
						placeholder="00:00">
				</div>
			</div>


			<div class="form-group">
				<div class="col-md-12" style="height: 10px"></div>
				<label for="">Responsável</label> <select name="idResponsavel"
					class="form-control">
					<option value="${idResponsavel}">${responsavel}</option>
					<c:forEach items="${usuariosHabilitados}" var="usuarios">
						<option value="${usuarios.idUsuario} ">${usuarios.nome}</option>
					</c:forEach>
				</select>
				<div class="col-md-12 tira-padding">
					<div class="checkbox">
						<label> <input type="checkbox"> Notificar
							responsável
						</label>
					</div>
					<p>Criado por ${nomeResponsável} em <fmt:formatDate value="${CriadoData.time}" pattern="dd/MM/yyyy HH:mm"/></p>
					
					<c:if test="${not empty concluidoPor}">	
					<p>Concluido por ${concluidoPor} em <fmt:formatDate value="${concluidoData.time}" pattern="dd/MM/yyyy HH:mm"/></p>
					</c:if>
				
				
				
				</div>
			</div>


			<div class="form-inline" style="padding-bottom: 20px;">

				<div class="form-group">

				<c:if test="${empty concluidoPor}">	
					<button type="submit" class="btn btn-danger">Salvar	Estratégia</button>
				</c:if>
				
				
				</div>
</form>				

<c:if test="${empty concluidoPor}">				
<div class="form-group col-md-offset-2">

			
			<form action="concluirEstrategia" method="post">
				<input type="hidden" name="outroId" value="${idJob}">
				<input type="hidden" name="outraEmp" value="${idEmp}" />
				<input type="hidden" name="codInterno" value="${idInterno}" />
				
				<button type="submit" class="btn btn-primary">Estratégia Concluida</button>
			</form>				
</div>
</c:if>
		
		<c:if test="${not empty concluidoPor}">
<div class="form-group">
			<form action="cancelaConcluirEstrategia" method="post">
			<input type="hidden" name="outroId" value="${idJob}">
			<input type="hidden" name="outraEmp" value="${idEmp}" />
			<input type="hidden" name="codInterno" value="${idInterno}" />
			
				<button type="submit" class="btn btn-danger">Cancelar Conclusão</button>
			</form>				
</div>
		</c:if>


			</div>

		


</div>

<!-- ------------------------------- Anexos ----------------------------------------  -->
<div class="col-md-10 col-md-offset-1">
<hr>
<h4>Anexos</h4>
<c:choose>


	<c:when test="${empty AnexosInternoEstr}">
		Sem nenhum anexo
	</c:when>

	<c:when test="${not empty AnexosInternoEstr}">
					<div class="form-group">
					<table class="table table-striped" style="font-size: 12px;">
						<tr>
							
							<th>Cod.</th>
							<th>Título</th>
							<th>Depto.</th>
							<th>Status</th>
							<!-- <th>Criado Por</th>
							<th>Em</th> -->
						</tr>
						<c:forEach items="${AnexosInternoEstr}" var="anexos">
							<tr>
								<td><a href="http://189.100.90.148:8080/syslocc/resources/images/a.pptx" >${anexos.anexoCod}</a></td>

								     <td>${anexos.anexoTitulo}</td>
								     <td>${anexos.anexoarea}</td>
								     <td>${anexos.anexoStatus}</td>
								     <td>${anexos.criadopor.nome}</td>
								<td><fmt:formatDate value="${anexos.criadoData.time}" pattern="dd/MM/yyyy HH:mm"/></td>
							</tr>
						</c:forEach>
					</table>
					</div>
	</c:when>
</c:choose>
<hr>				
</div>
<!-- ---------------------------------------------------------------------------------  -->	
	
<div class="col-md-10 col-md-offset-1">



<h4>Inserir Anexo para estratégia</h4>

	  <c:import url="../anexo/anexo_job.jsp?AnexoOrigem=internoEstr&display=block&idCodigoInterno=${codInterno}"/>
	
</div>	 --%>
	
