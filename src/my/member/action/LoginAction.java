package my.member.action;

import javax.servlet.http.*;


import my.member.db.*;
import my.action.CommandAction;

public class LoginAction implements CommandAction{

	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberDBBean dbPro = MemberDBBean.getInstance();
		int isLogin = dbPro.loginCheck(id, password);
		MemberBean member = dbPro.getMemberInfo(id);
		int point = 0;
		String nickname = null;
		if (member != null) {
			point = member.getPoint();
			nickname = member.getNickname();			
		}
		HttpSession session = request.getSession();
		session.setAttribute("isLogin", isLogin);
		session.setAttribute("id", id);
		session.setAttribute("nickname", nickname);
		request.setAttribute("point", point);
		return "loginPro.jsp";
	}
	
}
