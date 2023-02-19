<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="<c:url value='/favicon.ico' />">
	<meta charset="UTF-8" />
	<title>廣告列表</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<div class="row">			
			<div class="offset-sm-3 col-sm-6 my-5 p-5 border shadow">
				<table class="table table-bordered table-striped table-hover text-center">
					<caption class="text-center caption-top h5">廣告商上傳的廣告列表</caption>										
					<%
					String uploadDir = application.getInitParameter("uploadDir"); //uploadDir變數值= "/uploadDir"
					Set<String> set = application.getResourcePaths(uploadDir);
					//application.getResourcePaths("/uploadDir") 
					//回傳Web應用程式根目錄下的uploadDir目錄裡的「檔案路徑」集合(Set)。如果該目錄內不具任何檔案，則回傳null。
					//集合(Set)內所包含的「檔案路徑」的格式如下：
					//  /uploadDir/Andrew.png
					//  /uploadDir/Nancy.png
					//  ...					
					if(set != null){					
						for (String imgPath : set) { //imgPath之值如: /uploadDir/Andrew.png
							String imgName = new File(imgPath).getName(); //imgName之值如：Andrew.png
							String tr = String.format("<tr class='align-middle'>"
								+ "<td>%s</td>"
								+ "<td><img src='%s/uploadedPhotoTest?photo=%s' width='48' height='48' class='border rounded-3'/></td>"
								+ "</tr>", imgName, request.getContextPath(), imgName);
							out.println(tr);
							//例如：
							/*　
							<tr class='align-middle'>
							<td>Nancy.png</td>
							<td>
							  <img src='/ServletJSP/Servlet05_05?photo=Nancy.png' 
								width='48' height='48' class='border rounded-3'/>
							</td>
							</tr>
							*/
						}						
					}
					%>					
				</table>
				<button class="btn btn-primary my-2 float-end" type="button"
					onclick="location.href = `<c:url value='/index.jsp' />`">返回首頁</button>	
			</div>
		</div>		
	</div>	
</body>
</html>
