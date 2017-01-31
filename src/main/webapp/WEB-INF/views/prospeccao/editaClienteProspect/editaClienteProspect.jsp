<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- - - - - - - - - - Container - - - - - - - - - - - -->

<c:forEach items="${pagamento}" var="pagamento">
	<c:set var="idpagamento" value="${pagamento.idpagamento}" />
	<c:set var="idEmpresa" value="${pagamento.idEmpresa.idEmpresa}" />
	<c:set var="idtipoPagamento" value="${pagamento.idtipoPagamento}" />
	<c:set var="banco" value="${pagamento.banco}" />
	<c:set var="agencia" value="${pagamento.agencia}" />
	<c:set var="conta" value="${pagamento.conta}" />
</c:forEach>


<div class="container"> 


  <div class="row">

    <!-- - - - - - - - - - -  Cadastra Empresa - - - - - - - - - - -->
    <div class="col-md-7 painel" style="padding: 20px 0 25px 9px;margin-left: 20%;border-right: 1px solid #ddd;">

			<div style="padding: 15px;border-bottom: 1px solid #e5e5e5;margin-bottom: 15px;margin-top: -20px;">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
					<span aria-hidden="true" style="font-size: 50px; font-family: 'OpenSansLight'; font-weight: normal;" onclick="tiraLightbox();">×</span>
				</button>
				<h4 class="modal-title fontLight" id="myModalLabel">EDITA CLIENTE</h4>
			</div>

        <div class="col-md-12">
        	
         <form action="atualizaClienteProspeccao" method="post" id="atualizaEmpProspec">
            
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
              	      <input type="checkbox" class="checkbox ajuste" name="fornecedor" checked="checked" id="stipoFornecedor">&nbsp&nbspFornecedor
                    </c:if>
                    
                    <c:if test="${!infoempresa.fornecedor}">
                   		<input type="checkbox" class="checkbox ajuste" name="fornecedor" id="stipoFornecedor">&nbsp&nbspFornecedor
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
 
 
 
                <div class="form-inline">
<%--             <input name="empresa" id="nomeEmpresa" value="${infoempresa.empresa}"  type="text" class="form-control font-bold input-587px"  placeholder="Nome da Empresa"  onblur="verificaFornecedor();">&nbsp* --%>
                 <input name="empresa" id="snomeEmpresa" value="${infoempresa.empresa}"  type="text" class="form-control font-bold input-587px"  placeholder="Nome da Empresa">&nbsp*
       <!--      <span style="display: none;color: red"  id="emBrancoFornecedor">*Não deixa esse campo em branco<i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                 <span style="display: none;color: red"  id="jaExisteFornecedor">*Já existe um Fornecedor ou Cliente com esse nome cadastrado <i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                 <span style="display: none;color:green" id="naoExisteFornecedor">*Fornecedor disponível para cadastro <i class="glyphicon glyphicon-ok" style="top: 0px;left: 5px;color: green;"></i></span>
        -->         
                
                
                </div>
				<div class="divisor-fino"></div>
                 <div class="form-inline">
                 <input name="RazaoSocial" id="srazaoSocial"  value="${infoempresa.razaoSocial}" type="text" class="form-control input-587px"  placeholder="Razão Social"  >&nbsp*

                 </div>
				<div class="divisor-fino"></div>
                <div class="form-inline col-md-5 tira-padding">
                  <input value="${infoempresa.cnpj}" type="text" class="form-control" name="cnpj" placeholder="CNPJ" id="scnpj">&nbsp*
                <div class="divisor-fino"></div>
                </div>
					<div class="divisor-fino"></div>
                <div class="form-group">
                  <input value="${infoempresa.endereco}" type="text" class="form-control input-587px" name="endereco" placeholder="Endereço">
                </div>
                
                <div class="form-inline">
                  <input value="${infoempresa.bairro}" type="text" class="form-control" name="bairro" placeholder="Bairro">&nbsp
                  <input value="${infoempresa.cep}" type="text" class="form-control input-120px" name="cep" placeholder="CEP">&nbsp
                  <input value="${infoempresa.cidade}" type="text" class="form-control input-120px" name="cidade" placeholder="Cidade">&nbsp
                  
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
                
				  <input value="${infoempresa.telefone}" type="text" class="form-control" name="telefone" placeholder="Telefone" id="stelefoneEmpresa">&nbsp
                  <input value="${infoempresa.telefoneDesc}" type="text" class="form-control pop" id="stelefone" name="telefoneDesc" placeholder="Descrição do Telefone"
                  data-container="body" data-placement="right"
                  data-original-title="  Descrição do Telefone." data-content="Ex.: Telefone da Recepção" >

                </div>  
                    <div class="divisor-fino"></div> 
               <div class="form-inline">
                  <input value="${infoempresa.email}" type="text" class="form-control input-220px" name="email" placeholder="Email@email">&nbsp
                   <input value="${infoempresa.emailDesc}" type="text" class="form-control pop input-160px" id="semail" name="emailDesc" placeholder="Descrição do email"
                   data-container="body" data-placement="right"
                   data-content="Ex.: Email de contato da Recepção." data-original-title="  Descrição do email.">
                   <input value="${infoempresa.origem}" type="text" class="form-control pop" name="origem" placeholder="Conheceu por" style="width: 200px;">
               </div>
		<div class="divisor"></div>
	
				
		<div class="12 tira-padding formFornecedor input-587px" id="sfornecedorForm" style="padding:10px;background: #ecf0f1;">

				<div class="col-md-4 tira-padding" >
				<input type="hidden" value="${idpagamento}" name="idpagamento">
					<label>Pagamento Via  *</label>
  
                	 <select id="" class="form-control" name="idTipoPagamentoTransiente">
                	 
                	 	
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
                  
                  <textarea  class="form-control" cols="20" rows="5" name="obs" placeholder="Observações">${infoempresa.obs}</textarea> 
                </div>
  
                <div class="form-group col-md-12 tira-padding">
                  <hr class="message-inner-separator">  
                  <button type="submit" class="btn btn-danger" >Salvar</button>
<!--                   <a class="btn btn-primary display-none" id="ssfechaFornecedorLista" onclick="tiraLightbox();">Fechar</a> -->
                  <a class="btn btn-primary display-none" id="ssfechaFornecedorLista" onclick="location.reload();">Fechar</a>

                  <br><br>
                  
                  <span style="display: none;color: red" id="sssalvou">Cliente editado com sucesso !</span>
                  <span style="color: red" id="ssteste"></span>
                </div>
        </form>  
        </div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  </div>

 <div class="divisor-fino"></div>

</div>



<script type="text/javascript">
$(document).ready(function(){
	$('#atualizaEmpProspec').validate({
		rules: {
			empresa: { required: true, minlength: 2 },
			RazaoSocial: { required: true, minlength: 2 }
		},
		messages: {
			empresa: { required: 'Preencha o campo empresa', minlength: 'No mínimo 2 letras' },
			RazaoSocial: { required: 'Preencha o campo Razão Social', minlength: 'No mínimo 2 letras' }
			
		},
		submitHandler: function( form ){
			var dados = $( form ).serialize();
			
			$.ajax({
				type: "POST",
				url: "atualizaClienteProspeccao",
				data: dados,
				success: function( data )
				{   
					$("#ssalvaFornecedorLista").attr("disabled", true);
					$("#sssalvou").fadeIn(500);
					$("#ssfechaFornecedorLista").show(1000);
				}
			});
			
			return false;
		}
	});
});
</script>



</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<%-- <c:import url="../../_comum/footer.jsp" /> --%>

