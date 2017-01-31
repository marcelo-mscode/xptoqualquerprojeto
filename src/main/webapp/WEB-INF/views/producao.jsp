<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="_comum/header.jsp" />



<div class="container"> 

<!-- <div class="col-md-12 tira-padding" style="background: #fff;box-shadow: 5px 5px 5px #ccc;margin-bottom: 25px;
" >
    <div class="col-md-12 col-md-offset-1">
    
      <h4>A lista de produção funcionará da seguinte maneira:</h4>

      <p>
      Ao preencher os dados de título, cliente e job e clicar em "Criar nova lista de Produção",<br>
      automaticamente será preenchida a planilha abaixo com o título da lista. <br><br>
      
      Clicando no item <img src="http://maissim.com.br/syslocc/images/categoria.PNG" alt=""> um formulário será aberto para criar uma nova categoria na planilha.
      <br><br>
      Ao clicar no ícone &nbsp&nbsp&nbsp&nbsp<img src="http://maissim.com.br/syslocc/images/linha.PNG" alt=""> e preencher o formulário, uma nova linha será inserida na planilha daquela categoria.
      <br><br>
      Com a nova linha criada, podemos clicar no item da linha <img src="http://maissim.com.br/syslocc/images/cadeira.PNG" alt="">, e preencher os dados do Item. Após o preenchimento<br> os dados serão salvos e atualizados automaticamente.
      <br><br>
      Basta agora repetir o processo para criar novas linhas ou novas categorias na planilha ou mesmo atualiza-las. 
      <br /><br />




      </p>
    </div>
</div> -->




  <ol class="breadcrumb">
    <li><a href="#">Menu</a></li>
    <li><a href="#">Produção</a></li>
    <li class="active">Nova  lista de Produção</li>
  </ol>

<!-- Criar nova lista-->
<div class="row">
  
 <div class="col-md-10 painel ajuste-left">
 
    <form action="cadastraLista" class="col-md-8" id="cadastraLista">
      <div class="form-group">
        <label for="">Título da lista</label>
        <input type="text" class="form-control" style="font-weight:bold" placeholder="Título" name="lista" id="tituloLista" >
      </div>

      <div class="form-group col-md-8 tira-padding">
          <label for="exampleInputEmail1">Cliente</label>
          <select class="form-control" onchange="editaCelulaComunicador(this.value);" id="selectLista">
           <option value="">Selecione o Cliente</option>
           <c:forEach items="${empresas}" var="empresas">
           		<option>${empresas}</option>
           </c:forEach>
          </select>
        </div>
      
      <div class="form-group col-md-8 tira-padding">
          <label for="exampleInputEmail1">Job</label>
          <select class="form-control" id="selectJobsEmpresa" name="idJobLista">
            
          </select>
      </div>
      
      <div class=" form-inline col-md-12 tira-padding altura">
      <hr>
          <div class="form-group">
            <input class="btn btn-danger" type="submit" value="Criar Nova Lista de Produção">
          </div>
        
           <!-- <div class="form-group col-md-offset-0">
            <input class="btn btn-danger" type="submit" value="Copiar Lista Existente">
          </div> -->
      </div>  
    </form>  
  </div>
</div>
<!-- Fim Criar Listra-->
<div class="col-md-12" style="height:5px"></div>




<!-- Gerar Planilha-->
<!-- <div class="row col-md-offset-1">
 <div class="col-md-10 painel ajuste-left">

 <div class="col-md-12">
 
 

 
  <h4>LISTA DE PRODUÇÃO</h4>
  <hr>
  <button type="button" class="btn btn-default" disabled="disabled">Gerar Planilha do Cliente</button>
  <button type="button" class="btn btn-default" disabled="disabled">Exportar para Excel</button>
  <p style="color:red">( Em desenvolvimento )</p>
  <div class="col-md-12" style="height:10px"></div>  
  <table class="table table-striped table-td-ajuste" style="font-size: 12px;">
    <tr>
      <td>Código da Lista</td>
      
      <td>Título da lista</td>
      
      <td>Cliente</td>
      
      <td>Job</td>
      
    </tr>
    <td style="font-weight:bold;border-right:1px solid #ccc">1111003-LP020 .0</td>
    <td style="font-weight:bold;border-right:1px solid #ccc">Lançamento Xarelto Villa Rossa -  EXTRAS</td>
    <td style="font-weight:bold;border-right:1px solid #ccc">Bayer S/A</td>
    <td style="font-weight:bold;border-right:1px solid #ccc">Lançamento Xarelto Villa Rossa -  EXTRAS</td>
    <tr>
      
    </tr>
  </table>


  <div class="col-md-12 tira-padding" style="line-height: 40px;">

      <div class="col-md-8 tira-padding">

        <i class="glyphicon glyphicon-pencil"></i> 
        <button class="btn btn-link" id="TiraCategoria">Inserir Categoria</button>
      </div>

    <div class="col-md-8 form-group  tira-padding categoria" style="display:none">
          <div class="divisor"><hr></div>
          <div class="col-md-6 tira-padding ">

            <input type="text" class="form-control" placeholder="Nome da Categoria">
         </div>     

          
            <div class="col-md-8 tira-padding" style="margin: 10px 0;">        
              <select name="" id="">
                 <option value="">Locco imposto (22.9%)</option>
                 <option value="">Locco imposto (22.9%)</option>
                 <option value="">Locco imposto (22.9%)</option>
                 <option value="">Locco imposto (22.9%)</option>
                 <option value="">Locco imposto (22.9%)</option>
              </select>
           </div>
        
      <div class="col-md-6 tira-padding">  
        <input class="btn btn-danger" type="submit" value="Criar Categoria">
      </div>
    </div>
  </div>

 </div>
</div>
</div> -->
<!-- Fim Gerar Planilha -->

<div class="col-md-12" style="height:5px"></div>

<!-- Edicção Planilha-->
<!-- <div class="row col-md-offset-1">
 <div class="col-md-10 painel ajuste-left">

 <div class="col-md-12">
 
 

 
  
  <div class="col-md-12" style="height:10px"></div>  
      

      <table class="table table-striped table-td-ajuste ordem" style="font-size: 14px;border:1px solid #ccc">
  
    
    <th>Linha</th>
    <th>Fat Locco</th>
    <th>Fat Direto</th>
    <th>Opcional</th>
    <th>Informações</th>
    <th>Não inclusos no custo</th>
    <th>Excluir</th>

    <div class="ordem">
      
        <tr>
          <td colspan="7" style="background: #ccc" >
            <strong>Segurança</strong>
          </td>
        </tr>

        <tr>
          <td><button id="Cadeiras" type="button" class="btn btn-link">Cadeiras</button></td>
          <td>25.000,00</td>
          <td></td>
          <td></td>
          <td>Cadeiras confortáveis </td>
          <td>Nenhuma.</td>
          <td>
              <a href="" id="" style="color:red;">
                <i class="glyphicon glyphicon-remove"></i>
              </a>
          </td>

        </tr>


        <tr >
        <td colspan="7" class="cadeiras" style="display: none">
        <div class="col-md-12">
          <p>Inserir/Editar Item</p>
      <div class="row">
          <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">
            <div class="col-md-1">FILTRO</div>
            
                <div class="col-md-3" >
                    Tags
                    <select name="" id="" class="form-control">
                      <option value="">A&B</option>
                      <option value="">Assentos</option>
                      <option value="">Cadeiras</option>
                      <option value="">Bancos</option>
                      <option value="">Segurança</option>
                    </select>


                </div>
                
                <div class="col-md-5">
                Produtos
                   <select  multiple="" class="form-control" name="" id="">
                      <option value="">Eventos de Cantores Junior</option>
                      <option value="">Kalunga</option>
                      <option value="">C&C</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">Segurança</option>
                    </select> 

                </div>
          </div>  
  

      </div>Fim Filtro

          Desc Item    
          <div class="row">
              <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">
                                
                <div class="col-md-4" style="font-size:12px;color:#000;border-right:1px solid #dfdfdf">
                    <h5>Informações de   cadastro</h5>
                    <p>Custo cadastro R$ <span>2.500,00</span></p>
                    <p>Margem % <span>0,00</span></p>
                    <p>Preço s/ NF R$ <span>2.500,00</span></p>
                    <div class="checkbox ">
                    <label>
                      <input type="checkbox">Custo com BV forn. incluso
                    </label>
                    </div>
                </div>

              


               Custo Itens
              <div class="col-md-4" style="border-right:1px solid #dfdfdf;font-size: 12px;line-height: 20px;">
                      <div class="form-inline" style="margin-top:10px">
                      Custo Net Unit. R$
                      &nbsp<input type="text" class="form-control" style="  height: 24px;width: 110px;">
                      </div>

                    <div class="form-inline">
                    Marg. Padr.(Over)
                    &nbsp&nbsp<input type="checkbox">&nbsp% <span>0,00</span>
                    </div>

                    <div class="form-inline">
                      BV. Venda R$ R$
                      &nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" class="form-control" style="  height: 24px;width: 110px;">
                    </div>
                    
                    <div class="form-inline" style="background:#e4eef2">
                      Preço Unit. s/ NF R$ <span>25.000,00</span>&nbsp&nbsp<span>[0,00]</span>
                    </div>
                    
                    <div class="form-inline">
                    Incide Imposto
                    &nbsp&nbsp<input type="checkbox">&nbsp<span>% 22,90</span>
                    </div>                  

                    <div class="form-inline" style="background:#e4eef2">
                      Preço Unit. Final R$ <span>25.000,00</span>
                    </div>

              </div>
               Fim Custo Itens

               Qtd Custo  
              <div class="col-md-4" style="font-size:12px;line-height: 27px;">
                  <div class="form-inline" style="margin-top:10px">
                    
                    Quantidade&nbsp&nbsp<input type="text" class="form-control" style="  height: 24px;width: 50px;font-size: 11px;"> X <input type="text" class="form-control" style="  height: 24px;width: 50px;font-size: 11px;"> = 1 Unid.
                    <div class="form-inline col-md-offset-1">
                        X Diárias
                        <input type="text" class="form-control" style="  height: 24px;width: 50px;font-size: 11px;">
                    </div>
                    
                    <div class="form-group">
                      Custo total: R$ <span>25.000,00</span>
                    </div>

                    <div class="form-group">
                      Valor s/ NF R$ <span>25.000,00</span>&nbsp[0,00]
                    </div>

                    <div class="form-group" style="font-weight:bold">
                      Valor Final: R$ <span>32.425,42</span>
                    </div>
                      
                  </div>  
              </div>
              Fim Qtd Custo

      
          

          </div>
                    

          </div>
- - - - - - - - Fim Desc Item - - - - - - - - - - 
          
- - - - - - - Desc Fornecedores - - - - - - - - - - 
          <div class="row">
              <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">
                  <div class="col-md-2">
                  Fornecedores <br>  
                  <span style="font-size:12px">( Relacionado&nbsp<input type="checkbox"> )</span>
                  </div>

                  <div class="col-md-4">
                    <select  multiple="" class="form-control" name="" id="">
                      <option value="">Eventos de Cantores Junior</option>
                      <option value="">Kalunga</option>
                      <option value="">C&C</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">C&A</option>
                      <option value="">Segurança</option>
                    </select>                   
                  </div>

                  <div class="col-md-2">
                  <a href="#">&nbsp&nbsp&nbsp&nbspInserir >></a><br><br>
                  <a href="#"><< Remover </a>
                  </div>
 
                  <div class="col-md-3">
                      <select  multiple="" class="form-control" name="" id="">
                        <option value=""></option>

                      </select>  
                  </div>
              
  


              </div>
          </div>
- - - - - - - - Fim Desc Fornecedores - - - -- - 

- - - - - - - - - Observação - - - - - - - - - - 
          <div class="row">
              <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">
                <div class="col-md-6">
                    Observações <br>
                    <textarea rows="5" class="form-control" > </textarea>
                </div>
              </div>
            


          </div>
- - - - - - - - - - - Fim Observação - - - - - - -


- - - - - - - - - - - Salvar - - - - - - - - - - -
          <div class="row">
              <div class="form-group col-md-12 tira-padding" style="background:#F2F2F2;padding: 10px 0;">

                    <div class="col-md-8">
                      <input type="submit" class="btn btn-danger"  value="Salvar">
                      <input type="submit" class="btn btn-danger" id="Cadeiras2" value="Salvar e Fechar">
                      <input type="reset" class="btn btn-info" id="Cadeiras3" value="Cancelar">
                    </div>
            
              </div>
            


          </div>
- - - - - - - - - - -  Fim Salvar - - - - - - - - - - -
  

        </div>  
       </td>
      </tr>
    
  
    <tr>
        <td><button id="Cadeiras" type="button" class="btn btn-link" disabled="disabled">Mesas</button></td>
        <td>2.500,00</td>
        <td></td>
        <td></td>
        <td>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium "</td>
        <td>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium "</td>
        <td>
              <a href="" id="" style="color:red;">
                <i class="glyphicon glyphicon-remove"></i>
              </a>
          </td>
    </tr>


    <tr>
        <td><button id="Cadeiras" type="button" class="btn btn-link" disabled="disabled">Copos</button></td>
        <td>1.200,00</td>
        <td></td>
        <td>0,00</td>
        <td>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium"</td>
        <td>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium"</td>
        <td>
              <a href="" id="" style="color:red;">
                <i class="glyphicon glyphicon-remove"></i>
              </a>
          </td>
    </tr>

- - - - - - - - - - - - - - - - - -  Inserir Linha - - - - - - - - - - - - - - - - - 
  <tr>
      <td colspan="7">
      
      <div class="col-md-8 tira-padding " >
          
        <div class="col-md-8 tira-padding" style="margin-left:20px">

          <i class="glyphicon glyphicon-pencil"></i> 
          <button class="btn btn-link" id="tiraLinha">Inserir linha</button>

        </div>
        
      <div class="col-md-12 form-group tira-padding linha" style="display:none">
          <hr>
        <form action=""> 
          <div class="col-md-8">
              <input type="text" class="form-control" placeholder="Título da linha">
          </div>
          
          <div class="col-md-8 ">
              <div class="checkbox col-md-offset-1 tira-padding" style="margin-bottom: -5px;margin-left: 20px;">
                <input type="checkbox" class="form-group" checked="checked"> Incide Fee            
              </div>
          </div>

          <div class="col-md-8">
              <div class="checkbox col-md-offset-1 tira-padding" style="margin-left: 20px;">
                <input type="checkbox" class="form-group"> Opcional da Planilha            
              </div>
          </div>
          
          <div class="col-md-8">
              <div class="form-group">
                <textarea class="form-control" placeholder="Texto da Planilha"></textarea>
              </div>

          </div>

          <div class="col-md-8">
              <div class="form-group">
                <textarea class="form-control" placeholder="Necessidades"></textarea>
              </div>
          </div>  
          

          <div class="col-md-8">
                <input class="btn btn-danger" type="submit" value="Salvar linha">
                <input class="btn btn-info" type="reset" value="Cancelar">
              
          </div>

        </form>
      </div>
      



    </td>
  </tr>

- - - - - - - - - - - - Fim Inserir linha - - - - - - - - - - - - - - - - -


    <tr>
      <td style="background: #E0E0E0;color:#000">Segurança: 6.200,00</td>
      <td>6.200,00</td>
      <td>0,00</td>
      <td colspan="4"></td>
    </tr>
    
</div>
    
    <tr>
          <td colspan="7" style="background: #ccc" >
            <strong>Luminárias</strong>
          </td>
    </tr>

    <tr>
          <td><button id="Cadeiras" type="button" class="btn btn-link" disabled="disabled">Lâmpadas</button></td>
          <td>25.000,00</td>
          <td></td>
          <td></td>
          <td>Iluminação de alta tecnológia</td>
          <td>Suporte para o apoio das luminárias</td>
          <td>
              <a href="" id="" style="color:red;">
                <i class="glyphicon glyphicon-remove"></i>
              </a>
          </td>

    </tr>

    <tr>
          <td><button id="Cadeiras" type="button" class="btn btn-link" disabled="disabled">Telão</button></td>
          <td>25.000,00</td>
          <td></td>
          <td></td>
          <td>Iluminação de alta tecnológia</td>
          <td>Suporte para o apoio das luminárias</td>
          <td>
              <a href="" id="" style="color:red;">
                <i class="glyphicon glyphicon-remove"></i>
              </a>
          </td>

    </tr>

- - - - - - - - - - - - - - - - - -  Inserir Linha - - - - - - - - - - - - - - - - - 
  <tr>
      <td colspan="7">
      
      <div class="col-md-8 tira-padding " >
          
        <div class="col-md-8 tira-padding" style="margin-left:20px">

          <i class="glyphicon glyphicon-pencil"></i> 
          <button class="btn btn-link" id="tiraLinha2">Inserir linha</button>

        </div>
        
      <div class="col-md-12 form-group tira-padding linha2" style="display:none">
          <hr>
        <form action=""> 
          <div class="col-md-8">
              <input type="text" class="form-control" placeholder="Título da linha">
          </div>
          
          <div class="col-md-8 ">
              <div class="checkbox col-md-offset-1 tira-padding" style="margin-bottom: -5px;margin-left: 20px;">
                <input type="checkbox" class="form-group"> Incide Fee            
              </div>
          </div>

          <div class="col-md-8">
              <div class="checkbox col-md-offset-1 tira-padding" style="margin-left: 20px;">
                <input type="checkbox" class="form-group"> Opcional da Planilha            
              </div>
          </div>
          
          <div class="col-md-8">
              <div class="form-group">
                <textarea class="form-control" placeholder="Texto da Planilha"></textarea>
              </div>

          </div>

          <div class="col-md-8">
              <div class="form-group">
                <textarea class="form-control" placeholder="Necessidades"></textarea>
              </div>
          </div>  
          

          <div class="col-md-8">
                <input class="btn btn-danger" type="submit" value="Salvar linha">
                <input class="btn btn-info" type="reset" value="Cancelar">
              
          </div>

        </form>
      </div>
      



    </td>
  </tr>

- - - - - - - - - - - - Fim Inserir linha - - - - - - - - - - - - - - - - -


    
    <tr>
      <td style="background: #E0E0E0;color:#000">Luminária: 6.200,00</td>
      <td>6.200,00</td>
      <td>0,00</td>
      <td colspan="4"></td>
    </tr>



    <tr style="background: #E0E0E0;color:#000">
      <td ><strong>Sub-Total:</strong> 6.200,00</td>
      <td>6.200,00</td>
      <td>0,00</td>
      <td colspan="4"></td>
    </tr> 
      
    
    

    </div>

</table>


  

 </div>
</div>
</div> -->
<!-- Fim Edição Planilha -->


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>



<c:import url="_comum/footer.jsp" />