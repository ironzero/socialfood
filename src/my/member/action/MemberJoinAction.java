package my.member.action;

import java.sql.*;
import java.text.*;
import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class MemberJoinAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String year = request.getParameter("years");
		String month = request.getParameter("months");
		String day = request.getParameter("days");
		String date = year + "-" + month + "-" + day;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date birth = new java.sql.Date(format.parse(date).getTime());
		
		String p1 = request.getParameter("phone1");
		String p2 = request.getParameter("phone2");
		String p3 = request.getParameter("phone3");
		String phone = p1 + "-" + p2 + "-" + p3;
		
		String em1 = request.getParameter("email1");
		String em2 = request.getParameter("email2");
		String email = em1 + "@" + em2;
		
		MemberBean member = new MemberBean();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setNickname(request.getParameter("nickname"));
		member.setName(request.getParameter("name"));
		member.setEmail(email);
		member.setLocate(request.getParameter("locate"));
		member.setPhone(phone);
		member.setReg_date(new Timestamp(System.currentTimeMillis()));
		member.setBirth_date(birth);
		
		MemberDBBean dbPro = MemberDBBean.getInstance();
		dbPro.joinMember(member);
		
		request.setAttribute("id", member.getId());
		
		return "member/memberJoinPro.jsp";
	}

}
