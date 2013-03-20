package my.food.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.action.CommandAction;
import my.board.db.BoardDBBean;

public class DeleteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		int idx =Integer.parseInt(request.getParameter("idx"));
		BoardDBBean dbPro = BoardDBBean.getInstance();
		dbPro.deleteArticle(idx);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		
		return "food/deletePro.jsp";
		
	}
}
