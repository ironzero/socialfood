package my.shop.action;

import java.util.*;
import javax.servlet.http.*;

import my.action.*;
import my.shop.db.*;
public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		List<ShopDataBean> articleList = null;
		ShopDBBean dbPro = ShopDBBean.getInstance();
		articleList = dbPro.getarticleList();
		request.setAttribute("articleList", articleList);
		return "list.jsp";
	}
}
