package my.member.action;

import javax.servlet.http.*;

import my.action.*;

public class LogoutAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "logoutPro.jsp";
	}

}
