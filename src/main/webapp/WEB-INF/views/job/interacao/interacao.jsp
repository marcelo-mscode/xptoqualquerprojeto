<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  

<c:forEach items="${job}" var="job"> 
	<c:set var="idJob" value="${job.idJob}" />
	<c:set var="idContato" value="${job.contato.idContato}" />
    <c:set var="idEmp" value="${job.empresa.idEmpresa}" />
    <c:set var="tituloJob"  value="${job.titulo}" ></c:set>
</c:forEach>


<div class="row painel estilo-painel-job" style="border-right: 1px solid #ddd;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
	
		<div class="col-md-4" style="padding-left: 0;padding: 1px;">
			<h5 style="margin-left: 18px;"><i class="glyphicon glyphicon-retweet icones"></i>INTERAÇÃO</h5>
	    </div>
	    
	    <div class="col-md-4">
			
	    </div>

	
		<div class="col-md-4">
			<button class="btn btn-link" id="interacao" style="margin-top: 3px;">[+] Inserir interação</button>
	    </div>
	
	</div>
	
	<div class="col-md-6" style="margin-top:15px">

		<form action="CadInteracao" class="interacao" id="formInteracao" method="post"
			style="padding: 10px 10px 20px 0; margin-bottom: 30px;display:none" >

			<input type="hidden" name="idUsuarioUsuario" value="40" /> <input
				type="hidden" name="interacaoOrigem" value="job" /> <input
				type="hidden" name="idJobJob" value="${idJob}"> <input
				type="hidden" name="automatico" value="0"> <input
				type="hidden" name="idEmp" value="${idEmp}" /> <input type="hidden"
				name="tituloJob" value="${tituloJob}" />

			<div class="row">
				<div class="form-group">
					<label for="  ">Descrição da Interação</label>
					<textarea class="form-control null" id="intercaoTexteArea"
						name="interacao" rows="5"></textarea>
					<span class="errors" style="display: none"></span>
				</div>
			</div>

			<div class="row">
				<div class="checkbox">
					<label> <input type="checkbox" name="interno"
						id="internaInteracao"> Interação interna
					</label>
				</div>
			</div>

			<div class="row">

				<select name="idContatoContato" id="contatoInteracao"
					class="form-control" style="display: block">
					<c:forEach items="${contato}" var="contato">
						<option value="${contato.idContato}">${contato.contato}</option>
					</c:forEach>

				</select> <select id="usuarioInteracao" class="form-control"
					style="display: none">
					<c:forEach items="${usuariosHabilitados}" var="usuarios">
						<option value="${usuarios.idUsuario}">${usuarios.nome}</option>
						<%-- <input type="hidden" name="idUsuarioInterno" value="${usuarios.idUsuario}">      --%>
					</c:forEach>
				</select>
			</div>
			<div class="divisor-fino"></div>

			<div class="row">
				<div class="form-group col-md-3 tira-padding">
					<label for="">Forma de Contato</label> <select class="form-control"
						name="interacaoForma">
						<option value="Telefone">Telefone</option>
						<option value="Email">Email</option>
						<option value="Site">Site</option>
						<option value="Visita">Visita</option>
						<option value="Outro">Outro</option>
					</select>
				</div>


				<div class="form-group col-md-3 tira-padding">
					<label for="">Próxima Interação</label> <input type="text"
						class="form-control data" id="dataInteracao" name="pd"
						placeholder="__/__/____">

				</div>

				<div class="form-group col-md-3 tira-padding">
					<label for="">Hora</label> <input type="time" class="form-control"
						id="horaInteracao" name="ph">
				</div>



			</div>

			<div class="row">

				<div class="form-group col-md-10 tira-padding">
					<span class="errors_data" style="display: none">Preencher a
						DATA e a HORA da próxima interação ou Não Agendar.</span>
				</div>

			</div>




			<div class="row">
				<div class="checkbox">
					<label> <input type="checkbox" name=""> Inserir
						como Tarefa
					</label> &nbsp&nbsp&nbsp <label> <input type="checkbox"
						id="agendarInteracao" name="agendar"> Não Agendar
					</label>
				</div>

				<div class="checkbox">
					<label> <input type="checkbox" id="comunicacaoInterna"
						name=""> Comunicar Outros via Email
					</label>
				</div>

				<select name="multiplo" multiple id="comunicaUsuario"
					class="form-control" style="display: none; height: 170px">
					<c:forEach items="${usuariosHabilitados}" var="usuarios">
						<option value="${usuarios.idUsuario}">${usuarios.nome}</option>
					</c:forEach>
				</select>



			</div>


			<div class="row">
				<div class="divisor-fino"></div>
				<button type="submit" id="button_interacao" class="btn btn-danger">Salvar
					Interação</button>
				<div class="divisor-fino"></div>
				<img src="<c:url value="resources/images/loader.GIF" />" width="30"
					height="30" class="loader display-none" alt="loading" />
			</div>


		</form>


	</div>
	
	
	<div class="col-md-12" style="margin-top:15px">
		<c:choose>
			<c:when test="${empty interacao}">
				
			</c:when>

			<c:when test="${not empty interacao}">

				<table class="table table-bordered" style="font-size: 12px;">
					<tr class="active">

						<th>Interação</th>
						<th>Contato</th>
						<th>Usuário</th>
						<th>Data</th>
						<th>Forma</th>
						<th>Prox. Interação</th>

					</tr>
					<c:forEach items="${interacao}" var="interacao" varStatus="id">

						<tr>
							<td style="word-break: break-all;">${interacao.interacao}</td>

							<td><c:if test="${interacao.interno == true}">
									${interacao.idUsuarioInterno.nome}
								</c:if> <c:if test="${interacao.interno == false}">
									${interacao.idContato.contato}
								</c:if></td>

							<td>${interacao.idUsuario.nome}</td>
							<td><fmt:formatDate value="${interacao.dataInteracao.time}"
									pattern="dd/MM/yyyy HH:mm" /></td>
							<td>${interacao.interacaoForma}</td>
							<td><c:if test="${empty interacao.dataProximaInteracao}">
									Não agendada
								</c:if> <c:if test="${not empty interacao.dataProximaInteracao}">
									<fmt:formatDate value="${interacao.dataProximaInteracao}"
										pattern="dd/MM/yyyy HH:mm" />
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>

	</div>
</div>

