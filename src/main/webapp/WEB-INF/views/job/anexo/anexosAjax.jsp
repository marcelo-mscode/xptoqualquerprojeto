<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

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
 							<th>Excluir</th>
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
									  <i class="glyphicon glyphicon-remove excluir" style="font-size: 18px;color: #c0392b;cursor: pointer;"
									     onclick="apagarAnexo(${anexos.idAnexo}, ${idJob});"></i>
								</td>		
							</security:authorize>

							</tr>
						</c:forEach>


					</table>
				</div>
			</c:when>
		</c:choose>