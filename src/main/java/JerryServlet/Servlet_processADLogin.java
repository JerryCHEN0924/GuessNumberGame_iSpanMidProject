package JerryServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_processADLogin")
public class Servlet_processADLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//驗證使用者身份
    	if(ADLoginService.adAuthentication(request, response)) {    		
    		//重新導向「首頁」
    		response.sendRedirect(request.getContextPath() + "/index.jsp"); //亦可使用相對本頁面的路徑(即"index.jsp")
    	}
    	else {
    		//重新導向「登入表單」
    		response.sendRedirect(request.getContextPath() + "/JerryViews/AD_login-form.jsp");//亦可使用相對本頁面的路徑(即"_login-form.jsp")
    	}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
