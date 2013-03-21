<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 정보 수정</title>
<script type="text/javascript" src="js/script.js"></script>
<script>
window.onload = function() {
	var ph = document.updateMemberForm.phone1;
	var ph1 = document.updateMemberForm.ph1;
	for (var i = 0; i < ph.length; i++) {
		if (ph.options[i].value == ph1.value) {
			ph.options[i].selected = true;
		}
	}
	var em = document.updateMemberForm.em;
	var emailCheck = document.updateMemberForm.emailCheck;
	var isMatched = false;
	for (i = 0; i < emailCheck.length; i++) {
		if (emailCheck[i].value == em.value) {
			emailCheck[i].selected = true;
			isMatched = true;
		}
	}
	if(!isMatched){
		emailCheck[1].selected = true;
	}
}
</script>
<link rel="stylesheet" href="css/registerForm.css" type="text/css" />
</head>
<body>
<form action="updateMemberPro.do" name="updateMemberForm" method="post" onsubmit="return updateConfirm()">
<table border="1">
	<tr>
		<td class="title" align="center" colspan="2">내 정보 수정</td>
	</tr>
	<tr>
		<td class="tag">현재 패스워드</td>
		<td class="memInfo">
			<input type="password" name="currPasswd" size="20"/>
			<input type="hidden" name="dbPasswd" value=""/>
			<input type="hidden" name="id" value="${member.id}"/>
			<c:if test="${check == false}">
				<font style="color: #AA1111">패스워드가 틀렸습니다.</font>
			</c:if>
		</td>
	</tr>
	<tr>
		<td class="tag">새로운 패스워드</td>
		<td class="memInfo">
			<input type="password" name="newPasswd1" size="20" />
		</td>
	</tr>
	<tr>
		<td class="tag">패스워드 확인</td>
		<td class="memInfo">
			<input type="password" name="newPasswd2" size="20" />
		</td>
	</tr>
	<tr>
		<td class="tag">이메일<input type="hidden" name="em" value="${email2}"/></td>
		<td class="memInfo"><input type="text" size="10" name="email1" value="${email1}" />@<input type="text" name="email2" size="10" value="${email2}" readOnly/>
			<select name="emailCheck" onchange="setEmailTail(emailCheck.options[this.selectedIndex].value)">
				    <option value="notSelected" >::선택하세요::</option>
				    <option value="etc">직접입력</option>
				    <option value="naver.com">naver.com</option>
				    <option value="daum.net">daum.net</option>
				    <option value="empas.com">empas.com</option>
				    <option value="nate.com">nate.com</option>
				    <option value="hotmail.com">hotmail.com</option>
				    <option value="gmail.com">gmail.com</option>
				    <option value="lycos.co.kr">lycos.co.kr</option>
				    <option value="msn.com">msn.com</option>
				    <option value="hanmail.net">hanmail.net</option>
				    <option value="yahoo.com">yahoo.com</option>
				    <option value="yahoo.co.kr">yahoo.co.kr</option>
				    <option value="paran.com">paran.com</option>    
		   </select></td>
	</tr>
	<tr>
		<td class="tag">핸드폰</td>
		<td class="memInfo">
			<input type="hidden" name="ph1" value="${phone1}"/>
			<select name="phone1">
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="016">016</option>
				<option value="017">017</option>
				<option value="018">018</option>
				<option value="019">019</option>
			</select>
			<input type="text" name="phone2" value="${phone2}" size="4" maxlength="4" onKeyDown="onlyNumberInput()" style="IME-MODE: disabled;"/>-
			<input type="text" name="phone3" value="${phone3}" size="4" maxlength="4" onKeyDown="onlyNumberInput()" style="IME-MODE: disabled;"/>
		</td>
	</tr>
	<tr>
		<td class="tag">생일</td>
		<td class="memInfo"><script>fill_select(document.updateMemberForm, '${year}', '${month}', '${date}');</script></td>
	</tr>
	<tr>
		<td class="tag">지역</td>
		<td class="memInfo"><input type="text" name="locate" value="${member.locate}"/></td>
	</tr>
	<tr>
		<td align="center">
		</td>
		<td align="right">
			<button type="submit">수정</button>
			<button type="button" onclick="javascript:window.close()">취소</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>