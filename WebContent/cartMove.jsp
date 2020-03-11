<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h1{
text-align: center;
font-size: 200px;
}
input{
margin-left: 100px;
font-size: 100px;
}
</style>
</head>
<body>
	<form method="post">
	<h1> 장바구니 담기 성공</h1>
	<input type="submit" value="내 장바구니 보기" formaction="showcart">
	<input type="submit" value="쇼핑 계속 하기" formaction="access">
	</form>
</body>
</html>