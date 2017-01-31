<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:forEach items="${contato}" var="contato">
	<c:set var="habilitado" value="${contato.habilitado}" />
	<c:set var="idContato" value="${contato.idContato}" />
	<c:set var="nomeContato" value="${contato.contato}" />
	<c:set var="cargoContato" value="${contato.cargo}" />
	<c:set var="obsContato" value="${contato.obs}" />
	<c:set var="idEmpresa" value="${contato.empresa.idEmpresa}" />
</c:forEach>



<div class="contato">

              <input type="hidden" name="idEmp" value="${idEmpresa}" id="OutroidEmpNovo">
              <input type="hidden" name="idContato" value="${idContato}" id="OutroidContato">
              
              <div class="form-inline" style="margin-top: 20px;padding-bottom: 30px;">
                      
              <c:if test="${habilitado eq true}">
              	<div class="col-md-12" style="padding: 0;margin-top: -25px;font-family: 'OpenSansLight'">
	             <div class="col-md-8" style="padding: 0;">ATIVO</div>
	             <div class="col-md-4" style="padding: 0;padding-left: 23px;">	
	             	<input type="checkbox" name="habilitado" class="checkbox" onclick="ativaDesativaComunicador();" id="checarAtivo" >&nbsp;&nbsp;DESATIVAR
	             	<input type="checkbox" name="habilitado" class="checkbox" checked="checked" id="novoHabilitado" style="opacity:0">
              	 </div>	
              	</div>		
              </c:if>

              <c:if test="${habilitado eq false}">
	              <div class="col-md-12" style="padding: 0;margin-top: -25px;font-family: 'OpenSansLight'">
		             <div class="col-md-8" style="padding: 0;">DESATIVADO</div>
		             <div class="col-md-4" style="padding: 0;padding-left: 47px;">
		                <input type="checkbox" name="habilitado" class="checkbox" id="novoHabilitado">&nbsp;&nbsp;CHECAGEM	
                    	<input type="checkbox" class="checkbox"  name="habilitado" style="margin-top: -4px;" >&nbsp;&nbsp;ATIVAR
	              	 </div>	
	              	</div>
              
              </c:if>
                      
              </div>
              
              
              
              
              <!-- <div class="form-inline">
                <input type="checkbox" name="habilitado" checked="checked" class="checkbox" id="novoHabilitado">&nbsp&nbspAtivo
          	  <div class="divisor"></div>	
              </div> -->
            
              <div class="form-group">
                <input type="text" class="form-control" name="" placeholder="Nome do Contato" id="OutrocontatoNovo" value="${nomeContato}">
              </div>

              <div class="form-group">
                <input type="text" class="form-control" name="cargo" placeholder="Cargo" id="OutrocargoNovo" value="${cargoContato}">
              </div>
				
			  <div class="form-group">
                <input type="text" class="form-control" name="obs" placeholder="Observações" value="${obsContato}" id="novaObs">
                
              </div>	
 			
 			
 			  <div class="modal-footer" style="border: none">
        		<a href="#" class="btn btn-danger" onclick="editaContato('carrega');" id="editarContato">Editar Contato</a>
        		<button class="btn btn-default" data-dismiss="modal" aria-label="Close">Fechar</button>
    		  </div>	
			
		     <div style="background-color:#f1f1f1;border-radius:3px;margin-bottom: 10px;padding-top: 5px">
		
		
				<div class="retornoComunicador" style="padding: 10px;">
				        <input type="hidden" id="idContatoCriado" value="${contatos}">	
						<c:import url="contatoCriadoAjaxJob.jsp" />
				</div>	
         
         
         
	              <div class="form-inline" style="padding: 10px 10px 0px 10px;" > 
	                <a href="" class="btn btn-link" onclick="contato('telefone');" style="padding-left: 0" id="linkTelefone">Telefone</a>
	                |&nbsp&nbsp&nbsp<a href="" class="btn btn-link" onclick="contato('email');">Email</a>
	              </div>	
	             
	              <div id="OutrophoneContato" style="display:none;padding: 10px 10px 0px 10px;">
		        	   <div class="col-xs-6" style="padding-left: 0">
		           	  	<input type="text" class="form-control phone"  name="telefoneNovo" placeholder="Telefone" id="Outrophone">
					  </div>  		            
		        			
		     		  <div class="col-xs-6" style="padding-right: 0">      
			            <input type="text" class="form-control"  name="descricaoNovo" placeholder="Descrição" id="OutrodescricaoNovo">
		    		  </div>
		              
		              <div class="form-inline">
		                <a href="#" class="btn btn-link" style="padding-left: 0" onclick="SalvaComunicadorNovo('telefone');">Salvar</a>
		                <a href="#" type="reset" class="btn btn-link" onclick="contato('cancelar');">Cancelar</a>
		              </div>	
				  </div>
					
				  <div id="OutroContatoEmail" style="display:none;padding: 10px;">
		        	   <div class="col-xs-7" style="padding-left: 0">
		           	  	<input type="text" class="form-control"  name="email" placeholder="Email" id="OutroemailNovo">
					  </div>  		            
		        			
		     		  <div class="col-xs-5" style="padding-right: 0">      
			            <input type="text" class="form-control"  name="telefone" placeholder="OutrodescricaoNovo">
		    		  </div>
		              
		              <div class="form-inline">
		                <a href="#" class="btn btn-link" style="padding-left: 0" onclick="SalvaComunicadorNovo('email');">Salvar</a>
		                <a href="#" type="reset" class="btn btn-link" onclick="contato('cancelar');">Cancelar</a>
		              </div>	
				  </div>
				  
				  
				  
		</div>	  
</div>


	
      

				