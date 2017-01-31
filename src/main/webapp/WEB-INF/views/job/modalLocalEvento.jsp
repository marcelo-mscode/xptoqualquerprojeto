<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<script type="text/javascript" src="<c:url value="resources/js/jquery-1.11.2.min.js" />"></script>

<script type="text/javascript">
//Salva Novo Local do Evento em Job
$("#modalEvento").submit(function() {
	
    var modalLocalEventoNome = $("#modalLocalEventoNome").val();
	
	if( modalLocalEventoNome  == "" || modalLocalEventoNome  == null || modalLocalEventoNome  == " "){
		$("#modalLocalEventoNome").css("border","2px solid red")
   	    .parent().append("<span class='errors'> *Insira o nome do local do Evento.</span>");
		$(".errors").show();
		return false;
	}
	return true;
});
</script>


<c:forEach items="${evento}" var="evento">


<form action="cadLocalEvento" method="post" id="modalEvento">
<div class="row">
		    <div class="col-md-8">
			<div class="form-group">
				<label for="">Nome</label>
				<input type="hidden" name="idLocalEvento" value="${evento.idLocalEvento}">
				<input type="hidden" name="JobId" value="${evento.idJob.idJob}">
				<input type="hidden"   name="idEmpresa" value="${evento.idJob.empresa.idEmpresa}">
				
				
				<input type="text" name="localEventoNome" class="form-control" value="${evento.localEventoNome}" id="modalLocalEventoNome">
			</div>
			
			<div class="form-group">
					<label for="">Local do Evento</label> <input type="text"
						name="local" class="form-control" value="${evento.local}">
			</div>
			
			<div class="form-group">
				<label for="">Endereço</label> <input type="text"
					name="localEventoEndereco" class="form-control" value="${evento.localEventoEndereco}">
			</div>
			<div class="form-group">
				<label for="">Observação</label>
				<textarea name="localEventoObservacoes" class="form-control"
					rows="3">${evento.localEventoObservacoes}</textarea>
			</div>
			
			
		</div>	
</div>

<div class="row">
			<div class="col-md-8">
				
				<div class="form-inline">
					<div class="form-group col-md-12 tira-padding">
						<label for="">Qtd Pessoas&nbsp&nbsp&nbsp</label> <input type="text"
							name="localEventoQtdPessoas" class="form-control col-md-offset-1"
							style="width: 65px" value="${evento.localEventoQtdPessoas}">
					</div>
				</div>
				<div class="form-inline">
					<div class="col-md-8" style="height: 10px"></div>
					
					<div class="form-group">
						<label for="">Data de
							Inicio&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
							<input type="text" name="dataInicio" class="form-control data" style="width: 110px"
							value="<fmt:formatDate value="${evento.localEventoDataInicio}"
							pattern="dd/MM/yyyy"/>" placeholder="__/__/____" />
					</div>
					
					<div class="form-group">
						<input type="time" class="form-control" name="horaInicio"
							placeholder="Hora"
						value="<fmt:formatDate value="${evento.localEventoDataInicio}"
						pattern="HH:mm"/>"	
						>
						
							
							
					</div>
				</div>
				<div class="form-inline">
					<div class="col-md-8" style="height: 10px"></div>
					<div class="form-group">
						<label for="">Data de Término&nbsp&nbsp&nbsp</label> <input
							type="text" name="dataTermino" class="form-control data"
							style="width: 110px"
							value="<fmt:formatDate value="${evento.localEventoDataTermino}"
					  	    pattern="dd/MM/yyyy"/>"
							placeholder="__/__/____">
					</div>
					<div class="form-group">
						<input type="time" name="horaTermino" class="form-control"
						value="<fmt:formatDate value="${evento.localEventoDataTermino}"
					    pattern="HH:mm"/>" placeholder="Hora">
					</div>
				</div>
		  </div>
		  
</div>
<div class="divisor"></div>		  
	<div class="form-group">
	<hr>
			<div class="form-inline">
				
				<div class="form-group">
					<button type="submit" class="btn btn-danger">Salvar Local do evento</button>
				</div>
				
				<div class="form-group">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.reload();">Cancelar</button>
				</div>
			</div>
		
		</div>

</form>




</c:forEach>

		


