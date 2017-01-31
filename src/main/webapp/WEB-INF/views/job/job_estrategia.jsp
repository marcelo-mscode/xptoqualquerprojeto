<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:forEach items="${job}" var="job">
	<c:set var="idJob" value="${job.idJob}" />
	<c:set var="empresa" value="${job.empresa.empresa}" />
	<c:set var="idEmp" value="${job.empresa.idEmpresa}" />
	<c:set var="titulo" value="${job.titulo}" />
	<c:set var="codJob" value="${job.codJob}" />

</c:forEach>

<!---------------- Estratégia -------------------------->
<div class="row painel estilo-painel-job" style="border-right: 1px solid #ddd;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
	
		<div class="col-md-4" style="padding-left: 0;padding: 1px;">
			<h5 style="margin-left: 18px;"><i class="glyphicon glyphicon-list-alt icones"></i>ESTRATÉGIA (${qtdEstrategias})</h5>
	    </div>
	    
	    <div class="col-md-4">
			
	    </div>

	
		<div class="col-md-4">
			<button class="btn btn-link" id="chamaEstrategia" style="margin-top: 3px;">[+] Inserir estratégia</button>
	    </div>
	
	</div>
	
	
	<div class="col-md-6" id="estrategia" style="display: none">
				<div class="col-md-12" style="height: 20px"></div>

				<form action="salvaEstrategia" method="post" id="formEstrategia">


					<input type="text" hidden="true" name="JobId" value="${idJob}">
					<input type="hidden" name="internoTipo" value="internoEstr">
					<input type="hidden" name="idEmp" value="${idEmp}" />


					<div class="col-md-12" style="background: #ccc; padding-left: 5px">

						<c:choose>


							<c:when test="${qtdDeJobs == 0}">

								<span>Interno</span>
								<span style="color: red; font-weight: bold">${codJob}-DI001</span>
								<input type="hidden" name="codInterno" value="${codJob}-DI001">
							</c:when>

							<c:when test="${not empty qtdDeJobs}">

								<c:forEach items="${novaEstrategia}" var="estrategia"
									varStatus="id">

									<span>Interno</span>
									<span style="color: red; font-weight: bold">${codJob}${codInterno}</span>
									<input type="hidden" name="codInterno"
										value="${codJob}${codInterno}">
								</c:forEach>

							</c:when>

						</c:choose>



					</div>

					<div class="form-inline ">
						<br> <br> <span style="font-size: 16px">JOB - </span> <strong
							style="font-size: 16px">${empresa} - ${titulo} </strong><br>
						<br>
					</div>

					<div class="form-group">
						<label for="  ">Item</label>
						<input type="text" name="internoTitulo" class="form-control" maxlength="80">
					</div>



					<div class="form-group">
						<label for="  ">Linha Criativa</label>
						<textarea class="form-control" name="internoDescricao" rows="5" id="internoDescricao"></textarea>
					</div>


					<div class="form-group">
						<label for="">Prazo para conclusão</label>
					</div>


					<div class="form-inline">
						<div class="form-group">
							<input class="form-control input-data data" name="pConclusao"
								placeholder="__/__/____">
						</div>
						<div class="form-group">
							<input class="form-control" type="time" name="hConclusao"
								placeholder="00:00">
						</div>
					</div>


					<div class="form-group">
						<div class="col-md-12" style="height: 10px"></div>
						<label for="">Responsável</label>
						
						
						 <select name="idResponsavel" class="form-control">
							<c:forEach items="${usuariosHabilitados}" var="usuarios">
								<option value="${usuarios.idUsuario} ">${usuarios.nome}</option>
							</c:forEach>
						</select>
						
						
						<div class="col-md-12 tira-padding">
							<div class="checkbox">
								<label>
									 <input type="checkbox" name="notificaResponsavel"> Notificar responsável
								</label>
							</div>
							<hr>
						</div>
					</div>


					<div class="form-inline" style="padding-bottom: 20px;">

						<div class="form-group">

							<button id="button_estrategia" class="btn btn-danger">Salvar	Estratégia</button>
						</div>
						
						<div class="divisor-fino"></div>
						<div>
							<img src="resources/images/loader.GIF" width="30" height="30" class="loader display-none" alt="loading">		
						</div>
						
						<div class="form-group col-md-offset-2">
							<!-- <button type="reset" class="btn btn-primary">Estratégia
							Concluida</button> -->
						</div>

					</div>

				</form>


			</div>
			
	<div class="col-md-12" style="margin-top:15px">
		
		<c:choose>


				<c:when test="${empty estrategia}">
					
				</c:when>

				<c:when test="${not empty estrategia}">
					<div class="form-group">
						<table class="table table-bordered" style="font-size: 12px;">
							<tr class="active">

								<th>Codigo Interno</th>
								<th>Título</th>
								<th>Criado Por</th>
								<th>Cri. data</th>
								<th>Responsável</th>
								<th>Prazo</th>
								<th>Concluido por</th>
								<th>Conc. Data</th>
								<!-- <th>Anexo</th> -->
							</tr>
    			     <c:forEach items="${estrategia}" var="estrategia" varStatus="id">
					 		
							<c:if test="${not empty estrategia.concluidoData.time}">
								<tr class="concluido">
							</c:if>	
							
							<c:if test="${empty estrategia.concluidoData.time}">
								<tr>
							</c:if>	
							
							
							
									<td>${estrategia.codInterno}</td>
									<td>
									
									
									
	<%-- <a href="#" onclick="internoAlterar('internoAlteraEstrategia?codInterno=${estrategia.codInterno}');">${estrategia.internoTitulo}</a></td> --%>
	<a href="<c:url value="internoAlteraEstrategia?codInterno=${estrategia.codInterno}&idEmpresa=${idEmp}&idJob=${idJob}" />" >${estrategia.internoTitulo}</a></td>
	
	
	
	
									<td>${estrategia.criadoPor.nome}</td>
									<td><fmt:formatDate value="${estrategia.criadoData.time}"
											pattern="dd/MM/yyyy HH:mm" /></td>
									
									
									
									
									<td>
									
										<c:if test="${estrategia.notificaResponsavel == true}">
											<i class="glyphicon glyphicon-envelope" style="top: 2px;"></i>
										</c:if>
										
										<c:if test="${estrategia.notificaResponsavel == false}">
											<i class="glyphicon glyphicon-envelope" style="top: 2px;color: transparent;"></i>	
										</c:if>&nbsp&nbsp
										${estrategia.responsavel.nome}
									</td>
									
									
									
									
									
									<td><fmt:formatDate value="${estrategia.prazoConclusao}"
											pattern="dd/MM/yyyy HH:mm" /></td>
									<td>${estrategia.concluidoPor.nome}</td>
									<td><fmt:formatDate value="${estrategia.concluidoData.time}"
											pattern="dd/MM/yyyy HH:mm" /></td>
											
									
											
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
			</c:choose>
			
		<div class="divisor"></div>	
		</div>
			
			
			
	
</div>	


<!-- --------------------------------- Modal para atualizar estratégia ------------------------------ -->
<%-- <div class="modal fade" id="ModalEstrategia">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel" style="margin-left: 35px">Editar Estratégia</h4>
				<img src="<c:url value="resources/images/loader.GIF" />" width="30"
					height="30" class="loader" alt="loading" />
			</div>
			<div class="modal-body-estrategia">
			
			</div>
	    </div>
    </div>
</div> --%>
<!-- ------------------------------------------------------------------------------------------------------------------------------ -->
