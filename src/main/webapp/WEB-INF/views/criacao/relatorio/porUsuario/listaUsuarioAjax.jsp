<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div class="col-md-12" style="margin-bottom:140px;background: #fff;box-shadow:5px 5px 5px #ccc;padding: 20px 40px 70px 40px;border-radius:5px;border: 1px solid #ccc">
			<h3 style="font-weight: bolder;margin-bottom: 10px;color: #f05736;">${usuario.nome}</h3>
			<h4 style="font-weight: bolder;margin-bottom: 10px;color: #f05736;">
			
					<c:if test="${empty tempoListasPeriodos}">
						Não há registros para o período informado.	
					</c:if>
					<c:if test="${not empty tempoListasPeriodos}">
						Total de Horas no Período: ${tempoListasPeriodos[0]}Hs${tempoListasPeriodos[1]}Min
					</c:if>
			</h4>	
			
	<c:forEach items="${criacaoListas}" var="listas">
			<div class="col-md-3" style="margin-top: 30px;border-top: 1px solid #ccc;padding: 8px 0 0 0;color:#2e6da4;font-weight: bold;">${listas.listaTitulo}</div>
		
			<div class="col-md-3" style="margin-top: 30px;padding: 8px 0 0 0;margin-left: 25px;">
				<table class="table table-hover" style="margin-top: -8px;">
						<c:forEach items="${listas.criacaoItemLista}" var="itens">
							
							<c:set var="contador" value="0" />
							<c:forEach items="${itens.criacaoItemHistorico}" var="historico">
									<c:choose>
										<c:when test="${usuario.idUsuario == historico.iniciadoPor.idUsuario}">
											<c:set var="contador" value="1"/>																			
										</c:when>
										<c:when test="${usuario.idUsuario == historico.responsavelItem.idUsuario}">
											<c:set var="contador" value="1"/>
										</c:when>
										
										<c:otherwise>
											<c:choose>
													<c:when test="${usuario.idUsuario == historico.criacaoItemLista.responsavelItem.idUsuario}">
														<c:set var="contador" value="1"/>
													</c:when>										
													
													<c:otherwise>	
													   <c:choose>
															<c:when test="${usuario.idUsuario == historico.criacaoItemLista.criacaoLista.usuarioReponsável.idUsuario}">	
																<c:set var="contador" value="1"/>
															</c:when>
													  </c:choose>								
													</c:otherwise>	
														
											</c:choose>
										</c:otherwise>
										
									</c:choose>
							</c:forEach>
							
							<c:if test="${contador > 0}">
								<tr>
									<td style="width: 345px!important">${itens.tituloItem}</td>
									<td style="width: 245px!important">
									
										<c:forEach items="${itensTempo}" var="itensTempo">
											<c:if test="${itensTempo.idCriacaoLista == itens.idCriacaoItemLista}">
												<%-- ${itensTempo.idCriacaoLista}-  --%>${itensTempo.teste[0]}hs${itensTempo.teste[1]}min
											</c:if>	
										</c:forEach>
									
									</td>
								</tr>		
							</c:if>

						</c:forEach>
				</table>
			</div>
		
			<div class="col-md-2" style="margin-top: 30px;border-top: 1px solid #ccc;padding: 8px 0 0 0;margin-left: 25px;">
				Dt Fchto Lista <fmt:formatDate value="${listas.dataFechamento.time}" pattern="dd/MM/yyyy"/>  
			</div>
			<div class="col-md-3" style="margin-top: 30px;border-top: 1px solid #ccc;padding: 8px 0 0 0;font-weight: bolder;margin-left: 25px;">
				
				<c:forEach items="${tempoItensUsuario}" var="tempoItensUsuario">
					<c:if test="${tempoItensUsuario.idCriacaoLista == listas.idCriacaoLista}">
						Total horas: ${tempoItensUsuario.teste[0]}hs${tempoItensUsuario.teste[1]}min
					</c:if>
				 </c:forEach>

			</div>

	   <div class="col-md-12 divisor"></div>
	   </c:forEach>
	   	
   </div>	