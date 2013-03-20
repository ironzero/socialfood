package my.member.action;

import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class MemberIdCheck implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		MemberDBBean dbPro = MemberDBBean.getInstance();
		boolean check = dbPro.checkId(id);
		request.setAttribute("check", check);
		request.setAttribute("id", id);
		
		return "member/idCheckForm.jsp";
	}

}
