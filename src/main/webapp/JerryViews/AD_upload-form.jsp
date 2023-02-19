<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="<c:url value='/favicon.ico' />">
<meta charset="UTF-8">
<title>廣告商文案</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function() {
		//當選檔變更時，立即預覽之前被選擇的照片
		$("#AdPic").change(function() {
			$("#img-preview").attr("src", "");
			previewImg(this.files);
		});
	});
	function previewImg(files) {
		if (files.length == 0)
			return;
		var file = files[0];
		var fr = new FileReader();
		//註冊當選檔被讀取完成後之事件處理器
		fr.onload = function() {
			$("#img-preview").attr({
				src : fr.result
			});
			/*  fr.result: The file's contents. 內容如下:
			    data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA ...
			 */
		};
		fr.readAsDataURL(file);
	}
	$(function() {
		//當使用者上傳一個檔案後將進入Web Server回應的新頁面。
		//又當使用者「返回前頁」時，需要「重新預覽」前回點選擬上傳的圖片。
		previewImg($("AdPic")[0].files);
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="offset-sm-3 col-sm-6 my-5 p-5 border shadow">
				<h3 class="text-center">廣告上傳</h3>
				<form method="post" action="<c:url value='/AduploadProcess' />"
					enctype="multipart/form-data" id="form1">
					<div class="mb-3">
						<label for="username" class="form-label">廣告文案</label> <input
							class="form-control" type="text" name="Adtxt" id="Adtxt"
							placeholder="請輸入廣告文案" required autofocus />
					</div>
					<div class="mb-3">
						<div class="row">
							<div class="col-sm-9">
								<label for="uploadedFile" class="form-label">上傳照片</label> <input
									class="form-control" type="file" name="AdPic"
									id="AdPic" accept="image/*" />
							</div>
							<div class="col-sm-3">
								<div class="form-control" style="height: 100px;">
									<img src="" id="img-preview" class="h-100 w-100" />
								</div>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-danger">上傳</button>
					<a class="btn btn-secondary" href="<c:url value='/index.jsp'/>">返回首頁</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
