<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<input type="text" value="${titulo}" style="width: 90%;border: none;" onblur="atualizaNomeLista();" id="nomeLista">
<i class="glyphicon glyphicon-ok" style="top: 1px;color: #2ecc71;display: none" id="ok"></i>