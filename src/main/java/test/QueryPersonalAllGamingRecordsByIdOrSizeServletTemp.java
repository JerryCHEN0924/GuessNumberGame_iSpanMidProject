package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/QueryPersonalAllGamingRecordsByIdOrSizeServletTemp")
public class QueryPersonalAllGamingRecordsByIdOrSizeServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		String username = (String)request.getSession().getAttribute("authenticated");
		String correctSize = request.getParameter("correctSize");
		String id = request.getParameter("id");
		
//		int iid = Integer.parseInt(id);
		
		
//		MemberService memberService = new MemberServiceImpl_Hibernate();
//		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		
		GamingRecordService gamingRecordService = new GamingRecordServiceImpl_Jdbc();
		
//		Collection<Member> coll = memberService.findAll();
//		Collection<Member> coll = memberService.findAllByIdOrUsername(id, namelike);
		
		
		List<GamingRecord> coll = gamingRecordService.findAllByIdOrSize(id, correctSize, username);
		
//		request.setAttribute("AllRecords", coll);
//		RequestDispatcher rd = request
//				.getRequestDispatcher("/showPersonalAllGamingRecordTemp.jsp");
//		rd.forward(request, response);
//		return;
		
		
		
		
		List<GamingRecord> allRecords = coll;// 請求所有搜尋結果
		
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
