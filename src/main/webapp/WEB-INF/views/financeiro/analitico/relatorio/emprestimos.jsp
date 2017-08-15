<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-hover table-bordered">
    <tr>
       <td colspan="7" align="center" class="verdeClaroFlat"><b>EMPRÉSTIMOS</b></td>
    </tr>
    <tr>
       <td class="tiraPaddingData"><input id="dataPgtoEmprestimo" type="date"  class="ajusteInput2 tiraPaddingData input-140px" value="2017-08-18"/></td>		
       <td class="" style="padding: 0px">
       <select id="emprestimoTipoBanco" class="form-control" style="width: 120px;height: 50px;border: none;">
	       <option value="1">Itau</option>
	       <option value="2">CEF</option>
	       <option value="3">Bradesco</option>
	       <option value="4">Santander</option>
       </select>	
       </td>		
       <td class="tiraPaddingData" colspan="3"><input id="descEmprestimo" class="form-control ajusteInput2 tiraPaddingData input-140px" type="text" placeholder="Descrição" value="Teste de emprestimo"/></td>
       <td class="tiraPaddingData"><input id="valorEmprestimo" class="form-control ajusteInput2 tiraPaddingData" type="text" placeholder="valor" value="800"/></td>
       <td style="text-align: center !important;"><button onclick="insereEmprestimos('dataPgtoEmprestimo','descEmprestimo','valorEmprestimo','salvaNovoEmprestimo',${idAnalitico},'emprestimos','emprestimoTipoBanco');" class="btn btn-default botaoMais botaoMaisDespesa" >+</button> </td>
    </tr>
    <tr>
       <td>DATA PARCELA</td>
       <td>Banco</td>
       <td colspan="3">Descrição</td>
       <td colspan="1">Valor</td>
    </tr>
    <tr>
       <td colspan="7"></td>
    </tr>
    
    <c:set var="totalSantander" value="0.00" />
    <c:forEach items="${emprestimosBancos}" var="emprestimosBancos">
       <tr>
       	  <td class="tiraPaddingData">
       	  		<input id="dataPgtoEmprestimo${emprestimosBancos.idEmprestimo}" class="ajusteInput2 tiraPaddingData input-140px" value="<fmt:formatDate value="${emprestimosBancos.dataPrimeiroPagamento}" pattern="dd/MM/yyyy"/>" type="text"
                onclick="mudaCampoData('dataPgtoEmprestimo${emprestimosBancos.idEmprestimo}');"
                onblur="editaEmprestimos('dataPgtoEmprestimo${emprestimosBancos.idEmprestimo}',${idAnalitico},'data',${emprestimosBancos.idEmprestimo});"/>
          </td>
       	  <td class="tiraPaddingData">
	       	  <select id="emprestimoTipoBanco" class="form-control" onblur="editaEmprestimos('editaTarifas','dataSantander${emprestimosBancos.idEmprestimo}',${emprestimosBancos.idEmprestimo},'data','santanderTarifas','4');" style="width: 120px;height: 50px;border: none;">
		       <option value="1">${emprestimosBancos.banco.nomebanco}</option>
		       <option value="1">Itau</option>
		       <option value="2">CEF</option>
		       <option value="3">Bradesco</option>
		       <option value="4">Santander</option>
	       	  </select>
          </td>
          <td class="tiraPaddingData" colspan="3">
             <input id="descricaoSantander${emprestimosBancos.idEmprestimo}" class="ajusteInput2 tiraPaddingData input-140px" value="${emprestimosBancos.descricao}"
                onblur="editaEmprestimos('editaTarifas','descricaoSantander${emprestimosBancos.idEmprestimo}',${emprestimosBancos.idEmprestimo},'descricao','santanderTarifas','4');"
                />
          </td>
          <td class="tiraPaddingData"  <c:if test = "${emprestimosBancos.valorParcela < 0}">style='color:red'</c:if> >
             <input id="valorSantander${emprestimosBancos.idEmprestimo}" class="ajusteInput2 tiraPaddingData" value="<fmt:formatNumber value="${emprestimosBancos.valorParcela}" pattern="#,##0.00"/>"
             onblur="editaEmprestimos('editaTarifas','valorSantander${emprestimosBancos.idEmprestimo}',${emprestimosBancos.idEmprestimo},'valor','santanderTarifas','4');"
             /> 
          </td>
          <td>	
          		<c:if test="${emprestimosBancos.pago == true}">
	          		<a href="" class="btn btn-success" disabled="disabled">PAGO</a>
          		</c:if>
          		<c:if test="${emprestimosBancos.pago == false}">
	          		<a href="" class="btn btn-info">PAGAR</a>
          		</c:if>
          </td>
       </tr>
       <c:set var="totalSantander" value="${totalSantander+emprestimosBancos.valorParcela}" />
    </c:forEach>
    <tr>
    <tr>
       <td colspan="5"></td>
       <td style="height: 51px;vertical-align: middle;font-size: 15px" colspan="1">
       	<b><fmt:formatNumber value="${totalSantander}" pattern="#,##0.00"/></b>
       </td>
       <td></td>
    </tr>
</table>
