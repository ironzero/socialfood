<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view/color.jspf"%>
<html>
<head>
<title>게시판</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="js/script.js"></script>
</head>
<body bgcolor="${bodyback_c }">
	<center>
	<table width="800">
		<tr>
			<td align="right">
				게시물 (전체 글:${count}건)</td>
		</tr>
	</table>
	<c:if test="${count==0 }">
		<table width="800" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count>0 }">
		<table border="0" width="800" cellpadding="0" cellsapcing="0"
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
						<c:out value="${article.idx}" /> 
					<td width="350">
						<c:if test="${article.depth>0 }">
						<img src="images/level.gif" width="${5*article.depth}" height="16">
						<img src="images/re.gif">
						</c:if> 
						<c:if test="${article.depth ==0 }">
						<img src="images/level.gif" width="${5*article.depth}"height="16">
						</c:if>
						[${article.category}] <a href="/socialfood/travel/content.do?idx=${article.idx }&pageNum=${currentPage}">
							 ${article.title}</a>
						<c:if test="${article.read_count>=20 }">
							<img src="images/hot.gif" border="0" height="16" />
						</c:if>
					</td>
					<td align="center" width="100">
						<a href="mailto:${article.id }">${article.nickname }</a>
					</td>
					<td align="center" width="150">${article.wdate }</td>
					<td align="center">${article.read_count }</td>
					<td align="center">${article.recommand_count}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
		<table width="800" align="center" >
	<tr>
		<td align="center">
			<c:if test="${count>0 }">
				<c:set var="pageCount"	value="${count/pageSize + (count%pageSize==0?0:1) }" />
				<c:set var="startPage" value="${currentPage / pageSize + 1 }" />
				<c:set var="endPage" value="${startPage + 10 }" />
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount }" />
				</c:if>
				<c:if test="${startPage > 10 }">
					<a href="/socialfood/travel/list.do?pageNum=${startPage-10 }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="/socialfood/travel/list.do?pageNum=${i }">${i }</a>
				</c:forEach>
				<c:if test="${endPage < pageCount }">
					<a href="/socialfood/travel/list.do?pageNum=${startPage + 10 }">[다음]</a>
				</c:if>
			</c:if>
		</td>
	</tr>
	</table>
	<br />
	<form action="/socialfood/travel/search.do?pageNum=1&search=${search}&val=${val}" name="findForm">
			<table width="800" border="0" cellpadding="0" cellspacing="0">
			<tr>
			<td align="left">
			<input type="hidden" name="area" value="${area}" />
			<input type="button" onclick="document.location.href='/socialfood/travel/writeForm.do'" value="글쓰기">	
			</td>
				<td align="right">
					<select name="search">
						<option value="NICKNAME" selected>작성자</option>
						<option value="TITLE">제목</option>
						<option value="CONTENT">내용</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" size="20" maxlength="20" name="val">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="찾기">
				</td>
			</tr>
		</form>
	</center>
	</table>
</body>
</html>