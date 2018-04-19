<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


	<tr>
		<th class="fundoAmarelo input-260px">Eventos Contas a pagar:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${eventosContasPagar}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px">LoCCO Agência Contas a pagar:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 0px solid #000"><b></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px">Salários:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000;"><b><fmt:formatNumber value="${salarios}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px">Cachês:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${somaCacheTotal}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px">Imposto NF:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${impostos}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px">Outros impostos:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${outrosImpostos}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px">Total a pagar:</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px">=></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${totalPagar}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px colorRed">Giro Itau 25/05/2014:</th>
		<th class="input-160px fontSize12" style="padding: 0px 0px 0px 3px;vertical-align: middle;">Itau R$ 3.927,16 (36) 1,82%</th>
		<th class="input-160px fontSize12">1ª  30/05/2014</th>
		<th class="input-160px fontSize12">36ª 30/04/2017</th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${giroItau}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px colorRed">Conta Garantida Itau</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${totalEmprestimos}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	<tr>
		<th class="fundoAmarelo input-260px">Total a pagar c/ Emprestimos</th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px"></th>
		<th class="input-160px" style="border: 2px solid #000"><b><fmt:formatNumber value="${totalPagar + giroItau + totalEmprestimos}" pattern="#,##0.00"/></b></th>
		<th class="input-160px"></th>
	</tr>

	