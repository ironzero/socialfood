package my.member.action;

import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class MemberInfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		MemberDBBean dbPro = MemberDBBean.getInstance();
		MemberBean member = dbPro.getMemberInfo(id);
		
		request.setAttribute("member", member);
		
		return "member/memberInfo.jsp";
	}

}
