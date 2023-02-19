<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
	
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="<c:url value='/favicon.ico' />">
<meta charset="UTF-8" />	
<title>JSTL</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />
<script src="javascripts/jquery-3.6.0.min.js"></script>

<sql:setDataSource dataSource="jdbc/midrush" /><%-- 使用JNDI資源 --%>

<%-- 注意：如果請求參數值包含中文且請求方法為POST時，你必須設定請求訊息的body的字元編碼。(因為Tomcat預設請求訊息的body的字元編碼為ISO-8859-1(又稱Latin-1或「西歐語言」)) --%>
<%-- (第一招)在web.xml設定<request-character-encoding>UTF-8</request-character-encoding> 或 (第二招)如下：--%>
<% request.setCharacterEncoding("utf-8"); %>

</head>
<body class="" style="background:#7895B2">
	<div class="container w-75 mt-5">
		<h3 class="text-center">新玩家會員資料註冊</h3>
		<div class="row">
			<div class="offset-sm-3 col-sm-6 my-5 p-5 border shadow bg-body">
				<form method="post" action="<c:url value='/RegisterMemberServletTemp' />">
											<%--action屬性值為 http://localhost:8080/ServletJSP/Jsp04_13.jsp?insert --%>
					
					<div class="mb-3">
						<label for="name" class="form-label">帳號</label><font color='red' size='-1'>${ErrorMsg.username}</font>
						<input class="form-control" type="text" name="username" value="${param.username }" />
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">密碼</label><font color='red' size='-1'>${ErrorMsg.password}</font>
						<input class="form-control" type="text" name="password" value="${param.password }" />
					</div>
					<div class="mb-3">
						<label for="birthdate" class="form-label">暱稱</label><font color='red' size='-1'>${ErrorMsg.codename}</font>
						<input class="form-control" type="text" name="codename" value="${param.codename }" />
					</div>
					<div class="mb-3">
						<label for="gender" class="form-label">性別</label><font color='red' size='-1'>${ErrorMsg.gender}</font>
						<input class="form-control" type="text" name="gender" value="${param.gender }" />
					</div>
					<div class="mb-3">
						<label for="birthday" class="form-label">生日</label><font color='red' size='-1'>${ErrorMsg.birthday}</font>
						<input class="form-control" type="text" name="birthday" value="${param.birthday }" />
					</div>
					<div class="mb-3">
						<label for="phone" class="form-label">電話號碼</label><font color='red' size='-1'>${ErrorMsg.phone}</font>
						<input class="form-control" type="text" name="phone" value="${param.phone }" />
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email</label><font color='red' size='-1'>${ErrorMsg.email}</font>
						<input class="form-control" type="text" name="email" value="${param.email }" />
					</div>					
					<div class="mb-3">
						<button type="submit" class="btn btn-danger">註冊</button>
						<a class="btn btn-primary" href="<c:url value='/index.jsp'/>">返回首頁</a>
					</div>
					
				</form>
			</div>			
		</div>
	</div>	
</body>
</html>
