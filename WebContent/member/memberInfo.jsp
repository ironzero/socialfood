<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 정보</title>
<script type="text/javascript" src="js/script.js"></script>
<link rel="stylesheet" href="css/registerForm.css" type="text/css" />
</head>
<body>
<table border="1">
	<tr>
		<td class="title" align="center" colspan="4">내 정보 확인</td>
	</tr>
	<tr>
		<td class="tag">아이디</td>
		<td class="memInfo">${member.id}</td>
		<td class="tag">지역</td>
		<td class="memInfo">${member.locate}</td>
	</tr>
	<tr>
		<td class="tag">이 름</td>
		<td class="memInfo">${member.name }</td>
		<td class="tag">포인트</td>
		<td class="memInfo">${member.point}</td>
	</tr>
	<tr>
		<td class="tag">닉네임</td>
		<td class="memInfo">${member.nickname}</td>
		<td class="tag">가입일자</td>
		<td class="memInfo">${member.reg_date}</td>
	</tr>
	<tr>
		<td class="tag">이메일</td>
		<td class="memInfo">${member.email}</td>
		<td class="tag">최근접속날짜</td>
		<td class="memInfo">${member.last_login_time}</td>
	</tr>
	<tr>
		<td class="tag">핸드폰</td>
		<td class="memInfo">${member.phone}</td>
		<td class="tag">최근접속IP</td>
		<td class="memInfo">${member.last_login_ip}</td>
	</tr>
	<tr>
		<td class="tag">생일</td>
		<td class="memInfo">${member.birth_date}</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td align="right" colspan="4">
			<button type="button" onclick="updateMemberInfo('${member.id}')">정보 수정</button>
			<button type="button" onclick="deleteAccount('${member.id}')">탈 퇴</button>
		</td>
	</tr>
</table>

</body>
</html>