   <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <c:import url="../../_comum/header.jsp" />
   <c:forEach items="${demanda}" var="demanda">
      <c:set var="titulo" value="${demanda.internoTitulo}" />
      <c:set var="idInterno" value="${demanda.idInterno}" />
      <c:set var="idJob" value="${demanda.idJob.idJob}" />
      <c:set var="empresa" value="${demanda.idJob.empresa.empresa}" />
      <c:set var="idEmp" value="${demanda.idJob.empresa.idEmpresa}" />
      <c:set var="codInterno" value="${demanda.codInterno}" />
      <c:set var="internoDescricao" value="${demanda.internoDescricao}" />
      <c:set var="prazoConclusao" value="${demanda.prazoConclusao}" />
      <c:set var="idResponsavel" value="${demanda.responsavel.idUsuario}" />
      <c:set var="nomeResponsável" value="${demanda.criadoPor.nome}" />
      <c:set var="responsavel" value="${demanda.responsavel.nome}" />
      <c:set var="CriadoData" value="${demanda.criadoData}" />
      <c:set var="concluidoPor" value="${demanda.concluidoPor.nome}" />
      <c:set var="concluidoData" value="${demanda.concluidoData}" />
   </c:forEach>
   <div class="col-md-12 bodyXY" style="height: 35px;">
					
		 <ol class="breadcrumb">
         <li><a href="#">Menu</a></li>
         <li><a href="listajob">Jobs</a></li>
         <li><a href="<c:url value="editaJob?idJob=${idJob}&idEmpresa=${idEmp}" />">Job Atual</a></li>
         <li class="active">Edita Demanda</li>
      </ol>
					
		</div>
    </div>
</div>
<br /><br />
   <div class="container">
   <div class="row painel estilo-painel-job">
      <div class="col-md-12  painel" style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
         <div class="col-md-4" style="padding-left: 0;padding: 1px;">
            <h4 style="margin-left: 18px;">DEMANDA INTERNA</h4>
         </div>
         <div class="col-md-4" style="padding-left: 0;padding: 1px;margin-left: 25px;
            margin-top: 2px;">
            <a href="<c:url value="editaJob?idJob=${idJob}&idEmpresa=${idEmp}" />" class="btn btn-default" >Voltar para o Job</a>
         </div>
      </div>
      <div class="col-md-10" id="estrategia" style="padding-left: 0">
         <form action="CadDemanda" method="post" class="col-md-7" id="formDemanda">
            <input type="hidden" name="JobId" value="${idJob}">
            <input type="hidden" name="idInterno" value="${idInterno}">
            <input type="hidden" name="internoTipo" value="internoCriacao">
            <input type="hidden" name="idEmp" value="${idEmp}" />
            <input type="hidden" name="concluido" value="0" />
            <div class="divisor"></div>
            <div class="col-md-12" style="background: #ccc; padding-left: 5px">
               <span>Interno</span> <span style="color: red; font-weight: bold">${codInterno}</span>
               <input type="hidden" name="codInterno" value="${codInterno}">
            </div>
            <div class="form-inline ">
               <span style="font-size: 16px">JOB - </span> <strong
                  style="font-size: 16px">${empresa} - ${titulo} </strong><br> <br>
            </div>
            <div class="form-group">
               <label for="  ">Item</label>
               <input type="text" name="internoTitulo" class="form-control" value="${titulo}" id="internoDemanda" maxlength="40">
            </div>
            <div class="form-group">
               <label for="  ">Linha Criativa</label>
               <textarea class="form-control" name="internoDescricao" rows="5" id="internoDescricaoDemanda">${internoDescricao}</textarea>
               <span class="errors_text"></span>
            </div>
            <div class="form-group">
               <label for="">Prazo para conclusão</label>
            </div>
            <div class="form-inline">
               <div class="form-group">
                  <input class="form-control input-data data" name="pConclusao"value="<fmt:formatDate value="${prazoConclusao}" pattern="dd/MM/yyyy"/>" placeholder="__/__/____">
               </div>
               <div class="form-group">
                  <input type="time" class="form-control input-data"
                  name="hConclusao" value="<fmt:formatDate value="${prazoConclusao}" pattern="HH:mm"/>"placeholder="00:00">
               </div>
            </div>
            <div class="form-group">
               <div class="col-md-12" style="height: 10px"></div>
               <label for="">Responsável</label> 
               <select name="idResponsavel"
                  class="form-control">
                  <option value="${idResponsavel}">${responsavel}</option>
                  <c:forEach items="${usuariosHabilitados}" var="usuarios">
                     <option value="${usuarios.idUsuario} ">${usuarios.nome}</option>
                  </c:forEach>
               </select>
               <div class="col-md-12 tira-padding">
                  <div class="checkbox">
                     <label> <input type="checkbox" name="notificaResponsavel"> Notificar
                     responsável
                     </label>
                  </div>
                  <p>
                     Criado por ${nomeResponsável} em 
                     <fmt:formatDate value="${CriadoData.time}" pattern="dd/MM/yyyy HH:mm"/>
                  </p>
                  <c:if test="${not empty concluidoPor}">
                     <p>
                        Concluido por ${concluidoPor} em 
                        <fmt:formatDate value="${concluidoData.time}" pattern="dd/MM/yyyy HH:mm"/>
                     </p>
                  </c:if>
               </div>
            </div>
            <div class="form-inline" style="padding-bottom: 20px;">
               <div class="form-group">
                  <c:if test="${empty concluidoPor}">	
                     <button class="btn btn-danger" id="button_demanda">Salvar	Demanda</button>
                  </c:if>
               </div>
         </form>
         <c:if test="${empty concluidoPor}">				
         <div class="form-group col-md-offset-2">
            <form action="concluirDemanda" method="post">
               <input type="hidden" name="outroId" value="${idJob}">
               <input type="hidden" name="outraEmp" value="${idEmp}" />
               <input type="hidden" name="codInterno" value="${idInterno}" />
               <button type="submit" class="btn btn-primary">Demanda Concluida</button>
            </form>				
         </div>
         </c:if>
         <c:if test="${not empty concluidoPor}">
         <div class="form-group">
         <form action="cancelaConcluirDemanda" method="post">
         <input type="hidden" name="outroId" value="${idJob}">
         <input type="hidden" name="outraEmp" value="${idEmp}" />
         <input type="hidden" name="codInterno" value="${idInterno}" />
         <button type="submit" class="btn btn-danger">Cancelar Conclusão</button>
         </form>				
         </div>
         </c:if>
         <div class="divisor-fino"></div>
         <div>
         <img src="resources/images/loader.GIF" width="30" height="30" class="loader display-none" alt="loading">		
         </div>
         </div>
      </div>
   </div>
   
   
   
   <div class="divisor"></div>
   <!-- ------------------------------- Anexos ----------------------------------------  -->
   <!-- ------------------------------- Anexos ----------------------------------------  -->
   <div class="row painel estilo-painel-job">
      <div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
         <div class="col-md-4" style="padding-left: 0;padding: 1px;">
            <h5 style="margin-left: 18px;"><i class="glyphicon glyphicon-paperclip icones"></i>ANEXOS</h5>
         </div>
         <div class="col-md-4">
         </div>
         <div class="col-md-4">
            <button class="btn btn-link" style="margin-top: 3px;" id="anexo">[+] Inserir anexo para demanda</button>
         </div>
      </div>
      <div class="col-md-12" style="margin-top:15px">
         <form action="recebeAnexo" id="recebeAnexo" method="post" enctype="multipart/form-data" class="anexo"
            style="display: none; padding-bottom: 20px;">
            <input type="hidden" name="idJob" value="${idJob}">
            <input type="hidden" name="anexoOrigem" value="internoCriacao">
            <input type="hidden" name="CodInterno" value="${codInterno}">
            <input type="hidden" name="idCriadoPor" value="40">
            <input type="hidden" name="alteradoPor" value="40">
            <input type="hidden" name="idCriadoPor" value="40">
            <input type="hidden" name="idEmp" value="${idEmp}">
            <div class="form-inline">
               <div class="form-group">
                  <input class="form-control" type="text" name="AnexoTitulo" style="width: 427px" placeholder="Nome do Anexo"><br />
               </div>
               <br />
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
            <div class="form-group">
               <div class="divisor"></div>
               <div class="col-md-4" style="padding-left: 0;width: 440px;">
					 <input type="file" class="filestyle" data-buttonBefore="true" name="NomeAnexo" id="fileAnexo">
				</div>
            </div>
            <div class="row">
               <div class="form-group">
                  <div class="col-md-12">
                     <div class="divisor"></div>
                     <button type="submit" class="btn btn-danger">Salvar Anexo</button>
                  </div>
               </div>
            </div>
         </form>
      </div>
      <div class="col-md-12" style="margin-top:15px">
         <c:choose>
            <c:when test="${empty AnexosinternoCriacao}">
            </c:when>
            <c:when test="${not empty AnexosinternoCriacao}">
               <div class="form-group">
                  <table class="table table-bordered" style="font-size: 12px;">
                     <tr class="active">
                        <th>Cod.</th>
                        <th>Título</th>
                        <th>Depto.</th>
                        <th>Status</th>
                        <!-- <th>Criado Por</th>
                           <th>Em</th> -->
                     </tr>
                     <c:forEach items="${AnexosinternoCriacao}" var="anexos">
                        <tr>
                           <td><a href="${anexos.anexoDiretorio}${anexos.anexoArquivo}" >${anexos.anexoCod}</a></td>
                           <td>${anexos.anexoTitulo}</td>
                           <td>${anexos.anexoarea}</td>
                           <td>${anexos.anexoStatus}</td>
                           <%-- <td>${anexos.criadopor.nome}</td>
                              <td><fmt:formatDate value="${anexos.criadoData.time}" pattern="dd/MM/yyyy HH:mm"/></td> --%>
                        </tr>
                     </c:forEach>
                  </table>
               </div>
            </c:when>
         </c:choose>
      </div>
   </div>
   <!-- ------------------------------- Anexos ----------------------------------------  -->
   <!-- ------------------------------- Anexos ----------------------------------------  -->
   <!-- ------------------------------- Interação ----------------------------------------  -->
   <!-- ------------------------------- Interação ----------------------------------------  -->
   <div class="row painel estilo-painel-job">
      <div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
         <div class="col-md-4" style="padding-left: 0;padding: 1px;">
            <h5 style="margin-left: 18px;"><i class="glyphicon glyphicon-retweet icones"></i>INTERAÇÃO</h5>
         </div>
         <div class="col-md-4">
         </div>
         <div class="col-md-4">
            <button class="btn btn-link" style="margin-top: 3px;" id="interacao">[+] Inserir interação para demanda</button>
         </div>
      </div>
      <div class="col-md-6" style="margin-top:15px">
         <form action="CadInteracao" id="formInteracao" method="post" class="interacao" style="padding: 10px 10px 20px 0; margin-bottom: 30px;display:none">
            <input type="hidden" name="idUsuarioUsuario" value="40"/>
            <input type="hidden" name="interacaoOrigem" value="internoCriacao"/>
            <input type="hidden" name="idJobJob" value="${idJob}">
            <input type="hidden" name="automatico" value="0">
            <input type="hidden" name="idEmp" value="${idEmp}" />
            <input type="hidden" name="tituloJob" value="${tituloJob}" />
            <input type="hidden" name="codInterno" value="${codInterno}" />
            <div class="row">
               <div class="form-group">
                  <label for="  ">Descrição da Interação</label>
                  <textarea class="form-control null" id="intercaoTexteArea"  name="interacao" rows="5" ></textarea>
                  <span class="errors" style="display:none"></span>
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
               <select name="idContatoContato" id="contatoInteracao" class="form-control" style="display:block">
                  <c:forEach items="${contato}" var="contato">
                     <option value="${contato.idContato}">${contato.contato}</option>
                  </c:forEach>
               </select>
               <select id="usuarioInteracao" class="form-control" style="display:none">
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
                     <option value="Telefone" >Telefone</option>
                     <option value="Email">Email</option>
                     <option value="Site">Site</option>
                     <option value="Visita">Visita</option>
                     <option value="Outro">Outro</option>
                  </select>
               </div>
               <div class="form-group col-md-3 tira-padding">	
                  <label for="">Próxima Interação</label>	
                  <input type="text" class="form-control data" id="dataInteracao" name="pd" placeholder="__/__/____">
               </div>
               <div class="form-group col-md-3 tira-padding">
                  <label for="">Hora</label>		
                  <input type="time" class="form-control" id="horaInteracao" name="ph" >	
               </div>
            </div>
            <div class="row">
               <div class="form-group col-md-10 tira-padding">
                  <span class="errors_data" style="display:none">Preencher a DATA e a HORA da próxima interação ou Não Agendar.</span>	
               </div>
            </div>
            <div class="row">
               <div class="checkbox">
                  <label>
                  <input type="checkbox" name="" > Inserir como Tarefa
                  </label>
                  &nbsp&nbsp&nbsp
                  <label>
                  <input type="checkbox" id="agendarInteracao" name="agendar"> Não Agendar
                  </label>
               </div>
               <div class="checkbox">
                  <label>
                  <input type="checkbox" id="comunicacaoInterna" name=""> Comunicar Outros via Email
                  </label>
               </div>
               <select name="multiplo"  multiple id="comunicaUsuario" class="form-control" style="display:none;height: 170px">
                  <c:forEach items="${usuariosHabilitados}" var="usuarios">
                     <option value="${usuarios.idUsuario}">${usuarios.nome}</option>
                  </c:forEach>
               </select>
            </div>
            <div class="row">
               <div class="divisor-fino"></div>
               <button type="submit" id="button_interacao" class="btn btn-danger">Salvar Interação</button>
            </div>
            <div class="divisor-fino"></div>
            <img src="
            <c:url value="resources/images/loader.GIF" />
            " width="30" height="30" class="loader display-none" alt="loading" />
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
                        <td>
                           <c:if test="${interacao.interno == true}">
                              ${interacao.idUsuarioInterno.nome}
                           </c:if>
                           <c:if test="${interacao.interno == false}">
                              ${interacao.idContato.contato}
                           </c:if>
                        </td>
                        <td>${interacao.idUsuario.nome}</td>
                        <td>
                           <fmt:formatDate value="${interacao.dataInteracao.time}"
                              pattern="dd/MM/yyyy HH:mm" />
                        </td>
                        <td>${interacao.interacaoForma}</td>
                        <td>
                           <c:if test="${empty interacao.dataProximaInteracao}">
                              Não agendada
                           </c:if>
                           <c:if test="${not empty interacao.dataProximaInteracao}">
                              <fmt:formatDate	value="${interacao.dataProximaInteracao}" pattern="dd/MM/yyyy HH:mm" />
                           </c:if>
                        </td>
                     </tr>
                  </c:forEach>
               </table>
            </c:when>
         </c:choose>
      </div>
   </div>
   <!-- ------------------------------- Fim Interação ----------------------------------------  -->
   <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
   <c:import url="../../_comum/footer.jsp" />