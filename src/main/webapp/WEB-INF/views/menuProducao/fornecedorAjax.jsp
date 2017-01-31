<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<div class="col-md-8">

	<h4 style="font-family: 'OpenSansLight'">
		Fornecedor: <strong>${fornecedor.empresa}</strong> <i
			class="glyphicon glyphicon-pencil cor-lapis"
			onclick="efeitoToogle(fornecedorMuda);"
			style="cursor: pointer; font-size: 16px; margin-left: 15px; margin-top: 10px; position: absolute;"></i>
	</h4>
			<span style="color: red;display: none" id="sucessFornecedor">Atualizado com sucesso !</span>
</div>
<div class="col-md-4">
	<h4 style="font-family: 'OpenSansLight'">
		CNPJ: <strong>${fornecedor.cnpj}</strong>
	</h4>
</div>