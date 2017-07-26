<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../_comum/header.jsp" />


<div id="criacaoListas" class="efeitoDegrade" style="margin-top:34px;font-size: 12px;font-family: 'OpenSansLight';padding:35px 5px 70px 5px;width: 250%">
   
   <div class="col-md-6">
	   
	   <form action="salvarVideos">
		   	<input  class="form-control" value="Título do Video" /><br>
		   	<input  class="form-control" value="Descrição do Video" /><br>
		   	<input  class="form-control" value="Tags do Video" /><br>
		   	<button class="btn btn-primary"   type="submit">salvar</button>
	   </form>
 	</div>
   
   
 </div>  
   