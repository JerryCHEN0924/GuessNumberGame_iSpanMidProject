package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





@WebServlet("/gameRestartTemp")
public class gameRestartTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		
		// 取出 session 中存儲的隨機數字陣列
        int[] ThisRoundRandomArray = (int[]) session.getAttribute("numbers");
		
        
        if(ThisRoundRandomArray != null) {
        	
        	int[] randomArray = CreateNewCards.generateRandomArray();
        	
        	session.setAttribute("numbers", randomArray);
        	
        	String responseMsg = "";
        	
        	session.setAttribute("responseMsg", responseMsg);
        	
        	response.sendRedirect(request.getContextPath() + "/Game007Temp.jsp");
        	
        }
        
        
		
		
		
		
		//response.sendRedirect(request.getContextPath() + "/index.jsp");
		
		
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
    }

}
