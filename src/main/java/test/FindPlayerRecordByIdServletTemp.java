package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FindPlayerRecordByIdServletTemp")
public class FindPlayerRecordByIdServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		int iid = Integer.parseInt(id);
//		MemberService memberService = new MemberServiceImpl_Hibernate();
//		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
//		Member member = memberService.findById(iid);
		
		GamingRecordService gamingRecordService = new GamingRecordServiceImpl_Jdbc();
		GamingRecord record = gamingRecordService.findById(iid);
		
		
		System.out.println(record); //小技巧 可以看 record 內有無物件
		request.setAttribute("record", record);
		RequestDispatcher rd = request.getRequestDispatcher("/updatePlayerRecordByIdTemp.jsp");
		rd.forward(request, response);
		return;
		
		
	}

	

}
