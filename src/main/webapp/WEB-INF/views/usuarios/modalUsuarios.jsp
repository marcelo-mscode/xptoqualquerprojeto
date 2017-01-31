	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 


<div class="row">
          <div class="col-md-10 painel ajuste-left">
            <div class="col-md-10">
<form action="atualizaUsuario" method="post">
<%--  <c:forEach items="${usuarios}" var="usuario"> --%>
 
 
 <c:if test="${usuario.userNovo.login == null}">
	 <input type="hidden" value="nulo" name="userNovoTransiente">
	 <input type="hidden" value="${usuario.nivel}" name="nivel">
 </c:if>

<c:if test="${usuario.userNovo.login != null}">
	 <input type="hidden" value="${usuario.nivel}" name="nivel">
	 <input type="hidden" value="${usuario.userNovo.login}" name="userNovoTransiente">
</c:if> 

              <div class="form-inline">
                      
              <c:if test="${usuario.habilitado eq true}">
              	<div class="col-md-12" style="padding: 0;margin-top: -25px;font-family: 'OpenSansLight'">
	             <div class="col-md-8" style="padding: 0;">ATIVO</div>
	             <div class="col-md-4" style="padding: 0;padding-left: 23px;">	
	             	<input type="checkbox" class="checkbox" name="habilitado" checked="checked">&nbsp;&nbsp;DESATIVAR
              	 </div>	
              	</div>		
              </c:if>

              <c:if test="${usuario.habilitado eq false}">
	              <div class="col-md-12" style="padding: 0;margin-top: -25px;font-family: 'OpenSansLight'">
		             <div class="col-md-8" style="padding: 0;">DESATIVADO</div>
		             <div class="col-md-4" style="padding: 0;padding-left: 47px;">	
                    	<input type="checkbox" class="checkbox"  name="habilitado" style="margin-top: -4px;" >&nbsp;&nbsp;ATIVAR
	              	 </div>	
	              	</div>
              
              </c:if>
                      
              </div>
                  
              <div class="form-group">
                    <div class="divisor"></div>
                    <label>Nome</label>
                    <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">
                    <input type="text" class="form-control" name="nome" placeholder="${usuario.nome}" value="${usuario.nome}">
              </div>

              <div class="form-group">
                    <div class="divisor-fino"></div>
					<label>Email</label>
                    <input type="text" class="form-control" name="email" placeholder="${usuario.email}" value="${usuario.email}">
					
              </div>

              <div class="form-group">
                    <div class="divisor-fino"></div>
                    <label>Ramal</label>
                    <input type="text" class="form-control" name="ramal" placeholder="${usuario.ramal}" value="${usuario.ramal}">
              </div>
              
              <div class="form-group">
                  <div class="divisor-fino"></div>
                 
               <label>Departamento</label>
                  <select  id="" name="idDep" class="form-control">
                  		<option value="${usuario.departamento.idDepartamento}">${usuario.departamento.departamento}</option>
                  		<c:forEach items="${departamentos}" var="departamento">
                  			<option value="${departamento.idDepartamento}">${departamento.departamento}</option>
                  		</c:forEach>
                  		
                  		
                  </select>
              </div>

              <div class="form-group col-md-9 tira-padding">
                    <div class="divisor-fino"></div>
					<label>Cargo</label>
                    <input type="text" class="form-control" name= "cargo" placeholder="${usuario.cargo}" value="${usuario.cargo}">
              </div>
<security:authentication property="principal" var="user"/>			  



<security:authorize access="hasRole('ROLE_ADMIN')">   
			  
			  <div class="form-group col-md-9 tira-padding">
                    <div class="divisor-fino"></div>
                   
                   <label>Nível de acesso</label>	
                   <select class="form-control" name="nivelTransiente">
	                   	<c:if test="${usuario.nivel == 2}">
		                   	<option value="2">Administrador do Sistema</option>
		                   	<option value="1">Usuário</option>
		               	</c:if>
	                   	
	                   	<c:if test="${usuario.nivel != 2}">
		                   	<option value="1">Usuário</option>
							<option value="2">Administrador do Sistema</option>
						</c:if>	                   	
	                   	
                   </select>
                   
              </div>
			 	
</security:authorize>
					
<c:if test="${user.usuario.idUsuario == usuario.idUsuario || user.usuario.nivel == 2}">


			<c:if test="${user.usuario.nivel == 1}">
				<input type="hidden" value="1" name="nivelTransiente" />
			</c:if>	
				
					
              <div class="form-group col-md-5 tira-padding">
                    <div class="divisor-fino"></div>
                    
                    <input type="text" class="form-control input-120px" name="usuario" placeholder="${usuario.usuario}" value="${usuario.usuario}">
              <label>Usuario</label>
              </div>

              <div class="form-group col-md-4 tira-padding">
                    <div class="divisor-fino"></div>
                    <input type="password" class="form-control" name="senha" placeholder="Senha">
					<label>Senha</label>	
              </div>
   
   
   
              
   
		
	      <div class="form-group col-md-12 tira-padding">
	      <hr>
	          <button type="submit" class="btn btn-danger">Salvar Usuário</button>
		 </div>

		
</c:if>

<c:if test="${user.usuario.idUsuario != usuario.idUsuario}">

</c:if>

		 <div class="form-group col-md-12 tira-padding">
		 <div class="divisor"></div>
			  <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	     </div>
            
</form>


            </div>
          </div>
      </div>