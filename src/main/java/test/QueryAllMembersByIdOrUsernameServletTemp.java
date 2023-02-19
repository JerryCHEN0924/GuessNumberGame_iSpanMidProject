package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/QueryAllMembersByIdOrUsernameServletTemp")
public class QueryAllMembersByIdOrUsernameServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String namelike = request.getParameter("namelike");
		String id = request.getParameter("id");
//		int iid = Integer.parseInt(id);
		
		
//		MemberService memberService = new MemberServiceImpl_Hibernate();
		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		
//		Collection<Member> coll = memberService.findAll();
		List<Member> coll = memberService.findAllByIdOrUsername(id, namelike);
		
//		request.setAttribute("AllMembers", coll);
//		RequestDispatcher rd = request
//				.getRequestDispatcher("/showAllMembersTemp.jsp");
//		rd.forward(request, response);
		
//		List<GamingRecord> allRecords = gamingRecordService.findAll();// 請求所有搜尋結果
		List<Member> allMembers = coll;// 請求所有搜尋結果
		
		int pageSize = 10;
		int totalPages = (int) Math.ceil((double) allMembers.size() / pageSize);
		int page = 1;
		
		try {
			
		    page = Integer.parseInt(request.getParameter("page"));
		    
		} catch (NumberFormatException e) {
		    // 如果沒有指定頁碼，則使用預設值
		}
		
		int startIndex = (page - 1) * pageSize;
		
		int endIndex = Math.min(startIndex + pageSize, allMembers.size());
		
		List<Member> recordsForCurrentPage = allMembers.subList(startIndex, endIndex);
		
		request.setAttribute("AllMembers", recordsForCurrentPage);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPage", page);


//		request.getRequestDispatcher("/showAllGamingRecordForPageTemp.jsp").forward(request, response);
		
		request.getRequestDispatcher("/showAllMembersTemp.jsp").forward(request, response);

		return;
		
		
		
//		return;
		
		
		
	}


}
