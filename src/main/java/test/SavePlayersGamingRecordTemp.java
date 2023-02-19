package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/SavePlayersGamingRecordTemp")
public class SavePlayersGamingRecordTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

//		HttpSession session = request.getSession();

		// 準備存放錯誤訊息的 List 物件
//		Map<String, String> errorMsg = new HashMap<>();
//		request.setAttribute("ErrorMsg", errorMsg);

		// 1. 讀取使用者輸入資料
//		String username = request.getParameter("correctGuesses.size()");
//		String password = request.getParameter("password");
//		String codename = request.getParameter("codename");
//		String gender = request.getParameter("gender");
//		String birthday = request.getParameter("birthday");
//		String phone = request.getParameter("phone");
//		String email = request.getParameter("email");

		
		
		// 取出 session 中存儲的隨機數字陣列
		
		// 隨機的數字
//        int[] ThisRoundRandomArray = (int[]) session.getAttribute("numbers");
        
//        int correctSize = (int)session.getAttribute("correctSize");
        
       // ArrayList<Integer> correctNumbers = (ArrayList<Integer>) session.getAttribute("correctNumbers");
        
//        ArrayList<Integer> correctNumbers = (ArrayList<Integer>) request.getSession().getAttribute("correctNumbers");
        
//        int[] username = (int[]) session.getAttribute("authenticated");
        
        //ArrayList<Integer> dealerNumbers = (ArrayList<Integer>) session.getAttribute("dealerNumbers");
        
//        ArrayList<Integer> dealerNumbers = (ArrayList<Integer>) request.getSession().getAttribute("dealerNumbers");
        
		
		//玩家帳號
		String username = (String) request.getSession().getAttribute("authenticated");
		//玩家猜對個數
		Integer correctSize = (Integer) request.getSession().getAttribute("correctSize");
		//玩家猜對數字
		ArrayList<Integer> correctNumbers = (ArrayList<Integer>) request.getSession().getAttribute("correctNumbers");
		//玩家猜測數字
		ArrayList<Integer> playersCards = (ArrayList<Integer>) request.getSession().getAttribute("playersCards");
		//電腦隨機數
		ArrayList<Integer> dealerNumbers = (ArrayList<Integer>) request.getSession().getAttribute("dealerNumbers");
		
		
		//String dealerNumbers = (String) request.getSession().getAttribute("dealerNumbers");
		
		
		
		String correctNumbersString = correctNumbers.toString();
		
		String playersCardsString = playersCards.toString();
		
		String dealerNumbersString = dealerNumbers.toString();
		
		
		
		
		
//		response.getWriter().println(username + "<br><br>");
//        
//        response.getWriter().println(correctSize + "<br><br>");
//        
//        response.getWriter().println(correctNumbers + "<br><br>");
//        
//        response.getWriter().println(playersCards + "<br><br>");
//        
//        response.getWriter().println(dealerNumbers + "<br><br>");
        
        
        
        
        
		
		
		//response.getWriter().println("You guessed " + correctCount + " numbers correctly!");
        
        
//		GamingRecordService grs = new GamingRecordServiceImpl_Jdbc();
//		
//		
//        Timestamp gamingTime = new Timestamp(System.currentTimeMillis());
//        
//        GamingRecord gamingRecord = new GamingRecord(null, username, correctSize, correctNumbers, playersCards, dealerNumbers, gamingTime);
//        
//        GamingRecord gr = grs.save(gamingRecord);
		
		
		
//        request.getRequestDispatcher("/gameRestartTemp").forward(request, response);
        
        
        
		String sql = "INSERT INTO [dbo].[GamingRecordList] ([Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime])"
				+ "     VALUES (?,?,?,?,?,?)";
		
		
		try {
			
			
			InitialContext ic = new InitialContext();
			Context context = (Context) ic.lookup("java:/comp/env");
			DataSource ds = (DataSource) context.lookup("jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			Timestamp gamingTime = new Timestamp(System.currentTimeMillis());
			
			pstmt.setString(1,username);
			pstmt.setInt(2,correctSize);
			pstmt.setString(3,correctNumbersString);
			pstmt.setString(4,playersCardsString);
			pstmt.setString(5,dealerNumbersString);
			pstmt.setTimestamp(6,gamingTime);
			
			int count = pstmt.executeUpdate();
			
			if(count == 0) {
				
				throw new RuntimeException("新增失敗.");
				
			}
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
		} catch (NamingException | SQLException e) {
			
			e.printStackTrace();
			
			
		}
        
        
//		request.getSession().invalidate();
		
		
		response.sendRedirect(request.getContextPath() + "/gameRestartTemp");
		
		//找不到???
		//request.getRequestDispatcher(request.getContextPath() + "/Game007Temp.jsp").forward(request, response);
        
        
		
		
		

	}

}
