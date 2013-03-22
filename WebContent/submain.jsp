<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<title>소셜푸드.kr</title>
</head>
<script language="javascript">
self.on_error=null;
currentX = currentY = 0;
whichIt = null;
lastScrollX = 0; lastScrollY = 0;
action = window.setInterval('heartBeat()',1);
function heartBeat() {
  diffY = document.body.scrollTop;
  diffX = 0;
  if (diffY != lastScrollY) {
   percent = .2 * (diffY - lastScrollY);
   if (percent > 0) {
    percent = Math.ceil(percent);
   } else {
    percent = Math.floor(percent);
   }
   document.all.layer_right.style.pixelTop += percent;
   lastScrollY = lastScrollY + percent;
  }
  if (diffX != lastScrollX) {
   percent = .2 * (diffX - lastScrollX);
   if (percent > 0) {
    percent = Math.ceil(percent);
   } else {
    percent = Math.floor(percent);
   }
   document.all.layer_right.style.pixelLeft += percent;
   lastScrollY = lastScrollY + percent;
  }
}
</script>
<body bgcolor="#CDD7B8" text="black" link="blue" vlink="purple" alink="red" leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">
<p align="center" style="line-height:100%; margin-top:0; margin-bottom:0;">
</p>
<div id="layer_right"  style="width:45px; height:182px; position:absolute; left:0px; top:104px; z-index:100;">
    <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/quickmenu.png" width="95" height="193" border="0" usemap="#ImageMap1">
</p>
    <table id="Table_01" width="138" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="2">&nbsp; </td>
        </tr>
    </table>
</div>
<p align="center" style="line-height:100%; margin-top:0; margin-bottom:0;"><a href="/socialfood/"><img src="image/sub_main_up.png" width="1000" height="190" border="0"></a></p>
<p style="line-height:100%; margin-top:0; margin-bottom:0;" align="center"><img src="image/submenu.png" width="990" height="65" border="0" usemap="#ImageMap2"></p>
<p style="line-height:100%; margin-top:0; margin-bottom:0;" align="center"><map name="ImageMap2">

<area shape="rect" coords="251, 24, 353, 57" href="/socialfood/submain.do?group=travel" target="_parent">
<area shape="rect" coords="439, 25, 536, 53" href="/socialfood/submain.do?group=food" target="_parent">
<area shape="rect" coords="638, 27, 742, 55" href="/socialfood/submain.do?group=board" target="_parent">
<area shape="rect" coords="841, 25, 967, 56" href="/socialfood/submain.do?group=shopping" target="_parent">
</map>
<map name="ImageMap1">

<area shape="poly" coords="2, 47, 37, 14, 74, 55, 29, 79" href="http://www.naver.com" target="_parent">
<area shape="poly" coords="31, 86, 76, 64, 73, 141, 27, 115" href="http://www.naver.com" target="_parent">
<area shape="poly" coords="23, 119, 69, 148, 7, 191, 2, 135" href="http://www.naver.com" target="_parent">
</map>
</p>
<table border="1" borderColor ="white" width="1000" align="center" height="700" >
	<tr valign="top">
		<td width="200" >
			<jsp:include page="menu.jsp"></jsp:include>
		</td>
		<td width="800">
			<iframe src="/socialfood/${group}/list.do" width="800" height="700">			
		</td>
	</tr>
</table>
</body>

</html>
