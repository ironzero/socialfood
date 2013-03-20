package my.member.action;

import java.util.*;

import javax.servlet.http.*;

import my.action.*;
import my.member.db.*;

public class MemberSearchAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		String colName = request.getParameter("colName");
		String search = request.getParameter("search");
		if(pageNum == null){
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number= 0;
		List<MemberBean> memberList = null;
		MemberDBBean dbPro = MemberDBBean.getInstance();
		count = dbPro.getSearchCount(colName, search);
		
		if (count > 0){
			memberList = dbPro.getSearchMemberList(startRow, endRow, colName, search);
		} else {
			memberList = Collections.emptyList();
		}
		number = count - (currentPage - 1) * pageSize;
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("colName", colName);
		request.setAttribute("search", search);
		request.setAttribute("memberList", memberList);
		
		return "member/searchMemberList.jsp";
	}

}
