<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<input name="diferencaTrans" type="hidden"  value="<fmt:formatNumber value="${diferenca}" pattern="#,##0.00"/> " id="diferencaValor1"/>
<span style="font-weight: bold;" id="difer"><fmt:formatNumber value="${diferenca}" pattern="#,##0.00" /></span>
