<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../../_comum/header.jsp" />
<style type="text/css">
.prospeccao{background: #f1f1f1;}
.inputCheckbox{width: 15px;height: 15px;vertical-align: sub;margin-bottom: 10px !important;}
.boxFiltro{background: #fff;padding: 15px;box-shadow: 5px 5px 5px #ccc;margin-right: 12px;height: 205px;}
.larguraInteracao{width: 120px;}
</style>

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

<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
		  <ol class="breadcrumb">
		    <li><a href="index.html">Menu</a></li>
		    <li><a href="<c:url value="/prospeccoes" />">Prospecção</a></li>
		    <li class="active">Edição de Prospecção</li>
		  </ol>					
</div>

<c:if test="${msg.get(0) == 'sucesso'}">
	<div class="msg-email msg-sucesso-email" id="msg-sucesso-email" >${msg.get(1)}</div>
	<script type="text/javascript">emailSucesso();</script>
</c:if>	

<c:if test="${msg.get(0) == 'erro'}">
	<div class="msg-email msg-erro-email" id="msg-erro-email">${msg.get(1)}</div>
	<script type="text/javascript">emailErro();</script>
</c:if>



<div style="height:50px;margin-left: 45px;">
	
	<div class="col-md-12" style="margin: 10px 0px 25px -14px;">
		<span style="font-family: 'OpenSansLight';font-size: 25px;margin-right: 46px;">EDITA PROSPECÇÃO</span>
	</div>
</div>

<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 1600px;border-top: 1px solid #ccc;padding: 35px 60px;">


<div class="col-md-12" style="box-shadow: 0px 1px 14px 3px #ccc;border: 1px solid #e6e6e6;padding: 0px 15px 30px 15px;">
	<div class="row col-md-12" style="border-bottom: 1px solid #D7D1C8;margin-bottom: 15px;margin-left: 10px;">
			<h4 style="margin-left: -8px;"><i class="glyphicon glyphicon-bookmark icones"></i>PROSPECÇÃO</h4>
	</div>

<form action="editaProspeccao" method="post">

	<input name="idProspeccao" type="hidden" value="${prospeccao.idProspeccao}"/>
	<div class="row">
		<div class="col-md-3">	
			<span>Título</span>
			<input name="titulo" type="text" class="form-control input-480px" value="${prospeccao.titulo}"/>
		</div>	
	</div>	
	
	<div class="row">
		<div class="col-md-5">	
			<span>Cliente</span><br>
			<select name="idEmpresaTrans" id="sempresaFornecedor" class="form-control input-480px" onchange="procuraContato(this.value);">
		            <option value="${prospeccao.idEmpresa.idEmpresa}">${prospeccao.idEmpresa.empresa}</option>
		         <c:forEach items="${empresas}" var="empresas">
		            <option value="${empresas[0]}">${empresas[1]}</option>
		         </c:forEach>
			</select>
		</div>	
		<div class="col-md-3" style="padding: 25px 0 0 50px;">
			<a onclick="insereNovoFornecedor();" class="btn btn-link">Inserir</a> | 
			<a onclick="editaFornecedor();" class="btn btn-link">Editar</a>
		</div>
	</div>
		
	<div class="row">
		<div class="form-group col-md-8">
		<label for="">Contatos</label><br>
			<img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader display-none" alt="loading" />
	        <div class="divisor"></div>
			<div class="info-contato" style="height: 10px;" >
				<div class="col-md-8 fundo" style="height:250px;overflow: overlay;    border: 1px solid #ccc;">
		    		<div class="col-md-8" style="padding: 0">   
			       		<c:forEach items="${contato}" var="contato">
			                <a href="#"  onclick="pegaComunicadoresPorIdContato(${contato.idContato})" data-toggle="modal" data-target="#ModalEdita">${contato.contato}</a><br>
			        		 <c:forEach items="${contato.comunicador}" var="comunicador">			
				           	   &nbsp&nbsp<span class="cor">${comunicador.comunicador}</span><br>
				             </c:forEach>
			            	<div class="divisor-fino"></div> 
			           </c:forEach> 
		 		  </div>
				  <div class="col-md-4">
					  <a href="" data-toggle="modal" data-target="#myModal">Adicionar contato</a>
				  </div>     
		      </div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-5">	
			<span>Probabilidade</span>
			<select name="probabilidade" class="form-control input-120px">
				<option value="${prospeccao.probabilidade}">${prospeccao.probabilidade}%</option>
				<option value="100">100%</option>
				<option value="90">90%</option>
				<option value="75">75%</option>
				<option value="50">50%</option>
				<option value="25">25%</option>
				<option value="0">0%</option>
			</select>
		</div>	
	</div>	
	
	<div class="row">
	<br>
		
		<c:if test="${prospeccao.concluido == false}">
			<div class="col-md-5">	
				<button type="submit" class="btn btn-danger">Salvar Prospeccção</button>
				<a href="<c:url value="concluiProspeccao?idProspeccao=${prospeccao.idProspeccao}" />" class="btn btn-conclui-lista">Concluir Prospeccção</a>
			</div>
		</c:if>

		<c:if test="${prospeccao.concluido == true}">
			<div class="col-md-5">	
				<a href="<c:url value="cancelaConcluiProspeccao?idProspeccao=${prospeccao.idProspeccao}" />" class="btn btn-primary">Cancelar Concluir Prospeccção</a>
			</div>
		</c:if>
	</div>
		
	
</form>	
	
</div>



<div class="col-md-12" style="box-shadow: 0px 1px 14px 3px #ccc;border: 1px solid #e6e6e6;margin-top: 15px;">
	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
		
		
		<c:set var="cont" value="0" />
		<c:forEach items="${interacao}" var="interacao" varStatus="contador">
			<c:set var="cont" value="${cont + 1}" />
		</c:forEach>
		<div class="col-md-4" style="padding-left: 0;padding: 1px;">
			<h4 style="margin-left: 18px;"><i class="glyphicon glyphicon-retweet icones"></i>INTERAÇÃO (${cont})</h4>
	    </div>
	    
	    <div class="col-md-4">
			
	    </div>

	
		<div class="col-md-4">
			<button class="btn btn-link" id="prospectInteracao" style="margin-top: 3px;">[+] Inserir interação</button>
	    </div>
	
	</div>
	
	<div class="col-md-6" style="margin-top:15px">

		<form action="salvaInteracao" class="formInteracaoClass" id="formInteracao" method="post"
			style="padding: 10px 10px 20px 0; margin-bottom: 30px;display:none" >

			<input type="hidden" name="interacaoOrigem" value="prospeccao" />
			<input type="hidden" name="idProspeccaoTrans" value="${prospeccao.idProspeccao}">
			<input type="hidden" name="automatico" value="0">

			<div class="row">
				<div class="form-group">
					<label for="  ">Descrição da Interação</label>
					<textarea class="form-control null" id="intercaoTexteArea" name="interacao" rows="5"></textarea>
					<span class="errors" style="display: none"></span>
				</div>
			</div>

			<div class="row">
				<div class="checkbox">
					<label>
					  <input type="checkbox" name="interno" id="internaInteracao"> Interação interna
					</label>
				</div>
			</div>

			<div class="row">

				<select name="idContatoContato" id="contatoInteracao" class="form-control" style="display: block">
					<c:forEach items="${contato}" var="contato">
						<option value="${contato.idContato}">${contato.contato}</option>
					</c:forEach>

				</select>
				<select id="usuarioInteracao" class="form-control" style="display: none">
					<c:forEach items="${usuariosHabilitados}" var="usuarios">
						<option value="${usuarios.idUsuario}">${usuarios.nome}</option>
						<%-- <input type="hidden" name="idUsuarioInterno" value="${usuarios.idUsuario}">      --%>
					</c:forEach>
				</select>
			</div>
			<div class="divisor-fino"></div>

			<div class="row">
				<div class="form-group col-md-3 tira-padding">
					<label for="">Forma de Contato</label>
					<select class="form-control" name="interacaoForma">
						<option value="Telefone">Telefone</option>
						<option value="Email">Email</option>
						<option value="Site">Site</option>
						<option value="Visita">Visita</option>
						<option value="Outro">Outro</option>
					</select>
				</div>


				<div class="form-group col-md-3 tira-padding" style="margin-left: 10px;">
					<label for="">Próxima Interação</label>
					<input type="text" class="form-control data" id="dataInteracao" name="pd" placeholder="__/__/____">
				</div>

				<div class="form-group col-md-3 tira-padding" style="margin-left: 10px;">
					<label for="">Hora</label> <input type="time" class="form-control" id="horaInteracao" name="ph">
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
					<!-- <label> <input type="checkbox" name=""> Inserir como Tarefa
					</label>&nbsp&nbsp&nbsp  -->
					<label>
						<input type="checkbox" id="agendarInteracao" name="agendar"> Não Agendar
					</label>
				</div>

				<div class="checkbox">
					<label> <input type="checkbox" id="comunicacaoInterna" name=""> Comunicar Outros via Email
					</label>
				</div>

				<select name="multiplo" multiple id="comunicaUsuario" class="form-control" style="display: none; height: 170px">
					<c:forEach items="${usuariosHabilitados}" var="usuarios">
						<option value="${usuarios.idUsuario}">${usuarios.nome}</option>
					</c:forEach>
				</select>

			</div>


			<div class="row">
				<div class="divisor-fino"></div>
				<button type="submit" id="button_interacao" class="btn btn-danger">Salvar Interação</button>
				<div class="divisor-fino"></div>
				<img src="<c:url value="resources/images/loader.GIF" />" width="30"	height="30" class="loader display-none" alt="loading" />
			</div>


		</form>


	</div>
	
	
	<div class="col-md-12" style="margin-top:15px">
		<c:choose>
			<c:when test="${empty interacao}">
				
			</c:when>

			<c:when test="${not empty interacao}">

				<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
					<tr class="active">

						<th>Interação</th>
						<th class="larguraInteracao">Contato</th>
						<th class="larguraInteracao">Usuário</th>
						<th class="larguraInteracao">Data</th>
						<th class="input-60px">Forma</th>
						<th class="larguraInteracao">Prox. Interação</th>

					</tr>
					<c:forEach items="${interacao}" var="interacao" varStatus="id">

						<tr>
							<td>${interacao.interacao}</td>

							<td>
							    <c:if test="${interacao.interno == true}">
										${interacao.idUsuarioInterno.nome}
								</c:if>
								<c:if test="${interacao.interno == false}">
										${interacao.idContato.contato}
								</c:if>
							</td>

							<td>${interacao.idUsuario.nome}</td>
							<td><fmt:formatDate value="${interacao.dataInteracao.time}"
									pattern="dd/MM/yyyy HH:mm" /></td>
							<td>${interacao.interacaoForma}</td>
							<td>
								<c:if test="${interacao.dataProximaInteracao  == '0001-01-01 00:00:00.0'}">
								
								</c:if>
								<c:if test="${interacao.dataProximaInteracao  != '0001-01-01 00:00:00.0'}">
									<c:if test="${empty interacao.dataProximaInteracao}">
										Não agendada
									</c:if> <c:if test="${not empty interacao.dataProximaInteracao}">
										<fmt:formatDate value="${interacao.dataProximaInteracao}" pattern="dd/MM/yyyy HH:mm" />
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	  </div>
   </div>
</div>


		<div class="col-md-12 alpha60 div-confirmacao" id="editaClienteProspect" style="left: 0;position: fixed">
			<c:import url="../editaClienteProspect/editaClienteProspect.jsp" />
			</div>
		</div>
		
		<div class="col-md-12 alpha60 div-confirmacao" id="novoFornecedor" style="left: 0;position: fixed">
			<c:import url="../novoClienteProspect/novoFornecedor.jsp" />
			</div>
		</div>
		
		<c:import url="modalEditaContato/modalEditaContato.jsp">
			<c:param name="idEmpresa" value="${prospeccao}"></c:param>
		</c:import>
		
<!-- ---------------------------------------------------------------------------------  -->		
		



<!-- ---------------------------------------------------------------------------------  -->		
		
		
		
		
<c:import url="../../_comum/footer.jsp" />	
<%-- <script type="text/javascript" src="<c:url value="resources/js/fornecedor.js" />"></script> --%>
<script type="text/javascript" src="<c:url value="resources/js/prospeccaoNovoCliente.js" />"></script>