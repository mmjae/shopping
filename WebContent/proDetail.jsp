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
	img{
	width: 250px;
	height: 200px;
	}
	.cnt{
	width: 40px;
	}
	</style>
</head>
<body>
<form method="post">

<div></div>
수량:<input type="text" name="cnt" class="cnt" value="0" id="cnt">
<input type="button" value="▲" id="add">
<input type="button" value="▼" id="sub">
<input type="submit" value="장바구니" formaction="cart">
<input type="submit" value="바로 구매" formaction="buy">
<!-- <input type="hidden" name="p_code" id="p_code"> -->
</form>
<hr>
	<script type="text/javascript">
		var json = ${detail};
		console.log(json);
			$("div").append("주문번호:"+ json.p_code+"<br/>");
			$("div").append("판매자ID:"+json.p_id+"<br/>");
			$("div").append("가격:"+ json.p_price+"원<br/>");
			$("div").append("상품이름:"+ json.p_name+"<br/>");
			$("div").append("상품 설명:"+ json.p_contents+"<br/>");
			$("div").append("<img src='upload/"+json.p_sysFileName+"'>");
			$("div").append("<input type='hidden' name='p_code' value='"+json.p_code+"'>");
			
			$("#add").on("click",function(){   
			    var n=$("#add").index(this);
			    console.log(n);
			   var num=$("#cnt:eq("+n+")").val();
			    num=$("#cnt:eq("+n+")").val(num*1+1);}); 
			 
			 $("#sub").on("click",function(){
			    var n=$("#sub").index(this);
			   var num=$("#cnt:eq("+n+")").val();
			   num=$("#cnt:eq("+n+")").val(num*1-1);}); 
	</script>
</body>
</html>