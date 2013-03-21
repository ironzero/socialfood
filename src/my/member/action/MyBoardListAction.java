package my.member.action;

import java.util.*;

import javax.servlet.http.*;

import my.action.*;
import my.board.db.*;
import my.member.db.*;

public class MyBoardListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		//int area = Integer.parseInt(request.getParameter("area"));
		int area=1;
		if(pageNum == null){
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List<BoardDataBean> articleList = null;
		MemberDBBean dbPro = MemberDBBean.getInstance();
		count = dbPro.getArticleCount(id);
		
		if(count > 0){
			articleList = dbPro.getArticleList(startRow, endRow, id);
		}else{
			articleList = Collections.emptyList();
		}
		number = count - (currentPage - 1) * pageSize;
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("area", new Integer(area));
		request.setAttribute("id", id);
		request.setAttribute("articleList", articleList);
		
		return "member/myBoardList.jsp";
	}

}
