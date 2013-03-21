package my.travel.action;

import java.util.*;
import javax.servlet.http.*;

import my.action.*;
import my.travel.db.*;

public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
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
		int number= 0;
		List articleList = null;
		TravelDBBean dbPro = TravelDBBean.getInstance();
		count = dbPro.getArticleCount(area);
		
		if(count >0){
			articleList = dbPro.getarticleList(startRow, endRow, area);
		}else{
			articleList = Collections.EMPTY_LIST;
		}
		number = count - (currentPage -1)*pageSize;
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("area", new Integer(area));
		request.setAttribute("articleList", articleList);
		return "list.jsp";
	}
}
