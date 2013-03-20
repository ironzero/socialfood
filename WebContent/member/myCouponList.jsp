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
</head>
<body>
<div align="center"><b>내쿠폰 리스트(아이디: [${id}]  보유 쿠폰 : ${count}장)</b></div><br />
	
	<c:if test="${count == 0}">
		<table border="1" width="790" cellpadding="0" cellspacing="0" align="center">
			<tr height="30" bgcolor="#889944">
				<td align="center">보유 쿠폰이 없습니다.</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count > 0}">
		<table border="1" width="790" cellpadding="0" cellspacing="0" align="center">
				<tr height="30" bgcolor="#889944">
					<td align="center" width="50">번 호</td>
					<td align="center" width="200">쿠폰번호</td>
					<td align="center" width="200">쿠폰이름</td>
					<td align="center" width="100">사용여부</td>
					<td align="center" width="200">사용기한</td>
				</tr>
		<c:forEach var="coupon" items="${couponList}">
			<tr>
				<td align="center" width="50">
					<c:out value="${number}" /> 
					<c:set var="number" value="${number - 1}" />
				</td>
				<td align="center" width="200">${coupon.cou_num}</td>
				<td align="center" width="200">${coupon.cou_name}</td>
				<td align="center" width="100">
					<c:if test="${coupon.cou_usage == 0}"><font style="color: blue;">사용 가능</font></c:if>
					<c:if test="${coupon.cou_usage == 1}"><font style="color: red;">사용된 쿠폰</font></c:if>
				</td>
				<td align="center" width="200">${coupon.cou_date} 까지</td>
			</tr>
		</c:forEach>	
		</table>
	</c:if>
	
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
			<a href="/socialfood/myCouponList.do?pageNum=${startPage - 10}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="/socialfood/myCouponList.do?pageNum=${i}">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCount }">
			<a href="/socialfood/myCouponList.do?pageNum=${startPage + 10}">[다음]</a>
		</c:if>
	</c:if>
	<br />
	</center>
</body>
</html>