<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type="text/css">
h1 {
	text-align: center;
	color: white;
	background-color: black;
}

html, body {
	height: 95%;
}

div {
	border: 1px solid grey;
	margin: 5px;
}

#header {
	width: 98%;
	height: 20%;
}

#middle {
	width: 98%;
	height: 70%;
	overflow: hidden; /* 공식1 */
	border: 1px solid grey;
	position: relative; /* 공식2 */
	display: inline-flex;
}

#menu {
	width: 18%;
	height: 95%;
	text-align: center;
	float: left;
}

#main {
	width: 80%;
	height: 95%;
	float: left;
	overflow: auto; /* 상품이 많으면 스크롤 생성 */
	/* padding-top: 22px;
	padding-bottom: 22px; */
	text-align: center;
}

#footer {
	width: 98%;
	height: 20%;
	text-align: center;
	float: left;
	overflow: auto;
	color: white;
	background-color: black;
}
</style>
</head>
<body>
	<h1>My Shop</h1>
	<header>
		<div id="header">
			<jsp:include page="header.jsp"></jsp:include>
		</div>
	</header>
	<div id="middle">
		<div id="menu"></div>
		<div id="main"></div>
	</div>
	<footer>
		<div id="footer">
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</footer>
</body>
<script type="text/javascript">
/* if(${id!=null}){ //로그인 된경우
	Aj("menu","#menu");
	if(${page==null}){
		Aj("newitem","#main"); //new item.jsp page
	}else{						//best item.jsp page
		Aj("${page}","#main"); 
	}
} */
Aj("menu","#menu");
//if(${id!=null}){ 세션 검사
if(${page==null}){
	Aj("newitem","#main"); //new item.jsp page
}else{						//best item.jsp page
	Aj("${page}","#main"); 
}
//}
function Aj(url,position) {
	$.ajax({
		url: url,
		type:"get",
		dataType:"html",
		success:function(page){
			$(position).html(page);
		},
		error:function(error){
			console.log(error);
		}
	});//ajax end
}
</script>
</html>