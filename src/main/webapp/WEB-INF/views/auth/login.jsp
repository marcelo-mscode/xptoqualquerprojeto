<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<link rel="shortcut icon" href="<c:url value="resources/images/iconS.ico" />" />
<link rel="stylesheet" href="resources/css/bootstrap.css" />
<link rel="stylesheet" href="resources/css/syslocc.css" />
<title>Syslocc</title>
</head>
<body style="background: #fff;font-family: 'OpenSansLight'">

<div style="background: #f05736;width: 100%; height: 80px;padding: 0;margin: 0;">

<div class="col-md-4"></div>

	<div class="col-md-4" style="background: #fff;height: 135px;text-align: center;">
		<img src="resources/images/login2.png" width="320" height="140" style="margin: 0 auto;margin-top: 15px">
	</div>
	
	<div class="col-md-4"></div>

</div>
<div style="border: 1px solid #f05736;height: 0px;width: 100%;margin-top: 1px"></div>

<div class="col-md-12" style="margin-top: 20%">
<div class="col-md-4"></div>
<div class="col-md-4" style="background: #fff;padding: 17px 5px 10px 10px;">
<!-- <div class="col-md-4" style="background: #ecf0f1;padding: 17px 5px 10px 10px;"> -->
	<div style="margin-left: 55px">
		<p style="font-size: 1.4em;font-weight: bold;">Preencha os campos abaixo</p>
		<form:form servletRelativeAction="/login">
			
			<table>
				<tr>
					<td>| Login:&nbsp&nbsp&nbsp</td>
					<td><input class="form-control" type='text' name='username' value='' style="margin-top: 10px;"></td>
				</tr>
				<tr>
					<td>| Senha:&nbsp&nbsp&nbsp</td>
					<td><input class="form-control" type='password' name='password' value="" style="margin-top: 10px;"/></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit" value="Entrar" class="btn" style="background: #f05736;color: #fff;
					margin-top: 20px;width: 85px;float: right;" /></td>
				</tr>
			</table>
			
			<c:if test="${param.error != null}">
				<div class="alert alert-error">
					Falha ao fazer Login.<br>
					<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                      <span style="color: red;font-weight: bold"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
					</c:if>
				</div>
			</c:if>
			
		</form:form>
	</div>

</div>

<div class="col-md-4"></div>

</div>
	
	
	

</body>
</html>