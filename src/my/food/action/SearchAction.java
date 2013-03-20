package my.food.action;

import java.util.*;
import javax.servlet.http.*;

import my.action.CommandAction;
import my.board.*;
import my.board.db.BoardDBBean;

public class SearchAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		String val = request.getParameter("val");
		if(pageNum == null){
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number= 0;
		int area = 1;
		List articleList = null;
		BoardDBBean dbPro = BoardDBBean.getInstance();
		count = dbPro.getSearchArticleCount(search, val, area);
		if(count >0){
			articleList = dbPro.getSearchArticles(startRow, endRow, search, val, area);
		}else{
			articleList = Collections.EMPTY_LIST;
		}
		number = count - (currentPage -1)*pageSize;
		request.setAttribute("search", search);
		request.setAttribute("val", val);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("articleList", articleList);
		return "food/search.jsp";
	}
}
