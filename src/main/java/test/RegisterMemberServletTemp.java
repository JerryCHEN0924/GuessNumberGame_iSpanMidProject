package test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegisterMemberServletTemp")
public class RegisterMemberServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 準備存放錯誤訊息的 List 物件
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMsg);
		
		// 1. 讀取使用者輸入資料
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String codename = request.getParameter("codename");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
//		int experience = 0;
		
		// 2. 進行必要的資料轉換
		
//		if (expericnceStr == null || expericnceStr.trim().length() == 0) {
//			errorMsg.put("experience", "工作經驗欄必須輸入");
//		} else {
//			try {
//				experience = Integer.parseInt(expericnceStr.trim());
//			} catch (NumberFormatException e) {
//				errorMsg.put("experience", "工作經驗欄格式錯誤，應該為整數");
//			}
//		}
		
		
		
		// 3. 檢查使用者輸入資料
		if (username == null || username.trim().length() == 0) {
			errorMsg.put("username", "帳號欄必須輸入");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsg.put("password", "密碼欄必須輸入");
		}
		if (codename == null || codename.trim().length() == 0) {
			errorMsg.put("codename", "暱稱欄必須輸入");
		}
		
		if (gender == null || gender.trim().length() == 0) {
			errorMsg.put("gender", "性別欄必須輸入");
		}
		
		java.sql.Date date = null;
		
		if (birthday != null && birthday.trim().length() > 0) {
			try {
				date = SystemUtils.toSqlDate(birthday);
			} catch (Exception e) {
				errorMsg.put("birthday", "生日欄格式錯誤 xxxx-xx-xx");
			}
		}
		
//		if (birthday != null && birthday.trim().length() > 0) {
//			errorMsg.put("birthday", "生日欄必須輸入");
//		}
		
		if (phone == null || phone.trim().length() == 0) {
			errorMsg.put("phone", "電話號碼欄必須輸入");
		}
		
		if (email == null || email.trim().length() == 0) {
			errorMsg.put("email", "Email欄必須輸入");
		}
		
		
//		if (experience < 0) {
//			errorMsg.put("experience", "工作經驗應該為正整數或 0 ");
//		}
		
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/registerMemberTemp.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 4. 進行 Business Logic 運算
//		MemberService ms = new MemberServiceImpl_Hibernate(); 
		MemberService ms = new MemberServiceImpl_Jdbc();  // JDBC 
		
		if (ms.existsByUserId(username)) {
			
			errorMsg.put("username", "該代號 (" + username + ") 已經存在，請換新的代號");
			
		} else {
			
			Timestamp registerTime = new Timestamp(System.currentTimeMillis());
			
			Member mem = new Member(null, username, password, codename, gender, date, phone, email,registerTime);
			
			try {
				
				Member m = ms.save(mem);
				System.out.println(m);
				
			} catch (Exception e) {
				
				errorMsg.put("username", "儲存資料時發生錯誤，請檢查，例外=" + e.getMessage());
				
				e.printStackTrace();
			}
		}
		
		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		
		HttpSession session = request.getSession();
		session.setAttribute("userIdKey", username);  
		session.setAttribute("name", codename);  
		
		if (errorMsg.isEmpty()) {
			
			response.sendRedirect(request.getContextPath() + "/registerSuccessTemp.jsp");  
			return;
			
		} else {
			
			RequestDispatcher rd = request.getRequestDispatcher("/registerMemberTemp.jsp");
			rd.forward(request, response);
			return;
			
		}
		
		
	}

}
