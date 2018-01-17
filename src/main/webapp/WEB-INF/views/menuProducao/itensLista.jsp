<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
	.producao {	background: #f1f1f1;}
</style>

<c:import url="../_comum/header.jsp" />
      <c:forEach items="${lista}" var="lista">
        <c:set value="${lista.lista}" var="titulo" />
        <c:set value="${lista.idLista}" var="idLista" />
        <c:set value="${lista.idJob.empresa.empresa}" var="empresa" />
        <c:set value="${lista.idJob.titulo}" var="tituloJob" />
        <c:set value="${lista.listaCod}" var="codLista" />
        <c:set value="${lista.valorTotal}" var="valorTotal" />
        <c:set value="${lista.impostoValor}" var="impostoValor" />
        <c:set value="${lista.subTotalCusto}" var="subTotalCusto" />
        <c:set value="${lista.administracaoValor}" var="administracaoValor" />
      </c:forEach>
      <c:forEach items="${categoria}" var="categoria">
        <c:forEach items="${categoria.grupo}" var="grupo">
          <c:forEach items="${grupo.produtoGrupo}" var="produtoGrupo">
            <c:set value="${produtoGrupo.producaop}" var="itemDeProducao" />
          </c:forEach>
        </c:forEach>
      </c:forEach>
<!-- - - - - - - - - - Container - - - - - - - - - - - -->
      <div style="height: 80px;background: #e74c3c" class="">
        <div class="row" style="margin-top: 0px;margin-left: 5px">
          <div class="col-md-12" style="margin-top: 10px">
            <span style="font-size: 25px;color: #fff;font-family:'OpenSansLight' ">
              PRODUÇÃO
            </span>
          </div>
        </div>

        <div class="col-md-12" style="color:#fff !important">
          <ol class="breadcrumb">
            <li>
              <a href="#" style="color:#ecf0f1 !important">Menu</a>
            </li>
            <li class="active" style="color:#ecf0f1 !important">
              <a href="menuProducao" style="color:#ecf0f1 !important">Listas de Produção Aprovadas</a>
            </li>
            <li class="active" style="color:#bdc3c7">
              Itens da Lista
            </li>
          </ol>
        </div>
      </div>
  
        <%-- <c:forEach items="${Total}" var="total" varStatus="cont">
          <c:set value="${cont.index}" var="contador" />
        </c:forEach> --%>
        <c:set value="valorValueX" var="empresa" />
 
        <div class="container">
          <div class="row">
            <div class="col-md-12 painel ajuste-left"  style="margin-bottom: 160px;">
              <div class="col-md-12">
                <div class="form-inline">
                  <h4 style="font-family: 'OpenSansLight'; font-size: 25px">
                    Nome da Lista:
                    <strong>
                      ${titulo}
                    </strong>
                  </h4>

                  <table class="table table-sem-bold table-condensed" style="font-size: 12px;">
                    <tbody>
                      <c:forEach items="${categoria}" var="categoria">
                        <tr>
                          <td colspan="5" class="coluna-categoria">
                            <span style="font-size: 18px;">
                              ${categoria.categoria}
                            </span>
                          </td>
                        </tr>
                        <c:forEach items="${categoria.grupo}" var="grupo">
                          <c:if test="${grupo.opcional == false }">
                         
                          <tr class="border-teste">
                            <td class="alinhamentoVertical border-top-table-top" colspan="5">
                              ${grupo.grupo}
                            </td>
                            <%-- <td class="alinhamentoVertical border-top-table-top" style="width: 150px;">
                              <c:if test="${grupo.grupoValorIncideImposto == 0}">
                                Fat Locco R$ 0,00
                              </c:if>
                              <c:if test="${grupo.grupoValorIncideImposto > 1}">
                                Fat Locco R$
                                <fmt:formatNumber value="${grupo.grupoValorIncideImposto}" pattern="#,##0.00"
                                />
                              </c:if>
                            </td>
                            <td class="alinhamentoVertical border-top-table-top">
                              Fat Direto R$
                              <fmt:formatNumber value="${grupo.grupoValorNaoIncideImposto}" pattern="#,##0.00"
                              />
                            </td> --%>
                           <!--  <td colspan="2" class="alinhamentoVertical border-top-table-top">
                            </td> -->
                          </tr>
                          <tr>
                            <td colspan="5">
                              <table class="table" style="font-family: 'OpenSansLight'; margin-bottom: 0px;">
                                <c:forEach items="${grupo.produtoGrupo}" var="produtoGrupo" varStatus="loop">
                                  
                                  <c:if test="${not empty grupo.produtoGrupo}">
                                    <c:if test="${produtoGrupo.status == 1 }">
                                      <tr class="border-top-table-1 concluido">
                                    </c:if>
                                    <c:if test="${produtoGrupo.status != 1 }">
                                      <tr class="border-top-table-1">
                                    </c:if>
                                    <td style="width: 180px">
                                      ${produtoGrupo.produto}
                                    </td>
                                    <%-- <td style="width: 152px;">
                                      Custo R$ <fmt:formatNumber value="${produtoGrupo.custoProduto}" pattern="#,##0.00" />
                                    </td> --%>
                                    <td style="width: 155px;">
                                      Preço R$
                                      <fmt:formatNumber value="${produtoGrupo.precoProduto*(produtoGrupo.quantidade*produtoGrupo.quantidade2*produtoGrupo.diarias)}" pattern="#,##0.00"/>
                                    </td>
                                    <c:if test="${empty produtoGrupo.empresa.idEmpresa}">
                                      <td>
                                        Esse item dessa Lista é anterior a criação do Módulo Produção OU não existe
                                        fornecedor associado com esse item.
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalFornecedores">
                                          Entenda essa mensagem
                                        </button>
                                      </td>
                                    </c:if>
                                    <c:if test="${not empty produtoGrupo.empresa.idEmpresa}">
                                      <td class="input-360px">
                                        Fornecedor:
                                        <a class="btn btn-link" href="itemListaAjax?idFornecedor=${produtoGrupo.empresa.idEmpresa}&idLista=${idLista}&idProdutoGrupo=${produtoGrupo.idProdutoGrupo}"
                                        style="line-height: 0px; font-size: 18px;">${produtoGrupo.empresa.empresa}</a>
                                      </td>
                               <!-- ----------------------------  -->      
                                      <c:if test="${empty produtoGrupo.producaop}">
  	                                      <td style="width: 220px;border-left: 1px solid #ccc !important;border-right: 0px solid #ccc !important;">
	                                          <i class="glyphicon glyphicon-pencil cor-lapis" onclick="trocarModalFornecedor(${produtoGrupo.idProdutoGrupo});"
	                                          style="cursor: pointer; font-size: 16px; margin-left: 15px; margin-top: -5px;">
	                                          </i>
	                                          <span style="margin-left: 25px;font-size: 12px;">Trocar fornecedor</span>
	                                          
	                                      </td>
                                      </c:if>
							  <!-- ---------------------------- -->
                                      <c:if test="${not empty produtoGrupo.producaop}">
  	                                      <td style="width: 52px;">
	                                         
	                                      </td>
                                      </c:if>

                                      <td>
                                     	<%-- <c:if test=""> --%>	
                                      	<!-- 	<i class="glyphicon glyphicon-list-alt"></i> -->
                                        <%-- </c:if> --%>
                                      </td>
                                      
                                    </c:if>
                                    <!-- Se produtoGrupo tiver relação com Producao, exibir botão ativo -->
                                    <c:choose>
                                      <c:when test="${produtoGrupo.status == 1 }">
                                        <td style="width: 136px; border-left: 1px solid #ddd !important">
                                          <button type="button" class="btn btn-default" style="float: right" disabled="disabled">
                                            Item Fechado
                                          </button>
                                        </td>
                                      </c:when>
                                      <c:when test="${empty produtoGrupo.producaop}">
                                        <td style="width: 136px; border-left: 1px solid #ddd !important">
                                          <button type="button" class="btn btn-primary" style="float: right" disabled="disabled">
                                            Consolidar
                                          </button>
                                        </td>
                                      </c:when>
                                      <c:when test="${not empty produtoGrupo.producaop}">
                                        <td style="width: 136px; border-left: 1px solid #ddd !important">
                                          <form action="fechaItem" method="post">
                                            <input type="hidden" value="${produtoGrupo.idProdutoGrupo}" name="idDoProdutoGrupo"/>
                                            <input type="hidden" value="${idLista}" name="idLista" />
                                            <button type="submit" class="btn btn-success" style="float: right">
                                              Fechar Item
                                            </button>
                                          </form>
                                        </td>
                                      </c:when>
                                    </c:choose>
                                    </tr>
<!-- ------------------------------------------------------------------- -->
                                    <tr id="itemProducao${produtoGrupo.idProdutoGrupo}" class="display-none">
                                      <td colspan="5">
                                        <div class="col-md-5" style="height: 150px">
                                        </div>
                                        <div class="col-md-5" style="height: 150px">
                                          <img src="resources/images/ajax_Loading.gif" width="221" height="38" class="loader"
                                          alt="loading" style="margin-top: 60px;">
                                        </div>
                                      </td>
                                    </tr>
<!-- -------------------------------------------------------------------- -->
                                  </c:if>
                                  <c:if test="${empty grupo.produtoGrupo}">
                                    Nenhum item associado
                                  </c:if>
                                  <tr>
                                    <td colspan="6">
                                      <div class="row">
                                        <div id="fornecedorMuda${produtoGrupo.idProdutoGrupo}" class="col-md-12 form-inline display-none"
                                        style="height: 117px; border: 1px solid #ddd; padding: 20px 20px 20px 10px; margin-bottom: 10px; border-radius: 5px;">
                                          <p>
                                            Trocar de Fornecedor
                                          </p>
                                          <select class="form-control input-180px" id="idEmpresa${produtoGrupo.idProdutoGrupo}">
<!-- arrumar aqui !!!  -->	                                           
	                                            <%-- <c:forEach items="${fornecedoresLista}" var="fornecedoresLista">
	                                              <option value="${fornecedoresLista[0]}">
	                                                ${fornecedoresLista[1]}
	                                              </option>
	                                            </c:forEach> --%>
	                                            
	                                            
                                          </select>
                                          &nbsp&nbsp&nbsp
                                          <a class="btn btn-danger" onclick="trocarFornecedor(${produtoGrupo.idProdutoGrupo});">Trocar Agora</a>
                                          <br>
                                        </div>
                                      </div>
                                    </td>
                                  </tr>
                                
                                </c:forEach>
                              </table>
                            </td>
                            </tr>
                            </c:if>
                        </c:forEach>
                      </c:forEach>
                    </tbody>
                  </table>
                  
                </div>
              </div>
            </div>
          </div>
         
          <br>
        </div>
        <!-- - - - - - - - - - Fim Container - - - - - - - - - -->
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                style="margin-top: -12px;">
                  <span aria-hidden="true" style="font-size: 50px;font-family: 'OpenSansLight';font-weight: normal;">
                    &times;
                  </span>
                </button>
                <h4 class="modal-title" id="myModalLabel" style="font-family: 'OpenSansLight'">
                  Porque recebi essa mensagem ?
                </h4>
              </div>
              <div class="modal-body">
                <b>
                  Esse item dessa Lista é anterior a criação do Módulo de Produção OU não
                  existe fornecedor associado com esse item
                </b>
                <br>
                <br>
                <b>
                  Entenda:
                </b>
                antes da criação do Módulo de Produção não era obrigatório associar um
                fornecedor à um produto, agora é totalmente necessário, uma vez que a carta
                de contratação e as negociações com os fornecedores serão geradas com base
                nessa associação.
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                  Close
                </button>
              </div>
            </div>
          </div>
        </div>
        
        <!-- Modal Fornecedores-->
        <div class="modal fade" id="myModalFornecedores" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          
        </div>
        
  <c:import url="../_comum/footer.jsp" />