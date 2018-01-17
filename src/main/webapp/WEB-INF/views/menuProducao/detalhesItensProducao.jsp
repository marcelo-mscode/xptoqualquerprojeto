<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
    .producao{background: #f1f1f1;}
</style>

<c:import url="../_comum/header.jsp" />
<!-- - - - - - - - - - Container - - - - - - - - - - - -->

<c:set value="${produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias}" var="quantidadeItem" />
<!--  ------------------------------------------------------------------------------------------------------ -->        

<c:set value="${idLista}" var="idLista" />

<c:if test="${producaoP.idProducao != null}">
    <c:set value="${producaoP.prazoPagamento}" var="prazoPagamento" />
    <c:set value="${producaoP.prazoEntrega}" var="prazoEntrega" />
    <c:set value="${producaoP.idProducao}" var="idProducao" />
    <c:set value="${producaoP.diasPrazo}" var="diasPrazo" />
    <c:set value="${producaoP.usuario.nome}" var="responsavel" />
    <c:set value="${producaoP.idProducao}" var="idProducao" />
    <c:set value="${producaoP.tipoPagamento}" var="tiposPagamento" />
    <c:set value="${producaoP.diferenca}" var="diferenca" />
    <c:set value="${producaoP.localEntrega}" var="localEntrega" />
    <c:set value="${producaoP.valorContratacao}" var="valorContratacao" />
    <c:set value="${producaoP.valorItem}" var="valorItem" />
    <c:set value="${producaoP.referenciaEntrega}" var="referenciaEntrega" />
    <c:set value="${producaoP.obs}" var="obs" />
    <c:set value="${producaoP.numParcelas}" var="qtdParcelas" />

    <c:set value="${producaoP.cartaContracao.idCarta}" var="idCartaAtualiza" />

    <c:set value="${producaoP.cartaContracao.empresaCabecalho}" var="empresaCabecalho" />
    <c:set value="${producaoP.cartaContracao.empCab3}" var="empCab3" />
    <c:set value="${producaoP.imposto}" var="imposto" />
    <c:set value="${producaoP.valorDePagamentoContratacao}" var="valorDePagamentoContratacao" />
</c:if>	
<!--  ------------------------------------------------------------------------------------------------------ -->  


<div style="height: 80px;background: #e74c3c" class="">
    <div class="row" style="margin-top: 0px;margin-left: 5px">
        <div class="col-md-12" style="margin-top: 10px">
            <span style="font-size: 25px;color: #fff;font-family:'OpenSansLight' ">PRODUÇÃO</span>
        </div>
    </div>
    <div class="col-md-12" style="color:#fff !important">
        <ol class="breadcrumb">
            <li><a href="#" style="color:#ecf0f1 !important">Menu</a></li>
            <li class="active" style="color:#ecf0f1 !important"><a href="menuProducao" style="color:#ecf0f1 !important">Listas de Produção Aprovadas</a></li>
            <li class="active" style="color:#ecf0f1 !important"><a href="itensProducao?idLIsta=${idLista}" style="color:#ecf0f1 !important">Itens da Lista</a></li>
            <li class="active" style="color:#bdc3c7">Detalhes do Item</li>
        </ol>
    </div>
</div>

<div style="height: 150px;background: #fff" class="">
    <div class="row" style="margin-top: 0px;margin-left: 5px">

        <div class="col-md-2" style="margin-right:50px;margin-top: 10px;font-family:'OpenSansLight';font-weight: bolder; ">
            <h5 style="font-weight: bolder;">Informações Lista</h5>
        </div>    

        <div class="col-md-6" style="margin-top: 10px;font-family:'OpenSansLight' ">
            <h4>Lista: ${lista.lista}</h4>
            <h4>Job: ${InfoJobs.idJob.titulo}</h4>
            <h4>Empresa: ${InfoJobs.idJob.empresa.empresa}</h4>
            <h4>Data do Evento:

                <c:if
                    test="${empty InfoJobs.localEventoDataTermino}">
                    <span style="color: red">Local e Data do evento não cadastrados</span>
                </c:if>
                <c:if test="${not empty InfoJobs.localEventoDataTermino}">
                    <fmt:formatDate value="${InfoJobs.localEventoDataTermino}"
                                    pattern="dd/MM/YYYY HH:mm" />
                </c:if>
            </h4>
            
        </div>
        
        <div class="col-md-2" style="margin-top: 10px;font-family:'OpenSansLight' ">
         
        <c:if test="${not empty CartaNovaTeste}">	
		  <a href="#CartaFornecedor" class="btn btn-primary" style="margin-bottom: 10px">Carta Contratação</a>
		</c:if>
        <c:if test="${not empty outroFornx}">
		 <a href="#OutroFornecedor" class="btn btn-success">Carta Outro Fornecedor</a>
        </c:if>


        </div>
        
        
        
    </div>	
</div>
<div style="height: 170px;background: #fff;border-bottom:1px solid #ddd; " class="">
    <div class="row" style="margin-top: 0px;margin-left: 5px">

        <div class="col-md-2" style="margin-right:50px;margin-top: 10px;font-family:'OpenSansLight';font-weight: bolder;border-top: 1px solid #ddd; ">
            <h5 style="font-weight: bolder;">Informações Item</h5>
        </div>

        <div class="col-md-3" style="margin-top: 10px;font-family:'OpenSansLight';border-top: 1px solid #ddd;">
            <h4><strong>Item:</strong>&nbsp ${produtoGrupo.produto}<br /></h4>
            <c:set value="${produtoGrupo.precoProduto * quantidadeItem}" var="fatLocco" />
            <h4><strong>Fat LoCCo:</strong>
                <c:if test="${produtoGrupo.imposto == true}">
                    &nbsp&nbspR$&nbsp<fmt:formatNumber value="${fatLocco}" pattern="#,##0.00" />
                </c:if>

                <c:if test="${produtoGrupo.imposto == false}">
                    &nbsp&nbsp---
                </c:if>
            </h4>
            <c:set value="${produtoGrupo.precoProduto * quantidadeItem}" var="fatDireto" />
            <h4><strong>Fat Direto:</strong>
                <c:if test="${produtoGrupo.imposto == false}">
                    &nbsp&nbspR$<fmt:formatNumber value="${fatDireto}" pattern="#,##0.00" />
                </c:if>
                <c:if test="${produtoGrupo.imposto == true}">
                    &nbsp&nbsp--- 
                </c:if>
            </h4>
            <h4><strong>Quantidade:&nbsp</strong>${quantidadeItem}</h4>
        </div>
        <div class="col-md-4" style="margin-top: 10px;font-family:'OpenSansLight';border-top: 1px solid #ddd;">
            <h4><strong>Categoria:</strong>&nbsp ${produtoGrupo.idGrupo.idCategoria.categoria}</h4>
            <h4><strong>Linha:</strong>&nbsp ${produtoGrupo.idGrupo.grupo}</h4>
        </div>

    </div>

</div>

<div class="container">
    <div class="col-md-12" style="padding: 25px">

        <div class="row" style="border-bottom: 1px solid #ccc">
            <div class="col-md-12">
                <h4 style="font-family: 'OpenSansLight'">
                    Data do Evento: 
                    <strong>
                        <c:if
                            test="${empty InfoJobs.localEventoDataTermino}">
                            <span style="color: red">Local e Data do evento não
                                cadastrados</span>
                        </c:if>
                        <c:if test="${not empty InfoJobs.localEventoDataTermino}">
                            <fmt:formatDate value="${InfoJobs.localEventoDataTermino}"
                                            pattern="dd/MM/YYYY" />
                        </c:if>
                    </strong>
                </h4>
            </div>
        </div>

        <form action="salvaItem" method="post" id="salvaItemProducao">

            <input type="hidden" value="${idProducao}" name="idProducao" />
            <input type="hidden" value="${idLista}" name="idLista" id="idLista" />
            <input type="hidden" value="${idProdutoGrupo}" name="idProdutoGrupo" id="produtoGrupo" />
            <input type="hidden" value="${idCartaAtualiza}" name="idCarta" />
            <input type="hidden" value="${produtoGrupo.empresa.idEmpresa}" name="idEmpresaTransiente" />




            <div class="row" id="fornecedorAtual" style="border-bottom: 1px solid #ccc;padding-bottom: 10px;">
                <div class="col-md-8">
                    <h4 style="font-family: 'OpenSansLight'">
                        Fornecedor: <strong>${fornecedor.empresa}</strong>
                    </h4>
                </div>
                <div class="col-md-4">
                    <h4 style="font-family: 'OpenSansLight'">
                        CNPJ: <strong>${fornecedor.cnpj}</strong>
                    </h4>
                </div>

                <div class="col-md-4">
                    <h4 style="font-family: 'OpenSansLight'">
                        Contato no Fornecedor:
                        <select class="form-control input-180px" name="contatoFornecedor">
                            <c:forEach items="${contatoFornecedor}" var="contato">
                                <option value="${contato[0]}">${contato[1]}</option>
                            </c:forEach>
                        </select>
                    </h4>
                </div>

            </div>

            <div class="row">
                <label style="margin-left: 13px;padding-top: 10px">Informações de entrega</label>
                <div class="col-md-12 tira-padding" style="padding-top: 10px">
                    <div class="col-md-3">Data de Entrega *</div>
                    <div class="col-md-5">
                        <input type="text" class="form-control data" style="width: 110px" value='<fmt:formatDate value="${prazoPagamento}" pattern="dd/MM/yyyy" />' name="pEntrega" />
                    </div>
                </div>
            </div>
            <div class="row" style="border-bottom: 1px solid #ccc;padding-bottom: 20px">
                <div class="col-md-12 tira-padding">
                    <div class="col-md-3">Local de Entrega *</div>
                    <div class="col-md-3">
                        <select class="form-control input-180px" name="localEntrega"
                                onchange="localentrega();" id="localEntrega">
                            <c:if test='${referenciaEntrega == "agencia"}'>
                                <option value="agencia">Agência Locco</option>
                                <option value="localevento">Local do Evento</option>
                            </c:if>
                            <c:if test='${referenciaEntrega == "localevento"}'>
                                <option value="localevento">Local do Evento</option>
                                <option value="agencia">Agência Locco</option>
                            </c:if>
                            <c:if test='${empty referenciaEntrega}'>
                                <option value="agencia">Agência Locco</option>
                                <option value="localevento">Local do Evento</option>
                            </c:if>
                        </select>
                    </div>
                    <div class="col-md-6" style="padding-top: 7px; height: 34px;">
                        <c:if test="${empty localEntrega}">
                            <p id="localEvento">&bull; Rua Barão de Jaceguai, 418-São
                                Paulo/SP - CEP 04606-000
                            </p>
                        </c:if>
                        <c:if test="${not empty localEntrega}">
                            <p id="localEvento">&bull; ${localEntrega}</p>
                        </c:if>
                    </div>
                </div>
            </div>


            <!-- ---------------------------------------------------------------------------------------------------- -->

            <div class="row">
                <span>Informações de pagamento</span>
                <br>
                <br>
                <div class="col-md-12 tira-padding" style="padding-bottom: 20px">
                    <div class="col-md-3">

                        <c:choose>
                            <c:when test="${empty producaoP 
                            				|| producaoP.temContratacao == false 
                            				&& producaoP.temMesmoFornecedor == false 
                            				&& producaoP.temOutroFornecedor == false}">
                                <!-- se não tiver valor de contratacao salvo -->
                                <input type="radio" name="infoPag" checked="checked" value="0" onclick="efeitoHide(divValorContratacao);">&nbsp&nbspSem CL
                            </c:when>

                            <c:when test="${not empty producaoP 
                            				&& producaoP.temContratacao == true 
                            				&& producaoP.temMesmoFornecedor == true 
                            				&& producaoP.temOutroFornecedor == false}">
                                <input type="radio" name="infoPag" value="0" onclick="efeitoHide(divValorContratacao);">&nbsp&nbspSem CL
                                <!-- se tiver valor de contratacao e for mesmo fornecedor -->
                            </c:when>

                            <c:when test="${not empty producaoP 
                            				&& producaoP.temContratacao == true 
                            				&& producaoP.temMesmoFornecedor == false 
                            				&& producaoP.temOutroFornecedor == true}">
                                <input type="radio" name="infoPag" value="0" onclick="efeitoHide(divValorContratacao);">&nbsp&nbspSem CL
                                <!-- se tiver valor de contratacao e for mesmo fornecedor -->
                            </c:when>
                            
                        </c:choose>

                    </div>
                    <div class="col-md-3">

                        <c:choose>
                            <c:when test="${empty producaoP
											|| producaoP.temContratacao == false 
                            				&& producaoP.temMesmoFornecedor == false 
                            				&& producaoP.temOutroFornecedor == false}">
	
                                <!-- se não tiver valor de contratacao salvo -->
                                <input type="radio" name="infoPag" value="1" onclick="efeitoShow(divValorContratacao);">&nbsp&nbspCom CL
                                <!-- <input type="radio" name="infoPag" value="1" onclick="efeitoShow(divValorContratacao);">&nbsp&nbspCom CL -->
                            </c:when>

                            <c:when test="${not empty producaoP && producaoP.temContratacao == true}">
                                <input type="radio" name="infoPag" checked="checked" value="1" onclick="efeitoShow(divValorContratacao);">&nbsp&nbspCom CL
                                <!-- se tiver valor de contratacao e for mesmo fornecedor -->
                            </c:when>
								
                        </c:choose>

                    </div>

                </div>
            </div>
<!-- ---------------------------------------------------------------------------------------------------- -->

	<div class="row 
            <c:choose>
  
                <c:when test="${empty producaoP || producaoP.temContratacao == false}">
        			display-none
                </c:when>
                <c:when test="${empty producaoP && producaoP.temContratacao == true}">
                </c:when>
                
            </c:choose>		
            " style="border: 1px solid gray;padding: 15px;margin-bottom: 20px;" id="divValorContratacao">               

            <c:if test="${produtoGrupo.imposto == true}">
                <c:set value="${fatLocco}" var="valorItem" />
            </c:if>
  
            <c:if test="${produtoGrupo.imposto == false}">
                <c:set value="${fatDireto}" var="valorItem" />
            </c:if>
  
            <c:import url="fornFinanc/valorContratacao.jsp">
                <c:param name="valorItem" value="${valorItem}" />
                <c:param name="imposto" value="${imposto}" />
                <c:param name="diferenca" value="${diferenca}" />
                <c:param name="valorDePagamentoContratacao" value="${valorDePagamentoContratacao}" />
                <c:param name="valorContratacao" value="${valorContratacao}" />
                <c:param name="producao" value="${producao}" />
            </c:import>

    </div>

<!-- ---------------------------------------------------------------------------------------------------- -->

    <div class="col-md-12 tira-padding">
        <div class="col-md-3">Tipo pagamento *</div>
        <div class="col-md-5">
            <select class="form-control input-180px" name="tipoPagamento">
                <c:forEach items="${tipoPagamento}" var="tipoPagamento">
                    <c:if test="${tiposPagamento == tipoPagamento.tipoPagamento}">
                    </c:if>
                    <c:if
                        test="${tipodePagamento != tipoPagamento.tipoPagamento}">
                        <option value="${tipoPagamento.tipoPagamento}">${tipoPagamento.tipoPagamento}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="col-md-12 tira-padding" style="padding-top: 10px">
        <div class="col-md-3">Parcelamento *</div>
        <div class="col-md-5">
            <select class="form-control input-80px" name="numParcelas" onchange="parcelamento(this.value);">

                <c:if test="${empty qtdParcelas}"></c:if>	
                <c:if test="${not empty qtdParcelas}">	
                    <option value="${qtdParcelas}">${qtdParcelas}</option>
                </c:if>

                <c:forEach items="${parcelasPadrao}" var="numDaParcela" varStatus="status">
                    <c:if test="${qtdParcelas == numDaParcela}">

                    </c:if>
                    <c:if test="${qtdParcelas != numDaParcela}">
                        <option value="${numDaParcela}">${numDaParcela}</option>
                    </c:if>
                </c:forEach>

            </select>
        </div>
    </div>

    <!-- -----------------------------------------------------------------------------------------------------  -->
    <!-- Parcelamentos -->            
    <div class="row">
        <div class="col-md-6" style="background: #fff;padding: 0 20px;margin-top: 10px;padding: 20px 10px;border: 1px solid #ccc" id="tableParcelas">
            <table class="table table-bordered table-hover" style="margin-top: 20px;">

                <tr>
                    <th>Parcela</th>
                    <th class="input-160px">Prazo Pagamento *</th>
                    <th>Data</th>
                    <th>Valor R$</th>
                </tr>

                <c:forEach   items="${fornecedorFinanceiro}" var="valor" >  

                    <c:forEach items="${valor.idValorPgtoFornecedor}" var="valorPagto" varStatus="status">
                        <c:if test="${valor.idEmpresa ==  fornecedor}">	

                            <tr class="testePegaValorInput">
                                <td style="padding: 0; text-align: center; vertical-align: middle; width: 50px;">
                                    <input type="hidden" value="${valorPagto.parcela}" name="parcela[${status.index}]" />
                                    ${valorPagto.parcela}
                                </td>

                                <td style="padding: 0; width: 135px;"> 
                                    <input type="text" class="input-quantDeterm" style="width: 110px; text-align: center;"
                                           name="prazo[${status.index}]" value="${valorPagto.diasPrazoParaPagamento}" onblur="calculaPrazo(${valorPagto.parcela}, 'data[${status.index}]');" id="diasPrazo${valorPagto.parcela}" />
                                </td>

                                <td id="prazoPagamento${valorPagto.parcela}" style="padding: 0; text-align: center; vertical-align: middle; width: 135px;">
                                    <input type="hidden" value="${valorPagto.dtPgotFornecedor.dataPagar}" name="data[${status.index}]" />
                            <fmt:formatDate value="${valorPagto.dtPgotFornecedor.dataPagar}" pattern="dd/MM/yyyy" />
                            </td>

                            <td style="padding: 0;">
                            <c:if test="${not status.last}">
                                <input id="valorItem${valorPagto.parcela}" type="text" class="input-quantDeterm valorItem${valorPagto.parcela}" style="width: 110px" name="valor[${status.index}]"
                                       value='<fmt:formatNumber value="${valorPagto.valor}" pattern="#,##0.00"/>'
                                       onblur="calculaPagamento(${qtdParcelas}, ${produtoGrupo.idProdutoGrupo}, 'valorItem${valorPagto.parcela}', ${valorPagto.parcela});" />
                            </c:if>

                            <c:if test="${status.last}">
                                <input id="valorItem${valorPagto.parcela}" type="text" class="input-quantDeterm valorItem${valorPagto.parcela} is-disabled" style="width: 110px" name="valor[${status.index}]"
                                       value='<fmt:formatNumber value="${valorPagto.valor}" pattern="#,##0.00"/>' />
                            </c:if>

                            </td>
                            </tr>
                        </c:if>
                    </c:forEach>

                </c:forEach>

            </table>
        </div>
    </div>

    <!-- ----------------------------------------------------------------------------------------------------------------  -->

    <div class="row"  style="border-top: 1px solid #ccc;padding-top: 20px">
        <div class="col-md-12 tira-padding">
            <div class="col-md-3">Responsável Contraaaatação *&nbsp&nbsp&nbsp ${user.usuario.nome}
            </div>
            <div class="col-md-3">
                <select class="form-control" style="width: 230px" name="idUsuario">
                    
                    <option value="">${user.usuario.nome}</option>
                    
                    <c:forEach items="${usuarios}" var="usuarios">
                    
                        <option value="${usuarios[0]}">${usuarios[1]}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                <p>&bull;&nbsp${responsavel}</p>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 tira-padding">
            <div class="col-md-3">
                Observações:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            </div>
            <div class="col-md-5">
                <textarea class="form-control" rows="5" cols="35" name="obs">${obs}</textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 tira-padding">
            <div class="col-md-5">

                <c:if test="${empty InfoJobs.localEventoDataTermino}">
                    <span style="display: block;padding: 7px;background: red;color: #ecf0f1;width: 360px;border-radius: 4px;
                          margin-top: 20px;">Não tem uma data de término do Evento Cadastrada</span>
                </c:if>
                <c:if test="${not empty InfoJobs.localEventoDataTermino && produtoGrupo.status != 1}">
                    <input type="submit" class="btn btn-danger consolidaBotao" value="Consolidar Item" id="">
                    <span id="itemImage" style="margin-left: 15px"></span>
                    <span id="itemMsg" style="margin-left: 15px"></span>
                </c:if>

                <c:if test="${produtoGrupo.status == 1}">
                    <input class="btn btn-primary  is-disabled" value="Item Fechado">
                </c:if>


            </div>
        </div>
    </div>
</form>
</div>
</div>
<div class="divisor" style="border-bottom: 1px solid #ddd"></div>
<div class="" >

	<a name="CartaFornecedor"></a>
    <div class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 218px;">
        <h2 style="text-transform: uppercase;font-weight: bolder;letter-spacing: -2px;">Carta de Contratação</h2>
        <c:if test="${empty CartaNovaTeste}">
            <div class="col-md-12" style="background: #fff">
                Item não preenchido
            </div>		
        </c:if>
    </div>



    <c:if test="${not empty CartaNovaTeste}">
        <div class="col-md-10 painel shadow-com-padding" style="margin-left: 80px;margin-bottom: 70px;margin-top: -95px;padding-left: 40px;">
            <div class="divisor"></div>
            <div class="divisor"></div>
            <form action="atualizaCarta" method="post">
                <input type="hidden" value="${CartaNovaTeste.idCartaForn}" name="idCartaForn">
                <input type="hidden" value="${fornecedor.idEmpresa}" name="idFornecedorTrans">
                <input type="hidden" value="${fornecedor.idEmpresa}" name="idFornecedor">
                <input type="hidden" value="${idLista}" name="idLista">
                <input type="hidden" value="${idProdutoGrupo}" name="idProdutoGrupo">

                <div class="row">
                    <div class="col-md-12">
                        <img alt="" src="resources/images/locco.png" width="240" height="80" style="margin-bottom: 20px;margin-left: 20px;">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <input class="form-control border-none" name="dataCabecalho" value='São Paulo, ${dataHoje[0]} de ${dataHoje[1]} de ${dataHoje[2]}'>
                        </div>
                    </div>
                </div>	

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-5">
                            <input type="text" name="a" class="form-control border-none input-120px" value="${outraCarta.a}" >
                            <input type="text" name="empresa" class="form-control border-none input-420px font-bold cartaConfere" value="${CartaNovaTeste.empresa}" >
                            <input type="text" name="fornecedorContato" class="form-control border-none input-420px font-bold cartaConfere" value="${CartaNovaTeste.fornecedorContato}" >
                            <input type="text" name="fornecedorContatoEmail" class="form-control border-none input-420px font-bold cartaConfere" value="${CartaNovaTeste.fornecedorContatoEmail}" >
                        </div>
                    </div>
                </div>	

                <div class="row">
                    <div class="col-md-5">
                        <div class="col-md-12 form-inline">
                            <input type="text" name="empCab1" class="form-control border-none input-120px" value="${outraCarta.empCab1}" >
                            <input type="text" name="contatoResponsavel" class="form-control border-none input-220px" value="${CartaNovaTeste.contatoResponsavel}" >
                        </div>
                    </div>



                </div>	

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12 form-inline">
                            <input type="text" name="empCab2" class="form-control input-260px border-none" value="${outraCarta.empCab2}" >
                            <select class="form-control font-bold cartaConfere" name="empCab3">
                                <option value="${CartaNovaTeste.empCab3}">${CartaNovaTeste.empCab3}</option>
                                <option value="a COMPRA dos itens abaixo,">a COMPRA dos itens abaixo,</option>
                                <option value="a LOCAÇÃO dos itens abaixo,">a LOCAÇÃO dos itens abaixo,</option>
                                <option value="a CONTRATAÇÃO DE SERVIÇO,">a CONTRATAÇÃO DE SERVIÇO,</option>
                            </select>
                            <input type="text" name="empCab4" class="form-control input-260px border-none" value="${outraCarta.empCab4}" >
                        </div>
                    </div>
                </div>	

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12 form-inline">
                            <input type="text" name="empCab5" class="form-control input-440px border-none font-bold cartaConfere" value="${CartaNovaTeste.empCab5}" >
                            <input type="text" name="empCab6" class="form-control input-180px border-none" value="${outraCarta.empCab6}" ><br />
                            <input type="text" name="empCab7" class="form-control input-580px border-none font-bold cartaConfere" value="${CartaNovaTeste.empCab7}" style="margin-top: 7px;" >
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12 form-inline">
                            <input type="text" name="empCab8" class="form-control input-440px border-none" value="${outraCarta.empCab8}" >
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12 form-inline">
                            <input type="text" name="empCab9" class="form-control input-560px border-none" value="${outraCarta.empCab9}" >
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12 form-inline">
                            <textarea rows="4" cols="80" class="form-control cartaConfere" placeholder="Observações:" name="observacoes">${CartaNovaTeste.observacoes}</textarea>
                        </div>
                    </div>
                </div>

                <%-- <div class="row" style="margin-left: 25px">
                    <div class="col-md-12">
                        <input class="form-control border-none cartaConfere" name="descItem" value="${carta.descItem}" >
                    </div>
                </div> --%>

                <div class="row" style="margin-left: 2px">
                    <div class="col-md-12 form-inline">
                        <input class="form-control border-none input-160px" name="dataEntregaTexto" value="${outraCarta.dataEntregaTexto}" style="color: red">	
                        <input class="form-control border-none input-220px data cartaConfere" name="dataEntrega" value="${CartaNovaTeste.dataEntrega}" style="color: red">	
                    </div>			
                </div>					

                <div class="row" style="margin-left: 2px">
                    <div class="col-md-12 form-inline">					

                        <input class="form-control border-none cartaConfere input-160px" name="localEntrega" value="${outraCarta.localEntrega}" style="color: red">	

                        <c:if test="${CartaNovaTeste.localEntregaAgencia ==  true}">	
                            <input class="form-control border-none cartaConfere input-560px" name="localEntregaTexto" value="${outraCarta.loccoEndereco}" style="color: red">	
                        </c:if>
                        <c:if test="${CartaNovaTeste.localEntregaAgencia == false}">	
                            <input class="form-control border-none cartaConfere input-480px" name="localEntregaTexto" value="${CartaNovaTeste.localEntregaTexto}" style="color: red">	
                        </c:if>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12" style="margin-top: 15px;">
                        <div class="col-md-12">
                            <input type="text" name="descricao" class="form-control input-120px border-none font-bold" value="${outraCarta.descricao}" >
                        </div>
                    </div>
                </div>

                <!-- ----------------------------------------------------------------------------------- -->	
                <div class="row" style="margin:0px 35px 0px 40px;border: 0px solid gray;padding: 15px 0px;border-radius:5px">
	
                    <c:forEach  items="${fProducao}" var="fProducao">

                        <div style="font-size: 15px;text-decoration:underline;line-height: 10px;padding: 15px 0px 10px 0;">
                            <p>Item: ${fProducao.produtoGrupo.produto}</p>
                            <p>Quantidade: ${fProducao.produtoGrupo.quantidade *
                                fProducao.produtoGrupo.quantidade2 *
                                fProducao.produtoGrupo.diarias}</p>
                            <p style="line-height: 17px;">Descrição: ${fProducao.produtoGrupo.idGrupo.informacoes}</p>

		                    <p style="font-weight: bold;">Valor: <fmt:formatNumber value="${fProducao.valorItem}" pattern="#,##0.00"/> </p>

                        </div>
                    </c:forEach>

                </div>
                <!-- ----------------------------------------------------------------------------------- -->	
				
			    <c:if test="${empty somaImposto}"></c:if>

                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline font-bold" style="font-size: 16px;margin-left: 15px;color: red">					
                            <span>${outraCarta.valorTotalTexto}&nbsp&nbsp&nbsp 
                            <input name="valorTotal" type="hidden" value="${CartaNovaTeste.valorTotal}" />
                            ${CartaNovaTeste.valorTotal}</span>	<br />
                            <input class="form-control border-none input-480px" name="valorTotalTexto2" value="${outraCarta.valorTotalTexto2}" style="margin-top: 10px;margin-left: -12px;font-weight: normal;color: red" >	
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline" style="margin-left: 14px;color: red">					
                            <input class="form-control border-none input-220px" name="condicaofaturamento" value="${outraCarta.condicaofaturamento}" style="margin-top: 10px;margin-left: -12px;font-weight: bold;">	
						
							
								<h5 style="font-weight: bold;color: #333743">Parcelado Em: ${parceladoEm}x</h5>
	                            <c:forEach  items="${condicaoPagamentos}" var="f">
		                            <div style="padding: 0px 0px">
		                            		<h5>Parcela:  ${f.parcela}<br> para: <fmt:formatDate value="${f.dataPagar}" pattern="dd/MM/yyyy"/>
				                            ( ${f.dias} ),<br> 
				                            Valor: R$ ${f.valorPagar}</h5>
		                            </div>
	                            </c:forEach>
                            
                            
                            <%-- <input class="form-control border-none input-260px cartaConfere" name="condicaofaturamentoDias" value="${outraCarta.condicaofaturamentoDias}" >	
                            <input class="form-control border-none input-220px cartaConfere" name="dataFaturamento" value="${outraCarta.dataFaturamento}" > --%>	
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <strong><input class="form-control border-none input-660px" name="dadosEmissaoNota" value="${outraCarta.dadosEmissaoNota}" ></strong>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <strong><input class="form-control border-none input-220px" name="dadosEmissaoNotaResponsavel" value="${outraCarta.dadosEmissaoNotaResponsavel}" ></strong>	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <input class="form-control border-none input-480px" name="loccoRazaoSocial" value="${outraCarta.loccoRazaoSocial}" >	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <input class="form-control border-none input-560px" name="loccoEndereco" value="${outraCarta.loccoEndereco}" >	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <input class="form-control border-none input-480px" name="loccoCnpj" value="${outraCarta.loccoCnpj}" >	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <c:set var="string1" value="${outraCarta.informacoesImportantes}"/>
                            <c:set var="empresaJob" value="${fn:toUpperCase(InfoJobs.idJob.empresa.empresa)}"/>
                            <c:set var="informacoesImportantes" value="${fn:replace(string1,'/GTAV-MR', empresaJob)}" />


                            <textarea class="form-control font-bold" rows="12" cols="120" name="informacoesImportantes" style="font-size: 12px;text-decoration: underline;">${informacoesImportantes}</textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <input class="form-control border-none input-580px" name="esclarecimentos" value="${outraCarta.esclarecimentos}" >				
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <input class="form-control border-none input-180px" name="atenciosamente" value="${outraCarta.atenciosamente}" >					
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <input class="form-control border-none input-220px cartaConfere" name="responsavelContratacao" value="${CartaNovaTeste.responsavelContratacao}" >					
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">
                            <input class="form-control border-none input-120px" name="deAcordo" value="${outraCarta.deAcordo}" >					
                        </div>
                    </div>
                </div>

                <br />

                <div class="row">
                    <div class="col-md-6">
                        &nbsp&nbsp&nbsp&nbsp<button type="submit" class="btn btn-danger">Salvar Carta</button>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${CartaNovaTeste.gerarCarta == false}">
                            &nbsp&nbsp&nbsp&nbsp<a class="btn btn-primary disabled">Gerar Carta</a>
                        </c:if>

                        <c:if test="${CartaNovaTeste.gerarCarta == true}">
                            &nbsp&nbsp&nbsp&nbsp<a onclick="gerarWordCarta(${CartaNovaTeste.idCartaForn}, ${fornecedor.idEmpresa}, ${idLista})" class="btn btn-primary" id="gerarWord">Gerar Carta</a>
                        </c:if>
                    </div>
                </div>
                <br />
            </form>	

            <div class="row">

                <div class="col-md-12" style="margin: 0 0 15px 13px;font-size: 12px;">Última atualização:
                    <fmt:formatDate value="${CartaNovaTeste.atualizacao.time}" pattern="dd/MM/yyyy HH:mm:ss" /> 


                    - por ${CartaNovaTeste.atualizadoPor} </div>

            </div>
        </div>
    </c:if>
</div>
<div class="">
<div class="divisor" style="border-bottom: 1px solid #ddd"></div>
  <c:if test="${not empty outroFornx}">
  
  <a name="OutroFornecedor"></a>
	<div class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 218px;border-top: 1px solid #ccc">
		        <h2 style="text-transform: uppercase;font-weight: bolder;letter-spacing: -2px;">Carta Contratação Outro Fornecedor</h2>	
	</div>  
	
    <div class="col-md-10 painel shadow-com-padding" style="margin-left: 80px;margin-bottom: 70px;margin-top: -95px;padding-left: 40px;">
            <div class="divisor"></div>
            <div class="divisor"></div>
            <form action="atualizaCarta" method="post">
                <input type="hidden" value="${CartaOutroFornecedor.idCartaForn}" name="idCartaForn">
                <input type="hidden" value="${fornecedor.idEmpresa}" name="idFornecedor">
                <input type="hidden" value="${idLista}" name="idLista">
                <input type="hidden" value="${idProdutoGrupo}" name="idProdutoGrupo">
                <input type="hidden" value="${CartaOutroFornecedor.idproducao.idProducao}" name="idProducao">
                <input type="hidden" value="${outroFornx.idEmpresa.idEmpresa}" name="idFornecedorTrans">
                

                <div class="row">
                    <div class="col-md-12">
                        <img alt="" src="resources/images/locco.png" width="240" height="80" style="margin-bottom: 20px;margin-left: 20px;">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <input class="form-control border-none" name="dataCabecalho" value='São Paulo, ${dataHoje[0]} de ${dataHoje[1]} de ${dataHoje[2]}'>
                        </div>
                    </div>
                </div>	

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-5">
                            <input type="text" name="a" class="form-control border-none input-120px" value="${outraCarta.a}" >
                            <input type="text" name="empresa" class="form-control border-none input-420px font-bold cartaConfere" value="${CartaOutroFornecedor.empresa}" >
                            <input type="text" name="fornecedorContato" class="form-control border-none input-420px font-bold cartaConfere" value="${CartaOutroFornecedor.fornecedorContato}" >
                            <input type="text" name="fornecedorContatoEmail" class="form-control border-none input-420px font-bold cartaConfere" value="${CartaOutroFornecedor.fornecedorContatoEmail}" >
      						<input type="text" name="contatoResponsavel" class="form-control border-none input-220px cartaConfere" value="${CartaOutroFornecedor.contatoResponsavel}" >
                        </div>
                    </div>
                </div>	

                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-12 form-inline">
                            <textarea rows="4" cols="80" class="form-control cartaConfere" placeholder="Observações:" name="observacoes">${CartaOutroFornecedor.observacoes}</textarea>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline font-bold" style="font-size: 16px;margin-left: 15px;margin-top: 15px">					
                            <span>${outraCarta.valorTotalTexto}&nbsp&nbsp&nbsp 
                            <input name="valorTotal" type="hidden" value="${CartaOutroFornecedor.valorTotal}" />
                            ${CartaOutroFornecedor.valorTotal},</span>	<br />
                            
                            <c:forEach items="${outroFornx.idValorPgtoFornecedor}" var="valorOutro"><br />
                            	<p>Data Pagamento: <fmt:formatDate value="${valorOutro.dtPgotFornecedor.dataPagar}" pattern="dd/MM/yyyy"/></p>
                            </c:forEach>
                            
                            
                            <input class="form-control border-none input-480px" name="valorTotalTexto2" value="${outraCarta.valorTotalTexto2}" style="margin-top: 10px;margin-left: -12px;font-weight: normal;" >	
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <strong><input class="form-control border-none input-660px" name="dadosEmissaoNota" value="${outraCarta.dadosEmissaoNota}" ></strong>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <strong><input class="form-control border-none input-220px" name="dadosEmissaoNotaResponsavel" value="${outraCarta.dadosEmissaoNotaResponsavel}" ></strong>	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <input class="form-control border-none input-480px" name="loccoRazaoSocial" value="${outraCarta.loccoRazaoSocial}" >	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <input class="form-control border-none input-560px" name="loccoEndereco" value="${outraCarta.loccoEndereco}" >	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">					
                            <input class="form-control border-none input-480px" name="loccoCnpj" value="${outraCarta.loccoCnpj}" >	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <c:set var="string1" value="${outraCarta.informacoesImportantes}"/>
                            <c:set var="empresaJob" value="${fn:toUpperCase(InfoJobs.idJob.empresa.empresa)}"/>
                            <c:set var="informacoesImportantes" value="${fn:replace(string1,'/GTAV-MR', empresaJob)}" />


                            <textarea class="form-control font-bold" rows="12" cols="120" name="informacoesImportantes" style="font-size: 12px;text-decoration: underline;">${informacoesImportantes}</textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <input class="form-control border-none input-580px" name="esclarecimentos" value="${outraCarta.esclarecimentos}" >				
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <input class="form-control border-none input-180px" name="atenciosamente" value="${outraCarta.atenciosamente}" >					
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">	
                            <input class="form-control border-none input-220px cartaConfere" name="responsavelContratacao" value="${CartaOutroFornecedor.responsavelContratacao}" >					
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">					
                        <div class="col-md-12 form-inline">
                            <input class="form-control border-none input-120px" name="deAcordo" value="${outraCarta.deAcordo}" >					
                        </div>
                    </div>
                </div>

                <br />

                <div class="row">
                    <div class="col-md-6">
                        &nbsp&nbsp&nbsp&nbsp<button type="submit" class="btn btn-danger">Salvar Carta</button>
                    </div>
                    <div class="col-md-6">
                        <c:if test="${CartaOutroFornecedor.gerarCarta == false}">
                            &nbsp&nbsp&nbsp&nbsp<a class="btn btn-primary disabled">Gerar Carta</a>
                        </c:if>

                        <c:if test="${CartaOutroFornecedor.gerarCarta == true}">
                            &nbsp&nbsp&nbsp&nbsp<a onclick="gerarOutroWordCarta(${CartaOutroFornecedor.idCartaForn}, ${CartaOutroFornecedor.fornecedor.idEmpresa}, ${idLista},${fornecedor.idEmpresa},${idProdutoGrupo})" class="btn btn-primary" id="gerarWord">Gerar Carta</a>
                        </c:if>
                    </div>
                </div>
                <br />
            </form>	

            <div class="row">

                <div class="col-md-12" style="margin: 0 0 15px 13px;font-size: 12px;">Última atualização:
                    <fmt:formatDate value="${CartaOutroFornecedor.atualizacao.time}" pattern="dd/MM/yyyy HH:mm:ss" /> 


                    - por ${CartaOutroFornecedor.atualizadoPor} </div>

            </div>
        </div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	      
  </c:if>
</div>        





<div class="col-md-12 alpha60 div-confirmacao" id="geraCarta" style="position: fixed"></div>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<!-- - - - - - - - - - Fim Container - - - - - - - - - -->
<c:import url="../_comum/footer.jsp" />




