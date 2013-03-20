package my.member.action;

import javax.servlet.http.*;

import my.action.*;

public class MemberJoinFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		return "member/memberJoinForm.jsp";
	}

}
