<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-hover table-bordered">
                     <tr class="input-140px">
                        <td colspan="5" align="center" class="corFolhaPgto"><b>FOLHA PGTO</b></td>
                     </tr>
                     <tr>
                        <td class="tiraPaddingData"><input id="descFolha" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorFolha" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiro('descFolha','valorFolha','salvaNovoFolha',${idAnalitico},'folha');" class="btn btn-default botaoMais">+</button> </td>
                        <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>	
                        <td></td>
                     </tr>
                     <tr>
                        <td class="input-180px" colspan="2">Descrição</td>
                        <td class="input-30px">Valor</td>
                        <td>Fixo</td>
                        <td>Excluir</td>
                     </tr>
                     <tr>
                        <td colspan="5"></td>
                     </tr>
                     <c:set var="totalFolha" value="0.00" />
                     <c:forEach items="${folha}" var="folha">
                        <tr>
                           <td colspan="2" class="tiraPaddingData">
                              <input id="descricaoFolha${folha.idFinancFolha}" class="ajusteInput2 input-160px tiraPaddingData" value="${folha.descricao}"
                                 onblur="editaCamposAnalitico('editaFolha','descricaoFolha${folha.idFinancFolha}',${folha.idFinancFolha},'descricao','folha');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${folha.valor < 0}">style='color:red'</c:if> >
                              <input id="valorFolha${folha.idFinancFolha}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${folha.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnalitico('editaFolha','valorFolha${folha.idFinancFolha}',${folha.idFinancFolha},'valor','folha');"
                              /> 
                           </td>
                           <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>
                           <td style="padding: 20px !important;">
                           	<a href="excluiItemAnalitico?idAnalitico=${idAnalitico}&idTabela=${folha.idFinancFolha}&tabela=FinancFolhaPgto">
  	                        <i class="glyphicon glyphicon-trash"></i></a>
                           </td>	
                        </tr>
                        <c:set var="totalFolha" value="${totalFolha+folha.valor}" />
                     </c:forEach>
                     <tr>

                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
                        	<b><fmt:formatNumber value="${totalFolha}" pattern="#,##0.00"/></b>
                        </td>
                        <td colspan="2"></td>
                     </tr>
                  </table>