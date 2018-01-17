<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
    .producao{background: #f1f1f1;}
    
    .numberBox{font-size: 75px;color: #c7ccd6;}
    
</style>
<c:import url="../../_comum/header.jsp" />


<!--  ------------------------------------------------------------------------------------------------------ -->        
<c:set value="${produtoGrupo.quantidade * produtoGrupo.quantidade2 * produtoGrupo.diarias}" var="quantidadeItem" />
<c:set value="${idLista}" var="idLista" />
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

        <div class="col-md-8" style="margin-top: 10px;font-family:'OpenSansLight' ">
            <h4>Lista: ${lista.lista}</h4>
            <h4>Job: ${InfoJobs.idJob.titulo}</h4>
            <h4>Empresa: ${InfoJobs.idJob.empresa.empresa}</h4>
            <h4>Data do Evento:

                <c:if
                    test="${empty InfoJobs.localEventoDataTermino}">
                    <strong><span style="color: red">Local e Data do evento não cadastrados</span></strong>
                </c:if>
                <c:if test="${not empty InfoJobs.localEventoDataTermino}">
                    <fmt:formatDate value="${InfoJobs.localEventoDataTermino}"
                                    pattern="dd/MM/YYYY HH:mm" />
                </c:if>

            </h4>

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
                    &nbsp&nbspR$<fmt:formatNumber value="${fatLocco}" pattern="#,##0.00" />
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
    <div class="col-md-12" style="padding: 0 25px 25px 0">

        <%-- <div class="row" style="border-bottom: 1px solid #ccc">
            <div class="col-md-12" style="margin-left: 43px;">
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
        </div> --%>

        <form action="salvaItem" method="post" id="salvaItemProducao">

            <input type="hidden" value="${idProducao}" name="idProducao" />
            <input type="hidden" value="${idLista}" name="idLista" id="idLista" />
            <input type="hidden" value="${idProdutoGrupo}" name="idProdutoGrupo" id="produtoGrupo" />
            <input type="hidden" value="${idCartaAtualiza}" name="idCarta" />
            <input type="hidden" value="${produtoGrupo.empresa.idEmpresa}" name="idEmpresaTransiente" />



    <div class="row" id="fornecedorAtual" style="padding-bottom: 10px;">
   	   <table>
		 <tr>
			<td>
		         <div class="col-md-1" style="padding: 0;">
					<span class="numberBox">1</span>
				 </div>
		    </td>               
			<td>               
                <div class="col-md-7">
                    <h4 style="font-family: 'OpenSansLight'">
                       <strong> Fornecedor: ${fornecedor.empresa}</strong>
                    </h4>
                </div>
                <div class="col-md-5">
                    <h4 style="font-family: 'OpenSansLight'">
                        CNPJ: <strong>${fornecedor.cnpj}</strong>
                    </h4>
                </div>

                <div class="col-md-4">
                    <h4 style="font-family: 'OpenSansLight'">
                        Contato no Fornecedor:
                        <select class="form-control input-180px" name="contatoFornecedor" style="margin-top: 5px">
                            <c:forEach items="${contatoFornecedor}" var="contato">
                                <option value="${contato[0]}">${contato[1]}</option>
                            </c:forEach>
                        </select>
                    </h4>
                </div>
		 		</td>
		 	</tr>
		 </table>	                
     </div>

     <div class="row col-md-12" style="border-bottom: 1px solid #ccc;">
     	<table>
		 <tr>
			<td>
		         <div class="col-md-1" style="padding: 0;">
					<span class="numberBox">2</span>
				 </div>
		    </td>               
			<td style="width: 850px"> 
                <h4 style="font-family: 'OpenSansLight';margin-left: 20px">
               		 <strong> Informações de entrega</strong>
                </h4>
                
                <div class="col-md-12 tira-padding" style="padding: 10px 0px">
                    <div class="col-md-3">Data Entrega *</div>
                    <div class="col-md-7">
                        <input type="text" class="form-control data" style="width: 110px" name="pEntrega" />
                    </div>
                </div>
		      
       
	            <div class="row" style="padding-bottom: 20px">
	                <div class="col-md-12 tira-padding" style="margin-left: 7px;">
	                    <div class="col-md-3">Local de Entrega *</div>
	                    <div class="col-md-3" style="margin-left: -5px;">
	                        <select class="form-control input-180px" name="localEntrega" onchange="localentrega();" id="localEntrega">
	                            <option value="agencia">Agência Locco</option>
	                            <option value="localevento">Local do Evento</option>
	                        </select>
	                    </div>
	                    <div class="col-md-6" style="padding-top: 7px; height: 34px;">
	
	                        <p id="localEvento">&bull; Rua Barão de Jaceguai, 418-São
	                            Paulo/SP - CEP 04606-000
	                        </p>
	
	                    </div>
	                </div>
	            </div>
            </td>
		  </tr>
	    </table>	
	 </div>	

            <!-- ---------------------------------------------------------------------------------------------------- -->

     <div class="row col-md-12" style="border-bottom: 1px solid #ccc;">
     
	   <table>
		 <tr>
			<td>
		         <div class="col-md-1" style="padding: 0;">
					<span class="numberBox">3</span>
				 </div>
		    </td>               
			<td style="width: 850px"> 
                <h4 style="font-family: 'OpenSansLight';margin-left: 20px">
               		 <strong>Informações de Pagamento</strong>
                </h4>
			
                <div class="col-md-12 tira-padding" style="padding: 15px 0">
                    <div class="col-md-3">
                        <input type="radio" name="infoPag" checked="checked" value="0" onclick="efeitoHide(divValorContratacao);">&nbsp&nbspSem CL
                    </div>
                    <div class="col-md-3">
                        <input type="radio" name="infoPag" value="1" onclick="efeitoShow(divValorContratacao);">&nbsp&nbspCom CL
                    </div>

                </div>
                
            <!-- ---------------------------------------------------------------------------------------------------- -->

		     <div class="row" style="border: 1px solid #c7ccd6;padding: 15px;margin: 0px 0px 20px 10px;display:none" id="divValorContratacao">
		
		         <c:if test="${produtoGrupo.imposto == true}">
		             <c:set value="${fatLocco}" var="valorItem" />
		         </c:if>
		         <c:if test="${produtoGrupo.imposto == false}">
		             <c:set value="${fatDireto}" var="valorItem" />
		         </c:if>
		
		         <c:import url="salvaFornFinanc/salvaValorContratacao.jsp">
		             <c:param name="valorItem" value="${valorItem}" />
		             <c:param name="valorContratacao" value="${valorContratacao}" />
		         </c:import>
		
		     </div>

            <!-- ---------------------------------------------------------------------------------------------------- -->

            <div class="col-md-12 tira-padding">
                <div class="col-md-3">Tipo pagamento *</div>
                <div class="col-md-5">
                    <select class="form-control input-180px" name="tipoPagamento">
                        <c:forEach items="${tipoPagamento}" var="tipoPagamento">
                            <c:if test="${tipodePagamento != tipoPagamento.tipoPagamento}">
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
                <div class="col-md-7" style="background: #fff;padding: 0 20px;margin: 10px 0 20px 0;padding: 0px 10px;margin-left: 20px;box-shadow: 5px 5px 16px 2px #c7ccd6;" id="tableParcelas">
                    <table class="table table-bordered table-hover" style="margin-top: 20px;">

                        <tr>
                            <th>Parcela</th>
                            <th class="input-160px">Prazo Pagamento *</th>
                            <th>Data</th>
                            <th>Valor R$</th>
                        </tr>

                    </table>
                </div>
            </div>
			</td>
		</tr>
	</table>		
 </div>

            <!-- ----------------------------------------------------------------------------------------------------------------  -->

  <div class="row"  style="border-top: 1px solid #ccc;padding-top: 20px">
       <table>
		 <tr>
			<td>
		         <div class="col-md-1" style="padding: 0;">
					<span class="numberBox">4</span>
				 </div>
		    </td>               
			<td>               
                <div class="col-md-8">
                    <h4 style="font-family: 'OpenSansLight'">
                        <strong> Responsável Contratação *&nbsp&nbsp&nbsp  </strong>
                    </h4>
                </div>
    		
                <div class="col-md-12 tira-padding">
                    <div class="col-md-6">
                        <select class="form-control" style="width: 230px" name="idUsuario">
                                <option value="${userLogado.idUsuario}">${userLogado.nome}</option>
                            <c:forEach items="${usuarios}" var="usuarios">
                                <option value="${usuarios[0]}">${usuarios[1]}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <p>&bull;&nbsp<!-- ${responsavel} --></p>
                    </div>
                </div>
           <div class="col-md-12 tira-padding">
                    <div class="col-md-3" style="margin: 10px;">
                        Observações:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    </div>
                    <br>
                    <br>
                    <div class="col-md-5" style="margin: 0px 0px 10px 6px;">
                        <textarea class="form-control" rows="5" cols="35" name="obs"></textarea>
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
                            
                             <input type="submit" class="btn btn-danger consolidaBotao" value="Consolidar Item" id="" style="margin-left: 11px;padding: 16px;box-shadow: 5px 5px 16px 2px #c7ccd6;">
 		                     <span id="itemImage" style="margin-left: 15px"></span>
 		                     <span id="itemMsg" style="margin-left: 15px"></span>
                            
                        </c:if>

                        <c:if test="${produtoGrupo.status == 1}">
                            <input class="btn btn-primary  is-disabled" value="Item Fechado">
                        </c:if>

                    </div>
                </div>
            </div>
    		</td>
	  	</tr>
	 	</table>	 
	   </div>
      </form>
   </div>
</div>
<div class="divisor" style="border-bottom: 1px solid #ddd"></div>


<!-- ----------------------------------------------  CARTA CONTRATAÇÃO -------------------------------------- -->

<div class="" >
    <div class="col-md-12 efeitoDegrade" style="font-family: 'OpenSansLight';height: 218px;">
        <h2 style="text-transform: uppercase;font-weight: bolder;letter-spacing: -2px;">Carta de Contratação</h2>
        <c:if test="${empty CartaNovaTeste}">
            <div class="col-md-12" style="background: #fff">
                Item não preenchido
            </div>		
        </c:if>
    </div>
</div>


<div class="col-md-12 alpha60 div-confirmacao" id="geraCarta"></div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<!-- - - - - - - - - - Fim Container - - - - - - - - - -->
<c:import url="../../_comum/footer.jsp" />

