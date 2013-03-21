package my.board.action;

import javax.servlet.http.*;

import my.action.*;
import my.board.db.*;

public class WriteFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int idx = 0, ref = 1, step = 0, depth = 0;
		int area = 0;
		if (request.getParameter("area") != null) {
			String arat = request.getParameter("area");
			area = Integer.parseInt(arat);
		} else {
			area = (int) session.getAttribute("area");// null ?
		}
		session.setAttribute("area", area);
		
		try {
			if (request.getParameter("idx") != null) {
				idx = Integer.parseInt(request.getParameter("idx"));
				BoardDBBean dbPro = BoardDBBean.getInstance();
				BoardDataBean article = dbPro.getArticle(idx);
				String title = "re:"+ article.getTitle();
				String content = article.getContent();
				request.setAttribute("reTitle", title  );
				request.setAttribute("reContent", content );
				ref = Integer.parseInt(request.getParameter("ref"));
				step = Integer.parseInt(request.getParameter("step"));
				depth = Integer.parseInt(request.getParameter("depth"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		request.setAttribute("TITLE", "글쓰기"  );
		request.setAttribute("idx", new Integer(idx));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		return "writeForm.jsp";
		// 각종 값들 받아서 view로 보냄
	}
}
