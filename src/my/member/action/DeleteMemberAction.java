package my.member.action;

import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class DeleteMemberAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String colName = request.getParameter("colName");
		String search = request.getParameter("search");
		
		MemberDBBean dbPro = MemberDBBean.getInstance();
		dbPro.deleteMember(id);
		
		request.setAttribute("colName", colName);
		request.setAttribute("search", search);
		
		return "member/deleteMemberPro.jsp";
	}

}
