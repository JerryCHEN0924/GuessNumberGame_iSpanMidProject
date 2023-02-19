package JerryServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QueryAllADaccountServlet")
public class QueryAllADaccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdvertisementService advertisementService = new AdvertisementServiceImpl();
		// 追加分頁功能
		List<Advertisement> allAdvertisement = advertisementService.findAll();// 請求所有搜尋結果

		int pageSize = 10;
		int totalPages = (int) Math.ceil((double) allAdvertisement.size() / pageSize);
		int page = 1;

		try {

			page = Integer.parseInt(request.getParameter("page"));

		} catch (NumberFormatException e) {
			// 如果沒有指定頁碼，則使用預設值
		}

		int startIndex = (page - 1) * pageSize;

		int endIndex = Math.min(startIndex + pageSize, allAdvertisement.size());

		List<Advertisement> recordsForCurrentPage = allAdvertisement.subList(startIndex, endIndex);

		request.setAttribute("allAdAcount", recordsForCurrentPage);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPage", page);

		request.getRequestDispatcher("/JerryViews/showAllAdvertisementTemp.jsp").forward(request, response);
//		response.getWriter().print(allAdvertisement);


		return;
	}

}
