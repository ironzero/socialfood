package my.travel.action;

import java.util.Collections;
import java.util.List;

import my.action.*;
import javax.servlet.http.*;
import my.travel.db.*;
public class ContentAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("pageNum");
		TravelDBBean dbPro = TravelDBBean.getInstance();
		List fileList = null;
		
		if(request.getParameter("rec") != null)
			dbPro.CommandPlus(idx);
		else if(request.getParameter("non") != null){
			dbPro.Non_CommandPlus(idx);
		}
		
		int fileCount = 0;
		fileCount = dbPro.getFileCount(idx);
				if(fileCount >0){
			fileList = dbPro.getFileList(idx); 
		}else{
			fileList = Collections.EMPTY_LIST;
		}

		TravelDataBean article = dbPro.getArticle(idx);
		request.setAttribute("filecount", fileCount);
		request.setAttribute("idx", new Integer(idx));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("recommand", article.getRecommand());
		request.setAttribute("non_recommand", article.getNon_recommand());
		request.setAttribute("fileList", fileList);

		List commList =null;
		
		int comm_count = 0;
		comm_count = dbPro.getBoardCommentaryCount(idx);
		if(comm_count>0){
			commList = dbPro.getBoardCommentaryList(idx);
		}else{
			commList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("comm_count", comm_count);
		request.setAttribute("commList", commList);
		return "content.jsp";
	}
}
