<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="table table-hover table-bordered">
                     <tr class="input-140px">
                        <td colspan="5" align="center" class="corTelefone"><b>TELEFONES</b></td>
                     </tr>
                     <tr>
                        <td class="tiraPaddingData"><input id="descTelefone" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="Descrição"/></td>
                        <td class="tiraPaddingData"><input id="valorTelefone" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor"/></td>
                        <td><button onclick="editaCamposFinanceiro('descTelefone','valorTelefone','salvaNovoTelefone',${idAnalitico},'telefones');" class="btn btn-default botaoMais">+</button> </td>
                        <td style="padding: 15px !important;"><input type="checkbox" checked="checked" /></td>
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
                     <c:set var="totalTelefone" value="0.00" />
                     <c:forEach items="${telefone}" var="telefone">
                        <tr>
                           <td colspan="2" class="tiraPaddingData">
                              <input id="descricaoTelefone${telefone.idFinancTelefone}" class="ajusteInput2 input-160px tiraPaddingData" value="${telefone.descricao}"
                                 onblur="editaCamposAnalitico('editaTelefone','descricaoTelefone${telefone.idFinancTelefone}',${telefone.idFinancTelefone},'descricao','telefones');"
                                 />
                           </td>
                           <td class="tiraPaddingData"  <c:if test = "${telefone.valor < 0}">style='color:red'</c:if> >
                              <input id="valorTelefone${telefone.idFinancTelefone}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${telefone.valor}" pattern="#,##0.00"/>"
                              onblur="editaCamposAnalitico('editaTelefone','valorTelefone${telefone.idFinancTelefone}',${telefone.idFinancTelefone},'valor','telefones');"
                              /> 
                           </td>
                           <td style="padding: 15px !important;">
                           		<input type="checkbox" <c:if test = "${telefone.fixo == true}"> checked = checked</c:if >
                           			id="chkEditatelefones${telefone.idFinancTelefone}"
					 	  	  		onclick="editaCheckedFixo('editaFixo',${idAnalitico},${telefone.idFinancTelefone},'telefones',3225519);"
 				 	  	        />
                           <td style="padding: 20px !important;">
                           		<a href="excluiItemAnalitico?idAnalitico=${idAnalitico}&idTabela=${telefone.idFinancTelefone}&tabela=FinancTelefone">
                           		<i class="glyphicon glyphicon-trash"></i></a>
                           	</td>
                           
                           
                        </tr>
                        <c:set var="totalTelefone" value="${totalTelefone+telefone.valor}" />
                     </c:forEach>
                     <tr>
                        <td colspan="2"></td>
                        <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
                           <b>
                              <fmt:formatNumber value="${totalTelefone}" pattern="#,##0.00"/>
                           </b>
                        </td>
                        <td colspan="2"></td>
                     </tr>
                  </table>