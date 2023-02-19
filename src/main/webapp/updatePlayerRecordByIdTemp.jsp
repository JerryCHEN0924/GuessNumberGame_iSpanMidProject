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



<script type="text/javascript">

	function confirmDelete(username) {
		var result = confirm("確定刪除此筆記錄(會員代號：" + username + ")?");
		if (result) {
			document.forms[0].finalDecision.value = "DELETE";
			return true;
		}
		return false;
	}
	function confirmUpdate(username) {
		var result = confirm("確定送出此筆記錄(會員代號：" + username + ")?");
		if (result) {
			document.forms[0].finalDecision.value = "UPDATE";
			return true;
		}
		return false;
	}
	
</script>



</head>

			
<body class="" style="background:#7895B2">



<div class="container w-75 mt-5">

		
		<div class="row">
		
		<h3 class="text-center">遊戲紀錄管理介面</h3>
		
			<div class="offset-sm-3 col-sm-6 my-3 p-5 border shadow bg-body">
			
			
				<form method="post" action="<c:url value='/UpdateGamingRecordServletTemp' />">
											<%--action屬性值為 http://localhost:8080/ServletJSP/Jsp04_13.jsp?insert --%>
											
					<%--
					<input type="hidden" name="id" value="${member.playerId}${param.playerId}"> 
					<input type="hidden" name="username" value="${member.username}${param.username}">
					<input type="hidden" name="password" value="${member.password}${param.password}"> 
					<input type="hidden" name="finalDecision" value="">
					--%>
					
					<div class="mb-3">
						<input class="form-control" type="hidden" name="gamingRecordId" value="${record.gamingRecordId }${param.gamingRecordId }" />
						<input class="form-control" type="hidden" name="finalDecision" value="" />
					</div>
					<div class="mb-3">
						<label for="username" class="form-label">帳號</label><font color='red' size='-1'>${ErrorMsg.username}</font>
						<input class="form-control" type="text" name="username" value="${record.username }${param.username }" />
					</div>
					<div class="mb-3">
						<label for="correctSize" class="form-label">猜對個數</label><font color='red' size='-1'>${ErrorMsg.correctSize}</font>
						<input class="form-control" type="text" name="correctSize" value="${record.correctSize }${param.correctSize }" />
					</div>
					<div class="mb-3">
						<label for="correctNumbers" class="form-label">猜對數字</label><font color='red' size='-1'>${ErrorMsg.correctNumbers}</font>
						<input class="form-control" type="text" name="correctNumbers" value="${record.correctNumbers }${param.correctNumbers }" />
					</div>
					<div class="mb-3">
						<label for="playerNumbers" class="form-label">猜測數字</label><font color='red' size='-1'>${ErrorMsg.playerNumbers}</font>
						<input class="form-control" type="text" name="playerNumbers" value="${record.playerNumbers }${param.playerNumbers }" />
					</div>
					<div class="mb-3">
						<label for="dealerNumbers" class="form-label">隨機數字</label><font color='red' size='-1'>${ErrorMsg.dealerNumbers}</font>
						<input class="form-control" type="text" name="dealerNumbers" value="${record.dealerNumbers }${param.dealerNumbers }" />
					</div>
					<div class="mt-4 float-end">
						
						<button type="submit" class="btn btn-success" value="更新" name='updateBtn' onclick="return confirmUpdate('${record.username}');">更新資料</button>
						<button type="submit" class="btn btn-danger" value="刪除" name='deleteBtn' onclick="return confirmDelete('${record.username}');">刪除資料</button>
						
						<a class="btn btn-primary" href="<c:url value='/QueryAllGamingRecordServletTemp'/>">返回上一頁</a>
					</div>
					
					<c:if test="${not empty requestScope.modify}">
					<c:remove var="record" scope="request" />
					</c:if>
					
				</form>
			</div>			
		</div>
	</div>	





</body>
</html>