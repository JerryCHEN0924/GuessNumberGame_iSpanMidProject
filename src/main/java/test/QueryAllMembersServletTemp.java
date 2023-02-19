package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/QueryAllMembersServletTemp")
public class QueryAllMembersServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		MemberService memberService = new MemberServiceImpl_Hibernate();
		
		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		
//		Collection<Member> coll = memberService.findAll();
//		
//		request.setAttribute("AllMembers", coll);
//		
//		RequestDispatcher rd = request
//				.getRequestDispatcher("/showAllMembersTemp.jsp");
//		rd.forward(request, response);
//		return;
		
		
		//追加分頁功能
		List<Member> allMembers = memberService.findAll();// 請求所有搜尋結果
		
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


		request.getRequestDispatcher("/showAllMembersTemp.jsp").forward(request, response);
		
//		response.sendRedirect(request.getContextPath() + "/showAllMembersTemp.jsp");

		//response.sendRedirect("/showAllMembersTemp.jsp");
		
		return;
		
		
		
		
		
		
		
		
		
		
		
		
	}


}
