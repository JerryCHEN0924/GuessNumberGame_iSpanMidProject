<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="" style="background:#7895B2">

	<div class="container border w-75 mt-5 ">
	
		<div class="row">
		
		</div>
		
		<div class="row">
		
		</div>
		
		<div class="row">

			<div class="card">
				<div class="card-header text-center h1">會員資料新增成功</div>
				<div class="card-body">
					<h5 class="card-title text-primary h3">親愛的${name}您好，您的會員代號為：${userIdKey}</h5>
					<p class="card-text h3">感謝您加入成為會員，請開始使用本系統.</p>
					<a href="<c:url value='/index.jsp' />" class="btn btn-primary float-end">回首頁</a>
				</div>
			</div>

		</div>
	</div>
</body>
</html>