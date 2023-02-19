package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/QueryPersonalAllGamingRecordServletTemp")
public class QueryPersonalAllGamingRecordServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String username = (String)request.getSession().getAttribute("authenticated");
		
		GamingRecordService gamingRecordService = new GamingRecordServiceImpl_Jdbc();
		
//		List<GamingRecord> allRecords = gamingRecordService.findByUsername(username);
		
		
		
		
//		request.setAttribute("AllRecords", allRecords);
//		
//		
//		request.getRequestDispatcher("/showPersonalAllGamingRecordTemp.jsp").forward(request, response);
//		
//		
//		return;
		
//		List<GamingRecord> allRecords = gamingRecord;// 請求所有搜尋結果
		List<GamingRecord> allRecords = gamingRecordService.findByUsername(username);// 請求所有搜尋結果
		
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


		request.getRequestDispatcher("/showPersonalAllGamingRecordTemp.jsp").forward(request, response);

		return;
		
		
		
	}


}
