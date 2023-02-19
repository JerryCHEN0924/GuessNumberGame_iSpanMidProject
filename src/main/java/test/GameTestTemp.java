package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;






@WebServlet("/GameTestTemp")
public class GameTestTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 重大錯誤
		//變數字首記得要小寫 變數字首記得要小寫 變數字首記得要小寫 jsp網頁端要取資料時變數需要小寫 不然讀取不到
		//欄位名稱要小心打錯 欄位名稱要小心打錯 欄位名稱要小心打錯 都是拼字惹得禍
		//尤其是設計欄位名稱的時候 英文拼字就發生錯誤了 然後雖然自動產生SQL CRUD 語句語法都沒問題
		//但是coding的時候 就自認為是某個單字 殊不知設計欄位的英文都打錯了 結果就是 coding 的欄位名稱才是正確的
		//結果除錯除到最後才發現是一開始設計的問題 乾。。。
		//學我者生，似我者死
		//模仿、臨摹他人的程式碼後，更需要理解其原理，不然後果就是花了整天再除錯。。。最後發現問題竟然這麼可笑
		//陌生的程式碼、風格，再理解之後。更要轉換回自己的風格，自己的骨隨裡的記憶才是最珍貴的
		//今天浪費了一整天再除錯。。。WTF。。。
		
		
		
		
		
		
		
		request.setCharacterEncoding("UTF-8");
		
		
//		String sql = "SELECT [GameingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DelearNumbers],[GamingTime]"
//				+ "  FROM [dbo].[GamingRecordList]";
//		
//		String message = "99999999999";
//		
//		
//		try {
//			
//			
//			InitialContext ic = new InitialContext();
//			
//			Context context = (Context) ic.lookup("java:/comp/env");
//			DataSource ds = (DataSource) context.lookup("jdbc/midrush");
//			
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				response.getWriter().print(rs.getInt("GameingRecordId") + "\t");
//				response.getWriter().print(rs.getString("Username") + "\t");
//				response.getWriter().print(rs.getInt("CorrectSize") + "\t");
//				response.getWriter().print(rs.getString("CorrectNumbers") + "\t");
//				response.getWriter().print(rs.getString("PlayerNumbers") + "\t");
//				response.getWriter().print(rs.getString("DelearNumbers") + "\t");
//				response.getWriter().print(rs.getTimestamp("GamingTime") + "\n");
//				
//				
//			}
//			
//			
//			
//			
//			
//			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
//			
//		} catch (NamingException e) {
//			
//			e.printStackTrace();
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			
//		}
		
		
		
		
		
		
		
		//response.getWriter().print("666666666666666");
		
		
		//response.getWriter().print(message);
		
		
//	}
		
		String sql2 = "SELECT [GamingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime]"
				+ "  FROM [dbo].[GamingRecordList]";
		
		List<GamingRecord> gamingRecord = new ArrayList<>();
		
		
		try {
			
			
			InitialContext ic = new InitialContext();
			
			Context context = (Context) ic.lookup("java:/comp/env");
			DataSource ds = (DataSource) context.lookup("jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				GamingRecord gr = new GamingRecord();
				
				gr.setGamingRecordId(rs.getInt("GamingRecordId"));
				gr.setUsername(rs.getString("Username"));
				gr.setCorrectSize(rs.getInt("CorrectSize"));
				gr.setCorrectNumbers(rs.getString("CorrectNumbers"));
				gr.setPlayerNumbers(rs.getString("PlayerNumbers"));
				gr.setDealerNumbers(rs.getString("DealerNumbers"));
				gr.setGamingTime(rs.getTimestamp("GamingTime"));
				
				
				gamingRecord.add(gr);
				
			}
			
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		request.setAttribute("AllRecords", gamingRecord);
		
		request.getRequestDispatcher("/showAllGamingRecordTemp.jsp").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doGet(request, response);
		
		
		
	}

}
