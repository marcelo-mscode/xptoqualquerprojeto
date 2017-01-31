<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-md-5 ajuste-left tira-padding-top">
   <div class="row painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;border-right: 1px solid #ddd;">
      <div class="col-md-12 ">
         <!-- --------------- Novo contato --------------- -->	
         <button class="btn btn-primary" id="tiraContato">Adicionar novo Contato</button>
         <div class="contato display-none" style="margin-top: 10px;">
            <!--        <form action="salvaContato" method="post" id="salvaContato"> -->
            <div id="ContatoRecebeInput">        
            </div>
            <div class="form-inline">
               <input type="checkbox" name="habilitado" checked="checked" class="checkbox" id="ativoDesativado">&nbsp&nbspAtivo
               <div class="divisor"></div>
            </div>
            <input type="hidden" name="idEmp" value="${infoempresa.idEmpresa}" id="idEmNovo">
            <div class="form-group">
               <input type="text" class="form-control" name="contato" placeholder="Nome do Contato" id="contatoNovo">
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
            <div class="form-group col-md-2 col-md-offset-9">
               <button class="btn btn-danger" onclick="location.reload();" disabled="disabled" id="ConcluiContato">Concluir</button>
            </div>
            <div class="divisor"></div>
         </div>
      </div>
      <!-- --------------- Novo contato --------------- -->	
      <div class="col-md-12 tira-padding-top">
         <hr>
         <h5 style="font-family: 'OpenSansLight'">CONTATOS</h5>
         <div style="height: 200px;overflow: overlay;background-color: #f0f0f0;padding: 10px 25px 10px 10px;border: 1px solid #ccc; margin-bottom: 10px;">
            <div class="form-group">
               <c:forEach items="${contato}" var="contato">
                  <c:if test="${contato.habilitado == true}">
                  
                  <div class="table-comunicador">
                     <table>
                        <tbody class="info-contato">
                           <tr>
                              <td>
                                 <a href="#" onclick="pegaComunicadoresPorIdContato(${contato.idContato},'carrega')" data-toggle="modal" data-target="#ModalEdita">${contato.contato} - ${contato.cargo}</a>
                                 <%-- <span style="color: #333743">${contato.contato} - ${contato.cargo}</span> --%>
                                 <%-- <a href="${contato.idContato}">${contato.contato} - ${contato.cargo}</a> --%>
                              </td>
                           </tr>
                           <c:forEach items="${contato.comunicador}" var="comunicador">
                              <tr>
                                 <td>${comunicador.comunicador}</td>
                              </tr>
                           </c:forEach>
                        </tbody>
                     </table>
                  </div>
                  
                  </c:if>
               </c:forEach>
            </div>
         </div>
      </div>
   </div>
   <div class="divisor"></div>
   <div class="row painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;border-right: 1px solid #ddd;">
      <div class="col-md-12 " style="border-bottom: 0px solid #D7D1C8;padding-left: 0;">
         <div class="col-md-4">
            <button class="btn btn-link" id="tiraTag" style="margin-top: -10px;">[+] Adicionar Tags</button>
         </div>
         <div class="col-md-6">
         </div>
         <hr class="message-inner-separator">
         <div class="display-none tag col-md-10">
            <div class="divisor"></div>
            <form action="SalvarTagEmpresa" method="post" >
               <input type="hidden" value="${infoempresa.idEmpresa}" name="idEmpresaAtuacao"/>   
               <select class="form-control" name="idAtuacao" id="">
                  <c:forEach items="${tags}" var="tags">
                     <option value="${tags.idAtuacao}">${tags.atuacao}</option>
                  </c:forEach>
               </select>
               <div class="divisor"></div>
               <div class="form-inline col-md-10 tira-padding">
                  <button type="submit" class="btn btn-danger">Adicionar Tag</button>
                  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Tags">Criar Tag</button>
                  <div class="divisor"></div>
               </div>
            </form>
         </div>
      </div>
      <div class="col-md-12 painel">
         <div class="col-md-12" style="padding-left: 0;margin: 10px 0 10px 6px;">
            
            <b>Tags Relacionadas:</b>
				<table class="table table-condensed" style="margin-top: 5px;">
  				  <c:forEach items="${empresaAtuacao}" var="empresaAtuacao">
					<tr>
						<td class="input-160px"><span style="font-style: italic;">${empresaAtuacao.idAtuacao.atuacao}</span></td>
						<td><a href="excluitagEmpresa?idEmpresaAtuacao=${empresaAtuacao.idEmpresaAtuacao}&idEmpresa=${infoempresa.idEmpresa}">Excluir</a></td>
					</tr>
				  </c:forEach>
				</table>
            
            
            
           <%--  <p>Tags Relacionadas:</p>
            
            
            <c:forEach items="${empresaAtuacao}" var="empresaAtuacao">
               <span style="font-style: italic;">${empresaAtuacao.idAtuacao.atuacao} - </span>
            </c:forEach> --%>
            
            
            
            
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
        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button> -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -10px;">
            <span aria-hidden="true" style="font-size: 50px;font-family: 'OpenSansLight';font-weight: normal;">&times;</span>	
        </button>
        <h4 class="modal-title fontLight" id="myModalLabel">EDITAR CONTATO</h4>
      </div>
      <div class="modal-body listacomunicadores">
			
			<!-- ------------------------------------------------------------------------------------------------------------ -->
		
			
			<!-- -----------------------------------------------------------------------------------------------------------  -->

      </div>
    </div>
  </div>
</div>























<%-- <div class="col-md-5 ajuste-left tira-padding-top">
  	
  	 <div class="row painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;border-right: 1px solid #ddd;">
      <div class="col-md-12 ">
 


	 <button class="btn btn-primary" id="tiraContato">Adicionar novo Contato</button>
       <div class="contato display-none" style="margin-top: 10px;">
 <!--        <form action="salvaContato" method="post" id="salvaContato"> -->
        
			<div id="ContatoRecebeInput">        
        	  
            </div>
           
              <div class="form-inline">
                <input type="checkbox" name="habilitado" checked="checked" class="checkbox">&nbsp&nbspAtivo
          	  <div class="divisor"></div>	
              </div>
              
              <input type="hidden" name="idEmp" value="${infoempresa.idEmpresa}" id="idEmNovo">
              	
              <div class="form-group">
                <input type="text" class="form-control" name="contato" placeholder="Nome do Contato" id="contatoNovo">
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
                 
                <div class="form-group col-md-2 col-md-offset-9">
            			<button class="btn btn-danger" onclick="location.reload();" disabled="disabled" id="ConcluiContato">Concluir</button>
                </div>
       	<div class="divisor"></div>
      
         
       </div>
 </div>

<!-- --------------- Novo contato --------------- -->	

<div class="col-md-12 tira-padding-top">
       <hr>


     <h5 style="font-family: 'OpenSansLight'">CONTATOS</h5>
          <div style="height: 200px;overflow: overlay;background-color: #f0f0f0;padding: 10px 25px 10px 10px;border: 1px solid #ccc;
          margin-bottom: 10px;">
             <div class="form-group">		
					<c:forEach items="${contato}" var="contato">
					 <div class="table-comunicador"> 
					  <table>
           		         <tbody class="info-contato">

								<tr>
									<td>
											<span style="color: #333743">${contato.contato} - ${contato.cargo}</span>
										<a href="${contato.idContato}">${contato.contato} - ${contato.cargo}</a>
									</td>
								</tr>
						    	
 								<c:forEach items="${contato.comunicador}" var="comunicador">	    	
						    		<tr>
						    			<td>${comunicador.comunicador}</td>
  						    	    </tr>
  						    	    	
						    	</c:forEach>
						  </tbody>
           	            </table>
           	            </div>						
					</c:forEach>
	       	</div>	
              
              
              </div> 
      </div>
    </div>

 	
  	
  	
  	<div class="divisor"></div>
<div class="row painel estilo-painel-job" style="padding: 20px 0 30px 9px;margin-left: -8px;border-right: 1px solid #ddd;">

	<div class="col-md-12 " style="border-bottom: 0px solid #D7D1C8;padding-left: 0;">
	
		
	    
		<div class="col-md-4">
			<button class="btn btn-link" id="tiraTag" style="margin-top: -10px;">[+] Adicionar Tags</button>
	    </div>
	    
	    <div class="col-md-6">
			
	    </div>
	    <hr class="message-inner-separator">
	    <div class="display-none tag col-md-10">
            <div class="divisor"></div>
         <form action="SalvarTagEmpresa" method="post" >
           <input type="hidden" value="${infoempresa.idEmpresa}" name="idEmpresaAtuacao"/>   
             <select class="form-control" name="idAtuacao" id="">
                           <c:forEach items="${tags}" var="tags">
		           		       	<option value="${tags.idAtuacao}">${tags.atuacao}</option>
		                  </c:forEach>
            </select> 
           
           <div class="divisor"></div>
           
           <div class="form-inline col-md-10 tira-padding">
              <button type="submit" class="btn btn-danger">Adicionar Tag</button>
              
              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#Tags">Criar Tag</button>
           <div class="divisor"></div>
           </div>
           
          </form>
          
        </div>
	    
	    
	    
	
	</div>
	
	<div class="col-md-12 painel">	
		 	<div class="col-md-12" style="padding-left: 0;margin: 10px 0 10px 6px;">  
	
				<p>Tags Relacionadas:</p>
				<c:forEach items="${empresaAtuacao}" var="empresaAtuacao">
					   <span style="font-style: italic;">${empresaAtuacao.idAtuacao.atuacao} - </span>
				</c:forEach>
			</div>
	</div>
	
</div>

</div> --%>









