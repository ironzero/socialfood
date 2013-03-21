package my.action;

import java.util.*;
import javax.servlet.http.*;
import my.db.DBBean;

public class SubMainAction implements CommandAction{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		String group = request.getParameter("group");
		HttpSession session = request.getSession();
		if(session.getAttribute("isLogin") == null)
			session.setAttribute("isLogin", 0);
		int isLogin = (Integer)session.getAttribute("isLogin");
		
		if(isLogin == 2){
			group = "manage";
		}
		DBBean dbPro = DBBean.getInstance();
		List<?> newList = dbPro.getWhatsNew();
		List<?> issueList = dbPro.getHotIssu();
		List<?> recommandList = dbPro.getRecommandHot();
		request.removeAttribute("group");
		request.setAttribute("group", group);
		request.setAttribute("whatsnew", newList);
		request.setAttribute("hotissue", issueList);
		request.setAttribute("recommandL", recommandList);
		
		return "submain.jsp";
	}

}
