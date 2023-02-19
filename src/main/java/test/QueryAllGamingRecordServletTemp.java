package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/QueryAllGamingRecordServletTemp")
public class QueryAllGamingRecordServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
//		MemberService memberService = new MemberServiceImpl_Hibernate();
		//MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		GamingRecordService gamingRecordService = new GamingRecordServiceImpl_Jdbc();
		
//		Collection<Member> coll = memberService.findAll();
//		Collection<GamingRecord> coll = gamingRecordService.findAll();
		
//		List<GamingRecord> coll = gamingRecordService.findAll();
		
//		request.setAttribute("AllGamingRecords", coll);
//		
//		request.getRequestDispatcher("/showAllGamingRecordTemp.jsp").forward(request, response);
//		
//		return;
		
		
//		request.setAttribute("AllRecords", coll);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/showAllGamingRecordTemp.jsp");
//		
//		rd.forward(request, response);
//		
//		return;
		
		
//		request.setAttribute("AllRecords", coll);
//		
//		RequestDispatcher rd = request
//				.getRequestDispatcher("/showAllGamingRecordTemp.jsp");
//		rd.forward(request, response);
//		return;
		
		
		List<GamingRecord> allRecords = gamingRecordService.findAll();// 請求所有搜尋結果
		
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


		request.getRequestDispatcher("/showAllGamingRecordTemp.jsp").forward(request, response);

		return;
		
		
		
		
		
		
		//test
		
//		if(coll == null) {
//			
//			response.getWriter().println("他是 NULL");
//			
//		}else {
//			
//			response.getWriter().println("他不是 NULL");
//			
//		}
		
		
		
		
		
		
	}


}
