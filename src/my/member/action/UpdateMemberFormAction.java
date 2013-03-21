package my.member.action;

import java.util.*;

import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class UpdateMemberFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String check = request.getParameter("check");
		MemberDBBean dbPro = MemberDBBean.getInstance();
		MemberBean member = dbPro.getMemberInfo(id);
		
		StringTokenizer st = new StringTokenizer(member.getEmail(), "@");
		String email1 = st.nextToken();
		String email2 = st.nextToken();
		
		st = new StringTokenizer(member.getPhone(), "-");
		String phone1 = st.nextToken();
		String phone2 = st.nextToken();
		String phone3 = st.nextToken();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(member.getBirth_date());
		
		request.setAttribute("member", member);
		request.setAttribute("email1", email1);
		request.setAttribute("email2", email2);
		request.setAttribute("phone1", phone1);
		request.setAttribute("phone2", phone2);
		request.setAttribute("phone3", phone3);
		request.setAttribute("year", cal.get(Calendar.YEAR));
		request.setAttribute("month", cal.get(Calendar.MONTH) + 1);
		request.setAttribute("date", cal.get(Calendar.DATE));
		request.setAttribute("check", check);
		
		return "member/updateMemberForm.jsp";
	}

}
