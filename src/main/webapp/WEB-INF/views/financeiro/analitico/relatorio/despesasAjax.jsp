<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-hover table-bordered">
                     <tr>
                        <td colspan="4" align="center" class="corFolhaPgto"><b>DESPESAS GERAIS</b></td>
                     </tr>
                     <tr>
                     	<td class="tiraPaddingData"><input id="dataDespesas" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
                        <td class="tiraPaddingData"><input id="descDespesas" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorDespesas" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiroDespesas('dataDespesas','descDespesas','valorDespesas','salvaNovoDespesas',${idAnalitico},'despesas');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
                     </tr>
                     <tr>
                     	<td>Data</td>
                        <td>Descrição</td>
                        <td colspan="2">Valor</td>
                     </tr>
                     <tr>
                        <td colspan="4"></td>
                     </tr>
                     <c:set var="totalDespesas" value="0.00" />
                     <c:forEach items="${despesas}" var="despesas">
                        <tr>
                        	<td class="tiraPaddingData"><input id="dataDespesas${despesas.idFinancDespesas}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${despesas.data}" pattern="dd/MM"/>" type="text"
                                 onclick="mudaCampoData('dataDespesas${despesas.idFinancDespesas}');"
                                 onblur="editaCamposAnaliticoDespesas('editaDespesas','dataDespesas${despesas.idFinancDespesas}',${despesas.idFinancDespesas},'data','despesas');"
                                 /></td>
                           <td class="tiraPaddingData">
                              <input id="descricaoDespesas${despesas.idFinancDespesas}" class="ajusteInput2 tiraPaddingData input-120px " value="${despesas.descricao}"
                                 onblur="editaCamposAnaliticoDespesas('editaDespesas','descricaoDespesas${despesas.idFinancDespesas}',${despesas.idFinancDespesas},'descricao','despesas');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${despesas.valor < 0}">style='color:red'</c:if> >
                              <input id="valorDespesas${despesas.idFinancDespesas}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${despesas.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnaliticoDespesas('editaDespesas','valorDespesas${despesas.idFinancDespesas}',${despesas.idFinancDespesas},'valor','despesas');"
                              /> 
                           </td>
                        </tr>
                        <c:set var="totalDespesas" value="${totalDespesas+despesas.valor}" />
                     </c:forEach>
                     <tr>
                     
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                        	<b><fmt:formatNumber value="${totalDespesas}" pattern="#,##0.00"/></b>
                        </td>
                     </tr>
                  </table>