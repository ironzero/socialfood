package my.food.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import my.action.CommandAction;
import my.food.db.*;
public class UpdateProAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String upDir = "D:\\JAVA\\Project\\socialfood\\WebContent\\upload\\";
		int limitSize = 50 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request, upDir,
				limitSize, "utf-8", new DefaultFileRenamePolicy());
		int idx = Integer.parseInt(multi.getParameter("idx"));
		System.out.println(idx);
		FoodDBBean dbPro = FoodDBBean.getInstance();
		FoodFileBean fileBean = dbPro.getFileName(idx);
		FoodDataBean article = new FoodDataBean();
		String originalFileName = fileBean.getFilename();
		int fileid = fileBean.getFileid();
		String onFileName = multi.getOriginalFileName("filename");
		if (onFileName.equals(originalFileName)) {

		} else {
			if (onFileName != null) {
				fileBean = new FoodFileBean();
				String fileName = multi.getFilesystemName("filename");
				File file = multi.getFile("filename");
				String fileFullName = upDir + fileName;
				long fileSize = file.length();
				fileBean.setFilename(fileFullName);
				fileBean.setFilesize(fileSize);
				fileBean.setIdx(dbPro.getIdxNum());
				dbPro.deleteFile(fileid);
				try	{ 
					File f = new File("/upload/"+fileName); 
					f.delete(); 
				}catch (Exception e){ 
					e.printStackTrace();	
				}
				dbPro.saveFile(fileBean);
			}
			
		}
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		dbPro.updateArticle(idx, title, content);
		request.setAttribute("idx", idx);
		request.setAttribute("pageNum", multi.getParameter("pageNum"));
		return "updatePro.jsp";

	}
}
