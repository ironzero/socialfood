package my.travel.action;

import javax.servlet.http.*;

import my.action.CommandAction;
import my.travel.db.*;

public class DeleteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		int idx =Integer.parseInt(request.getParameter("idx"));
		TravelDBBean dbPro = TravelDBBean.getInstance();
		dbPro.deleteArticle(idx);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		
		return "deletePro.jsp";
		
	}
}
