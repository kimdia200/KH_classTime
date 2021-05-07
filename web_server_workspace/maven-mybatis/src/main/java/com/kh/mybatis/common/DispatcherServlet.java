package com.kh.mybatis.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, AbstractController> urlControllerMap = new HashMap<>();

	/**
	 * Servlet 생성시 최초 1회 생성 사용자 요청 주소 - controller 객체를 binding 할 수 있도록 정보를 가진 map객체
	 * 생성
	 * 
	 * /student/selectOneStudent.do -> SelectOneStudentController연결
	 * /student/insertStudent.do -> InsertStudentController연결
	 */
	public void init(ServletConfig config) throws ServletException {
		Properties prop = new Properties();
		String fileName = DispatcherServlet.class.getResource("/url-command.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// url-pattern:String - Controller객체:AbstractController
		Set<String> keys = prop.stringPropertyNames();// 키값만 모아줌
		for (String key : keys) {
			String value = prop.getProperty(key);// 패키지 포함 클래스 풀네임
			// controller객체화 : new SomeController();
			try {
				// 클래스 객체에 : forName메서드에 패키지 포함 클래스명을 넣어주면 해당 클래스를 하나 리턴
				//java reflection api
				Class clazz = Class.forName(value);
				AbstractController controller = (AbstractController) clazz.newInstance();

				// urlControllerMap에 추가
				urlControllerMap.put(key, controller);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.out.println("urlControllerMap = " + urlControllerMap);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * doGet이건 doPost건 doGet에서 분기처리함
		 */
		// 1. 요청주소 가져오기
		String uri = request.getRequestURI(); // maven-mybatis/student/insertStudent.do 와 같은걸 리턴
		int beginIndex = request.getContextPath().length();
		String url = uri.substring(beginIndex);

		// 2. controller객체 호출
		AbstractController controller = urlControllerMap.get(url);
		if (controller == null) {
			throw new ControllerNotFoundException(url + "에 해당하는 controller가 없습니다.");
		}

		String method = request.getMethod();
		String viewName = null;
		switch(method) {
		case "GET" : viewName = controller.doGet(request, response); break;
		case "POST" : viewName = controller.doPost(request, response); break;
		default: throw new MethodNotAllowedException(method);
		}

		// 3. jsp forwarding 또는 redirect 처리
		if (viewName != null) {
			if (viewName.startsWith("redirect:")) {
				// redirect
				String location = request.getContextPath() + viewName.replace("redirect:", "");
				response.sendRedirect(location);
			} else {
				// jsp forwarding
				final String prefix = "/WEB-INF/views/";
				final String suffix = ".jsp";
				request.getRequestDispatcher(prefix + viewName + suffix).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
