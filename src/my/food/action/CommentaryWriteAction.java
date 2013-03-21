package my.food.action;

import java.sql.*;

import javax.servlet.http.*;

import my.action.*;
import my.food.db.*;
public class CommentaryWriteAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		FoodDataBean article = new FoodDataBean();
		FoodDBBean dbPro = FoodDBBean.getInstance();
		String idtmp= request.getParameter("idx");
		int idx = Integer.parseInt(idtmp);
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		article.setComm_idx(new Integer(idx));
		System.out.println(request.getParameter("comm_content"));
		article.setComm_wdate(new Timestamp(System.currentTimeMillis()));
		article.setComm_content(request.getParameter("comm_content"));
		article.setComm_nickname(request.getParameter("nickname"));
		dbPro.insertCommentary(article);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("idx", idx);
		return "content.do?idx="+idx+"&pageNum="+pageNum;
	}
}
