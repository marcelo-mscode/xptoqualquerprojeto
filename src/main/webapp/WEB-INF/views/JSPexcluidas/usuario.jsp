<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="_comum/header.jsp" />


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
              </div>
                  
              <div class="form-group">
                    <div class="divisor"></div>
                    <input type="text" class="form-control" name="nome" placeholder="Nome do Usuário">
              </div>

              <div class="form-group">
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="email" placeholder="email@email">
              </div>

              <div class="form-group col-md-3 tira-padding">
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="ramal" placeholder="Ramal">
              </div>
              
              <div class="form-group">
                  <div class="divisor-fino"></div>
                  <select  id="" name="idDep" class="form-control">
                    <option value="dep">Selecione o Departamento</option>
                
                  	<c:forEach items="${departamento}" var="departamentos">
                  		<option value="${departamentos.idDepartamento }">${departamentos.departamento}</option>
                  	</c:forEach>
                
                    <option value="novo">Criar Novo Departamento</option>
                  </select>
              </div>

              <div class="form-group col-md-9 tira-padding">
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name= "cargo" placeholder="Cargo">
              </div>

              <div class="form-group col-md-5 tira-padding">
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="usuario" placeholder="Usuário">
              </div>

              <div class="form-group col-md-4 tira-padding">
                    <div class="divisor-fino"></div>
                    <input type="text" class="form-control" name="senha" placeholder="Senha">
              </div>
              

              <button type="submit" class="btn btn-danger">Salvar Usuário</button>



</form>


            </div>
          </div>
      </div>


</div>






<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br>

</body>
<!-- - - - - - - - - - Fim Container - - - - - - - - - -->



<c:import url="../_comum/footer.jsp" />