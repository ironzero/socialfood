<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file = "../view/color.jspf" %>    
<!DOCTYPE HTML>
<html>
<head>
	<title>게시판</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body bgcolor="${bodyback_c }">
	<center><b>글내용 보기</b></center>
	<br />
	<form action="/socialfood/travel/updatePro.do" method="post" name="updateform" enctype="multipart/form-data" >
		<input type="hidden" name="idx" value="${idx }">
		<input type="hidden" name="pageNum" value="${pageNum }">
		
		<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
		<tr height="30">
			<td align="center" width="125" bgcolor="${value_c }">글번호</td>
			<td align="center" width="125" align="center">${idx}</td>
			<td align="center" width="125" bgcolor="${value_c }">조회수</td>
			<td align="center" width="125" align="center">${read_count }</td>
		</tr>
		<tr height="30">
			<td align="center" width="125" bgcolor="${value_c }">작성자</td>
			<td align="center" width="125" align="center">${nickname} </td>
			<td align="center" width="125" bgcolor="${value_c }">작성일</td>
			<td align="center" width="125" align="center">${wdate}</td>
		</tr>
		<tr height="30">
			<td align="center" width="125" bgcolor="${value_c }">글제목</td>
			<td align="left" colspan="3">
			<input type="text" name="title" value="${title}" size="70"></td>
		</tr>
		<tr >
			<td align="center" width="125" colspan="4" cellpadding ="5"><br/><textarea name="content" rows="15" cols="70">${content}</textarea><br/><br/>
			</td>
		</tr>
		</table>
		<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td bgcolor="${value_c }" align="center">기존 파일명</td>
				<td>${filename}</td>
			</tr>
				<tr>
				<td bgcolor="${value_c }" align="center">파일 첨부</td>
				<td>
				<input type="file" name="filename" size="0"></td>
			</tr>
		</table>
		<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
		<tr height="30">
			<td colspan="4" bgcolor="${value_c }" align="center">
			<input type="submit" value="수정완료">
			<input type="button" value="목록" 
			onclick="document.location.href='/socialfood/travel/list.do?pageNum=${pageNum}'"/>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>
