<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


			<tr>
				<th class="fundoDespesasFixas input-260px">Despesas Variáveis:</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th style="font-weight: bold;"><fmt:formatNumber value="${somaDespVariaveis}" pattern="#,##0.00"/></th>
				<th>35,3</th>
			</tr>
			
			<tr>
				<th class="input-260px">Despesas bancárias:</th>
				<th style="background-color: red"> 9.344,94</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			
			<tr>
				<th class="input-260px">Despesas Caixa/Projetos</th>
				<th><fmt:formatNumber value="${despCaixasProjetos}" pattern="#,##0.00"/></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			
			<tr>
				<th class="input-260px">Outros Variáveis</th>
				<th><fmt:formatNumber value="${outrasDespesas}" pattern="#,##0.00"/></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			
			<tr>
				<th class="input-260px"><b>Juros Simples Nacional</b></th>
				<th style="background-color: red"> - </th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th>0,00</th>
			</tr>

			<tr>
				<th class="fundoDespesasFixas input-260px">Créditos e Aplicações:</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th>136,10</th>
			</tr>

			<tr>
				<th class="fundoDespesasFixas input-260px">Total Despesas:</th>
				<th class="fundoDespesasFixas">=></th>
				<th class="fundoDespesasFixas">=></th>
				<th class="fundoDespesasFixas">=></th>
				<th class="fundoDespesasFixas">=></th>
				<th  class="fundoDespesasFixas" style="border: 2px solid #000"><b><fmt:formatNumber value="${creditosAplicacoes}" pattern="#,##0.00"/></b></th>
				<th>136,10</th>
			</tr>
			
			 <tr><td colspan="7"></td></tr>
			
			<tr>
				<th class="input-260px" style="color: blue;">MO 20% sobre Jobs</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th style="color: blue;"><b><fmt:formatNumber value="${MOmargemContribuicao}" pattern="#,##0.00"/></b></th>
				<th>136,10</th>
			</tr>

			<tr class="fundoAmarelo input-260px" style="border: 2px solid #000">
				<th  class="fundoAmarelo">Giro ( déficit ou superávit ):</th>
				<th  class="fundoAmarelo">=></th>
				<th  class="fundoAmarelo">=></th>
				<th  class="fundoAmarelo">=></th>
				<th  class="fundoAmarelo">=></th>
				<th  class="fundoAmarelo" style="font-weight: bold;color: red;border: 2px solid #000"><fmt:formatNumber value="${giroDeficitAvit}" pattern="#,##0.00"/></th>
				<th style="color: red;background-color: #fff;border: none">(92,88)</th>
			</tr>