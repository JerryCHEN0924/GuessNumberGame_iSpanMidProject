package JerryServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FindADIDServlet")
public class FindADIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		int iid = Integer.parseInt(id);
		AdvertisementService advertisementService = new AdvertisementServiceImpl();
		Advertisement AdById = advertisementService.findById(iid);
		request.setAttribute("account", AdById);
		System.out.println(AdById);
//		response.getWriter().print(AdById);
		RequestDispatcher rd = request.getRequestDispatcher("/JerryViews/updateAD1Temp.jsp");
		rd.forward(request, response);
		return;
		
		
	}

	

}
