package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

@WebServlet("/QuerryForRankingServletTemp")
public class QuerryForRankingServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		
		
//		String sqltest = "SELECT Username,AVG(CAST(CorrectSize AS INT)) AS AverageCorrectSize,SUM(CAST(CorrectSize AS INT)) AS TotalCorrectSize,MIN(GamingTime) AS FirstGamingTime,MAX(GamingTime) AS LastGamingTime \r\n"
//				+ "FROM GamingRecordList\r\n"
//				+ "GROUP BY Username\r\n"
//				+ "ORDER BY AverageCorrectSize DESC";
		
		
		
		String sql = "SELECT Username,AVG(CAST(CorrectSize AS INT)) AS AverageCorrectSize,SUM(CAST(CorrectSize AS INT)) AS TotalCorrectSize,MIN(GamingTime) AS FirstGamingTime,MAX(GamingTime) AS LastGamingTime"
				+ "  FROM GamingRecordList "
				+ "  GROUP BY Username"
				+ "  ORDER BY AverageCorrectSize DESC";
		
		
		List<GamingRecord> gamingRecord = new ArrayList<>();
		
		
		try {
			
			InitialContext ic = new InitialContext();
			
			Context context = (Context) ic.lookup("java:/comp/env");
			DataSource ds = (DataSource) context.lookup("jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
//				GamingRecord gr = new GamingRecord();
				
				
				String username = rs.getString("Username");
				
				int averageCorrectSize = rs.getInt("AverageCorrectSize");
				
				int totalCorrectSize = rs.getInt("TotalCorrectSize");
				
				Timestamp firstGamingTime = rs.getTimestamp("FirstGamingTime");
				
				Timestamp lastGamingTime = rs.getTimestamp("LastGamingTime");
				
				GamingRecord gr = new GamingRecord(username, averageCorrectSize, totalCorrectSize, firstGamingTime, lastGamingTime);
				
				
//				gr.setGamingRecordId(rs.getInt("GamingRecordId"));
//				gr.setUsername(rs.getString("Username"));
//				gr.setCorrectSize(rs.getInt("CorrectSize"));
//				gr.setCorrectNumbers(rs.getString("CorrectNumbers"));
//				gr.setPlayerNumbers(rs.getString("PlayerNumbers"));
//				gr.setDealerNumbers(rs.getString("DealerNumbers"));
//				gr.setGamingTime(rs.getTimestamp("GamingTime"));
				
//				System.out.println(gr);
				
				gamingRecord.add(gr);
				
			}
			
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
			
			
			
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		
//		request.setAttribute("AllRecords", gamingRecord);
//		
//		
//		request.getRequestDispatcher("/showAllGamingRecordForRankingTemp.jsp").forward(request, response);
		
		
		
//		List<GamingRecord> allRecords = gamingRecordService.findAll();// 請求所有搜尋結果
		List<GamingRecord> allRecords = gamingRecord;// 請求所有搜尋結果
		
		int pageSize = 10;
		int totalPages = (int) Math.ceil((double) allRecords.size() / pageSize);
		int page = 1;
		
		try {
			
		    page = Integer.parseInt(request.getParameter("page"));
		    
		} catch (NumberFormatException e) {
		    // 如果沒有指定頁碼，則使用預設值
		}
		
		int startIndex = (page - 1) * pageSize;
		
		int endIndex = Math.min(startIndex + pageSize, allRecords.size());
		
		List<GamingRecord> recordsForCurrentPage = allRecords.subList(startIndex, endIndex);
		
		request.setAttribute("AllRecords", recordsForCurrentPage);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPage", page);


		request.getRequestDispatcher("/showAllGamingRecordForRankingTemp.jsp").forward(request, response);

		return;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}

}
