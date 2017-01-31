<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="row">
    <div class="form-group col-md-12 tira-padding"
        style="padding: 15px 0px 10px 0;">
        <div class="col-md-4">
            Fornecedor <br>
            <h4>${detalhesItem.empresa.empresa}</h4>
        </div>
        <div class="col-md-4">
            <select size=5 class="form-control" name="idEmpresaTransiente" id="empresaFornecedor">
                <option value="${detalhesItem.empresa.idEmpresa}"
                    selected="selected">${detalhesItem.empresa.empresa}</option>
                <c:forEach items="${fornecedores}" var="fornecedores">
                    <option value="${fornecedores[0]}">${fornecedores[1]}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>


<script type="text/javascript" src="resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
$("#atualizaDetalhesItem").submit(function() {
	
	
	
	var produtoAtuacao = $("#produtoAtuacao").val();
	var empresaFornecedor = $("#empresaFornecedor").val();
//	if(seFalseItem($("#produtoAtuacao"),produtoAtuacao) == false){return false;}
	if($("#empresaFornecedor").val() == ""){
		$("#empresaFornecedor").css("border","2px solid red")
		.parent().append("<span class='errors'>*Selecione um Fornecedor.</span>");
		$(".errors").show();
		return false;
	}
	return true;
});
</script>