package my.board.action;

import java.io.File;
import java.sql.*;

import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.*;

import my.action.*;
import my.board.db.*;

public class WriteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		String upDir = "D:\\JAVA\\Project\\socialfood\\WebContent\\upload\\";
		int limitSize = 3 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request,upDir,limitSize,"utf-8",new DefaultFileRenamePolicy());
		int area = Integer.parseInt(request.getParameter("area"));
		BoardDataBean article = new BoardDataBean();
		BoardDBBean dbPro = BoardDBBean.getInstance();
		String title = multi.getParameter("title");
		String cat = multi.getParameter("category");
		String con = multi.getParameter("content");
		String nick = multi.getParameter("nickname");
		String id = multi.getParameter("id");
		article.setArea(area);
		article.setWdate(new Timestamp(System.currentTimeMillis()));
		article.setCategory(cat);
		article.setTitle(title);
		article.setContent(con);
		article.setNickname(nick);
		article.setId(id);

		if (multi.getParameter("idx") != null) {
			article.setIdx(Integer.parseInt(multi.getParameter("idx")));
			article.setRef(Integer.parseInt(multi.getParameter("ref")));
			article.setStep(Integer.parseInt(multi.getParameter("step")));
			article.setDepth(Integer.parseInt(multi.getParameter("depth")));
		}
		int idx = dbPro.insertArticle(article); 
		if (idx != 0) {
			String oriFileName = multi.getOriginalFileName("filename");
			if (oriFileName != null) {
				BoardFileBean fileBean = new BoardFileBean();
				String fileName = multi.getFilesystemName("filename");
				File file = multi.getFile("filename");
				String fileFullName = upDir + fileName;
				long fileSize = file.length();
				fileBean.setFilename(fileFullName);
				fileBean.setFilesize(fileSize);
				fileBean.setFilestep(1);
				fileBean.setIdx(idx);
				dbPro.saveFile(fileBean);
			
			}
		}
		request.setAttribute("area", area );
		return "writePro.jsp";
	}
}
