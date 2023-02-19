<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="<c:url value='/favicon.ico' />">
<meta charset="UTF-8" />
<title>廣告商註冊</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />
<script src="javascripts/jquery-3.6.0.min.js"></script>

<sql:setDataSource dataSource="jdbc/midrush" />


<%
request.setCharacterEncoding("utf-8");
%>

</head>
<body class="" style="background: #7895B2">
	<div class="container">
		<div class="row">
			<div class="offset-sm-3 col-sm-6 my-5 p-5 border shadow bg-body">
					<h3 class="text-center">廣告商註冊</h3>
				<form method="post"
					action="${pageContext.request.requestURL += '?insert' }">

					<div class="mb-3">
						<label for="name" class="form-label">帳號</label><font color='red'
							size='-1'>${ErrorMsg.username}</font> <input class="form-control"
							type="text" pattern=".{0,10}" title="不得超過10個字" name="account"
							value="" />
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">密碼</label><font
							color='red' size='-1'>${ErrorMsg.password}</font> <input
							class="form-control" type="text"
							pattern="(?=.*[a-z])(?=.*[A-Z]).{5,10}"
							title="至少包含一個大寫與小寫的英文字,最少5個字,不得超過10個字" name="password"
							value="" />
					</div>
					<div class="mb-3">
						<button type="submit" class="btn btn-danger">註冊</button>
						<a class="btn btn-primary" href="<c:url value='/index.jsp'/>">返回首頁</a>
						<a class="btn btn-primary" href="<c:url value='/JerryViews/AD_login-form.jsp'/>">我要登入</a>
					</div>
					<%--...................................................................................--%>
					<c:if test="${ param.insert != null}">
						<c:catch var="error">
							<sql:update var="affectedRowCount">
							  	insert into AdAcc(Username,Password) values(?,?)
					<sql:param value="${param.account }"></sql:param>
								<sql:param value="${param.password }"></sql:param>
							</sql:update>
						</c:catch>
						<c:choose>
							<c:when test="${error == null && affectedRowCount > 0 }">
								<span id="msg" class="text-primary">廣告商資料新增成功!</span>
							</c:when>
							<c:otherwise>
								<span id="msg" class="text-danger">廣告商資料新增失敗(
									${error.message} )</span>
							</c:otherwise>
						</c:choose>
						<script>
							setTimeout(function() {
								$("#msg").remove();
							}, 10000);
						</script>
					</c:if>

				</form>
			</div>
		</div>
	</div>
</body>
</html>
