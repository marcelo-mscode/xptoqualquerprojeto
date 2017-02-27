<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<c:forEach items="${job}" var="job">  
    <c:set var="idJob" value="${job.idJob}" />
    <c:set var="jobTitulo" value="${job.titulo}" />
    <c:set var="JobEmp" value="${job.empresa.empresa}" />
    <c:set var="idEmp" value="${job.empresa.idEmpresa}" />
    <c:set var="JobContato" value="${job.contato.contato}" />
    <c:set var="JobContatoHabilitado" value="${job.contato.habilitado}" />
    <c:set var="JobContatoId" value="${job.contato.idContato}" />
    <c:set var="JobPropostaData" value="${job.propostaData}" />
    <c:set var="JobApresPropostaData" value="${job.apresPropostaData}" />
    <c:set var="JobDescricao" value="${job.descricao}" />
    <c:set var="JobVerba" value="${job.verba}" />
    <c:set var="JobcodJob" value="${job.codJob}" />
</c:forEach>


<script type="text/javascript" src="resources/js/jquery-1.11.2.min.js"></script>	


<script type="text/javascript">
function emailSucesso(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-sucesso-email").slideToggle("slow"); 
	    }, 4000);
	});
}

function emailErro(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-erro-email").slideToggle("slow"); 
	    }, 10000);
	});
}
</script>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li><a href="listajob">Lista de Jobs</a></li>
		    <li class="active"><b>Edição do Job</b></li>
		</ol>
	</div>
</div>
<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 26px;background: #fff">
	<h4 style="font-family: 'OpenSansLight'">EDIÇÃO DO JOB</h4>
</div>

<c:if test="${msg.get(0) == 'sucesso'}">
	<div class="msg-email msg-sucesso-email" id="msg-sucesso-email" >${msg.get(1)}</div>
	<script type="text/javascript">emailSucesso();</script>
</c:if>	

<c:if test="${msg.get(0) == 'erro'}">
	<div class="msg-email msg-erro-email" id="msg-erro-email">${msg.get(1)}</div>
	<script type="text/javascript">emailErro();</script>
</c:if>


<div class="container"> 

<c:forEach items="${estatus}" var="estatus">
	<c:set var="idStatus" value="${estatus.idStatus.idEstatus}" />
	<c:set var="observacao" value="${estatus.jobStatusObservacao}" />	
	<c:set var="prazoConclusao" value="${estatus.prazoConclusao}" />	
	<c:set var="statusAtual" value="${estatus.idStatus.estatus}" />
	<c:set var="statusUsuario" value="${estatus.idUsuario.nome}" />
	<c:set var="statusIdUsuario" value="${estatus.idUsuario.idUsuario}" />
	<c:set var="produtor2id" value="${estatus.produtor2.idUsuario}" />
	<c:set var="produtor2nome" value="${estatus.produtor2.nome}" />
	<c:set var="ultimaAtualizacao" value="${estatus.criadoData.time}" />
	<c:set var="criadoPor" value="${estatus.criadoPor}" />
</c:forEach>

     
 <div class="row painel estilo-painel-job" style="border-right: 1px solid #ddd;">

	<div class="col-md-8  painel" style="border-bottom: 1px solid #D7D1C8;padding-left: 0;border-right: 3px solid #f1f1f1;">
	
		<div class="col-md-4" style="padding-left: 0;padding: 1px;">
			<h4 style="margin-left: 18px;">JOB <span style="color: red;font-weight: bolder;">${JobcodJob}</span></h4>
	    </div>
	    
   </div> 
	
	<div class="col-md-4" style="border-bottom: 1px solid #D7D1C8;padding: 1px;">
			<h4 style="padding-left: 27px">STATUS</h4>
    </div>


 <div class="col-md-8" id="" style="padding-bottom: 17px;border-right: 3px solid #f1f1f1;">
				<div class="col-md-12" style="height: 20px"></div>
		
		   <form action="cadjob" method="post"> 
		   		
		   		<input type="hidden" name="idJobEditar" value="${idJob}">	 
		   		<input type="hidden" name="codJobTransient" value="${JobcodJob}">
		   			 
		        <div class="form-group col-md-8 tira-padding">
		          <label for="exampleInputEmail1">Título</label>
		          <input type="text" class="form-control" name="titulo" value="${jobTitulo}" placeholder="Título do Job">
				</div>		    	
		        
		        <div class="form-group col-md-8 tira-padding">
		          <label for="exampleInputEmail1">Cliente</label>
		         
		          <select name="idEmp" class="form-control">
		         	<option value="${idEmp}">${JobEmp}</option>
			        <option >Selecione a empresa</option>
		          </select>
		        
		        </div>
		    
		    <div class="form-group col-md-12 tira-padding" >
		        <label for="">Contatos</label><br>
		        <img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader display-none" alt="loading" />
		        
		            <div class="info-contato">
			            <div class="col-md-8 fundo" style="height:250px;overflow: overlay;">
						     <c:forEach items="${contato}" var="contato">
						     <a href="#" onclick="pegaComunicadoresPorIdContato(${contato.idContato},'carrega')" data-toggle="modal" data-target="#ModalEdita">${contato.contato}</a><br>
				       		 
				        	 	 <c:forEach items="${contato.comunicador}" var="comunicador">			
					           	   &nbsp&nbsp<span class="cor">${comunicador.comunicador}</span><br>
					             </c:forEach>
				            </c:forEach> 
			           	 <div class="divisor-fino"></div> 
					   
					   
					   
					   
					   </div>
					    
					   <div class="col-md-4">
			  				<a href="" data-toggle="modal" data-target="#myModal">Adicionar contato</a>
		  				</div>
					   
					   
		          </div>
			</div>
		    
				
		    <div class="form-group col-md-8 tira-padding">
		        <div class="divisor"></div>	
		        
		          <label>Solicitante Job</label>  
		        	  
		          <select name="idContato" class="form-control">
		        
			        <c:if test="${JobContatoHabilitado == false}"></c:if>
			        <c:if test="${JobContatoHabilitado == true}">
			        	<option value="${JobContatoId}">${JobContato}</option>
			        </c:if>
		
		        	<c:forEach items="${contato}" var="contato"> 
		        		<c:if test="${contato.habilitado == true}">
		        			<c:if test="${JobContatoId != contato.idContato}">
			        	  	 <option value="${contato.idContato}">${contato.contato}</option>
							</c:if>
						</c:if>
		            </c:forEach>
		        
		          </select>
		    </div>

		   <div class="form-inline col-md-8 tira-padding">
				<div class="divisor"></div>
				<div class="form-group">
				
					<label for="data">Prazo para
						proposta&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
						
						<input	type="text"	value="<fmt:formatDate value="${JobPropostaData}" pattern="dd/MM/yyyy"/>"
						class="form-control data" name="pd" style="width: 110px"
						placeholder="__/__/____">

				</div>
				<div class="form-group ">

					<input type="time" class="form-control"
						value="<fmt:formatDate value="${JobPropostaData}" pattern="HH:mm"/>"
						name="ph" placeholder="Hora">
				</div>
			</div>
		        
		        
	        <div class="col-md-12" style="height:20px"></div>
	        <div class="form-inline col-md-8 tira-padding">
	          <div class="form-group">
	            <label for="data">Proposta apresentada em&nbsp</label>
	             
	            <input type="text" value="<fmt:formatDate value="${JobApresPropostaData}" pattern="dd/MM/yyyy"/>"
	            class="form-control data" name="dataProposta" style="width:110px" placeholder="__/__/____">
	          </div>
	          <div class="form-group ">
	           
	            <input type="time" value="<fmt:formatDate value="${JobApresPropostaData}" pattern="HH:mm"/>"
	            
	            class="form-control"  name="apresHoraProposta" placeholder="Hora">
	          </div>
	        </div>  

		
		        <div class="col-md-8" style="height:20px"></div>
		          
		        <div class="form-group col-md-8 tira-padding">
		            <label for="">Descrição do Evento:</label>
		            <textarea class="form-control" name="descricao" rows="3">${JobDescricao}</textarea>
		        </div>  
		         
		        <div class="col-md-8 tira-padding">
		            <label for="">Verba declarada (R$)</label>
		            <input type="text" class="form-control real"
					value=<fmt:formatNumber value="${JobVerba}" pattern="#,##0.00"/>
		            name="verba1" style="width:135px">
		 			
		 
		        </div>
		  		      
		        <div class=" form-inline col-md-8 tira-padding">
		            
		             <hr> 
		              <div class="form-group">
		               <button class="btn btn-danger"  type="submit">Salvar Job</button>
		              </div>
		
		            <!-- <div class="checkbox col-md-offset-2">
		              <label>
		                <input type="checkbox" name="">&nbsp&nbspNotificar equipe Locco&nbsp
		              </label>
		            </div> -->
					
	          </div>
	          </form>			    
   </div>	

   <div class="col-md-4 col-xs-4 painel">
  
  		
<form action="cadastraStatus" method="post" style="padding: 10px 13px;" id="cadastraStatus">
				
			<input type="hidden" name="idJobTransiente" value="${idJob}">	
			<input type="hidden" name="idEmpTransiente" value="${idEmp}">	

		    <div class="form-group">
	
		          <label for="">&nbsp</label>
		          <select name="idStatusTransiente" id="" class="form-control">
					 <option value="${idStatus}">${statusAtual}</option>
					
				   		<c:forEach items="${status}" var="status" varStatus="id">
	                	
		                	<c:choose>
		                	
			                	<c:when test="${empty statusAtual}" >
									<option value="7">Novo Job</option>
							    </c:when>
			                
			                    <c:when test="${status.estatus == statusAtual}">
			                   		
			                    </c:when>
			                   
			                    <c:otherwise>
					  			    <option value="${status.idEstatus}">${status.estatus}</option>
							    </c:otherwise>				
						   
						   </c:choose>
					   
					</c:forEach>	
		           
		          </select>
		        </div>


<div style="background: #ecf0f1;padding: 8px;border-radius: 5px;">		        
		        <div class="form-group">
		         <label for="">Produtor 1</label> 
		          <select name="idUsuarioTransiente" id="" class="form-control">
		           	
		           	<option value="${statusIdUsuario}">${statusUsuario}</option>
		            	
		            <c:forEach items="${usuariosHabilitados}" var="usuarios">
		            	<c:choose>
		            		<c:when test="${usuarios.nome == statusUsuario}">
			                   		
			                </c:when>
		            
		            		<c:otherwise>
								<option value="${usuarios.idUsuario} ">${usuarios.nome}</option>
					        </c:otherwise>	
					
					
					
						</c:choose>
					</c:forEach>
		          </select>
		        </div>  
		        
		        <div class="form-group">
		         <label for="">Produtor 2</label> 
		          <select name="idProdutor2Transiente" id="produto2" class="form-control">
		           	
		           	
		           		<c:if test="${empty produtor2id}">
							<option value="selecione">---------------------------------------------</option>		           		
		           		</c:if>
		           		
		           		<c:if test="${not empty produtor2id}">
			           		<option value="${produtor2id}">${produtor2nome}</option>
		           		</c:if>
		           		
		            	
		            <c:forEach items="${usuariosHabilitados}" var="usuarios">
		            	<c:choose>
		            		<c:when test="${usuarios.nome == statusUsuario}">
			                   		
			                </c:when>
		            
		            		<c:otherwise>
								<option value="${usuarios.idUsuario} ">${usuarios.nome}</option>
					        </c:otherwise>	
					
					
					
						</c:choose>
					</c:forEach>
		          </select>
		        </div>  
</div>		        
		        
		        
		        
		        
		    <div class="row">
		          <div class="col-md-5 form-group" style="padding-left: 6px;">
		            <label for="">Prazo</label>
		            <input type="text" name="prazoEstatusData" class="form-control data" placeholder="__/__/____"
		            value="<fmt:formatDate value="${prazoConclusao}" pattern="dd/MM/yyyy"/>">
		          </div>
		
		          <div class="col-md-5 form-group">
		   	        <label for="">&nbsp</label>
		            <input type="time" class="form-control" name="prazoEstatusHora" value="<fmt:formatDate value="${prazoConclusao}" pattern="HH:mm"/>">
		          </div>
	       </div>
		
		        <div style="width:100%;height:12px"></div>
		        
		        <div class="form-group">
		          <label for="">Observação:</label>
		          <textarea class="form-control" name="jobStatusObservacao" rows="4">${observacao}</textarea>
		        </div>
		        
				<c:if test="${prazoDia	< 0}">
			         <div class="alert alert-danger" role="alert">
				 		<p>Atrasado em ${prazoDia} dias, ${prazoHora} horas e ${prazoMinuto} Minutos para a proposta.</p>
	  		         </div> 
				</c:if>
				
				<c:if test="${(prazoDia	== 0) && (prazoHora <= 12)}">
			         <div class="alert alert-success" role="alert">
				 		<strong>Proposta para ser apresentada HOJE</strong>
				 		<p>Faltam: ${prazoDia} dias, ${prazoHora} horas e ${prazoMinuto} Minutos para a proposta.</p>
	  		         </div> 
				</c:if>
				
				
				
				<c:if test="${(prazoDia	>= 0)&&(prazoDia	< 1)&&(prazoHora > 12)}">
			         <div class="alert alert-warning" role="alert">
				 		<p>Faltam:${prazoDia} dias, ${prazoHora} horas e ${prazoMinuto} Minutos para a proposta.</p>
	  		         </div> 
				</c:if>





				<c:if test="${(prazoDia >= 1 ) && (prazoDia <= 5)}">
			         <div class="alert alert-warning" role="alert">
				 		<p>Faltam:${prazoDia} dias, ${prazoHora} horas e ${prazoMinuto} Minutos para a proposta.</p>
	  		         </div> 
				</c:if>
				
				
				<c:if test="${prazoDia >= 6}">
			         <div class="alert alert-info" role="alert">
				 		<p>Faltam : ${prazoDia} dias, ${prazoHora} horas e ${prazoMinuto} Minutos para a proposta.</p>
	  		         </div> 
				</c:if>

				
				<div class="ultimaAtualizacaoStatus" id="statusAtualizacao" style="display: none;">
					<span style="font-size: 12px;color: #ecf0f1">
						Última atualização <fmt:formatDate value="${ultimaAtualizacao}" pattern="dd/MM/yyyy HH:mm" /><br />
						Por ${criadoPor.nome} 
					</span>
		        </div>
		        
		        
		        <div class="form-group">
		          <div class="form-group">
		                <input class="btn btn-danger" type="submit" value="Salvar alteração de Status">
		                
		                <div  style="width: 40px;float: right;margin-top: -3px;">
		                
		                <i class="fa fa-info infoStatus" id="atualizaUsuario"></i>
		                </div>
		                
		              </div>
		        </div>
		    </form>
         </div>
   			
		<div class="col-md-4"> 		
		   	<form action="notificaEquipe" id="notificaEquipe" style="padding: 10px 13px;border-top: 1px solid #D7D1C8;
		   															 border-bottom: 1px solid #D7D1C8;">
		   	
		   		 <input type="hidden" name="idJobJob" value="${idJob}">
		   		 <input type="hidden" name="idEmp" value="${idEmp}">
		   		 <input type="hidden" name="jobTitulo" value="${jobTitulo}">
		   		 <input type="hidden" name="codJob" value="${JobcodJob}">
		   		 <input type="hidden" name="descricao" value="${JobDescricao}">
		   		 
		   		 
		   		 	
		   		 <div class="checkbox" style="background: #fff">
		             <label>
		                <input type="checkbox" name="multiploNotifica" id="notificar">&nbsp&nbspNotificar equipe Locco&nbsp
		             </label>
				</div>
				
				
					
			
				<div class="notificar" style="display:none">
				<%-- <select name="idUsuarioNotifica" multiple class="form-control" style="display: block; height: 170px">
						<c:forEach items="${usuariosHabilitados}" var="usuarios">
								<option value="${usuarios.idUsuario}">${usuarios.nome}</option>
						</c:forEach>
				</select>	 --%>
				<div class="divisor"></div>
				<div class="form-group">
					<button id="button_notifica" class="btn btn-danger">Notificar Equipe</button>
				</div>
				</div>
				<img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader_notifica display-none" alt="loading" />
			</form>	
			


			<!-- <div class="progress col-md-11"	style="padding-left: 0; margin-left: 14px;">
				<div class="progress-bar progress-bar-success progress-bar-striped"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 40%">
					<span class="sr-only">40% Complete (success)</span>
				</div>
			</div> -->

		</div>

		

		<div class="col-md-4" style="  padding: 10px 30px;">
	   
	   <c:if test="${empty notifica}">
		   <hr>	
		    Equipe não notificada
		   <hr>	
	   </c:if>
	   
	   
	   
	   <c:if test="${not empty notifica}">
	   	   <p>Equipe Notificada por email</p>	
		   <table class="table table-bordered">
		   	<tr class="active">
		   		<th>Data da notificação</th>
		   	</tr>
		   			
		   	<c:forEach items="${notifica}" var="notifica">
		   		<tr style="font-size: 12px;">
		   			<td style="padding: 3px 7px;"><fmt:formatDate value="${notifica.enviadoEm.time}" pattern="dd/MM/yyyy HH:mm"/> </td>
		   	    </tr>
		   	</c:forEach>
		   	
		   </table> 
	  </c:if>	   

	   </div>
   		
   		
   
   
   </div>

<!-----------------------------------------------------------------------------------------------------------------------------------------------  -->
<div class="row painel estilo-painel-job" style="border-right: 1px solid #ddd;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
	
		<div class="col-md-4" style="padding-left: 0;padding: 1px;">
			<h5 style="margin-left: 18px;"><i class="glyphicon glyphicon-calendar icones"></i>DATA E LOCAL DO EVENTO</h5>
	    </div>
	    
	    <div class="col-md-4">
			
	    </div>

		<div class="col-md-4">
			<button class="btn btn-link" id="chamaEvento" style="margin-top: 3px;">[+] Inserir data e local do evento</button>
	    </div>
	
	</div>
	
	
	<div class="col-md-10">

		<form action="cadLocalEvento" method="post" id="evento"
			style="display: none">
			<input type="hidden" name="JobId" value="${idJob}"> <input
				type="hidden" name="idEmpresa" value="${idEmp}">
			


			<div class="col-md-12" style="height: 23px"></div>
			<div class="col-md-5 form-group tira-padding">
				<div class="form-group">
					<label for="">Nome</label> <input type="text" name="localEventoNome" class="form-control" maxlength="80" id="localEventoNome">
				</div>
				<div class="form-group">
					<label for="">Local do Evento</label> <input type="text"
						name="local" class="form-control" placeholder="Ex.: São Paulo">
				</div>

				<div class="form-group">
					<label for="">Endereço</label> <input type="text"
						name="localEventoEndereco" class="form-control">
				</div>
				<div class="form-group">
					<label for="">Observação</label>
					<textarea name="localEventoObservacoes" class="form-control"
						rows="3"></textarea>
				</div>
				<div class="form-inline">
					<hr>
					<div class="form-group">
						<button type="submit" class="btn btn-danger">Salvar Local
							do evento</button>
					</div>
					<div class="form-group">
						<button type="reset" class="btn btn-primary chamaEvento">Cancelar</button>
					</div>
				</div>
			</div>
			<div class="divisor"></div>
			<div class="col-md-6">
				<div class="col-md-8" style="height: 23px"></div>
				<div class="form-inline">
					<div class="form-group col-md-12 tira-padding">
						<label for="">Qtd Pax/Dias</label> <input type="text"
							name="localEventoQtdPessoas" class="form-control col-md-offset-1"
							style="width: 65px">
					</div>
				</div>
				<div class="form-inline">
					<div class="col-md-8" style="height: 10px"></div>
					<div class="form-group">
						<label for="">Data de
							Inicio&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label> <input
							type="text" name="dataInicio" class="form-control data"
							style="width: 110px" placeholder="__/__/____">
					</div>
					<div class="form-group">
						<input type="time" class="form-control" name="horaInicio"
							placeholder="Hora">
					</div>
				</div>
				<div class="form-inline">
					<div class="col-md-8" style="height: 10px"></div>
					<div class="form-group">
						<label for="">Data de Término&nbsp&nbsp&nbsp</label> <input
							type="text" name="dataTermino" class="form-control data"
							style="width: 110px" placeholder="__/__/____">
					</div>
					<div class="form-group">
						<input type="time" name="horaTermino" class="form-control"
							placeholder="Hora">
					</div>
				</div>
			</div>
		</form>

	</div>
	
	
	
	<div class="col-md-12" style="margin-top:15px">
		
		<c:choose>
				<c:when test="${empty localEvento}">
					
				</c:when>
			
				<c:when test="${not empty localEvento}">
								<div class="form-group">
									<table class="table table-bordered" style="font-size: 12px;">
										<tr class="active">
											<th></th>
											<th>Nome</th>
											<th>Qtd Pes.</th>
											<th>Início</th>
											<th>Término</th>
											<th>Local do Evento</th>
											<th>Endereço</th>
											<th>Obs.</th>
										</tr>
										<c:forEach items="${localEvento}" var="evento" varStatus="id">
											<tr>
												<td>${id.count}</td>
												<td><a href="#"
													onclick="modalLocalEvento('ExibeLocalEvento?idLocalEvento=${evento.idLocalEvento}');"
													>${evento.localEventoNome}</a></td>
				
												<td>${evento.localEventoQtdPessoas}</td>
												<td><fmt:formatDate value="${evento.localEventoDataInicio}"
														pattern="dd/MM/yyyy HH:mm" /></td>
												<td><fmt:formatDate
														value="${evento.localEventoDataTermino}"
														pattern="dd/MM/yyyy HH:mm" /></td>
												<td>${evento.local}</td>
												<td>${evento.localEventoEndereco}</td>
												<td style="word-break: break-all ">${evento.localEventoObservacoes}</td>
											</tr>
										</c:forEach>
									</table>
								</div>
				</c:when>
			</c:choose>	
			<div class="divisor"></div>
	</div>
</div>
<!---------------------------------------------------- Fim data local evento -----------------------------------------------------------------  -->

<!-- --------------------------------------------------------- Anexo -------------------------------------------------------------------------- -->
<div class="row painel estilo-painel-job" style="border-right: 1px solid #ddd;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
	
		<div class="col-md-4" style="padding-left: 0;padding: 1px;">
			<h5 style="margin-left: 18px;"><i class="glyphicon glyphicon-paperclip icones"></i>  ANEXO (${qtdAnexos})</h5>
	    </div>
	    
	    <div class="col-md-4">     
			
	    </div>

		<div class="col-md-4">
			<button class="btn btn-link" style="margin-top: 3px;" id="anexo">[+] Inserir anexo do JOB</button>
	    </div>
	
	</div>

	<div class="col-md-12" style="margin-top:15px">
	
		<c:import url="anexo/anexo_job.jsp?AnexoOrigem=job&display=none&JobCodJob=${JobcodJob}" />
	
	</div>
	
	<div class="col-md-12" style="margin-top:15px" id="anexosAjax">

		<c:choose>
			<c:when test="${empty anexosJob}">

			</c:when>

			<c:when test="${not empty anexosJob}">
				<div class="form-group">
					<table class="table table-bordered" style="font-size: 12px;">
						<tr class="active">

							<th>Cod.</th>
							<th>Título</th>
							<th>Depto.</th>
							<th>Status</th>
							<th>Criado Por</th>
							<th>Em</th>
						
				<security:authorize access="hasRole('ROLE_ADMIN')"> 
 							<th style="text-align: center;">Excluir</th>
				</security:authorize>	
						
						
						
						
						</tr>



						<c:forEach items="${anexosJob}" var="anexos">
							<tr>
								<td>${anexos.anexoCod}</td>

								<td>
									<a href="${anexos.anexoDiretorio}${anexos.anexoArquivo}">${anexos.anexoTitulo}</a>
								</td>

								<td>${anexos.anexoarea}</td>
								<td>${anexos.anexoStatus}</td>
								<td>${anexos.criadopor.nome}</td>
								<td><fmt:formatDate value="${anexos.criadoData.time}"
										pattern="dd/MM/yyyy HH:mm" /></td>
							
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<td class="" style="text-align: center;padding: 5px;">
			  <i class="glyphicon glyphicon-remove excluir" style="font-size: 18px;color: #c0392b;cursor: pointer;" onclick="apagarAnexo(${anexos.idAnexo}, ${idJob});"></i>
		</td>		
	</security:authorize>

							</tr>
						</c:forEach>


					</table>
				</div>
			</c:when>
		</c:choose>
	</div>
</div>

<!-- ------------------------------- Inserir anexo ---------------------------- -->


<!-- -------------------------------------------------------------------------- -->

<div class="col-md-12" style="height:10px"></div>

<!-- -----------------   Notificar equipe locco ------------------------------- -->
	
<!-- -------------------------------------------------------------------------- -->


<div class="col-md-12" style="height:10px"></div>

<!--------------------------- Estrategia -------------------------------------- -->

 <c:import url="job_estrategia.jsp" />

<%-- </c:forEach> --%>

<!----------------------------------------------------------------------------- -->

<div class="col-md-12" style="height:10px"></div>

<!-- ------------------------ Demanda Interna ------------------------------------->

<c:import url="demanda/demanda.jsp" />

<!-- --------------------------------------------------------------------------- -->



<!-- ------------------------------- Interacao --------------------------------- -->

<c:import url="interacao/interacao.jsp" />

<!-- --------------------------------------------------------------------------- -->




  <br><br><br><br><br><br><br><br>


<!-- Modal Local Evento -->  

<div class="modal fade " id="ModalEvento">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Data e Local do Evento</h4>
        <img src="<c:url value="resources/images/loader.GIF" />" width="80" height="80" class="loader" alt="loading" />
      </div>
        <div class="modal-body">
      
      		
      	
       </div>
  </div>
 </div>
</div>

</div>

<!-- Modal Novo Contato -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title  fontLight" id="myModalLabel" >ADICIONAR CONTATO</h4>
      </div>
      <div class="modal-body">
<!-- --------------------------------------------------------------------------------------------------------------------------------------- -->       
    		<div class="contato">
				<div id="ContatoRecebeInput">        
				         </div>
				           <div class="form-inline">
				             <input type="checkbox" name="habilitado" checked="checked" id="ativoDesativado" class="checkbox">&nbsp&nbspAtivo
				       	  <div class="divisor"></div>	
				           </div>
				           
				           <input type="hidden" name="idEmp" value="${idEmp}" id="idEmNovo">
				           	
				           <div class="form-group">
				             <input type="text" class="form-control" name="" placeholder="Nome do Contato" id="contatoNovo">
				           </div>
				
				           <div class="form-group">
				             <input type="text" class="form-control" name="cargo" placeholder="Cargo" id="cargoNovo">
				           </div>
					
				           <div class="form-group">
				             <input type="text" class="form-control" name="obs" placeholder="Observações">
				           </div>	
				
						   <div class="col-md-12" style="background-color:#f1f1f1;border-radius:3px;margin-bottom: 10px;padding-top: 5px">
					  	   <div class="retornoContato" style="  margin-top: 5px;">
				   
				           	<input type="hidden" id="idContatoCriado" value="">	
  				           </div>	
				
				             <div class="form-inline" > 
				               <a href="" class="btn btn-link" onclick="contato('telefone');" style="padding-left: 0" id="linkTelefone">Telefone</a>
				               |&nbsp&nbsp&nbsp<a href="" class="btn btn-link" onclick="contato('email');">Email</a>
				             </div>	
				            
				            
				            <div id="phoneContato" style="display:none">
				        	   <div class="col-xs-6" style="padding-left: 0">
				           	  	<input type="text" class="form-control phone"  name="telefoneNovo" placeholder="Telefone" id="phone">
							  </div>  		            
				        			
				     		  <div class="col-xs-6" style="padding-right: 0">      
					            <input type="text" class="form-control"  name="descricaoNovo" placeholder="Descrição" id="descricaoNovo">
				    		  </div>
				              
				              <div class="form-inline">
				                <a href="#" class="btn btn-link" style="padding-left: 0" onclick="SalvaContatoAjax('telefone');">Salvar</a>
				                <a href="#" type="reset" class="btn btn-link" onclick="contato('cancelar');">Cancelar</a>
				              </div>	
						</div>
						
						<div id="ContatoEmail" style="display:none">
				        	   <div class="col-xs-7" style="padding-left: 0">
				           	  	<input type="text" class="form-control"  name="email" placeholder="Email" id="emailNovo">
							  </div>  		            
				        			
				     		  <div class="col-xs-5" style="padding-right: 0">      
					            <input type="text" class="form-control"  name="telefone" placeholder="Descrição">
				    		  </div>
				              
				              <div class="form-inline">
				                <a href="#" class="btn btn-link" style="padding-left: 0" onclick="SalvaContatoAjax('email');">Salvar</a>
				                <a href="#" type="reset" class="btn btn-link" onclick="contato('cancelar');">Cancelar</a>
				              </div>	
						</div>
			  	      </div>
				    </div>
<!-- --------------------------------------------------------------------------------------------------------------------------------------- -->       
	      </div>
	      <div class="col-md-12" id="novoContatoCriado">
	      	<p class='sucess' style="display:none;font-size: 12px">
	      		&nbspContato Criado.
	      	Feche ou adicione mais telefones e emails.</p>
	      </div>
	      <div class="modal-footer" style="border: none">
	      
	        <button class="btn btn-danger" disabled="disabled" id="ConcluiContato" onclick="location.reload();">Concluir</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close" onclick="location.reload();">Fechar</button>
	      </div>
	    </div>
  </div>
</div>


<!-- ----------------------------------------------------------- Modal Edita contato --------------------------------------------------------- -->
<!-- Modal -->
<div class="modal fade" id="ModalEdita" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button> -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
            <span aria-hidden="true" style="font-size: 50px;font-family: 'OpenSansLight';font-weight: normal;">&times;</span>	
        </button>
        <h4 class="modal-title fontLight" id="myModalLabel">EDITAR CONTATO</h4>
      </div>
      <div class="modal-body listacomunicadores">
			
			<!-- ------------------------------------------------------------------------------------------------------------ -->
		
			
			<!-- -----------------------------------------------------------------------------------------------------------  -->

      </div>
    </div>
  </div>
</div>

<c:import url="../_comum/footer.jsp" />




