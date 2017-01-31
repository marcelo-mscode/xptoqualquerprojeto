<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<form action="atualizaTags" method="post">		
   <c:forEach items="${tags}" var="tags">
	
		<div class="form-group" style="padding-bottom: 20px;">
			<input type="hidden" name="idAtuacao" value="${tags.idAtuacao}">
			<label>Editar Tag</label>
			<input type="text" name="atuacao" class="form-control form-tag" value="${tags.atuacao}" placeholder="${tags.atuacao}">
		</div>

		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary">Atualizar Tag</button>
	</c:forEach>	
		
			<!-- <button type="button" class="btn btn-default" onclick="location.reload();" >Fechar</button> -->
			<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
		
		</div>
		
</form>		