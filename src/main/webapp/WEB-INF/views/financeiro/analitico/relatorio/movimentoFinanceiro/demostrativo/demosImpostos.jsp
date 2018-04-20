<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="2" align="center" class="info"><b>IMPOSTOS NF EVENTOS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData" colspan="2"></td>
    </tr>
    <tr>
       <td colspan="1">Descrição</td>
       <td>Valor</td>
    </tr>
    <tr>
       <td colspan="2" class="input-120px pad0" align="center">Simples Nacional</td>
    </tr>
    
   
       <tr>
          <td class="tiraPaddingData" colspan="1"><input class="ajusteInput2 tiraPaddingData input-120px"  value="*15,95%"/></td>
          <td class="tiraPaddingData" colspan="1"><input class="ajusteInput2 tiraPaddingData input-80px"  value="<fmt:formatNumber value="${DemostrativoImpostos}" pattern="#,##0.00"/>"></td>
       </tr>
       

    <tr>
    
    <tr>
       <td></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" >
       	<b><fmt:formatNumber value="${DemostrativoImpostos}" pattern="#,##0.00"/></b>
       </td>
       
    </tr>
</table>
