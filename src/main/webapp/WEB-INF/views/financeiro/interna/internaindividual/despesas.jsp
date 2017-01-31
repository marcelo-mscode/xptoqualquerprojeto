<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
	.ajusteInput2{border: none;height: 35px;width: 90px;}
	.ajustWidthValor{width: 155px}
	.ajusteTd{width: 90px}
	.ajusteData{width: 165px}
	.ajusteDescr{width: 260px}
	.ajusteObs{width: 360px !important}
</style>



<div class="col-md-12" style="padding: 0;padding-right: 30px;margin-top: 0px">

<table class="table table-bordered table-condensed" style="box-shadow: 0px 1px 14px 3px #ccc;margin-top: -1px;width: 60%;">
	<tbody>
		<tr>
			<th>Data</th>
			<th>Descrição</th>
			<th>Marcelo $</th>
			<th>Pedro $</th>
			<th>Célia $</th>
			<th>Loco $</th>
			<th>C.C Locco</th>
			<th>Obs</th>
		</tr>
		<tr>
			<td colspan="8"></td>
		</tr>
		<form id="formDespesas" action="salvaDespesas" method="post" >
			<tr class="tiraPadding">
				
				<td class="ajusteData">
					<input name="IdListaTransiente" type="hidden" value="${param.idlista}"/>
					<input name="idLista" type="hidden" value="${param.idlista}"/>
					<input name="dataTransiente" id="" class="form-control ajusteInput2 ajusteData" type="date" />
				</td>
				<td class="ajusteDescr"><input name="descricao2" class="form-control ajusteInput2 ajusteDescr" type="text" /></td>
				<td class="ajusteTd"><input name="valor1Transiente" id="" class="form-control ajusteInput2" type="text" /></td>
				<td class="ajusteTd"><input name="valor2Transiente" id="" class="form-control ajusteInput2" type="text" /></td>
				<td class="ajusteTd"><input name="valor3Transiente" id="" class="form-control ajusteInput2" type="text" /></td>
				<td class="ajusteTd"><input name="loccoTransiente" id="" class="form-control ajusteInput2" type="text" /></td>
				<td class="ajusteTd"><input name="cCreditoTransiente" id="" class="form-control ajusteInput2" type="text" /></td>
				<td class="ajusteObs"><input name="observacao" id="" class="form-control ajusteInput2 ajusteObs" type="text" /></td>
			</tr>
			<tr>
				<td colspan="8">
					<button type="submit" class="btn btn-danger" style="float: right;">Salvar</button>
				</td>
			</tr>
		</form>
	</tbody>
</table>
</div>

<div class="col-md-12" style="padding: 0;padding-right: 30px;margin-top: 20px">
<table class="table table-bordered table-condensed" style="box-shadow: 0px 1px 14px 3px #ccc;margin-top: -1px;width: 60%;">
	<tbody>
		<tr>
			<th>Data</th>
			<th>Descrição</th>
			<th>Marcelo $</th>
			<th>Pedro $</th>
			<th>Célia $</th>
			<th>Loco $</th>
			<th>C.C Locco</th>
			<th>Obs</th>
		</tr>
		<!-- <tr>
			<td colspan="8"></td>
		</tr> -->
		
		<c:set var="valor1" value="0" />  
		<c:set var="valor2" value="0" />  
		<c:set var="valor3" value="0" />  
		<c:set var="locco" value="0" />  
		<c:set var="cCredito" value="0" />  
		<c:set var="total" value="0" />  
		<c:forEach items="${teste}" var="despesas">
			<c:set var="valor1" value="${valor1 + despesas.valor1 }" />
			<c:set var="valor2" value="${valor2 + despesas.valor2 }" />
			<c:set var="valor3" value="${valor3 + despesas.valor3 }" />
			<c:set var="locco" value="${locco + despesas.locco }" />
			<c:set var="cCredito" value="${cCredito + despesas.cCredito}" />
			<tr class="tiraPadding">
				<td class="ajusteData">
					<input name="" id="data${despesas.idDesp}" class="form-control ajusteInput2 ajusteData"
					 value='<fmt:formatDate value="${despesas.data}" pattern="dd/MM/yyyy" />' onclick="mudaCampoData('data${despesas.idDesp}');"
					 onblur="editaCamposDespesas('data${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'data');"
					 />
				</td>
				<td class="ajusteDescr"><input name="" id="descricao2${despesas.idDesp}" class="form-control ajusteInput2 ajusteDescr" type="text" value="${despesas.descricao2}" onblur="editaCamposDespesas('descricao2${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'descricao2');"/></td>
				<td class="ajusteTd"><input name="" id="valor1${despesas.idDesp}" class="form-control ajusteInput2" type="text" value="<fmt:formatNumber value="${despesas.valor1}" pattern="#,##0.00"/>" onblur="editaCamposDespesas('valor1${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'valor1');" /></td>
				<td class="ajusteTd"><input name="" id="valor2${despesas.idDesp}" class="form-control ajusteInput2" type="text" value="<fmt:formatNumber value="${despesas.valor2}" pattern="#,##0.00"/>" onblur="editaCamposDespesas('valor2${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'valor2');" /></td>
				<td class="ajusteTd"><input name="" id="valor3${despesas.idDesp}" class="form-control ajusteInput2" type="text" value="<fmt:formatNumber value="${despesas.valor3}" pattern="#,##0.00"/>" onblur="editaCamposDespesas('valor3${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'valor3');"/></td>
				<td class="ajusteTd"><input name="" id="locco${despesas.idDesp}" class="form-control ajusteInput2" type="text" value="<fmt:formatNumber value="${despesas.locco}" pattern="#,##0.00"/>" onblur="editaCamposDespesas('locco${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'locco');"/></td>
				<td class="ajusteTd"><input name="" id="cCredito${despesas.idDesp}" class="form-control ajusteInput2" type="text" value="<fmt:formatNumber value="${despesas.cCredito}" pattern="#,##0.00"/>" onblur="editaCamposDespesas('cCredito${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'cCredito');"/></td>
				<td class="ajusteObs"><input name="" id="observacao${despesas.idDesp}" class="form-control ajusteInput2 ajusteObs" type="text" value="${despesas.observacao}" onblur="editaCamposDespesas('observacao${despesas.idDesp}','valoresDespesas',${despesas.idDesp}, 'observacao');"/></td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="8"></td>
		</tr>
		
		<tr>
		<c:set var="total" value="${valor1+valor2+valor3+locco+cCredito}" />  
		<td colspan="2" style="font-size: 17px"><strong style="margin-right: 25px;">Subtotal</strong><fmt:formatNumber value="${total}" pattern="#,##0.00"/></td>
		<td><b><fmt:formatNumber value="${valor1}" pattern="#,##0.00"/></b></td>			
		<td><b><fmt:formatNumber value="${valor2}" pattern="#,##0.00"/></b></td> 
		<td><b><fmt:formatNumber value="${valor3}" pattern="#,##0.00"/></b></td> 
		<td><b><fmt:formatNumber value="${locco}" pattern="#,##0.00"/></b></td> 
		<td><b><fmt:formatNumber value="${cCredito}" pattern="#,##0.00"/></b></td>
		<td></td>
		
		</tr>
	</tbody>
</table>

<button class="btn btn-danger" onclick="location.reload();" style="float: right">Atualizar</button>

</div>







