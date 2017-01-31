<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Adiconar Contato</h4>
      </div>
      <div class="modal-body">
<!-- --------------------------------------------------------------------------------------------------------------------------------------- -->       
    		<div class="contato">
				<div id="ContatoRecebeInput">        
				         </div>
				           <div class="form-inline">
				             <input type="checkbox" name="habilitado" checked="checked" class="checkbox" id="ativoDesativado">&nbsp&nbspAtivo
				       	  <div class="divisor"></div>	
				           </div>
				           
				           <input type="hidden" name="idEmp" value="${prospeccao.idEmpresa.idEmpresa}" id="idEmNovo">
				           	
				           <div class="form-group">
				             <input type="text" class="form-control" name="" placeholder="Nome do Contato" id="contatoNovo">
				           </div>
				
				           <div class="form-group">
				             <input type="text" class="form-control" name="cargo" placeholder="Cargo" id="cargoNovo">
				           </div>
					
				           <div class="form-group">
				             <input type="text" class="form-control" name="obs" placeholder="Observações">
				           </div>	
				
						   <div class="col-md-12" style="background-color:#f1f1f1;border-radius:3px;margin-bottom: 10px;padding-top: 5px">
					  	   <div class="retornoContato" style="  margin-top: 5px;">
				   
				           	<input type="hidden" id="idContatoCriado" value="">	
  				           </div>	
				
				             <div class="form-inline" > 
				               <a href="" class="btn btn-link" onclick="contato('telefone');" style="padding-left: 0" id="linkTelefone">Telefone</a>
				               |&nbsp&nbsp&nbsp<a href="" class="btn btn-link" onclick="contato('email');">Email</a>
				             </div>	
				            
				            
				            <div id="phoneContato" style="display:none">
				        	   <div class="col-xs-6" style="padding-left: 0">
				           	  	<input type="text" class="form-control phone"  name="telefoneNovo" placeholder="Telefone" id="phone">
							  </div>  		            
				        			
				     		  <div class="col-xs-6" style="padding-right: 0">      
					            <input type="text" class="form-control"  name="descricaoNovo" placeholder="Descrição" id="descricaoNovo">
				    		  </div>
				              
				              <div class="form-inline">
				                <a href="#" class="btn btn-link" style="padding-left: 0" onclick="SalvaContatoAjax('telefone');">Salvar</a>
				                <a href="#" type="reset" class="btn btn-link" onclick="contato('cancelar');">Cancelar</a>
				              </div>	
						</div>
						
						<div id="ContatoEmail" style="display:none">
				        	   <div class="col-xs-7" style="padding-left: 0">
				           	  	<input type="text" class="form-control"  name="email" placeholder="Email" id="emailNovo">
							  </div>  		            
				        			
				     		  <div class="col-xs-5" style="padding-right: 0">      
					            <input type="text" class="form-control"  name="telefone" placeholder="Descrição">
				    		  </div>
				              
				              <div class="form-inline">
				                <a href="#" class="btn btn-link" style="padding-left: 0" onclick="SalvaContatoAjax('email');">Salvar</a>
				                <a href="#" type="reset" class="btn btn-link" onclick="contato('cancelar');">Cancelar</a>
				              </div>	
						</div>
			  	      </div>
				    </div>
<!-- --------------------------------------------------------------------------------------------------------------------------------------- -->       
	      </div>
	      <div class="col-md-12" id="novoContatoCriado">
	      	<span class='sucess' style="display:none"><i class='glyphicon glyphicon-ok' style='top: 2px;padding-right: 7px;'></i>&nbspContato Criado. Feche e selecione a lista de clientes novamente.</span>
	      </div>
	      <div class="modal-footer" style="border: none">
	      
	        <button class="btn btn-danger" data-dismiss="modal" aria-label="Close" disabled="disabled" id="ConcluiContato" onclick="location.reload();">Concluir</button>
	        <!-- <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close" onclick="location.reload();">Fechar</button> -->
	      </div>
	    </div>
  </div>
</div>


<!-- ----------------------------------------------------------- Modal Edita contato --------------------------------------------------------- -->
<!-- Modal -->
<div class="modal fade" id="ModalEdita" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Editar Contato</h4>
      </div>
      <div class="modal-body listacomunicadores">
			
			<!-- ------------------------------------------------------------------------------------------------------------ -->
		
			
			<!-- -----------------------------------------------------------------------------------------------------------  -->

      </div>
    </div>
  </div>
</div>