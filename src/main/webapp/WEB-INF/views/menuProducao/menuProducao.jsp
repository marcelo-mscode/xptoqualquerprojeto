<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../_comum/header.jsp" />
<style>
   .producao{background: #f1f1f1;}
</style>
<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div style="height: 80px;background: #e74c3c;box-shadow: 3px 0px 5px 5px #ccc;">
   <div class="row" style="margin-top: 0px;margin-left: 5px">
      <div class="col-md-12" style="margin-top: 10px">
         <span style="font-size: 25px;color: #fff;font-family:'OpenSansLight' ">PRODUÇÃO</span>
      </div>
   </div>
   <div class="col-md-12" style="color:#fff !important">
      <ol class="breadcrumb">
         <li><a href="menuProducao" style="color:#ecf0f1 !important">Menu</a></li>
         <li class="active" style="color:#bdc3c7">Listas de Produção Aprovadas</li>
      </ol>
   </div>
</div>
<div class="container">
   <div>
      <div class="row">
         <div class="col-md-12 painel ajuste-left" style="box-shadow: 0px 10px 30px 7px #ccc;margin-top: -6px">
            <div class="col-md-12">
               <table class="table table-striped table-td-ajuste table-sem-bold table-hover table-condensed" style="font-size: 12px;">
                  <tbody>
                     <tr>
                        <td colspan="6" style="text-align: right;padding-right: 68px !important">
                           <h3 style="margin: 0">${ano}</h3>
                        </td>
                     </tr>
                     <tr class="active">
                        <th class="col-md-4">Nome Lista</th>
                        <th>Empresa</th>
                        <th>Codigo da Lista</th>
                        <th>Data Evento</th>
                        <th>Data Aprovação</th>
                        <th>Aprovado Por</th>
                     </tr>
                     <c:forEach items="${data}" var="data">
                        <fmt:formatDate value="${data.time}" pattern="yyyy" var="anoAtual" />
                        <c:if test="${ano == anoAtual}">
                           <tr>
                              <td colspan="6">
                                 <fmt:formatDate value="${data.time}" pattern="MM" var="mesEvento"/>
                                 <c:forEach items="${mesesReferencia}" var="mesReferencia" varStatus="count">
                                    <c:if test="${count.count == mesEvento}">
                                       <b>${mesReferencia}</b>						  				
                                    </c:if>
                                 </c:forEach>
                              </td>
                           </tr>
                           <fmt:formatDate value="${data.time}" pattern="yyyy/MM" var="mesAno" />
                           <c:forEach items="${lista}" var="lista">
                              <fmt:formatDate value="${lista.dataDoEvento.time}" pattern="yyyy/MM" var="mesAnoEvento" />
                              <tr>
                                 <c:if test="${mesAno == mesAnoEvento}">
                                    <td class="alinhamentoVertical" style="padding-left: 20px !important"><a href="itensProducao?idLIsta=${lista.idLista}">${lista.lista}</a></td>
                                    <td class="alinhamentoVertical">${lista.idJob.empresa.empresa}</td>
                                    <td class="alinhamentoVertical">${lista.listaCod}.${lista.revisao}</td>
                                    <td class="alinhamentoVertical">
                                       <fmt:formatDate value="${lista.dataDoEvento.time}" pattern="dd/MM/yyyy"/>
                                    </td>
                                    <td class="alinhamentoVertical">
                                       <fmt:formatDate value="${lista.dataAprovacao.time}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                    </td>
                                    <td class="alinhamentoVertical">${lista.usuarioAprova.nome}</td>
                                 </c:if>
                              </tr>
                           </c:forEach>
                        </c:if>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
      </div>
   </div>
   <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</div>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->
<c:import url="../_comum/footer.jsp" />