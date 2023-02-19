package JerryServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ADLoginService {
	
	//檢查使用者是否已通過身分驗證?
	public static boolean isAuthenticated(HttpServletRequest request) {
		
		boolean authenticated =false;
		
		String authenticatedUsername = (String) request.getSession().getAttribute("authenticated");
		
		
		// 1 當使用者尚未通過身分驗證(尚未登入)
		if (authenticatedUsername == null) {
			
			//查看cookie來檢查使用者是否具有自動登入的資格
			//檢查使用者之前是否通過身份驗證且設定了未來擬自動登入?
			Cookie[] cookies = request.getCookies();
			String autoLoginUsername = null;
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("auto-login")) {
						autoLoginUsername = cookie.getValue();
						break;
					}
				}
			}
			
			//當使用者具備有自動登入的資格
			if (autoLoginUsername != null) {
				request.getSession().setAttribute("authenticated", autoLoginUsername);						
				authenticated = true;
			}
	
		}
		else //2 當使用者已通過身分驗證
			authenticated = true;
		
		return authenticated;
	}

	public static boolean isSuperAdmin(HttpServletRequest request) {
		
		boolean authenticated =false;
		
		String authenticatedUsername = (String) request.getSession().getAttribute("authenticated");
		String superAdmin = "topgun";
		
		// 1 當使用者尚未通過身分驗證(尚未登入)
		if (authenticatedUsername == null) {
			
			//查看cookie來檢查使用者是否具有自動登入的資格
			//檢查使用者之前是否通過身份驗證且設定了未來擬自動登入?
			Cookie[] cookies = request.getCookies();
			String autoLoginUsername = null;
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("auto-login")) {
						autoLoginUsername = cookie.getValue();
						break;
					}
				}
			}
			
			//當使用者具備有自動登入的資格
			if (autoLoginUsername != null) {
				request.getSession().setAttribute("authenticated", autoLoginUsername);	
				if(autoLoginUsername.equals(superAdmin))
				authenticated = true;
			}
			
		}
		else //2 當使用者已通過身分驗證
			if(authenticatedUsername.equals(superAdmin)) {
			authenticated = true;
			}
		return authenticated;
	}
	//進行使用者身分驗證
	public static boolean adAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");    		
		boolean authenticated = false;	
		try {			
			InitialContext ic = new InitialContext();
			Context envContext = (Context) ic.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/midrush");
			String sql = "select * from AdAcc where Username=? and Password=?";	
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				authenticated = true;
				
				request.getSession().setAttribute("authenticated", account);
				
				//未來是否要自動登入
				String autoLogin = request.getParameter("auto-login");
				
				//當使用者在登入表單也設定了「將來擬自動登入」
				if (autoLogin != null) {
					Cookie cookie = new Cookie("auto-login", account);
//					cookie.setMaxAge(60*60*24*14); //單位:秒
					cookie.setMaxAge(60*3); //單位:秒
					response.addCookie(cookie);//在回應訊息裡，添加set-cookie標頭，讓瀏覽器在前端建立auto-login的cookie				
				}
							
			}
			conn.close();		
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return authenticated;
	}
		
}
