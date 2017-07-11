<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width: 30%">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">APROVAR PLANILHA PARA PRODUÇÃO</h4>
	      </div>
	      <div class="modal-body">
	      	<span>Data do Evento:</span> 
	        <input class="form-control input-180px" type="date" id="dataParaAprovarEvento" />
	        <span style="color:red;display: none" id="erroDataAprovar">*Preencha a data do Evento</span>
	      </div>
	      <div class="modal-footer">
		
			<button onclick="aprovarPlanilhaPorAjax(${param.idlista});" type="button" class="btn btn-primary" id="aprovarPlanilha" style="float: left;">Aprovar</button>
		    <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>