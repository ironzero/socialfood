package my.travel.action;

import java.io.File;
import java.sql.*;

import javax.servlet.http.*;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

import my.action.*;
import my.travel.db.*;

public class WriteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		String upDir = "D:\\JAVA\\Project\\socialtravel\\WebContent\\upload\\";
		int limitSize = 3 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request,upDir,limitSize,"utf-8",new DefaultFileRenamePolicy());
		TravelDataBean article = new TravelDataBean();
		TravelDBBean dbPro = TravelDBBean.getInstance();
		
			String oriFileName =  multi.getOriginalFileName("filename");
			if(oriFileName != null){
				TravelFileBean fileBean = new TravelFileBean();
				String fileName = multi.getFilesystemName("filename");
				File file= multi.getFile("filename");
				String fileFullName = upDir + fileName;	
				long fileSize = file.length();
				fileBean.setFilename(fileFullName);
				fileBean.setFilesize(fileSize);
				fileBean.setIdx(dbPro.getIdxNum());
				dbPro.saveFile(fileBean);
		}
		article.setIdx(Integer.parseInt(multi.getParameter("idx")));
		article.setArea(Integer.parseInt(multi.getParameter("area")));
		article.setWdate(new Timestamp(System.currentTimeMillis()));
		article.setCategory(multi.getParameter("category"));
		article.setTitle(multi.getParameter("title"));
		article.setContent(multi.getParameter("content"));
		article.setNickname(multi.getParameter("nickname"));
		article.setRef(Integer.parseInt(multi.getParameter("ref")));
		article.setStep(Integer.parseInt(multi.getParameter("step")));
		article.setDepth(Integer.parseInt(multi.getParameter("depth")));
		article.setId(multi.getParameter("id"));
		dbPro.insertArticle(article);
		return "writePro.jsp";
	}
}
