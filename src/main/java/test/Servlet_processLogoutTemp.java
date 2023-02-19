package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_processLogoutTemp")
public class Servlet_processLogoutTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(UserServiceTemp.isAuthenticated(request)) {//當使用者已通過身分驗證
    		//銷毀目前的Session物件
			request.getSession().invalidate();
			//通知瀏覽器銷毀前端的「auto-login」cookie
			Cookie cookie = new Cookie("auto-login","");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			//重新導向「登入表單」
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
    	else
    		response.sendError(HttpServletResponse.SC_FORBIDDEN);//狀態碼：403    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
