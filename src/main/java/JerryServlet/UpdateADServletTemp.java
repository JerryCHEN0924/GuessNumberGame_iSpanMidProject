package JerryServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.RecordNotFoundException;




@WebServlet("/UpdateADServletTemp")
public class UpdateADServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession httpSession = request.getSession();
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMsg);
		String modify = request.getParameter("finalDecision");
		
		String id = request.getParameter("id");
		
		int iid = Integer.parseInt(id);
		AdvertisementService advertisementService = new AdvertisementServiceImpl();

		if (modify.equalsIgnoreCase("DELETE")) {
			try {
				
				advertisementService.delete(iid);
				httpSession.setAttribute("modify", "刪除成功");
				
			} catch (RecordNotFoundException ex) {
				httpSession.setAttribute("error", "要刪除的紀錄不存在，鍵值: " + ex.getId());
			} catch (Exception ex) {
				httpSession.setAttribute("error", "刪除時發生異常: " + ex.getMessage());
			}
		} else if (modify.equalsIgnoreCase("UPDATE")) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			
			// 3. 檢查使用者輸入資料
			if (username == null || username.trim().length() == 0) {
				errorMsg.put("username", "帳號欄必須輸入");
			}
			if (password == null || password.trim().length() == 0) {
				errorMsg.put("password", "密碼欄必須輸入");
			}
				
			
			if (!errorMsg.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/JerryViews/updateAD1Temp.jsp");
				rd.forward(request, response);
				return;
			}
			
			
//			Advertisement advertisement = new Advertisement(iid,username,password);
			
//			advertisement.setAdId(iid);
			Advertisement advertisement = new Advertisement(iid,username,password);
			try {
//				advertisement.setAdId(iid);
//				advertisement.setUsername(username);
//				advertisement.setPassword(password);
				advertisementService.update(advertisement);
				httpSession.setAttribute("modify", "修改成功");
			} catch (RecordNotFoundException ex) {
				httpSession.setAttribute("error", "要修改的紀錄不存在，鍵值: " + ex.getId());
			} catch (Exception ex) {
				httpSession.setAttribute("error", "修改時發生異常: " + ex.getMessage());
			}

		}
		response.sendRedirect(request.getContextPath() + "/QueryAllADaccountServlet");
		
		
		
		
		
		
		
		
		
		
		
	}

}
