<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="<c:url value='/favicon.ico' />">
<meta charset="utf-8">
<title>圖片來源</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />

</head>
<body>
	<div class="container border shadow mt-5 w-50 p-0">
		<div class="row">
			<div class="col-sm-12 my-4 ms-5">
				<%-- <a href="#"> <img src="<c:url value='/images/logo.gif' />" /></a> --%>
			</div>
		</div>
		<div class="row">
			<div class="offset-sm-3 col-sm-6 py-3">
				<div class="h3 text-dark text-break">${requestScope.message }</div>
			</div>
		</div>
		<div class="row">
			<div class="offset-sm-9 col-sm-6">
				<button class="btn btn-primary mb-3" type="button"
					onclick="history.back();">返回前頁</button>
				<a class="btn btn-secondary" href="<c:url value='/index.jsp'/>">返回首頁</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="p-2 bg-secondary">
					<%--<a href="#"> <img src="<c:url value='/images/footer_logo.gif' />" /></a> --%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

