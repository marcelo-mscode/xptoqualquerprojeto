<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 


<ol class="breadcrumb col-md-offset-1">
    <li><a href="#">Menu</a></li>
    <li><a href="empresa">Empresas</a></li>
    <li class="active">Atualiza Status</li>
</ol>

	<div class="row col-md-offset-1">
		<div class="col-md-9 painel ajuste-left">
			<div class="col-md-12">


EM CONSTRUÇÃO!
			
			</div>
	</div>
</div>
			
			
			
			
			<!-- - - - - - - - - - Fim Container - - - - - - - - - -->



<div class="modal fade" id="25" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Atualização de Tag</h4>
        <img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader" alt="loading" />
      </div>
      <div class="modal-body">
        
        <div class="modalUser"></div>
       
      </div>
          </div>
  </div>
</div>


<c:import url="../_comum/footer.jsp" />
			
</body>
</html>