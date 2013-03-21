package my.food.action;

import javax.servlet.http.*;

import my.action.CommandAction;
import my.food.db.*;

public class DeleteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		int idx =Integer.parseInt(request.getParameter("idx"));
		FoodDBBean dbPro = FoodDBBean.getInstance();
		dbPro.deleteArticle(idx);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		
		return "deletePro.jsp";
		
	}
}
