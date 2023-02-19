<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

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

<header>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Index</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">沒作用</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">暫時沒作用</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            還是沒作用
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">你看不到我</a></li>
            <li><a class="dropdown-item" href="#">你還是看不到我</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">你就是看不到我</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">永遠沒作用</a>
        </li>
      </ul>
		<form class="d-flex">
		      	<c:if test="${ UserServiceTemp.isAuthenticated(pageContext.request) }">
		        	<a class="btn btn-outline-primary" type="button" href="<c:url value='/Servlet_processLogoutTemp' />">${'會員：【' += sessionScope.authenticated += '】' } 登出</a>
		        </c:if>
		</form>
    </div>
  </div>
</nav>





</header>


<main>

<div class="container border w-75 my-5">

	<div class="row text-center m-3">
	
	<h2>玩家個人遊戲紀錄查詢</h2>
	
	<div class="offset-sm-1 col-sm-10 my-3">
	
	
			<form method="get" action="<c:url value='/QueryPersonalAllGamingRecordsByIdOrSizeServletTemp' />">
				
				<div class="row">
				
					<label for="id" class="col-sm-2 col-form-label text-end">編號</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="id" id="id" value=""/>
					</div>
					
					<label for="correctSize" class="col-sm-1 col-form-label text-end">個數</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="correctSize" id="correctSize" value=""/>
					</div>
					
					<div class="col-sm-3">
						<button type="submit" class="btn btn-danger">查詢</button>
						<a class="btn btn-primary" href="<c:url value='/index.jsp' />">返回首頁</a>
					</div>
				
				</div>
				
			</form>
		</div>

	</div>
	
	
	<div class="row my-5">
	
		<%-- <div class="offset-sm-1 col-sm-10"> --%>
		<div class="offset-sm-1 col-sm-10">
		
		<table class="table table-primary table-striped table-bordered table-hover bg-light bg-gradient w-100 m-1 text-center">
		
		
		<%
    int currentPage = (int) request.getAttribute("currentPage");
    int totalPages = (int) request.getAttribute("totalPages");
    
    out.print("<ul class='pagination pagination-lg'>");
    
    for (int i = 1; i <= totalPages; i++) {
    	
        if (i == currentPage) {
        	
            out.print("    <li class='page-item'>       <a class='page-link' href='?page=" + i + "'>               " + i + "           </a>     </li>         ");
            
        } else {
        	
        	out.print("    <li class='page-item'>       <a class='page-link' href='?page=" + i + "'>               " + i + "           </a>     </li>         ");
        	
        }
    }
    
    out.print("</ul>");
    
%>
		
		
		
		
		<c:choose>
			
			<c:when test="${empty AllRecords}">
			
    			<font color='red'>查無任何遊戲資料</font>
    			
   			</c:when>
   			 
   			<c:otherwise>
   			
   			
				<tbody>
					<tr>
						<c:forEach var="record" varStatus="statusX" items="${AllRecords}">
						
						<c:if test="${statusX.first}">
						
						<tr class="align-middle">
				
							<th>編號</th>
							<th>帳 號</th>
							<th>玩家猜對個數</th>
							<th>玩家猜對數字</th>
							<th>玩家猜測數字</th>
							<th>電腦隨機數字</th>
							<th>遊戲時間</th>
					
						</tr>
						
						</c:if>
						
						<tr class="align-middle">
			
							<%-- <td><a href="<c:url value='/FindMemberServletTemp?id=${mem.playerId}' />" >${mem.username}</a></td> --%>
							<td>${record.gamingRecordId}</td>
							<td>${record.username}</td>
							<td>${record.correctSize}</td>
							<td>${record.correctNumbers}</td>
							<td>${record.playerNumbers}</td>
							<td>${record.dealerNumbers}</td>
							<td>${record.gamingTime}</td>
							
				
						</tr>
						
						<%--
						<c:if test="${statusX.last}">
						</c:if>
						--%>
					
						</c:forEach>
					</tr>
				</tbody>
   			
   			
   			
   			
   			
   			</c:otherwise>
   			
		
		
		</c:choose>
		
			
			
			</table>
			
		</div>
			
	</div>
	
			
</div>

</main>

</body>
</html>