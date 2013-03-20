<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose> 
<c:when test="${colName == 'undefined'}">
	<meta http-equiv="Refresh" content="0;url=/socialfood/memberList.do"/>
</c:when>
<c:otherwise>
	<meta http-equiv="Refresh" content="0;url=/socialfood/searchMember.do?colName=${colName}&search=${search}"/>
</c:otherwise>
</c:choose>