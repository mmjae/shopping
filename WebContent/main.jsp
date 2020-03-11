<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#list{
	width:30%; 
	float: left;
	height: 100px;
	border: 1px solid black;
	}
	#detail{
	display: none; width: 30%; height 20% ; border: 0.5px solid black;}
	#detail.open{display: block; color: black;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<h1>main</h1>
<div id="detail">
</div>
${pListHtml}
<script type="text/javascript">
function detail(pCode) {
	//$("#detail").toggleClass("open");
	$("#detail").toggle();
	console.log(pCode);
	$.ajax({
		url:"ajaxDetail",
		type:"get",
		data:{pCode:pCode},
		dataType:"json",
		success:function(data){
			console.log(data);
			$("#detail").html("상품코드:"+data.p_code+"<br/>"
					+"상품명:"+data.p_name+"<br/>"
					+"가격:"+data.p_price+"<br/>"
					+"재고량:"+data.p_qty+"<br/>"
					+"등록일:"+data.p_date+"<br/>"
					+"등록자:"+data.p_id+"<br/>");
		},
		error:function(error){
			console.log(error);
		}
	});
}
</script>
</body>
</html>