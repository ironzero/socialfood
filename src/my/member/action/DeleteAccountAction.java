package my.member.action;

import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class DeleteAccountAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		MemberDBBean dbPro = MemberDBBean.getInstance();
		dbPro.deleteMember(id);
		
		request.setAttribute("id", id);
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "member/goodbye.jsp";
	}

}
