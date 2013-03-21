package my.food.action;

import java.util.*;

import javax.servlet.http.*;

import my.action.*;
import my.food.db.FoodDBBean;
import my.food.db.FoodDataBean;


public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
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
		List<FoodDataBean> articleList;
		FoodDBBean dbPro = FoodDBBean.getInstance();
		count = dbPro.getArticleCount();
		if(count >0){
			articleList = dbPro.getArticleList(startRow, endRow);
		}else{
			articleList = Collections.emptyList();
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
