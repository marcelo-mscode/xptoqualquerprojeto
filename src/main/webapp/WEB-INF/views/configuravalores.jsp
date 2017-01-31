<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="_comum/header.jsp" />

<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 


<ol class="breadcrumb col-md-offset-1">
    <li><a href="#">Menu</a></li>
    <li class="active">Configuração de valores padrão</li>
</ol>



<div class="row col-md-offset-1">
          <div class="col-md-10 painel">
            <div class="col-md-7">
                    
              <div class="divisor"></div>
              <div class="divisor"></div>
              <h3 class="tira-margim-top" style="font-family:OpenSansLight;">Configuração de Impostos</h3>
              <hr>
              <div class="form-inline  tira-padding">
                    <div class="input-group col-md-4">
      
                      <input type="text" class="form-control" id="exampleInputAmount"
                        placeholder="Marg. Padrão">
                      <div class="input-group-addon">%</div>
                    
                    </div>

              </div>

              <div class="form-inline tira-padding">
                    <div class="divisor"></div>
                <div class="input-group col-md-4">
      
                      <input type="text" class="form-control" id="exampleInputAmount"
                        placeholder="Fee Padrão">
                      <div class="input-group-addon">%</div>
                    
                </div>
              </div>
              

              <div class="form-group">
                    <div class="divisor"></div>
                    <label for="">Final Padrão</label>
                    <input type="text" class="form-control" placeholder="Notas Fiscais de Serviço de Assessoria de Evento.">
              </div>

              <div class="form-group">

                    <label for="">Final Padrão Destaque</label>
                    <input type="text" class="form-control" placeholder="Procedimento de emissão de nota conforme normas do Cliente.">
              </div>  

              <button class="btn btn-danger">Salvar Configuração</button>
              <div class="divisor"></div>
              <div class="divisor"></div>
            </div>

          </div>
</div>
<div class="divisor-fino"></div>
<div class="row col-md-offset-1">
          <div class="col-md-10 painel">
            <div class="col-md-11">

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
                  
                  <tr>
                    <td><a href="#" onclick="modalConfig(1);" >Editar</a></td>
                    <td>1</td>
                    <td>22,9</td>
                    <td>Imposto Locco</td>
                    <td>Imposto Locco (22,9%)</td>
                  </tr>

                  <tr>
                    <td><a href="#" onclick="modalConfig(2);" >Editar</a></td>
                    <td>2</td>
                    <td>15,15</td>
                    <td>Imposto Locco2</td>
                    <td>Imposto Locco2 (15,15%)</td>
                  </tr>

                  <tr>
                    <td><a href="#" onclick="modalConfig(3);" >Editar</a></td>
                    <td>3</td>
                    <td>12,8</td>
                    <td>Imposto Locco</td>
                    <td>Imposto Locco (12,8%)</td>
                  </tr>

                  <tr>
                    <td><a href="#" onclick="modalConfig(4);" >Editar</a></td>
                    <td>4</td>
                    <td>22,9</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% )</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% ) (22,9%)</td>
                  </tr>

                  <tr>
                    <td><a href="#" onclick="modalConfig(5);">Editar</a></td>
                    <td>5</td>
                    <td>22,9</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% )</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% ) (22,9%)</td>
                  </tr>

                  <tr>
                    <td><a href="#" onclick="modalConfig(6);" >Editar</a></td>
                    <td>6</td>
                    <td>22,9</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% )</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% ) (22,9%)</td>
                  </tr>

                  <tr>
                    <td><a href="#" onclick="modalConfig(7);" >Editar</a></td>
                    <td>7</td>
                    <td>22,9</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% )</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% ) (22,9%)</td>
                  </tr>

                  <tr>
                    <td><a href="#" onclick="modalConfig(8);" >Editar</a></td>
                    <td>8</td>
                    <td>22,9</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% )</td>
                    <td>Locco Sebrae (16,86% + 1/3 de 11% ) (22,9%)</td>
                  </tr>
              
              </table>
              
              <div class="form-group ">            
                <button class="btn btn-link tira-padding" id="tiraImposto">Inserir Imposto</button>
               
                  <div class="imposto display-none">
                   
                    <div class="form-group tira-padding">
                      <div class="divisor-fino"></div>
                      <input type="text" class="form-control input-titulo-imposto" placeholder="Título do Imposto">
                    </div>
                    
                    <div class="form-group tira-padding">
                      <div class="divisor-fino"></div>
                      <input type="text" class="form-control input-imposto" placeholder="Imposto em %">
                    </div>
                    

                    <button class="btn btn-danger">Salvar Imposto</button> 
                    <button class="btn btn-info">Cancelar</button> 
                    


                <div class="divisor"></div>
                </div>    
              </div>

            </div>  
          
</div>

</div>              

<!-- Button trigger modal -->


<!-- Modal -->
<form action="" method="post">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Editar Configuração do Imposto</h4>
      </div>
      <div class="modal-body">
          <div id="chama">
          	
          	
          	<div class="form-group">
          	<label>Título</label>
	          	   <input type="text" class="form-control input-titulo-imposto" value="" placeholder="Título do Imposto">	
	        </div>
          	
          	<div class="form-group">
          	<label>Imposto</label>	
	           	   <input type="text" class="form-control input-imposto" value="" placeholder="Imposto %">	
            </div>
          	
          	
          	
          	
          	
          	
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Salvar</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
       
      </div>
    </div>
  </div>
</div>
</form>	
<!-- Fim Modal -->







<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br>

</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->


<c:import url="_comum/footer.jsp" />