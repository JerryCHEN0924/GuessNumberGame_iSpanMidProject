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




@WebServlet("/UpdateByPlayerServletTemp")
public class UpdateByPlayerServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession httpSession = request.getSession();
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMsg);
		String modify = request.getParameter("finalDecision");
		
		String id = request.getParameter("id");
		
		int iid = Integer.parseInt(id);
//		MemberService memberService = new MemberServiceImpl_Hibernate();
		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC

		if (modify.equalsIgnoreCase("DELETE")) {
			try {
				
				memberService.delete(iid);
				httpSession.setAttribute("modify", "刪除成功");
				
			} catch (RecordNotFoundException ex) {
				httpSession.setAttribute("error", "要刪除的紀錄不存在，鍵值: " + ex.getId());
			} catch (Exception ex) {
				httpSession.setAttribute("error", "刪除時發生異常: " + ex.getMessage());
			}
		} else if (modify.equalsIgnoreCase("UPDATE")) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String codename = request.getParameter("codename");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
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
			if (password == null || password.trim().length() == 0) {
				errorMsg.put("password", "密碼欄必須輸入");
			}
			if (codename == null || codename.trim().length() == 0) {
				errorMsg.put("userName", "暱稱欄必須輸入");
			}
			if (gender == null || gender.trim().length() == 0) {
				errorMsg.put("gender", "性別欄必須輸入");
			}
			
			java.sql.Date date = null;
			if (birthday != null && birthday.trim().length() > 0) {
				try {
					date = SystemUtils.toSqlDate(birthday);
				} catch (Exception e) {
					errorMsg.put("birthday", "生日欄格式錯誤");
				}
			}
			
			if (phone == null || phone.trim().length() == 0) {
				errorMsg.put("phone", "電話號碼欄必須輸入");
			}
			
			if (email == null || email.trim().length() == 0) {
				errorMsg.put("email", "email欄必須輸入");
			}
			
			
//			if (experience < 0) {
//				errorMsg.put("experience", "工作經驗欄應為正整數或 0 ");
//			}
			
			
			if (!errorMsg.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/playerPersonalSearchTemp.jsp");
				rd.forward(request, response);
				return;
			}
			
//			Member mem = new Member(null, userId, password, name, phoneNo, experience, date, registerTime);
			Member member = new Member(iid, username, password, codename, gender, date, phone, email, null);
			member.setPlayerId(iid);
			try {
				Member memberTemp = memberService.findById(iid);
				member.setRegisterTime(memberTemp.getRegisterTime());
				memberService.update(member);
				httpSession.setAttribute("modify", "修改成功");
			} catch (RecordNotFoundException ex) {
				httpSession.setAttribute("error", "要修改的紀錄不存在，鍵值: " + ex.getId());
			} catch (Exception ex) {
				httpSession.setAttribute("error", "修改時發生異常: " + ex.getMessage());
			}

		}
		response.sendRedirect(request.getContextPath() + "/playerPersonalSearchServletTemp");
		
		
		
		
		
		
		
		
		
		
		
	}

}
