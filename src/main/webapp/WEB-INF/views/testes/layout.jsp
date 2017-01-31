<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 



<style type="text/css" >
div{border:0px solid red;}

</style>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />



<div class="container" style="boder:1px solid red">




<c:forEach items="${job}" var="job">  
  		
    <c:set var="idJob" value="${job.idJob}" />
    <c:set var="jobTitulo" value="${job.titulo}" />
    <c:set var="JobEmp" value="${job.empresa.empresa}" />
    <c:set var="idEmp" value="${job.empresa.idEmpresa}" />
    <c:set var="JobContato" value="${job.contato.contato}" />
    <c:set var="JobContatoId" value="${job.contato.idContato}" />
    <c:set var="JobPropostaData" value="${job.propostaData}" />
    <c:set var="JobApresPropostaData" value="${job.apresPropostaData}" />
    <c:set var="JobDescricao" value="${job.descricao}" />
    <c:set var="JobVerba" value="${job.verba}" />
  		
  	
  	
</c:forEach>


<div class="row painel col-md-12" style="border-top: 1px solid #98A0A3;margin-top:15px;box-shadow: 5px 5px 5px #ccc;padding-left: 0;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;padding-left: 0;">
	
		<div class="col-md-4" style="padding-left: 0;padding: 1px;">
			<h4 style="margin-left: 18px;">JOB 1506040</h4>
	    </div>
	    
	    <div class="col-md-4">
			
	    </div>
	    
   </div> 

 <div class="col-md-8" id="estrategia" style="display: block">
				<div class="col-md-12" style="height: 20px"></div>
		
		   <form action="cadjob" method="post"> 
		   		
		   		<input type="hidden" name="idJobEditar" value="${idJob}">	 
		        <div class="form-group col-md-8 tira-padding">
		          <label for="exampleInputEmail1">Título</label>
		          <input type="text" class="form-control" name="titulo" value="${jobTitulo}" placeholder="Título do Job">
				</div>		    	
		        
		        <div class="form-group col-md-8 tira-padding">
		          <label for="exampleInputEmail1">Cliente</label>
		         
		          <select name="idEmp" class="form-control">
		         	 
		         	<!-- <option >Selecione a empresa</option> -->
		         	<option value="${idEmp}">${JobEmp}</option>
			        
		          
		          </select>
		        
		        </div>
		    
		    <div class="form-group col-md-12 tira-padding" >
		        <label for="">Contatos</label><br>
		        <img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader display-none" alt="loading" />
		        
		            <div class="info-contato">
			            <div class="col-md-8 fundo" style="height:250px;overflow: overlay;">
						     <c:forEach items="${contato}" var="contato">
				       		  <a href="#">${contato.contato}</a><br>
				        	 	 <c:forEach items="${contato.comunicador}" var="comunicador">			
					           	   &nbsp&nbsp<span class="cor">${comunicador.comunicador}</span><br>
					             </c:forEach>
				            </c:forEach> 
			           	 <div class="divisor-fino"></div> 
					   </div>
		          </div>
			</div>
		    
				
		    <div class="form-group col-md-8 tira-padding">
		        <div class="divisor"></div>	
		        
		          <label>Solicitante Job</label>  
		        	  
		          <select name="idContato" class="form-control">
		        
		        	<option value="${JobContatoId}">${JobContato}</option>
		
		        	<c:forEach items="${contato}" var="contato"> 
		        	  	 <option value="${contato.idContato}">${contato.contato}</option>
		            </c:forEach>
		        
		          </select>
		    </div>

		   <div class="form-inline col-md-8 tira-padding">
				<div class="form-group">
					<label for="data">Prazo para
						proposta&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label> <input
						type="text"
						value="<fmt:formatDate value="${JobPropostaData}" pattern="dd/MM/yyyy"/>"
						class="form-control data" name="pd" style="width: 110px"
						placeholder="__/__/____">

				</div>
				<div class="form-group ">

					<input type="time" class="form-control"
						value="<fmt:formatDate value="${JobPropostaData}" pattern="HH:mm"/>"
						name="ph" placeholder="Hora">
				</div>
			</div>
		        
		        
	        <div class="col-md-12" style="height:20px"></div>
	        <div class="form-inline col-md-8 tira-padding">
	          <div class="form-group">
	            <label for="data">Proposta apresentada em&nbsp</label>
	             
	            <input type="text" value="<fmt:formatDate value="${JobApresPropostaData}" pattern="dd/MM/yyyy"/>"
	            class="form-control data" name="dataProposta" style="width:110px" placeholder="__/__/____">
	          </div>
	          <div class="form-group ">
	           
	            <input type="time" value="<fmt:formatDate value="${JobApresPropostaData}" pattern="HH:mm"/>"
	            
	            class="form-control"  name="apresHoraProposta" placeholder="Hora">
	          </div>
	        </div>  

		
		        <div class="col-md-8" style="height:20px"></div>
		          
		        <div class="form-group col-md-8 tira-padding">
		            <label for="">Descrição do Evento:</label>
		            <textarea class="form-control" name="descricao" rows="3">${JobDescricao}</textarea>
		        </div>  
		         
		        <div class="col-md-8 tira-padding">
		            <label for="">Verba declarada (R$)</label>
		            <input type="text" class="form-control real"
					value=<fmt:formatNumber value="${JobVerba}" pattern="#,##0.00"/>
		            name="verba1" style="width:135px">
		 			
		 
		        </div>
		  		      
		        <div class=" form-inline col-md-8 tira-padding">
		            
		             <hr> 
		              <div class="form-group">
		               <button class="btn btn-danger"  type="submit">Salvar Job</button>
		              </div>
		            
		         
		
		            <div class="checkbox col-md-offset-2">
		              <label>
		                <input type="checkbox" name="">&nbsp&nbspNotificar equipe Locco&nbsp
		              </label>
		            </div>
		
	
	          </div>
	          </form>			    
   </div>	
   
   <div class="col-md-4">
   
  		
		   <form action="">
		    <div class="form-group">
		      
		          <label for="">Status</label>
		          <select name="" id="" class="form-control">
		            <option value="">Novo Job</option>
		            <option value="">Adiado</option>
		            <option value="">Aguardando</option>
		            <option value="">Aprovado</option>
		            <option value="">Cancelado</option>
		            <option value="">Concluido</option>
		            <option value="">Des. Proposta</option>
		            <option value="">Execução</option>
		            <option value="">Não Aprovado</option>
		          </select>
		        </div>
		        
		        <div class="form-group">
		         <label for="">Responsável</label> 
		          <select name="" id="" class="form-control">
		            <c:forEach items="${usuariosHabilitados}" var="usuarios">
									<option value="${usuarios.idUsuario} ">${usuarios.nome}</option>
					</c:forEach>
		          </select>
		          
		        </div>  
		        
		    <div class="row">
		          <div class="col-md-5 form-group-sm">
		          <label for="">Prazo</label>
		            <input type="text" class="form-control data" placeholder="__/__/____">
		          </div>
		
		          <div class="col-md-4 form-group-sm">
		          <label for="">&nbsp</label>
		            <input type="text" class="form-control" placeholder="Hora">
		          </div>
		        </div>
		
		        <div style="width:100%;height:12px"></div>
		        
		        <div class="form-group">
		          <label for="">Observação:</label>
		          <textarea class="form-control" rows="2"></textarea>
		        </div>
		        
		         <div class="alert alert-danger" role="alert">
		        Atrasado 207 dias - 22h - 38min
		      </div> 
		
		
		        <div class="form-group">
		          <div class="form-group">
		                <input class="btn btn-danger" type="submit" value="Salvar alteração de Status" disabled>
		              </div>
		        </div>
		    </form>
   
  </div>
   
   </div>
   
   
			    
			    
 </div>
    




<br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br >
<br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br >
<br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br ><br >









		<div class="row">
		   <div class="col-sm-1">col-</div>
		   <div class="col-sm-11">col-11</div>
		</div>
		
		<div class="row">
		   
		   <div class="col-sm-2">col-2</div>
		   <div class="col-sm-10">col-10</div>
		
		</div>
		   
		<div class="row">
		   
		   <div class="col-sm-3">col-3</div>
		   <div class="col-sm-9">col-9</div>
		
		</div>

<div class="divisor"></div>
	
	
</div>


<div class="row painel" style="border-top: 1px solid #98A0A3;margin-top:15px;box-shadow: 5px 5px 5px #ccc;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;">
	
		<div class="col-md-8" style="padding-left: 0;">
			<h5>DATA E LOCAL DO EVENTO</h5>
	    </div>
	
		<div class="col-md-4">
			<button class="btn btn-link" id="chamaEvento" style="margin-top: 3px;">[+] Inserir data e local do evento</button>
	    </div>
	
	</div>




	
	<div class="col-md-12" style="margin-top:15px">
		
		<table class="table table-bordered" style="font-size: 12px;">
			<tr class="active">
				<th>Nome</th>
				<th>Qtd. Pessoas</th>
				<th>Início</th>
				<th>Término</th>
				<th>Endereço</th>
				<th>Obs.</th>
			</tr>
		
			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			
			
		
		</table>	
	</div>
</div>


<div class="divisor"></div>
<div class="divisor"></div>

<div class="row painel" style="border-top: 1px solid #061225;border-bottom: 1px solid #061225;box-shadow: 5px 5px 5px #ccc;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;">
	
		<div class="col-md-8">
			<h4>ANEXOS</h4>
	    </div>
	
		<div class="col-md-4">
			<button class="btn btn-link">[+] Inserir novo Anexo</button>
	    </div>
	
	
	
	
	</div>
	
	<div class="col-md-12" style="margin-top:15px">
		
		<table class="table table-bordered" style="font-size: 12px;">
			<tr class="active">
				<th>Nome</th>
				<th>Qtd. Pessoas</th>
				<th>Início</th>
				<th>Término</th>
				<th>Endereço</th>
				<th>Obs.</th>
			</tr>
		
			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>
		</table>	
	</div>
</div>

<div class="divisor"></div>
<div class="divisor"></div>

<div class="row painel" style="border-top: 1px solid #98A0A3;border-bottom: 1px solid #98A0A3;box-shadow: 5px 5px 5px #ccc;">

	<div class="col-md-12 " style="border-bottom: 1px solid #D7D1C8;">
	
		<div class="col-md-8">
			<h4>ESTRATÉGIA</h4>
	    </div>
	
		<div class="col-md-4">
			<button class="btn btn-link">[+] Inserir nova estratégia</button>
	    </div>
	
	</div>
	
	<div class="col-md-12" style="margin-top:15px">
		
		<table class="table table-bordered" style="font-size: 12px;">
			<tr class="active">
				<th>Nome</th>
				<th>Qtd. Pessoas</th>
				<th>Início</th>
				<th>Término</th>
				<th>Endereço</th>
				<th>Obs.</th>
			</tr>
		
			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>

			<tr>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
				<td>style="font-size: 12px;"></td>
			</tr>
		</table>	
	</div>
</div>

<div class="divisor"></div>
<div class="divisor"></div><div class="divisor"></div>
<div class="divisor"></div><div class="divisor"></div>
<div class="divisor"></div><div class="divisor"></div>
<div class="divisor"></div><div class="divisor"></div>
<div class="divisor"></div><div class="divisor"></div>
<div class="divisor"></div>















	
<c:import url="../_comum/footer.jsp" />