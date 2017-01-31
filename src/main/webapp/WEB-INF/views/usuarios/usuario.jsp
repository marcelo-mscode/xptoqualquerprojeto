<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>




	<c:import url="../_comum/header.jsp" />


<!-- - - - - - - - - - Container - - - - - - - - - - - -->
<div class="container"> 


<ol class="breadcrumb col-md-offset-1">
    <li><a href="#">Menu</a></li>
    <li><a href="listausuario">Usuários</a></li>
    <li class="active">Novo Usuário</li>
</ol>






      <div class="row col-md-offset-1">
          <div class="col-md-7 painel ajuste-left">
            <div class="col-md-10">



            
<form action="salvaUsuario" method="post">
              <div class="form-inline">
                  <input type="checkbox" name="habilitado" checked="checked" class="checkbox">&nbsp;&nbsp;Habilitado
                  <div class="divisor"></div>
              </div>
              
              
              <div class="form-group col-md-6 tira-padding">
                    Login para acessar o sistema:
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="usuario" placeholder="Usuário" onblur="verificaLogin();" id="loginUsuario" />
              </div>

              <div class="form-group col-md-4 tira-padding" style="padding-left: 10px;">
                    Senha de Acesso:
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="senha" placeholder="Senha" />&nbsp&nbsp
              </div>
              
              <div class="form-group col-md-12 tira-padding display-none" style="margin-top: -15px;margin-bottom: 10px;" id="verifica">
                    <span style="display: none;color: red" id="jaExiste" >*Já existe um usuário com esse login cadastrado <i class="glyphicon glyphicon-remove" style="top: 2px;left: 5px;color: red;"></i></span>
                    <span style="display: none;color:green" id="naoExiste" >*Login disponível <i class="glyphicon glyphicon-ok" style="top: 0px;left: 5px;color: green;"></i></span>
              </div>
                  
                  
                
                  
              <div class="form-group">
                    <div class="divisor"></div>
                    <input type="text" class="form-control" name="nome" placeholder="Nome do Usuário" />
                    
              </div>

              <div class="form-group">
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="email" placeholder="email@email" />
                    
              </div>

              <div class="form-group col-md-3 tira-padding">
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="ramal" placeholder="Ramal" />
                    <div class="divisor-fino"></div>
              </div>
              



              <div class="form-group">
                  <div class="divisor-fino"></div>
                  <select  id="" name="idDep" class="form-control">
                    <option value="dep">Selecione o Departamento</option>
                
                  	<c:forEach items="${departamento}" var="departamentos">
                  		<option value="${departamentos.idDepartamento }">${departamentos.departamento}</option>
                  	</c:forEach>
                
                   <!--  <option value="novo">Criar Novo Departamento</option> -->
                  </select>
              </div>

              <div class="form-group col-md-9 tira-padding">
                    <input type="text" class="form-control" name= "cargo" placeholder="Cargo" />
              </div>
              
              <div class="form-group col-md-9 tira-padding">
                    <div class="divisor-fino"></div>
                   
                   <select class="form-control" name="nivel">
	                   	<option value="1">Usuário</option>
	                   	<option value="2">Administrador do Sistema</option>
                   </select>
                   
                <div class="divisor"></div>   
                <div class="divisor"></div>   
                <div class="divisor"></div>   
              </div>
 	 <div >
              <button type="submit" class="btn btn-danger is-disabled" id="cadastraNovoUser">Salvar Usuário</button>
              <a href="<c:url value="/listausuario" />" class="btn btn-primary col-md-offset-3" >Acessar Lista de Usuários</a>
              
     </div>

</form>

            </div>
          </div>

      </div>
     
      <div class="row col-md-offset-1">
        <div class="col-md-5">
			<h4 style="color: red">${sucesso}</h4>
		</div>
	</div>

</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br>

</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->



<c:import url="../_comum/footer.jsp" />