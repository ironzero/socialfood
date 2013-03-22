<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>소설푸드</title>
<style type="text/css">
a:link {
 text-decoration: none;
}
a:visited {
 text-decoration: none;
}
a:hover {
 text-decoration: none;
}
a:active {
 text-decoration: none;
}
</style> 



<script language="JavaScript">

function na_open_window(name, url, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable)
{
  toolbar_str = toolbar ? 'yes' : 'no';
  menubar_str = menubar ? 'yes' : 'no';
  statusbar_str = statusbar ? 'yes' : 'no';
  scrollbar_str = scrollbar ? 'yes' : 'no';
  resizable_str = resizable ? 'yes' : 'no';

  cookie_str = document.cookie;
  cookie_str.toString();

  pos_start  = cookie_str.indexOf(name);
  pos_end    = cookie_str.indexOf('=', pos_start);

  cookie_name = cookie_str.substring(pos_start, pos_end);

  pos_start  = cookie_str.indexOf(name);
  pos_start  = cookie_str.indexOf('=', pos_start);
  pos_end    = cookie_str.indexOf(';', pos_start);
  
  if (pos_end <= 0) pos_end = cookie_str.length;
  cookie_val = cookie_str.substring(pos_start + 1, pos_end);
  if (cookie_name == name && cookie_val  == "done")
    return;
  var x = (screen.availWidth-450)/2;
  var y = (screen.availHeight-420)/2;
  window.open(url, name, 'left='+x+',top='+y+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str);
}
</script>

</head>


<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red" leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">

<p style="line-height:100%; margin-top:0; margin-bottom:0;">&nbsp;</p>
<table border="0" align="center" width="1006" height="" style="line-height:100%; margin-top:0; margin-bottom:0;">
   <tr>
   	<td colspan="4">
   		<c:if test="${isLogin == 1 }">
			<p align="right" style="line-height:200%; margin-top:0; margin-bottom:0;"><span style="line-height:200%; margin-top:0; margin-bottom:0; border-width:1; border-style:none;"><a href="javascript:location.href='/socialfood/logout.do'" target="_self" resizable="no"><font size="2" face="굴림" color="#999999">로그아웃</font></a></span><font size="2" face="굴림" color="#999999">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><a href="javascript:na_open_window('join_win', '/socialfood/memberJoinForm.do', 0, 0, 600,500 , 0, 0, 0, 0, 0)" target="_blank"><font size="2" face="굴림" color="#999999">내정보</font></a> &nbsp;&nbsp;</p>
		</c:if>
		<c:if test="${isLogin == 2 }">
			<p align="right" style="line-height:200%; margin-top:0; margin-bottom:0;"><span style="line-height:200%; margin-top:0; margin-bottom:0; border-width:1; border-style:none;"><a href="javascript:location.href='/socialfood/logout.do'" target="_self" resizable="no"><font size="2" face="굴림" color="#999999">로그아웃</font></a></span><font size="2" face="굴림" color="#999999">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><a href="javascript:na_open_window('join_win', 'http://', 0, 0, 300, 200, 0, 0, 0, 0, 0)" target="_blank"><font size="2" face="굴림" color="#999999">관리자페이지</font></a> &nbsp;&nbsp;</p>
		</c:if>
		<c:if test="${isLogin!=1 && isLogin!=2 }">
			<p align="right" style="line-height:200%; margin-top:0; margin-bottom:0;"><span style="line-height:200%; margin-top:0; margin-bottom:0; border-width:1; border-style:none;"><a href="javascript:na_open_window('login_win', 'login.jsp', 0, 0, 350, 230, 0, 0, 0, 0, 0)" target="_self" resizable="no"><font size="2" face="굴림" color="#999999">로그인</font></a></span><font size="2" face="굴림" color="#999999">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><a href="javascript:na_open_window('join_win', '/socialfood/memberJoinForm.do',0 , 0, 500, 450, 0, 0, 0, 0, 0)" target="_blank"><font size="2" face="굴림" color="#999999">회원가입</font></a> &nbsp;&nbsp;</p>
		</c:if>
		</form>
   	</td>
   </tr> 
   <tr>
       	<td width="1000" height="524">
            <p align="center" style="line-height:200%; margin-top:0; margin-bottom:0;"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="980" height="524">
            <param name="movie" value="mainvisual.swf">
            <param name="play" value="true">
            <param name="loop" value="true">
            <param name="quality" value="high">
<embed width="980" height="524" src="mainvisual.swf" play="true" loop="true" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"></embed></object></p>
        </td>
    </tr>
</table>
<table border="0" align="center" width="1000" style="line-height:100%; margin-top:0; margin-bottom:0;">
    <tr>
        <td width="250" height="45">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/seoul_icon.png" width="240" height="40" border="0"></p>
        </td>
        <td width="250" height="45">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/kyoungIn_icon.png" width="240" height="40" border="0"></p>
        </td>
        <td width="250" height="45">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/kangwon_icon.png" width="240" height="40" border="0"></p>
        </td>
        <td width="250" height="45">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/jeongwang_icon.png" width="240" height="40" border="0"></p>
        </td>
    </tr>
    <tr>
        <td width="250" height="22">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/kyoungbu_icon.png" width="240" height="40" border="0"></p>
        </td>
        <td width="250" height="46">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/chungdae_icon.png" width="240" height="40" border="0"></p>
        </td>
        <td width="250" height="46">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/jeju_icon.png" width="240" height="40" border="0"></p>
        </td>
        <td width="250" height="46">

            <p style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/socialcom_icon.png" width="240" height="40" border="0"></p>
        </td>
    </tr>
    <tr>
        <td width="1000" height="90" colspan="4">

            <p align="center" style="line-height:100%; margin-top:0; margin-bottom:0;"><img src="image/footer.png" width="980" height="70" border="0"></p>
        </td>
    </tr>
</table>

</body>

</html>
