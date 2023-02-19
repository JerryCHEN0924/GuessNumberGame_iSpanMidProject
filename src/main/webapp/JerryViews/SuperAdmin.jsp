<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廣告商後台管理</title>

<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="" style="background: #7895B2">

	<header>

		<nav class="navbar navbar-expand-lg navbar-light bg-light">

			<div class="container-fluid">

				<a class="navbar-brand" href="index.jsp">首頁</a>

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">

					<span class="navbar-toggler-icon"></span>

				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">

					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="<c:url value='/QueryAllMembersServletTemp' />">後臺管理區查詢全部會員註冊資料</a>
						</li>

						<li class="nav-item"><a class="nav-link  active"
							href="<c:url value='/QueryAllGamingRecordServletTemp' />">後臺管理區查詢全部會員遊戲紀錄</a>
						</li>


					</ul>



					<form class="d-flex">
						<c:if
							test="${ UserServiceTemp.isAuthenticated(pageContext.request) }">
							<a class="btn btn-outline-primary" type="button"
								href="<c:url value='/Servlet_processLogoutTemp' />">${'會員：【' += sessionScope.authenticated += '】' }
								登出</a>
						</c:if>
					</form>

				</div>
			</div>
		</nav>

	</header>


	<main>

		<div class="container border w-75 mt-5">

			<div class="row text-center m-3">

				<h2>廣告商資料管理系統</h2>



			</div>
		</div>


		<div class="row">

			<%-- <div class="offset-sm-1 col-sm-10"> --%>
			<div class="offset-sm-1 col-sm-10">

				<table
					class="table table-primary table-striped table-bordered table-hover bg-light bg-gradient w-100 m-1 text-center">

					<c:choose>

						<c:when test="${empty allacount}">
							<font color='red'>查無任何會員資料</font>
						</c:when>

						<c:otherwise>

							<tbody>
								<tr>
									<c:forEach var="ad" varStatus="statusX" items="${allacount}">

										<c:if test="${statusX.first}">

											<tr class="align-middle">
												<th>編號</th>
												<th>帳號</th>
												<th>密碼</th>
												<th>管理</th>
											</tr>

										</c:if>

										<tr class="align-middle">

											<td>${ad.ADID}</td>
											<td>${ad.account}</td>
											<td>${ad.password}</td>
											<td><a class="btn btn-warning float-center"
												href="<c:url value='/FindADIDServlet?id=${ad.ADID}' />">修改</a></td>

										</tr>

									</c:forEach>
								</tr>
							</tbody>
						</c:otherwise>

					</c:choose>

				</table>

			</div>

		</div>

	</main>

</body>
</html>