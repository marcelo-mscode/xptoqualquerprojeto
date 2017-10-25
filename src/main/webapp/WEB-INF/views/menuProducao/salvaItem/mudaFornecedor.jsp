<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                style="margin-top: -12px;">
                  <span aria-hidden="true" style="font-size: 50px;font-family: 'OpenSansLight';font-weight: normal;">
                    &times;
                  </span>
                </button>
                <h4 class="modal-title" id="myModalLabel" style="font-family: 'OpenSansLight'">
                  Trocar de Fornecedor
                </h4>
              </div>
              <div class="modal-body">
                <div class="row">
	                     <div class="col-md-12 form-inline"
	                     style="height: 117px; border: 1px solid #ddd; padding: 20px 20px 20px 10px; margin-bottom: 10px; border-radius: 5px;">
	                       <p>
	                         Trocar de Fornecedor
	                       </p>
	                       <select class="form-control input-180px" id="idEmpresa${produtoGrupo.idProdutoGrupo}">
	                                        
	                          <c:forEach items="${fornecedoresLista}" var="fornecedoresLista">
	                            <option value="${fornecedoresLista[0]}">
	                              ${fornecedoresLista[1]}
	                            </option>
	                          </c:forEach>
	                          
	                       </select>
	                       &nbsp&nbsp&nbsp
	                       <a class="btn btn-danger" onclick="trocarFornecedor(${produtoGrupo.idProdutoGrupo});">Trocar Agora</a>
	                       <br>
	                     </div>
                    </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                  Close
                </button>
              </div>
            </div>
</div>