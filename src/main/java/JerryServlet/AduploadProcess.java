package JerryServlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

@MultipartConfig
@WebServlet("/AduploadProcess")
public class AduploadProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//圖片處理→進硬碟，文字處理→進資料庫
		// 取得上傳的圖片檔
		Part adPic = request.getPart("AdPic");
		// 如果adpic為空，表示上傳失敗，回應錯誤訊息
		if (adPic == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "adpic為null");
			return;
		}

		// 取得上傳的文字資料
		String adtxt = request.getParameter("Adtxt");
		// 判斷adtxt有沒有內容
		adtxt = adtxt == null ? "" : adtxt;
		//取得上傳的檔案名稱
		String filename = adPic.getSubmittedFileName();
		//取得上傳的檔案大小
		long filesize = adPic.getSize();
		//此段為寫入硬碟
		if("".equals(filename) || filesize <= 0 ){//當用戶端未上傳任何檔案或上傳的檔案內容是empty
			String message = String.format("<h6>您上傳無效的檔案，請重新執行</h6>");
			request.setAttribute("message", message);
		}
		else {
			String uploadDir = "/uploadDir";
			
			//檢查上傳檔案之儲存目錄是否存在?若否,則立即建立。
				//註：Eclipse預設將Web應用程式部署在如下目錄：
				//   _workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ServletJSP
			String uploadDirRealPath = this.getServletContext().getRealPath(uploadDir);
	        File _uploadDir = new File( uploadDirRealPath );	        
	        if(!_uploadDir.exists())
	        	_uploadDir.mkdirs();
	        
	        try {
	        	//SQL上傳文案區
	        	try {
	        	InitialContext initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/midrush");
				Connection conn = ds.getConnection();
				String sql = "insert into AdTxt (Txt) Values (?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, adtxt);
				int affectedRowCount = pstmt.executeUpdate();
				if (affectedRowCount == 1) {
					String message = "檔案上傳成功";
					request.setAttribute("message", message);
//					request.getRequestDispatcher("/JerryViews/Ad_upload-response.jsp").forward(request, response);
				} else {
					String message = "檔案上傳失敗";
					request.setAttribute("message", message);
//					request.getRequestDispatcher("/JerryViews/Ad_upload-response.jsp").forward(request, response);
				}
				
				}catch(Exception e) {
					e.printStackTrace();
				}
	        	
	        	//圖片作業區
	        	Path outputPath = Paths.get(uploadDirRealPath, filename);
	        	Files.copy(adPic.getInputStream(), outputPath,StandardCopyOption.REPLACE_EXISTING);//進行存檔 	        	
	        	//上式若添加第三個參數(StandardCopyOption.REPLACE_EXISTING)，將允許覆蓋舊檔。	        		        	
	        	String message = String.format("<h6>檔案上傳成功</h6>");
		        request.setAttribute("message", message);
				request.getRequestDispatcher("/JerryViews/Ad_upload-response.jsp").forward(request, response);

	        }
	        catch(IOException ex) {	//例如：java.nio.file.FileAlreadyExistsException        	
	        	String message = String.format("<h6>您上傳檔案失敗，請洽系統管理員</h6>");
				request.setAttribute("message", message);
				request.getRequestDispatcher("/JerryViews/Ad_upload-response.jsp").forward(request, response);

	        }	        
	        //-----------------------------------------------------------------------------------//	        	             
		}
		// 此段為寫入資料庫，但很多建議都說不要把圖片放入資料庫，所以先註解掉
//		try {
//			InitialContext initContext = new InitialContext();
//			Context envContext = (Context) initContext.lookup("java:/comp/env");
//			DataSource ds = (DataSource) envContext.lookup("jdbc/ServletProject");
//			Connection conn = ds.getConnection();
//			// SQL語句
//			String sql = "insert into AD_paper (Adtxt,AdPic) Values (?,?)";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, adtxt);
//			pstmt.setBinaryStream(2, adPic.getInputStream(), (int) adPic.getSize());
//			int affectedRowCount = pstmt.executeUpdate();
//			if (affectedRowCount == 1) {
//				String message = "檔案上傳成功";
//				request.setAttribute("message", message);
//				request.getRequestDispatcher("/JerryViews/Ad_upload-response.jsp").forward(request, response);
//			} else {
//				String message = "檔案上傳失敗";
//				request.setAttribute("message", message);
//				request.getRequestDispatcher("/JerryViews/Ad_upload-response.jsp").forward(request, response);
//			}
//			pstmt.close();
//			conn.close();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}
