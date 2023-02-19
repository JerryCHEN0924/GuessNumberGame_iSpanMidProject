<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="<c:url value='/favicon.ico' />">
	<meta charset="UTF-8" />
	<title>登入表單</title>
	<link rel="stylesheet"	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />
</head>
<body class="" style="background:#7895B2">
	<div class="container">
		<div class="row">
			<div class="offset-sm-3 col-sm-6 my-5 p-5 border shadow bg-body">
				<h3 class="text-center">登入表單</h3>
				
				<form method="post" action="<c:url value='/Servlet_processLoginTemp' />">
				
					<div class="mb-3">
						<label for="username" class="form-label">使用者名稱</label> 
						<input class="form-control" type="text" name="username" id="username"
							placeholder="請輸入使用者名稱" value="c3po" />
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">密碼</label>
						<input class="form-control" type="password" name="password"	id="password" 
							placeholder="請輸入密碼" value="c3po" />
					</div>
					<div class="mb-3 form-check">			  			
			  			<input class="form-check-input" type="checkbox" id="auto-login" name="auto-login" />
			  			<label class="form-check-label" for="auto-login">自動登入(Remember Me)</label>
			  		</div>					

					<button type="submit" class="btn btn-primary">登入</button>
					<a class="btn btn-secondary" href="<c:url value='/index.jsp'/>">返回首頁</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
