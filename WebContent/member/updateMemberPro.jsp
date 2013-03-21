<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose> 
<c:when test="${requestScope.check == false}">
	<meta http-equiv="Refresh" content="0;url=/socialfood/updateMemberForm.do?id=${id}&check=${check}"/>
</c:when>
<c:otherwise>
	<script type="text/javascript">
		opener.location.reload(true);
		window.close();
	</script>
</c:otherwise>
</c:choose>