<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%-- <c:forEach items="${categoria}" var="categoria"> --%>
		
		        <%-- <td>${categoria.categoriaOrdem}</td> --%>
		        <td><img src="resources/images/configuracao-loader.gif" class="loader${categoria.idcategoria} display-none" width="20" height="20"  alt="loading">${categoria.categoria}</td>
		        <td>${categoria.imposto} %</td>
		        <td>${categoria.idImposto.impostoTitulo} (${categoria.imposto} %)</td>
		        <td><a class="btn btn-link" onclick="efeitoToogle(editacategoria${categoria.idcategoria})">Editar</a></td>
		
<%-- </c:forEach> --%>