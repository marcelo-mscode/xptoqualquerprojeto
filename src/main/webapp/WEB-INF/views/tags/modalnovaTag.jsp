<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<form action="SalvaNovaTag" method="post" id="salvaNovaTag">	
          	
          	<label>Nome da Nova Tag</label>
            <input type="text" class="form-control" placeholder="Nome da nova Tag" style="width:180px" name="atuacao" id="novaTag" >
            <div class="form-inline">
            </div>
         
    
      		</div>
      		<div class="divisor"></div>
      		<div class="modal-footer">
        	<button type="submit" class="btn btn-primary">Salvar Tag</button>
</form>
<button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.reload();">Fechar</button>	

<script>
$("#salvaNovaTag").submit(function() {
	var novaTag = $("#novaTag").val();
	if(seFalse($("#novaTag"),novaTag) == false){return false;}
	return true;
});
</script>