package my.travel.action;

import javax.servlet.http.*;

import my.action.*;

public class WriteFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int idx = 0, ref = 1, step = 0, depth = 0;
		int area = Integer.parseInt(request.getParameter("area"));
		try {
			if (request.getParameter("idx") != null) {
				idx = Integer.parseInt(request.getParameter("idx"));
				ref = Integer.parseInt(request.getParameter("ref"));
				step = Integer.parseInt(request.getParameter("step"));
				depth = Integer.parseInt(request.getParameter("depth"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		request.setAttribute("area", new Integer(area));
		request.setAttribute("idx", new Integer(idx));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		return "writeForm.jsp";
		// 각종 값들 받아서 view로 보냄
	}
}
