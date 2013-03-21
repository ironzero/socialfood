<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.hi tr{
	height:30px;
}

</style>
<table width="200" align="center">
	<c:if test="${isLogin == 0}">
		<tr height="150">
			<td colspan="4" align="center"><font size="+2"><b>
						로그인해 <br /> 주세요
				</b></font></td>
		</tr>
	</c:if>
	<c:if test="${isLogin == 1}">
		<tr height="50">
			<td width="100" colspan="2">${nickname }</td>
			<td width="100" colspan="2">${point }점 </td>
		</tr>
		<tr>
	</c:if>
	<c:if test="${isLogin!=1 && isLogin!=0}">
		<tr height="150">
			<td colspan="4" align="center"><font size="+2"><b>관리자</b></font>
			</td>
		</tr>
	</c:if>
	</table>
	<table width="200" align="center" border="1" class="hi">
	<tr>
		<td align="center" colspan="2" bgcolor="#ffd700"><b><i>What's New</i></b></td>
	</tr>
	<tr >
		<td width="140" bgcolor="#2e8b57" align="center" color="white">제목</td>
		<td width="60" bgcolor="#2e8b57" align="center"color="white">닉네임</td>
	</tr>
	<c:forEach var="wn" items="${whatsnew}">
	<tr>
	<td bgcolor="#d3d3d3">${wn.title }</td>
	<td bgcolor="#d3d3d3">${wn.nickname}</td>
	</tr>
	</c:forEach>
	<tr>
		<td align="center" colspan="2" bgcolor="#ffd700"><b><i>Hot Issue</i></b></td>
	</tr>
	<tr >
		<td width="140" bgcolor="#2e8b57" align="center" color="white">제목</td>
		<td width="60" bgcolor="#2e8b57" align="center"color="white">닉네임</td>
	</tr>
	<c:forEach var="hi" items="${hotissue}">
	<tr>
	<td bgcolor="#d3d3d3">${hi.title }</td>
	<td bgcolor="#d3d3d3">${hi.nickname}</td>
	</tr>
	</c:forEach>

	<tr>
		<td align="center" colspan="2" bgcolor="#ffd700"><b><i>Top Recommand</i></b></td>
	</tr>
		<tr >
		<td width="140" bgcolor="#2e8b57" align="center" color="white">제목</td>
		<td width="60" bgcolor="#2e8b57" align="center"color="white">닉네임</td>
	</tr>
	
	<c:forEach var="rl" items="${recommandL}">
	<tr>
	<td bgcolor="#d3d3d3">${rl.title }</td>
	<td bgcolor="#d3d3d3">${rl.nickname}</td>
	</tr>
	</c:forEach>
</table>
