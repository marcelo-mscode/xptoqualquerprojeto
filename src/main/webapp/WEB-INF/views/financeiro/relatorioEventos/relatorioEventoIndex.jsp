<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../../_comum/header.jsp" />
<style type="text/css">
   .financeiro{background: #f1f1f1;}
   .cabecalhoLista {text-align: center !important;line-height: 33px !important;}
   .cabecalhoLista > td {line-height: 33px !important;}
   .descricao{padding-left: 15px !important;}
</style>
<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ccc;">
   <ol class="breadcrumb">
      <li><a href="index.html">Menu</a></li>
      <li class="active">Relatório de Eventos</li>
   </ol>
</div>
<div style="height: 35px;">
   <div class="col-md-12" style="margin: 10px 0px 25px -14px;/* border-bottom: 1px solid #ccc; */padding-bottom: 10px;">
      <span style="font-family: 'OpenSansLight';font-size: 25px;margin-left: 32px;">RELATÓRIO DE EVENTOS</span>
   </div>
</div>
<div id="criacaoListas" class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';border-top: 1px solid #ccc;padding:35px 40px 70px 35px;">
   <div style="padding: 20px;box-shadow: 0px 0px 30px 5px #ccc;" class="col-md-12">
      <div class="col-md-12" style="padding-right: 0;">
         <table class="table table-bordered table-hover table-condensed" >
            <tbody id="prospeccaoFiltro">
               <tr style="background: #f1f1f1 !important;">
               <td colspan="1">Data</td>
                  <td colspan="2" align="center">Evento</td>
                  <td>Empresa</td>
               </tr>
               
               <c:forEach items="${ano}" var="ano" >
                        <tr>
                           <td colspan="4">
                              <h4>${ano}</h4>
                           </td>
                        </tr>
                  <c:forEach items="${listaRelatorioEventos}" var="listaRelatorioEventos">
                     <c:if test="${ano == listaRelatorioEventos.anoEvento}">
                        <tr>
						   	
                           <c:forEach items="${listasProducao}" var="listasProducao" varStatus="loops">
                              <c:if test="${listaRelatorioEventos.idLista == listasProducao.idLista}">
	                             <td>${listaRelatorioEventos.mesEvento}</td>
<%-- 	                             <td  class="descricao">EVENTO ${loops.count}</td> --%>
                                 <td colspan="2"><a href="relatorioEventoIndividual?idLista=${listaRelatorioEventos.idLista}&idRelatorioEvento=${listaRelatorioEventos.idRelatorioEvento}">${listasProducao.lista}</a></td>
                                 <td>${listasProducao.idJob.empresa.empresa}</td>
                              </c:if>
                           </c:forEach>


                        </tr>
                        
                     </c:if>
                  </c:forEach>
               
               
               </c:forEach>
               
               
            </tbody>
         </table>
      </div>
   </div>
</div>
<c:import url="../../_comum/footer.jsp" />