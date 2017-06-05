<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


			<tr>
				<th class="fundoDespesasFixas input-260px">Despesas Fixas :</th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px" style="font-weight: bold;">58.268,84</th>
				<th class="input-160px">100,80	</th>
			</tr>
			
			<tr>
				<th class="input-160px">Outros Impostos/contador</th>
				<th class="input-160px"><fmt:formatNumber value="${outrosImpostosContador}" pattern="#,##0.00"/></th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px"></th>
				<th class="input-160px">11,2</th>
			</tr>
			
			<tr>
				<th class="input-260px">Escritório</th>
				<th><fmt:formatNumber value="${outrosEscritorio}" pattern="#,##0.00"/></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th>16,3</th>
			</tr>
			
			<tr>
				<th class="input-260px">Telefones</th>
				<th><fmt:formatNumber value="${outrosTelefones}" pattern="#,##0.00"/></th>
				<th><fmt:formatNumber value="${outrosTelefones}" pattern="#,##0.00"/></th>
				<th></th>
				<th></th>
				<th></th>
				<th>2,7</th>
			</tr>
			
			<tr>
				<th class="input-260px">Salários</th>
				<th><fmt:formatNumber value="${outrosFolhaPgto}" pattern="#,##0.00"/></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th>70,5</th>
			</tr>

			<tr>
				<th class="input-260px" style="background-color: red">Dissidio Fev ??</th>
				<th class="fundoAmarelo"></th>
				<th style="color: red"><fmt:formatNumber value="${outrosImpostosContador}" pattern="#,##0.00"/> - 43.245,52</th>
				<th></th>
				<th></th>
				<th></th>
				<th>0,00</th>
			</tr>