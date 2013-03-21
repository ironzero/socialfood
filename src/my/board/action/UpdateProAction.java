package my.board.action;

import java.io.File;

import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import my.action.*;
import my.board.db.*;

public class UpdateProAction implements CommandAction {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String upDir = "D:\\JAVA\\Project\\socialfood\\WebContent\\upload\\";
		int limitSize = 3 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request, upDir,
				limitSize, "utf-8", new DefaultFileRenamePolicy());
		int idx = Integer.parseInt(multi.getParameter("idx"));
		
		multi.getFile("");
		multi.getFileNames();// Enum

		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardFileBean fileBean = dbPro.getFileName(idx);
		BoardDataBean article = new BoardDataBean();

		// 수정 글에서 올린 파일의 이름을 얻는다.
		String onFileName = multi.getOriginalFileName("filename");// 파라미터 이름이겠지?

		if (onFileName != null) {

			// DB에서 꺼내온 파일과 이름 비교
			String originalFileName = fileBean.getFilename();
			if (originalFileName != null) {

				// 다른 이름일 경우 교체한다.
				if (!onFileName.equals(originalFileName)) {
					fileBean = new BoardFileBean();
					// 웹서버 측에 저장된 파일명 multi.getFilesystemName(formName)
					String fileName = multi.getFilesystemName("filename");// 아직
																			// 저장하지
																			// 않dms?

					/* 새파일 이름저장 */String fileFullName = upDir + fileName;
					fileBean.setFilename(fileFullName);

					/* 새파일 사이즈 */File file = multi.getFile("filename");
					long fileSize = file.length();
					fileBean.setFilesize(fileSize);

					/* 새파일 F.K. */fileBean.setIdx(idx); // 이부분이 다릅니다
					// fileBean.setFilestep(1);
					/* 기존 파일 삭제 */int fileid = fileBean.getFileid();
					dbPro.deleteFile(fileid);

					try { // 상대경로 사용하는 것인가?
						File f = new File("/upload/" + fileName);
						f.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					dbPro.saveFile(fileBean);
				} else {
					System.out.println("새파일과 기존파일  이름이 같은 경우는 일단 그대로 둔다.");
				}
			} else {
				System.out.println("원래 파일이 없는 경우이다.");
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
