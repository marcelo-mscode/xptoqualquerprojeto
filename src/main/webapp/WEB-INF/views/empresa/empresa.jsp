<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<div class="col-md-12 bodyXY" style="height: 35px;border-bottom: 1px solid #ddd;">

	<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li><a href="listaempresa?first=0&max=350">Lista de Empresas</a></li>
			<li class="active">Nova Empresa</li>
		</ol>
	</div>
</div>
<div style="width: 100%;height: 50px;border-bottom: 1px solid #ddd;padding: 45px 27px;background: #fff">
	<h4 style="font-family: 'OpenSansLight'">CADASTRO DE NOVA EMPRESA</h4>
</div>









<div class="container"> 

  <div class="row">

    <!-- - - - - - - - - - -  Cadastra Empresa - - - - - - - - - - -->
    <div class="col-md-7 painel estilo-painel-job" style="padding: 20px 0 25px 9px;margin-left: -8px;border-right: 1px solid #ddd;">
        <div class="col-md-11">
        	
            <form action="salvaEmpresa" method="post" id="salvaEmpresa" enctype="multipart/form-data">
            
            <input type="hidden" value="${infoempresa.idEmpresa}" name="idEmpresaAtualiza">
                <div class="form-inline" style="border-bottom: 1px solid #ddd;margin-bottom: 28px;padding-bottom: 10px;">
                    <label for="">Categoria:</label>
                    <input type="checkbox" class="checkbox ajuste" name="cliente" checked="checked">&nbspCliente
                    <input type="checkbox" class="checkbox ajuste" name="fornecedor" id="fornecedorEmpresa" >&nbsp&nbspFornecedor
                    <input type="checkbox" class="checkbox ajuste" name="prospect" >&nbsp&nbspProspect
                    &nbsp |&nbsp <input type="checkbox" name="habilitado" checked="checked" class="checkbox ajuste">&nbsp&nbspAtivo
                </div>
                <div class="form-inline">

                 <input type="text" class="form-control font-bold input-560px" name="empresa" placeholder="Nome da Empresa" id="nomeEmpresa" onblur="verificaFornecedor();">&nbsp*
                 <span style="display: none;color: red"  id="emBrancoFornecedor">*Não deixa esse campo em branco<i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                 <span style="display: none;color: red"  id="jaExisteFornecedor">*Já existe um cadastro com esse nome<i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                 <span style="display: none;color:green" id="naoExisteFornecedor">*Nome disponível para cadastro<i class="glyphicon glyphicon-ok" style="top: 0px;left: 5px;color: green;"></i></span>


                </div>
				<div class="divisor-fino"></div>
                 <div class="form-inline">
                 <input type="text" class="form-control input-560px" name="RazaoSocial" placeholder="Razão Social" id="razaoSocial" >&nbsp*

                 </div>
				<div class="divisor-fino"></div>
                <div class="form-inline col-md-5 tira-padding">
                  <input type="text" class="form-control" name="cnpj" placeholder="CNPJ" id="cnpj">&nbsp*
                <div class="divisor-fino"></div>
                </div>
					<div class="divisor-fino"></div>
                <div class="form-group">
                  <input type="text" class="form-control input-560px" name="endereco" placeholder="Endereço">
                </div>
                
                <div class="form-inline">
                  <input type="text" class="form-control" name="bairro" placeholder="Bairro">&nbsp&nbsp&nbsp&nbsp&nbsp
                  <input type="text" class="form-control" name="cep" placeholder="CEP">
                  
                </div>  
                
                <div class="divisor-fino"></div>

                <div class="form-inline">
                
                  <input type="text" class="form-control" name="cidade" placeholder="Cidade">&nbsp&nbsp&nbsp&nbsp&nbsp
                  
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
				  <input type="text" class="form-control" name="telefone" placeholder="Telefone" id="telefoneEmpresa">
                
                  <input type="text" class="form-control pop" id="telefone" name="telefoneDesc" placeholder="Descrição do Telefone"
                  data-container="body" data-placement="right"
                  data-original-title="  Descrição do Telefone." data-content="Ex.: Telefone da Recepção" >


                </div>  
                    <div class="divisor-fino"></div> 
               <div class="form-group">
                  <input type="text" class="form-control" name="email" placeholder="Email@email">
               </div>
            
               <div class="form-group">
                   <input type="text" class="form-control pop" id="email" name="emailDesc" placeholder="Descrição do email"
                   data-container="body" data-placement="right"
                   data-content="Ex.: Email de contato da Recepção." data-original-title="  Descrição do email.">
                   
               </div>
            
            
            
                
                <div class="form-group col-md-6 tira-padding">
                 
                  <input type="text" class="form-control pop" name="origem" placeholder="Conheceu por"
				   data-container="body" data-placement="right"
                   data-original-title="Conheceu por:" data-content="Ex.: 'Histórico' ou 'Indicação da Empresa X' ... etc">

                </div>
	
   	    <div class="divisor"></div>
		<div class="divisor"></div>
		<div class="divisor"></div>
	
	
				
				 <c:if test="${!infoempresa.fornecedor}">
     				<div class="12 tira-padding display-none formFornecedor" id="fornecedorForm" style="padding:10px;background: #ecf0f1;">            
                 </c:if>
                 
                 <c:if test="${infoempresa.fornecedor}">
					<div class="12 tira-padding formFornecedor" id="fornecedorForm" style="padding:10px;background: #ecf0f1;">
    	          </c:if>
		
		
		
		


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
             
				
                <div class="form-group">
                  
                  <textarea class="form-control" cols="20" rows="5" name="obs" placeholder="Observações"></textarea> 
                </div>
  
                <div class="form-group col-md-12 tira-padding">
                  <hr class="message-inner-separator">  
                  <button type="submit" class="btn btn-danger is-disabled" id="salvaFornecedorLista">Salvar</button>
                </div>
          
        </form>  
        </div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  </div>
  <!-- - - - - - - - Fim Cadastra Empresa - - - - - - - - - - - -  -->
  
  <!-- - - - - - - - Cadastra Tag / Contatos - - - - - - - - - - - -  -->
  
  <!-- <div class="col-md-3 ajuste-left tira-padding-top">
  	
   	 <div class="row painel padding-bottom">      
      <div class="col-md-12 ">
       <button class="btn btn-link tira-padding" id="tiraContato">Adicionar Contato</button>
       <p class="desenvolvimento">Em desenvolvimento</p>
          
          <div class="contato display-none">
          <div class="divisor"></div>
           <form action="salvaContato" method="post">
              
              <div class="form-inline">
                <input type="checkbox" name="habilitado" checked="checked" class="checkbox">&nbsp&nbspAtivo
          	  <div class="divisor"></div>	
              </div>
              
              <input type="hidden" name="idEmp" value="1">
              	
              <div class="form-group">
                <input type="text" class="form-control" name="contato" placeholder="nome">
              </div>

              <div class="form-group">
                <input type="text" class="form-control" name="cargo" placeholder="Cargo">
              </div>

              <div class="form-group">
                <input type="text" class="form-control"  name="telefone" placeholder="Telefone">
              </div>
	
              <div class="form-group">
                <input type="text" class="form-control" name="email" placeholder="email@email">
              </div>   
				
			  <div class="form-group">
                <input type="text" class="form-control" name="obs" placeholder="Observações">
              </div>	

          
                 <div class="divisor"></div>
                <div class="form-group col-md-2 tira-padding">
              <button type="submit" class="btn btn-danger">Salvar Contato</button>
           </div>
         </form>
       </div>
    </div>
</div>
  	
  	<div class="divisor"></div>
 	  
    <div class="row painel padding-bottom">          
     <div class="col-md-12">
       <button class="btn btn-link tira-padding" id="tiraTag">Atuação/Tags</button>
       <p class="desenvolvimento">Em desenvolvimento</p>
          
          <div class="display-none tag">
            <div class="divisor"></div>
           <form action="" >
             <select multiple class="form-control" name="" id="">
                <option>Armações</option>
                <option>Bistrô</option>
                <option>Iluminação</option>
                <option>Transporte</option>
                <option>Iluminação</option>
                <option>Transporte</option>
                <option>Iluminação</option>
                <option>Transporte</option>
                <option>Iluminação</option>
                <option>Transporte</option>
             </select>
           <div class="divisor"></div>
           <div class="form-inline col-md-12 tira-padding">
              <button type="submit" class="btn btn-danger">Salvar</button>
              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Tags">Criar Tag</button>
           </div>
         </form>
         
         
       </div>
    </div>
    
    
    
    
    
    
  </div>

</div> -->
  <!-- - - - - - - - Cadastra Tag / Contatos - - - - - - - - - - - -  -->
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

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
            <input type="text" class="form-control" placeholder="Nome da nova Tag" style="width:180px">
            <div class="form-inline">
            </div>
         
              
  
    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Salvar Tag</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        
      </div>
    </div>
  </div>
</div>




<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />
<script type="text/javascript" src="<c:url value="resources/js/fornecedor.js" />"></script>