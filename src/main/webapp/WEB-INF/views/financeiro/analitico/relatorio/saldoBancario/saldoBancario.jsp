<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="table table-bordered table-hover bordaDestaque" style="width: 16%;">
  <tbody>
     <tr>	
     	<td colspan="13" align="center" class="corEscritorio" style="border-right: 2px solid #ccc"><b>SALDO BANCÁRIO</b></td>
      </tr>
      <tr>
      	<td><b>Saldo ITAU</b></td>
      	<td class="campoSaldos">
      	<input id="dataSaldoAnteriorItau1" style="border: none;padding: 8px;line-height: 13px;"
      			<c:if test="${movimentoItau.dataAberturaCaixa == null}">placeholder="Data de Abertura Caixa" class="amareloFlat2"</c:if>
      			<c:if test="${movimentoItau.dataAberturaCaixa != null}">
      				value="<fmt:formatDate value="${movimentoItau.dataAberturaCaixa}" pattern="dd/MM/yyyy"/>"
      			</c:if>
                onclick="mudaCampoData('dataSaldoAnteriorItau1');"
                onblur="editaSaldos('dataSaldoAnteriorItau1',${idAnalitico},'data','1');"/>
      	</td>
      	<td class="campoSaldos">
      		<input id="valorAnteriorItau1" style="border: none;padding: 8px;line-height: 13px;"
      			<c:if test="${movimentoItau.valorAbertura == null}">placeholder="$Valor Abertura Caixa" class="amareloFlat2"</c:if>
      			<c:if test="${movimentoItau.valorAbertura != null}">
	      			 value="<fmt:formatNumber value="${movimentoItau.valorAbertura}" pattern="#,##0.00"/>"
      			</c:if>
             	onblur="editaSaldos('valorAnteriorItau1',${idAnalitico},'valor','1');"/>
      	</td>
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoItau.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoItau.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoItau.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo Itau</b></td>
      	<td class="campoSaldos">
      		<input id="dataSaldoFechamentoItau1" style="border: none;padding: 8px 10px 8px;line-height: 13px;"
      		<c:if test="${movimentoItau.dataFechamentoCaixa == null}">placeholder="Data Fechamento Caixa" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoItau.dataFechamentoCaixa != null}">
	      		value="<fmt:formatDate value="${movimentoItau.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/>"
      		</c:if>
      		
            onclick="mudaCampoData('dataSaldoFechamentoItau1');"
            onblur="editaSaldos('dataSaldoFechamentoItau1',${idAnalitico},'dataFechamento','1');"/>
      	</td>
      	<td><fmt:formatNumber value="${movimentoItau.valorAbertura -
      								   movimentoItau.totalTarifas + 
      								   movimentoItau.totalCreditos - 
      								   movimentoItau.totalDebitos}" pattern="#,##0.00"/></td>
      	<td class="campoSaldos">
      		<input id="valorAlternativoItau1" style="border: none;padding: 8px;line-height: 13px;"
      		<c:if test="${movimentoItau.valoresDefinir == null}">placeholder="$Valor definir" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoItau.valoresDefinir != null}">
	      		 value="<fmt:formatNumber value="${movimentoItau.valoresDefinir}" pattern="#,##0.00"/>"
      		</c:if>
             	onblur="editaSaldos('valorAlternativoItau1',${idAnalitico},'valorDefinir','1');"/>
      	</td>
      </tr>
      
      <tr>
      	<td><b>Saldo CEF</b></td>
      	<td class="campoSaldos">
      	<input id="dataSaldoAnteriorCEF2" style="border: none;padding: 8px;line-height: 13px;"
      		    <c:if test="${movimentoCef.dataAberturaCaixa == null}">placeholder="Data de Abertura Caixa" class="amareloFlat2"</c:if>
      			<c:if test="${movimentoCef.dataAberturaCaixa != null}">
		      	  value="<fmt:formatDate value="${movimentoCef.dataAberturaCaixa}" pattern="dd/MM/yyyy"/>"
      			</c:if>
                onclick="mudaCampoData('dataSaldoAnteriorCEF2');"
                onblur="editaSaldos('dataSaldoAnteriorCEF2',${idAnalitico},'data','2');"/>
      	</td>
      		<td class="campoSaldos">
      		<input id="valorAnteriorCEF2" style="border: none;padding: 8px;line-height: 13px;"
      		<c:if test="${movimentoCef.valorAbertura == null}">placeholder="$Valor Abertura Caixa" class="amareloFlat2"</c:if>
    		<c:if test="${movimentoCef.valorAbertura != null}">
	   		   value="<fmt:formatNumber value="${movimentoCef.valorAbertura}" pattern="#,##0.00"/>"
    		</c:if>
             	onblur="editaSaldos('valorAnteriorCEF2',${idAnalitico},'valor','2');"/>
      	</td>
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoCef.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoCef.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoCef.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo CEF</b></td>
      	<td class="campoSaldos">
      		<input id="dataSaldoFechamentoCEF2" style="border: none;padding: 8px 10px 8px;line-height: 13px;"
      		<c:if test="${movimentoCef.dataFechamentoCaixa == null}">placeholder="Data Fechamento Caixa" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoCef.dataFechamentoCaixa != null}">
	      		 value="<fmt:formatDate value="${movimentoCef.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/>"
      		</c:if>
      		
            onclick="mudaCampoData('dataSaldoFechamentoCEF2');"
            onblur="editaSaldos('dataSaldoFechamentoCEF2',${idAnalitico},'dataFechamento','2');"/>
      	</td>
      	<td><fmt:formatNumber value="${movimentoCef.valorAbertura -
      								   movimentoCef.totalTarifas + 
      								   movimentoCef.totalCreditos - 
      								   movimentoCef.totalDebitos}" pattern="#,##0.00"/></td>
      	<td class="campoSaldos">
      		<input id="valorAlternativoCEF2" style="border: none;padding: 8px;line-height: 13px;"
      		<c:if test="${movimentoCef.valoresDefinir == null}">placeholder="$Valor definir" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoCef.valoresDefinir != null}">
	      		 value="<fmt:formatNumber value="${movimentoCef.valoresDefinir}" pattern="#,##0.00"/>"
      		</c:if>
             	onblur="editaSaldos('valorAlternativoCEF2',${idAnalitico},'valorDefinir','2');"/>
      	</td>
      </tr>
      
      <tr>
      	<td><b>Saldo BRADESCO</b></td>
      	<td class="campoSaldos">
      		<input id="dataSaldoAnteriorBRADESCO3" style="border: none;padding: 8px 10px 8px;line-height: 13px;"
      		<c:if test="${movimentoBradesco.dataAberturaCaixa == null}">placeholder="Data de Abertura Caixa" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoBradesco.dataAberturaCaixa != null}">
	      		 value="<fmt:formatDate value="${movimentoBradesco.dataAberturaCaixa}" pattern="dd/MM/yyyy"/>"
      		</c:if>	
              onclick="mudaCampoData('dataSaldoAnteriorBRADESCO3');"
              onblur="editaSaldos('dataSaldoAnteriorBRADESCO3',${idAnalitico},'data','3');"/>
      	</td>
      	<td class="campoSaldos">
      		<input id="valorAnteriorBRADESCO3" style="border: none;padding: 8px;line-height: 13px;"
      		<c:if test="${movimentoBradesco.valorAbertura == null}">placeholder="$Valor Abertura Caixa" class="amareloFlat2"</c:if>
    		<c:if test="${movimentoBradesco.valorAbertura != null}">
	      		value="<fmt:formatNumber value="${movimentoBradesco.valorAbertura}" pattern="#,##0.00"/>"
    		</c:if>
           onblur="editaSaldos('valorAnteriorBRADESCO3',${idAnalitico},'valor','3');"/>
         </td>  
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoBradesco.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoBradesco.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoBradesco.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo BRADESCO</b></td>
      	<td class="campoSaldos">
      		<input id="dataSaldoFechamentoBRADESCO3" style="border: none;padding: 8px 10px 8px;line-height: 13px;"
      		<c:if test="${movimentoBradesco.dataFechamentoCaixa == null}">placeholder="Data Fechamento Caixa" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoBradesco.dataFechamentoCaixa != null}">
	      		 value="<fmt:formatDate value="${movimentoBradesco.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/>"
      		</c:if>
            onclick="mudaCampoData('dataSaldoFechamentoBRADESCO3');"
            onblur="editaSaldos('dataSaldoFechamentoBRADESCO3',${idAnalitico},'dataFechamento','3');"/>
      	</td>
      	<td><fmt:formatNumber value="${movimentoBradesco.valorAbertura -
      								   movimentoBradesco.totalTarifas + 
      								   movimentoBradesco.totalCreditos - 
      								   movimentoBradesco.totalDebitos}" pattern="#,##0.00"/></td>
      	<td class="campoSaldos">
      		<input id="valorAlternativoBRADESCO3" style="border: none;padding: 8px;line-height: 13px;"
      		<c:if test="${movimentoBradesco.valoresDefinir == null}">placeholder="$Valor definir" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoBradesco.valoresDefinir != null}">
	      		 value="<fmt:formatNumber value="${movimentoBradesco.valoresDefinir}" pattern="#,##0.00"/>"
      		</c:if>
      		
             	onblur="editaSaldos('valorAlternativoBRADESCO3',${idAnalitico},'valorDefinir','3');"/>
      	</td>
      </tr>
  
      <tr>
      	<td><b>Saldo SANTANDER</b></td>
      	<td class="campoSaldos">
      		<input id="dataSaldoAnteriorSANTANDER4" style="border: none;padding: 8px 10px 8px;line-height: 13px;"
      		<c:if test="${movimentoSantander.dataAberturaCaixa == null}">placeholder="Data de Abertura Caixa" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoSantander.dataAberturaCaixa != null}">
	      		 value="<fmt:formatDate value="${movimentoSantander.dataAberturaCaixa}" pattern="dd/MM/yyyy"/>"
      		</c:if>	
      		
                onclick="mudaCampoData('dataSaldoAnteriorSANTANDER4');"
                onblur="editaSaldos('dataSaldoAnteriorSANTANDER4',${idAnalitico},'data','4');"/>
      	</td>
      	<td class="campoSaldos">
      		<input id="valorAnteriorSANTANDER4" style="border: none;padding: 8px;line-height: 13px;"
      		<c:if test="${movimentoSantander.valorAbertura == null}">placeholder="$Valor Abertura Caixa" class="amareloFlat2"</c:if>
    		<c:if test="${movimentoSantander.valorAbertura != null}">
	      		value="<fmt:formatNumber value="${movimentoSantander.valorAbertura}" pattern="#,##0.00"/>"
    		</c:if>
      		
           onblur="editaSaldos('valorAnteriorSANTANDER4',${idAnalitico},'valor','4');"/>
         </td> 
      	<td><b>Tarifas</b></td>
      	<td><fmt:formatNumber value="${movimentoSantander.totalTarifas}" pattern="#,##0.00"/></td>
      	<td><b>Créditos</b></td>
      	<td><fmt:formatNumber value="${movimentoSantander.totalCreditos}" pattern="#,##0.00"/></td>
      	<td><b>Débitos</b></td>
      	<td><fmt:formatNumber value="${movimentoSantander.totalDebitos}" pattern="#,##0.00"/></td>
      	<td><b>Saldo SANTANDER</b></td>
      	<td class="campoSaldos">
      		<input id="dataSaldoFechamentoSANTANDER4" style="border: none;padding: 8px 10px 8px;line-height: 13px;"
      		<c:if test="${movimentoSantander.dataFechamentoCaixa == null}">placeholder="Data Fechamento Caixa" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoSantander.dataFechamentoCaixa != null}">
	      		 value="<fmt:formatDate value="${movimentoSantander.dataFechamentoCaixa}" pattern="dd/MM/yyyy"/>"
      		</c:if>
      		
            onclick="mudaCampoData('dataSaldoFechamentoSANTANDER4');"
            onblur="editaSaldos('dataSaldoFechamentoSANTANDER4',${idAnalitico},'dataFechamento','4');"/>
      	</td>
      	<td><fmt:formatNumber value="${movimentoSantander.valorAbertura -
      								   movimentoSantander.totalTarifas + 
      								   movimentoSantander.totalCreditos - 
      								   movimentoSantander.totalDebitos}" pattern="#,##0.00"/></td>
      	<td class="campoSaldos">
      		<input id="valorAlternativoSANTANDER4" style="border: none;padding: 8px;line-height: 13px;"
      		<c:if test="${movimentoSantander.valoresDefinir == null}">placeholder="$Valor definir" class="amareloFlat2"</c:if>
      		<c:if test="${movimentoSantander.valoresDefinir != null}">
	      		 value="<fmt:formatNumber value="${movimentoSantander.valoresDefinir}" pattern="#,##0.00"/>"
      		</c:if>
             	onblur="editaSaldos('valorAlternativoSANTANDER4',${idAnalitico},'valorDefinir','4');"/>
      	</td>
      </tr>
      
      
   </tbody>
</table>		