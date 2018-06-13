<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<script type="text/javascript" src="resources/js/jquery-1.11.2.min.js"></script>	
<script type="text/javascript">
function emailSucesso(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-sucesso-email").slideToggle("slow"); 
	    }, 2500);
	});
}

function emailErro(){
	$(document).ready(function() {
		window.setTimeout(function()
	    {
		   $("#msg-erro-email").slideToggle("slow"); 
	    }, 10000);
	});
}
</script>



<c:forEach items="${pagamento}" var="pagamento">
	<c:set var="idpagamento" value="${pagamento.idpagamento}" />
	<c:set var="idEmpresa" value="${pagamento.idEmpresa.idEmpresa}" />
	<c:set var="idtipoPagamento" value="${pagamento.idtipoPagamento}" />
	<c:set var="banco" value="${pagamento.banco}" />
	<c:set var="agencia" value="${pagamento.agencia}" />
	<c:set var="conta" value="${pagamento.conta}" />
</c:forEach>

<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li><a href="listaempresa?first=0&max=51">Lista de Empresas</a></li>
    		<li class="active">Edição de Empresa</li>
		</ol>
	</div>
</div>
<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 27px;background: #fff">
	<h4 style="font-family: 'OpenSansLight'">EDIÇÃO DE EMPRESA</h4>
</div>

<c:if test="${msg.get(0) == 'sucesso'}">
	<div class="msg-email msg-sucesso-email" id="msg-sucesso-email" >${msg.get(1)}</div>
	<script type="text/javascript">emailSucesso();</script>
</c:if>	

<c:if test="${msg.get(0) == 'erro'}">
	<div class="msg-email msg-erro-email" id="msg-erro-email">${msg.get(1)}</div>
	<script type="text/javascript">emailErro();</script>
</c:if>





<div class="container" style="padding-bottom: 250px;">

<div class="row">

    <!-- - - - - - - - - - -  Cadastra Empresa - - - - - - - - - - -->
    <div class="col-md-6 painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;border-right: 1px solid #ddd;">
        <div class="col-md-11">
        	
	           <form action="AtualizaEmpresa" method="post"  enctype="multipart/form-data" id="formEmpresaFornecedor">
				
				<input type="hidden" value="${infoempresa.idEmpresa}" name="idEmpresaAtualiza">
		
                <div class="form-inline" style="border-bottom: 1px solid #ddd;padding-bottom: 10px">
                
                    <label for="">Categoria:</label>
                    
                    <c:if test="${infoempresa.cliente}">
                    	<input type="checkbox" class="checkbox ajuste" name="cliente" checked="checked">&nbspCliente
                    </c:if>
                    <c:if test="${!infoempresa.cliente}">
                    	<input type="checkbox" class="checkbox ajuste" name="cliente" >&nbspCliente
                    </c:if>
                    
                    <c:if test="${infoempresa.fornecedor}">
                    <input type="checkbox" class="checkbox ajuste" name="fornecedor" checked="checked" id="tipoFornecedor">&nbsp&nbspFornecedor
                    </c:if>
                    
                    <c:if test="${!infoempresa.fornecedor}">
                   		 <input type="checkbox" class="checkbox ajuste" name="fornecedor" id="tipoFornecedor">&nbsp&nbspFornecedor
                    </c:if>
                    
                    
                	<c:if test="${infoempresa.prospect}">
                    <input type="checkbox" class="checkbox ajuste" name="prospect" checked="checked" >&nbsp&nbspProspect
                    &nbsp |&nbsp
                    </c:if>
                    
                    <c:if test="${!infoempresa.prospect}">
                    <input type="checkbox" class="checkbox ajuste" name="prospect">&nbsp&nbspProspect
                    &nbsp |&nbsp
                    </c:if>
                                        
                    
                    <c:if test="${infoempresa.habilitado}">
                    <input type="checkbox" name="habilitado" checked="checked" class="checkbox ajuste">&nbsp&nbspAtivo
                	</c:if>
                
                    <c:if test="${!infoempresa.habilitado}">
                    <input type="checkbox" name="habilitado" class="checkbox ajuste">&nbsp&nbspAtivo
                	</c:if>
                
                </div>
                
<!-- / -->
                <div class="form-inline">
					<div class="divisor"></div>
					<input type="hidden" value="${infoempresa.idEmpresa}">
	                <input type="text" class="form-control font-bold input-460px" name="empresa" value="${infoempresa.empresa}" placeholder="Nome da Empresa" id="nomeEmpresa">&nbsp*           
                </div>
<!-- / -->
			     <div class="divisor-fino"></div>
                 <div class="form-inline">
	                 <input type="text" class="form-control input-460px" name="RazaoSocial" value="${infoempresa.razaoSocial}" placeholder="Razão Social" id="razaoSocial">&nbsp*
                 </div>
<!-- / -->		<div class="divisor-fino"></div>
                <div class="form-inline col-md-5 tira-padding">
                  <input type="text" class="form-control" name="cnpj" value="${infoempresa.cnpj}" placeholder="CNPJ" id="cnpj">&nbsp*
                  <div class="divisor-fino"></div>
                </div>
<!-- / -->
                <div class="form-group">
                  <input type="text" class="form-control input-460px" name="endereco" value="${infoempresa.endereco}" placeholder="Endereço">
                </div>
<!-- / -->
                <div class="form-inline">
                  <input type="text" class="form-control" name="bairro" value="${infoempresa.bairro}" placeholder="Bairro">&nbsp&nbsp&nbsp&nbsp&nbsp
                  <input type="text" class="form-control" name="cep" placeholder="CEP" value="${infoempresa.cep}" >
                </div>  
                
                <div class="divisor-fino"></div>

<!-- / -->
                <div class="form-inline">
                
                  <input type="text" class="form-control" name="cidade" value="${infoempresa.cidade}" placeholder="Cidade">&nbsp&nbsp&nbsp&nbsp&nbsp
                  
                  <select name="uf" id="" class="form-control" >
						<option value="${infoempresa.uf}">${infoempresa.cidade}</option>
						
						<c:if test="${infoempresa.cidade}">
							<option value="SP">São Paulo</option>
						</c:if>
						
						<option value="AC">Acre</option>
						<option value="AL">Alagoas</option> 
						<option value="AM">Amazonas</option> 
						<option value="AP">Amapá</option> 
						<option value="BA">Bahia</option> 
						<option value="CE">Ceará</option> 
						<option value="DF">Distrito Federal</option> 
						<option value="ES">Espírito Santo</option> 
						<option value="GO">Goiás</option> 
						<option value="MA">Maranhão</option> 
						<option value="MT">Mato Grosso</option> 
						<option value="MS">Mato G. do Sul</option> 
						<option value="MG">Minas Gerais</option> 
						<option value="PA">Pará</option> 
						<option value="PB">Paraíba</option> 
						<option value="PR">Paraná</option> 
						<option value="PE">Pernambuco</option> 
						<option value="PI">Piauí</option> 
						<option value="RJ">Rio de Janeiro</option> 
						<option value="RN">Rio G. do Norte</option> 
						<option value="RO">Rondônia</option> 
						<option value="RS">Rio G. do Sul</option> 
						<option value="RR">Roraima</option> 
						<option value="SC">Santa Catarina</option>
						<option value="SP">São Paulo</option> 
						<option value="SE">Sergipe</option> 
						<option value="TO">Tocantins</option>
				     </select>                
                </div>
                <div class="divisor-fino"></div>                              

<!-- / -->
                <div class="form-inline">
				  <input type="text" class="form-control" name="telefone" value="${infoempresa.telefone}" placeholder="Telefone">&nbsp&nbsp&nbsp&nbsp&nbsp
                  <input type="text" class="form-control pop" id="telefone" name="telefoneDesc" value="${infoempresa.telefoneDesc}" placeholder="Descrição do Telefone" data-container="body" data-placement="right" data-original-title="  Descrição do Telefone." data-content="Ex.: Telefone da Recepção" >
                </div>  
                <div class="divisor-fino"></div> 
 <!-- / -->
 
               <div class="form-group">
                  <input type="text" class="form-control input-460px" name="email" value="${infoempresa.email}" placeholder="Email@email">
               </div>
<!-- / -->            
               <div class="form-group">
                   <input type="text" class="form-control pop input-460px" id="email" name="emailDesc" value="${infoempresa.emailDesc}" placeholder="Descrição do email" data-container="body" data-placement="right" data-content="Ex.: Email de contato da Recepção." data-original-title="  Descrição do email.">
               </div>
<!-- / -->                
                <div class="form-group col-md-6 tira-padding">
                  <input type="text" class="form-control pop" name="origem" value="${infoempresa.origem}" placeholder="Conheceu por" data-container="body" data-placement="right" data-original-title="Conheceu por:" data-content="Ex.: 'Histórico' ou 'Indicação da Empresa X' ... etc">
                </div>
<!-- / -->                
		<div class="divisor"></div>
		<div class="divisor"></div>
		<div class="divisor"></div>

                
                
                
                
                 
     				<div class="12 tira-padding formFornecedor  <c:if test="${!infoempresa.fornecedor}">display-none</c:if>
                    <c:if test="${infoempresa.fornecedor}"></c:if> " id="fornecedorForm" style="padding: 10px;background: #ecf0f1;">            
                 
                 
<!-- / -->		
				<div class="col-md-6 tira-padding" >
				<input type="hidden" value="${idpagamento}" name="idpagamento">
					<label>Pagamento Via *</label>
  
                	 <select id="fornecedorTipoPagamento" class="form-control" name="idTipoPagamentoTransiente">
                	 
						<option value="${idtipoPagamento.idTipoPagamento}">${idtipoPagamento.tipoPagamento}</option>      	 	
                	 	
                	 	<c:forEach items="${tipoPagamento}" var="tipoPagamento">
                	 	
	                	 	<c:choose>
	                	 	
	                	 		<c:when test="${(tipoPagamento.tipoPagamento) == (idtipoPagamento.tipoPagamento)}">
	                	 			
	                	 		</c:when>
	                	 		
	                	 		<c:when test="${(tipoPagamento.tipoPagamento) != (idtipoPagamento.tipoPagamento)}">
	                	 			<option value="${tipoPagamento.idTipoPagamento}">${tipoPagamento.tipoPagamento}</option> 	
	                	 		</c:when>	
	                	 			
	                	 	</c:choose>
                	 	
                	 	</c:forEach>
                	 	                	 	
                	 </select>
                	
					<div class="col-md-12"></div>
                </div>
<!-- / -->
				<div class="form-group col-md-4 tira-padding" style="margin-left: 10px">
					<label>Banco</label>	
                	<input type="text" id="" class="form-control" placeholder="Nome do banco" value="${banco}" name="banco">
                <div class="divisor"></div>
                </div>	
				<div class="col-md-12"></div> 
<!-- / -->

				<div class="form-group col-md-4 tira-padding">
					<label>Agência</label>	
                	<input type="text" id="" class="form-control" placeholder="Agência" value="${agencia}" name="agencia">
                </div>	
<!-- / -->

				<div class="form-group col-md-4 tira-padding" style="margin-left: 10px">
					<label>CC</label>	
                	<input type="text" id="" class="form-control" placeholder="CC" value="${conta}" name="conta">
                </div>	
				<div class="divisor"></div> 
<!-- / -->			


             </div> <!-- Esse div é o problema ----------------------------------------/ -->	



				<div class="form-group">
					<div class="divisor"></div> 
					<textarea class="form-control" cols="20" rows="5" name="obs"
					placeholder="Observações">${infoempresa.obs}</textarea>
				</div>

				
				
				
				<div class="form-group col-md-8 tira-padding" style="margin-top: 25px;">
                  <label for="">Logotipo</label>
                 <input type="file" data-buttonBefore="true" name="NomeAnexo" id="fileAnexo">
                </div>	
                
                
                <div style="border:0px solid gray; width: 150px;height: 150px;float:right;">
                <c:if test="${not empty infoempresa.logotipo}">
                	<img src="upload/upload/logoEmpresas/${infoempresa.logotipo}" alt="" width="150" height="150"
                    style="max-width: 160px;max-height: 80px;width: auto;height: auto;margin-top: 25%;">
                	
                </c:if>
				
				<c:if test="${empty infoempresa.logotipo}">
                	<img src="upload/upload/logoEmpresas/semfoto.png" alt=""
                	style="max-width: 160px;max-height: 80px;width: auto;height: auto;margin-top: 25px;">
                </c:if>
                
                
                </div>
  
                <div class="form-group col-md-12 tira-padding">
                <hr class="message-inner-separator">  
                  <button type="submit" class="btn btn-danger">Salvar</button>
                </div>
          
    </form>  
        </div>

</div>

<!-- --------------- Novo contato --------------- -->	

 <c:import url="sideBarContato.jsp" /> 




</div>


<!-- Modal -->
<div class="modal fade" id="Tags" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Criar Nova Tag</h4>
      </div>
      <div class="modal-body">
          

            <label for="">Tag</label>
          	
      <form action="salvaNovaTag" method="post" id="salvaNovaTag">	
          	<input type="hidden" value="${infoempresa.idEmpresa}" name="idEmpresaTag" >
          
            <input type="text" class="form-control" placeholder="Nome da nova Tag" style="width:180px" name="atuacao" id="novaTag">
            <div class="form-inline">
            </div>
         
              
  
    
      		</div>
      		<div class="modal-footer">
        	<button type="submit" class="btn btn-primary">Salvar Tag</button>
        </form>
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.reload();">Fechar</button>
        
      </div>
    </div>
  </div>
</div>


<c:import url="../_comum/footer.jsp" />