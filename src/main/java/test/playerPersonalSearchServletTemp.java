package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/playerPersonalSearchServletTemp")
public class playerPersonalSearchServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String username = (String)request.getSession().getAttribute("authenticated");
//		int iid = Integer.parseInt(id);
//		MemberService memberService = new MemberServiceImpl_Hibernate();
		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		Member member = memberService.findByUsername(username);
		System.out.println(member);
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("/playerPersonalSearchTemp.jsp");
		rd.forward(request, response);
		return;
		
		
	}

	

}
