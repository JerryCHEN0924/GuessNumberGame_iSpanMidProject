package test;

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




@WebServlet("/UpdateGamingRecordServletTemp")
public class UpdateGamingRecordServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession httpSession = request.getSession();
		
		Map<String, String> errorMsg = new HashMap<>();
		
		request.setAttribute("ErrorMsg", errorMsg);
		
		
		
		String modify = request.getParameter("finalDecision");
		
		String id = request.getParameter("gamingRecordId");
		
		int iid = Integer.parseInt(id);
		
		
//		MemberService memberService = new MemberServiceImpl_Hibernate();
//		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		
		GamingRecordService gamingRecordService = new GamingRecordServiceImpl_Jdbc();
		
		
		
		
		

		if (modify.equalsIgnoreCase("DELETE")) {
			
			try {
				
				
				gamingRecordService.delete(iid);
				httpSession.setAttribute("modify", "刪除成功");
				
				
			} catch (RecordNotFoundException ex) {
				httpSession.setAttribute("error", "要刪除的紀錄不存在，鍵值: " + ex.getId());
			} catch (Exception ex) {
				httpSession.setAttribute("error", "刪除時發生異常: " + ex.getMessage());
			}
			
			
		} else if (modify.equalsIgnoreCase("UPDATE")) {
			
			String username = request.getParameter("username");
			
			String correctSize = request.getParameter("correctSize");
			Integer correctSizeInt = Integer.parseInt(correctSize);
			
			String correctNumbers = request.getParameter("correctNumbers");
			String playerNumbers = request.getParameter("playerNumbers");
			String dealerNumbers = request.getParameter("dealerNumbers");
			//String gamingTime = request.getParameter("gamingTime");
			
//			String expericnceStr = request.getParameter("experience");

			// 2. 進行必要的資料轉換
			
//			int experience = 0;
//			
//			try {
//				experience = Integer.parseInt(expericnceStr.trim());
//			} catch (NumberFormatException e) {
//				errorMsg.put("experience", "工作經驗欄格式錯誤，應該為整數");
//			}
			
			
			// 3. 檢查使用者輸入資料
			if (username == null || username.trim().length() == 0) {
				errorMsg.put("username", "帳號欄必須輸入");
			}
			if (correctSize == null || correctSize.trim().length() == 0) {
				errorMsg.put("correctSize", "猜對個數欄必須輸入");
			}
			if (correctNumbers == null || correctNumbers.trim().length() == 0) {
				errorMsg.put("correctNumbers", "猜對數字欄必須輸入");
			}
			if (playerNumbers == null || playerNumbers.trim().length() == 0) {
				errorMsg.put("playerNumbers", "猜測數字欄必須輸入");
			}
			
//			java.sql.Date date = null;
//			if (birthday != null && birthday.trim().length() > 0) {
//				try {
//					date = SystemUtils.toSqlDate(birthday);
//				} catch (Exception e) {
//					errorMsg.put("birthday", "生日欄格式錯誤");
//				}
//			}
			
			if (dealerNumbers == null || dealerNumbers.trim().length() == 0) {
				errorMsg.put("dealerNumbers", "隨機數字欄必須輸入");
			}
			
//			if (gamingTime == null || gamingTime.trim().length() == 0) {
//				errorMsg.put("gamingTime", "遊戲時間欄必須輸入");
//			}
			
			
//			if (experience < 0) {
//				errorMsg.put("experience", "工作經驗欄應為正整數或 0 ");
//			}
			
			
			if (!errorMsg.isEmpty()) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/updatePlayerRecordByIdTemp.jsp");
				rd.forward(request, response);
				return;
				
			}
			
//			Member mem = new Member(null, userId, password, name, phoneNo, experience, date, registerTime);
//			Member member = new Member(iid, username, password, codename, gender, date, phone, email, null);
			
			GamingRecord gr = new GamingRecord(iid, username, correctSizeInt, correctNumbers, playerNumbers, dealerNumbers, null);
			
			gr.setGamingRecordId(iid);
			
			try {
				
				GamingRecord grTemp = gamingRecordService.findById(iid);
				
				gr.setGamingTime(grTemp.getGamingTime());
				
				gamingRecordService.update(gr);
				
				httpSession.setAttribute("modify", "修改成功");
				
			} catch (RecordNotFoundException ex) {
				
				httpSession.setAttribute("error", "要修改的紀錄不存在，鍵值: " + ex.getId());
				
			} catch (Exception ex) {
				
				httpSession.setAttribute("error", "修改時發生異常: " + ex.getMessage());
				
			}

		}
		response.sendRedirect(request.getContextPath() + "/QueryAllGamingRecordServletTemp");
		
		
		
		
		
		
		
		
		
		
		
	}

}
