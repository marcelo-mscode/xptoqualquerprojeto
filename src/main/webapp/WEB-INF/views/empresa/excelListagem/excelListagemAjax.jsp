<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-striped table-bordered">
		  <tbody>
			<tr>
				<th class="input-180px">Empresa</th>
				<th class="input-180px">Telefone Principal</th>
				<th>Contatos</th>
			</tr>
				<c:forEach items="${empresa}" var="empresa">
				<tr>
					<td style="border-right: 1px solid #ddd">${empresa[1]}</td>
					<td style="border-right: 1px solid #ddd">${empresa[2]}</td>
					<td style="padding: 0px !important">
						<table class="table" style="background-color: #f8f8f8;margin: 0">
							<tr>
								<c:forEach items="${contato}" var="contato">
									<c:if test="${empresa[0] == contato[2]}">
									 <tr>
										  <td class="input-220px" style="border-bottom: 0px solid #ddd;vertical-align: middle;">
										  	${contato[1]}
										  </td>
										  <td  style="border-bottom: 0px solid #ddd;">
										  	<table>
												  <c:forEach items="${comunicador}" var="comunicador">
													<c:if test="${contato[0] == comunicador[1]}">
														<tr>
														 <td>
															${comunicador[0]}														
														 </td>	
													    </tr>
													</c:if>
												   </c:forEach>
											 </table>	  
										  </td>
									 </tr> 
									</c:if>
								</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
		  </tbody>
		</table>