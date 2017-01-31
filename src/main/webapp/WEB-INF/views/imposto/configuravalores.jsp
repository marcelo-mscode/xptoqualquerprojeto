<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 

	<c:forEach items="${configuracao}" var="configuracao">
	
		<c:set var="idconfiguracao" value="${configuracao.idconfiguracao}"> </c:set>
		<c:set var="margemPadrao" value="${configuracao.margemPadrao}"> </c:set>
		<c:set var="administracaoPadrao" value="${configuracao.administracaoPadrao}"> </c:set>
		<c:set var="textoFinalPadrao" value="${configuracao.textoFinalPadrao}"> </c:set>
		<c:set var="textoFinalPadrao2" value="${configuracao.textoFinalPadrao2}"> </c:set>
	
	</c:forEach>




<ol class="breadcrumb col-md-offset-1">
    <li><a href="#">Menu</a></li>
    <li class="active">Configuração de Impostos</li>
</ol>



<div class="row col-md-offset-1">
          <div class="col-md-10 painel">
            <div class="col-md-7">
                    
              <div class="divisor"></div>
              <div class="divisor"></div>
              <h3 class="tira-margim-top" style="font-family:OpenSansLight;">Configuração de Impostos</h3>
              <hr>
<form action="salvaConfiguracao" method="post">

			  <div>
			  	<label>Margem padrão</label>
			  </div>	
              <div class="form-inline  tira-padding">
                    <div class="input-group col-md-4">
	                  <input type="hidden" value="${idconfiguracao}" name="idconfiguracao">
                      <input type="text" class="form-control" id="exampleInputAmount" value="${margemPadrao}"
                      placeholder="Margem Padrão" name="margemPadrao">
                      <div class="input-group-addon">%</div>
                    
                    </div>

              </div>

<div class="divisor"></div>

			  <div>
			  	<label>Fee padrão</label>
			  </div>
              <div class="form-inline tira-padding">
                <div class="input-group col-md-4">
                      <input type="text" class="form-control" id="exampleInputAmount" value="${administracaoPadrao}"
                        placeholder="Fee Padrão" name="administracaoPadrao">
                      <div class="input-group-addon">%</div>
                    
                </div>
              </div>
              

              <div class="form-group">
                    <div class="divisor"></div>
                    <label for="">Final Padrão</label>
                    <input type="text" class="form-control" placeholder="Notas Fiscais de Serviço de Assessoria de Evento."
                    value="${textoFinalPadrao}" name="textoFinalPadrao">
              </div>

              <div class="form-group">

                    <label for="">Final Padrão Destaque</label>
                    <input type="text" class="form-control" placeholder="Procedimento de emissão de nota conforme normas do Cliente."
                    value="${textoFinalPadrao2}" name="textoFinalPadrao2">
              </div>  

              <button class="btn btn-danger">Salvar Configuração</button>
              <div class="divisor"></div>
              <div class="divisor"></div>
</form>            
            </div>

          </div>
</div>
<div class="divisor-fino"></div>
<div class="row col-md-offset-1">
          <div class="col-md-10 painel">
            <div class="col-md-12">

              <div class="form-inline  tira-padding">
              <div class="divisor"></div>
              <div class="divisor"></div>
                    <h3 class="tira-margim-top" style="font-family:OpenSansLight;">Impostos</h3>
              </div>
<hr>



              <table class="table table-striped table-td-ajuste table-sem-bold" style="font-size: 12px;">
                  <tr>
                    <th>Editar</th>
                    <th>idImposto</th>
                    <th>Imposto</th>
                    <th>Título do Imposto</th>
                    <th>Descrição do Imposto</th>
                  </tr>
                  
                  
	              <c:forEach items="${imposto}" var="imposto">
	              	<tr>
	                    <td><a href="#" onclick="modalConfig('ChamaJSPEditaImpostoPorAjax?idImposto=${imposto.idimposto}')" >Editar</a></td>
	                    <td>${imposto.idimposto}</td>
	                    <td>${imposto.imposto}</td>
	                    <td>${imposto.impostoTitulo}</td>
	                    <td>${imposto.impostoTitulo} (${imposto.imposto}%)</td>
                     </tr>
       			  </c:forEach>


				</table>
				
              
              <div class="form-group ">            
                <button class="btn btn-link tira-padding" id="tiraImposto">[+] Inserir Imposto</button>
               
                  <div class="imposto display-none">
                   
                    <div class="form-group tira-padding">
                      <div class="divisor-fino"></div>
        <form action="salvaImposto" method="post" id="salvaImposto">
                      <input type="text" class="form-control input-titulo-imposto"  name="impostoTitulo" placeholder="Título do Imposto" id="impostoTitulo">
                    </div>
                    
                    <div class="form-group tira-padding">
                      <div class="divisor-fino"></div>
                      <input type="text" class="form-control input-imposto" name="imposto" placeholder="Imposto em %" id="imposto">
                    </div>
                    

                    <button type="submit" class="btn btn-danger">Salvar Imposto</button> 
	    	                  
                    <button type="reset" class="btn btn-primary">Cancelar</button> 
          </form>          


                <div class="divisor"></div>
                </div>    
              </div>

            </div>  
          
</div>

</div>              

<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Editar Configuração do Imposto</h4>
        <img src="<c:url value="resources/images/loader.GIF" />" width="40" height="40" class="loader" alt="loading" />
      </div>
      <div class="modal-body">
          <div id="chama"></div>
      </div>

      <div class="modal-footer"></div>
    </div>
  </div>
</div>



<!-- Fim Modal -->

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br>

</div>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<c:import url="../_comum/footer.jsp" />