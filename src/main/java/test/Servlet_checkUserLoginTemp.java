package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_checkUserLoginTemp")
public class Servlet_checkUserLoginTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//檢查使用者是否已通過身分驗證?
		if( !UserServiceTemp.isAuthenticated(request) ) {//當使用者未通過身分驗證
			//重新導向「登入表單」
			response.sendRedirect(request.getContextPath() + "/_login-form.jsp");
			return;
			
		}
		//當使用者已通過身分驗證
		String username = (String) request.getSession().getAttribute("authenticated");
		String message = String.format("<h4>%s您好</h4><h6>歡迎光臨...</h6>%s"
				, username, "<a href='Servlet_processLogoutTemp' class='btn btn-danger'>登出</a>");
		
		//request.setAttribute("message", message);
		//request.getRequestDispatcher("/WEB-INF/views/response-message.jsp").forward(request, response);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/views/responseMessage.jsp").forward(request, response);
		
		
	}
}
