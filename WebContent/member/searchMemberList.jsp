<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 리스트</title>
<script type="text/javascript" src="js/script.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script>
$(function() {
    $(".research tr:not(.accordion)").hide();
    $(".research tr.head").show();
    $(".research tr:first-child").show();
    $(".research tr.accordion").click(function(){
    $(this).next("tr").fadeToggle().next("tr").fadeToggle().next("tr").fadeToggle();
     }).on("mouseover", function(e) {
    	 $(this).css("background", "#04B4AE");
     }).on("mouseout", function(e) {
    	 $(this).css("background", "#FFFFFF");
     });
    $(".research tr:not(.accordion)").on("mouseover", function(e) {
    	$(this).prev("tr.accordion").css("background", "#04B4AE");
    	$(this).prev().prev("tr.accordion").css("background", "#04B4AE");
    	$(this).prev().prev().prev("tr.accordion").css("background", "#04B4AE");
    }).on("mouseout", function(e) {
    	$(this).prev("tr.accordion").css("background", "#FFFFFF");
    	$(this).prev().prev("tr.accordion").css("background", "#FFFFFF");
    	$(this).prev().prev().prev("tr.accordion").css("background", "#FFFFFF");
    });
  });
</script>
</head>
<body>
<div align="center"><b>회원 리스트(검색된 회원 수 : ${count})</b></div><br />
	<form action="/socialfood/searchMember.do?colName=${colName}&search=${search}" name="searchForm" onsubmit="return searchCheck()">
		<table align="center" width="400" border="1" cellpadding="0" cellspacing="0">
			<tr class="accordion">
				<td align="center">
					<select name="colName">
						<option value="id" selected>아이디</option>
						<option value="nickname">닉네임</option>
						<option value="name">이름</option>
						<option value="locate">지역</option>
						<option value="phone">연락처</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" size="20" maxlength="20" name="search">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="검색">
				</td>
			</tr>
	</table>
	</form>
	
	<center>
	<c:if test="${count > 0}">
		<c:set var="pageCount"
			value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}" />
		<c:set var="startPage" value="${currentPage / pageSize + 1}" />
		<c:set var="endPage" value="${startPage + 10}" />
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		<c:if test="${startPage > 10}">
			<a href="/socialfood/searchMember.do?colName=${colName}&search=${search}&pageNum=${startPage - 10}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="/socialfood/searchMember.do?colName=${colName}&search=${search}&pageNum=${i}">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCount }">
			<a href="/socialfood/searchMember.do?colName=${colName}&search=${search}&pageNum=${startPage + 10}">[다음]</a>
		</c:if>
	</c:if>
	<br />
	</center>
	
	<c:if test="${count == 0}">
		<table border="1" width="790" cellpadding="0" cellspacing="0" align="center">
			<tr height="30" bgcolor="#889944">
				<td align="center">검색 결과가 없습니다.</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count > 0}">
		<table class="research" border="1" width="790" cellpadding="0" cellspacing="0" align="center">
				<tr class="head" height="30" bgcolor="#889944">
					<td align="center" width="70">번 호</td>
					<td align="center" width="130">아이디</td>
					<td align="center" width="150">이 름</td>
					<td align="center" width="130">닉네임</td>
					<td align="center" width="175">가입일자</td>
					<td align="center" width="175">지역</td>
					<td align="center" width="100">포인트</td>
				</tr>
		<c:forEach var="member" items="${memberList}">
				<tr class="accordion" height="30">
					<td align="center" width="50">
						<c:out value="${number}" /> 
						<c:set var="number" value="${number - 1}" />
					</td>
					<td align="center" width="100">${member.id}</td>
					<td align="center" width="100">${member.name}</td>
					<td align="center" width="100">${member.nickname}</td>
					<td align="center" width="100">${member.reg_date}</td>
					<td align="center" width="100">${member.locate}</td>
					<td align="center" width="100">${member.point}</td>
				</tr>
				<tr class="ac" height="30" bgcolor="#EEDDAA">
					<td align="center" width="100" colspan="2">연락처</td>
					<td align="center" width="200">생년월일</td>
					<td align="center" width="200">E-MAIL</td>
					<td align="center" width="200">최근 접속 시간</td>
					<td align="center" width="200">최근 접속 IP</td>
					<td align="center" width="200">권 한</td>
				</tr>
				<tr class="ac" height="30">
					<td align="center" width="200" colspan="2">${member.phone}</td>
					<td align="center" width="200">${member.birth_date}</td>
					<td align="center" width="200"><a href="mailto:${member.email}">${member.email}</a></td>
					<td align="center" width="200">${member.last_login_time}</td>
					<td align="center" width="200">${member.last_login_ip}</td>
					<td align="center" width="200">0</td>
				</tr>
				<tr class="ac">
					<td align="right" colspan="7">
						<input type="button" value="보유 쿠폰 리스트" onclick="myCoupon('${member.id}')" />
						<input type="button" value="작성 글 보기" />
						<input type="button" value="강제 탈퇴" onclick="deleteMember('${member.id}','${colName}','${search}')"/>
					</td>
				</tr>
			</c:forEach>	
		</table>
	</c:if>
	<table border="0" width="790" cellpadding="0" cellspacing="0" align="center">
		<tr class="head">
				<td align="center" colspan="7"><a href="/socialfood/memberList.do">전체 리스트</a></td>
		</tr>
	</table>
    
</body>
</html>