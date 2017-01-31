<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  

<%-- <input name="custoProdutoTransiente" type="text" class="form-control"
value="<fmt:formatNumber value='${custo}' pattern="#,##0.00" />"
style="height: 24px;width: 110px;" /> --%>

<fmt:formatNumber value='${custo}' pattern="#,##0.00" />