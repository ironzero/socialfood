<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<script type="text/javascript" src="../js/script.js"></script>
<link rel="stylesheet" href="../css/registerForm.css" type="text/css" />
</head>
<body>
<form action="/socialfood/memberJoinAction.do" method="post" name="joinForm" onsubmit="return joinCheck()">
<table border="1">
	<tr>
		<td class="title" align="center" colspan="2">회 원&nbsp;&nbsp;&nbsp;가 입</td>
	</tr>
	<tr>
		<td class="tag">아이디</td>
		<td>
			<input type="text" name="id" maxlength="12" onChange="resetIdCheck()" onKeyDown="onlyAlphaNumberInput()" style="ime-mode:disabled" autofocus placeholder="3자이상 영문,숫자"/>
			<button type="button" onclick="check_ID(document.joinForm.id.value)">중복확인</button>
			<input type="hidden" name="idCheck" value="0"/>
		</td>
	</tr>
	<tr>
		<td class="tag">패스워드</td>
		<td><input type="password" name="passwd" maxlength="12" placeholder="4자 이상 영문,숫자"/></td>
	</tr>
	<tr>
		<td class="tag">패스워드 확인</td>
		<td><input type="password" name="passwdCheck" /></td>
	</tr>
	<tr>
		<td class="tag">이 름</td>
		<td><input type="text" name="name"/></td>
	</tr>
	<tr>
		<td class="tag">닉네임</td>
		<td>
			<input type="text" onChange="resetNickCheck()" name="nickname"/>
			<button type="button" onclick="check_nick(document.joinForm.nickname.value)">중복확인</button>
			<input type="hidden" name="nickCheck" value="0" />
		</td>
	</tr>
	<tr>
		<td class="tag">이메일</td>
		<td>
			<input type="text" size="10" name="email1" value="" />@<input type="text" name="email2" size="10" value="" readOnly/>
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
		   </select>
		</td>
	</tr>
	<tr>
		<td class="tag">핸드폰</td>
		<td>
			<select name="phone1">
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="016">016</option>
				<option value="017">017</option>
				<option value="018">018</option>
				<option value="019">019</option>
			</select>
			<input type="text" name="phone2" size="4" maxlength="4" onKeyDown="onlyNumberInput()" style="IME-MODE: disabled;"/>-
			<input type="text" name="phone3" size="4" maxlength="4" onKeyDown="onlyNumberInput()" style="IME-MODE: disabled;"/>
		</td>
	</tr>
	<tr>
		<td class="tag">생일</td>
		<td><script>fill_select(document.joinForm);</script></td>
	</tr>
	<tr>
		<td class="tag">지역</td>
		<td><input type="text" name="locate" placeholder="ex) 서울시 강남구"/></td>
	</tr>
	<tr>
		<td align="center"><button type="reset">다시 작성</button></td>
		<td align="right">
			<button type="submit">가 입</button>&nbsp;&nbsp;
			<button type="reset">취 소</button>
		</td>
	</tr>
</table>
</form>

</body>
</html>