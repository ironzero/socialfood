<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/view/color.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기 뷰</title>
<link rel="stylesheet" href="../style.css" type="text/css" />
<link rel="stylesheet" href="style.css" type="text/css" />
<script type="text/javascript" src="script.js"></script>

</head>
<body bgcolor="${bodyback_c }">

	<center>
		<h2>글 쓰 기</h2>
	</center>
	<form action="/socialfood/writePro.do" method="post" name="writeform"
		enctype="multipart/form-data" onsubmit="return writeSave()">

		<input type="hidden" name="idx" value="${ idx }" /> <input
			type="hidden" name="ref" value="${ ref }" /> <input type="hidden"
			name="step" value="${step}" /> <input type="hidden" name="depth"
			value="${ depth}" /> <input type="hidden" name="area"
			value="${ area }" /> <input type="hidden" name="id" value="ㅇㅇㅇ">
		<input type="hidden" name="nickname" value="ㅋㅋㅋ">

		<table width="800" align="center" border="1" cellspacing="0"
			cellpadding="0">
			<tr>
				<td width="30%" bgcolor="${value_c }" align="center">게시판 구분(지역)</td>
				<td width="70%"><select name="area" id="area">
						<option value="1">서울</option>
						<option value="2">경기</option>
						<option value="4">강원</option>
						<option value="5">경상</option>
						<option value="6">전라</option>
						<option value="7">제주</option>
						<option value="3">충청</option>
				</select></td>
			</tr>

			<tr>
				<td bgcolor="${value_c }" align="center">분류</td>
				<td><select name="category">
						<option value="맛집">맛집</option>
						<option value="여행">여행</option>
						<option value="자유게시판">자유게시판</option>
				</select></td>
			</tr>
			<tr>
				<td bgcolor="${value_c }" align="center">제목</td>
				<td><select name="category" id="category">
						<option value="후기">[후기]</option>
						<option value="질문">[질문]</option>
						<option value="답변">[답변]</option>
						<option value="영상">[영상]</option>
						<option value="유머">[유머]</option>
						<option value="잡담">[잡담]</option>

				</select> <c:if test="${idx==0 }">
						<input type="text" size="40" maxlength="40" name="title"
							id="title" />
					</c:if> <c:if test="${idx!=0}">
						<input type="text" size="40" maxlength="40" name="title"
							id="title" value="[답변] :" />
					</c:if></td>

			</tr>

			<tr>
				<td bgcolor="${value_c }" align="center">내 용</td>
				<td><textarea rows="13" cols="60" name="content"></textarea></td>
			</tr>
			<tr>
				<td bgcolor="${value_c }" align="center">파일 첨부</td>
				<td><input type="file" name="filename"></td>
		</table>
		<p>
		<center>
			<input type="submit" value="작성완료" onclick="return sub()" /> <input
				type="reset" value="다시작성" /> <input type="button" value="목록 보기"
				onclick="window.location='/socialfood/list.do'" />
		</center>
	</form>
</body>
</html>