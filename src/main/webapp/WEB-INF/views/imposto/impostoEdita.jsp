<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<script type="text/javascript" src="<c:url value="resources/js/syslocc.js" />"></script>

<form action="editaImposto" method="post" id="impostoValida">

		<c:forEach items="${impostoEdita}" var="impostos">
			
	
		<div class="form-group">

          	<label>Título</label>
			
			<input type="hidden" value="${impostos.idimposto}" name="idimposto" />
	        <input type="text" class="form-control input-titulo-imposto" value="${impostos.impostoTitulo}" id="testeNameImposto" name="impostoTitulo">	

	    </div>
          	
        <div class="form-group">
        
          	<label>Imposto</label>	

	        <input type="text" class="form-control input-imposto somenteNumeros"
	         onkeypress="mascara( this, mcep);" value="${impostos.imposto}" id="testeImposto" name="imposto" >
	   
	    </div>
	    </c:forEach>
	        
	       <div class="divisor"></div>	 
	       <button class="btn btn-primary">Salvar</button>
           <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
           
	  
</form>		

