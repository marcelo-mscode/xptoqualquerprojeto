<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- - - - - - - - - - Container - - - - - - - - - - - -->

<div class="container"> 


  <div class="row">

    <!-- - - - - - - - - - -  Cadastra Empresa - - - - - - - - - - -->
    <div class="col-md-7 painel" style="padding: 20px 0 25px 9px;margin-left: 20%;border-right: 1px solid #ddd;">

			<div style="padding: 15px;border-bottom: 1px solid #e5e5e5;margin-bottom: 15px;margin-top: -20px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
					<span aria-hidden="true" style="font-size: 50px; font-family: 'OpenSansLight'; font-weight: normal;" onclick="tiraLightbox();">×</span>
				</button>
				<h4 class="modal-title fontLight" id="myModalLabel">CADASTRO DE NOVO CLIENTE</h4>
			</div>

        <div class="col-md-12">
        	
<!--             <form action="salvaEmpresaLista" method="post" id="salvaEmpresa" enctype="multipart/form-data"> -->
            <form action="salvaEmpresaNovaProspeccao" method="post" id="ssalvaEmpresaLista">
            
            <%-- <input type="hidden" value="${infoempresa.idEmpresa}" name="idEmpresaAtualiza"> --%>
                <div class="form-inline" style="border-bottom: 0px solid #ddd;margin-bottom: 28px;padding-bottom: 10px;">
                    <label for="">Categoria:</label>
                    <input type="checkbox" class="checkbox ajuste" name="cliente" checked="checked">&nbspCliente
                    <input type="checkbox" class="checkbox ajuste" name="fornecedor" id="fornecedorEmpresa">&nbsp&nbspFornecedor
                    <input type="checkbox" class="checkbox ajuste" name="prospect">&nbsp&nbspProspect
                    &nbsp |&nbsp <input type="checkbox" name="habilitado" checked="checked" class="checkbox ajuste">&nbsp&nbspAtivo
                </div>
                <div class="form-inline">
                 <input type="text" class="form-control font-bold input-587px" name="empresa" placeholder="Nome da Empresa" id="nomeDaEmpresa" onblur="verificaFornecedor();">&nbsp*
                 <span style="display: none;color: red"  id="semBrancoFornecedor">*Não deixa esse campo em branco<i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                 <span style="display: none;color: red"  id="sjaExisteFornecedor">*Já existe um Fornecedor ou Cliente com esse nome cadastrado <i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                 <span style="display: none;color:green" id="snaoExisteFornecedor">*Fornecedor disponível para cadastro <i class="glyphicon glyphicon-ok" style="top: 0px;left: 5px;color: green;"></i></span>
                
                
                
                </div>
				<div class="divisor-fino"></div>
                 <div class="form-inline">
                 <input type="text" class="form-control input-587px" name="RazaoSocial" placeholder="Razão Social" id="razaoSocial" >&nbsp*

                 </div>
				<div class="divisor-fino"></div>
                <div class="form-inline col-md-5 tira-padding">
                  <input type="text" class="form-control" name="cnpj" placeholder="CNPJ" id="cnpj">&nbsp*
                <div class="divisor-fino"></div>
                </div>
					<div class="divisor-fino"></div>
                <div class="form-group">
                  <input type="text" class="form-control input-587px" name="endereco" placeholder="Endereço">
                </div>
                
                <div class="form-inline">
                  <input type="text" class="form-control" name="bairro" placeholder="Bairro">&nbsp
                  <input type="text" class="form-control input-120px" name="cep" placeholder="CEP">&nbsp
                  <input type="text" class="form-control input-120px" name="cidade" placeholder="Cidade">&nbsp
                  
                <!-- </div>  
                
                <div class="divisor-fino"></div>

                <div class="form-inline"> -->
                
                  
                  <select name="uf" id="" class="form-control" >
				        <!-- <option value="estado">Selecione o Estado</option> --> 
						<option value="SP">São Paulo</option> 
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
						<option value="SE">Sergipe</option> 
						<option value="TO">Tocantins</option>
				     </select>                
                </div>
                    <div class="divisor-fino"></div>                              
                <div class="form-inline">
                
				  <input type="text" class="form-control" name="telefone" placeholder="Telefone" id="telefoneEmpresa">&nbsp
                  <input type="text" class="form-control pop" id="telefone" name="telefoneDesc" placeholder="Descrição do Telefone"
                  data-container="body" data-placement="right"
                  data-original-title="  Descrição do Telefone." data-content="Ex.: Telefone da Recepção" >


                </div>  
                    <div class="divisor-fino"></div> 
               <div class="form-inline">
                  <input type="text" class="form-control input-220px" name="email" placeholder="Email@email">&nbsp
                   <input type="text" class="form-control pop input-160px" id="email" name="emailDesc" placeholder="Descrição do email"
                   data-container="body" data-placement="right"
                   data-content="Ex.: Email de contato da Recepção." data-original-title="  Descrição do email.">
                   <input type="text" class="form-control pop" name="origem" placeholder="Conheceu por" style="width: 200px;">
               </div>
		<div class="divisor"></div>
	
				
				<%--  <c:if test="${!infoempresa.fornecedor}">
     				<div class="12 tira-padding display-none formFornecedor" id="fornecedorForm" style="padding:10px;background: #ecf0f1;">            
                 </c:if> --%>
                 
<%--                  <c:if test="${infoempresa.fornecedor}"> --%>
					<div class="12 tira-padding formFornecedor input-587px" id="fornecedorForm" style="padding:10px;background: #ecf0f1;">
    	          <%-- </c:if> --%>
		
		
		
		


				<div class="col-md-4 tira-padding" >
				<input type="hidden" value="${idpagamento}" name="idpagamento">
					<label>Pagamento Via  *</label>
  
                	 <select id="" class="form-control" name="idTipoPagamentoTransiente">
                	 
					<%-- 	<option value="${idtipoPagamento.idTipoPagamento}">${idtipoPagamento.tipoPagamento}</option> --%>      	 	
                	 	
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
                	
                </div>
				
				<div class="form-group col-md-5 tira-padding" style="margin-left: 10px">
					<label>Banco</label>	
                	<input type="text" id="" class="form-control" placeholder="Nome do banco" value="${banco}" name="banco">
				<div class="divisor"></div>
                </div>	
				<div class="form-group col-md-4 tira-padding">
					<label>Agência</label>	
                	<input type="text" id="" class="form-control" placeholder="Agência" value="${agencia}" name="agencia">
                </div>	
				
				<div class="form-group col-md-4 tira-padding" style="margin-left: 10px">
					<label>CC</label>	
                	<input type="text" id="" class="form-control" placeholder="CC" value="${conta}" name="conta">
                </div>	


 
             </div>
             <div class="divisor-fino"></div>
             
				
                <div class="form-group input-587px">
                  
                  <textarea class="form-control" cols="20" rows="5" name="obs" placeholder="Observações"></textarea> 
                </div>
  
                <div class="form-group col-md-12 tira-padding">
                  <hr class="message-inner-separator">  
                  <button type="submit" class="btn btn-danger is-disabled" id="ssalvaFornecedorLista">Salvar</button>
                  <a class="btn btn-primary display-none" id="sfechaFornecedorLista" onclick="tiraLightbox();">Fechar</a>

                  <br><br>
                  
                  <span style="display: none;color: red" id="ssalvou">Novo Cliente salvo com sucesso !</span>
                  
                  
                </div>
          
        </form>  
        </div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  </div>

 <div class="divisor-fino"></div>

</div>

</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<%-- <c:import url="../../_comum/footer.jsp" /> --%>

