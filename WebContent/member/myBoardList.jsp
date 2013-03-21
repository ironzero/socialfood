<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/view/color.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
<style type="text/css">
.btn-bordok {
	background-color: #6B66FF;
	cursor: pointer;
	height: 23px;
	width: 62px;
	border: none;
}

.btn-bordcancel {
	background-color: #FF3636;
	cursor: pointer;
	height: 23px;
	width: 62px;
	border: none;
}
table {
    border-collapse: collapse;
    width: 40em;
    border: 2px solid #ddd;
}

th, td{
    padding: 0.1em 1em;
    font: 9pt sans-serif;
}

caption{
    font: bold 1.2em sans-serif;
    margin: 1em 0;
}

col{
    border-right: 1px solid #ccc;
}
col#album{
    border: none;
}

thead{
    background-color: #ddd;
    border-top: 1px solid #a5a5a5;
    border-bottom: 1px solid #a5a5a5;
}

th{
    font-weight: normal;
    text-align: center;
}

#artistNumHead{
    text-indent: -1000em;
}

.odd{
    background-color:#edf5ff;
}

tr:hover{
    background-color: #3d80df;
    color: #fff;
}

thead tr:hover, tfoot tr:hover{
    background-color: transparent;
    color: inherit;
}

thead td{
    border-right: 1px solid #EEE;
}

A:link {
	COLOR: #374273; TEXT-DECORATION: none
}
A:visited {
	COLOR: #374273; TEXT-DECORATION: none
}


INPUT,TEXTAREA {border:expression('1px solid #7f9db9');} 


@import url('demo.css');
@import url('font-awesome.css');
</style>
</head>
<body bgcolor="${bodyback_c}">
	<table width="800" align="center">
		<tr>
			<td align="right">
				게시물 (전체 글:${count}건)</td>
		</tr>
	</table>
	<c:if test="${count == 0}">
		<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count > 0}">
		<table border="0" width="800" cellpadding="0" cellspacing="0"
			align="center">
			<tr height="30" bgcolor="${value_c }">
				<td align="center" width="50">번 호</td>
				<td align="center" width="350">제 목</td>
				<td align="center" width="110">작성자</td>
				<td align="center" width="150">작성일</td>
				<td align="center" width="70">조회수</td>
				<td align="center" width="70">추천지수</td>
				
			</tr>
			<c:forEach var="article" items="${articleList}">
				<tr height="30">
					<td align="center" width="50">
						<c:out value="${number}" /> 
						<c:set var="number" value="${number - 1}" />
					<td width="350">
						<c:if test="${article.depth > 0}">
						<img src="images/level.gif" width="${5*article.depth}" height="16">
						<img src="images/re.gif">
						</c:if> 
						<c:if test="${article.depth == 0}">
						<img src="images/level.gif" width="${5*article.depth}"height="16">
						</c:if>
						[${article.category}] 
							 ${article.title}
						<c:if test="${article.read_count>=20}">
							<img src="images/hot.gif" border="0" height="16" />
						</c:if>
					</td>
					<td align="center" width="100">
						<a href="mailto:${article.id}">${article.nickname}</a>
					</td>
					<td align="center" width="150">${article.wdate}</td>
					<td align="center">${article.read_count}</td>
					<td align="center">${article.recommand_count}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<center>
	<c:if test="${count > 0}">
		<c:set var="pageCount"
			value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}" />
		<c:set var="startPage" value="${currentPage / pageSize + 1 }" />
		<c:set var="endPage" value="${startPage + 10}" />
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		<c:if test="${startPage > 10}">
			<a href="/socialfood/myBoardList.do?id=${id}&pageNum=${startPage - 10}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="/socialfood/myBoardList.do?id=${id}&pageNum=${i}">${i}</a>
		</c:forEach>
		<c:if test="${endPage < pageCount }">
			<a href="/socialfood/myBoardList.do?id=${id}&pageNum=${startPage + 10}">[다음]</a>
		</c:if>
	</c:if>
	</center>
	<br />
</body>
</html>