package my.member.action;

import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class MemberNickCheck implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String nick = request.getParameter("nickname");
		MemberDBBean dbPro = MemberDBBean.getInstance();
		boolean check = dbPro.checkNick(nick);
		request.setAttribute("check", check);
		request.setAttribute("nick", nick);
		
		return "member/nickCheckForm.jsp";
	}

}
