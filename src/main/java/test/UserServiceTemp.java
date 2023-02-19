package test;

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

public class UserServiceTemp {
	
	//檢查使用者是否已通過身分驗證?
	public static boolean  isAuthenticated(HttpServletRequest request) {
		
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
	
	
	
	
	//檢查使用者是否已通過身分驗證?
		public static boolean  isManagerAuthenticated(HttpServletRequest request) {
			
			boolean authenticated =false;
			
			String authenticatedUsername = (String) request.getSession().getAttribute("authenticated");
			String managerUsername = "topgun";
			
			
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
					
					//要滿足管理者的帳號相符才給過
					if(autoLoginUsername.equals(managerUsername)) {
						
					authenticated = true;
					
					}
					
				}
				
				
				
			}
			else //2 當使用者已通過身分驗證
				
				//要滿足管理者的帳號相符才給過
				if(authenticatedUsername.equals(managerUsername)) {
					
					authenticated = true;
					
				}
			
			
			return authenticated;
		}
			
			
	
	
	
	
	
	//進行使用者身分驗證
	public static boolean userAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");//中文請求參數值(必要)!
		
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");    	
    	
		boolean authenticated = false;
		
		
		try {			
			//使用JNDI三部曲
			//抓取JNDI(Java Naming and Directory Interface)的DataSource資源
			//情境A：每次本方法被呼叫時，就去抓取這個DataSource
			InitialContext ic = new InitialContext();
			
			Context context = (Context) ic.lookup("java:/comp/env");
			DataSource ds = (DataSource) context.lookup("jdbc/midrush");
			
			//DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/midrush");
			
			
			/* /META-INF/context.xml
			 * <?xml version="1.0" encoding="UTF-8"?>
			 * <Context> 
			 * 	<Resource name="jdbc/midrush" type="javax.sql.DataSource"
			 * 		driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
			 * 		url="jdbc:sqlserver://localhost:1433;databaseName=midrush;" 
			 * 		username="sa" password="as" />
			 * </Context>
			 */
			
			//情境B：應用程式啟動後即抓取這個DataSource並儲存成應用程式的屬性，以分享給所有的Servlet來使用(ServletContextListener01類別實作了此一需求)
			//DataSource ds = (DataSource)request.getServletContext().getAttribute("ds-sqlsrv-northwind");
			
			String sql = "SELECT [Username],[Password]"
					+ "  FROM [dbo].[memberstemp]"
					+ "  WHERE Username = ? and Password = ?";
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				authenticated = true;
				
				request.getSession().setAttribute("authenticated", username);
				
				//未來是否要自動登入
				String autoLogin = request.getParameter("auto-login");
				
				//當使用者在登入表單也設定了「將來擬自動登入」
				if (autoLogin != null) {
					Cookie cookie = new Cookie("auto-login", username);
//					cookie.setMaxAge(60*60*24*14); //單位:秒
					cookie.setMaxAge(60*3); //單位:秒
					response.addCookie(cookie);//在回應訊息裡，添加set-cookie標頭，讓瀏覽器在前端建立auto-login的cookie
					
				}
				
				//conn.close();//將connection釋放回連線池				
			}
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線		
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
