package my.board.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.action.CommandAction;
import my.board.db.BoardDBBean;
import my.board.db.BoardDataBean;
import my.board.db.BoardFileBean;

public class UpdateFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article = dbPro.getArticle(idx);
		
		int fileCount = 0;
		List fileList = null;
		fileCount = dbPro.getFileCount(idx);
		if(fileCount >0){
			fileList = dbPro.getFileList(idx); 
		}else{
			fileList = Collections.EMPTY_LIST;
		}
		System.out.println(idx);
		BoardFileBean fileBean = dbPro.getFileName(idx);
		request.setAttribute("filename", fileBean.getFilename());
		request.setAttribute("filecount", fileCount);
		request.setAttribute("fileList", fileList);
		request.setAttribute("idx", new Integer(idx));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("read_count", article.getRead_count());
		request.setAttribute("nickname", article.getNickname());
		request.setAttribute("wdate", article.getWdate());
		request.setAttribute("title", article.getTitle());
		request.setAttribute("content", article.getContent());
		return "board/updateForm.jsp";
	}
}
