<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廣告商資料更新</title>

<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
	function confirmDelete(userId) {
		var result = confirm("確定刪除此筆記錄(會員代號：" + userId + ")?");
		if (result) {
			document.forms[0].finalDecision.value = "DELETE";
			return true;
		}
		return false;
	}
	function confirmUpdate(userId) {
		var result = confirm("確定送出此筆記錄(會員代號：" + userId + ")?");
		if (result) {
			document.forms[0].finalDecision.value = "UPDATE";
			return true;
		}
		return false;
	}
</script>
</head>

<body class="" style="background: #7895B2">
	<div class="container w-75 mt-5">
		<div class="row">
			<h3 class="text-center">廣告商資料更新</h3>
			<div class="offset-sm-3 col-sm-6 my-3 p-5 border shadow bg-body">
				<div>
					<p>
						<c:if test="${not empty sessionScope.modify}">
							<font color='blue'>${sessionScope.modify}</font>
							<c:remove var="modify" scope="session" />
						</c:if>
						
						<c:if test="${not empty sessionScope.error}">
							<font color='red'>${sessionScope.error}</font>
							<c:remove var="error" scope="session" />
						</c:if>
						<br>
				</div>

				<form method="post"
					action="<c:url value='/UpdateByPlayerServletTemp' />">
					<div class="mb-3">
						<input class="form-control" type="hidden" name="id"
							value="${member.playerId }${param.playerId }" /> <input
							class="form-control" type="hidden" name="finalDecision" value="" />
					</div>
					<div class="mb-3">
						<label for="username" class="form-label">帳號</label><font
							color='red' size='-1'>${ErrorMsg.username}</font> <input
							class="form-control" type="text" name="account"
							value="${member.username }${param.username }" />
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">密碼</label><font
							color='red' size='-1'>${ErrorMsg.password}</font> <input
							class="form-control" type="text" name="password"
							value="${member.password }${param.password }" />
					</div>
					<div class="mt-4 float-end">

						<button type="submit" class="btn btn-success" value="更新"
							name='updateBtn'
							onclick="return confirmUpdate('${member.codename}');">更新資料</button>

						<a class="btn btn-primary" href="<c:url value='/index.jsp'/>">返回首頁</a>
					</div>

					<c:if test="${not empty requestScope.modify}">
						<c:remove var="member" scope="request" />
					</c:if>

				</form>
			</div>
		</div>
	</div>

</body>
</html>