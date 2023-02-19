<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="java.util.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
	
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="<c:url value='/favicon.ico' />">
<meta charset="UTF-8" />	
<title>廣告商管理</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />

<sql:setDataSource dataSource="jdbc/ServletProject" /> 

<% request.setCharacterEncoding("utf-8"); %>

<sql:query var="result">
	select account, password from AD where account like ? and password like ? order by 1
	<sql:param value="${'%' += param.account += '%' }"></sql:param>
	<sql:param value="${'%' += param.password += '%' }"></sql:param>
</sql:query>

</head>
<body>
	<div class="container w-75 mt-5">
		<h4 class="text-center">廣告商管理</h4>
		<div class="row">
			<div class="offset-sm-2 col-sm-8 my-5">
				<form method="get" action="${pageContext.request.requestURL}"><%-- pageContext.request.requestURL之值為「本身頁面的網址」 --%>
					<div class="row">
						<label for="ADID" class="col-sm-1 col-form-label text-end">帳號</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="account" name="account" value="${param.account }" />
						</div>
					
						<label for="employeeid" class="col-sm-2 col-form-label text-end">密碼</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="password"	name="password" value="${param.password }" />
						</div>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-danger">查詢</button>
							<a class="btn btn-primary" href="<c:url value='/index.jsp'/>">返回首頁</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="offset-sm-1 col-sm-10">
			<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>						
							<c:forEach var="colName" items="${result.columnNames }">
								<th>${colName}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<%-- 迭代資料表的資料(方法一) --%>
						<c:forEach var="row" items="${result.rows}"><%--result.rows資料型別：SortedMap[]--%>
							<tr>
								<td>${row.account }</td><%--欄位名稱不區分大小寫--%>		
								<td>${row.password }</td>
							</tr>
						</c:forEach>
						<%-- 迭代資料表的資料(方法二) --%>
						<%-- 
						<c:forEach var="row" items="${result.rowsByIndex}">result.rowsByIndex資料型別：Object[][]
							<tr>
								<c:forEach var="col" items="${row}">
									<td>${col}</td>
								</c:forEach>
							</tr>
						</c:forEach>
						 --%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>

