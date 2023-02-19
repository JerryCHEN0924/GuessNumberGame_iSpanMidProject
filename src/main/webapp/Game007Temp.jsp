<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>期中專題遊戲介面</title>

<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>


  
<script
  src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" ></script>
  
  
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">



<style>



.main-box {

	margin: 50px auto;
	width: 1300px;
	height: 800px;
	padding: 50px;
	box-shadow: 5px 5px 10px #999;
	border: 10px solid gray;
	text-align: center;
	font-size: 1.3em;
	border-radius: 0%;
	
}




</style>


<script>



	$(function(){
		
	//對話框
	let dialog1 = $("<div>test123456789</div>").dialog({
			autoOpen: false, modal:true, title:"遊戲說明", width:800
	});
	
	$("#showit").click(function(event){
		
		event.preventDefault();
				
		dialog1.html("<h3>電腦會產生五個隨機亂數【0 - 9】，<br>請玩家們分別在五個輸入欄中，<br>填入【0 - 9】的數字，進行猜數字遊戲。<br>好久沒人猜對五個數字了呢！<br>各位玩家們，加油好嗎！？<br><br>備註：儲存遊戲紀錄後將重啟一場新遊戲。</h3>").dialog("open");
		
		
		//return false;
		
		
	});
	
	
	
	
	
	
	})

	
	
	
	
	
	
	
	
	
	

</script>






</head>


<body class="" style="background:#7895B2" >



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
          <a class="nav-link active" aria-current="page" href="#">你猜啊</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">你快猜啊</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            你怎還不猜啊
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">加油好嗎！？</a></li>
            <li><a class="dropdown-item" href="#">加油好嗎！？</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">加油好嗎！？</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">你是不是猜不出來啊</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="我猜！我猜！我猜猜猜！" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>





</header>

<main>

<div class="container border mt-5 w-75" style="height: 800px">

	<div class="row mt-5">
	
	
		
		<div class="offset-sm-3 col-sm-6 form-floating w-50 my-5 ">
		  <textarea class="form-control h3" placeholder="" id="gamingTextarea" style="height: 300px" disabled></textarea>
		  <label for="gamingTextarea"><br>${ responseMsg }</label>
		</div>
		
		
		
		
			<div class="offset-sm-2 col-sm-8 my-2 p-5 border rounded-3 shadow bg-body">
				<form class="row g-3" method="post" action="<c:url value='/CheckGuessNumberServletTemp' />">
											<%--action屬性值為 http://localhost:8080/ServletJSP/Jsp04_13.jsp?insert --%>
											
					<div class="col-md-2">
					    <label for="guess1" class="form-label">數字一</label>
					    <input type="number" name="guess1" class="form-control" id="guess1" placeholder="0-9" value="" maxlength="1" min="0" max="9">
				  	</div>
				  	<div class="col-md-2">
					    <label for="guess2" class="form-label">數字二</label>
					    <input type="number" name="guess2" class="form-control" id="guess2" placeholder="0-9" value="" maxlength="1" min="0" max="9">
				  	</div>
				  	<div class="col-md-2">
					    <label for="guess3" class="form-label">數字三</label>
					    <input type="number" name="guess3" class="form-control" id="guess3" placeholder="0-9" value="" maxlength="1" min="0" max="9">
				 	 </div>
				  	<div class="col-md-2">
					    <label for="guess4" class="form-label">數字四</label>
					    <input type="number" name="guess4" class="form-control" id="guess4" placeholder="0-9" value="" maxlength="1" min="0" max="9">
				  </div>
				  <div class="col-md-2">
					    <label for="guess5" class="form-label">數字五</label>
					    <input type="number" name="guess5" class="form-control" id="guess5" placeholder="0-9" value="" maxlength="1" min="0" max="9">
				  </div>
				  
				  <div class="col-12 g-5">
				  
				  	<a id="showit" class="col-2 btn btn-success mx-3" href="#" title="查看詳細內容">遊戲規則</a>
				    
				    <a class="col-2 btn btn-warning mx-3" href="<c:url value='/gameRestartTemp'/>">重新開始</a>
				    
				    <a class="col-3 btn btn-secondary mx-3" href="<c:url value='/SavePlayersGamingRecordTemp'/>">紀錄本次遊戲紀錄</a>
				    
				    <button type="submit" class="col-2 btn btn-primary mx-3 float-end">檢視猜測數字</button>
				    
				  </div>
					
				</form>
				
				
				
			</div>			
		</div>




</div>











</main>


</body>
</html>