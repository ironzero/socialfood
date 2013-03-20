<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" href="../style.css" type="text/css" />
<script type="text/javascript" src="../script.js"></script>
</head>
<body>
<center>
<form action="/socialfood/memberJoinAction.do" method="post" name="joinForm">
<table border="1" align="center" width="50%">
	<tr>
		<td align="center" colspan="3">회원 가입</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" name="id" />
			<input type="button" value="중복확인" />
		</td>
		<td>
			3글자 이상의 영문과 한글
		</td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type="password" name="passwd" /></td>
		<td>4~12자 영문,숫자,한글</td>
	</tr>
	<tr>
		<td>패스워드 확인</td>
		<td><input type="password" name="passwdCheck" /></td>
		<td></td>
	</tr>
	<tr>
		<td>이 름</td>
		<td><input type="text" name="name"/></td>
		<td></td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>
			<input type="text" name="nickname"/>
			<input type="button" value="중복확인" />
		</td>
		<td></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email"/></td>
		<td></td>
	</tr>
	<tr>
		<td>핸드폰</td>
		<td>
			<input type="text" name="phone" />
		</td>
		<td></td>
	</tr>
	<tr>
		<td>생일</td>
		<td><script>fill_select(document.joinForm);</script></td>
		<td></td>
	</tr>
	<tr>
		<td>지역</td>
		<td><input type="text" name="locate"/></td>
		<td>예) 인천 남동구</td>
	</tr>
	<tr>
		<td colspan="3" align="right">
			<input type="submit" value="가입" />&nbsp;&nbsp;&nbsp;
			<input type="reset" value="다시 작성" />
			<input type="button" value="취소" />
		</td>
	</tr>
</table>
</form>
</center>
</body>
</html>