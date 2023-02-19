package JerryServlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadedPhotoTest")
public class uploadedPhotoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uploadDir;
	
	public void init(){
		 uploadDir = this.getServletContext().getInitParameter("uploadDir");					
	}
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		request.setCharacterEncoding("utf-8");
		String photoFileName = request.getParameter("photo");
		
		if(photoFileName != null){
			response.setContentType("image/png");
			String photoRealPath = this.getServletContext().getRealPath(uploadDir + "/" + photoFileName);			
			try {
				Path inputPath = Paths.get(photoRealPath);	
				Files.copy(inputPath, response.getOutputStream());//進行下載圖片
				//long Files.copy(Path source, OutputStream out) throws IOException
			}
			catch(IOException ex) {//例如：java.nio.file.NoSuchFileException
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.toString());//狀態碼: 500
			}
		}
		else
			response.sendError(HttpServletResponse.SC_BAD_REQUEST); //狀態碼: 400
	}
}
