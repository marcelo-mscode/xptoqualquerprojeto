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
    
    <c:set var="totalCaches" value="0.00" />
    <c:forEach items="${ListaCacheTotal}" var="ListaCacheTotal">

       <tr>
          <td class="tiraPaddingData" colspan="3">${ListaCacheTotal.nomeFuncionario}</td>
          <td class="tiraPaddingData"><fmt:formatNumber value="${ListaCacheTotal.valor}" pattern="#,##0.00"/></td>
       </tr>


       <c:set var="totalCaches" value="${totalCaches+ListaCacheTotal.valor}" />
    </c:forEach>
    <tr>
    
    <tr>
       <td colspan="3"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalCaches}" pattern="#,##0.00"/></b>
       </td>
       
    </tr>
</table>
