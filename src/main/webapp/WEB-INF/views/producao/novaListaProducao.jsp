<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %> 
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />


<div id="efeito-blur">

<ol class="breadcrumb">
    <li><a href="#">Menu</a></li>
    <li><a href="listaProducao">Produção</a></li>
    <li class="active">Nova  lista de Produção</li>
  </ol>

<!-- Criar nova lista-->
<div class="row">
  
 <div class="col-md-10 painel ajuste-left">
 
<form action="cadastraLista" class="col-md-8" id="cadastraLista" method="post" onsubmit="criarNovaListaBotao.disabled = true; return true;">
      <div class="form-group">
        <label for="">Título da lista</label>
        <input type="text" class="form-control" style="font-weight:bold" placeholder="Título" name="lista" id="tituloLista" onBlur="verificaTituloemNovaLista(criarNovaListaBotao);">
      </div>

      <div class="form-group col-md-8 tira-padding">
          <label for="exampleInputEmail1">Cliente</label>
          <select class="form-control" onchange="editaCelulaComunicador(this.value);" id="selectLista">
           <option value="selecione">Selecione o Cliente</option>
           <c:forEach items="${empresas}" var="empresas">
           		<option>${empresas}</option>
           </c:forEach>
          </select>
        </div>
      
      <div class="form-group col-md-8 tira-padding">
          <label for="exampleInputEmail1">Job</label>
          <select class="form-control" id="selectJobsEmpresa" name="idJobLista" onBlur="verificaTituloemNovaLista(criarNovaListaBotao);">
            
          </select>
      </div>
      
      <div class=" form-inline col-md-12 tira-padding altura" id="criarNovaLista">
      <hr>
          <div class="form-group">
            <input class="btn btn-danger" type="submit" value="Criar Nova Lista de Produção" name="criarNovaListaBotao" id="criarNovaListaBotao">
          </div>
        
           <div class="form-group col-md-offset-0">
            <a class="btn btn-danger col-md-offset-7" onclick="efeitoToogleDuplo(duplicaListaToggle,criarNovaLista)">Copiar Lista de Produção Existente</a>
          </div>
      </div>  
    </form>  
  </div>
</div>
<!-- Fim Criar Listra-->
<div class="col-md-12" style="height:5px"></div>


<div class="row display-none" id="duplicaListaToggle">

	 <div class="col-md-10 painel ajuste-left">
	 	<div class="form-group col-md-8">
          <label for="exampleInputEmail1">Cliente de Origem</label>
          <select class="form-control" onchange="exibeJobPorEmpresa(this.value);" id="selectListaCopiar">
	           <option value="selecione">Selecione o Cliente</option>
	           <c:forEach items="${empresas}" var="empresas">
	           		<option>${empresas}</option>
	           </c:forEach>
          </select>
        </div>
      
      <div class="form-group col-md-8 ">
          <label for="exampleInputEmail1">Job de Origem</label>
          <select class="form-control" onchange="exibeListasPorJob(this.value);" id="copiaJobsEmpresa" name="idJobLista" >
          <option value="selecione">Selecione o Cliente</option>
            
          </select>
      </div>
      
      
      <div class="form-group col-md-8 ">
          <label for="exampleInputEmail1">Lista de Origem</label>
          <select class="form-control" id="copiaListasEmpresa" name="" >
          <option value="selecione">Selecione o Cliente</option>
            
          </select>
      </div>
      
      
      <div class="form-group col-md-8 col-md-offset-0">
      <div class="divisor"></div>
            <a class="btn btn-danger" onclick="copiaLista()" id="CriarListaOrigem">Criar Lista à partir de origem</a>
            <a class="btn btn-danger col-md-offset-2" onclick="efeitoToogleDuplo(duplicaListaToggle,criarNovaLista)">Cancelar Copiar Lista</a>
      </div>

      
      
      
      
      
      
	 </div>

      


	</div>
</div>



<div class="col-md-12 alpha60 div-confirmacao" id="listaCopiada" style="position: fixed;"></div>



<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<c:import url="../_comum/footer.jsp" />













