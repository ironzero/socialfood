<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../view/color.jspf"%>
<!DOCTYPE HTML>
<html>
<head>
<title>게시판</title>
<script type="text/javascript">
</script>
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body bgcolor="${bodyback_c }">
	<center>
		<b>글내용 보기</b>
	</center>
	<br />
		<table width="500" border="1" cellspacing="0" cellpadding="0"
			align="center">
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">글번호</td>
				<td align="center" width="125" align="center">${article.idx}</td>
				<td align="center" width="125" bgcolor="${value_c }">조회수</td>
				<td align="center" width="125" align="center">${article.read_count}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">작성자</td>
				<td align="center" width="125" align="center">${article.nickname}</td>
				<td align="center" width="125" bgcolor="${value_c }">작성일</td>
				<td align="center" width="125" align="center">${article.wdate }</td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c }">글제목</td>
				<td align="center" width="125" align="center" colspan="3">${article.title}</td>
			</tr>
			</table>
			<table width="500" border="1" cellspacing="0" cellpadding="0"
			align="center">
			<c:if test="${filecount>0}">
				<tr>
					<td width="60%">파일명</td>
					<td width="40%">파일크기</td>
				</tr>
				<c:forEach var="fileBean" items="${fileList}">
					<tr>
						<td ><a href="${fileBean.realpath}">${fileBean.filename
								}</a></td>
						<td >${fileBean.filesize} bytes</td>
					</tr>
				</c:forEach>
			</c:if>
			</table>
			<table width="500" border="1" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td align="center" width="125" colspan="4"><pre>${article.content }</pre>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4"><input type="button"
					class="btn-bordok" value="추천: ${article.recommand}"
					onclick="document.location.href='/socialfood/travel/content.do?idx=${article.idx }&pageNum=${pageNum}&rec=1'">

					<input type="button" class="btn-bordcancel"
					value="비추천: ${article.non_recommand}"
					onclick="document.location.href='/socialfood/travel/content.do?idx=${article.idx }&pageNum=${pageNum}&non=1'"
					>
					
				</td>
			</tr>
			<tr height="30">
				<td colspan="4" bgcolor="${value_c }" align="right"><input
					type="button" value="글수정"
					onclick="document.location.href='/socialfood/travel/updateForm.do?idx=${article.idx}&pageNum=${pageNum}'" />
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="글삭제"
					onclick="document.location.href='/socialfood/travel/deletePro.do?idx=${article.idx }&pageNum=${pageNum}'" />
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="답글쓰기"
					onclick="document.location.href='/socialfood/travel/writeForm.do?idx=${article.idx }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'" />
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="목록"
					onclick="document.location.href='/socialfood/travel/list.do?pageNum=${pageNum}'" />
				</td>
			</tr>
		</table>
	</form>
	<form action="/socialfood/travel/commentaryWrite.do" method="post">
	<input type="hidden" name="idx" value="${article.idx }"/>
	<input type="hidden" name="pageNum" value="${pageNum }"/>
		<table width="500" align="center" border="0">
			<tr> 
				<td colspan="5" align="center">댓글 갯수 : ${comm_count }</td>
			</tr>
			<c:if test="${comm_count>0 }">
				<c:forEach var="comm" items="${commList}">
				<tr>
					<td bgcolor="white" color="black" colspan="2">${comm.comm_nickname } &nbsp;&nbsp;${comm.comm_wdate }</td>
				</tr>
				<tr>
					<td colspan="2">${comm.comm_content }</td>
				</tr>
			</c:forEach>
			</c:if>
			<tr>
				<td><textarea cols="60" rows="3" width="470" height="50" name="comm_content"></textarea>
				</td>
				<td width="30"><input type="submit" value="작성"/></td>
			</tr>
	</table>
	</form>
</body>
</html>
