package JerryServlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "FilterLoginADCheckTemp", urlPatterns = {"", "/JerryViews/AD_upload-form.jsp"}, /* urlPatterns用以設定攔截的路徑(*表示全部路徑) */
			initParams = { @WebInitParam(name="login-form-url", value="/JerryViews/AD_login-form.jsp"),
						   @WebInitParam(name="login-processing-url", value="/Servlet_processADLogin")} )
public class FilterLoginADCheckTemp extends HttpFilter implements Filter { //HttpFilter類別始於JavaEE 8(Servlet 4.0)	
	private static final long serialVersionUID = 1L;
	
	private String loginFormUrl, loginProcessingUrl;	
	
	//下列由Eclipse自動產生的init(FilterConfig)方法須拿掉，否則this.getFilterConfig()會回傳null
	//	@Override //javax.servlet.GenericFilter.init(FilterConfig)
	//	public void init(FilterConfig filterConfig) throws ServletException {
	//		
	//	}
	
	@Override //javax.servlet.GenericFilter.init()
	public void init() {
		loginFormUrl = this.getFilterConfig().getInitParameter("login-form-url");
		loginProcessingUrl = this.getFilterConfig().getInitParameter("login-processing-url");
	}
	
	@Override //javax.servlet.http.HttpFilter.doFilter()
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//(1) 使用者對「登入表單」與「處理登入」以外的請求，皆須進行身分驗證
		
		//*
		
		if(!req.getServletPath().equals(loginFormUrl) && !req.getServletPath().equals(loginProcessingUrl) ) {	
			
			//當使用者尚未通過身份驗證時，將之重新導向登入表單	
			if(!ADLoginService.isAuthenticated(req)){				
				res.sendRedirect(req.getContextPath() + loginFormUrl);
				return;
			}
		}
		
		//*/
		
		//(2)設定請求與回應之預設字元編碼
		//request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");

		//(3)測量使用者的請求的處理時間
		long begin = System.currentTimeMillis();
		
		//以上為Serlvet's service()方法的前置作業
		//---------------------------------------------------------------------------------//		
		
		chain.doFilter(request, response);
		
		//---------------------------------------------------------------------------------//
		//以下為Serlvet's service()方法的後置作業		
		long end = System.currentTimeMillis();
		
		String message = String.format("Filter01：客戶端的請求(%s)共處理了%d毫秒\n", req.getRequestURL(),end-begin);
		this.getServletContext().log(message);	//將指定的訊息以「Tomcat日誌」的方式輸出至主控台。訊息如下：
		//7月 14, 2022 9:20:18 下午 org.apache.catalina.core.ApplicationContext log
		//資訊: Filter01：客戶端的請求(http://localhost:8080/ServletJSP/index.jsp)共處理了62毫秒
		
	}
}
