<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-hover table-bordered">
                     <tr>
                        <td colspan="4" align="center" class="corEscritorio"><b>ESCRITÓRIO</b></td>
                     </tr>
                     <tr>
                        <td class="tiraPaddingData"><input id="descEscritorio" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorEscritorio" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiro('descEscritorio','valorEscritorio','salvaNovoEscritorio',${idAnalitico},'escritorio');" class="btn btn-default botaoMais">+</button> </td>
	                    <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>
                     </tr>
                     <tr>
                        <td colspan="2">Descrição</td>
                        <td>Valor</td>
                        <td>Fixo</td>
                     </tr>
                     <tr>
                        <td colspan="4"></td>
                     </tr>
                     <c:set var="totalEscritorio" value="0.00" />
                     <c:forEach items="${escritorio}" var="escritorio">
                        <tr>
                           <td colspan="2" class="tiraPaddingData">
                              <input id="descricao${escritorio.idFinancEscritorio}" class="ajusteInput2 input-160px tiraPaddingData" value="${escritorio.descricao}"
                                 onblur="editaCamposAnalitico('editaEscritorio','descricao${escritorio.idFinancEscritorio}',${escritorio.idFinancEscritorio},'descricao','escritorio');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${escritorio.valor < 0}">style='color:red'</c:if> >
                              <input id="valor${escritorio.idFinancEscritorio}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${escritorio.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnalitico('editaEscritorio','valor${escritorio.idFinancEscritorio}',${escritorio.idFinancEscritorio},'valor','escritorio');"
                              /> 
                           </td>
                           <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>
                        </tr>
                        <c:set var="totalEscritorio" value="${totalEscritorio+escritorio.valor}" />
                     </c:forEach>
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="2">
                           <b>
                              <fmt:formatNumber value="${totalEscritorio}" pattern="#,##0.00"/>
                           </b>
                        </td>
                     </tr>
                  </table>