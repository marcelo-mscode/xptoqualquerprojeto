<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <script type="text/javascript" src="<c:url value="resources/js/jquery-ui.min.js" />"></script>
<script type="text/javascript" src="<c:url value="resources/js/syslocc.js" />"></script>
 --%>
 
 
<td colspan="4"> 
			 <div class="col-md-12" style="padding: 25px">
			 
<c:set value="${idLista}" var="idLista" />			 
			 

<c:forEach items="${producao}" var="producao">
	<c:set value="${producao.prazoPagamento}" var="prazoPagamento" />
	<c:set value="${producao.prazoEntrega}" var="prazoEntrega" />
	<c:set value="${producao.idProducao}" var="idProducao" />
	<c:set value="${producao.diasPrazo}" var="diasPrazo" />
	<c:set value="${producao.usuario.nome}" var="responsavel" />
	<c:set value="${producao.idProducao}" var="idProducao" />
	<c:set value="${producao.tipoPagamento}" var="tiposPagamento" />
	<c:set value="${producao.diferenca}" var="diferenca" />
	<c:set value="${producao.localEntrega}" var="localEntrega" />
	<c:set value="${producao.valorContratacao}" var="valorContratacao" />
	<c:set value="${producao.valorItem}" var="valorItem" />
	<c:set value="${producao.referenciaEntrega}" var="referenciaEntrega" />
	<c:set value="${producao.obs}" var="obs" />
</c:forEach>			 

<form action="salvaItem" method="post">
                   
                   <input type="hidden"  value="${idProducao}" name="idProducao" />
                   <input type="hidden"  value="${idLista}" name="idLista" id="idLista" />
                   <input type="hidden"  value="${idProdutoGrupo}" name="idProdutoGrupo" />
                   
                   
				 	<div class="row" id="fornecedorAtual">
				 		<div class="col-md-8" >
				 			
 				 		    <h4 style="font-family: 'OpenSansLight'">Fornecedor3333: <strong>${fornecedor.empresa}</strong>
 				 		    <i class="glyphicon glyphicon-pencil cor-lapis" onclick="efeitoToogle(fornecedorMuda);"
 				 		    style="cursor: pointer;font-size: 16px;margin-left: 15px;margin-top: 10px;position: absolute;"></i></h4>
				 		</div>
						<div class="col-md-4">
							<h4 style="font-family: 'OpenSansLight'">CNPJ: <strong>${fornecedor.cnpj}</strong></h4>	
						</div>	
				 	</div>
				 	
				 	<div class="row">
							<div id="fornecedorMuda" class="col-md-8 display-none" style="height: 117px;border: 1px solid #ddd;padding: 20px 20px 20px 10px;margin-bottom: 10px;border-radius: 5px;">
								<p>Trocar de Fornecedor</p>
									<input type="hidden" value="${idProdutoGrupo}" id="idProdutoGrupo">

										<select class="form-control input-180px" id="idEmpresa">
										
											<c:forEach items="${fornecedoresLista}" var="fornecedoresLista">
												<option value="${fornecedoresLista[0]}">${fornecedoresLista[1]}</option>
											</c:forEach>											
										
										</select>&nbsp&nbsp&nbsp 
									<a class="btn btn-danger" onclick="trocarFornecedor();">Trocar Agora</a><br>	
																
							</div>
					
					</div>				 	
				 	
				 	<div class="row">
	   			        <div class="col-md-12">
								<h4 style="font-family: 'OpenSansLight'">Data do Evento:
									 <strong>
									 	<c:if test="${empty InfoJobs.localEventoDataTermino}">
									 		<span style="color: red">Local e Data do evento não cadastrados</span>
									 	</c:if>
									 	
									 	<c:if test="${not empty InfoJobs.localEventoDataTermino}">
									 		<fmt:formatDate value="${InfoJobs.localEventoDataTermino}" pattern="dd/MM/YYYY"/>
									 	</c:if>
									 
									 </strong>
								</h4>	
					 	</div>
				 	</div>
				 	
					<div class="row">
						<div class="col-md-12 tira-padding">
							<div class="col-md-3">
								Tipo pagamento
							</div>
							<div class="col-md-5">
								<select class="form-control" name="tipoPagamento">
									
									<c:forEach items="${tipoPagamento}" var="tipoPagamento">
										
										<c:if test="${tiposPagamento == tipoPagamento.tipoPagamento}">
																			
										</c:if>
										
										<c:if test="${tipodePagamento != tipoPagamento.tipoPagamento}">
												<option value="${tipoPagamento.tipoPagamento}">${tipoPagamento.tipoPagamento}</option>								
										</c:if>
										
									</c:forEach> 
								
								</select>
							</div>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-md-12 tira-padding">
							<div class="col-md-3">
								Prazo para Pagamento&nbsp&nbsp&nbsp
							</div>
							<div class="col-md-3">
							<input type="text" class="form-control" style="width: 50px"
							name="diasPrazo" value="${diasPrazo}" onblur="calculaPrazo();" id="diasPrazo" />&nbspDias  
							</div>
							<div class="col-md-5" style="padding-top: 7px;height: 34px;font-size: 14px" id="prazoPagamento">
							&bull;Vencimento dia:&nbsp
							<c:if test="${empty prazoPagamento}">
								-------------							
							</c:if>
							<c:if test="${not empty prazoPagamento}">
								<strong style="font-size: 16px"><fmt:formatDate value="${prazoPagamento}" pattern="dd/MM/yyyy"/></strong> 
							</c:if>
							
							
							</div>
						</div>
					</div>
	

					
					<div class="row">
						<div class="col-md-12 tira-padding">
						  <div class="col-md-3">
							Data de Entrega
						  </div>
						  <div class="col-md-5">
							<input type="text" class="form-control data" style="width: 110px"
							value="<fmt:formatDate value="${prazoEntrega}"
							pattern="dd/MM/yyyy"/>" name="pEntrega"/> 
						  </div>
						  	
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12 tira-padding">
						  <div class="col-md-3">
							Local de Entrega
						  </div>
						  <div class="col-md-3">
							<select class="form-control" name="localEntrega" onchange="localentrega();" id="localEntrega">
								
								
								<c:if test='${referenciaEntrega == "agencia"}'>
									<option value="agencia">Agência Locco</option>
									<option value="localevento">Local do Evento</option>
								</c:if>
								
								<c:if test='${referenciaEntrega == "localevento"}'>
									<option value="localevento">Local do Evento</option>
									<option value="agencia">Agência Locco</option>
								</c:if>
								
								<c:if test='${empty referenciaEntrega}'>
									<option value="agencia">Agência Locco</option>
									<option value="localevento">Local do Evento</option>
								</c:if>
								
								
								
								
								
								
							</select>
						  </div>
						  <div class="col-md-6" style="padding-top: 7px;height: 34px;">
						  
							<c:if test="${empty localEntrega}">
								<p id="localEvento">&bull; Rua Barão de Jaceguai, 418-São Paulo/SP - CEP 04606-000</p>
							</c:if>
							
							<c:if test="${not empty localEntrega}">
								<p id="localEvento">&bull; ${localEntrega}</p>
							</c:if>
						  
						  
						  
						  </div>
						  	
						</div>
					</div>
					

					
					<div class="row">
						<div class="col-md-12 tira-padding">
						  <div class="col-md-3">
							Valor do Item
						  </div>
						  <div class="col-md-2">
							<input id="itemValor1" type="text" class="form-control real" style="width: 110px"
							name="itemValor1" value='<fmt:formatNumber value="${valorItem}" pattern="#,##0.00"/>' 
							onblur="calculaDiferenca();" /> 
						  </div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12 tira-padding">
						  <div class="col-md-3">
							Valor da Contratação&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						  </div>
						  <div class="col-md-2">
							<input id="contratacaoValor1" type="text" class="form-control real" style="width: 110px"
							name="contratacaoValor1"  value='<fmt:formatNumber value="${valorContratacao}" pattern="#,##0.00"/>'
							onblur="calculaDiferenca();" /> 
						  </div>
						  	
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12 tira-padding">
						  <div class="col-md-3" style="padding: 6px 0 6px 20px;">
							Diferença&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						  </div>
						  <div class="col-md-1" id="diferenca" style="padding: 8px 0 8px 14px;width: 110px;background: #ddd;margin-left: 15px;border-radius: 5px;">
							<input type="hidden" name="diferencaValor1" value="<fmt:formatNumber value="${diferenca}" pattern="#,##0.00"/> " />
							<fmt:formatNumber value="${diferenca}" pattern="#,##0.00"/> 
						  </div>
						  
						  <div class="col-md-3" style="padding: 6px 0 6px 20px;">
							<a class="btn btn-link" onclick="alert()" >Atribuir diferença para outro Fornecedor</a>
						  </div>
						  		
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12 tira-padding">
							<div class="col-md-3">
								Responsável Contratação&nbsp&nbsp&nbsp
							</div>
							<div class="col-md-4">
									<select class="form-control" style="width: 230px" name="idUsuario">
										<c:forEach items="${usuarios}" var="usuarios">
											<option value="${usuarios[0]}">${usuarios[1]}</option>
										</c:forEach>
									</select>
							</div>
							<div class="col-md-3">
										<p>&bull;${responsavel}</p>
							</div>
							
							
							
						</div>
					</div>
					
					
					
					
					<div class="row">
						<div class="col-md-12 tira-padding">
						  <div class="col-md-3">
							Observações:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						  </div>
						  <div class="col-md-5">
							<textarea class="form-control" rows="5" cols="35" name="obs">${obs}</textarea> 
						  </div>
						  	
						</div>
					</div>
					
					
					
					
					
					
					<div class="row">
						<div class="col-md-12 tira-padding">
							<div class="col-md-3">
								
									<input type="submit" class="btn btn-danger" value="Salvar Informações" >	

<%-- 								<c:choose>
								
									<c:when test="${empty confereProducao}">
										<input type="submit" class="btn btn-danger" value="Salvar Informações" >	
									</c:when>
									
									<c:otherwise>
										<input type="submit" class="btn btn-danger" value="Salvar Informações" disabled="disabled">	
									</c:otherwise>
									
								</c:choose>
 --%>							
							</div>
						</div>
					</div>					
	</form>	

					
					
			 </div>
<td>
	
<c:import url="../_comum/footer.jsp" />

	