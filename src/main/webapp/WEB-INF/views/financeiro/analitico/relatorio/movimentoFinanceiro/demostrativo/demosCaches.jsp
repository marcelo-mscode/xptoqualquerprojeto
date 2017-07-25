<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="5" align="center" class="info"><b>CACHES DE EVENTOS DO MÊS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData" colspan="5"></td>
    </tr>
    <tr>
       <td colspan="3">Descrição</td>
       <td>Valor</td>
    </tr>
    <tr>
       <td colspan="5"></td>
    </tr>
    
    <c:set var="totalOutrasdespesas" value="0.00" />
    <c:forEach items="${outrasdespesas}" var="outrasdespesas">
       <tr>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData input-140px" value="${outrasdespesas.descricao}"
                onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','descricaoOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'descricao','outrasdespesas');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${outrasdespesas.valor < 0}">style='color:red'</c:if> >
             <input id="valorOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${outrasdespesas.valor}" pattern="#,##0.00"/>"
             onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','valorOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'valor','outrasdespesas');"
             /> 
          </td>
       </tr>
       <c:set var="totalOutrasdespesas" value="${totalOutrasdespesas+outrasdespesas.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="3"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalOutrasdespesas}" pattern="#,##0.00"/></b>
       </td>
       
    </tr>
</table>
