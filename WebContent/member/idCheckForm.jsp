<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 중복 확인</title>
<link rel="stylesheet" href="../css/registerForm.css" type="text/css" />
<script type="text/javascript" src="../js/script.js">
</script>
<script>
window.onload = function() {
	document.subCheck.id.value = opener.joinForm.id.value;
}
</script>
</head>
<body>
<form action="/socialfood/memberIdCheck.do" name="subCheck" onsubmit="return sub_idCheck()">
<table class="check">
	<tr>
		<td class="title" align="center" colspan="2">아이디 확인</td>
	</tr>
	<tr align="center">
		<td><input type="text" name="id"/></td>
		<td><button type="submit">확인</button></td>
	</tr>
	<c:if test="${id == null || id == \"\"}">
		<tr>
			<td class="info">아이디를 입력하세요.</td>
		</tr>
	</c:if>
	<c:if test="${check == false && id != \"\"}">
		<tr>
			<td class="info">${id} 사용가능</td>
			<td class="info"><button type="button" onclick="use_ID('${id}')">사용하기</button></td>
		</tr>
	</c:if>
	<c:if test="${check == true}">
		<tr>
			<td colspan="2">${id} 은(는) 사용 중입니다.</td>
		</tr>
	</c:if>
</table>
</form>
</body>
</html>