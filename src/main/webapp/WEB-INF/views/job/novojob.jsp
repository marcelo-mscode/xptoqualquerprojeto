<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %> 
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<div class="col-md-12 bodyXY" style="height: 35px;">
					
  <ol class="breadcrumb">
    <li><a href="#">Menu</a></li>
    <li><a href="listajob">Jobs</a></li>
    <li class="active">Novo Job</li>
  </ol>
					
		</div>
    </div>
</div>
<br /><br />





<div class="container"> 

<div class="row">

  <div class="col-md-8 painel ajuste-left " >
     
   <form action="cadjob" method="post">
   		<input type="hidden" name="idJobEditar" value="">  
        <div class="form-group col-md-10 tira-padding">
  		<h4>NOVO JOB</h4>
  		<hr>
  
          <label for="exampleInputEmail1">Título</label>
          <input type="text" class="form-control" name="titulo" placeholder="Título do Job">
    	  
          
        </div>
    
    
        <div class="form-group col-md-10 tira-padding">
          <label for="exampleInputEmail1">Cliente</label>
         
          <select name="idEmp" onchange="procuraContato(this.value);" class="form-control" id="idEmNovo">
         
         	 <option value="selecione">Selecione</option>
	        
	         <c:forEach items="${empresas}" var="empresas">
	            <option value="${empresas[0]}">${empresas[1]}</option>
	         </c:forEach>
          
          </select>
        
        </div>
        
   
         
        <div class="form-group col-md-12 tira-padding" >
        <label for="">Contatos</label><br>
        <img src="<c:url value="resources/images/loader.GIF" />" width="30" height="30" class="loader display-none" alt="loading" />
        <div class="divisor"></div>
        <div class="info-contato"></div>
         
        <div class="form-inline col-md-10 tira-padding" >
          <div class="form-group">
          	
            <label for="data">Prazo para proposta&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
            <input type="text" class="form-control data" name="pd" style="width:110px" placeholder="__/__/____">
          </div>
          <div class="form-group ">
           
         <input type="time" class="form-control" name="ph" placeholder="Hora">
        
          </div>
        </div>
          
        
        <div class="col-md-12" style="height:20px"></div>
        <div class="form-inline col-md-8 tira-padding">
          <div class="form-group">
            <label for="data">Proposta apresentada em&nbsp</label>
            <input type="text" class="form-control data" name="dataProposta" style="width:110px" placeholder="__/__/____">
          </div>
          <div class="form-group ">
           
            <input type="time" class="form-control" name="apresHoraProposta" placeholder="Hora">
          </div>
        </div>  
      

        <div class="col-md-8" style="height:20px"></div>
          
        <div class="form-group col-md-10 tira-padding">
            <label for="">Descrição do Evento:</label>
            <textarea class="form-control" name="descricao" rows="3"></textarea>
            <div id="ajax"></div>
 
        </div>  
         
        <div class="col-md-8 tira-padding">
            <label for="">Verba declarada (R$)</label>
            <input type="text" class="form-control real" name="verba1" style="width:135px">
 			
 
        </div>
 		      
        <div class=" form-inline col-md-10 tira-padding">
            
             <hr> 
              <div class="form-group">
               <button class="btn btn-danger"  type="submit">Salvar Job</button>
              </div>
 		</div>           
</form>			            
        </div>




  </div>
</div>

<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>



<c:import url="../_comum/footer.jsp" />