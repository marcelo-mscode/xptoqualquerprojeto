<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-hover table-bordered">
                     <tr>
                        <td colspan="5" align="center" class="corFolhaPgto"><b>OUTRAS DESPESAS</b></td>
                     </tr>
                     <tr>
                     	<td class="tiraPaddingData"><input id="dataOutrasDespesas" type="date"  class="ajusteInput2 tiraPaddingData input-140px" /></td>		
                        <td class="tiraPaddingData"><input id="descOutrasDespesas" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorOutrasDespesas" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiroDespesas('dataOutrasDespesas','descOutrasDespesas','valorOutrasDespesas','salvaNovoOutrasDespesas',${idAnalitico},'outrasdespesas');" class="btn btn-default botaoMais botaoMaisDespesa">+</button> </td>
                    	<td style="padding: 15px !important;"><input type="checkbox" /></td>	
                     </tr>
                     <tr>
                     	<td>Data</td>
                        <td>Descrição</td>
                        <td colspan="1">Valor</td>
                        <td>Fixo</td>
                        <td>Excluir</td>
                     </tr>
                     <tr>
                        <td colspan="5"></td>
                     </tr>
                     <c:set var="totalOutrasdespesas" value="0.00" />
                     <c:forEach items="${outrasdespesas}" var="outrasdespesas">
                        <tr>
                        	<td class="tiraPaddingData"><input id="dataOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${outrasdespesas.data}" pattern="dd/MM"/>" type="text"
                                 onclick="mudaCampoData('dataOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}');"
                                 onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','dataOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'data','outrasdespesas');"
                                 /></td>
                           <td class="tiraPaddingData">
                              <input id="descricaoOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData input-120px" value="${outrasdespesas.descricao}"
                                 onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','descricaoOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'descricao','outrasdespesas');"
                                 />
                           </td>
                           <td colspan="1" class="tiraPaddingData">
                              <input id="valorOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${outrasdespesas.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnaliticoDespesas('editaOutrasDespesas','valorOutrasdespesas${outrasdespesas.idFinancOutrasDespesas}',${outrasdespesas.idFinancOutrasDespesas},'valor','outrasdespesas');"
                              /> 
                           </td>
                           <td style="padding: 15px !important;">
                           <input type="checkbox" <c:if test = "${outrasdespesas.fixo == true}"> checked = checked</c:if >
                           		id="chkEditaoutrasdespesas${outrasdespesas.idFinancOutrasDespesas}"
					 	  	  	onclick="editaCheckedFixo('editaFixo',${idAnalitico},${outrasdespesas.idFinancOutrasDespesas},'outrasdespesas',52251);"
					 	  	  />
                           </td>	
						   
						   <td style="padding: 20px !important;">
						   <a href="excluiItemAnalitico?idAnalitico=${idAnalitico}&idTabela=${outrasdespesas.idFinancOutrasDespesas}&tabela=FinancOutrasDespesas">
                           <i class="glyphicon glyphicon-trash"></i></a>	
						   </td>
                        </tr>
                        <c:set var="totalOutrasdespesas" value="${totalOutrasdespesas+outrasdespesas.valor}" />
                     </c:forEach>
                     <tr>
                     
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
                        	<b><fmt:formatNumber value="${totalOutrasdespesas}" pattern="#,##0.00"/></b>
                        </td>
                        <td colspan="2"></td>
                     </tr>
                  </table>