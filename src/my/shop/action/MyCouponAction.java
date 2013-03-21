package my.shop.action;

import java.util.*;

import javax.servlet.http.*;

import my.action.*;
import my.shop.db.*;

public class MyCouponAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number= 0;
		List<ShopDataBean> couponList = null;
		ShopDBBean dbPro = ShopDBBean.getInstance();
		count = dbPro.getCouponCount(id);
		
		if (count > 0){
			couponList = dbPro.getCouponList(startRow, endRow, id);
		} else {
			couponList = Collections.emptyList();
		}
		number = count - (currentPage - 1) * pageSize;
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("id", id);
		request.setAttribute("couponList", couponList);
		
		return "member/myCouponList.jsp";
	}

}
