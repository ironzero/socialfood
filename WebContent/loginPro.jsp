<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
</title>
</head>
<body>
		<c:if test="${isLogin == 0 }">
		로그인실패!
		<jsp:include page="login.jsp"></jsp:include>
		</c:if>
		<c:if test="${isLogin == 1 }">
		로그인성공!
		</c:if>
		<c:if test="${isLogin == 2 }">
		관리자..
		</c:if>
		<script>
			opener.location.reload(true);
			window.close();
		</script>
</body>
</html>