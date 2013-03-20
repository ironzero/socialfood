package my.controller;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import my.action.*;
public class ControllerAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	// 명령어와 명령어 처리 클래스를 저장
	// 명령어와 처리 클래스 매핑 되어있는 properties파일 읽어서 map객체에 저장
	// 명령어와 처리 클래스 매핑 되어있는 properties파일은 CommandPro.properties
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		//web.xml에서 propertyconfig에 해당하는 패러미터 읽어옴
		Properties pr = new Properties();
//		String realpath = config.getServletContext().getRealPath(".");
		FileInputStream f = null;
		try{
			f = new FileInputStream(props);
			//commandpro 파일 읽어옴
			pr.load(f);
			//읽어온 파일을 프로퍼티객체 저장
		}catch(IOException ex){
			throw new ServletException(ex);
		}finally{
			if(f!=null){
				try{
					f.close();
				}catch(IOException ex){
					System.out.println("파일스트림 닫는 오류" + ex);
				}
			}
		}
		Iterator<Object> keyIter = pr.keySet().iterator();
		//iterator 객체는 enumeration 객체를 확장시킨 객체.. 더 좋은거?
		while(keyIter.hasNext()){
			
			String command = (String)keyIter.next();
			
			String className = pr.getProperty(command);
			try{
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
				//commandmap객체에 객체 저장
			}catch(ClassNotFoundException ex){
				throw new ServletException(ex);
			}catch(InstantiationException ex){
				throw new ServletException(ex);
			}catch(IllegalAccessException ex){
				throw new ServletException(ex);
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		requestPro(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		requestPro(request,response);
	}
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String view = null;
		CommandAction com = null;
		try{
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0){
				//command = command.substring(request.getContextPath().length());
			}
			System.out.println(command);
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Throwable ex){
			throw new ServletException(ex);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}


